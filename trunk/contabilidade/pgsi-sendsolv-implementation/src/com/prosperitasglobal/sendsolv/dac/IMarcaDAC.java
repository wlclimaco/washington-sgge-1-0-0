package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Marca;
import com.prosperitasglobal.sendsolv.model.MarcaProd;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMarcaDAC.
 */
public interface IMarcaDAC
{

	/**
	 * Update uni med.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateMarca(Marca uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uni med.
	 *
	 * @param uniMed the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertMarca(Marca uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteMarca(Marca uniMed, InternalResultsResponse<?> response);

	/**
	 * Fetch uni med by id.
	 *
	 * @param id the id
	 * @return the internal results response
	 */
	public InternalResultsResponse<Marca> fetchMarcaById(Integer id);

	/**
	 * Fetch uni med by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Marca> fetchMarcaByRequest(PagedInquiryRequest request);

	/**
	 * Update uni med prod.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateMarcaProd(MarcaProd uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uni med prod.
	 *
	 * @param uniMed the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertMarcaProd(MarcaProd uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med prod.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteMarcaProd(MarcaProd uniMed, InternalResultsResponse<?> response);

}