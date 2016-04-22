package com.qat.samples.sysmgmt.bar.Email;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface EmailBAR.. (Data Access Component - DAC)
 */
public interface IEmailBAR
{

	/**
	 * Fetch email by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Email fetchEmailById(FetchByIdRequest request);

	/**
* Insert email.
*
* @param email the email
*
* @return the internal response
*/
	public InternalResponse insertEmail(Email email);

	/**
* Update email.
*
* @param email the email
*
* @return the internal response
*/
	public InternalResponse updateEmail(Email email);

	/**
* Delete email.
*
* @param email the email
*
* @return the internal response
*/
	public InternalResponse deleteEmailById(Email email);

	/**
* Delete all emails.
*
* @return the internal response
*/
	public InternalResponse deleteAllEmails();

	/**
* Fetch all emails.
*
* @return the list< email>
*/
	public InternalResultsResponse<Email> fetchAllEmails(Email  email);

	/**
* Fetch emails by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Email> fetchEmailsByRequest(PagedInquiryRequest request);

}
