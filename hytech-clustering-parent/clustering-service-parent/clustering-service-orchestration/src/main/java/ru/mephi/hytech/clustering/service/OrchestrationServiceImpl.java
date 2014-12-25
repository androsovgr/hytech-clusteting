package ru.mephi.hytech.clustering.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.model.Cluster;
import ru.mephi.hytech.clustering.model.MinMax;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.request.ClusteringRequest;
import ru.mephi.hytech.clustering.request.GetBordersRequest;
import ru.mephi.hytech.clustering.request.MinMaxListRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.ClasterListResponse;
import ru.mephi.hytech.clustering.response.CountRequest;
import ru.mephi.hytech.clustering.response.DoubleArrayResponse;
import ru.mephi.hytech.clustering.response.MinMaxListResponse;
import ru.mephi.hytech.clustering.response.PersonListResponse;
import ru.mephi.hytech.clustering.service.util.PersonFactory;
import ru.mephi.hytech.clustering.util.ErrorCode;
import ru.mephi.hytech.clustering.util.MethodUtil;

@Stateless
@Local(OrchestrationService.class)
public class OrchestrationServiceImpl implements OrchestrationService {

	private static final PersonFactory pf = new PersonFactory();
	private static final Logger LOGGER = LoggerFactory.getLogger(OrchestrationServiceImpl.class);

	@EJB
	private UserDbService userDbService;
	@EJB
	private ClusteringService clusteringService;

	@Override
	public BaseResponse fillDb(CountRequest request) {
		String methodName = "fillDb()";
		return MethodUtil.processRequest(BaseResponse.class, request, methodName, LOGGER, (r) -> {
			List<Person> persons = new ArrayList<Person>();
			for (int i = 0; i < r.getCount(); i++) {
				persons.add(pf.randomPerson());
			}
			PersonListRequest putPeopleRequest = new PersonListRequest(r.getTransactionId(), persons);
			return userDbService.putPeople(putPeopleRequest);
		});
	}

	@Override
	public ClasterListResponse clusterize(CountRequest request) {
		String methodName = "clasterize()";
		return MethodUtil.processRequest(ClasterListResponse.class, request, methodName, LOGGER,
				(r) -> clusterizePrivate(r));
	}

	private ClasterListResponse clusterizePrivate(CountRequest request) {
		// Get all
		PersonListResponse getAllPeopleResponse = userDbService.getAllPeople(request);
		if (ErrorCode.OK != getAllPeopleResponse.getErrorCode()) {
			return new ClasterListResponse(getAllPeopleResponse.getErrorCode(), getAllPeopleResponse.getErrorMessage());
		}
		// Get boundes
		PersonListRequest personListRequest = new PersonListRequest(request.getTransactionId(),
				getAllPeopleResponse.getUsers());
		MinMaxListResponse minMaxListResponse = clusteringService.getMinMaxCoordinates(personListRequest);
		if (ErrorCode.OK != minMaxListResponse.getErrorCode()) {
			return new ClasterListResponse(minMaxListResponse.getErrorCode(), minMaxListResponse.getErrorMessage());
		}
		// Get norma
		MinMaxListRequest MinMaxListRequest = new MinMaxListRequest(request.getTransactionId(),
				minMaxListResponse.getMinMaxs());
		DoubleArrayResponse normaResponse = clusteringService.getNorma(MinMaxListRequest);
		if (ErrorCode.OK != normaResponse.getErrorCode()) {
			return new ClasterListResponse(normaResponse.getErrorCode(), normaResponse.getErrorMessage());
		}
		// Clusterize
		ClusteringRequest clusterizeRequest = new ClusteringRequest(personListRequest.getUsers(),
				normaResponse.getDs(), (int) request.getCount());
		ClasterListResponse clusterizeResponse = clusteringService.clusterize(clusterizeRequest);
		if (ErrorCode.OK != clusterizeResponse.getErrorCode()) {
			return new ClasterListResponse(clusterizeResponse.getErrorCode(), clusterizeResponse.getErrorMessage());
		}
		// Get Borders
		for (Cluster cluster : clusterizeResponse.getClusters()) {
			GetBordersRequest getBordersRequest = new GetBordersRequest(request.getTransactionId(),
					cluster.getPersons(), normaResponse.getDs());
			MinMaxListResponse getBordersResponse = clusteringService.getBorders(getBordersRequest);
			if (ErrorCode.OK != getBordersResponse.getErrorCode()) {
				return new ClasterListResponse(getBordersResponse.getErrorCode(), getBordersResponse.getErrorMessage());
			}
			cluster.getClusterInfo().setBorders(getBordersResponse.getMinMaxs());
			MinMax minMax1 = cluster.getClusterInfo().getBorders().get(0);
			double radius = (minMax1.getMax() - minMax1.getMin()) / 2;
			for (MinMax minMax : cluster.getClusterInfo().getBorders()) {
				double radiusCurrent = (minMax.getMax() - minMax.getMin()) / 2;
				if (radiusCurrent > radius) {
					radius = radiusCurrent;
				}
			}
			cluster.getClusterInfo().setRadius(radius);
		}
		return clusterizeResponse;
	}
}
