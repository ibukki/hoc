package com.prometheus.hoc.authentication;

import javax.servlet.http.HttpServletRequest;

public class Credential {
	
	private HttpServletRequest request;
	private String companyId;
	private String userId;
	private String password;
	private String expire;
	private String clientIP;
	private String timeZone;
	private CredentialTypeEnum type;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	/**
	 * @return the type
	 */
	public CredentialTypeEnum getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(CredentialTypeEnum type) {
		this.type = type;
	}
}
