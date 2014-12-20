package ru.mephi.hytech.clustering.model;

import java.time.LocalDate;

public class Person {

	private long userId;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private float weight;
	private Gender gender;

	public Person(long userId, String firstName, String lastName,
			LocalDate birthDate, float weight, Gender gender) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.weight = weight;
		this.gender = gender;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", weight=" + weight + ", gender=" + gender + "]";
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
