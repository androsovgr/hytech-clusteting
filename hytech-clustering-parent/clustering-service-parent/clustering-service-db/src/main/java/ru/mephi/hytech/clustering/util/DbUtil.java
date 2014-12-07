package ru.mephi.hytech.clustering.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

import ru.mephi.hytech.clustering.model.ConnectionInfo;
import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public class DbUtil {

	public static Connection getConnection(ConnectionInfo info)
			throws SQLException, ClassNotFoundException {
		if (info != null) {
			Class.forName(DbConstants.DRIVER_NAME);
			String connectionUrl = String.format(
					DbConstants.CONNECTION_URL_TEMPLATE,
					new Object[] { info.getHost(), info.getPort() });
			return DriverManager.getConnection(connectionUrl, info.getLogin(),
					info.getPassword());
		} else {
			return null;
		}
	}

	public static <T extends BaseResponse, R extends ConnectionInfoRequest> T processRequest(
			Class<T> resposneClass, R request, String methodName,
			Logger LOGGER, DbExecuteProcessor<T> processor)
			throws InstantiationException, IllegalAccessException {
		T response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try (Connection con = DbUtil.getConnection(request.getConnectionInfo());
				Statement stm = con.createStatement()) {
			response = processor.process(con, stm);
		} catch (SQLException e) {
			response = resposneClass.newInstance();
			if (e.getMessage().startsWith(DbConstants.CONNECTION_ERROR_PREFIXE)) {
				response.setErrorCode(ErrorCode.UNABLE_CONNECT);
			} else {
				response.setErrorCode(ErrorCode.SQL_ERROR);
			}
			response.setErrorMessage(e.getMessage());
			LogUtil.logError(LOGGER, methodName, request, e);
		} catch (Exception e) {
			response = resposneClass.newInstance();
			response.setErrorCode(ErrorCode.INTERNAL_ERROR);
			response.setErrorMessage(e.getMessage());
			LogUtil.logError(LOGGER, methodName, request, e);
		}
		LogUtil.logFinished(LOGGER, methodName, request, response);
		return response;
	}
}
