package ru.mephi.hytech.clustering.service;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.util.DbUtil;

@Stateless
@Local(TestDbService.class)
public class TestDbServiceImpl implements TestDbService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestDbServiceImpl.class);

	@Override
	public BaseResponse testDbConnection(BaseRequest request) {
		final String methodName = "testDbConnection";
		return DbUtil.processRequest(BaseResponse.class, request, methodName,
				LOGGER, (r, con, stm) -> {
					return new BaseResponse();
				});
	}
}
