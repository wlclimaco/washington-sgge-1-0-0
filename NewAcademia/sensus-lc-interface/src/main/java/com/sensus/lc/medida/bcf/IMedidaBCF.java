package com.sensus.lc.medida.bcf;

import com.sensus.lc.medida.model.request.InquiryMedidaRequest;
import com.sensus.lc.medida.model.request.MedidaRequest;
import com.sensus.lc.medida.model.response.InquiryMedidaResponse;
import com.sensus.lc.medida.model.response.MedidaResponse;

/**
 * The Interface IMedidaBCF.
 * 
 * @author Washington.
 * 
 */
public interface IMedidaBCF
{

	/**
	 * Fetch all medidas.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the inquiry medida response
	 */
	InquiryMedidaResponse fetchAllMedidas(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Fetch medida by id.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the medida response
	 */
	MedidaResponse fetchMedidaById(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Fetch medida by name.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the medida response
	 */
	MedidaResponse fetchMedidaByName(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Insert medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the medida response
	 */
	MedidaResponse insertMedida(MedidaRequest medidaRequest);

	/**
	 * Update medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the medida response
	 */
	MedidaResponse updateMedida(MedidaRequest medidaRequest);

	/**
	 * Delete medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the medida response
	 */
	MedidaResponse deleteMedida(MedidaRequest medidaRequest);

}
