package com.sensus.dm.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DeviceTypeModelPermissions;
import com.sensus.dm.common.base.model.DeviceTypePermissions;
import com.sensus.dm.common.base.model.ServicesTypePermissions;
import com.sensus.dm.common.base.model.UiModulesPermissions;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.controller.util.DMUtil;

/**
 * The Class BaseModuleController.
 */
public class BaseModuleController extends BaseViewController
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant COMMON_DEVICE_TYPE_MODEL_NAME. */
	private static final String COMMON_DEVICE_TYPE_MODEL_NAME = "COMMON";

	/** The device bcf. */
	private IDeviceBCF deviceBCF;

	/**
	 * Gets the device bcf.
	 * 
	 * @return the device bcf
	 */
	public IDeviceBCF getDeviceBCF()
	{
		return deviceBCF;
	}

	/**
	 * Sets the device bcf.
	 * 
	 * @param deviceBCF the new device bcf
	 */
	@Resource
	public void setDeviceBCF(IDeviceBCF deviceBCF)
	{
		this.deviceBCF = deviceBCF;
	}

	/**
	 * Fetch service type permissions by service type.
	 * 
	 * @param serviceType the service type
	 * @return the list
	 */
	public List<ServicesTypePermissions> fetchServiceTypePermissionsByServiceType(ServiceTypeEnum serviceType)
	{
		// Fetch Services
		DeviceRequest deviceRequest = new DeviceRequest();
		deviceRequest.setGrantedAuthorityList(getUserSettings().getRoles());
		DeviceResponse deviceResponse = getDeviceBCF().fetchServicesByDeviceType(deviceRequest);

		// Get service permission by service type
		return (List<ServicesTypePermissions>)DMUtil
				.getServiceTypePermissions(deviceResponse.getServicesPermissions().getServicesTypePermissions(),
						serviceType);
	}

	/**
	 * Gets the dashboard ui modules by service.
	 * 
	 * @param serviceType the service type
	 * @return the dashboard ui modules by service
	 */
	public List<String> getDashboardUiModulesByService(ServiceTypeEnum serviceType)
	{
		// Get service permission by service type and return Dashboard UI Modules
		return fetchServiceTypePermissionsByServiceType(serviceType).get(ZERO).getDashboardUiModules();
	}

	/**
	 * Gets the detail ui modules.
	 * 
	 * @param serviceType the service type
	 * @param deviceTypeEnum the device type enum
	 * @param deviceSubType the device sub type
	 * @return the detail ui modules
	 */
	public UiModulesPermissions getDetailUiModules(ServiceTypeEnum serviceType, DeviceTypeEnum deviceTypeEnum,
			String deviceSubType)
	{

		// Fetch Services and get service permission by service type
		List<ServicesTypePermissions> listServicesTypePermissions =
				fetchServiceTypePermissionsByServiceType(serviceType);

		// Get device type permissions by device type
		List<DeviceTypePermissions> listDeviceTypePermissions = (List<DeviceTypePermissions>)
				DMUtil.getDeviceTypePermissions(listServicesTypePermissions.get(ZERO).getDeviceTypePermissions(),
						deviceTypeEnum);

		DeviceTypePermissions deviceTypePermission = listDeviceTypePermissions.get(ZERO);

		if (ValidationUtil.isNull(deviceSubType))
		{
			deviceSubType = "";
		}

		// Get device type model permissions by device sub type (FLEXNET_LCM, LCM)
		List<DeviceTypeModelPermissions> lisDeviceTypeModelPermissions = (List<DeviceTypeModelPermissions>)
				DMUtil.getDeviceTypeModels(deviceTypePermission.getDeviceTypeModels(), COMMON_DEVICE_TYPE_MODEL_NAME,
						deviceSubType);

		if (lisDeviceTypeModelPermissions.size() > 1)
		{

			UiModulesPermissions uiModules = new UiModulesPermissions();
			uiModules.setTabs(new ArrayList<String>());
			uiModules.setSummaryDatas(new ArrayList<String>());
			uiModules.setContents(new ArrayList<String>());

			for (DeviceTypeModelPermissions deviceTypeModelPermissions : lisDeviceTypeModelPermissions)
			{
				if (!ValidationUtil.isNull(deviceTypeModelPermissions.getUiModules()))
				{
					if (!ValidationUtil.isNull(deviceTypeModelPermissions.getUiModules().getTabs()))
					{
						uiModules.getTabs().addAll(deviceTypeModelPermissions.getUiModules().getTabs());
					}

					if (!ValidationUtil.isNull(deviceTypeModelPermissions.getUiModules().getSummaryDatas()))
					{
						uiModules.getSummaryDatas().addAll(deviceTypeModelPermissions.getUiModules().getSummaryDatas());
					}

					if (!ValidationUtil.isNull(deviceTypeModelPermissions.getUiModules().getContents()))
					{
						uiModules.getContents().addAll(deviceTypeModelPermissions.getUiModules().getContents());
					}
				}
			}

			return uiModules;
		}

		return lisDeviceTypeModelPermissions.get(ZERO).getUiModules();
	}

	/**
	 * Fill modules response.
	 * 
	 * @param modules the modules
	 * @return the map
	 */
	public Map<String, Boolean> fillModulesResponse(List<String> modules)
	{

		Map<String, Boolean> responseMap = new HashMap<String, Boolean>();

		for (String module : modules)
		{
			responseMap.put(module, true);
		}

		return responseMap;
	}
}