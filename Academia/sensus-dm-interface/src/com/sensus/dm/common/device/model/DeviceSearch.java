package com.sensus.dm.common.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.elec.device.model.LCMSearch;
import com.sensus.dm.gas.device.model.GasMeterSearch;
import com.sensus.dm.water.device.model.WaterMeterSearch;

/**
 * The Class DeviceSearch.
 * 
 * @author QAT Brazil
 * 
 */
@SuppressWarnings("serial")
public class DeviceSearch extends BaseSearch
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The electric meter search. */
	private ElectricMeterSearch electricMeterSearch;

	/** The han device search. */
	private HanDeviceSearch hanDeviceSearch;

	/** The Lcm search. */
	private LCMSearch lcmSearch;

	/** The water meter search. */
	private WaterMeterSearch waterMeterSearch;

	/** The gas meter search. */
	private GasMeterSearch gasMeterSearch;

	/** The groups. */
	private List<Group> groups;

	/** The tags. */
	private List<Tag> tags;

	/** The device models. */
	private List<DeviceModel> deviceModels;

	/** The process id. */
	private Integer processId;

	/**
	 * Instantiates a new search meter.
	 */
	public DeviceSearch()
	{

	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param searchText the search text
	 * @param groupTypesParam the group types param
	 */
	public DeviceSearch(String searchText, List<GroupTypeEnum> groupTypesParam)
	{
		super(searchText, groupTypesParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param deviceTypesParam the device types param
	 */
	public DeviceSearch(List<DeviceTypeEnum> deviceTypesParam)
	{
		setDeviceTypes(deviceTypesParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param electricMeterSearchParam the electric meter search param
	 */
	public DeviceSearch(ElectricMeterSearch electricMeterSearchParam)
	{
		setElectricMeterSearch(electricMeterSearchParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param lcmSearchParam the lcm search param
	 */
	public DeviceSearch(LCMSearch lcmSearchParam)
	{
		setLcmSearch(lcmSearchParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param hanDeviceSearchParam the han device search param
	 */
	public DeviceSearch(HanDeviceSearch hanDeviceSearchParam)
	{
		setHanDeviceSearch(hanDeviceSearchParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param deviceTypeParam the device type param
	 * @param hanDeviceSearchParam the han device search param
	 */
	public DeviceSearch(DeviceTypeEnum deviceTypeParam, HanDeviceSearch hanDeviceSearchParam)
	{
		addDeviceType(deviceTypeParam);
		setHanDeviceSearch(hanDeviceSearchParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param waterMeterSearchParam the water meter search param
	 */
	public DeviceSearch(WaterMeterSearch waterMeterSearchParam)
	{
		setWaterMeterSearch(waterMeterSearchParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param gasMeterSearchParam the gas meter search param
	 */
	public DeviceSearch(GasMeterSearch gasMeterSearchParam)
	{
		setGasMeterSearch(gasMeterSearchParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param deviceTypeParam the device type param
	 */
	public DeviceSearch(DeviceTypeEnum deviceTypeParam)
	{
		addDeviceType(deviceTypeParam);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param deviceTypeParam the device type param
	 * @param deviceModelList the device model list
	 */
	public DeviceSearch(DeviceTypeEnum deviceTypeParam, List<DeviceModel> deviceModelList)
	{
		addDeviceType(deviceTypeParam);
		setDeviceModels(deviceModelList);
	}

	/**
	 * Instantiates a new device search.
	 * 
	 * @param containDevicesParam the contain devices param
	 */
	public DeviceSearch(Boolean containDevicesParam)
	{
		super(containDevicesParam);
	}

	/**
	 * Gets the electric meter search.
	 * 
	 * @return the electric meter search
	 */
	public ElectricMeterSearch getElectricMeterSearch()
	{
		return electricMeterSearch;
	}

	/**
	 * Sets the electric meter search.
	 * 
	 * @param electricMeterSearch the new electric meter search
	 */
	public void setElectricMeterSearch(ElectricMeterSearch electricMeterSearch)
	{
		this.electricMeterSearch = electricMeterSearch;
	}

	/**
	 * Gets the han device search.
	 * 
	 * @return the han device search
	 */
	public HanDeviceSearch getHanDeviceSearch()
	{
		return hanDeviceSearch;
	}

	/**
	 * Sets the han device search.
	 * 
	 * @param hanDeviceSearch the new han device search
	 */
	public void setHanDeviceSearch(HanDeviceSearch hanDeviceSearch)
	{
		this.hanDeviceSearch = hanDeviceSearch;
	}

	/**
	 * Gets the lcm search.
	 * 
	 * @return the lcm search
	 */
	public LCMSearch getLcmSearch()
	{
		return lcmSearch;
	}

	/**
	 * Sets the lcm search.
	 * 
	 * @param lcmSearch the new lcm search
	 */
	public void setLcmSearch(LCMSearch lcmSearch)
	{
		this.lcmSearch = lcmSearch;
	}

	/**
	 * Gets the water meter search.
	 * 
	 * @return the water meter search
	 */
	public WaterMeterSearch getWaterMeterSearch()
	{
		return waterMeterSearch;
	}

	/**
	 * Sets the water meter search.
	 * 
	 * @param waterMeterSearch the new water meter search
	 */
	public void setWaterMeterSearch(WaterMeterSearch waterMeterSearch)
	{
		this.waterMeterSearch = waterMeterSearch;
	}

	/**
	 * Gets the gas meter search.
	 * 
	 * @return the gas meter search
	 */
	public GasMeterSearch getGasMeterSearch()
	{
		return gasMeterSearch;
	}

	/**
	 * Sets the gas meter search.
	 * 
	 * @param gasMeterSearch the new gas meter search
	 */
	public void setGasMeterSearch(GasMeterSearch gasMeterSearch)
	{
		this.gasMeterSearch = gasMeterSearch;
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
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the device model.
	 * 
	 * @return the device model
	 */
	public List<DeviceModel> getDeviceModels()
	{
		return deviceModels;
	}

	/**
	 * Sets the device model.
	 * 
	 * @param deviceModels the new device model
	 */
	public void setDeviceModels(List<DeviceModel> deviceModels)
	{
		this.deviceModels = deviceModels;
	}

	/**
	 * Adds the device model.
	 * 
	 * @param deviceModelParam the device model param
	 */
	public void addDeviceModels(DeviceModel deviceModelParam)
	{
		if (getDeviceModels() == null)
		{
			setDeviceModels(new ArrayList<DeviceModel>());
		}

		getDeviceModels().add(deviceModelParam);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.BaseSearch#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceSearch [getElectricMeterSearch()=" + getElectricMeterSearch() + ", getHanDeviceSearch()="
				+ getHanDeviceSearch() + ", getLcmSearch()=" + getLcmSearch() + ", getWaterMeterSearch()="
				+ getWaterMeterSearch() + ", getGasMeterSearch()=" + getGasMeterSearch()
				+ ", getGroups()=" + getGroups()
				+ ", getTags()=" + getTags() + ", getFirstTag()=" + getFirstTag() + ", getFirstGroup()="
				+ getFirstGroup() + ", getProcessId()=" + getProcessId() + ", getDeviceModels()=" + getDeviceModels()
				+ "]";
	}
}
