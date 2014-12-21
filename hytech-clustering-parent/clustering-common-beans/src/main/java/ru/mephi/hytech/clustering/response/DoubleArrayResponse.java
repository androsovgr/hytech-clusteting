package ru.mephi.hytech.clustering.response;

import java.util.Arrays;

import ru.mephi.hytech.clustering.util.ErrorCode;

public class DoubleArrayResponse extends BaseResponse {

	private double[] ds;

	public double[] getDs() {
		return ds;
	}

	public void setDs(double[] ds) {
		this.ds = ds;
	}

	@Override
	public String toString() {
		return "DoubleArrayResponse [ds=" + Arrays.toString(ds) + ", errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + "]";
	}

	public DoubleArrayResponse() {
		super();
	}

	public DoubleArrayResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public DoubleArrayResponse(double[] ds) {
		super();
		this.ds = ds;
	}

}
