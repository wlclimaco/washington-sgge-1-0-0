package com.sensus.dm.water.device.dac;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Interface IWaterMeterDAC.
 * 
 * @author QAT Global
 */
public interface IWaterMeterDAC
{

	/**
	 * Fetch all water meters.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<WaterMeter> fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch water meter by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchWaterMeterById(DeviceRequest deviceRequest);

	/**
	 * Fetch communication.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<WaterGasMeterStatusCount> fetchCommunication(DeviceRequest deviceRequest);

	/**
	 * Fetch leak report.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<WaterLeak> fetchLeakReport(DeviceRequest request);
}
