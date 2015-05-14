package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IOrcamentoItensDAC
{
	/**
	 * Update orcamentoItens.
	 *
	 * @param orcamentoItens the orcamentoItens
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateOrcamentoItens(OrcamentoItens orcamentoItens, InternalResultsResponse<?> response);

	/**
	 * Insert orcamentoItens.
	 *
	 * @param orcamentoItens the orcamentoItens
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertOrcamentoItens(OrcamentoItens orcamentoItens, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business orcamentoItens.
	 *
	 * @param orcamentoItens the orcamentoItens
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessOrcamentoItens(OrcamentoItens orcamentoItens, InternalResultsResponse<?> response);

	/**
	 * Delete person orcamentoItens.
	 *
	 * @param orcamentoItens the orcamentoItens
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonOrcamentoItens(OrcamentoItens orcamentoItens, InternalResultsResponse<?> response);

	/**
	 * Fetch orcamentoItens by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< orcamentoItens>
	 */
	public InternalResultsResponse<OrcamentoItens> fetchOrcamentoItensByParent(Integer parentId,
			BusinessTypeEnum parentType);

	/**
	 * Fetch orcamentoItens by id.
	 *
	 * @param id the id
	 * @return the internal results response< orcamentoItens>
	 */
	public InternalResultsResponse<OrcamentoItens> fetchOrcamentoItensById(Integer id);

	/**
	 * Maintain orcamentoItens associations.
	 *
	 * @param orcamentoItensList the orcamentoItens list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainOrcamentoItensAssociations(List<OrcamentoItens> orcamentoItensList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}