package ru.mephi.hytech.clustering.service;

import javax.ejb.EJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.StringListResponse;
import ru.mephi.hytech.clustering.util.MethodUtil;
import ru.mephi.hytech.clustering.util.processor.RequestProcessor;

public class ApiServiceImpl implements ApiService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApiServiceImpl.class);
	@EJB
	private TestDbService testDbService;
	@EJB
	private TableDbService tableDbService;

	@Override
	public BaseResponse testConnection(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException {
		final String methodName = "testConnection";
		return MethodUtil.processRequest(BaseResponse.class, request,
				methodName, LOGGER,
				new RequestProcessor<ConnectionInfoRequest, BaseResponse>() {
					@Override
					public BaseResponse process(ConnectionInfoRequest request)
							throws InstantiationException,
							IllegalAccessException {
						return testDbService.testDbConnection(request);
					}
				});
	}

	@Override
	public StringListResponse getTableNames(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException {
		final String methodName = "getTableNames";
		return MethodUtil
				.processRequest(
						StringListResponse.class,
						request,
						methodName,
						LOGGER,
						new RequestProcessor<ConnectionInfoRequest, StringListResponse>() {
							@Override
							public StringListResponse process(
									ConnectionInfoRequest request)
									throws InstantiationException,
									IllegalAccessException {
								return tableDbService.getTableNames(request);
							}
						});
	}

}
