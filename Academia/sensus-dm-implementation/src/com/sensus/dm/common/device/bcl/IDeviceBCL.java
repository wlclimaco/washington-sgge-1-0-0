package com.sensus.dm.common.device.bcl;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;

/**
 * The Interface IElectricMeterBCL.
 * 
 * @author QAT Global.
 */
public interface IDeviceBCL
{

	/**
	 * Fetch all devices type count.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<DeviceTypeCount> fetchAllDevicesTypeCount(DeviceRequest deviceRequest);

	/**
	 * Fetch all by premise id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchAllByPremiseId(DeviceRequest deviceRequest);

	/**
	 * Fetch services by device type.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<ServicesPermissions> fetchServicesByDeviceType(DeviceRequest deviceRequest);

	/**
	 * Fetch device by id import.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchDeviceByIdImport(DeviceRequest deviceRequest);

	/**
	 * Fetch alarm history.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Alarm> fetchAlarmHistory(DeviceRequest deviceRequest);

	/**
	 * Fetch devices bounds to map.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesBoundsToMap(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch devices to map.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<GeocodeDeviceInfo> fetchDevicesToMap(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch all device type descriptions.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<DeviceModel> fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest);

	/**
	 * Fetch quarantine count.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchQuarantineCount(DeviceRequest deviceRequest);
	
	/**
	 * Fetch alarms types count.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<AlarmsTypesCount> fetchAlarmsTypesCount(DeviceRequest deviceRequest);
}