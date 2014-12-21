package ru.mephi.hytech.clustering.request;

import java.util.List;

import ru.mephi.hytech.clustering.model.MinMax;

public class MinMaxListRequest extends BaseRequest {

	private List<MinMax> minMaxs;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "MinMaxListRequest [minMaxs="
				+ (minMaxs != null ? minMaxs.subList(0, Math.min(minMaxs.size(), maxLen)) : null) + "]";
	}

	public MinMaxListRequest(List<MinMax> minMaxs) {
		super();
		this.minMaxs = minMaxs;
	}

	public MinMaxListRequest() {
		super();
	}

	public MinMaxListRequest(String transactionId, List<MinMax> minMaxs) {
		super(transactionId);
		this.minMaxs = minMaxs;
	}

	public List<MinMax> getMinMaxs() {
		return minMaxs;
	}

	public void setMinMaxs(List<MinMax> minMaxs) {
		this.minMaxs = minMaxs;
	}

}
