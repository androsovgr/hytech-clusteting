package ru.mephi.hytech.clustering.util;

import org.slf4j.Logger;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.util.processor.RequestProcessor;

public class MethodUtil {

	public static <T extends BaseResponse, R extends BaseRequest> T processRequest(
			Class<T> resposneClass, R request, String methodName,
			Logger LOGGER, RequestProcessor<R, T> processor)
			throws InstantiationException, IllegalAccessException {
		T response = null;
		LogUtil.logStarted(LOGGER, methodName, request);
		try {
			response = processor.process(request);
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
