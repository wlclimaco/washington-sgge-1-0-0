package com.sensus.mlc.process.model;

import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class ProcessResult.
 */
public class ProcessItem
{

	/** The light. */
	private Light light;

	/** The process result. */
	private ProcessItemStatusEnum processItemStatusEnum;

	/** The process item status reason. */
	private ProcessStatusReasonEnum processStatusReasonEnum;

	/**
	 * Instantiates a new process result.
	 */
	public ProcessItem()
	{

	}

	/**
	 * Instantiates a new process item.
	 *
	 * @param lightValue the light value
	 * @param processItemStatusEnumValue the process item status enum value
	 * @param processStatusReasonEnumValue the process status reason enum value
	 */
	public ProcessItem(Light lightValue, ProcessItemStatusEnum processItemStatusEnumValue,
			ProcessStatusReasonEnum processStatusReasonEnumValue)
	{
		setLight(lightValue);
		setProcessItemStatusEnum(processItemStatusEnumValue);
		setProcessStatusReasonEnum(processStatusReasonEnumValue);
	}

	/**
	 * Instantiates a new process item.
	 *
	 * @param processItemStatusEnumValue the process item status enum value
	 */
	public ProcessItem(ProcessItemStatusEnum processItemStatusEnumValue)
	{
		setProcessItemStatusEnum(processItemStatusEnumValue);
	}

	/**
	 * Gets the light.
	 *
	 * @return the light
	 */
	public Light getLight()
	{
		return light;
	}

	/**
	 * Sets the light.
	 *
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/**
	 * Gets the process result.
	 *
	 * @return the process result
	 */
	public ProcessItemStatusEnum getProcessResult()
	{
		return processItemStatusEnum;

	}

	/**
	 * Gets the process reason.
	 *
	 * @return the process reason
	 */
	public ProcessStatusReasonEnum getProcessReason()
	{
		return processStatusReasonEnum;

	}

	/**
	 * Sets the process result.
	 *
	 * @param processItemStatusEnum the new process item status enum
	 */
	public void setProcessItemStatusEnum(ProcessItemStatusEnum processItemStatusEnum)
	{
		this.processItemStatusEnum = processItemStatusEnum;
	}

	/**
	 * Sets the process status reason enum.
	 *
	 * @param processStatusReasonEnum the new process status reason enum
	 */
	public void setProcessStatusReasonEnum(ProcessStatusReasonEnum processStatusReasonEnum)
	{
		this.processStatusReasonEnum = processStatusReasonEnum;
	}

	/**
	 * Sets the process result value.
	 *
	 * @param processItemStatusEnumValue the new process item status enum value
	 */
	public void setProcessItemStatusEnumValue(Integer processItemStatusEnumValue)
	{
		processItemStatusEnum = ProcessItemStatusEnum.enumForValue(processItemStatusEnumValue);
	}

	/**
	 * Sets the process status reason enum value.
	 *
	 * @param processStatusReasonEnumValue the new process status reason enum value
	 */
	public void setProcessStatusReasonEnumValue(Integer processStatusReasonEnumValue)
	{
		processStatusReasonEnum = ProcessStatusReasonEnum.enumForValue(processStatusReasonEnumValue);
	}

	/**
	 * Gets the process result value.
	 *
	 * @return the process result value
	 */
	public Integer getProcessItemStatusEnumValue()
	{
		if (processItemStatusEnum == null)
		{
			return null;
		}
		return processItemStatusEnum.getValue();
	}

	/**
	 * Gets the process status reason enum value.
	 *
	 * @return the process status reason enum value
	 */
	public Integer getProcessStatusReasonEnumValue()
	{
		if (processStatusReasonEnum == null)
		{
			return null;
		}
		return processStatusReasonEnum.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessItem [getLight()=" + getLight() + ", getProcessResult()=" + getProcessResult()
				+ ", getProcessReason()=" + getProcessReason() + ", getProcessItemStatusEnumValue()="
				+ getProcessItemStatusEnumValue() + ", getProcessStatusReasonEnumValue()="
				+ getProcessStatusReasonEnumValue() + "]";
	}

}
