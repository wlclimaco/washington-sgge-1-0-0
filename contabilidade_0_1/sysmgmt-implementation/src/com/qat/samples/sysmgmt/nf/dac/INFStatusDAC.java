package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.NFStatus;

/**
 * The Interface INFStatusDAC.
 */
public interface INFStatusDAC
{

	/**
	 * Update nf status.
	 * 
	 * @param nfStatus the nf status
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateNFStatus(NFStatus nfStatus, InternalResultsResponse<?> response);

	/**
	 * Insert nf status.
	 * 
	 * @param nfStatus the nf status
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertNFStatus(NFStatus nfStatus, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete nf status.
	 * 
	 * @param nfStatus the nf status
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteNFStatus(NFStatus nfStatus, InternalResultsResponse<?> response);

	/**
	 * Fetch nf status by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NFStatus> fetchNFStatusByRequest(PagedInquiryRequest request);

}
