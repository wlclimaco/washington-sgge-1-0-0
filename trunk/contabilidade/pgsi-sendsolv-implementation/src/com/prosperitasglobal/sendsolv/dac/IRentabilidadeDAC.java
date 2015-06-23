package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.dacd.mybatis.Rentabilidade;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IRentabilidadeDAC.
 */
public interface IRentabilidadeDAC
{

	/**
	 * Update rentabilidade.
	 *
	 * @param rentabilidade the rentabilidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateRentabilidade(Rentabilidade rentabilidade, InternalResultsResponse<?> response);

	/**
	 * Insert rentabilidade.
	 *
	 * @param rentabilidade the rentabilidade
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertRentabilidade(Rentabilidade rentabilidade, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business rentabilidade.
	 *
	 * @param rentabilidade the rentabilidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteRentabilidade(Rentabilidade rentabilidade, InternalResultsResponse<?> response);

	/**
	 * Fetch rentabilidade by id.
	 *
	 * @param id the id
	 * @return the internal results response< rentabilidade>
	 */
	public InternalResultsResponse<Rentabilidade> fetchRentabilidadeById(Integer id);

}
