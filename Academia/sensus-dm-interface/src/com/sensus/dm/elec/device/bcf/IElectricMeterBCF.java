package com.sensus.dm.elec.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;

/**
 * The Interface IElectricMeterBCF.
 * 
 * @author QAT Global.
 */
public interface IElectricMeterBCF
{

	/**
	 * Fetch all electric meter.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse fetchAllElectricMeters(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch all lcm.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse fetchAllLCMs(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch all han devices.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch device by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchDeviceById(DeviceRequest deviceRequest);

	/**
	 * Generate peak demand csv.
	 * 
	 * @param request the request
	 * @return the inquiry peak demand response
	 */
	InquiryPeakDemandResponse generatePeakDemandCSV(DeviceReadingRequest request);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryMeterRequest the inquiry meter request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryMeterRequest);

	/**
	 * Fetch lifecycle states.
	 * 
	 * @param request the request
	 * @return the device response
	 */
	DeviceResponse fetchLifecycleStates(DeviceRequest request);

	/**
	 * Fetch all han devices by meter.
	 * 
	 * @param deviceRequest the device request
	 * @return the han device response
	 */
	DeviceResponse fetchAllHanDevicesByMeter(DeviceRequest deviceRequest);

	/**
	 * Fetch all manufacture by device sub type.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest);

	/**
	 * Fetch all model by device sub type.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAllModelByDeviceSubType(DeviceRequest deviceRequest);

	/**
	 * Fetch all connection state.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchAllConnectionState(DeviceRequest deviceRequest);

	/**
	 * Fetch all peak demand.
	 * 
	 * @param request the request
	 * @return the inquiry peak demand response
	 */
	InquiryPeakDemandResponse fetchAllPeakDemand(DeviceReadingRequest request);

	/**
	 * Fetch lcm relay by device id.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchLCMRelaysByDevice(DeviceRequest deviceRequest);
}
