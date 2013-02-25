package com.sensus.mlc.wui.group.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * The Class GroupSearchAjaxAction.
 */
@SuppressWarnings("serial")
/**
 * Action for searching groups.
 * @author Anke Doerfel-Parker
 */
public class GroupSearchAjaxAction extends ActionBase
{
	/**
	 * The logger for this class.
	 */
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * The Group BCF object. Injected by Spring.
	 */
	private IGroupBCF groupBCF;

	/** The response. */
	private Response response;

	/** The inquiry pagination request. */
	private InquiryPaginationRequest inquiryPaginationRequest;

	/**
	 * AJAX callback for searching groups. At this point, the search doesn't
	 * take any parameters. It populates <code>groupSearchResult</code> which is
	 * returned as JSON. Error messages are communicated via the JSON object.
	 * 
	 * @return always "SUCCESS"
	 */
	public String search()
	{

		try
		{

			InquiryGroupResponse resp = getGroupBCF().fetchAllGroups(getInquiryPaginationRequest());

			setResponse(resp);

		}
		catch (Exception e)
		{
			logger.error("Error searching for groups", e);
		}
		return SUCCESS;
	}

	/**
	 * Set the Group BCF. Injected by Spring.
	 * 
	 * @param groupBCFIn the Group BCF
	 */
	public void setGroupBCF(IGroupBCF groupBCFIn)
	{
		groupBCF = groupBCFIn;
	}

	/**
	 * Get the Group BCF.
	 * 
	 * @return the Group BCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
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
	 * @return the request
	 */

	/**
	 * @return the inquiryPaginationRequest
	 */
	public InquiryPaginationRequest getInquiryPaginationRequest()
	{
		return inquiryPaginationRequest;
	}

	/**
	 * @param inquiryPaginationRequest the inquiryPaginationRequest to set
	 */
	public void setInquiryPaginationRequest(InquiryPaginationRequest inquiryPaginationRequest)
	{
		this.inquiryPaginationRequest = inquiryPaginationRequest;
	}

}