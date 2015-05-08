package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * Class represents a grouping of {@link TemplateProductPlan}'s. Provided as a convenience class for setting up new
 * {@link ProductPlan}'s.
 */
@SuppressWarnings("serial")
public class ProductPlanTemplateGroup extends QATModelOL
{
	/** The id of the product plan template group. */
	private Integer id;

	/** The name of the product plan template gorup. */
	private String name;

	/** The status. */
	private StatusEnum status;

	/** The list of template product plans associated. */
	private List<TemplateProductPlan> templateProductPlanList;

	/**
	 * Default Constructor.
	 */
	public ProductPlanTemplateGroup()
	{
		super();
	}

	/**
	 * Get the id of the product plan template group.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the product plan template group.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the name of the product plan template group.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the product plan template group.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the status.
	 *
	 * @return The status.
	 */
	public StatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Get the status value.
	 *
	 * @return The status value.
	 */
	public Integer getStatusValue()
	{
		if (getStatus() == null)
		{
			return null;
		}

		return getStatus().getValue();
	}

	/**
	 * Set the status.
	 *
	 * @param status The status to set.
	 */
	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Set the status based on the enumeration value.
	 *
	 * @param statusValue The status enumeration value to set.
	 */
	public void setStatusValue(Integer statusValue)
	{
		status = StatusEnum.enumForValue(statusValue);
	}

	/**
	 * Get the {@link List} of {@link TemplateProductPlan}'s.
	 *
	 * @return The list of template product plan's.
	 */
	public List<TemplateProductPlan> getTemplateProductPlanList()
	{
		if (ValidationUtil.isNull(templateProductPlanList))
		{
			setTemplateProductPlanList(new ArrayList<TemplateProductPlan>());
		}

		return templateProductPlanList;
	}

	/**
	 * Set the {@link List} of {@link TemplateProductPlan}'s.
	 *
	 * @param templateProductPlanList The list of template product plan's.
	 */
	public void setTemplateProductPlanList(List<TemplateProductPlan> templateProductPlanList)
	{
		this.templateProductPlanList = templateProductPlanList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroup [getId()=" + getId() + ", getName()=" + getName() + ", getStatus()="
				+ getStatus() + ", getStatusValue()=" + getStatusValue() + ", getTemplateProductPlanList()="
				+ getTemplateProductPlanList() + ", toString()=" + super.toString() + "]";
	}
}
