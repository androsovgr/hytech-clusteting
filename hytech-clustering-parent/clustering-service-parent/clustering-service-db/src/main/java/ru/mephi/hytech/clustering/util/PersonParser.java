package ru.mephi.hytech.clustering.util;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.mephi.hytech.clustering.model.Gender;
import ru.mephi.hytech.clustering.model.Person;

public class PersonParser {

	public static Person parseOne(ResultSet rs) throws SQLException {
		Person model = null;

		if (rs.next()) {
			model = new Person();
			long id = rs.getLong("user_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			Date date = rs.getDate("birth_date");
			float weight = rs.getFloat("weight");
			byte gender = rs.getByte("gender");
			model.setUserId(id);
			model.setFirstName(firstName);
			model.setLastName(lastName);
			model.setBirthDate(date.toLocalDate());
			model.setWeight(weight);
			model.setGender(gender == 1 ? Gender.MALE : Gender.FEMALE);
		}

		return model;
	}

	public static List<Person> parseList(ResultSet rs) throws SQLException {
		List<Person> models = null;

		if (rs != null) {
			models = new ArrayList<Person>();
			while (true) {
				Person model = parseOne(rs);
				if (model != null) {
					models.add(model);
				} else {
					break;
				}
			}
		}

		return models;
	};
}
