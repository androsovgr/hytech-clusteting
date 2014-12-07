package ru.mephi.hytech.clustering.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.StringListResponse;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ApiService {

	@Path("/testConnection")
	@POST
	public BaseResponse testConnection(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException;

	@Path("/getTableNames")
	@POST
	public StringListResponse getTableNames(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException;
}
