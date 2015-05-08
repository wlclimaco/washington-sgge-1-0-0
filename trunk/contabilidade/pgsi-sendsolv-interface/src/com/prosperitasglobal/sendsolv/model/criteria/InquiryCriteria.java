package com.prosperitasglobal.sendsolv.model.criteria;

/**
 * @author aporto
 * @version 1.0
 * @created 21-Aug-2014 11:51:40 AM
 */
public class InquiryCriteria
{
	/** Attributes. */
	private String name;

	/** The has product plan. */
	private Boolean hasProductPlan;

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
	 * @param name the name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the hasProductPlan
	 */
	public Boolean getHasProductPlan()
	{
		return hasProductPlan;
	}

	/**
	 * @param hasProductPlan the hasProductPlan to set
	 */
	public void setHasProductPlan(Boolean hasProductPlan)
	{
		this.hasProductPlan = hasProductPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryCriteria [getName()=" + getName() + ", getHasProductPlan()=" + getHasProductPlan() + "]";
	}
}
