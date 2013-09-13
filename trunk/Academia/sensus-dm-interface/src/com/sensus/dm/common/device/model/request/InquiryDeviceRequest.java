package com.sensus.dm.common.device.model.request;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.GeocodeLatLongTrunc;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryDeviceRequest.
 * 
 * @author QAT Global
 */
public class InquiryDeviceRequest extends InquiryPaginationRequest
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The DeviceSearch. */
	private DeviceSearch deviceSearch;

	/** The groups. */
	private List<Group> groups;

	/** The tags. */
	private List<Tag> tags;

	/** The id file type value. */
	private String idFileTypeValue;

	/** The devices. */
	private List<Device> devices;

	/** The list column. */
	private List<DeviceColumnEnum> listColumn;

	/** The device type enum. */
	private DeviceTypeEnum deviceType;

	/** The geo code trunc. */
	private Integer geoCodeTrunc;

	/** The geocode lat long truncs. */
	private List<GeocodeLatLongTrunc> geocodeLatLongTruncs;

	/** The bottom left lat. */
	private Double bottomLeftLat;

	/** The bottom left lon. */
	private Double bottomLeftLon;

	/** The top right lat. */
	private Double topRightLat;

	/** The top right lon. */
	private Double topRightLon;

	/**
	 * Instantiates a new inquiry device request.
	 */
	public InquiryDeviceRequest()
	{
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param deviceTypeEnumParam the device type param
	 */
	public InquiryDeviceRequest(DeviceTypeEnum deviceTypeParam)
	{
		setDeviceType(deviceTypeParam);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param deviceTypeParam the device type param
	 * @param tenantParam the tenant param
	 */
	public InquiryDeviceRequest(DeviceTypeEnum deviceTypeParam, DMTenant tenantParam)
	{
		setDeviceType(deviceTypeParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param deviceParam the device param
	 * @param tagParam the tag param
	 * @param userContextParam the user context param
	 */
	public InquiryDeviceRequest(Device deviceParam, Tag tagParam, UserContext userContextParam)
	{
		addDevice(deviceParam);
		addTag(tagParam);
		setUserContext(userContextParam);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param device the device
	 * @param group the group
	 * @param userContext the user context
	 */
	public InquiryDeviceRequest(Device device, Group group, UserContext userContext)
	{
		addDevice(device);
		addGroup(group);
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryDeviceRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param selectionPaginationIds the selection pagination ids
	 * @param paginationAllSelected the pagination all selected
	 * @param userContext the user context
	 * @param group the group
	 * @param sortExpression the sort expression
	 */
	public InquiryDeviceRequest(List<BigInteger> selectionPaginationIds, Boolean paginationAllSelected,
			UserContext userContext, Group group, SortExpression sortExpression)
	{
		this(userContext);
		setSelectionPaginationIds(selectionPaginationIds);
		setPaginationAllSelected(paginationAllSelected);
		addGroup(group);
		getSortExpressions().add(sortExpression);

	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param selectionPaginationIds the selection pagination ids
	 * @param paginationAllSelected the pagination all selected
	 * @param userContext the user context
	 * @param group the group
	 * @param sortExpression the sort expression
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryDeviceRequest(List<BigInteger> selectionPaginationIds, Boolean paginationAllSelected,
			UserContext userContext, Group group, SortExpression sortExpression, ServiceTypeEnum serviceTypeEnum,
			DMTenant tenant)
	{
		this(userContext);
		setSelectionPaginationIds(selectionPaginationIds);
		setPaginationAllSelected(paginationAllSelected);
		addGroup(group);
		getSortExpressions().add(sortExpression);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);

	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param selectionPaginationIds the selection pagination ids
	 * @param paginationAllSelected the pagination all selected
	 * @param userContext the user context
	 * @param sortExpression the sort expression
	 * @param search the search
	 */
	public InquiryDeviceRequest(
			List<BigInteger> selectionPaginationIds, Boolean paginationAllSelected, UserContext userContext,
			SortExpression sortExpression, DeviceSearch search, DMTenant tenantParam)
	{
		this(selectionPaginationIds, paginationAllSelected, userContext, null, sortExpression);
		setDeviceSearch(search);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param paginationAllSelected the pagination all selected
	 * @param userContext the user context
	 * @param sortExpression the sort expression
	 * @param devicesParam the devices param
	 * @param serviceTypeEnumParam the service type enum param
	 */
	public InquiryDeviceRequest(Boolean paginationAllSelected,
			UserContext userContext, SortExpression sortExpression, List<Device> devicesParam,
			ServiceTypeEnum serviceTypeEnumParam)
	{
		this(null, paginationAllSelected, userContext, null, sortExpression);
		setDevices(devicesParam);
		setServiceTypeEnum(serviceTypeEnumParam);
	}

	/**
	 * Instantiates a new inquiry device request.
	 * 
	 * @param paginationAllSelected the pagination all selected
	 * @param userContext the user context
	 * @param sortExpression the sort expression
	 * @param devicesParam the devices param
	 * @param serviceTypeEnumParam the service type enum param
	 * @param tenant the tenant
	 */
	public InquiryDeviceRequest(Boolean paginationAllSelected,
			UserContext userContext, SortExpression sortExpression, List<Device> devicesParam,
			ServiceTypeEnum serviceTypeEnumParam, DMTenant tenant)
	{
		this(null, paginationAllSelected, userContext, null, sortExpression);
		setDevices(devicesParam);
		setServiceTypeEnum(serviceTypeEnumParam);
		setTenant(tenant);
	}

	/**
	 * Gets the device type value.
	 * 
	 * @return the device type value
	 */
	public Integer getDeviceTypeValue()
	{

		if (getDeviceType() != null)
		{
			return getDeviceType().getValue();
		}

		return null;
	}

	/**
	 * Sets the device type value.
	 * 
	 * @param deviceTypeValue the new device type value
	 */
	public void setDeviceTypeValue(Integer deviceTypeValue)
	{
		setDeviceType(DeviceTypeEnum.enumForValue(deviceTypeValue));
	}

	/**
	 * Gets the id file type value.
	 * 
	 * @return the id file type value
	 */
	public String getIdFileTypeValue()
	{
		return idFileTypeValue;
	}

	/**
	 * Sets the id file type value.
	 * 
	 * @param idFileTypeValue the new id file type value
	 */
	public void setIdFileTypeValue(String idFileTypeValue)
	{
		this.idFileTypeValue = idFileTypeValue;
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
	 * Gets the first tag.
	 * 
	 * @return the first tag
	 */
	public Tag getFirstTag()
	{
		if (getTags() != null && !getTags().isEmpty())
		{
			return getTags().get(FIRST);
		}

		return null;
	}

	/**
	 * Gets the first group.
	 * 
	 * @return the first group
	 */
	public Group getFirstGroup()
	{
		if (getGroups() != null && !getGroups().isEmpty())
		{
			return getGroups().get(FIRST);
		}

		return null;
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
	 * Adds the device.
	 * 
	 * @param deviceObject the device object
	 */
	public void addDevice(Device deviceObject)
	{
		if (getDevices() == null)
		{
			setDevices(new ArrayList<Device>());
		}

		getDevices().add(deviceObject);
	}

	/**
	 * Adds the tag.
	 * 
	 * @param tag the tag
	 */
	public void addTag(Tag tag)
	{
		if (getTags() == null)
		{
			setTags(new ArrayList<Tag>());
		}

		getTags().add(tag);
	}

	/**
	 * Adds the group.
	 * 
	 * @param group the group
	 */
	public void addGroup(Group group)
	{
		if (getGroups() == null)
		{
			setGroups(new ArrayList<Group>());
		}

		getGroups().add(group);
	}

	/**
	 * Gets the list column.
	 * 
	 * @return the list column
	 */
	public List<DeviceColumnEnum> getListColumn()
	{
		return listColumn;
	}

	/**
	 * Sets the list column.
	 * 
	 * @param listColumn the new list column
	 */
	public void setListColumn(List<DeviceColumnEnum> listColumn)
	{
		this.listColumn = listColumn;
	}

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<Group> getGroups()
	{
		return groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups the new groups
	 */
	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the new tags
	 */
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
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
	 * Gets the geo code trunc.
	 * 
	 * @return the geo code trunc
	 */
	public Integer getGeoCodeTrunc()
	{
		return geoCodeTrunc;
	}

	/**
	 * Sets the geo code trunc.
	 * 
	 * @param geoCodeTrunc the new geo code trunc
	 */
	public void setGeoCodeTrunc(Integer geoCodeTrunc)
	{
		this.geoCodeTrunc = geoCodeTrunc;
	}

	/**
	 * Gets the geocode lat long truncs.
	 * 
	 * @return the geocode lat long truncs
	 */
	public List<GeocodeLatLongTrunc> getGeocodeLatLongTruncs()
	{
		return geocodeLatLongTruncs;
	}

	/**
	 * Sets the geocode lat long truncs.
	 * 
	 * @param geocodeLatLongTruncs the new geocode lat long truncs
	 */
	public void setGeocodeLatLongTruncs(List<GeocodeLatLongTrunc> geocodeLatLongTruncs)
	{
		this.geocodeLatLongTruncs = geocodeLatLongTruncs;
	}

	/**
	 * Gets the first geocode lat long truncs.
	 * 
	 * @return the first geocode lat long truncs
	 */
	public GeocodeLatLongTrunc getFirstGeocodeLatLongTruncs()
	{
		if (getGeocodeLatLongTruncs() != null && !getGeocodeLatLongTruncs().isEmpty())
		{
			return getGeocodeLatLongTruncs().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the geocode lat long truncs.
	 * 
	 * @param geocodeLatLongTrunc the geocode lat long trunc
	 */
	public void addGeocodeLatLongTruncs(GeocodeLatLongTrunc geocodeLatLongTrunc)
	{
		if (getGeocodeLatLongTruncs() == null)
		{
			setGeocodeLatLongTruncs(new ArrayList<GeocodeLatLongTrunc>());
		}

		getGeocodeLatLongTruncs().add(geocodeLatLongTrunc);
	}

	/**
	 * Gets the bottom left lat.
	 * 
	 * @return the bottom left lat
	 */
	public Double getBottomLeftLat()
	{
		return bottomLeftLat;
	}

	/**
	 * Sets the bottom left lat.
	 * 
	 * @param bottomLeftLat the new bottom left lat
	 */
	public void setBottomLeftLat(Double bottomLeftLat)
	{
		this.bottomLeftLat = bottomLeftLat;
	}

	/**
	 * Gets the bottom left lon.
	 * 
	 * @return the bottom left lon
	 */
	public Double getBottomLeftLon()
	{
		return bottomLeftLon;
	}

	/**
	 * Sets the bottom left lon.
	 * 
	 * @param bottomLeftLon the new bottom left lon
	 */
	public void setBottomLeftLon(Double bottomLeftLon)
	{
		this.bottomLeftLon = bottomLeftLon;
	}

	/**
	 * Gets the top right lat.
	 * 
	 * @return the top right lat
	 */
	public Double getTopRightLat()
	{
		return topRightLat;
	}

	/**
	 * Sets the top right lat.
	 * 
	 * @param topRightLat the new top right lat
	 */
	public void setTopRightLat(Double topRightLat)
	{
		this.topRightLat = topRightLat;
	}

	/**
	 * Gets the top right lon.
	 * 
	 * @return the top right lon
	 */
	public Double getTopRightLon()
	{
		return topRightLon;
	}

	/**
	 * Sets the top right lon.
	 * 
	 * @param topRightLon the new top right lon
	 */
	public void setTopRightLon(Double topRightLon)
	{
		this.topRightLon = topRightLon;
	}

	/**
	 * Gets the device type.
	 * 
	 * @return the device type
	 */
	public DeviceTypeEnum getDeviceType()
	{
		return deviceType;
	}

	/**
	 * Sets the device type.
	 * 
	 * @param deviceType the new device type
	 */
	public void setDeviceType(DeviceTypeEnum deviceType)
	{
		this.deviceType = deviceType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryDeviceRequest [getDeviceType()=" + getDeviceType() + ", getDeviceTypeValue()="
				+ getDeviceTypeValue() + ", getIdFileTypeValue()=" + getIdFileTypeValue() + ", getDevices()="
				+ getDevices() + ", getFirstDevice()=" + getFirstDevice() + ", getFirstTag()=" + getFirstTag()
				+ ", getFirstGroup()=" + getFirstGroup() + ", getListColumn()=" + getListColumn() + ", getGroups()="
				+ getGroups() + ", getTags()=" + getTags() + ", getDeviceSearch()=" + getDeviceSearch()
				+ ", getGeoCodeTrunc()=" + getGeoCodeTrunc() + ", getGeocodeLatLongTruncs()="
				+ getGeocodeLatLongTruncs() + ", getFirstGeocodeLatLongTruncs()=" + getFirstGeocodeLatLongTruncs()
				+ ", getBottomLeftLat()=" + getBottomLeftLat() + ", getBottomLeftLon()=" + getBottomLeftLon()
				+ ", getTopRightLat()=" + getTopRightLat() + ", getTopRightLon()=" + getTopRightLon() + ", toString()="
				+ super.toString() + "]";
	}

}
