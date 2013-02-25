package com.sensus.mlc.wui.smartpoint.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.ActionPaginationUtil;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SearchParametersUtil;

/**
 * The Class SmartpointSearchAjaxAction is called when table is rendering.
 * 
 * @author Anke Doerfel-Parker
 */

public class SmartpointSearchAjaxAction extends ActionBase
{

	/** CONSTANTS *. */

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;

	/**
	 * The parameter name for the "tab" search parameter. Part of request parameter stack.
	 */
	public static final String HASH_TAB = "tb";

	/** The Constant HASH_LONG_RUNNING_PROCESS. */
	public static final String HASH_LONG_RUNNING_PROCESS = "process";

	/** The Constant HASH_SEARCH_VALUE. */
	public static final String HASH_SEARCH_VALUE = "query";

	/** The Constant PIPE. */
	public static final String PIPE = "[|]";

	/** The Constant COMA. */
	public static final String COMA = ",";

	/* Response Parameters */
	/**
	 * The result object that will be returned as JSON to the caller.
	 */
	private SearchJsonResult smartpointSearchResult;

	/* Internal Fields */
	/**
	 * The logger for this class.
	 */
	private static transient Log LOG = LogFactory.getLog(SmartpointSearchAjaxAction.class);

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The smart point updater bcf. */
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/** The list smartpoints ids. */
	private String listSmartpointsIds;

	/** The is monitored. */
	private Boolean isMonitored;

	/** The process id. */
	private Integer processId;

	/** The file name. */
	private String fileName;

	/** The response. */
	private Response response;

	/** The inquiry light request. */
	private InquiryLightRequest inquiryLightRequest;

	/** The light selection request. */
	private LightSelectionRequest lightSelectionRequest;

	/** The list column. */
	private String listColumn;

	/**
	 * Search SmartPoints. This method will take into account the request parameters and populate the search result
	 * accordingly.
	 * 
	 * @param parameters the parameters
	 * @return always "SUCCESS"
	 */

	private InquiryLightRequest fillInquiryLightRequest(Map<String, String[]> parameters)
	{

		InquiryLightRequest request = new InquiryLightRequest(getUserContext());

		request.setSearch(SearchParametersUtil.getSearchLight(parameters));

		request.setSortExpression(ActionPaginationUtil.getMLCSortExpression(parameters));

		request.setPageSize(ActionPaginationUtil.getPageDisplaySize(parameters));

		// First page pagination starts at index 0
		request.setStartRow(ActionPaginationUtil.getCurrentDisplayStartIndex(parameters));

		// Send the total amount received from the first page pagination back to the server.(round trip)
		// this tells the server to minimize the effort and not do a full count again.
		request.setEndRow(ActionPaginationUtil.getTotalRowsRecord(parameters));

		Integer longRunningProcess = null;
		if (parameters.containsKey(HASH_LONG_RUNNING_PROCESS))
		{
			if (!ValidationUtil.isNullOrEmpty(parameters.get(HASH_LONG_RUNNING_PROCESS)[0]))
			{
				String[] sId = parameters.get(HASH_LONG_RUNNING_PROCESS)[0].split(PIPE);
				longRunningProcess = Integer.parseInt(sId[0]);
			}
		}

		request.setProcessId(longRunningProcess);

		return request;
	}

	/**
	 * Insert process.
	 * 
	 * @return the string
	 */
	public String insertProcess()
	{

		try
		{

			ProcessResponse lightResp = getSmartPointUpdaterBCF().insertCSVProcess(getLightSelectionRequest());
			setResponse(lightResp);

		}
		catch (Exception e)
		{
			getSmartpointSearchResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Generate file csv.
	 * 
	 * @return the string
	 */
	public String generateFileCSV()
	{

		try
		{

			InquiryLightResponse lightResp = getSmartPointAccessorBCF().generateFileCSV(getInquiryLightRequest());
			setResponse(lightResp);
		}
		catch (Exception e)
		{
			getSmartpointSearchResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Generate file csv History.
	 * 
	 * @return the string
	 */
	public String generateFileCSVHistory()
	{

		try
		{

			InquiryLightResponse lightResp =
					getSmartPointAccessorBCF().generateLightHistoryFileCSV(getInquiryLightRequest());
			setResponse(lightResp);
		}
		catch (Exception e)
		{
			getSmartpointSearchResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Search.
	 * 
	 * @return the string
	 */
	public String search()
	{
		try
		{
			InquiryLightResponse inquiryLightResponse =
					getSmartPointAccessorBCF().fetchAllLights(getInquiryLightRequest());
			setResponse(inquiryLightResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search SmartPoints", ex);
			}
		}

		return SUCCESS;
	}

	/**
	 * Set the Smartpoint Search Result Object.
	 * 
	 * @param smartPointSearchResult the empty Smartpoint Search Result Object
	 */
	public void setSmartpointSearchResult(
			SearchJsonResult smartPointSearchResult)
	{
		smartpointSearchResult = smartPointSearchResult;
	}

	/**
	 * Get the Smartpoint Search Result Object.
	 * 
	 * @return the Smartpoint Search Result Object
	 */
	public SearchJsonResult getSmartpointSearchResult()
	{
		return smartpointSearchResult;
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
	 * Gets the list smartpoints ids.
	 * 
	 * @return the list smartpoints ids
	 */
	public String getListSmartpointsIds()
	{
		return listSmartpointsIds;
	}

	/**
	 * Sets the list smartpoints ids.
	 * 
	 * @param listSmartpointsIds the new list smartpoints ids
	 */
	public void setListSmartpointsIds(String listSmartpointsIds)
	{
		this.listSmartpointsIds = listSmartpointsIds;
	}

	/**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the new checks if is monitored
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		this.isMonitored = isMonitored;
	}

	/**
	 * Gets the checks if is monitored.
	 * 
	 * @return the checks if is monitored
	 */
	public Boolean getIsMonitored()
	{
		return isMonitored;
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
	 * Gets the inquiry light request.
	 * 
	 * @return the inquiryLightRequest
	 */
	public InquiryLightRequest getInquiryLightRequest()
	{
		inquiryLightRequest.setUserContext(getUserContext());
		return inquiryLightRequest;
	}

	/**
	 * Sets the inquiry light request.
	 * 
	 * @param inquiryLightRequest the inquiryLightRequest to set
	 */
	public void setInquiryLightRequest(InquiryLightRequest inquiryLightRequest)
	{
		this.inquiryLightRequest = inquiryLightRequest;
	}

	/**
	 * Gets the list column.
	 * 
	 * @return the listColumn
	 */
	public String getListColumn()
	{
		return listColumn;
	}

	/**
	 * Sets the list column.
	 * 
	 * @param listColumn the listColumn to set
	 */
	public void setListColumn(String listColumn)
	{
		this.listColumn = listColumn;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the processId
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * @return the lightSelectionRequest
	 */
	public LightSelectionRequest getLightSelectionRequest()
	{
		lightSelectionRequest.setUserContext(getUserContext());
		return lightSelectionRequest;
	}

	/**
	 * @param lightSelectionRequest the lightSelectionRequest to set
	 */
	public void setLightSelectionRequest(LightSelectionRequest lightSelectionRequest)
	{
		this.lightSelectionRequest = lightSelectionRequest;
	}

}
