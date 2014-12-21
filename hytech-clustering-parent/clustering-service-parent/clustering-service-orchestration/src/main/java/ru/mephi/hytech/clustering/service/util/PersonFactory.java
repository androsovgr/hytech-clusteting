package ru.mephi.hytech.clustering.service.util;

import java.time.LocalDate;

import ru.mephi.hytech.clustering.model.Gender;
import ru.mephi.hytech.clustering.model.Person;

public class PersonFactory {

	private static final String[] maleNames = { "Gregory", "Andrew", "Alex", "Ben", "Bob" };
	private static final String[] femaleNames = { "Anny", "Kate", "Alex", "Liza", "Ember" };
	private static final String[] lastNames = { "House", "Wilson", "Kaddy", "Forman", "Black" };

	public Person randomPerson() {
		Person person = new Person();
		person.setBirthDate(getRandomDate());
		person.setGender(getRandomFrom(Gender.values()));
		person.setFirstName(person.getGender() == Gender.MALE ? getRandomFrom(maleNames) : getRandomFrom(femaleNames));
		person.setLastName(getRandomFrom(lastNames));
		person.setWeight(person.getGender() == Gender.MALE ? getRandomFloat(40, 75) : getRandomFloat(75, 120));

		return person;
	}

	private static <T> T getRandomFrom(T[] tArr) {
		return tArr[getRandomInt(0, tArr.length - 1)];
	}

	private static int getRandomInt(int from, int to) {
		return (int) Math.round(Math.random() * (to - from + 1) + from - 0.5);
	}

	private static float getRandomFloat(int from, int to) {
		return (float) (Math.random() * (to - from) + from);
	}

	private static LocalDate getRandomDate() {
		return LocalDate.ofYearDay(getRandomInt(1950, 2000), getRandomInt(1, 365));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			LocalDate ld = getRandomDate();
		}
	}

}
