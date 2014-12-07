package ru.mephi.hytech.clustering.response;

import java.util.List;

import ru.mephi.hytech.clustering.util.ErrorCode;

public class StringListResponse extends BaseResponse {

	private List<String> strings;

	@Override
	public String toString() {
		return "StringListResponse [strings=" + strings + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	public StringListResponse() {
		super();
	}

	public StringListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public StringListResponse(List<String> strings) {
		super();
		this.strings = strings;
	}

}
