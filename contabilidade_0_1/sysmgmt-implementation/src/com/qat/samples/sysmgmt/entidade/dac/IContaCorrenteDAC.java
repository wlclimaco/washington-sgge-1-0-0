package com.qat.samples.sysmgmt.entidade.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.conta.ContaCorrente;

/**
 * The Interface IContaCorrenteDAC.
 */
public interface IContaCorrenteDAC
{

	/**
	 * Update contaCorrente.
	 * 
	 * @param contaCorrente the contaCorrente
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateContaCorrente(ContaCorrente contaCorrente, InternalResultsResponse<?> response);

	/**
	 * Insert contaCorrente.
	 * 
	 * @param contaCorrente the contaCorrente
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertContaCorrente(ContaCorrente contaCorrente, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business contaCorrente.
	 * 
	 * @param contaCorrente the contaCorrente
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessContaCorrente(ContaCorrente contaCorrente, InternalResultsResponse<?> response);

	/**
	 * Delete person contaCorrente.
	 * 
	 * @param contaCorrente the contaCorrente
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteContaCorrente(ContaCorrente contaCorrente, InternalResultsResponse<?> response);

	/**
	 * Fetch contaCorrente by id.
	 * 
	 * @param id the id
	 * @return the internal results response< contaCorrente>
	 */
	public InternalResultsResponse<ContaCorrente> fetchContaCorrenteById(Integer id);

	/**
	 * Maintain contaCorrente associations.
	 * 
	 * @param contaCorrenteList the contaCorrente list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainContaCorrenteAssociations(List<ContaCorrente> contaCorrenteList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);

}
