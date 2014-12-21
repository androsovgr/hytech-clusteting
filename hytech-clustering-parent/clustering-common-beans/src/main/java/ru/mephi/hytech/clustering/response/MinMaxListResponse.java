package ru.mephi.hytech.clustering.response;

import java.util.List;

import ru.mephi.hytech.clustering.model.MinMax;
import ru.mephi.hytech.clustering.util.ErrorCode;

public class MinMaxListResponse extends BaseResponse {

	private List<MinMax> minMaxs;

	@Override
	public String toString() {
		return "MinMaxListResponse [minMaxs=" + minMaxs + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ "]";
	}

	public MinMaxListResponse(List<MinMax> minMaxs) {
		super();
		this.minMaxs = minMaxs;
	}

	public MinMaxListResponse() {
		super();
	}

	public MinMaxListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public List<MinMax> getMinMaxs() {
		return minMaxs;
	}

	public void setMinMaxs(List<MinMax> minMaxs) {
		this.minMaxs = minMaxs;
	}

}
