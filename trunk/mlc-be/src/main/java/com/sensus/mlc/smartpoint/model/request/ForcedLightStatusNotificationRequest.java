package com.sensus.mlc.smartpoint.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ForcedLightStatusNotificationRequest.
 * 
 * @author - Alex Barros - QAT Omaha
 */

public class ForcedLightStatusNotificationRequest extends AlarmNotificationRequest
{

	/** The light detail data type. */
	private List<LightDetailDataTypeEnum> lightDetailDataType = new ArrayList<LightDetailDataTypeEnum>();

	/**
	 * Instantiates a new forced light status notification request.
	 */
	public ForcedLightStatusNotificationRequest()
	{
	}

	/**
	 * Instantiates a new forced light status notification request.
	 * 
	 * @param userContext the user context
	 */
	public ForcedLightStatusNotificationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new forced light status notification request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public ForcedLightStatusNotificationRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the light detail data type.
	 * 
	 * @return the light detail data type
	 */
	public List<LightDetailDataTypeEnum> getLightDetailDataType()
	{
		return lightDetailDataType;
	}

	/**
	 * Sets the light detail data type.
	 * 
	 * @param lightDetailDataType the new light detail data type
	 */
	public void setLightDetailDataType(List<LightDetailDataTypeEnum> lightDetailDataType)
	{
		this.lightDetailDataType = lightDetailDataType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ForcedLightStatusNotificationRequest [getLightDetailDataType()=" + getLightDetailDataType() + "]";
	}

}