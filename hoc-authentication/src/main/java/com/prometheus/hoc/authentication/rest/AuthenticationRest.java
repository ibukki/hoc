package com.prometheus.hoc.authentication.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.authentication.Credential;
import com.prometheus.hoc.authentication.CredentialTypeEnum;
import com.prometheus.hoc.authentication.LoginResult;
import com.prometheus.hoc.authentication.service.AuthenticationService;
import com.prometheus.hoc.authentication.util.AuthenticationUtils;
import com.prometheus.hoc.common.dao.BaseHibernateDao;

@Component
@Path("auth")
public class AuthenticationRest {
	
	@Autowired
	private BaseHibernateDao baseHibernateDao = new BaseHibernateDao();
	
	@Autowired
	private AuthenticationService authService;
	
	@GET
	@Path("ent_login")
	@Produces({MediaType.APPLICATION_JSON})
	public LoginResult EnterpriseLogin(@Context HttpServletRequest request,
			@QueryParam("companyId") String companyId,
			@QueryParam("userId") String userId,
			@QueryParam("password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
		Credential credential = AuthenticationUtils.buildCredential(request, companyId,
				userId, password, CredentialTypeEnum.ENTERPRISE);
		LoginResult result = authService.login(credential);
		return result;
	}
	
	@GET
	@Path("ind_login")
	@Produces({MediaType.APPLICATION_JSON})
	public LoginResult individualLogin(@Context HttpServletRequest request,
			@QueryParam("companyId") String companyId,
			@QueryParam("userId") String userId,
			@QueryParam("password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
		Credential credential = AuthenticationUtils.buildCredential(request, null,
				userId, password, CredentialTypeEnum.INDIVIDUAL);
		LoginResult result = authService.login(credential);
		return result;
	}
}
