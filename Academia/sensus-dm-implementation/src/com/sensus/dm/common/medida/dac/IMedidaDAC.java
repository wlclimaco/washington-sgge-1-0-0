package com.sensus.dm.common.medida.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.medida.model.request.InquiryMedidaRequest;
import com.sensus.dm.common.medida.model.request.MedidaRequest;

/**
 * The Interface IMedidaDAC.
 * 
 * @author - Washington.
 */
public interface IMedidaDAC
{
	/**
	 * Fetch all medidas.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> fetchAllMedidas(InquiryMedidaRequest inquiryMedidaRequest);

	/**
	 * Fetch devices by medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> fetchMedidasById(InquiryMedidaRequest medidaRequest);

	/**
	 * Fetch devices by medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> fetchMedidasByName(InquiryMedidaRequest medidaRequest);

	/**
	 * Insert medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> insertMedida(MedidaRequest medidaRequest);

	/**
	 * Delete medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal response
	 */
	InternalResponse deleteMedida(MedidaRequest medidaRequest);

	/**
	 * Update medida.
	 * 
	 * @param medidaRequest the medida request
	 * @return the internal results response
	 */
	InternalResultsResponse<Medida> updateMedida(MedidaRequest medidaRequest);

}
