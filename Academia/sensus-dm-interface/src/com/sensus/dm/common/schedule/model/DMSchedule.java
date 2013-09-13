package com.sensus.dm.common.schedule.model;

import java.util.Date;
import java.util.List;

import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.Schedule;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class Schedule.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class DMSchedule extends Schedule
{
	/** The properties. */
	private List<Property> properties;

	/** The DM action. */
	private DMAction dmAction;

	/** The service type enum. */
	private ServiceTypeEnum serviceTypeEnum;

	/** The customer id. */
	private String customerId;

	/**
	 * Instantiates a new schedule.
	 */
	public DMSchedule()
	{
	}

	/**
	 * Instantiates a new schedule view.
	 * 
	 * @param id the id
	 */
	public DMSchedule(Integer id)
	{
		setId(id);
	}

	/**
	 * Instantiates a new schedule view.
	 * 
	 * @param name the name
	 */
	public DMSchedule(String name)
	{
		setName(name);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param name the name
	 * @param actionParam the action param
	 */
	public DMSchedule(String name, DMAction actionParam)
	{
		this(name);
		setAction(actionParam);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param id the id
	 * @param name the name
	 */
	public DMSchedule(Integer id, String name)
	{
		setId(id);
		setName(name);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param id the id
	 * @param scheduleStatusEnum the schedule status enum
	 */
	public DMSchedule(Integer id, ScheduleStatusEnum scheduleStatusEnum)
	{
		this(id);
		setStatusEnum(scheduleStatusEnum);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param id the id
	 * @param scheduleStatusEnum the schedule status enum
	 * @param name the name
	 * @param description the description
	 * @param startTime the start time
	 * @param frequency the frequency
	 */
	public DMSchedule(Integer id, ScheduleStatusEnum scheduleStatusEnum, String name, String description,
			Date startTime, Frequency frequency)
	{
		this(id, name);
		setDescription(description);
		setStartTime(startTime);
		setStatusEnum(scheduleStatusEnum);
		setFrequency(frequency);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param name the name
	 * @param scheduleStatusEnum the schedule status enum
	 */
	public DMSchedule(String name, ScheduleStatusEnum scheduleStatusEnum)
	{
		this(name);
		setStatusEnum(scheduleStatusEnum);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param id the id
	 * @param actionParam the action param
	 */
	public DMSchedule(Integer id, DMAction actionParam)
	{
		this(id);
		setAction(actionParam);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param actionParam the action param
	 */
	public DMSchedule(DMAction actionParam)
	{
		setAction(actionParam);
	}

	/**
	 * Instantiates a new dM schedule.
	 * 
	 * @param frequency the frequency
	 */
	public DMSchedule(Frequency frequency)
	{
		setFrequency(frequency);
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
	 * Gets the dm action.
	 * 
	 * @return the dm action
	 */
	public DMAction getDmAction()
	{
		return dmAction;
	}

	/**
	 * Sets the dm action.
	 * 
	 * @param dmAction the new dm action
	 */
	public void setDmAction(DMAction dmAction)
	{
		this.dmAction = dmAction;
	}

	/**
	 * Gets the service type enum.
	 * 
	 * @return the service type enum
	 */
	public ServiceTypeEnum getServiceTypeEnum()
	{
		return serviceTypeEnum;
	}

	/**
	 * Sets the service type enum.
	 * 
	 * @param serviceTypeEnum the new service type enum
	 */
	public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum)
	{
		this.serviceTypeEnum = serviceTypeEnum;
	}

	/**
	 * Gets the service type enum description.
	 * 
	 * @return the service type enum description
	 */
	public String getServiceTypeEnumDescription()
	{
		if (getServiceTypeEnum() != null)
		{
			return getServiceTypeEnum().getServiceTypeDescription();
		}

		return null;
	}

	/**
	 * Sets the device type enum value.
	 * 
	 * @param serviceTypeEnumParam the new service type enum description
	 */
	public void setServiceTypeEnumDescription(String serviceTypeEnumParam)
	{
		setServiceTypeEnum(ServiceTypeEnum.enumForDescription(serviceTypeEnumParam));
	}

	/**
	 * Gets the customer id.
	 * 
	 * @return the customer id
	 */
	public String getCustomerId()
	{
		return customerId;
	}

	/**
	 * Sets the customer id.
	 * 
	 * @param customerId the new customer id
	 */
	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	@Override
	public String toString()
	{
		return "DMSchedule [getProperties()=" + getProperties() + ", getDmAction()=" + getDmAction()
				+ ", getServiceTypeEnum()=" + getServiceTypeEnum() + ", getServiceTypeEnumDescription()="
				+ getServiceTypeEnumDescription() + ", getCustomerId()=" + getCustomerId() + ", toString()="
				+ super.toString() + "]";
	}

}
