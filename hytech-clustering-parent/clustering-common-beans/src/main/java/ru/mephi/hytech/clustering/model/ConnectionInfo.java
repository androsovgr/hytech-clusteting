package ru.mephi.hytech.clustering.model;

public class ConnectionInfo {

	private String name;
	private String host;
	private String port;
	private String login;
	private String password;

	@Override
	public String toString() {
		return "ConnectionInfo [name=" + name + ", host=" + host + ", port="
				+ port + ", login=" + login + ", password=" + password + "]";
	}

	public ConnectionInfo() {
		super();
	}

	public ConnectionInfo(String name, String host, String port, String login,
			String password) {
		super();
		this.name = name;
		this.host = host;
		this.port = port;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
