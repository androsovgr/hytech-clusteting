package ru.mephi.hytech.clustering.util;

import org.slf4j.Logger;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public class LogUtil {

	public static void logError(Logger LOGGER, String methodName,
			BaseRequest request, Exception e) {
		String transactionId = request != null ? request.getTransactionId()
				: null;
		LOGGER.error("[{}] Got exception while was in {}. Request: {}",
				transactionId, methodName, request, e);
	}

	public static void logStarted(Logger LOGGER, String methodName,
			BaseRequest request) {
		String transactionId = request != null ? request.getTransactionId()
				: null;
		LOGGER.debug("[{}] Method {} started", transactionId, methodName);
		LOGGER.trace("[{}] Method {} started with request {}", transactionId,
				methodName, request);
	}

	public static void logFinished(Logger LOGGER, String methodName,
			BaseRequest request, BaseResponse response) {
		String transactionId = request != null ? request.getTransactionId()
				: null;
		LOGGER.debug("[{}] Method {} finished", transactionId, methodName);
		LOGGER.trace("[{}] Method {} finished with response {}", transactionId,
				methodName, response);
	}
}
