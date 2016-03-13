package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.PlanoSaude;
import com.qat.samples.sysmgmt.clinica.PlanoSaudePessoa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IPlanoSaudeDAC.
 */
public interface IPlanoSaudeDAC
{

	/**
	 * Update planoSaude.
	 * 
	 * @param planoSaude the planoSaude
	 * @return the internal results response< planoSaude>
	 */
	public Integer updatePlanoSaude(PlanoSaude planoSaude);

	/**
	 * Insert planoSaude.
	 * 
	 * @param planoSaude the planoSaude
	 * @return the internal results response< planoSaude>
	 */
	public Integer insertPlanoSaude(PlanoSaude planoSaude);

	/**
	 * Delete planoSaude.
	 * 
	 * @param planoSaude the planoSaude
	 * @return the internal response
	 */
	public Integer deletePlanoSaude(PlanoSaude planoSaude);

	/**
	 * Fetch planoSaude by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<PlanoSaude> fetchPlanoSaudeById(FetchByIdRequest request);

	/**
	 * Fetch all planoSaudes.
	 * 
	 * @return the internal results response< planoSaude>
	 */
	public InternalResultsResponse<PlanoSaude> fetchAllPlanoSaudes();

	/**
	 * Fetch planoSaude by request.
	 * 
	 * @param request the request
	 * @return the internal results response< planoSaude>
	 */
	public InternalResultsResponse<PlanoSaude> fetchPlanoSaudeByRequest(PagedInquiryRequest request);

	/**
	 * Update planoSaude.
	 * 
	 * @param planoSaude the planoSaude
	 * @return the internal results response< planoSaude>
	 */
	public Integer updatePlanoSaudePessoa(PlanoSaudePessoa planoSaude);

	/**
	 * Insert planoSaude.
	 * 
	 * @param planoSaude the planoSaude
	 * @return the internal results response< planoSaude>
	 */
	public Integer insertPlanoSaudePessoa(PlanoSaudePessoa planoSaude);

	/**
	 * Delete planoSaude.
	 * 
	 * @param planoSaude the planoSaude
	 * @return the internal response
	 */
	public Integer deletePlanoSaudePessoa(PlanoSaudePessoa planoSaude);

}
