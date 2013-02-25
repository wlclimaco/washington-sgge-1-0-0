package com.sensus.mlc.user.model;

import java.math.BigDecimal;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.group.model.Group;

/**
 * * The Model Object User.
 * 
 * @author - Igor Mendes- QAT Brazil
 */
@SuppressWarnings("serial")
public class User extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The username. */
	private String userName;

	/** The firstName. */
	private String firstName;

	/** The full name. */
	private String fullName;

	/** The role. */
	private String role;

	/** The lastName. */
	private String lastName;

	/** The password. */
	private String password;

	/** The email. */
	private String email;

	/** The enabled. */
	private Boolean enabled;

	/** The all_lights_auth. */
	private Boolean allLightsAuth;

	/** The total lights. */
	private Integer totalLights;

	/** The latitude. */
	private BigDecimal centerLatitude;

	/** The longitude. */
	private BigDecimal centerLongitude;

	/** The groups. */
	private List<Group> groups;

	/**
	 * Instantiates a new User.
	 */
	public User()
	{
	}

	/**
	 * Instantiates a new user with the given user name.
	 * 
	 * @param userNameValue the user name value
	 */
	public User(String userNameValue)
	{
		setUserName(userNameValue);
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the new password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email the new email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Gets the enabled.
	 * 
	 * @return the enabled
	 */
	public Boolean getEnabled()
	{
		return enabled;
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled the new enabled
	 */
	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	/**
	 * Gets the all lights auth.
	 * 
	 * @return the all lights auth
	 */
	public Boolean getAllLightsAuth()
	{
		return allLightsAuth;
	}

	/**
	 * Sets the All lights auth.
	 * 
	 * @param allLightsAuth the new all lights auth
	 */
	public void setAllLightsAuth(Boolean allLightsAuth)
	{
		this.allLightsAuth = allLightsAuth;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the firstName.
	 * 
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Sets the firstName.
	 * 
	 * @param firstNameValue the new first name
	 */
	public void setFirstName(String firstNameValue)
	{
		firstName = firstNameValue;
	}

	/**
	 * Gets the lastName.
	 * 
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Sets the lastName.
	 * 
	 * @param lastNameValue the new last name
	 */
	public void setLastName(String lastNameValue)
	{
		lastName = lastNameValue;
	}

	/**
	 * Gets the full name.
	 * 
	 * @return the full name
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * Sets the full name.
	 * 
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * Gets the total lights.
	 * 
	 * @return the total lights
	 */
	public Integer getTotalLights()
	{
		return totalLights;
	}

	/**
	 * Sets the total lights.
	 * 
	 * @param totalLights the new total lights
	 */
	public void setTotalLights(Integer totalLights)
	{
		this.totalLights = totalLights;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public BigDecimal getLatitude()
	{
		return centerLatitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the new latitude
	 */
	public void setLatitude(BigDecimal latitude)
	{
		centerLatitude = latitude;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public BigDecimal getLongitude()
	{
		return centerLongitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(BigDecimal longitude)
	{
		centerLongitude = longitude;
	}

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<Group> getGroups()
	{
		return groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups the new groups
	 */
	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}

	/**
	 * Gets the role.
	 * 
	 * @return the role
	 */
	public String getRole()
	{
		return role;
	}

	/**
	 * Sets the role.
	 * 
	 * @param roles the new role
	 */
	public void setRole(String role)
	{
		this.role = role;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName the new user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Gets the center latitude.
	 * 
	 * @return the center latitude
	 */
	public BigDecimal getCenterLatitude()
	{
		return centerLatitude;
	}

	/**
	 * Sets the center latitude.
	 * 
	 * @param centerLatitude the new center latitude
	 */
	public void setCenterLatitude(BigDecimal centerLatitude)
	{
		this.centerLatitude = centerLatitude;
	}

	/**
	 * Gets the center longitude.
	 * 
	 * @return the center longitude
	 */
	public BigDecimal getCenterLongitude()
	{
		return centerLongitude;
	}

	/**
	 * Sets the center longitude.
	 * 
	 * @param centerLongitude the new center longitude
	 */
	public void setCenterLongitude(BigDecimal centerLongitude)
	{
		this.centerLongitude = centerLongitude;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "User [getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getEnabled()=" + getEnabled()
				+ ", getAllLightsAuth()=" + getAllLightsAuth() + ", getId()=" + getId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getFullName()= " + getFullName()
				+ "getTotalLights()=" + getTotalLights() + ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getGroups()=" + getGroups() + ", getRole()=" + getRole() + ", getUserName()="
				+ getUserName() + ", getCenterLatitude()=" + getCenterLatitude() + ", getCenterLongitude()="
				+ getCenterLongitude() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()="
				+ getCreateDate() + ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate()
				+ "]";
	}

}