package com.prometheus.hoc.authentication;

import java.io.Serializable;

public class LoginResult implements Serializable {

	private static final long serialVersionUID = 2342119331242972783L;

	private String sessionId = null;
	private String loginURL = null;
	
	/**
	 * return sessionID, or null if login failed.
	 * 
	 * @return sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * return server URL or null if login failed.
	 * 
	 * @return server url
	 */
	public String getURL() {
		return loginURL;
	}

	public void setSessionId(String sid) {
		sessionId = sid;
	}

	public void setURL(String url) {
		loginURL = url;
	}
}
