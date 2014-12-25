package ru.mephi.hytech.clustering.request;

import java.util.Arrays;
import java.util.List;

import ru.mephi.hytech.clustering.model.Person;

public class ClusteringRequest extends BaseRequest {

	private List<Person> persons;
	private double[] norma;
	private int clusterCount;

	public ClusteringRequest() {
		super();
	}

	public ClusteringRequest(List<Person> persons, double[] norma, int clusterCount) {
		super();
		this.persons = persons;
		this.norma = norma;
		this.clusterCount = clusterCount;
	}

	public int getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
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

	@Override
	public String toString() {
		final int maxLen = 10;
		return "ClusteringRequest [persons="
				+ (persons != null ? persons.subList(0, Math.min(persons.size(), maxLen)) : null) + ", norma="
				+ (norma != null ? Arrays.toString(Arrays.copyOf(norma, Math.min(norma.length, maxLen))) : null)
				+ ", clusterCount=" + clusterCount + "]";
	}

}
