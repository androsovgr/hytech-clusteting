package ru.mephi.hytech.clustering.service.util;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.List;

import ru.mephi.hytech.clustering.model.Gender;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.service.estimate.Estimator;
import ru.mephi.hytech.clustering.service.estimate.GenderEstimator;
import ru.mephi.hytech.clustering.service.estimate.StringLengthEstimator;

public class Coordinator {

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

	public static int getCoordinatesCount(Person o) {
		if (o.getClass().equals(Person.class)) {
			return 5;
		}
		throw new RuntimeException("Unknown model type: " + o.getClass());
	}

	public static double[] getCenter(List<Person> persons, double[] norma) {
		double[] center = new double[Coordinator.getCoordinatesCount(persons.get(0))];
		for (int i = 0; i < center.length; i++) {
			center[i] = 0;
		}
		int j = 1;
		for (Person person : persons) {
			for (int i = 0; i < center.length; i++) {
				double[] personCoordinates = Coordinator.coordinateNormalized(person, norma);
				center[i] = center[i] / (double) j * (double) (j - 1) + personCoordinates[i] / (double) j;
			}
			j++;
		}
		return center;
	}

	public static double[] getCenter(List<Person> persons) {
		double[] norm = { 1, 1, 1, 1, 1 };
		return getCenter(persons, norm);
	}

	public static double getDistance(double[] p1, double[] p2) {
		if (p1.length != p2.length) {
			throw new RuntimeException("Incomplicated points: p1 " + p1 + " and p2 " + p2);
		}
		double distanceSqr = 0;
		for (int i = 0; i < p2.length; i++) {
			distanceSqr += Math.pow(p1[i] - p2[i], 2);
		}
		return Math.sqrt(distanceSqr);
	}

}
