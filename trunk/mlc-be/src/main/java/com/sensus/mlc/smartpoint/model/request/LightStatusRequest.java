package com.sensus.mlc.smartpoint.model.request;

import java.util.Date;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class LightStatusRequest.
 * 
 * @author - Alex Barros - QAT Omaha
 * 
 */
public class LightStatusRequest extends LightingControlRequest
{

	/** The light id. */
	private Integer lightId;

	/** The Tenant Rni Code. */
	private String tenantRniCode;

	/** The light RniId. */
	private Integer lightRniId;

	/** The initial date. */
	private Date initialDate;

	/** The end date. */
	private Date endDate;

	/**
	 * Instantiates a new light status request.
	 */
	public LightStatusRequest()
	{
	}

	/**
	 * Instantiates a new light status request.
	 * 
	 * @param userContext the user context
	 */
	public LightStatusRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new light status request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightStatusRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext);
	}

	/**
	 * Gets the light id.
	 * 
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the tenant rni code.
	 * 
	 * @return the tenant rni code
	 */
	public String getTenantRniCode()
	{
		return tenantRniCode;
	}

	/**
	 * Sets the tenant rni code.
	 * 
	 * @param tenantRniCode the new tenant rni code
	 */
	public void setTenantRniCode(String tenantRniCode)
	{
		this.tenantRniCode = tenantRniCode;
	}

	/**
	 * Gets the light rni id.
	 * 
	 * @return the light rni id
	 */
	public Integer getLightRniId()
	{
		return lightRniId;
	}

	/**
	 * Sets the light rni id.
	 * 
	 * @param lightRniId the new light rni id
	 */
	public void setLightRniId(Integer lightRniId)
	{
		this.lightRniId = lightRniId;
	}

	/**
	 * Gets the initial date.
	 * 
	 * @return the initial date
	 */
	public Date getInitialDate()
	{
		return initialDate;
	}

	/**
	 * Sets the initial date.
	 * 
	 * @param initialDate the new initial date
	 */
	public void setInitialDate(Date initialDate)
	{
		this.initialDate = initialDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightStatusRequest [getLightId()=" + getLightId() + ", getTenantRniCode()=" + getTenantRniCode()
				+ ", getLightRniId()=" + getLightRniId() + ", getInitialDate()=" + getInitialDate() + ", getEndDate()="
				+ getEndDate() + ", getUserContext()=" + getUserContext() + "]";
	}
}
