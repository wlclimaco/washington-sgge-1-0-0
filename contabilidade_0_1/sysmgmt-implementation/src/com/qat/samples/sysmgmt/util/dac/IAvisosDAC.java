package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condominio.model.Avisos;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IAvisosDAC.
 */
public interface IAvisosDAC
{

	/**
	 * Update cidade.
	 * 
	 * @param cidade the cidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateAvisos(Avisos cidade, InternalResultsResponse<?> response);

	/**
	 * Insert cidade.
	 * 
	 * @param cidade the cidade
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertAvisos(Avisos cidade, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business cidade.
	 * 
	 * @param cidade the cidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessAvisos(Avisos cidade, InternalResultsResponse<?> response);

	/**
	 * Delete person cidade.
	 * 
	 * @param cidade the cidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteAvisos(Avisos cidade, InternalResultsResponse<?> response);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param id the id
	 * @return the internal results response< cidade>
	 */
	public InternalResultsResponse<Avisos> fetchAvisosById(FetchByIdRequest id);

	/**
	 * Fetch all bancos.
	 * 
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Avisos> fetchAllAvisos();

	/**
	 * Fetch banco by request.
	 * 
	 * @param request the request
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Avisos> fetchAvisoByRequest(PagedInquiryRequest request);
}
