package com.prometheus.hoc.masterdata.person.eo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="ms_person")
public class PersonEO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="person_id", insertable = true, updatable = true, nullable = false, scale=10)
	@OrderBy("personId asc")
	private Long personId;
	
	@Column(name="type", length=2, scale=2)
	private int type;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endData;
	
	@Column(name="first_name", length=100)
	private String firstName;
	
	@Column(name="middle_name", length=100)
	private String middleName;
	
	@Column(name="last_name",length=100)
	private String lastName;
	
	@Column(name="nick_name", length=100)
	private String nickName;
	
	@Column(name="gender", scale=1)
	private int gender;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="country_of_birth",length = 20)
	private String countryOfBirth;
	
	@Column(name="place_of_birth", length = 50)
	private String placeOfBirth;
	
	@Column(name="nationality", length=50)
	private String nationality;
	
	@Column(name="snd_nationality", length=50)
	private String secondNationality;
	
	@Column(name="language", length=5)
	private String language;
	
	@Column(name="religion", length=10)
	private String religion;
	
	@Column(name="marital_status", length=1)
	private int maritalStatus;

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endData
	 */
	public Date getEndData() {
		return endData;
	}

	/**
	 * @param endData the endData to set
	 */
	public void setEndData(Date endData) {
		this.endData = endData;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the countryOfBirth
	 */
	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	/**
	 * @param countryOfBirth the countryOfBirth to set
	 */
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	/**
	 * @return the placeOfBirth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * @param placeOfBirth the placeOfBirth to set
	 */
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the secondNationality
	 */
	public String getSecondNationality() {
		return secondNationality;
	}

	/**
	 * @param secondNationality the secondNationality to set
	 */
	public void setSecondNationality(String secondNationality) {
		this.secondNationality = secondNationality;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the maritalStatus
	 */
	public int getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
}
