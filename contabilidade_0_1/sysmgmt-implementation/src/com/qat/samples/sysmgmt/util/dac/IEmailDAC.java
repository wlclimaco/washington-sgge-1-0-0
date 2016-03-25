package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.Email;

/**
 * The Interface IEmailDAC.
 */
public interface IEmailDAC
{

	public Integer updateEmail(Email email, InternalResultsResponse<?> response);

	/**
	 * Insert Email.
	 * 
	 * @param Email the Email
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEmail(Email email, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business Email.
	 * 
	 * @param Email the Email
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEmail(Email email, InternalResultsResponse<?> response);

	public InternalResultsResponse<Email> fetchEmailById(FetchByIdRequest request);

	public InternalResultsResponse<Email> fetchEmailByRequest(PagedInquiryRequest request);
}
