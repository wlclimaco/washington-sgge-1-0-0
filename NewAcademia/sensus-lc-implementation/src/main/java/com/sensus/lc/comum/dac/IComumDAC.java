package com.sensus.lc.comum.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.comentario.model.request.ComentarioRequest;
import com.sensus.lc.comentario.model.request.InquiryComentarioRequest;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.curtir.model.request.CurtirRequest;
import com.sensus.lc.curtir.model.request.InquiryCurtirRequest;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;

/**
 * The Interface IAcademiaDAC.
 * 
 * @author - Washington.
 */
public interface IComumDAC
{

	/**
	 * Fetch all comentarios.
	 * 
	 * @param inquiryComentarioRequest the inquiry comentario request
	 * @return the internal results response
	 */
	InternalResultsResponse<Comentario> fetchAllComentarios(InquiryComentarioRequest inquiryComentarioRequest);

	/**
	 * Fetch comentario by id.
	 * 
	 * @param inquiryComentarioRequest the inquiry comentario request
	 * @return the internal results response
	 */
	InternalResultsResponse<Comentario> fetchComentarioById(InquiryComentarioRequest inquiryComentarioRequest);

	/**
	 * Insert comentario.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Comentario> insertComentario(ComentarioRequest dietaRequest);

	/**
	 * Update comentario.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Comentario> updateComentario(ComentarioRequest dietaRequest);

	/**
	 * Delete comentario.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal response
	 */
	InternalResponse deleteComentario(ComentarioRequest dietaRequest);

	// =====================

	/**
	 * Fetch all curtirs.
	 * 
	 * @param inquiryCurtirRequest the inquiry curtir request
	 * @return the internal results response
	 */
	InternalResultsResponse<Curtir> fetchAllCurtirs(InquiryCurtirRequest inquiryCurtirRequest);

	/**
	 * Fetch curtir by id.
	 * 
	 * @param inquiryCurtirRequest the inquiry curtir request
	 * @return the internal results response
	 */
	InternalResultsResponse<Curtir> fetchCurtirById(InquiryCurtirRequest inquiryCurtirRequest);

	/**
	 * Insert curtir.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Curtir> insertCurtir(CurtirRequest dietaRequest);

	/**
	 * Update curtir.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Curtir> updateCurtir(CurtirRequest dietaRequest);

	/**
	 * Delete curtir.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal response
	 */
	InternalResponse deleteCurtir(CurtirRequest dietaRequest);

	// =========================

	/**
	 * Fetch all fotos.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Fetch foto by id.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> fetchFotoById(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Insert foto.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> insertFoto(FotoRequest dietaRequest);

	/**
	 * Update foto.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal results response
	 */
	InternalResultsResponse<Foto> updateFoto(FotoRequest dietaRequest);

	/**
	 * Delete foto.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the internal response
	 */
	InternalResponse deleteFoto(FotoRequest dietaRequest);

}
