package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.UniMedProd;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IUniMedDAC.
 */
public interface IUniMedDAC
{

	/**
	 * Update uni med.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateUniMed(UniMed uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uni med.
	 *
	 * @param uniMed the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertUniMed(UniMed uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteUniMed(UniMed uniMed, InternalResultsResponse<?> response);

	/**
	 * Fetch uni med by id.
	 *
	 * @param id the id
	 * @return the internal results response
	 */
	public InternalResultsResponse<UniMed> fetchUniMedById(Integer id);

	/**
	 * Fetch uni med by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<UniMed> fetchUniMedByRequest(PagedInquiryRequest request);

	/**
	 * Update uni med prod.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateUniMedProd(UniMedProd uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uni med prod.
	 *
	 * @param uniMed the uni med
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertUniMedProd(UniMedProd uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete uni med prod.
	 *
	 * @param uniMed the uni med
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteUniMedProd(UniMedProd uniMed, InternalResultsResponse<?> response);

}