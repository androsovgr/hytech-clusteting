package ru.mephi.hytech.clustering.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.request.MinMaxListRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.CountRequest;
import ru.mephi.hytech.clustering.response.DoubleArrayResponse;
import ru.mephi.hytech.clustering.response.MinMaxListResponse;
import ru.mephi.hytech.clustering.response.PersonListResponse;
import ru.mephi.hytech.clustering.service.util.PersonFactory;
import ru.mephi.hytech.clustering.util.ErrorCode;
import ru.mephi.hytech.clustering.util.MethodUtil;

@Stateless
@Local(OrchestrationService.class)
public class OrchestrationServiceImpl implements OrchestrationService {

	private static final PersonFactory pf = new PersonFactory();
	private static final Logger LOGGER = LoggerFactory.getLogger(OrchestrationServiceImpl.class);

	@EJB
	private UserDbService userDbService;
	@EJB
	private ClusteringService clusteringService;

	@Override
	public BaseResponse fillDb(CountRequest request) {
		String methodName = "fillDb()";
		return MethodUtil.processRequest(BaseResponse.class, request, methodName, LOGGER, (r) -> {
			List<Person> persons = new ArrayList<Person>();
			for (int i = 0; i < r.getCount(); i++) {
				persons.add(pf.randomPerson());
			}
			PersonListRequest putPeopleRequest = new PersonListRequest(r.getTransactionId(), persons);
			return userDbService.putPeople(putPeopleRequest);
		});
	}

	@Override
	public BaseResponse clasterize(BaseRequest request) {
		String methodName = "clasterize()";
		return MethodUtil
				.processRequest(
						BaseResponse.class,
						request,
						methodName,
						LOGGER,
						(r) -> {
							// Get all
							PersonListResponse getAllPeopleResponse = userDbService.getAllPeople(r);
							if (ErrorCode.OK != getAllPeopleResponse.getErrorCode()) {
								return new BaseResponse(getAllPeopleResponse.getErrorCode(), getAllPeopleResponse
										.getErrorMessage());
							}
							// Get boundes
							PersonListRequest personListRequest = new PersonListRequest(r.getTransactionId(),
									getAllPeopleResponse.getUsers());
							MinMaxListResponse minMaxListResponse = clusteringService
									.getMinMaxCoordinates(personListRequest);
							if (ErrorCode.OK != minMaxListResponse.getErrorCode()) {
								return new BaseResponse(minMaxListResponse.getErrorCode(), minMaxListResponse
										.getErrorMessage());
							}
							// Get norma
							MinMaxListRequest MinMaxListRequest = new MinMaxListRequest(r.getTransactionId(),
									minMaxListResponse.getMinMaxs());
							DoubleArrayResponse normaResponse = clusteringService.getNorma(MinMaxListRequest);
							if (ErrorCode.OK != normaResponse.getErrorCode()) {
								return new BaseResponse(normaResponse.getErrorCode(), normaResponse.getErrorMessage());
							}

							return null;
						});
	}
}
