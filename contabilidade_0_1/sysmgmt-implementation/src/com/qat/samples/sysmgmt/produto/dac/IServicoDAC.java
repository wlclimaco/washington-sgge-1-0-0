package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.produto.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.Servico;

/**
 * The Interface IServicoDAC.
 */
public interface IServicoDAC
{

	/**
	 * Update servico.
	 * 
	 * @param servico the servico
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateServico(PlanoByServico servico, InternalResultsResponse<?> response);

	/**
	 * Insert servico.
	 * 
	 * @param servico the servico
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertServico(PlanoByServico servico, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business servico.
	 * 
	 * @param servico the servico
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteServico(PlanoByServico servico, InternalResultsResponse<?> response);

	/**
	 * Fetch servico by id.
	 * 
	 * @param id the id
	 * @return the internal results response< servico>
	 */
	public InternalResultsResponse<Servico> fetchServicoById(Integer id);

}
