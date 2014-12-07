package ru.mephi.hytech.clustering.service;

import java.sql.Connection;
import java.sql.Statement;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.util.DbExecuteProcessor;
import ru.mephi.hytech.clustering.util.DbUtil;

@Stateless
@Local(TestDbService.class)
public class TestDbServiceImpl implements TestDbService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestDbServiceImpl.class);

	@Override
	public BaseResponse testDbConnection(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException {
		final String methodName = "testDbConnection";
		return DbUtil.processRequest(BaseResponse.class, request, methodName,
				LOGGER, new DbExecuteProcessor<BaseResponse>() {
					@Override
					public BaseResponse process(Connection con, Statement stm) {
						return new BaseResponse();
					}
				});
	}
}
