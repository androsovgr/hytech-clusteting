package ru.mephi.hytech.clustering.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.model.Cluster;
import ru.mephi.hytech.clustering.model.ClusterInfo;
import ru.mephi.hytech.clustering.model.MinMax;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.request.ClusteringRequest;
import ru.mephi.hytech.clustering.request.GetBordersRequest;
import ru.mephi.hytech.clustering.request.MinMaxListRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.ClasterListResponse;
import ru.mephi.hytech.clustering.response.DoubleArrayResponse;
import ru.mephi.hytech.clustering.response.MinMaxListResponse;
import ru.mephi.hytech.clustering.service.model.PersonCoordinateModel;
import ru.mephi.hytech.clustering.service.util.Coordinator;
import ru.mephi.hytech.clustering.util.MethodUtil;

@Stateless
@Local(ClusteringService.class)
public class ClusteringServiceImpl implements ClusteringService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClusteringServiceImpl.class);

	@Override
	public MinMaxListResponse getMinMaxCoordinates(PersonListRequest request) {
		String methodName = "getMinMaxCoordinates()";
		return MethodUtil.processRequest(MinMaxListResponse.class, request, methodName, LOGGER, (r) -> {
			List<MinMax> minMaxs = new ArrayList<MinMax>();
			// Initial coordinates
				double[] coordinates = Coordinator.coordinate(r.getUsers().get(0));
				for (double d : coordinates) {
					minMaxs.add(new MinMax(d, d));
				}
				// Find bound cooridinates
				for (Person person : r.getUsers()) {
					coordinates = Coordinator.coordinate(person);
					for (int i = 0; i < coordinates.length; i++) {
						MinMax minMax = minMaxs.get(i);
						if (minMax.getMin() > coordinates[i]) {
							minMax.setMin(coordinates[i]);
						}
						if (minMax.getMax() < coordinates[i]) {
							minMax.setMax(coordinates[i]);
						}
					}
				}

				return new MinMaxListResponse(minMaxs);
			});
	}

	@Override
	public DoubleArrayResponse getNorma(MinMaxListRequest request) {
		String methodName = "getNorma()";
		final double defaultSize = 1000;
		return MethodUtil.processRequest(DoubleArrayResponse.class, request, methodName, LOGGER, (r) -> {
			double[] norma = new double[r.getMinMaxs().size()];
			for (int i = 0; i < r.getMinMaxs().size(); i++) {
				MinMax mm = r.getMinMaxs().get(i);
				double delta = mm.getMax() - mm.getMin();
				if (delta != 0) {
					norma[i] = defaultSize / (mm.getMax() - mm.getMin());
				} else {
					norma[i] = 1;
				}
				// norma[i] = 1;
			}
			return new DoubleArrayResponse(norma);
		});
	}

	@Override
	public ClasterListResponse clusterize(ClusteringRequest request) {
		String methodName = "clusterize()";
		return MethodUtil.processRequest(ClasterListResponse.class, request, methodName, LOGGER,
				(r) -> clusterizePrivate(r));
	}

	@Override
	public MinMaxListResponse getBorders(GetBordersRequest request) {
		String methodName = "getBorders()";
		return MethodUtil.processRequest(MinMaxListResponse.class, request, methodName, LOGGER,
				(r) -> getBordersPrivate(r));
	}

	private MinMaxListResponse getBordersPrivate(GetBordersRequest r) {
		List<MinMax> minMaxs = new ArrayList<MinMax>();
		// Initial coordinates
		double[] coordinates = Coordinator.coordinateNormalized(r.getPersons().get(0), r.getNorma());
		for (double d : coordinates) {
			minMaxs.add(new MinMax(d, d));
		}
		// Find bound cooridinates
		for (Person person : r.getPersons()) {
			coordinates = Coordinator.coordinateNormalized(person, r.getNorma());
			for (int i = 0; i < coordinates.length; i++) {
				MinMax minMax = minMaxs.get(i);
				if (minMax.getMin() > coordinates[i]) {
					minMax.setMin(coordinates[i]);
				}
				if (minMax.getMax() < coordinates[i]) {
					minMax.setMax(coordinates[i]);
				}
			}
		}

		return new MinMaxListResponse(minMaxs);
	}

	private ClasterListResponse clusterizePrivate(ClusteringRequest request) {
		// get coordinates for persons
		List<PersonCoordinateModel> personCoordinateModels = new ArrayList<PersonCoordinateModel>();
		for (Person person : request.getPersons()) {
			double[] coordinates = Coordinator.coordinateNormalized(person, request.getNorma());
			personCoordinateModels.add(new PersonCoordinateModel(person, coordinates));
		}
		LOGGER.info("Got coordinates for person.");
		LOGGER.debug("Got coordinates for person: {}", personCoordinateModels);
		// Handle too much cluster count
		if (request.getClusterCount() > personCoordinateModels.size()) {
			LOGGER.info("Too little clusters count: {}", request.getClusterCount());
			List<Cluster> clusters = new ArrayList<Cluster>();
			for (PersonCoordinateModel personCoordinateModel : personCoordinateModels) {
				List<Person> personsCurrent = new ArrayList<Person>();
				personsCurrent.add(personCoordinateModel.getPerson());
				ClusterInfo clusterInfo = new ClusterInfo();
				clusterInfo.setCenterCoordinates(personCoordinateModel.getCoordinates());
				Cluster cluster = new Cluster(clusterInfo, personsCurrent);
				clusters.add(cluster);
			}
			return new ClasterListResponse(clusters);
		}
		LOGGER.info("Too little clusters count handled.");
		// Get initial centers
		List<Cluster> clusters = new ArrayList<Cluster>();
		for (int i = 0; i < request.getClusterCount(); i++) {
			ClusterInfo clusterInfo = new ClusterInfo();
			clusterInfo.setCenterCoordinates(personCoordinateModels.get(i).getCoordinates());
			clusters.add(new Cluster(clusterInfo, null));
		}
		LOGGER.info("Got initial centers.");
		LOGGER.debug("Got initial centers: {}", clusters);
		// fill clusters
		while (true) {
			// clear cluster
			for (Cluster cluster : clusters) {
				cluster.setPersons(new ArrayList<Person>());
			}
			// fill clusters
			for (PersonCoordinateModel personCoordinateModel : personCoordinateModels) {
				putPersonIntoNearest(personCoordinateModel, clusters);
			}
			// Compare coordinates
			{
				// Get old centers coordinates
				double[][] oldCentersCoordinates = getCoordinateList(clusters);
				// Update centers coordinates
				for (Cluster cluster : clusters) {
					cluster.getClusterInfo().setCenterCoordinates(
							Coordinator.getCenter(cluster.getPersons(), request.getNorma()));
					cluster.getClusterInfo().setCenterCoordinatesUnnormed(Coordinator.getCenter(cluster.getPersons()));
				}
				// Get new centers coordinates
				double[][] newCentersCoordinates = getCoordinateList(clusters);
				// if not changed => exit
				if (Arrays.deepEquals(oldCentersCoordinates, newCentersCoordinates)) {
					break;
				}
			}
		}

		for (Cluster cluster : clusters) {
			cluster.getClusterInfo().setModelCount(cluster.getPersons().size());
		}
		return new ClasterListResponse(clusters);
	}

	private void putPersonIntoNearest(PersonCoordinateModel personCoordinateModel, List<Cluster> clusters) {
		Cluster nearest = clusters.get(0);
		double distanceNearest = Coordinator.getDistance(personCoordinateModel.getCoordinates(), nearest
				.getClusterInfo().getCenterCoordinates());
		for (Cluster cluster : clusters) {
			double disntanceCurrent = Coordinator.getDistance(personCoordinateModel.getCoordinates(), cluster
					.getClusterInfo().getCenterCoordinates());
			if (disntanceCurrent < distanceNearest) {
				distanceNearest = disntanceCurrent;
				nearest = cluster;
			}
		}
		nearest.getPersons().add(personCoordinateModel.getPerson());
	}

	private double[][] getCoordinateList(List<Cluster> clusters) {
		double[][] coordinates = new double[clusters.size()][];
		int i = 0;
		for (Cluster cluster : clusters) {
			coordinates[i] = cluster.getClusterInfo().getCenterCoordinates();
		}
		return coordinates;
	}

}
