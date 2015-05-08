package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * Criteria member used for doing a lookup on a business product plan in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class ProductPlanTemplateGroupCriteria implements Serializable
{
	/**
	 * Default constructor.
	 */
	public ProductPlanTemplateGroupCriteria()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroupCriteria []";
	}
}
