package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Profissao;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IProfissaoDAC.
 */
public interface IProfissaoDAC
{

	/**
	 * Update profissao.
	 *
	 * @param profissao the profissao
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateProfissao(Profissao profissao, InternalResultsResponse<?> response);

	/**
	 * Insert profissao.
	 *
	 * @param profissao the profissao
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertProfissao(Profissao profissao, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business profissao.
	 *
	 * @param profissao the profissao
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteProfissao(Profissao profissao, InternalResultsResponse<?> response);

	/**
	 * Fetch profissao by id.
	 *
	 * @param id the id
	 * @return the internal results response< profissao>
	 */
	public InternalResultsResponse<Profissao> fetchProfissaoById(Integer id);

}
