package com.sensus.mlc.wui.user.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;

/**
 * The Class UserSearchAjaxAction.
 */
@SuppressWarnings("serial")
/**
 * Action for searching users.
 * @author Alex Tiveron
 */
public class UserSearchAjaxAction extends ActionBase
{
	/**
	 * The logger for this class.
	 */
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * The object returned to the caller.
	 */
	private SearchJsonResult userSearchResult;

	/** The user bcf. */
	private IUserBCF userBCF;

	/** The response. */
	private Response response;

	/** The inquiry user request. */
	private InquiryUserRequest inquiryUserRequest;

	/**
	 * AJAX callback for searching groups. At this point, the search doesn't
	 * take any parameters. It populates <code>groupSearchResult</code> which is
	 * returned as JSON. Error messages are communicated via the JSON object.
	 * 
	 * @return always "SUCCESS"
	 */
	public String search()
	{

		getInquiryUserRequest().setUserContext(getUserContext());
		InquiryUserResponse response = getUserBCF().fetchAllUsers(getInquiryUserRequest());

		setResponse(response);

		return SUCCESS;
	}

	/**
	 * Gets the user search result.
	 * 
	 * @return the user search result
	 */
	public SearchJsonResult getUserSearchResult()
	{
		return userSearchResult;
	}

	/**
	 * Sets the user search result.
	 * 
	 * @param userSearchResult the new user search result
	 */
	public void setUserSearchResult(SearchJsonResult userSearchResult)
	{
		this.userSearchResult = userSearchResult;
	}

	/**
	 * Gets the user bcf.
	 * 
	 * @return the user bcf
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * Sets the user bcf.
	 * 
	 * @param userBCF the new user bcf
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * @return the inquiryUserRequest
	 */
	public InquiryUserRequest getInquiryUserRequest()
	{
		return inquiryUserRequest;
	}

	/**
	 * @param inquiryUserRequest the inquiryUserRequest to set
	 */
	public void setInquiryUserRequest(InquiryUserRequest inquiryUserRequest)
	{
		this.inquiryUserRequest = inquiryUserRequest;
	}
}