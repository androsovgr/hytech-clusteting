package ru.mephi.hytech.clustering.service;

import javax.ejb.EJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.PersonListResponse;
import ru.mephi.hytech.clustering.util.MethodUtil;

public class ApiServiceImpl implements ApiService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApiServiceImpl.class);
	@EJB
	private TestDbService testDbService;
	@EJB
	private UserDbService userDbService;

	@Override
	public BaseResponse testConnection(BaseRequest request) {
		final String methodName = "testConnection";
		return MethodUtil.processRequest(BaseResponse.class, request,
				methodName, LOGGER, (r) -> {
					return testDbService.testDbConnection(r);
				});
	}

	@Override
	public PersonListResponse getAllUsers(BaseRequest request) {
		final String methodName = "getAllUsers";
		return MethodUtil.processRequest(PersonListResponse.class, request,
				methodName, LOGGER, (r) -> {
					return userDbService.getAllPeople(r);
				});
	}

	@Override
	public BaseResponse putPeople(PersonListRequest request) {
		final String methodName = "getAllUsers";
		return MethodUtil.processRequest(BaseResponse.class, request,
				methodName, LOGGER, (r) -> {
					return userDbService.putPeople(r);
				});
	}

}
