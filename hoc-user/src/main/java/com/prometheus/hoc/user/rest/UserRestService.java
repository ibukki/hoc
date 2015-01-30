/**
 * 
 */
package com.prometheus.hoc.user.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.user.UserConstants;
import com.prometheus.hoc.user.UserTypeEnum;
import com.prometheus.hoc.user.eo.UserEO;
import com.prometheus.hoc.user.eo.UserExtendedInfoEO;
import com.prometheus.hoc.user.util.SimpleCipherUtil;
import com.prometheus.hoc.user.vo.UserVO;
import com.sun.jersey.core.util.Base64;

/**
 * @author haibin
 * 
 */
@Component
@Path("user")
public class UserRestService {
	private static Logger logger = Logger.getLogger(UserRestService.class);
	
	@Autowired
	private BaseHibernateDao baseHibernateDao = new BaseHibernateDao();

	@GET
	@Path("getbyid")
	@Produces({MediaType.APPLICATION_JSON})
	public UserVO getUserInfoById(@QueryParam("userId") String userId) {
		UserVO vo = new UserVO();
		UserEO eo = (UserEO) baseHibernateDao.findById(
				UserEO.class, userId);
		try {
			BeanUtils.copyProperties(eo, vo);
		} catch (Exception e) {
			logger.debug("failed to convert the property", e);
		}
		return vo;
	}

	@POST
	@Path("register")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public int registerUser(UserVO vo) {
		int rcode = -1;
		vo.setCreatedDate(new Date());
		vo.setLastModified(new Date());
		vo.setStatus(UserConstants.ACCOUNT_STATUS_ACTIVE);
		vo.setType(UserTypeEnum.INDIVIDUAL.value());
		
		UserEO eo = new UserEO();
		try {
			BeanUtils.copyProperties(eo, vo);
		} catch (Exception e) {
			// do nothing
		}
		
		rcode = baseHibernateDao.save(eo);
		
		UserExtendedInfoEO ext = new UserExtendedInfoEO();
		try {
			BeanUtils.copyProperties(ext, vo);
		} catch (Exception e) {
			//do nothing
		}
		if(rcode > 0){
			ext.setUserId(eo.getUserId());
			rcode = baseHibernateDao.save(ext);
		}
		return rcode;
	}
	
	
	@GET
	@Path("active")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public int activeUser(@QueryParam("code") String code) {
		String userId = new String(Base64.decode(code));
		UserEO userEO = (UserEO) baseHibernateDao.findById(UserEO.class,Long.parseLong(userId));
		if(userEO != null){
			userEO.setStatus(UserConstants.ACCOUNT_STATUS_ACTIVE);
		}
		int res = baseHibernateDao.save(userEO);
		return res;
	}
	
	public byte[] generateActiveCode(Long userId){
		return Base64.encode(userId.toString());
	}
	
	
	
	public static void main(String[] args) {
		UserRestService s = new UserRestService();
		System.out.println(SimpleCipherUtil.encrypt("1"));
		System.out.println(SimpleCipherUtil.decrypt("Uw=="));
	}
}
