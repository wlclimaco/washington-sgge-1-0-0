package com.qat.samples.sysmgmt.bar.Status;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface StatusBAR.. (Data Access Component - DAC)
 */
public interface IStatusBAR
{

	/**
	 * Fetch status by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Status fetchStatusById(FetchByIdRequest request);

	/**
* Insert status.
*
* @param status the status
*
* @return the internal response
*/
	public InternalResponse insertStatus(Status status);

	/**
* Update status.
*
* @param status the status
*
* @return the internal response
*/
	public InternalResponse updateStatus(Status status);

	/**
* Delete status.
*
* @param status the status
*
* @return the internal response
*/
	public InternalResponse deleteStatusById(Status status);

	/**
* Delete all statuss.
*
* @return the internal response
*/
	public InternalResponse deleteAllStatus();

	/**
* Fetch all statuss.
*
* @return the list< status>
*/
	public InternalResultsResponse<Status> fetchAllStatus(Status  status);

	/**
* Fetch statuss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Status> fetchStatusByRequest(PagedInquiryRequest request);

}
