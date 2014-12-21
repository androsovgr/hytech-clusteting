package ru.mephi.hytech.clustering.model;

import java.util.List;

public class Cluster {

	private ClusterInfo clusterInfo;
	private List<Person> persons;

	public Cluster(ClusterInfo clusterInfo, List<Person> persons) {
		super();
		this.clusterInfo = clusterInfo;
		this.persons = persons;
	}

	public Cluster() {
		super();
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Cluster [clusterInfo=" + clusterInfo + ", persons="
				+ (persons != null ? persons.subList(0, Math.min(persons.size(), maxLen)) : null) + "]";
	}

	public ClusterInfo getClusterInfo() {
		return clusterInfo;
	}

	public void setClusterInfo(ClusterInfo clusterInfo) {
		this.clusterInfo = clusterInfo;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
