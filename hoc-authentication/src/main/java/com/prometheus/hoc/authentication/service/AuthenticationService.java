package com.prometheus.hoc.authentication.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.hoc.authentication.GeneralSecurityException;
import com.prometheus.hoc.authentication.AuthenticationResponse;
import com.prometheus.hoc.authentication.Credential;
import com.prometheus.hoc.authentication.LoginResult;
import com.prometheus.hoc.authentication.util.PasswordHashUtils;
import com.prometheus.hoc.common.context.GlobalContext;
import com.prometheus.hoc.user.UserConstants;
import com.prometheus.hoc.user.bean.UserAccountBean;
import com.prometheus.hoc.user.bean.UserBean;
import com.prometheus.hoc.user.service.UserAccountService;
import com.prometheus.hoc.user.service.UserService;

/**
 * TODO the class needs to be moved to a new package in a new project
 * 
 * @author haibin
 * 
 */
@Service
public class AuthenticationService {
	
	@Autowired
	private GlobalContext globalContext;

	@Autowired
	private HttpServletRequest request;

	/**
	 * This method needs to be called first to do authentication and create
	 * session.
	 * 
	 * @return LoginResult the result with sessionId and requestURL. Return null
	 *         when login failed.
	 */
	public LoginResult login(Credential credential) {

		// TODO add log
		// TODO get company bean
		// TODO get config bean

		LoginResult result = null;

		// do authentication
		AuthenticationResponse response = new AuthenticationResponse();
		response = authenticate(credential);

		if (response.getStatus() != AuthenticationResponse.LOGIN_OK) {
			// TODO - login failed, put error message into log
		} else {
			// generate login result
			result = new LoginResult();
			HttpServletRequest request = credential.getRequest();
			if (request != null) {
				HttpSession session = request.getSession();
				if (session != null) {
					result.setSessionId(session.getId());
				}
				result.setURL(request.getRequestURL().toString());
			}
		}

		return result;
	}

	protected AuthenticationResponse authenticate(Credential credential) {
		// TODO check login session
		// if session exists, get response from session / seam

		AuthenticationResponse response = new AuthenticationResponse();

		if (credential == null) {
			response.setStatus(AuthenticationResponse.LOGIN_FAILED_MISSING_CREDENTIAL);
			return response;
		}

		if (!validateUserInput(credential)) {
			response.setStatus(AuthenticationResponse.LOGIN_FAILED_INVALID_INPUT);
			return response;
		}

		/**
		 * validate user account
		 */
		UserAccountBean accountBean = UserAccountService
				.getUserAccountBean(credential.getUserId());
		if (accountBean == null) {
			response.setStatus(AuthenticationResponse.LOGIN_FAILED_INVALID_USER);
			return response;
		}
		// check user account status
		switch (accountBean.getStatus()) {
			case UserConstants.ACCOUNT_STATUS_ACTIVE :
				// TODO check login attempts and password expiration
				//
				// validate password
				byte[] storedPassword = accountBean.getPasswordHash();
				byte[] salt = accountBean.getSalt();
				boolean isPasswordValid = false;
				try {
					isPasswordValid = PasswordHashUtils.validatePassword(
							credential.getPassword(), salt, storedPassword);
				} catch (GeneralSecurityException e) {
					//TODO log error
					response.setStatus(AuthenticationResponse.LOGIN_FAILED_SECURITY_EXCEPTION);
					return response;
				}
				// build response
				if (isPasswordValid) {
					response.setStatus(AuthenticationResponse.LOGIN_OK);
					response.setAuthenticatedUserAccount(accountBean);
					UserBean userBean = UserService.getUserBean(credential
							.getUserId());
					response.setAuthenticatedUser(userBean);
				} else {
					response.setStatus(AuthenticationResponse.LOGIN_FAILED_INVALID_PWD);
				}
				break;
			case UserConstants.ACCOUNT_STATUS_INACTIVE :
				response.setStatus(AuthenticationResponse.LOGIN_FAILED_USER_NO_LOGIN_PERMISSION);
				break;
			case UserConstants.ACCOUNT_STATUS_LOCKED :
				response.setStatus(AuthenticationResponse.LOGIN_FAILED_ACCOUNT_LOCKED);
			default :
				response.setStatus(AuthenticationResponse.LOGIN_FAILED_INVALID_USER);
				break;
		}

		// TODO - check company info, speical feature on-off and system config

		return response;
	}

	private boolean validateUserInput(Credential credential) {
		String companyId = credential.getCompanyId();
		String userId = credential.getUserId();
		String password = credential.getPassword();
		if (companyId == null || userId == null || password == null
				|| companyId.length() < 1 || userId.length() < 1
				|| password.length() < 1) {
			return false;
		}
		return true;
	}
}
