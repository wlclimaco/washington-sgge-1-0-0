package com.sensus.mlc.smartpoint.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AlarmNotificationRequest.
 * 
 * @author - Alex Barros - QAT Omaha
 * 
 */
public class AlarmNotificationRequest extends ChannelSetupAuditRequest
{

	/** The operational Data. */
	private List<OperationalData> operationalData = new ArrayList<OperationalData>();

	/** The properties. */
	private List<LightParameter> lightParameters = new ArrayList<LightParameter>();

	/** The status exception. */
	private List<StatusException> statusException = new ArrayList<StatusException>();

	/**
	 * Instantiates a new alarm notification request.
	 */
	public AlarmNotificationRequest()
	{
	}

	/**
	 * Instantiates a new alarm notification request.
	 * 
	 * @param userContext the user context
	 */
	public AlarmNotificationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new alarm notification request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public AlarmNotificationRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Sets the operational data.
	 * 
	 * @param operationalData the new operational data
	 */
	public void setOperationalData(List<OperationalData> operationalData)
	{
		this.operationalData = operationalData;
	}

	/**
	 * Adds the operational data.
	 * 
	 * @param operationalData the operational data
	 */
	public void addOperationalData(OperationalData operationalDataValue)
	{
		getOperationalData().add(operationalDataValue);
	}

	/**
	 * Gets the operational data.
	 * 
	 * @return the operational data
	 */
	public List<OperationalData> getOperationalData()
	{
		return operationalData;
	}

	/**
	 * Gets the light parameters.
	 * 
	 * @return the light parameters
	 */
	public List<LightParameter> getLightParameters()
	{
		return lightParameters;
	}

	/**
	 * Sets the light parameters.
	 * 
	 * @param lightParameters the new light parameters
	 */
	public void setLightParameters(List<LightParameter> lightParameters)
	{
		this.lightParameters = lightParameters;
	}

	/**
	 * Adds the light parameter.
	 * 
	 * @param lightParameter the light parameter
	 */
	public void addLightParameter(LightParameter lightParameter)
	{
		getLightParameters().add(lightParameter);
	}

	/**
	 * Gets the status exception.
	 * 
	 * @return the status exception
	 */
	public List<StatusException> getStatusException()
	{

		return statusException;
	}

	/**
	 * Sets the status exception.
	 * 
	 * @param statusException the new status exception
	 */
	public void setStatusException(List<StatusException> statusException)
	{
		this.statusException = statusException;
	}

	@Override
	public String toString()
	{
		return "AlarmNotificationRequest [getOperationalData()=" + getOperationalData() + ", getLightParameters()="
				+ getLightParameters() + ", getStatusException()=" + getStatusException() + "]";
	}

}