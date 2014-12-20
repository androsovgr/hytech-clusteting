package ru.mephi.hytech.clustering.service;

import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDbServiceImpl.class);

	@Override
	public PersonListResponse getAllPeople(BaseRequest request) {
		final String methodName = "getAllPeople";
		return DbUtil.processRequest(PersonListResponse.class, request,
				methodName, LOGGER, (con, stm) -> {
					stm.execute("fix all;");
					ResultSet rs = stm.executeQuery("select * from PEOPLE;");
					List<Person> users = PersonParser.parseList(rs);
					return new PersonListResponse(users);
				});
	}

	@Override
	public BaseResponse putPeople(PersonListRequest request) {
		final String methodName = "getAllPeople";
		return DbUtil
				.processRequest(
						BaseResponse.class,
						request,
						methodName,
						LOGGER,
						(con, stm) -> {
							for (int i = 0; i < 100; i++) {
								stm.execute("insert into people values (0,'Greg', 'Drsv',char2date('07-02-93','dmy'),98.3, 1);");
							}
							stm.execute("fix all;");
							return new BaseResponse();
						});
	}

	private void addPerson(Person model) {

	}
}
