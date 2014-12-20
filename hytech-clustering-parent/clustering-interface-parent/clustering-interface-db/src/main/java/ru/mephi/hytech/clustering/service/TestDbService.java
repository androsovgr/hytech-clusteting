package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public interface TestDbService {

	public BaseResponse testDbConnection(BaseRequest request);
}
