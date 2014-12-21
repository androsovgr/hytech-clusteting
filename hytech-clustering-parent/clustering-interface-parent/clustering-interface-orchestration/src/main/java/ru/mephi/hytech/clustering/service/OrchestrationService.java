package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.CountRequest;

public interface OrchestrationService {

	public BaseResponse fillDb(CountRequest request);

	public BaseResponse clasterize(BaseRequest request);
}
