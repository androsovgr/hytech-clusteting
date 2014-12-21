package ru.mephi.hytech.clustering.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.model.MinMax;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.request.MinMaxListRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.DoubleArrayResponse;
import ru.mephi.hytech.clustering.response.MinMaxListResponse;
import ru.mephi.hytech.clustering.service.util.Coortinator;
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
				double[] coordinates = Coortinator.coordinate(r.getUsers().get(0));
				for (double d : coordinates) {
					minMaxs.add(new MinMax(d, d));
				}
				// Find bound cooridinates
				for (Person person : r.getUsers()) {
					coordinates = Coortinator.coordinate(person);
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
			}
			return new DoubleArrayResponse(norma);
		});
	}
}
