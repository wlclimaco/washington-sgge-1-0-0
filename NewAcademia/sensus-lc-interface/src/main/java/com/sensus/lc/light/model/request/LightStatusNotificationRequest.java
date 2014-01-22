package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.light.model.LightDetailDataTypeEnum;
import com.sensus.lc.light.model.LightParameter;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class AlarmNotificationRequest.
 * 
 */

public class LightStatusNotificationRequest extends AlarmNotificationRequest
{
	private List<LightParameter> lightParameters = new ArrayList<LightParameter>();
	private List<LightDetailDataTypeEnum> lightDetailDataType = new ArrayList<LightDetailDataTypeEnum>();
	private String transactionId;
	private Boolean forced = Boolean.FALSE;

	/**
	 * @return the transactionId
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	/**
	 * @return the forced
	 */
	public Boolean isForced()
	{
		return forced;
	}

	/**
	 * @param forced the forced to set
	 */
	public void setForced(Boolean forced)
	{
		this.forced = forced;
	}

	/**
	 * Instantiates a new light status notification request.
	 */
	public LightStatusNotificationRequest()
	{
	}

	/**
	 * Instantiates a new light status notification request.
	 * 
	 * @param userContext the user context
	 */
	public LightStatusNotificationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new light status notification request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightStatusNotificationRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * @return the lightParameters
	 */
	@Override
	public List<LightParameter> getLightParameters()
	{
		return lightParameters;
	}

	/**
	 * @param lightParameters the lightParameters to set
	 */
	@Override
	public void setLightParameters(List<LightParameter> lightParameters)
	{
		this.lightParameters = lightParameters;
	}

	/**
	 * @return the lightDetailDataType
	 */
	public List<LightDetailDataTypeEnum> getLightDetailDataType()
	{
		return lightDetailDataType;
	}

	/**
	 * @param lightDetailDataType the lightDetailDataType to set
	 */
	public void setLightDetailDataType(List<LightDetailDataTypeEnum> lightDetailDataType)
	{
		this.lightDetailDataType = lightDetailDataType;
	}

	@Override
	public String toString()
	{
		return "LightStatusNotificationRequest [getTransactionId()=" + getTransactionId() + ", isForced()="
				+ isForced() + ", getLightParameters()=" + getLightParameters() + ", getLightDetailDataType()="
				+ getLightDetailDataType() + ", toString()=" + super.toString() + "]";
	}
}