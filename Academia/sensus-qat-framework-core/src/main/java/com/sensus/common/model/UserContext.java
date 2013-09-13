package com.sensus.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Used to hold user context information related to a request.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UserContext
{
	/** The user id. */
	@XmlElement(nillable = false)
	private String userId;

	/** The user id key. */
	@XmlElement(nillable = false)
	private Integer id;

	/** The user role/roles. */
	@XmlElement(nillable = false)
	private String userRole;

	/** The locale. */
	@XmlElement(nillable = true)
	private String localeString;

	/** The tenant. */
	@XmlElement(nillable = true)
	private Tenant tenant;

	/** The authorities. */
	@XmlElement(nillable = true)
	private List<Authority> authorities;

	/**
	 * Instantiates a new user context.
	 */
	public UserContext()
	{
	}

	/**
	 * Instantiates a new user context.
	 *
	 * @param id the id
	 */
	public UserContext(Integer id)
	{
		this();
		setId(id);
	}

	/**
	 * Instantiates a new user context.
	 *
	 * @param userId the user id
	 */
	public UserContext(String userId)
	{
		this();
		setUserId(userId);
	}

	/**
	 * Instantiates a new user context.
	 *
	 * @param newUserId the new user id
	 * @param newTenent the new tenent
	 * @param newAuthorites the authorities
	 */
	public UserContext(String newUserId, Tenant newTenent, List<Authority> newAuthorites)
	{
		this(newUserId);
		setTenant(newTenent);
		setAuthorities(newAuthorites);
	}

	/**
	 * Instantiates a new user context.
	 *
	 * @param newId the id
	 * @param newTenant the new tenant
	 * @param newAuthories the authorities
	 */
	public UserContext(Integer newId, Tenant newTenant, List<Authority> newAuthories)
	{
		this(newId);
		setTenant(newTenant);
		setAuthorities(newAuthories);
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public String getUserRole()
	{
		return userRole;
	}

	/**
	 * Sets the user role.
	 *
	 * @param userRole the new user role
	 */
	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
	}

	/**
	 * Gets the locale. Use the LocaleUtil to convert to java.util.Locale instance.
	 *
	 * @return the locale
	 */
	public String getLocaleString()
	{
		return localeString;
	}

	/**
	 * Sets the locale.
	 *
	 * @param value The locale string. This parameter supports an underline separated value in
	 *            the form of: language_country_variant. Country and variant are optional so you could just pass in a
	 *            value of "EN" to set the language to EN.
	 */
	public void setLocaleString(String value)
	{
		localeString = value;
	}

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	@SuppressWarnings("unchecked")
	public <T extends Tenant> T getTenant()
	{
		return (T)tenant;
	}

	/**
	 * Sets the tenant id.
	 *
	 * @param tenant the new tenant id
	 */
	public void setTenant(Tenant tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * Gets the authorities.
	 *
	 * @return the authorities
	 */
	public List<Authority> getAuthorities()
	{
		return authorities;
	}

	/**
	 * Sets the authorities.
	 *
	 * @param authorities the new authorities
	 */
	public void setAuthorities(List<Authority> authorities)
	{
		this.authorities = authorities;
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

	@Override
	public String toString()
	{
		return "UserContext [getUserId()=" + getUserId() + ", getUserRole()=" + getUserRole() + ", getTenant()="
				+ getTenant() + ", getAuthorities()=" + getAuthorities()
				+ ", getLocaleString()=" + getLocaleString() + ", getId()=" + getId() + "]";
	}

}
