package com.sensus.dm.common.foto.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.foto.model.request.FotoRequest;
import com.sensus.dm.common.foto.model.request.InquiryFotoRequest;
import com.sensus.dm.common.foto.model.response.FotoResponse;
import com.sensus.dm.common.foto.model.response.InquiryFotoResponse;

/**
 * The Interface IFotoBCF.
 * 
 * @author Washington.
 * 
 */
public interface IFotoBCF
{

	/**
	 * Fetch all fotos.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the inquiry foto response
	 */
	InquiryFotoResponse fetchAllFotos(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Fetch foto by id.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the foto response
	 */
	FotoResponse fetchFotoById(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Fetch foto by name.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the foto response
	 */
	FotoResponse fetchFotoByName(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Insert foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the foto response
	 */
	FotoResponse insertFoto(FotoRequest fotoRequest);

	/**
	 * Update foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the foto response
	 */
	FotoResponse updateFoto(FotoRequest fotoRequest);

	/**
	 * Delete foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the foto response
	 */
	FotoResponse deleteFoto(FotoRequest fotoRequest);

	/**
	 * Fetch devices by foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesByFoto(FotoRequest fotoRequest);

}
