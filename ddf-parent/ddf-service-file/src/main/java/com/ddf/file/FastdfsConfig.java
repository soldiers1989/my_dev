package com.ddf.file;


public class FastdfsConfig {
	private int connect_timeout;
	private int network_timeout;
	private String charset;
	private int http_tracker_http_port;
	private boolean http_anti_steal_token;
	private String http_secret_key;
	private String tracker_server;
	
	
	public int getConnect_timeout() {
		return connect_timeout;
	}
	public int getNetwork_timeout() {
		return network_timeout;
	}
	public String getCharset() {
		return charset;
	}
	public int getHttp_tracker_http_port() {
		return http_tracker_http_port;
	}
	public boolean isHttp_anti_steal_token() {
		return http_anti_steal_token;
	}
	public String getHttp_secret_key() {
		return http_secret_key;
	}
	public String getTracker_server() {
		return tracker_server;
	}
	public void setConnect_timeout(int connect_timeout) {
		this.connect_timeout = connect_timeout;
	}
	public void setNetwork_timeout(int network_timeout) {
		this.network_timeout = network_timeout;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public void setHttp_tracker_http_port(int http_tracker_http_port) {
		this.http_tracker_http_port = http_tracker_http_port;
	}
	public void setHttp_anti_steal_token(boolean http_anti_steal_token) {
		this.http_anti_steal_token = http_anti_steal_token;
	}
	public void setHttp_secret_key(String http_secret_key) {
		this.http_secret_key = http_secret_key;
	}
	public void setTracker_server(String tracker_server) {
		this.tracker_server = tracker_server;
	}
}
