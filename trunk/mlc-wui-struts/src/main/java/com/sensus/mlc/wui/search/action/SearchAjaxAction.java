package com.sensus.mlc.wui.search.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * Action handling Search-related Ajax callbacks.
 * 
 * @author Cristiane Cobo
 * 
 */
@SuppressWarnings("serial")
public class SearchAjaxAction extends ActionBase
{

	/**
	 * The logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SearchAjaxAction.class);
	/**
	 * The SmartPoint BCF object.
	 */
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/** The custom search request. */
	private CustomSearchRequest customSearchRequest;

	/** The response. */
	private Response response;

	/**
	 * Initiate delete search.
	 * 
	 * @return the string
	 */
	public String deleteSearch()
	{

		try
		{

			CustomSearchResponse response = getSmartPointUpdaterBCF().deleteCustomSearch(getCustomSearchRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error initiating delete search", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Gets the smart point updater bcf.
	 * 
	 * @return the smart point updater bcf
	 */
	public ISmartPointUpdaterBCF getSmartPointUpdaterBCF()
	{
		return smartPointUpdaterBCF;
	}

	/**
	 * Sets the smart point updater bcf.
	 * 
	 * @param smartPointUpdaterBCF the new smart point updater bcf
	 */
	public void setSmartPointUpdaterBCF(ISmartPointUpdaterBCF smartPointUpdaterBCF)
	{
		this.smartPointUpdaterBCF = smartPointUpdaterBCF;
	}

	/**
	 * @return the customSearchRequest
	 */
	public CustomSearchRequest getCustomSearchRequest()
	{
		return customSearchRequest;
	}

	/**
	 * @param customSearchRequest the customSearchRequest to set
	 */
	public void setCustomSearchRequest(CustomSearchRequest customSearchRequest)
	{
		this.customSearchRequest = customSearchRequest;
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

}
