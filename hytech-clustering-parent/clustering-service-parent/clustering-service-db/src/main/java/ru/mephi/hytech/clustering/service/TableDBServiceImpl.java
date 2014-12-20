package ru.mephi.hytech.clustering.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.StringListResponse;
import ru.mephi.hytech.clustering.util.DbUtil;

public class TableDBServiceImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TableDBServiceImpl.class);

	public StringListResponse getTableNames(BaseRequest request) {
		final String methodName = "getTables";
		return DbUtil
				.processRequest(
						StringListResponse.class,
						request,
						methodName,
						LOGGER,
						(con, stm) -> {
							ResultSet rs = stm
									.executeQuery("select NAME from TABLES;");
							List<String> tableNames = new ArrayList<String>();
							while (rs.next()) {
								String tableName = rs.getString("NAME");
								tableNames.add(tableName);
							}
							return new StringListResponse(tableNames);
						});
	}
}
