/** create by system gera-java version 1.0.0 27/07/2016 17:37 : 11*/
package com.qat.samples.sysmgmt.bar.Util;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface DoisValoresBAR.. (Data Access Component - DAC)
 */
public interface IDoisValorBAR
{

	/**
	 * Fetch doisvalor by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public DoisValores fetchDoisValoresById(FetchByIdRequest request);

	/**
* Insert doisvalor.
*
* @param doisvalor the doisvalor
*
* @return the internal response
*/
	public InternalResponse insertDoisValores(DoisValores doisvalor);

	/**
* Update doisvalor.
*
* @param doisvalor the doisvalor
*
* @return the internal response
*/
	public InternalResponse updateDoisValores(DoisValores doisvalor);

	/**
* Delete doisvalor.
*
* @param doisvalor the doisvalor
*
* @return the internal response
*/
	public InternalResponse deleteDoisValoresById(DoisValores doisvalor);

	/**
* Delete all doisvalors.
*
* @return the internal response
*/
	public InternalResponse deleteAllDoisValoress();

	/**
* Fetch all doisvalors.
*
* @return the list< doisvalor>
*/
	public InternalResultsResponse<DoisValores> fetchAllDoisValoress(DoisValores  doisvalor);

	/**
* Fetch doisvalors by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<DoisValores> fetchDoisValoressByRequest(DoisValoresInquiryRequest request);

}
