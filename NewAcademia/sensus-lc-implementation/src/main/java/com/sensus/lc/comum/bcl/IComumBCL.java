package com.sensus.lc.comum.bcl;

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
 * The Interface IAcademiaBCL.
 * 
 * @author Washington.
 * 
 */
public interface IComumBCL
{
	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryComentarioRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InternalResultsResponse<Comentario> fetchAllComentarios(InquiryComentarioRequest inquiryComentarioRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryComentarioRequest the inquiry dieta request
	 * @return the dieta response
	 */
	InternalResultsResponse<Comentario> fetchComentarioById(InquiryComentarioRequest inquiryComentarioRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResultsResponse<Comentario> insertComentario(ComentarioRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResponse updateComentario(ComentarioRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResponse deleteComentario(ComentarioRequest dietaRequest);

	// =====================

	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryCurtirRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InternalResultsResponse<Curtir> fetchAllCurtirs(InquiryCurtirRequest inquiryCurtirRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryCurtirRequest the inquiry dieta request
	 * @return the dieta response
	 */
	InternalResultsResponse<Curtir> fetchCurtirById(InquiryCurtirRequest inquiryCurtirRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResultsResponse<Curtir> insertCurtir(CurtirRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResponse updateCurtir(CurtirRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResponse deleteCurtir(CurtirRequest dietaRequest);

	// =========================
	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryFotoRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryFotoRequest the inquiry dieta request
	 * @return the dieta response
	 */
	InternalResultsResponse<Foto> fetchFotoById(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResultsResponse<Foto> insertFoto(FotoRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResponse updateFoto(FotoRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	InternalResponse deleteFoto(FotoRequest dietaRequest);
}
