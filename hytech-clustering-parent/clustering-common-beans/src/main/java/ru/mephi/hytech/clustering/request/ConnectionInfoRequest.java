package ru.mephi.hytech.clustering.request;

import ru.mephi.hytech.clustering.model.ConnectionInfo;

public class ConnectionInfoRequest extends BaseRequest {

	private ConnectionInfo connectionInfo;

	@Override
	public String toString() {
		return "ConnectionInfoRequest [connectionInfo=" + connectionInfo
				+ ", transactionId=" + transactionId + "]";
	}

	public ConnectionInfoRequest() {
		super();
	}

	public ConnectionInfoRequest(ConnectionInfo connectionInfo) {
		super();
		this.connectionInfo = connectionInfo;
	}

	public ConnectionInfoRequest(String transactionId,
			ConnectionInfo connectionInfo) {
		super(transactionId);
		this.connectionInfo = connectionInfo;
	}

	public ConnectionInfo getConnectionInfo() {
		return connectionInfo;
	}

	public void setConnectionInfo(ConnectionInfo connectionInfo) {
		this.connectionInfo = connectionInfo;
	}

}
