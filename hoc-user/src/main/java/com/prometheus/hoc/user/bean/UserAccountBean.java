package com.prometheus.hoc.user.bean;

import java.util.Date;

public class UserAccountBean {
	
	private String userId;
	
	private String userName;
	
	private byte[] passwordHash;
	
	private int status;

	private String emailAddress;
	
	private String locale;
	
	private Long passwordExpiration;
	
	private Date creationDate;
	
	private Date lastSuccessLogin;
	
	private Date lastModified;
	
	private int loginAttempts;
	
	private byte[] salt;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the passwordHash
	 */
	public byte[] getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}
	
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	/**
	 * @return the passwordExpiration
	 */
	public Long getPasswordExpiration() {
		return passwordExpiration;
	}
	
	/**
	 * @param passwordExpiration the passwordExpiration to set
	 */
	public void setPasswordExpiration(Long passwordExpiration) {
		this.passwordExpiration = passwordExpiration;
	}
	
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * @return the lastSuccessLogin
	 */
	public Date getLastSuccessLogin() {
		return lastSuccessLogin;
	}
	
	/**
	 * @param lastSuccessLogin the lastSuccessLogin to set
	 */
	public void setLastSuccessLogin(Date lastSuccessLogin) {
		this.lastSuccessLogin = lastSuccessLogin;
	}
	
	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}
	
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * @return the loginAttempts
	 */
	public int getLoginAttempts() {
		return loginAttempts;
	}
	
	/**
	 * @param loginAttempts the loginAttempts to set
	 */
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
	
	/**
	 * @return the salt
	 */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
}
