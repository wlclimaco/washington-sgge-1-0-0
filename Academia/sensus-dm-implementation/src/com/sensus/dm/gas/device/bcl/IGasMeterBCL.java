package com.sensus.dm.gas.device.bcl;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Interface IGasMeterBCL.
 */
public interface IGasMeterBCL
{

	/**
	 * Fetch all gas meters.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<GasMeter> fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch gas meter by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchGasMeterById(DeviceRequest deviceRequest);

	/**
	 * Generate devices csv.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal response
	 */
	InternalResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest);
}
