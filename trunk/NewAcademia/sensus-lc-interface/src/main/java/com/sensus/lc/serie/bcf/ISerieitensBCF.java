package com.sensus.lc.serie.bcf;

import com.sensus.lc.serie.model.request.InquirySerieitensRequest;
import com.sensus.lc.serie.model.request.SerieitensRequest;
import com.sensus.lc.serie.model.response.InquirySerieitensResponse;
import com.sensus.lc.serie.model.response.SerieitensResponse;

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

}
