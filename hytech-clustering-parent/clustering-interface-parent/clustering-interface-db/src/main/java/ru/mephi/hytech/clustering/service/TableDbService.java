package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.StringListResponse;

public interface TableDbService {

	public StringListResponse getTableNames(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException;
}
