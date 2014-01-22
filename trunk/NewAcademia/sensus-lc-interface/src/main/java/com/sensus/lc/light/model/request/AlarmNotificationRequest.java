package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.LightParameter;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.tenant.model.Tenant;

/**
 * Request object for AlarmNotification.
 * 
 * @author - Alex Barros, Thiago - QAT
 */
public class AlarmNotificationRequest extends ChannelSetupAuditRequest
{

	/** The operational Data. */
	private List<OperationalData> operationalData = new ArrayList<OperationalData>();

	/** The light parameters. */
	private List<LightParameter> lightParameters = new ArrayList<LightParameter>();

	/** The alert sub types. */
	private List<AlertSubTypeEnum> alertSubTypes = new ArrayList<AlertSubTypeEnum>();

	/** The light in communication failure. */
	private Boolean lightInCommunicationFailure = false;

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
	 * @return the alertSubTypes
	 */
	public List<AlertSubTypeEnum> getAlertSubTypes()
	{
		return alertSubTypes;
	}

	/**
	 * @param alertSubTypes the alertSubTypes to set
	 */
	public void setAlertSubTypes(List<AlertSubTypeEnum> alertSubTypes)
	{
		this.alertSubTypes = alertSubTypes;
	}

	/**
	 * @return the lightInCommunicationFailure
	 */
	public Boolean getLightInCommunicationFailure()
	{
		return lightInCommunicationFailure;
	}

	/**
	 * Checks if is light in communication failure.
	 * 
	 * @return the boolean
	 */
	public Boolean isLightInCommunicationFailure()
	{
		return lightInCommunicationFailure;
	}

	/**
	 * Sets the light in communication failure.
	 * 
	 * @param lightInCommunicationFailure the new light in communication failure
	 */
	public void setLightInCommunicationFailure(Boolean lightInCommunicationFailure)
	{
		this.lightInCommunicationFailure = lightInCommunicationFailure;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AlarmNotificationRequest [operationalData=" + operationalData + ", lightParameters=" + lightParameters
				+ ", alertSubTypes=" + alertSubTypes + ", lightInCommunicationFailure=" + lightInCommunicationFailure
				+ "]";
	}

}