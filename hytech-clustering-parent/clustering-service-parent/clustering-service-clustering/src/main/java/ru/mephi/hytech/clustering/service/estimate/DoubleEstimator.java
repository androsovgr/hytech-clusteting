package ru.mephi.hytech.clustering.service.estimate;

public class DoubleEstimator implements Estimator<Long> {

	@Override
	public double estimate(Long t) {
		if (t == null) {
			return 0;
		} else {
			return t;
		}
	}

}
