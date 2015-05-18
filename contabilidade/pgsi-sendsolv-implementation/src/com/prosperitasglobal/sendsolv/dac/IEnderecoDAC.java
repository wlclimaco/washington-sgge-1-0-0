package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

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
	public Integer deleteEndereco(Endereco endereco, InternalResultsResponse<?> response);

	public Integer maintainEnderecoAssociations(List<Endereco> enderecoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}