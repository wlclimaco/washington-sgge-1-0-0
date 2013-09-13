package com.sensus.dm.elec.action.model;

import java.util.Date;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class SendHanTextMessageAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class SendHanTextMessageAction extends HanAction
{
	/** The text message. */
	private String textMessage;

	/** The duration han text message. */
	private Integer durationHANTextMessage;

	/**
	 * Instantiates a new send han text message action.
	 */
	public SendHanTextMessageAction()
	{
		super(new ActionType(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE));
	}

	/**
	 * Instantiates a new send han text message action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 * @param actionTime the action time
	 */
	public SendHanTextMessageAction(Boolean onDemand, Boolean isMonitored, Date actionTime)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
		setActionTime(actionTime);
	}

	/**
	 * Instantiates a new send han text message action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param actionTime the action time
	 */
	public SendHanTextMessageAction(Integer id, boolean isMonitored, boolean onDemand, Date actionTime)
	{
		this(isMonitored, onDemand, actionTime);
		setId(id);
	}

	/**
	 * Instantiates a new send han text message action.
	 * 
	 * @param id the id
	 */
	public SendHanTextMessageAction(Integer id)
	{
		this();
		setId(id);
	}

	/**
	 * Instantiates a new send han text message action.
	 * 
	 * @param action the action view
	 */
	public SendHanTextMessageAction(DMAction action)
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
	 * Instantiates a new send han text message action.
	 * 
	 * @param textMessageParam the text message param
	 * @param durationHANTextMessageParam the duration han text message param
	 * @param displayOnParam the display on param
	 */
	public SendHanTextMessageAction(String textMessageParam, Integer durationHANTextMessageParam, Date displayOnParam)
	{
		this();
		setTextMessage(textMessageParam);
		setDurationHANTextMessage(durationHANTextMessageParam);
		setActionTime(displayOnParam);
	}

	/**
	 * Gets the text message.
	 * 
	 * @return the text message
	 */
	public String getTextMessage()
	{
		return textMessage;
	}

	/**
	 * Sets the text message.
	 * 
	 * @param textMessage the new text message
	 */
	public void setTextMessage(String textMessage)
	{
		this.textMessage = textMessage;
	}

	/**
	 * Gets the duration han text message.
	 * 
	 * @return the duration han text message
	 */
	public Integer getDurationHANTextMessage()
	{
		return durationHANTextMessage;
	}

	/**
	 * Sets the duration han text message.
	 * 
	 * @param durationHANTextMessage the new duration han text message
	 */
	public void setDurationHANTextMessage(Integer durationHANTextMessage)
	{
		this.durationHANTextMessage = durationHANTextMessage;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.model.HanAction#toString()
	 */
	@Override
	public String toString()
	{
		return "SendHanTextMessageAction [getTextMessage()=" + getTextMessage() + ", getDurationHANTextMessage()="
				+ getDurationHANTextMessage() + ", toString()=" + super.toString() + "]";
	}
}
