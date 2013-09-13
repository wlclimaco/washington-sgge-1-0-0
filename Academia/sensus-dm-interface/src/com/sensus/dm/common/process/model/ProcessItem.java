package com.sensus.dm.common.process.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.cbof.model.Device;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class ProcessItem.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class ProcessItem extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The device. */
	private Device device;

	/** The process item status enum. */
	private ProcessItemStatusEnum processItemStatusEnum;

	/** The process item histories. */
	private List<ProcessItemHistory> processItemHistories;

	/** The message. */
	private String message;

	/** The participation. */
	private String participation;

	/** The properties. */
	private List<Property> properties;

	/** The row num. */
	private Integer rowNum;

	/**
	 * Instantiates a new process item.
	 */
	public ProcessItem()
	{
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param idParam the id
	 */
	public ProcessItem(Integer idParam)
	{
		setId(idParam);
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param deviceParam the device
	 */
	public ProcessItem(Device deviceParam)
	{
		setDevice(deviceParam);
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param idParam the id
	 * @param deviceParam the device
	 */
	public ProcessItem(Integer idParam, Device deviceParam)
	{
		setDevice(deviceParam);
		setId(idParam);
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param idParam the id
	 * @param processItemStatusEnumParam the process item status enum
	 */
	public ProcessItem(Integer idParam, ProcessItemStatusEnum processItemStatusEnumParam)
	{
		setId(idParam);
		setProcessItemStatusEnum(processItemStatusEnumParam);
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param deviceParam the device
	 * @param processItemStatusEnumParam the process item status enum
	 */
	public ProcessItem(Device deviceParam, ProcessItemStatusEnum processItemStatusEnumParam)
	{
		setDevice(deviceParam);
		setProcessItemStatusEnum(processItemStatusEnumParam);
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param deviceParam the device
	 * @param processItemStatusEnumParam the process item status enum
	 * @param messageParam the message
	 */
	public ProcessItem(Device deviceParam, ProcessItemStatusEnum processItemStatusEnumParam, String messageParam)
	{
		setDevice(deviceParam);
		setProcessItemStatusEnum(processItemStatusEnumParam);
		setMessage(messageParam);
	}

	/**
	 * Instantiates a new process item.
	 * 
	 * @param processItemHistoryList the process item histories
	 */
	public ProcessItem(List<ProcessItemHistory> processItemHistoryList)
	{
		setProcessItemHistories(processItemHistoryList);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the device.
	 * 
	 * @return the device
	 */
	public Device getDevice()
	{
		return device;
	}

	/**
	 * Sets the device.
	 * 
	 * @param device the new device
	 */
	public void setDevice(Device device)
	{
		this.device = device;
	}

	/**
	 * Gets the process item status enum.
	 * 
	 * @return the process item status enum
	 */
	public ProcessItemStatusEnum getProcessItemStatusEnum()
	{
		return processItemStatusEnum;
	}

	/**
	 * Sets the process item status enum.
	 * 
	 * @param processItemStatusEnum the new process item status enum
	 */
	public void setProcessItemStatusEnum(ProcessItemStatusEnum processItemStatusEnum)
	{
		this.processItemStatusEnum = processItemStatusEnum;
	}

	/**
	 * Gets the process item status enum value.
	 * 
	 * @return the process item status enum value
	 */
	public Integer getProcessItemStatusEnumValue()
	{
		ProcessItemStatusEnum processItemStatus = getProcessItemStatusEnum();

		if (processItemStatus != null)
		{
			return processItemStatus.getValue();
		}

		return null;
	}

	/**
	 * Sets the process item status enum value.
	 * 
	 * @param processItemStatusEnumValue the new process item status enum value
	 */
	public void setProcessItemStatusEnumValue(Integer processItemStatusEnumValue)
	{
		setProcessItemStatusEnum(ProcessItemStatusEnum.enumForValue(processItemStatusEnumValue));
	}

	/**
	 * Gets the process item histories.
	 * 
	 * @return the process item histories
	 */
	public List<ProcessItemHistory> getProcessItemHistories()
	{
		return processItemHistories;
	}

	/**
	 * Gets the first process item history.
	 * 
	 * @return the first process item history
	 */
	public ProcessItemHistory getFirstProcessItemHistory()
	{
		if ((getProcessItemHistories() != null) && !getProcessItemHistories().isEmpty())
		{
			return getProcessItemHistories().get(0);
		}

		return null;
	}

	/**
	 * Sets the process item histories.
	 * 
	 * @param processItemHistories the new process item histories
	 */
	public void setProcessItemHistories(List<ProcessItemHistory> processItemHistories)
	{
		this.processItemHistories = processItemHistories;
	}

	/**
	 * Adds the process item history.
	 * 
	 * @param processItemHistory the process item history
	 */
	public void addProcessItemHistory(ProcessItemHistory processItemHistory)
	{
		if (getProcessItemHistories() == null)
		{
			setProcessItemHistories(new ArrayList<ProcessItemHistory>());
		}

		getProcessItemHistories().add(processItemHistory);
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Gets the participation.
	 * 
	 * @return the participation
	 */
	public String getParticipation()
	{
		return participation;
	}

	/**
	 * Sets the participation.
	 * 
	 * @param participation the new participation
	 */
	public void setParticipation(String participation)
	{
		this.participation = participation;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public List<Property> getProperties()
	{
		return properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(List<Property> properties)
	{
		this.properties = properties;
	}

	/**
	 * Gets the row num.
	 * 
	 * @return the row num
	 */
	public Integer getRowNum()
	{
		return rowNum;
	}

	/**
	 * Sets the row num.
	 * 
	 * @param rowNum the new row num
	 */
	public void setRowNum(Integer rowNum)
	{
		this.rowNum = rowNum;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessItem [getId()=" + getId() + ", getDevice()=" + getDevice() + ", getProcessItemStatusEnum()="
				+ getProcessItemStatusEnum() + ", getProcessItemStatusEnumValue()=" + getProcessItemStatusEnumValue()
				+ ", getProcessItemHistories()=" + getProcessItemHistories() + ", getFirstProcessItemHistory()="
				+ getFirstProcessItemHistory() + ", getMessage()=" + getMessage() + ", getParticipation()="
				+ getParticipation() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + ", getProperties()=" + getProperties()
				+ ", getRowNum()=" + getRowNum() + "]";
	}
}
