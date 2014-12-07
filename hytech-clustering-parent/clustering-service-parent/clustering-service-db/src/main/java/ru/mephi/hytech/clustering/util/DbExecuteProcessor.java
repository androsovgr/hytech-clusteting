package ru.mephi.hytech.clustering.util;

import java.sql.Connection;
import java.sql.Statement;

import ru.mephi.hytech.clustering.response.BaseResponse;

public interface DbExecuteProcessor<T extends BaseResponse> {

	public T process(Connection con, Statement stm) throws Exception;
}
