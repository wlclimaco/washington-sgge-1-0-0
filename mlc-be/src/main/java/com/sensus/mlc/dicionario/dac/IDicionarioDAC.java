package com.sensus.mlc.dicionario.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;


/**
 * The Interface IGroupDAC.
 */
public interface IDicionarioDAC {

	/**
	 * Insert dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tela> insertTela(
			TelaRequest dicionarioRequest);

	/**
	 * Delete dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the internal response
	 */
	public InternalResponse deleteTela(TelaRequest dicionarioRequest);

	/**
	 * Update dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the internal response
	 */
	public InternalResultsResponse<Tela> updateTela(
			TelaRequest dicionarioRequest);
	/**
	 * Fetch all dicionarios.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tela> fetchAllTela(
			InquiryTelaRequest inquiryDicionarioRequest);

	/**
	 * Fetch dicionario by id.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tela> fetchTelaById(
			TelaRequest dicionarioRequest);


}