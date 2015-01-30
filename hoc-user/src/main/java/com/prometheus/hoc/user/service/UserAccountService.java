package com.prometheus.hoc.user.service;

import com.prometheus.hoc.user.bean.UserAccountBean;
import com.prometheus.hoc.user.dao.UserAccountDao;

public class UserAccountService {
	public static UserAccountBean getUserAccountBean(String userId) {
		UserAccountBean accountBean = new UserAccountBean();
		
		// TODO temp used for testing
//		accountBean.setSalt(userId.getBytes("UTF-8"));
		accountBean.setStatus(1);
		
		UserAccountDao accountDao = new UserAccountDao();
		// TODO accountDao.findById(cls, id)

		return accountBean;
	}
	
	public static void saveUserAccount(UserAccountBean accountBean) {
		
	}
}
