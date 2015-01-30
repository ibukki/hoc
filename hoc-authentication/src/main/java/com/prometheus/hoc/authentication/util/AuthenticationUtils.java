package com.prometheus.hoc.authentication.util;

import javax.servlet.http.HttpServletRequest;

import com.prometheus.hoc.authentication.Credential;
import com.prometheus.hoc.authentication.CredentialTypeEnum;

public class AuthenticationUtils {

	public static Credential buildCredential(HttpServletRequest request, String companyId, String userId,
			String password, CredentialTypeEnum credentialType) {
		Credential credential = new Credential();
		
		credential.setRequest(request);
		credential.setCompanyId(companyId);
		credential.setPassword(password);
		credential.setUserId(userId);
		credential.setType(credentialType);
		
		return credential;
	}
}
