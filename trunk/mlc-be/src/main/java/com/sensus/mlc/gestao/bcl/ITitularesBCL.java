package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Titulares;
import com.sensus.mlc.gestao.model.request.InquiryTitularesRequest;
import com.sensus.mlc.gestao.model.request.TitularesRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface ITitularesBCL
{

	/**
	 * Insert titulares.
	 *
	 * @param titularesRequest the titulares request
	 * @return the titulares response
	 */
	public InternalResultsResponse<Titulares> insertTitulares(TitularesRequest titularesRequest);

	/**
	 * Update titulares.
	 *
	 * @param titularesRequest the titulares request
	 * @return the titulares response
	 */
	public InternalResultsResponse<Titulares> updateTitulares(TitularesRequest titularesRequest);

	/**
	 * Delete titulares.
	 *
	 * @param titularesRequest the titulares request
	 * @return the titulares response
	 */
	public InternalResponse deleteTitulares(TitularesRequest titularesRequest);

	/**
	 * Fetch all titulares.
	 *
	 * @param inquirytitularesRequest the inquirytitulares request
	 * @return the inquiry titulares response
	 */
	public InternalResultsResponse<Titulares> fetchAllTitulares(InquiryTitularesRequest inquirytitularesRequest);

	/**
	 * Fetch titulares by id.
	 *
	 * @param inquirytitularesRequest the inquirytitulares request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Titulares> fetchTitularesById(TitularesRequest titularesRequest);

	/**
	 * Fetch all titulares types.
	 *
	 * @param request the request
	 * @return the titulares response
	 */
	public InternalResultsResponse<Titulares> fetchAllTitularesTypes(InquiryTitularesRequest inquirytitularesRequest);

	/**
	 * Fetch all titulares filial.
	 *
	 * @param request the request
	 * @return the titulares response
	 */
	public InternalResultsResponse<Titulares> fetchAllTitularesFilial(TitularesRequest titularesRequest);

}
