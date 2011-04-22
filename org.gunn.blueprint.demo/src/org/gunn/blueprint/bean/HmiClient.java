package org.gunn.blueprint.bean;

public class HmiClient {
	private int port;
	private String host;
	
	
	
	public HmiClient() {
		super();
		System.out.println("i have create hmi client:" + this.toString());
	}

	


	public int getPort() {
		return port;
	}




	public void setPort(int port) {
		this.port = port;
	}




	public String getHost() {
		return host;
	}




	public void setHost(String host) {
		this.host = host;
	}




	@Override
	public String toString() {
		return "HmiClient [host=" + host + ", port=" + port + "]";
	}
	
	
}
