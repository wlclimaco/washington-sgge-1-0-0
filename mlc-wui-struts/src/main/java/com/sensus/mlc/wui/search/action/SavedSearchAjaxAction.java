package com.sensus.mlc.wui.search.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * The Class SavedSearchAjaxAction.
 */
@SuppressWarnings("serial")
/**
 * Action for searching saved.
 * @author Cristiane Cobo
 */
public class SavedSearchAjaxAction extends ActionBase
{

	/**
	 * The logger for this class.
	 */
	private final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * The SmartPoint BCF object.
	 */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The response. */
	private Response response;

	/** The inquiry pagination request. */
	private InquiryPaginationRequest inquiryPaginationRequest;

	/**
	 * AJAX callback for searching saved searchs. At this point, the search doesn't
	 * take any parameters. It populates <code>savedSearchResult</code> which is
	 * returned as JSON. Error messages are communicated via the JSON object.
	 * 
	 * @return always "SUCCESS"
	 */
	public String search()
	{

		try
		{

			InquiryCustomSearchResponse customSearchResponse =
					getSmartPointAccessorBCF().fetchAllCustomSearch(getInquiryPaginationRequest());

			setResponse(customSearchResponse);

		}
		catch (Exception e)
		{
			logger.error("Error searching for custom searches", e);
		}
		return SUCCESS;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

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