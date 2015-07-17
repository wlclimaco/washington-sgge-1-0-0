package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.SubGrupo;
import com.prosperitasglobal.sendsolv.model.SubGrupoProd;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISubGrupoDAC.
 */
public interface ISubGrupoDAC
{

	/**
	 * Update uni med.
	 *
	 * @param subGrupo the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Insert uni med.
	 *
	 * @param subGrupo the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSubGrupo(SubGrupo subGrupo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med.
	 *
	 * @param subGrupo the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Fetch uni med by id.
	 *
	 * @param id the id
	 * @return the internal results response
	 */
	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(Integer id);

	/**
	 * Fetch uni med by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<SubGrupo> fetchSubGrupoByRequest(PagedInquiryRequest request);

	/**
	 * Update uni med prod.
	 *
	 * @param subGrupo the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateSubGrupoProd(SubGrupoProd subGrupo, InternalResultsResponse<?> response);

	/**
	 * Insert uni med prod.
	 *
	 * @param subGrupo the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSubGrupoProd(SubGrupoProd subGrupo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med prod.
	 *
	 * @param subGrupo the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteSubGrupoProd(SubGrupoProd subGrupo, InternalResultsResponse<?> response);

}