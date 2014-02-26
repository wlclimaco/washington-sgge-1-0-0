package com.sensus.lc.comum.bcf;

import com.sensus.lc.comentario.model.request.ComentarioRequest;
import com.sensus.lc.comentario.model.request.InquiryComentarioRequest;
import com.sensus.lc.comentario.model.response.ComentarioResponse;
import com.sensus.lc.comentario.model.response.InquiryComentarioResponse;
import com.sensus.lc.curtir.model.request.CurtirRequest;
import com.sensus.lc.curtir.model.request.InquiryCurtirRequest;
import com.sensus.lc.curtir.model.response.CurtirResponse;
import com.sensus.lc.curtir.model.response.InquiryCurtirResponse;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;
import com.sensus.lc.foto.model.response.FotoResponse;
import com.sensus.lc.foto.model.response.InquiryFotoResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDietaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IComumBCF
{

	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryComentarioRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InquiryComentarioResponse fetchAllComentarios(InquiryComentarioRequest inquiryComentarioRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryComentarioRequest the inquiry dieta request
	 * @return the dieta response
	 */
	InquiryComentarioResponse fetchComentarioById(InquiryComentarioRequest inquiryComentarioRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	ComentarioResponse insertComentario(ComentarioRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	ComentarioResponse updateComentario(ComentarioRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	ComentarioResponse deleteComentario(ComentarioRequest dietaRequest);

	// =====================

	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryCurtirRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InquiryCurtirResponse fetchAllCurtirs(InquiryCurtirRequest inquiryCurtirRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryCurtirRequest the inquiry dieta request
	 * @return the dieta response
	 */
	InquiryCurtirResponse fetchCurtirById(InquiryCurtirRequest inquiryCurtirRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	CurtirResponse insertCurtir(CurtirRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	CurtirResponse updateCurtir(CurtirRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	CurtirResponse deleteCurtir(CurtirRequest dietaRequest);

	// =========================
	/**
	 * Fetch all dietas.
	 * 
	 * @param inquiryFotoRequest the inquiry dieta request
	 * @return the inquiry dieta response
	 */
	InquiryFotoResponse fetchAllFotos(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Fetch dieta by id.
	 * 
	 * @param inquiryFotoRequest the inquiry dieta request
	 * @return the dieta response
	 */
	InquiryFotoResponse fetchFotoById(InquiryFotoRequest inquiryFotoRequest);

	/**
	 * Insert dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	FotoResponse insertFoto(FotoRequest dietaRequest);

	/**
	 * Update dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	FotoResponse updateFoto(FotoRequest dietaRequest);

	/**
	 * Delete dieta.
	 * 
	 * @param dietaRequest the dieta request
	 * @return the dieta response
	 */
	FotoResponse deleteFoto(FotoRequest dietaRequest);

}
