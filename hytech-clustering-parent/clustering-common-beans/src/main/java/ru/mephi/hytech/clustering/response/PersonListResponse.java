package ru.mephi.hytech.clustering.response;

import java.util.List;

import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.util.ErrorCode;

public class PersonListResponse extends BaseResponse {

	private List<Person> users;

	public PersonListResponse(List<Person> users) {
		super();
		this.users = users;
	}

	public PersonListResponse() {
		super();
	}

	public PersonListResponse(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public List<Person> getUsers() {
		return users;
	}

	public void setUsers(List<Person> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserListResponse [users=" + users + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}

}
