package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Documento;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IDocumentoDAC
{
	/**
	 * Update documento.
	 *
	 * @param documento the documento
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateDocumento(Documento documento, InternalResultsResponse<?> response);

	/**
	 * Insert documento.
	 *
	 * @param documento the documento
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertDocumento(Documento documento, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business documento.
	 *
	 * @param documento the documento
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessDocumento(Documento documento, InternalResultsResponse<?> response);

	/**
	 * Delete person documento.
	 *
	 * @param documento the documento
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonDocumento(Documento documento, InternalResultsResponse<?> response);

	/**
	 * Fetch documento by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< documento>
	 */
	public InternalResultsResponse<Documento> fetchDocumentoByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch documento by id.
	 *
	 * @param id the id
	 * @return the internal results response< documento>
	 */
	public InternalResultsResponse<Documento> fetchDocumentoById(Integer id);

	/**
	 * Maintain documento associations.
	 *
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainDocumentoAssociations(List<Documento> documentoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}