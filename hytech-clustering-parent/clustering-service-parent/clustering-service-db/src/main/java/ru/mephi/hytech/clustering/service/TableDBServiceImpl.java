package ru.mephi.hytech.clustering.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.request.ConnectionInfoRequest;
import ru.mephi.hytech.clustering.response.StringListResponse;
import ru.mephi.hytech.clustering.util.DbExecuteProcessor;
import ru.mephi.hytech.clustering.util.DbUtil;

@Stateless
@Local(TableDbService.class)
public class TableDBServiceImpl implements TableDbService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TableDBServiceImpl.class);

	@Override
	public StringListResponse getTableNames(ConnectionInfoRequest request)
			throws InstantiationException, IllegalAccessException {
		final String methodName = "getTables";
		return DbUtil.processRequest(StringListResponse.class, request,
				methodName, LOGGER,
				new DbExecuteProcessor<StringListResponse>() {
					@Override
					public StringListResponse process(Connection con,
							Statement stm) throws Exception {
						ResultSet rs = stm
								.executeQuery("select NAME from TABLES;");
						List<String> tableNames = new ArrayList<String>();
						while (rs.next()) {
							String tableName = rs.getString("NAME");
							tableNames.add(tableName);
						}
						return new StringListResponse(tableNames);
					}

				});
	}
}
