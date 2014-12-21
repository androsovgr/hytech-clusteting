package ru.mephi.hytech.clustering.util;

import java.sql.Connection;
import java.sql.Statement;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public interface DbExecuteProcessor<Res extends BaseResponse, Req extends BaseRequest> {

	public Res process(Req request, Connection con, Statement stm)
			throws Exception;
}
