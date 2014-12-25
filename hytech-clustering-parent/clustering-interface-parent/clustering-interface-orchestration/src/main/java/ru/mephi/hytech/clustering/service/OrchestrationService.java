package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.ClasterListResponse;
import ru.mephi.hytech.clustering.response.CountRequest;

public interface OrchestrationService {

	public BaseResponse fillDb(CountRequest request);

	public ClasterListResponse clusterize(CountRequest request);
}
