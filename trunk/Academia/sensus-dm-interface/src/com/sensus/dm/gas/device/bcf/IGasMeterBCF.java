package com.sensus.dm.gas.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;

/**
 * The Interface IGasMeterBCF.
 */
public interface IGasMeterBCF
{

	/**
	 * Fetch all gas meters.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch gas meter by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse fetchGasMeterById(DeviceRequest deviceRequest);

	/**
	 * Generate devices csv.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 */
	InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest);
}
