package com.sensus.mlc.wui.systemsettings.tags.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;

/**
 * Action providing the callback for the tag search.
 * 
 * @author Raphael Constantino
 */
@SuppressWarnings("serial")
public class TagSearchAjaxAction extends ActionBase
{

	/**
	 * The logger for this class.
	 */
	private Log logger = LogFactory.getLog(this.getClass());

	/** The system settings result. */
	private SearchJsonResult tagResult;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The response. */
	private Response response;

	/** The inquiry tag request. */
	private InquiryTagRequest inquiryTagRequest;

	/**
	 * Search.
	 * 
	 * @return the string
	 */
	public String search()
	{
		// setTagResult(new SearchJsonResult());
		try
		{
			// Map<String, String[]> parameters = this.getParameters();

			// InquiryTagRequest request = new InquiryTagRequest();

			// ADDS tenent and user context to request
			getInquiryTagRequest().setUserContext(getUserContext());
			// request.getSortExpressions().add(ActionPaginationUtil.getSortExpressionTag(parameters));

			// request.setPageSize(ActionPaginationUtil.getPageDisplaySize(parameters));

			// First page pagination starts at index 0
			// request.setStartRow(ActionPaginationUtil.getCurrentDisplayStartIndex(parameters));

			// Send the total amount received from the first page pagination back to the server.(round trip)
			// this tells the server to minimize the effort and not do a full count again.
			// request.setEndRow(ActionPaginationUtil.getTotalRowsRecord(parameters));

			InquiryTagResponse response = getTagBCF().fetchAllTags(getInquiryTagRequest());

			setResponse(response);
			/*
			 * ResultUtil.setMessages(getTagResult(), response);
			 * if (response.isOperationSuccess())
			 * {
			 * List<String[]> records = new ArrayList<String[]>();
			 * String[] record;
			 * if (!ValidationUtil.isNullOrEmpty(response.getTags()))
			 * {
			 * for (Tag tag : response.getTags())
			 * {
			 * record = new String[] {
			 * String.valueOf(tag.getId()), // Tag Id
			 * tag.getName(), // Tag Name
			 * SensusStringUtil.createToString(tag.getAutoGroup()),// Auto group
			 * String.valueOf(tag.getTotalSmartpoints()), // Smartpoint Count
			 * String.valueOf(tag.getDate()), // Date
			 * // Added
			 * };
			 * records.add(record);
			 * }
			 * }
			 * getTagResult().setAaData(records.toArray(new String[records.size()][]));
			 * // set total amount of rows for pagination
			 * if (!ValidationUtil.isNull(response.getResultsSetInfo()))
			 * {
			 * getTagResult().setiTotalDisplayRecords(
			 * response.getResultsSetInfo().getTotalRowsAvailable());
			 * }
			 * }
			 */

		}
		catch (Exception e)
		{
			getTagResult().setResult(Constants.JSON_FAIL);
			logger.error("Error searching for tags", e);
		}
		return SUCCESS;
	}

	/**
	 * Gets the tag result.
	 * 
	 * @return the tag result
	 */
	public SearchJsonResult getTagResult()
	{
		return tagResult;
	}

	/**
	 * Sets the tag result.
	 * 
	 * @param tagResult the new tag result
	 */
	public void setTagResult(SearchJsonResult tagResult)
	{
		this.tagResult = tagResult;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the new tag bcf
	 */
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
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
	 * @return the inquiryTagRequest
	 */
	public InquiryTagRequest getInquiryTagRequest()
	{
		return inquiryTagRequest;
	}

	/**
	 * @param inquiryTagRequest the inquiryTagRequest to set
	 */
	public void setInquiryTagRequest(InquiryTagRequest inquiryTagRequest)
	{
		this.inquiryTagRequest = inquiryTagRequest;
	}
}
