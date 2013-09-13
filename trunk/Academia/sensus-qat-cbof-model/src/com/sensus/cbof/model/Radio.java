package com.sensus.cbof.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class Radio.
 * 
 */
@SuppressWarnings("serial")
public class Radio extends SensusModel
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The id. */
	private Integer id;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The customer id. */
	private String customerId;

	/** The devices. */
	private List<Device> devices = new ArrayList<Device>();

	/** The location. */
	private Location location;

	/** The flex net id. */
	private BigInteger flexNetId;

	/** The ip address. */
	private String ipAddress;

	/**
	 * Instantiates a new radio.
	 */
	public Radio()
	{
	}

	/**
	 * Instantiates a new radio.
	 * 
	 * @param locationParam the location
	 */
	public Radio(Location locationParam)
	{
		super();
		setLocation(locationParam);
	}

	/**
	 * Instantiates a new radio.
	 * 
	 * @param paramId the param id
	 * @param paramStartDate the param start date
	 * @param paramEndDate the param end date
	 */
	public Radio(Integer paramId, Date paramStartDate, Date paramEndDate)
	{
		setId(paramId);
		setStartDate(paramStartDate);
		setEndDate(paramEndDate);
	}

	/**
	 * @param flexNetIdValue
	 */
	public Radio(BigInteger flexNetIdValue)
	{
		setFlexNetId(flexNetIdValue);
	}

	/**
	 * Instantiates a new radio.
	 * 
	 * @param customerIdParam the customer id
	 */
	public Radio(String customerIdParam)
	{
		setCustomerId(customerIdParam);
	}

	/**
	 * Instantiates a new radio.
	 * 
	 * @param flexNetIdValue the flex net id value
	 * @param customerIdParam the customer id
	 */
	public Radio(BigInteger flexNetIdValue, String customerIdParam)
	{
		setFlexNetId(flexNetIdValue);
		setCustomerId(customerIdParam);
	}

	/**
	 * Instantiates a new radio.
	 * 
	 * @param flexNetIdValue the flex net id value
	 * @param customerIdParam the customer id param
	 * @param locationParam the location param
	 */
	public Radio(BigInteger flexNetIdValue, String customerIdParam, Location locationParam)
	{
		this(flexNetIdValue, customerIdParam);
		setLocation(locationParam);
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
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
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

	/**
	 * Gets the devices.
	 * 
	 * @return the devices
	 */
	public List<Device> getDevices()
	{
		return devices;
	}

	/**
	 * Sets the devices.
	 * 
	 * @param devices the new devices
	 */
	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	/**
	 * Gets the first device.
	 * 
	 * @return the first device
	 */
	public Device getFirstDevice()
	{
		if ((getDevices() != null) && !getDevices().isEmpty())
		{
			return getDevices().get(ZERO);
		}

		return null;
	}

	/**
	 * Adds the device.
	 * 
	 * @param device the device
	 */
	public void addDevice(Device device)
	{
		if (getDevices() == null)
		{
			setDevices(new ArrayList<Device>());
		}

		getDevices().add(device);
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location the new location
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * Gets the flex net id.
	 * 
	 * @return the flex net id
	 */
	public BigInteger getFlexNetId()
	{
		return flexNetId;
	}

	/**
	 * Gets the flex net id as string.
	 * 
	 * @return the flex net id as string
	 */
	public String getFlexNetIdAsString()
	{
		if (flexNetId != null)
		{
			return flexNetId.toString();
		}

		return null;
	}

	/**
	 * Sets the flex net id.
	 * 
	 * @param flexNetId the new flex net id
	 */
	public void setFlexNetId(BigInteger flexNetId)
	{
		this.flexNetId = flexNetId;
	}

	/**
	 * Sets the flex net id by string.
	 * 
	 * @param flexNetId the new flex net id by string
	 */
	public void setFlexNetIdByString(String flexNetId)
	{
		if (flexNetId != null)
		{
			this.flexNetId = new BigInteger(flexNetId);
		}
	}

	/**
	 * Gets the ip address.
	 * 
	 * @return the ip address
	 */
	public String getIpAddress()
	{
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 * 
	 * @param ipAddress the new ip address
	 */
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString()
	{
		return "Radio [getId()=" + getId() + ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate()
				+ ", getCustomerId()=" + getCustomerId() + ", getDevices()=" + getDevices() + ", getFirstDevice()="
				+ getFirstDevice() + ", getLocation()=" + getLocation() + ", getFlexNetId()=" + getFlexNetId()
				+ ", getFlexNetIdAsString()=" + getFlexNetIdAsString() + ", getIpAddress()=" + getIpAddress() + "]";
	}

}
