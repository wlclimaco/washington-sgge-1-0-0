package com.sensus.dm.common.tenant.model;

import com.sensus.common.model.Tenant;

/**
 * The Class DMTenant.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class DMTenant extends Tenant
{

	/** The key. */
	private String key;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new tenant.
	 */
	public DMTenant()
	{
	}

	/**
	 * Instantiates a new dM tenant.
	 * 
	 * @param keyParam the key param
	 */
	public DMTenant(String keyParam)
	{
		setKey(keyParam);
	}

	/**
	 * Instantiates a new dM tenant.
	 * 
	 * @param keyParam the key param
	 * @param nameParam the name param
	 */
	public DMTenant(String keyParam, String nameParam)
	{
		setKey(keyParam);
		setName(nameParam);
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key the new key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.Tenant#toString()
	 */
	@Override
	public String toString()
	{
		return "DMTenant [getKey()=" + getKey() + ", getName()=" + getName() + ", toString()=" + super.toString() + "]";
	}

}
