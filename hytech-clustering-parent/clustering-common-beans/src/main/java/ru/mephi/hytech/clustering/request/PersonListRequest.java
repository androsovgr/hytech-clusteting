package ru.mephi.hytech.clustering.request;

import java.util.List;

import ru.mephi.hytech.clustering.model.Person;

public class PersonListRequest extends BaseRequest {

	private List<Person> users;

	@Override
	public String toString() {
		return "UserListRequest [users=" + users + "]";
	}

	public PersonListRequest(List<Person> users) {
		super();
		this.users = users;
	}

	public PersonListRequest() {
		super();
	}

	public PersonListRequest(String transactionId, List<Person> users) {
		super(transactionId);
		this.users = users;
	}

	public List<Person> getUsers() {
		return users;
	}

	public void setUsers(List<Person> users) {
		this.users = users;
	}

}
