package com.sensus.dm.common.device.bcl.impl;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.base.model.DefaultActionsPermissions;
import com.sensus.dm.common.base.model.DeviceTypeModelPermissions;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.dac.IDeviceDAC;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;

/**
 * The Class ElectricMeterBCLImpl.
 * 
 * @author QAT Global.
 * 
 */
public class DeviceBCLImpl implements IDeviceBCL
{

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device dac. */
	private IDeviceDAC deviceDAC; // injected by Spring through setter

	/** The services parameters. */
	private ServicesPermissions services; // injected by Spring through setter

	/**
	 * Gets the device dac.
	 * 
	 * @return the device dac
	 */
	public IDeviceDAC getDeviceDAC()
	{
		return deviceDAC;
	}

	/**
	 * Sets the device dac.
	 * 
	 * @param deviceDAC the new device dac
	 */
	public void setDeviceDAC(IDeviceDAC deviceDAC)
	{
		this.deviceDAC = deviceDAC;
	}

	/**
	 * Gets the services.
	 * 
	 * @return the services
	 */
	public ServicesPermissions getServices()
	{
		return services;
	}

	/**
	 * Sets the services.
	 * 
	 * @param param the new services
	 */
	public void setServices(ServicesPermissions param)
	{
		services = param;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchAllByPremiseId(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchAllByPremiseId(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<DeviceTypeCount> fetchAllDevicesTypeCount(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchAllDevicesTypeCount(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchServicesByDeviceType(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<ServicesPermissions> fetchServicesByDeviceType(DeviceRequest deviceRequest)
	{
		InternalResultsResponse<ServicesPermissions> response =
				new InternalResultsResponse<ServicesPermissions>();

		ServicesPermissions allServicePermissions = getServices();

		if (ValidationUtil.isNull(allServicePermissions))
		{
			return response;
		}

		ServicesPermissions applicableServicePermissions = new ServicesPermissions();

		for (ServicesTypePermissions servicesTypePermissions : allServicePermissions.getServicesTypePermissions())
		{
			if (!ValidationUtil.isNullOrEmpty(servicesTypePermissions.getDeviceTypePermissions())
					&& deviceRequest.getGrantedAuthorityList().contains(servicesTypePermissions.getGrantedAuthority()))
			{
				ServicesTypePermissions servicesTypePermission = new ServicesTypePermissions();

				servicesTypePermission.setServiceTypeEnum(servicesTypePermissions.getServiceTypeEnum());
				servicesTypePermission.setDashboardUiModules(servicesTypePermissions.getDashboardUiModules());

				// permissions are granted for only those who are allowed access to the listed action type for device
				// types.
				setDeviceTypeByGrantedAuthority(deviceRequest, servicesTypePermissions, servicesTypePermission);

				// permissions are granted for only those who are allowed access to the listed action types for the
				// scheduler.
				setActionTypeScheduleByGrantedAuthority(deviceRequest, servicesTypePermissions,
						servicesTypePermission);
				// permissions are granted for only those who are allowed access to the listed action types on the
				// Action page.
				setActionTypeListByGrantedAuthority(deviceRequest, servicesTypePermissions, servicesTypePermission);

				// permissions are granted for only those who are allowed access to the listed action types for event
				// history filters.
				setEventHistoryFilterActionsByGrantedAuthority(deviceRequest, servicesTypePermissions,
						servicesTypePermission);

				applicableServicePermissions.addServicesTypePermissions(servicesTypePermission);
			}
		}
		if (!ValidationUtil.isNullOrEmpty(applicableServicePermissions.getServicesTypePermissions()))
		{
			response.addResult(applicableServicePermissions);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Device> fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchDeviceByIdImport(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAlarmHistory(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Alarm> fetchAlarmHistory(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchAlarmHistory(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesBoundsToMap(InquiryDeviceRequest request)
	{
		return getDeviceDAC().fetchDevicesBoundsToMap(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesToMap(InquiryDeviceRequest request)
	{
		return getDeviceDAC().fetchDevicesToMap(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllDeviceTypeDescriptions(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<DeviceModel> fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchAllDeviceTypeDescriptions(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchQuarantineCount(deviceRequest);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<AlarmsTypesCount> fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		return getDeviceDAC().fetchAlarmsTypesCount(deviceRequest);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private:

	/**
	 * Sets the event history filter actions by granted authority.
	 * 
	 * @param deviceRequest the device request
	 * @param servicesTypePermissions the services type permissions
	 * @param servicesTypePermission the services type permission
	 */
	private void setEventHistoryFilterActionsByGrantedAuthority(DeviceRequest deviceRequest,
			ServicesTypePermissions servicesTypePermissions, ServicesTypePermissions servicesTypePermission)
	{
		for (ActionType actionType : servicesTypePermissions.getEventHistoryFilterActions())
		{
			for (String grantedAuthority : actionType.getGrantedAuthorityList())
			{
				if (deviceRequest.getGrantedAuthorityList().contains(grantedAuthority))
				{
					servicesTypePermission.addEventHistoryFilterAction(actionType);
					break;
				}
			}
		}
	}

	/**
	 * Sets the action type list by granted authority.
	 * 
	 * @param deviceRequest the device request
	 * @param servicesTypePermissions the services type permissions
	 * @param servicesTypePermission the services type permission
	 */
	private void setActionTypeListByGrantedAuthority(DeviceRequest deviceRequest,
			ServicesTypePermissions servicesTypePermissions, ServicesTypePermissions servicesTypePermission)
	{
		for (ActionType actionType : servicesTypePermissions.getListActions())
		{
			for (String grantedAuthority : actionType.getGrantedAuthorityList())
			{
				if (deviceRequest.getGrantedAuthorityList().contains(grantedAuthority))
				{
					servicesTypePermission.addListAction(actionType);
					break;
				}
			}
		}
	}

	/**
	 * Sets the action type schedule by granted authority.
	 * 
	 * @param deviceRequest the device request
	 * @param servicesTypePermissions the services type permissions
	 * @param servicesTypePermission the services type permission
	 */
	private void setActionTypeScheduleByGrantedAuthority(DeviceRequest deviceRequest,
			ServicesTypePermissions servicesTypePermissions, ServicesTypePermissions servicesTypePermission)
	{
		for (ActionType actionType : servicesTypePermissions.getScheduleActions())
		{
			for (String grantedAuthority : actionType.getGrantedAuthorityList())
			{
				if (deviceRequest.getGrantedAuthorityList().contains(grantedAuthority))
				{
					servicesTypePermission.addScheduleAction(actionType);
					break;
				}
			}
		}
	}

	/**
	 * Sets the device type by granted authority.
	 * 
	 * @param deviceRequest the device request
	 * @param servicesTypePermissions the services type permissions
	 * @param servicesTypePermission the services type permission
	 */
	private void setDeviceTypeByGrantedAuthority(DeviceRequest deviceRequest,
			ServicesTypePermissions servicesTypePermissions, ServicesTypePermissions servicesTypePermission)
	{
		for (DeviceTypePermissions deviceTypePermissions : servicesTypePermissions.getDeviceTypePermissions())
		{
			DeviceTypePermissions deviceTypePermission = new DeviceTypePermissions();
			deviceTypePermission.setDeviceType(deviceTypePermissions.getDeviceType());

			// permissions are granted for only those who are allowed access to the listed action types for default
			// actions.
			setDefaultActionsByGrantedAuthority(deviceRequest, deviceTypePermissions, deviceTypePermission);

			// permissions are granted for only those who are allowed access to the listed action types for device
			// types.
			setDeviceTypePermissionsByGrantedAuthority(deviceRequest, deviceTypePermissions, deviceTypePermission);

			servicesTypePermission.addDeviceTypePermissions(deviceTypePermission);

			deviceTypePermission.setAlarms(deviceTypePermissions.getAlarms());
		}
	}

	/**
	 * Sets the default actions by granted authority.
	 * 
	 * @param deviceRequest the device request
	 * @param deviceTypePermissions the device type permissions
	 * @param deviceTypePermission the device type permission
	 */
	private void setDefaultActionsByGrantedAuthority(DeviceRequest deviceRequest,
			DeviceTypePermissions deviceTypePermissions, DeviceTypePermissions deviceTypePermission)
	{
		DefaultActionsPermissions defaultActionsPermissionList = new DefaultActionsPermissions();

		for (ActionType actionType : deviceTypePermissions.getDefaultActions().getActions())
		{
			for (String grantedAuthority : actionType.getGrantedAuthorityList())
			{
				if (deviceRequest.getGrantedAuthorityList().contains(grantedAuthority))
				{
					defaultActionsPermissionList.addAction(actionType);
					break;
				}
			}
		}

		deviceTypePermission.setDefaultActions(defaultActionsPermissionList);
	}

	/**
	 * Sets the device type permissions by granted authority.
	 * 
	 * @param deviceRequest the device request
	 * @param deviceTypePermissions the device type permissions
	 * @param deviceTypePermission the device type permission
	 */
	private void setDeviceTypePermissionsByGrantedAuthority(DeviceRequest deviceRequest,
			DeviceTypePermissions deviceTypePermissions, DeviceTypePermissions deviceTypePermission)
	{
		for (DeviceTypeModelPermissions deviceTypeModelPermissions : deviceTypePermissions
				.getDeviceTypeModels())
		{
			DeviceTypeModelPermissions deviceTypeModelPermission = new DeviceTypeModelPermissions();
			deviceTypeModelPermission.setName(deviceTypeModelPermissions.getName());
			deviceTypeModelPermission.setUiModules(deviceTypeModelPermissions.getUiModules());

			for (ActionType actionType : deviceTypeModelPermissions.getActions())
			{
				for (String grantedAuthority : actionType.getGrantedAuthorityList())
				{
					if (deviceRequest.getGrantedAuthorityList().contains(grantedAuthority))
					{
						deviceTypeModelPermission.addActions(actionType);
						break;
					}
				}
			}

			deviceTypePermission.addDeviceTypePermissions(deviceTypeModelPermission);
		}
	}
}
