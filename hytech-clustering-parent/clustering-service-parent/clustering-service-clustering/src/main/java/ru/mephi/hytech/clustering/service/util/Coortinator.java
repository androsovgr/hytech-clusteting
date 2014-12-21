package ru.mephi.hytech.clustering.service.util;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

import ru.mephi.hytech.clustering.model.Gender;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.service.estimate.Estimator;
import ru.mephi.hytech.clustering.service.estimate.GenderEstimator;
import ru.mephi.hytech.clustering.service.estimate.StringLengthEstimator;

public class Coortinator {

	private static Estimator<String> stringEstimator = new StringLengthEstimator();
	private static Estimator<Gender> genderEstimator = new GenderEstimator();
	private static final TemporalField field = ChronoField.EPOCH_DAY;

	public static double[] coordinateNormalized(Person person, double[] norm) {
		double[] coordinates = { person.getWeight(), stringEstimator.estimate(person.getFirstName()),
				stringEstimator.estimate(person.getLastName()), person.getBirthDate().getLong(field),
				genderEstimator.estimate(person.getGender()) };
		if (coordinates.length != norm.length) {
			throw new RuntimeException("Wrong norm for coordinates. Norm: " + norm + " coordinates: " + coordinates);
		}
		double[] coordinatesNormed = new double[coordinates.length];
		for (int i = 0; i < coordinates.length; i++) {
			coordinatesNormed[i] = coordinates[i] * norm[i];
		}

		return coordinatesNormed;
	}

	public static double[] coordinate(Person person) {
		double[] norm = { 1, 1, 1, 1, 1 };
		return coordinateNormalized(person, norm);
	}
}
