package com.sensus.dm.common.foto.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.foto.model.Foto;
import com.sensus.dm.common.foto.model.request.FotoRequest;
import com.sensus.dm.common.foto.model.request.InquiryFotoRequest;

/**
 * The Interface IFotoDAC.
 * 
 * @author - Washington.
 */
public interface IFotoDAC
{
	/**
	 * Fetch all fotos.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Fetch devices by foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchFotosById(InquiryFotoRequest fotoRequest);

	/**
	 * Fetch devices by foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchFotosByName(InquiryFotoRequest fotoRequest);

	/**
	 * Insert foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> insertFoto(FotoRequest fotoRequest);

	/**
	 * Delete foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the internal response
	 */
	InternalResponse deleteFoto(FotoRequest fotoRequest);

	/**
	 * Update foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> updateFoto(FotoRequest fotoRequest);

}
