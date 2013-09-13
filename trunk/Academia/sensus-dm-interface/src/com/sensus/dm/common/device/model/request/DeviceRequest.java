package com.sensus.dm.common.device.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class DeviceRequest.
 * 
 * @author QAT Global.
 */
public class DeviceRequest extends TenantRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The devices. */
	private List<Device> devices;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The granted authority list. */
	private List<String> grantedAuthorityList;

	/**
	 * Instantiates a new device request.
	 */
	public DeviceRequest()
	{
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param device the device
	 */
	public DeviceRequest(Device device)
	{
		addDevice(device);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param device the device
	 * @param tenantParam the DM Tenant
	 */
	public DeviceRequest(Device device, DMTenant tenantParam)
	{
		addDevice(device);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param device the device
	 * @param serviceTypeEnum the service type enum
	 */
	public DeviceRequest(Device device, ServiceTypeEnum serviceTypeEnum)
	{
		addDevice(device);
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param device the device
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public DeviceRequest(Device device, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addDevice(device);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param deviceList the devices
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public DeviceRequest(List<Device> deviceList, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		setDevices(deviceList);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param device the device
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 * @param deviceSearchParam the device search param
	 */
	public DeviceRequest(Device device, ServiceTypeEnum serviceTypeEnum, DMTenant tenant, DeviceSearch deviceSearchParam)
	{
		this(device, serviceTypeEnum, tenant);
		setDeviceSearch(deviceSearchParam);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public DeviceRequest(DeviceSearch deviceSearchParam)
	{
		setDeviceSearch(deviceSearchParam);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param deviceSearchParam the device search param
	 * @param tenantParam the tenant param
	 */
	public DeviceRequest(DeviceSearch deviceSearchParam, DMTenant tenantParam)
	{
		this(deviceSearchParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param userContext the user context
	 */
	public DeviceRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new device request.
	 * 
	 * @param userContext the user context
	 * @param tenantParam the tenant param
	 */
	public DeviceRequest(UserContext userContext, DMTenant tenantParam)
	{
		super(userContext, tenantParam);
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
	 * Gets the first device.
	 * 
	 * @return the first device
	 */
	public Device getFirstDevice()
	{
		if (getDevices() != null && !getDevices().isEmpty())
		{
			return getDevices().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the devices.
	 * 
	 * @param deviceList the new devices
	 */
	public void setDevices(List<Device> deviceList)
	{
		devices = deviceList;
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
	 * Gets the device search.
	 * 
	 * @return the device search
	 */
	public DeviceSearch getDeviceSearch()
	{
		return deviceSearch;
	}

	/**
	 * Sets the device search.
	 * 
	 * @param deviceSearch the new device search
	 */
	public void setDeviceSearch(DeviceSearch deviceSearch)
	{
		this.deviceSearch = deviceSearch;
	}

	/**
	 * Gets the granted authority list.
	 * 
	 * @return the granted authority list
	 */
	public List<String> getGrantedAuthorityList()
	{
		return grantedAuthorityList;
	}

	/**
	 * Sets the granted authority list.
	 * 
	 * @param grantedAuthorityList the new granted authority list
	 */
	public void setGrantedAuthorityList(List<String> grantedAuthorityList)
	{
		this.grantedAuthorityList = grantedAuthorityList;
	}

	/**
	 * Adds the granted authority.
	 * 
	 * @param grantedAuthority the granted authority
	 */
	public void addGrantedAuthority(String grantedAuthority)
	{
		if (getGrantedAuthorityList() == null)
		{
			setGrantedAuthorityList(new ArrayList<String>());
		}

		getGrantedAuthorityList().add(grantedAuthority);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceRequest [getDevices()=" + getDevices() + ", getFirstDevice()=" + getFirstDevice()
				+ ", getDeviceSearch()=" + getDeviceSearch() + ", getGrantedAuthorityList()="
				+ getGrantedAuthorityList()
				+ ", getIsMonitored()=" + getIsMonitored() + ", getRecentRequestMonitored()="
				+ getRecentRequestMonitored() + ", getTimeZone()=" + getTimeZone() + ", getDateFormat()="
				+ getDateFormat() + ", getFileName()=" + getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getUnreachableIds()=" + getUnreachableIds() + ", getIdFileType()=" + getIdFileType()
				+ ", getUploadIds()=" + getUploadIds() + ", getParamType()=" + getParamType()
				+ ", getServiceTypeEnum()=" + getServiceTypeEnum() + ", getServiceTypeEnumValue()="
				+ getServiceTypeEnumValue() + ", getUserContext()=" + getUserContext() + "]";
	}
}
