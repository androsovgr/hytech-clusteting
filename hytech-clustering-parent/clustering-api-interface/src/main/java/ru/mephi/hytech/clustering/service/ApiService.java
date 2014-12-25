package ru.mephi.hytech.clustering.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.request.PersonListRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.CountRequest;
import ru.mephi.hytech.clustering.response.PersonListResponse;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ApiService {

	@Path("/testConnection")
	@POST
	public BaseResponse testConnection(BaseRequest request);

	@Path("/getAllUsers")
	@POST
	public PersonListResponse getAllUsers(BaseRequest request);

	@Path("/putPeople")
	@POST
	public BaseResponse putPeople(PersonListRequest request);

	@Path("/fillDb")
	@POST
	public BaseResponse fillDb(CountRequest request);

	@Path("/clear")
	@POST
	public BaseResponse clear(BaseRequest request);

	@Path("/clusterize")
	@POST
	public BaseResponse clusterize(CountRequest request);

}
