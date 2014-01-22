package com.sensus.lc.foto.bcf;

import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;
import com.sensus.lc.foto.model.response.FotoResponse;
import com.sensus.lc.foto.model.response.InquiryFotoResponse;

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

}
