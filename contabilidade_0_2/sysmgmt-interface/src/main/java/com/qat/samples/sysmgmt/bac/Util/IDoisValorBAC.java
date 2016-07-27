/** create by system gera-java version 1.0.0 27/07/2016 15:44 : 43*/
package com.qat.samples.sysmgmt.bac.undefined;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
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
	public InternalResultsResponse<Doisvalor> insertDoisvalor(DoisvalorMaintenanceRequest request);

	/**
* Update doisvalor.
*
* @param request the doisvalor maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Doisvalor> updateDoisvalor(DoisvalorMaintenanceRequest request);

	/**
* Delete doisvalor.
*
* @param request the doisvalor maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Doisvalor> deleteDoisvalor(DoisvalorMaintenanceRequest request);

	/**
* Refresh doisvalors.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Doisvalor> refreshDoisvalors(RefreshRequest request);

	/**
* Fetch doisvalor by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Doisvalor> fetchDoisvalorById(FetchByIdRequest request);

	/**
* Fetch all doisvalors.
*
* @return the internal results response< doisvalor>
*/
	public InternalResultsResponse<Doisvalor> fetchAllDoisvalors(Doisvalor  doisvalor);

	/**
* Fetch doisvalors by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Doisvalor> fetchDoisvalorsByRequest(DoisvalorInquiryRequest request);

}
