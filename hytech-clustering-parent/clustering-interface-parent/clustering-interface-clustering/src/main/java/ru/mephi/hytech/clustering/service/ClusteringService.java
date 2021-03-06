package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.request.ClusteringRequest;
import ru.mephi.hytech.clustering.request.GetBordersRequest;
import ru.mephi.hytech.clustering.request.MinMaxListRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.ClasterListResponse;
import ru.mephi.hytech.clustering.response.DoubleArrayResponse;
import ru.mephi.hytech.clustering.response.MinMaxListResponse;

public interface ClusteringService {

	public MinMaxListResponse getMinMaxCoordinates(PersonListRequest request);

	public DoubleArrayResponse getNorma(MinMaxListRequest request);

	public ClasterListResponse clusterize(ClusteringRequest request);

	public MinMaxListResponse getBorders(GetBordersRequest request);

}
