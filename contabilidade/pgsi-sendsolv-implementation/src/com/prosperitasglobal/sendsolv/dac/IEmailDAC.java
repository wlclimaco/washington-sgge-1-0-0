package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Email;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

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
	public Integer deleteBusinessEmail(Email email, InternalResultsResponse<?> response);

	/**
	 * Delete person Email.
	 *
	 * @param Email the Email
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonEmail(Email email, InternalResultsResponse<?> response);

	/**
	 * Fetch Email by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< Email>
	 */
	public InternalResultsResponse<Email> fetchEmailByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch Email by id.
	 *
	 * @param id the id
	 * @return the internal results response< Email>
	 */
	public InternalResultsResponse<Email> fetchEmailById(Integer id);

	public InternalResultsResponse<Email> fetchEmailByRequest(PagedInquiryRequest request);
}
