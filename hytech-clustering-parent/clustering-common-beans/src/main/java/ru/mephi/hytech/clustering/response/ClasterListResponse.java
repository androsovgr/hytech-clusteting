package ru.mephi.hytech.clustering.response;

import java.util.List;

import ru.mephi.hytech.clustering.model.Cluster;
import ru.mephi.hytech.clustering.util.ErrorCode;

public class ClasterListResponse extends BaseResponse {

	private List<Cluster> clusters;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "ClasterListResponse [clusters="
				+ (clusters != null ? clusters.subList(0, Math.min(clusters.size(), maxLen)) : null) + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	public ClasterListResponse() {
		super();
	}

	public ClasterListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public ClasterListResponse(List<Cluster> clusters) {
		super();
		this.clusters = clusters;
	}

}
