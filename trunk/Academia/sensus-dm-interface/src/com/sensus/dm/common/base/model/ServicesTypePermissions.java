package com.sensus.dm.common.base.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.device.model.ServiceTypeEnum;

/**
 * The Class ServicesElectricType.
 * 
 * @author QAT Global.
 */
public class ServicesTypePermissions
{
	/** The service type. */
	private ServiceTypeEnum serviceTypeEnum;

	/** The device type permissions. */
	private List<DeviceTypePermissions> deviceTypePermissions;

	/** The dashboard ui modules. */
	private List<String> dashboardUiModules;

	/** The schedule actions. */
	private List<ActionType> scheduleActions;

	/** The list actions. */
	private List<ActionType> listActions;

	/** The event history filter actions. */
	private List<ActionType> eventHistoryFilterActions;

	/** The granted authority. */
	private String grantedAuthority;

	/**
	 * Instantiates a new services electric type permissions.
	 */
	public ServicesTypePermissions()
	{

	}
	
	/**
	 * Instantiates a new services electric type permissions.
	 * 
	 * @param param the param
	 */
	public ServicesTypePermissions(List<DeviceTypePermissions> param)
	{
		setDeviceTypePermissions(param);
	}

	/**
	 * Instantiates a new services type permissions.
	 * 
	 * @param serviceTypeEnumParam the service type enum
	 * @param deviceTypePermissionsParam the device type permissions
	 * @param dashboardUiModulesParam the dashboard ui modules
	 * @param scheduleActionsParam the schedule actions
	 * @param listActionsParam the list actions
	 * @param eventHistoryFilterActionsParam the event history filter actions
	 */
	public ServicesTypePermissions(ServiceTypeEnum serviceTypeEnumParam,
			List<DeviceTypePermissions> deviceTypePermissionsParam,
			List<String> dashboardUiModulesParam, List<ActionType> scheduleActionsParam,
			List<ActionType> listActionsParam,
			List<ActionType> eventHistoryFilterActionsParam)
	{
		this(deviceTypePermissionsParam);
		setServiceTypeEnum(serviceTypeEnumParam);
		setDashboardUiModules(dashboardUiModulesParam);
		setScheduleActions(scheduleActionsParam);
		setListActions(listActionsParam);
		setEventHistoryFilterActions(eventHistoryFilterActionsParam);
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
	 * Gets the device type permissions.
	 * 
	 * @return the device type permissions
	 */
	public List<DeviceTypePermissions> getDeviceTypePermissions()
	{
		return deviceTypePermissions;
	}

	/**
	 * Sets the device type permissions.
	 * 
	 * @param param the new device type permissions
	 */
	public void setDeviceTypePermissions(List<DeviceTypePermissions> param)
	{
		deviceTypePermissions = param;
	}

	/**
	 * Gets the dashboard ui modules.
	 * 
	 * @return the dashboard ui modules
	 */
	public List<String> getDashboardUiModules()
	{
		return dashboardUiModules;
	}

	/**
	 * Sets the dashboard ui modules.
	 * 
	 * @param param the new dashboard ui modules
	 */
	public void setDashboardUiModules(List<String> param)
	{
		dashboardUiModules = param;
	}

	/**
	 * Gets the schedule actions.
	 * 
	 * @return the schedule actions
	 */
	public List<ActionType> getScheduleActions()
	{
		return scheduleActions;
	}

	/**
	 * Sets the schedule actions.
	 * 
	 * @param param the new schedule actions
	 */
	public void setScheduleActions(List<ActionType> param)
	{
		scheduleActions = param;
	}

	/**
	 * Gets the list actions.
	 * 
	 * @return the list actions
	 */
	public List<ActionType> getListActions()
	{
		return listActions;
	}

	/**
	 * Sets the list actions.
	 * 
	 * @param param the new list actions
	 */
	public void setListActions(List<ActionType> param)
	{
		listActions = param;
	}

	/**
	 * Gets the event history filter actions.
	 * 
	 * @return the event history filter actions
	 */
	public List<ActionType> getEventHistoryFilterActions()
	{
		return eventHistoryFilterActions;
	}

	/**
	 * Sets the event history filter actions.
	 * 
	 * @param eventHistoryFilterActionsParam the new event history filter actions
	 */
	public void setEventHistoryFilterActions(List<ActionType> eventHistoryFilterActionsParam)
	{
		eventHistoryFilterActions = eventHistoryFilterActionsParam;
	}

	/**
	 * Gets the granted authority.
	 * 
	 * @return the granted authority
	 */
	public String getGrantedAuthority()
	{
		return grantedAuthority;
	}

	/**
	 * Sets the granted authority.
	 * 
	 * @param grantedAuthority the new granted authority
	 */
	public void setGrantedAuthority(String grantedAuthority)
	{
		this.grantedAuthority = grantedAuthority;
	}

	/**
	 * Adds the device type permissions.
	 * 
	 * @param deviceTypePermission the device type permission
	 */
	public void addDeviceTypePermissions(DeviceTypePermissions deviceTypePermission)
	{
		if (getDeviceTypePermissions() == null)
		{
			setDeviceTypePermissions(new ArrayList<DeviceTypePermissions>());
		}

		getDeviceTypePermissions().add(deviceTypePermission);
	}

	/**
	 * Adds the schedule action.
	 * 
	 * @param actionType the action type
	 */
	public void addScheduleAction(ActionType actionType)
	{
		if (getScheduleActions() == null)
		{
			setScheduleActions(new ArrayList<ActionType>());
		}

		getScheduleActions().add(actionType);
	}

	/**
	 * Adds the list action.
	 * 
	 * @param actionType the action type
	 */
	public void addListAction(ActionType actionType)
	{
		if (getListActions() == null)
		{
			setListActions(new ArrayList<ActionType>());
		}

		getListActions().add(actionType);
	}

	/**
	 * Adds the event history filter action.
	 * 
	 * @param actionType the action type
	 */
	public void addEventHistoryFilterAction(ActionType actionType)
	{
		if (getEventHistoryFilterActions() == null)
		{
			setEventHistoryFilterActions(new ArrayList<ActionType>());
		}

		getEventHistoryFilterActions().add(actionType);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ServicesTypePermissions [getDeviceTypePermissions()=" + getDeviceTypePermissions()
				+ ", getDashboardUiModules()=" + getDashboardUiModules() + ", getScheduleActions()="
				+ getScheduleActions() + ", getListActions()=" + getListActions() + ", getServiceTypeEnum()="
				+ getServiceTypeEnum() + ", getEventHistoryFilterActions()=" + getEventHistoryFilterActions()
				+ ",getGrantedAuthority()=" + getGrantedAuthority() + "]";
	}
}
