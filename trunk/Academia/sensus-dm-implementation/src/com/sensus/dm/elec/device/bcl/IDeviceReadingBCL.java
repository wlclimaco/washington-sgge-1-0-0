package com.sensus.dm.elec.device.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDeviceReadingBCLImpl.
 * 
 * @author QAT Global
 */
public interface IDeviceReadingBCL
{

	/**
	 * Fetch all water gas data read.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<IntervalRead> fetchAllWaterGasDataRead(DeviceReadingRequest request);

	/**
	 * Generate file csv water gas data read.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVWaterGasDataRead(DeviceReadingRequest request);

	/**
	 * Fetch all tou read.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<String[][]> fetchAllTOURead(DeviceReadingRequest request);

	/**
	 * Generate file csvtou read.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVTOURead(DeviceReadingRequest request);

	/**
	 * Fetch all interval read.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<IntervalRead> fetchAllIntervalRead(DeviceReadingRequest request);

	/**
	 * Generate file csv interval read.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVIntervalRead(DeviceReadingRequest request);

	/**
	 * Fetch all load profile.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<IntervalRead> fetchAllLoadProfileRead(DeviceReadingRequest request);

	/**
	 * Generate file csv load profile read.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVLoadProfileRead(DeviceReadingRequest request);

	/**
	 * Fetch all snapshots.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<IntervalRead> fetchAllSnapshots(DeviceReadingRequest request);

	/**
	 * Generate file csv snapshot.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse generateFileCSVSnapshot(DeviceReadingRequest request);

	/**
	 * Fetch updated meter load profile.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<LoadProfile> fetchUpdatedMeterLoadProfile(DeviceRequest request);

}
