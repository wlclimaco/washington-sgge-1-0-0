package com.sensus.dm.elec.device.model.request;

import java.util.Date;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.ServiceTypeEnum;

/**
 * The Class DeviceReadingRequest.
 * 
 * @author - QAT Brazil.
 */
public class DeviceReadingRequest extends InquiryPaginationRequest
{

	/** The meter. */
	private Device device;

	/** The initial date. */
	private Date initialDate;

	/** The end date. */
	private Date endDate;

	/**
	 * Instantiates a new inquiry interval read request.
	 * 
	 */
	public DeviceReadingRequest()
	{
	}

	/**
	 * Instantiates a new inquiry interval read request.
	 * 
	 * @param deviceParam the device param
	 */
	public DeviceReadingRequest(Device deviceParam)
	{
		setDevice(deviceParam);
	}

	/**
	 * Instantiates a new inquiry interval read request.
	 * 
	 * @param userContext the user context
	 */
	public DeviceReadingRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new inquiry interval read request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public DeviceReadingRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry interval read request.
	 * 
	 * @param userContext the user context
	 * @param serviceTypeEnum the service type enum
	 */
	public DeviceReadingRequest(UserContext userContext, ServiceTypeEnum serviceTypeEnum)
	{
		super(userContext);
		setServiceTypeEnum(serviceTypeEnum);
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceReadingRequest [getIsMonitored()=" + getIsMonitored() + ", getDevice()=" + getDevice()
				+ ", getInitialDate()=" + getInitialDate() + ", getEndDate()=" + getEndDate() + ", toString()="
				+ super.toString() + "]";
	}

}