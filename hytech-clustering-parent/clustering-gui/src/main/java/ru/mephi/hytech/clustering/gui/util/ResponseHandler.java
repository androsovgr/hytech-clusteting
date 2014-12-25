package ru.mephi.hytech.clustering.gui.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.util.ErrorCode;

public class ResponseHandler {

	public static boolean handle(BaseResponse response, boolean needGrowl, String operation) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (response != null) {
			if (ErrorCode.OK == response.getErrorCode()) {
				if (needGrowl) {
					context.addMessage(null, new FacesMessage(operation, "Выполнено успешно"));
				}
				return true;
			} else {
				if (needGrowl) {
					context.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, operation, response.getErrorMessage()));
				}
				return false;
			}
		} else {
			if (needGrowl) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, operation, "Внутренняя ошибка"));
			}
			return false;
		}
	}
}
