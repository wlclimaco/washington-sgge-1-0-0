package com.sensus.dm.elec.device.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryIntervalReadResponse;
import com.sensus.dm.elec.device.model.response.InquiryLoadProfileResponse;
import com.sensus.dm.elec.device.model.response.TOUReadResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDeviceReadingBCF.
 * 
 * @author QAT Global
 */
public interface IDeviceReadingBCF
{

	/**
	 * Fetch all water gas data read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse fetchAllWaterGasDataRead(DeviceReadingRequest request);

	/**
	 * Generate file csv water gas data read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse generateFileCSVWaterGasDataRead(DeviceReadingRequest request);

	/**
	 * Fetch all tou read.
	 * 
	 * @param request the request
	 * @return the tOU read response
	 */
	TOUReadResponse fetchAllTOURead(DeviceReadingRequest request);

	/**
	 * Generate file csvtou read.
	 * 
	 * @param request the request
	 * @return the tOU read response
	 */
	TOUReadResponse generateFileCSVTOURead(DeviceReadingRequest request);

	/**
	 * Fetch all interval read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse fetchAllIntervalRead(DeviceReadingRequest request);

	/**
	 * Generate file csv interval read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse generateFileCSVIntervalRead(DeviceReadingRequest request);

	/**
	 * Fetch all load profile read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse fetchAllLoadProfileRead(DeviceReadingRequest request);

	/**
	 * Generate file csv load profile read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse generateFileCSVLoadProfileRead(DeviceReadingRequest request);

	/**
	 * Fetch all snapshots.
	 * 
	 * @param request the inquiry interval read request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse fetchAllSnapshots(DeviceReadingRequest request);

	/**
	 * Generate file csv snapshot.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	InquiryIntervalReadResponse generateFileCSVSnapshot(DeviceReadingRequest request);

	/**
	 * Fetch updated meter load profile.
	 * 
	 * @param request the request
	 * @return the inquiry load profile response
	 */
	InquiryLoadProfileResponse fetchUpdatedMeterLoadProfile(DeviceRequest request);
}
