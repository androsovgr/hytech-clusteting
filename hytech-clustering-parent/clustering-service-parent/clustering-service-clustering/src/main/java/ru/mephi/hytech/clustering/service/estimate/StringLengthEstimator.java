package ru.mephi.hytech.clustering.service.estimate;

public class StringLengthEstimator implements Estimator<String> {

	@Override
	public double estimate(String t) {
		if (t == null) {
			return 0;
		}
		return t.length();
	}

}
