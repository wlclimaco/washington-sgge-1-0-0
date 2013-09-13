package com.sensus.dm.water.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * The Interface IWaterMeterBCF.
 */
public interface IWaterMeterBCF
{

	/**
	 * Fetch all water meters.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch water meter by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchWaterMeterById(DeviceRequest deviceRequest);

	/**
	 * Generate devices csv.
	 * 
	 * @param inquiryDeviceRequest the inquiry water meter request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch communication.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchCommunication(DeviceRequest deviceRequest);

	/**
	 * Fetch leak report.
	 * 
	 * @param request the request
	 * @return the water meter response
	 */
	WaterMeterResponse fetchLeakReport(DeviceRequest request);

	/**
	 * Generate file csv leak report.
	 * 
	 * @param request the request
	 * @return the water meter response
	 */
	WaterMeterResponse generateFileCSVLeakReport(DeviceRequest request);
}
