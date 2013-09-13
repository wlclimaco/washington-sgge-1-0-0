package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class ConnectHanDeviceAction.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class ConnectHanDeviceAction extends HanAction
{
	/** The security token type. */
	private String securityTokenType;

	/** The pre config link key. */
	private String preConfigLinkKey;

	/**
	 * Instantiates a new connect han device action.
	 */
	public ConnectHanDeviceAction()
	{
		super(new ActionType(ActionTypeEnum.CONNECT_HAN_DEVICE));
	}

	/**
	 * Instantiates a new connect han device action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public ConnectHanDeviceAction(boolean isMonitored, boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new connect han device action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public ConnectHanDeviceAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new connect han device action.
	 * 
	 * @param securityTokenTypeParam the security token type param
	 * @param preConfigLinkKeyParam the pre config link key param
	 */
	public ConnectHanDeviceAction(String securityTokenTypeParam, String preConfigLinkKeyParam)
	{
		this();
		setSecurityTokenType(securityTokenTypeParam);
		setPreConfigLinkKey(preConfigLinkKeyParam);
	}

	/**
	 * Instantiates a new connect han device action.
	 * 
	 * @param action the action
	 */
	public ConnectHanDeviceAction(DMAction action)
	{
		this();
		setId(action.getId());
		setCreateDate(action.getCreateDate());
		setCreateUser(action.getCreateUser());
		setDevices(action.getDevices());
		setGroups(action.getGroups());
		setActionTime(action.getActionTime());
		setIsMonitored(action.getIsMonitored());
		setProcessId(action.getProcessId());
		setTags(action.getTags());
		setTotalDevices(action.getTotalDevices());
	}

	/**
	 * Gets the security token type.
	 * 
	 * @return the security token type
	 */
	public String getSecurityTokenType()
	{
		return securityTokenType;
	}

	/**
	 * Sets the security token type.
	 * 
	 * @param securityTokenType the new security token type
	 */
	public void setSecurityTokenType(String securityTokenType)
	{
		this.securityTokenType = securityTokenType;
	}

	/**
	 * Gets the pre config link key.
	 * 
	 * @return the pre config link key
	 */
	public String getPreConfigLinkKey()
	{
		return preConfigLinkKey;
	}

	/**
	 * Sets the pre config link key.
	 * 
	 * @param preConfigLinkKey the new pre config link key
	 */
	public void setPreConfigLinkKey(String preConfigLinkKey)
	{
		this.preConfigLinkKey = preConfigLinkKey;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.model.HanAction#toString()
	 */
	@Override
	public String toString()
	{
		return "ConnectHanDeviceAction [getSecurityTokenType()=" + getSecurityTokenType() + ", getPreConfigLinkKey()="
				+ getPreConfigLinkKey() + ", toString()=" + super.toString() + "]";
	}

}
