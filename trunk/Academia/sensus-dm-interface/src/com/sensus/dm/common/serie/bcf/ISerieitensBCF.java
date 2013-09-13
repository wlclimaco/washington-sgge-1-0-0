package com.sensus.dm.common.serie.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.serie.model.request.InquirySerieitensRequest;
import com.sensus.dm.common.serie.model.request.SerieitensRequest;
import com.sensus.dm.common.serie.model.response.InquirySerieitensResponse;
import com.sensus.dm.common.serie.model.response.SerieitensResponse;

/**
 * The Interface ISerieitensBCF.
 * 
 * @author Washington.
 * 
 */
public interface ISerieitensBCF
{

	/**
	 * Fetch all serieitenss.
	 * 
	 * @param inquirySerieitensRequest the inquiry serieitens request
	 * @return the inquiry serieitens response
	 */
	InquirySerieitensResponse fetchAllSerieitenss(InquirySerieitensRequest inquirySerieitensRequest);

	/**
	 * Fetch serieitens by id.
	 * 
	 * @param inquirySerieitensRequest the inquiry serieitens request
	 * @return the serieitens response
	 */
	SerieitensResponse fetchSerieitensById(InquirySerieitensRequest inquirySerieitensRequest);

	/**
	 * Fetch serieitens by name.
	 * 
	 * @param inquirySerieitensRequest the inquiry serieitens request
	 * @return the serieitens response
	 */
	SerieitensResponse fetchSerieitensByName(InquirySerieitensRequest inquirySerieitensRequest);

	/**
	 * Insert serieitens.
	 * 
	 * @param serieitensRequest the serieitens request
	 * @return the serieitens response
	 */
	SerieitensResponse insertSerieitens(SerieitensRequest serieitensRequest);

	/**
	 * Update serieitens.
	 * 
	 * @param serieitensRequest the serieitens request
	 * @return the serieitens response
	 */
	SerieitensResponse updateSerieitens(SerieitensRequest serieitensRequest);

	/**
	 * Delete serieitens.
	 * 
	 * @param serieitensRequest the serieitens request
	 * @return the serieitens response
	 */
	SerieitensResponse deleteSerieitens(SerieitensRequest serieitensRequest);

	/**
	 * Fetch devices by serieitens.
	 * 
	 * @param serieitensRequest the serieitens request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesBySerieitens(SerieitensRequest serieitensRequest);

}
