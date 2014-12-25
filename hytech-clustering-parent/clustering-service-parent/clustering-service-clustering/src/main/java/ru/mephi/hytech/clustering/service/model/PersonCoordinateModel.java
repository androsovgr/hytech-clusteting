package ru.mephi.hytech.clustering.service.model;

import java.util.Arrays;

import ru.mephi.hytech.clustering.model.Person;

public class PersonCoordinateModel {

	private Person person;
	private double[] coordinates;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "PersonCoordinateModel [person="
				+ person
				+ ", coordinates="
				+ (coordinates != null ? Arrays.toString(Arrays.copyOf(coordinates,
						Math.min(coordinates.length, maxLen))) : null) + "]";
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public PersonCoordinateModel() {
		super();
	}

	public PersonCoordinateModel(Person person, double[] coordinates) {
		super();
		this.person = person;
		this.coordinates = coordinates;
	}

}
