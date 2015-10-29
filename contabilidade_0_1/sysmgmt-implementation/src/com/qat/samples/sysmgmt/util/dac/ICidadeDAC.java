package com.qat.samples.sysmgmt.util.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.Cidade;

/**
 * The Interface ICidadeDAC.
 */
public interface ICidadeDAC
{

	/**
	 * Update cidade.
	 * 
	 * @param cidade the cidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateCidade(Cidade cidade, InternalResultsResponse<?> response);

	/**
	 * Insert cidade.
	 * 
	 * @param cidade the cidade
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCidade(Cidade cidade, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business cidade.
	 * 
	 * @param cidade the cidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessCidade(Cidade cidade, InternalResultsResponse<?> response);

	/**
	 * Delete person cidade.
	 * 
	 * @param cidade the cidade
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteCidade(Cidade cidade, InternalResultsResponse<?> response);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param id the id
	 * @return the internal results response< cidade>
	 */
	public InternalResultsResponse<Cidade> fetchCidadeById(Integer id);

	/**
	 * Maintain cidade associations.
	 * 
	 * @param cidadeList the cidade list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainCidadeAssociations(List<Cidade> cidadeList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response);

}
