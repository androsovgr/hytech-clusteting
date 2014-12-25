package ru.mephi.hytech.clustering.request;

import java.util.Arrays;
import java.util.List;

import ru.mephi.hytech.clustering.model.Person;

public class GetBordersRequest extends BaseRequest {

	private List<Person> persons;
	private double[] norma;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "GetBordersRequest [persons="
				+ (persons != null ? persons.subList(0, Math.min(persons.size(), maxLen)) : null) + ", norma="
				+ (norma != null ? Arrays.toString(Arrays.copyOf(norma, Math.min(norma.length, maxLen))) : null) + "]";
	}

	public GetBordersRequest(List<Person> persons, double[] norma) {
		super();
		this.persons = persons;
		this.norma = norma;
	}

	public GetBordersRequest() {
		super();
	}

	public GetBordersRequest(String transactionId, List<Person> persons, double[] norma) {
		super(transactionId);
		this.persons = persons;
		this.norma = norma;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public double[] getNorma() {
		return norma;
	}

	public void setNorma(double[] norma) {
		this.norma = norma;
	}

}
