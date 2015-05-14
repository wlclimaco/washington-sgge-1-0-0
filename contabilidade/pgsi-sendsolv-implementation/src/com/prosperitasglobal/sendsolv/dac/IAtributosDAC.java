package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IAtributosDAC
{
	/**
	 * Update atributos.
	 *
	 * @param atributos the atributos
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateAtributos(Atributos atributos, InternalResultsResponse<?> response);

	/**
	 * Insert atributos.
	 *
	 * @param atributos the atributos
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertAtributos(Atributos atributos, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business atributos.
	 *
	 * @param atributos the atributos
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessAtributos(Atributos atributos, InternalResultsResponse<?> response);

	/**
	 * Delete person atributos.
	 *
	 * @param atributos the atributos
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonAtributos(Atributos atributos, InternalResultsResponse<?> response);

	/**
	 * Fetch atributos by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< atributos>
	 */
	public InternalResultsResponse<Atributos> fetchAtributosByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch atributos by id.
	 *
	 * @param id the id
	 * @return the internal results response< atributos>
	 */
	public InternalResultsResponse<Atributos> fetchAtributosById(Integer id);

	/**
	 * Maintain atributos associations.
	 *
	 * @param atributosList the atributos list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainAtributosAssociations(List<Atributos> atributosList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}