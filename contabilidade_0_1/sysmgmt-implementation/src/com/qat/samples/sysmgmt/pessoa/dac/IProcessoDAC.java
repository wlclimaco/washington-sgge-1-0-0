package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.model.Processo;
import com.qat.samples.sysmgmt.advocacia.model.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IAgenciaDAC.
 */
public interface IProcessoDAC
{

	/**
	 * Update agencia.
	 * 
	 * @param agencia the agencia
	 * @return the internal results response< agencia>
	 */
	public Integer updateProcesso(Processo processo);

	/**
	 * Insert processo.
	 * 
	 * @param processo the processo
	 * @return the internal results response< processo>
	 */
	public Integer insertProcesso(Processo processo);

	/**
	 * Delete processo.
	 * 
	 * @param processo the processo
	 * @return the internal response
	 */
	public Integer deleteProcesso(Processo processo);

	/**
	 * Fetch processo by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request);

	/**
	 * Fetch all processos.
	 * 
	 * @return the internal results response< processo>
	 */
	public InternalResultsResponse<Processo> fetchAllProcessos();

	/**
	 * Fetch processo by request.
	 * 
	 * @param request the request
	 * @return the internal results response< processo>
	 */
	public InternalResultsResponse<Processo> fetchProcessoByRequest(ProcessoInquiryRequest request);

}
