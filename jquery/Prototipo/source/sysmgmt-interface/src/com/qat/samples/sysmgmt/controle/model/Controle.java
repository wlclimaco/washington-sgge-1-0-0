package com.qat.samples.sysmgmt.controle.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Controle", propOrder = {"propertys", "id"})
public class Controle extends Util
{

	/** The code. */
	private List<Property> propertys;

	/** The id. */
	private Integer id;

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public List<Property> getPropertys()
	{
		return propertys;
	}

	/**
	 * Sets the code.
	 * 
	 * @param propertys the new code
	 */
	public void setPropertys(List<Property> propertys)
	{
		this.propertys = propertys;
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
	 * Adds the property.
	 * 
	 * @param grantedAuthority the granted authority
	 */
	public void addProperty(Property grantedAuthority)
	{
		if (getPropertys() == null)
		{
			setPropertys(new ArrayList<Property>());
		}

		getPropertys().add(grantedAuthority);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "Controle [getPropertys()=" + getPropertys() + ", getId()=" + getId() + ", toString()="
				+ super.toString() + "]";
	}

}
