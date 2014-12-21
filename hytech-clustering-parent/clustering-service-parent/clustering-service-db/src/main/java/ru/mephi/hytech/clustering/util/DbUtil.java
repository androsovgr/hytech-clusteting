package ru.mephi.hytech.clustering.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public class DbUtil {

	public static Connection getConnection() throws NamingException,
			SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context
				.lookup("java:jboss/datasources/HytechDS");
		return ds.getConnection();
	}

	public static <T extends BaseResponse, R extends BaseRequest> T processRequest(
			Class<T> resposneClass, R request, String methodName,
			Logger LOGGER, DbExecuteProcessor<T, R> processor) {
		T response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try (Connection con = DbUtil.getConnection();
				Statement stm = con.createStatement()) {
			response = processor.process(request, con, stm);
		} catch (SQLException e) {
			try {
				response = resposneClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				throw new RuntimeException("Can't create new instance of "
						+ resposneClass, e1);
			}
			if (e.getMessage().startsWith(DbConstants.CONNECTION_ERROR_PREFIXE)) {
				response.setErrorCode(ErrorCode.UNABLE_CONNECT);
			} else {
				response.setErrorCode(ErrorCode.SQL_ERROR);
			}
			response.setErrorMessage(e.getMessage());
			LogUtil.logError(LOGGER, methodName, request, e);
		} catch (Exception e) {
			try {
				response = resposneClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				throw new RuntimeException("Can't create new instance of "
						+ resposneClass, e1);
			}
			response.setErrorCode(ErrorCode.INTERNAL_ERROR);
			response.setErrorMessage(e.getMessage());
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
