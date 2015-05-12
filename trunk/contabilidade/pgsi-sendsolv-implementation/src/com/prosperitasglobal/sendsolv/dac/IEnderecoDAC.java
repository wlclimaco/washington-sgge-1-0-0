package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Endereco;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IEnderecoDAC
{
	/**
	 * Update endereco.
	 *
	 * @param endereco the endereco
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateEndereco(Endereco endereco, InternalResultsResponse<?> response);

	/**
	 * Insert endereco.
	 *
	 * @param endereco the endereco
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEndereco(Endereco endereco, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business endereco.
	 *
	 * @param endereco the endereco
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessEndereco(Endereco endereco, InternalResultsResponse<?> response);

	/**
	 * Delete person endereco.
	 *
	 * @param endereco the endereco
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonEndereco(Endereco endereco, InternalResultsResponse<?> response);

	/**
	 * Fetch endereco by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Endereco> fetchEnderecoByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch endereco by id.
	 *
	 * @param id the id
	 * @return the internal results response< endereco>
	 */
	public InternalResultsResponse<Endereco> fetchEnderecoById(Integer id);

	/**
	 * Maintain endereco associations.
	 *
	 * @param enderecoList the endereco list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainEnderecoAssociations(List<Endereco> enderecoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}