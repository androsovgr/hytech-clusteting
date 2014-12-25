package ru.mephi.hytech.clustering.service;

import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.model.Gender;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.PersonListResponse;
import ru.mephi.hytech.clustering.util.DbUtil;
import ru.mephi.hytech.clustering.util.PersonParser;

@Stateless
@Local(UserDbService.class)
public class UserDbServiceImpl implements UserDbService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDbServiceImpl.class);

	@Override
	public PersonListResponse getAllPeople(BaseRequest request) {
		final String methodName = "getAllPeople";
		return DbUtil.processRequest(PersonListResponse.class, request, methodName, LOGGER, (r, con, stm) -> {
			stm.execute("fix all;");
			ResultSet rs = stm.executeQuery("select * from PEOPLE;");
			List<Person> users = PersonParser.parseList(rs);
			return new PersonListResponse(users);
		});
	}

	@Override
	public BaseResponse putPeople(PersonListRequest request) {
		final String methodName = "getAllPeople";
		return DbUtil.processRequest(
				BaseResponse.class,
				request,
				methodName,
				LOGGER,
				(r, con, stm) -> {
					String sqlTemplate = "insert into people values (0,'%s', '%s',char2date('%s','dmy'),%s, %d);";
					for (Person p : r.getUsers()) {
						String date = p.getBirthDate().getDayOfMonth() + "-" + p.getBirthDate().getMonthValue() + "-"
								+ p.getBirthDate().getYear();
						String sql = String.format(sqlTemplate, new Object[] { p.getFirstName(), p.getLastName(), date,
								p.getWeight(), p.getGender() == Gender.MALE ? 1 : 2 });
						stm.execute(sql);
					}
					stm.execute("fix all;");
					return new BaseResponse();
				});
	}

	private void addPerson(Person model) {

	}

	@Override
	public BaseResponse clear(BaseRequest request) {
		final String methodName = "clear";
		return DbUtil.processRequest(BaseResponse.class, request, methodName, LOGGER, (r, con, stm) -> {
			stm.execute("fix all;");
			stm.execute("delete from people;");
			stm.execute("fix all;");
			return new BaseResponse();
		});
	}
}
