package com.prometheus.hoc.authentication;

import java.io.Serializable;

import com.prometheus.hoc.user.bean.UserAccountBean;
import com.prometheus.hoc.user.bean.UserBean;

public class AuthenticationResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4345953996134049990L;

	// TODO remove useless status code
	public static final int LOGIN_OK = 1;
	public static final int LOGIN_FAILED_INACTIVE_COMPANY = 2;
	public static final int LOGIN_FAILED_INVALID_COMPANYID = 3;
	public static final int LOGIN_FAILED_INVALID_USER = 4;
	public static final int LOGIN_FAILED_INVALID_PWD = 5;
	public static final int LOGIN_FAILED_MAX_API_CALL_EXCEEDED = 6;
	public static final int LOGIN_FAILED_MISSING_CREDENTIAL = 7;
	public static final int LOGIN_FAILED_INVALID_IP = 8;
	public static final int LOGIN_FAILED_INVALID_MD5_HASH = 9;
	public static final int LOGIN_FAILED_EXPIRED_CREDENTIAL = 10;
	public static final int LOGIN_FAILED_INVALID_TOKEN = 11;
	public static final int LOGIN_FAILED_EXPIRED_COMPANY = 12;
	public static final int LOGIN_FAILED_ACCOUNT_LOCKED = 13;
	public static final int LOGIN_FAILED_USER_NO_LOGIN_PERMISSION = 14;
	public static final int LOGIN_FAILED_INVALID_SHA_1_HASH = 15;
	public static final int LOGIN_FAILED_INVALID_HMACSHA_1_HASH = 16;
	public static final int LOGIN_FAILED_USER_NEEDS_VALIDATION = 17;
	public static final int LOGIN_FAILED_INVALID_MANAGER = 18;
	public static final int LOGIN_FAILED_PASSWORD_EXPIRED = 19;
	public static final int LOGIN_FAILED_INVALID_INPUT = 20;
	public static final int LOGIN_FAILED_SECURITY_EXCEPTION = 21;
	public static final int LOGIN_FAILED_REASON_UNKNOWN = 22;
	// Add following meaningful message, no I18n supported.
	public static final String LOGIN_FAILED_INACTIVE_COMPANY_STR = "Login failed - inactive company";
	public static final String LOGIN_FAILED_INVALID_COMPANYID_STR = "Login failed - invalid company";
	public static final String LOGIN_FAILED_INVALID_USER_STR = "Login failed - invalid user";
	public static final String LOGIN_FAILED_INVALID_PWD_STR = "Login failed - invalid password";
	public static final String LOGIN_FAILED_MAX_API_CALL_EXCEEDED_STR = "Login failed - max api call exceeded";
	public static final String LOGIN_FAILED_MISSING_CREDENTIAL_STR = "Login failed - missing credential";
	public static final String LOGIN_FAILED_INVALID_IP_STR = "Login failed - invalid ip";
	public static final String LOGIN_FAILED_INVALID_MD5_HASH_STR = "Login failed - invalid MD5 hash";
	public static final String LOGIN_FAILED_EXPIRED_CREDENTIAL_STR = "Login failed - expired credential";
	public static final String LOGIN_FAILED_INVALID_TOKEN_STR = "Login failed - invalid token";
	public static final String LOGIN_FAILED_EXPIRED_COMPANY_STR = "Login failed - expired company";
	public static final String LOGIN_FAILED_ACCOUNT_LOCKED_STR = "Login failed - account locked";
	public static final String LOGIN_FAILED_USER_NO_LOGIN_PERMISSION_STR = "Login failed - user has no login permission";
	public static final String LOGIN_FAILED_INVALID_SHA_1_HASH_STR = "Login failed - invalid SHA1 hash";
	public static final String LOGIN_FAILED_INVALID_HMACSHA_1_HASH_STR = "Login failed - invalid HMACSHA1 hash";
	public static final String LOGIN_FAILED_USER_NEEDS_VALIDATION_STR = "Login failed - user needs validation";
	public static final String LOGIN_FAILED_INVALID_MANAGER_STR = "Login failed - invalid manager";
	public static final String LOGIN_FAILED_PASSWORD_EXPIRED_STR = "Login failed - password expired";
	public static final String LOGIN_FAILED_INVALID_INPUT_STR = "Login failed - invalid input";
	public static final String LOGIN_FAILED_SECURITY_EXCEPTION_STR = "Login failed - security exception";
	public static final String LOGIN_FAILED_REASON_UNKNOWN_STR = "Login failed - reason unknown";

	private int status;

	private UserBean authenticatedUser = null;

	private UserAccountBean authenticatedUserAccount = null;
	
	private String invalidURL = null;

	public String getInvalidURL() {
		return invalidURL;
	}

	public void setInvalidURL(String invalidURL) {
		this.invalidURL = invalidURL;
	}

	public AuthenticationResponse() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserBean getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(UserBean authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public UserAccountBean getAuthenticatedUserAccount() {
		return authenticatedUserAccount;
	}

	public void setAuthenticatedUserAccount(
			UserAccountBean authenticatedUserAccount) {
		this.authenticatedUserAccount = authenticatedUserAccount;
	}
}
