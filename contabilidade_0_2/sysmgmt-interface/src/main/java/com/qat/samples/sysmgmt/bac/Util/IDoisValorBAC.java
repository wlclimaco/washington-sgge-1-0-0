/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.Util;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDoisValorBAC. (Business Area Component - BAC)
 */
public interface IDoisValorBAC
{



//===================================### DOISVALOR ####======================================
	/**

	/**
	 * Insert doisvalor.
	 *
* @param request the doisvalor maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<DoisValores> insertDoisValor(DoisValoresMaintenanceRequest request);

	/**
* Update doisvalor.
*
* @param request the doisvalor maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<DoisValores> updateDoisValor(DoisValoresMaintenanceRequest request);

	/**
* Delete doisvalor.
*
* @param request the doisvalor maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<DoisValores> deleteDoisValor(DoisValoresMaintenanceRequest request);

	/**
* Refresh doisvalors.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<DoisValores> refreshDoisValors(RefreshRequest request);

	/**
* Fetch doisvalor by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<DoisValores> fetchDoisValorById(FetchByIdRequest request);

	/**
* Fetch all doisvalors.
*
* @return the internal results response< doisvalor>
*/
	public InternalResultsResponse<DoisValores> fetchAllDoisValors(DoisValores  doisvalor);

	/**
* Fetch doisvalors by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<DoisValores> fetchDoisValorsByRequest(DoisValoresInquiryRequest request);

}
