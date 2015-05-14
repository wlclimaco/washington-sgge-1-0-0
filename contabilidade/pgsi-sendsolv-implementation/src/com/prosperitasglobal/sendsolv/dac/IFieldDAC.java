package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IFieldDAC
{
	/**
	 * Update field.
	 *
	 * @param field the field
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateField(Field field, InternalResultsResponse<?> response);

	/**
	 * Insert field.
	 *
	 * @param field the field
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertField(Field field, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business field.
	 *
	 * @param field the field
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessField(Field field, InternalResultsResponse<?> response);

	/**
	 * Delete person field.
	 *
	 * @param field the field
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonField(Field field, InternalResultsResponse<?> response);

	/**
	 * Fetch field by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< field>
	 */
	public InternalResultsResponse<Field> fetchFieldByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch field by id.
	 *
	 * @param id the id
	 * @return the internal results response< field>
	 */
	public InternalResultsResponse<Field> fetchFieldById(Integer id);

	/**
	 * Maintain field associations.
	 *
	 * @param fieldList the field list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainFieldAssociations(List<Field> fieldList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}