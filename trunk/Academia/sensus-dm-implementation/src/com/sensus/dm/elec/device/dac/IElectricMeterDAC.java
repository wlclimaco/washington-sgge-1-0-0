package com.sensus.dm.elec.device.dac;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

/**
 * The Interface IElectricMeterDAC.
 * 
 * @author QAT Global.
 */
public interface IElectricMeterDAC
{
	/**
	 * Fetch all electric meter.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<ElectricMeter> fetchAllElectricMeter(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch all han devices.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<HanDevice> fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch all lcm.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal results response
	 */
	InternalResultsResponse<LCM> fetchAllLCM(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Fetch lcm by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchLCMById(DeviceRequest deviceRequest);

	/**
	 * Fetch electric meter by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchElectricMeterById(DeviceRequest deviceRequest);

	/**
	 * Fetch han device by id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchHanDeviceById(DeviceRequest deviceRequest);

	/**
	 * Fetch lifecycle states.
	 * 
	 * @param meter the meter
	 * @return the cached results response
	 */
	CachedResultsResponse<LifecycleState> fetchLifecycleStates(DeviceRequest meter);

	/**
	 * Fetch all han devices by meter.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchAllHanDevicesByMeter(DeviceRequest deviceRequest);

	/**
	 * Fetch all manufacture by device sub type.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest);

	/**
	 * Fetch all model by device sub type.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchAllModelByDeviceSubType(DeviceRequest deviceRequest);

	/**
	 * Fetch han device type id.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchHanDeviceTypeId(DeviceRequest deviceRequest);

	/**
	 * Update device status.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse updateDeviceStatus(DeviceRequest deviceRequest);

	/**
	 * Delete device status.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse deleteDeviceStatus(DeviceRequest deviceRequest);

	/**
	 * Delete device reference.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse deleteDevice(DeviceRequest deviceRequest);

	/**
	 * Fetch device type id by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchDeviceTypeIdByDevice(DeviceRequest deviceRequest);

	/**
	 * Fetch all connection state.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<ConnectionState> fetchAllConnectionState(DeviceRequest deviceRequest);

	/**
	 * Fetch esm tou enabled.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchEsmTouEnabled(DeviceRequest deviceRequest);

	/**
	 * Fetch all peak demand.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<PeakDemand> fetchAllPeakDemand(DeviceReadingRequest request);
}
