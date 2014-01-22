package com.sensus.lc.foto.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;

/**
 * The Interface IFotoBCL.
 * 
 * @author Washington.
 * 
 */
public interface IFotoBCL
{

	/**
	 * Fetch all fotos.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryPaginationRequest);

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
	 * @return the internal response
	 */
	InternalResultsResponse<Foto> updateFoto(FotoRequest fotoRequest);

	/**
	 * Fetch fotos by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchFotosById(InquiryFotoRequest InquiryFotoRequest);

	/**
	 * Insert foto.
	 * 
	 * @param fotoRequest the foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> insertFoto(FotoRequest fotoRequest);

}
