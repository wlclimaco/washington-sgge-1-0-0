package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.GrupoProd;

/**
 * The Interface IGrupoDAC.
 */
public interface IGrupoDAC
{

	/**
	 * Update uni med.
	 * 
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateGrupo(Grupo uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uni med.
	 * 
	 * @param uniMed the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertGrupo(Grupo uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med.
	 * 
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteGrupo(Grupo uniMed, InternalResultsResponse<?> response);

	/**
	 * Fetch uni med by id.
	 * 
	 * @param id the id
	 * @return the internal results response
	 */
	public InternalResultsResponse<Grupo> fetchGrupoById(Integer id);

	/**
	 * Fetch uni med by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Grupo> fetchGrupoByRequest(PagedInquiryRequest request);

	/**
	 * Update uni med prod.
	 * 
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateGrupoProd(GrupoProd uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uni med prod.
	 * 
	 * @param uniMed the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertGrupoProd(GrupoProd uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med prod.
	 * 
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteGrupoProd(GrupoProd uniMed, InternalResultsResponse<?> response);

}