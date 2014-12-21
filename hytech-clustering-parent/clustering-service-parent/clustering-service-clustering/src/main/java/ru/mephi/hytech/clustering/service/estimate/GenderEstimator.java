package ru.mephi.hytech.clustering.service.estimate;

import ru.mephi.hytech.clustering.model.Gender;

public class GenderEstimator implements Estimator<Gender> {

	@Override
	public double estimate(Gender t) {
		if (t == null) {
			return 0;
		} else {
			if (t == Gender.MALE) {
				return 1;
			} else {
				return 2;
			}
		}
	}

}
