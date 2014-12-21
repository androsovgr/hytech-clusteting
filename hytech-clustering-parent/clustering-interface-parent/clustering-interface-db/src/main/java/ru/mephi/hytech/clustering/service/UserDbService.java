package ru.mephi.hytech.clustering.service;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.PersonListResponse;

public interface UserDbService {

	public PersonListResponse getAllPeople(BaseRequest request);

	public BaseResponse putPeople(PersonListRequest request);

	public BaseResponse clear(BaseRequest request);

}
