package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public interface TestDbService {

	public BaseResponse testDbConnection(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException;
}
