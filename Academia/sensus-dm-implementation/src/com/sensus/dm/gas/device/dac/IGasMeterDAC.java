package com.sensus.dm.gas.device.dac;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.gas.device.model.GasMeter;

/**
 * The Interface IGasMeterDAC.
 * 
 * @author QAT Global.
 */
public interface IGasMeterDAC
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
}
