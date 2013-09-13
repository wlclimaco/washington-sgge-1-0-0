package com.sensus.dm.common.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryGeocodeDeviceInfoResponse;

/**
 * The Interface IElectricMeterBCF.
 * 
 * @author QAT Global.
 */
public interface IDeviceBCF
{

	/**
	 * Fetch all devices type count.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAllDevicesTypeCount(DeviceRequest deviceRequest);

	/**
	 * Fetch all by premise id.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAllByPremiseId(DeviceRequest deviceRequest);

	/**
	 * Fetch services by device type.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchServicesByDeviceType(DeviceRequest deviceRequest);

	/**
	 * Fetch device by id import.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchDeviceByIdImport(DeviceRequest deviceRequest);

	/**
	 * Fetch alarm history.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAlarmHistory(DeviceRequest deviceRequest);

	/**
	 * Fetch devices bounds to map.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry geocode device info response
	 */
	InquiryGeocodeDeviceInfoResponse fetchDevicesBoundsToMap(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch devices to map.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry geocode device info response
	 */
	InquiryGeocodeDeviceInfoResponse fetchDevicesToMap(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch all device type descriptions.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest);

	/**
	 * Fetch quarantine count.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchQuarantineCount(DeviceRequest deviceRequest);

	/**
	 * Fetch alarms types count.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAlarmsTypesCount(DeviceRequest deviceRequest);
}
