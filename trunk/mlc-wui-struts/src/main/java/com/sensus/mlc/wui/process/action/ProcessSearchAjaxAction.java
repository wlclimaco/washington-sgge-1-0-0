/*
 * 
 */
package com.sensus.mlc.wui.process.action;

import com.sensus.common.model.Response;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;
import com.sensus.mlc.wui.base.util.SummaryJsonResult;

/**
 * The Class ProcessSearchAjaxAction is called when table is rendering.
 * 
 * @author Raphael Constantino
 */

public class ProcessSearchAjaxAction extends ActionBase
{

	/** ************************ CONSTANTS ***********************. */

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;

	/** The Constant HOURS. */
	public static final String HOURS = "process.page.hours";

	/** The Constant MINUTES. */
	public static final String MINUTES = "process.page.minutes";

	/** The Constant SECONDS. */
	public static final String SECONDS = "process.page.seconds";

	/** The Constant DAYS. */
	public static final String DAYS = "process.page.days";

	/** The Constant EAST. */
	public static final String EAST = "process.page.east";

	/** The Constant TYPE. */
	public static final String TYPE = "event_type";

	/** The Constant HASH_DATE_INIT. */
	public static final String HASH_DATE_INIT = "di";

	/** The Constant HASH_DATE_END. */
	public static final String HASH_DATE_END = "de";

	/** The Constant HASH_DATE_TYPE. */
	public static final String HASH_DATE_TYPE = "dt";

	/** The Constant DAYS_END. */
	public static final String START_DATE = "total_days";

	/** The Constant START_DATE. */
	public static final String DAYS_END = "view_from";

	/** The Constant FILTER_USER. */
	public static final String FILTER_USER = "users";

	/** The Constant TOKEN. */
	public static final String PIPE = "[|]";

	/** The Constant TEXT_QUERY. */
	public static final String TEXT_QUERY = "query";

	/** The Constant HOUR_FORMAT. */
	public static final String HOUR_FORMAT = "HH:mm";

	/** The Constant MARKER. */
	public static final String MARKER = "a";

	/** The Constant SMTPROTECTED_KEY. */
	public static final String SMTPROTECTED_KEY = "smpProtected";

	/** The Constant SYNC_KEY. */
	public static final String SYNC_KEY = "sync";

	/** The Constant ASYNC_KEY. */
	public static final String ASYNC_KEY = "async";

	/** The Constant ABORTED_KEY. */
	public static final String ABORTED_KEY = "aborted";

	/** The Constant DEACTIVATED_KEY. */
	public static final String DEACTIVATED_KEY = "deactivated";

	/** The Constant MAINTENANCE_KEY. */
	public static final String MAINTENANCE_KEY = "maintenance";

	/** The Constant MONTH_FORMAT. */
	public static final String MONTH_FORMAT = "MMMMM";

	/** The Constant DAY_FORMAT. */
	public static final String DAY_FORMAT = "dd";

	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant DATE_FORMAT_HOUR. */
	public static final String DATE_FORMAT_HOUR = "M/d/yy h:mm:ss a";

	/** The Constant TODAY. */
	public static final String TODAY = "process.table.table.today";

	/** The Constant ERROR_COLD_NOT_SEARCH_PROCESS. */
	public static final String ERROR_COLD_NOT_SEARCH_PROCESS = "Could not search Process";

	/** The Constant SPACER. */
	public static final String SPACER = " ";

	/** ************************ PROPERTIES ***********************. */

	/** The long running process bcf. */
	private transient IProcessBCF processBCF;

	/** The long running process search result. */
	private SearchJsonResult processSearchResult;

	/** The id. */
	private Integer id;

	/** The success process. */
	private String successProcess;

	/** The success process percent. */
	private String successProcessPercent;

	/** The fail process. */
	private String failProcess;

	/** The fail process percent. */
	private String failProcessPercent;

	/** The completed time. */
	private String completedTime;

	/** The is equal user logged. */
	private Boolean isEqualUserLogged;

	/** The is monitored. */
	private boolean isMonitored;

	/** The process id. */
	private Integer processId;

	/** The file name. */
	private String fileName;

	/** The user. */
	private String user;

	/** The summary json result. */
	private SummaryJsonResult summaryJsonResult;

	/** The inquiry process request. */
	private InquiryProcessRequest inquiryProcessRequest;

	/** The process request. */
	private ProcessRequest processRequest;

	/** The light selection request. */
	private LightSelectionRequest lightSelectionRequest;

	/** The response. */
	private Response response;

	/**
	 * Search.
	 * 
	 * @return the string
	 */
	public String searchProcess()
	{
		try
		{
			// Call BCF Layer
			InquiryProcessResponse response = getProcessBCF().fetchProcesses(getInquiryProcessRequest());

			setResponse(response);

		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_COLD_NOT_SEARCH_PROCESS, ex);
			}
		}
		return SUCCESS;
	}

	/**
	 * Gets the process by id.
	 * 
	 * @return the process by id
	 */
	public String getProcessById()
	{

		try
		{

			ProcessResponse processResponse = getProcessBCF().fetchProcessById(getProcessRequest());

			setResponse(processResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search Process Id", e);
			}
			getProcessSearchResult().setResult(Constants.JSON_FAIL);
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

			ProcessResponse processResp = getProcessBCF().fetchMonitoredProcesses(getProcessRequest());

			setResponse(processResp);

		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_COLD_NOT_SEARCH_PROCESS, ex);
			}
		}
		return SUCCESS;
	}

	/**
	 * Summary process.
	 * 
	 * @return the string
	 */
	public String summaryProcess()
	{

		try
		{

			ProcessResponse response = getProcessBCF().fetchProcessById(getProcessRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not Process", e);
			}
		}

		return SUCCESS;
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

			ProcessResponse response = getProcessBCF().insertCSVProcess(getLightSelectionRequest());

			setResponse(response);
		}
		catch (Exception e)
		{
			getProcessSearchResult().setResult(Constants.JSON_FAIL);

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

			InquiryProcessResponse response = getProcessBCF().generateFileCSV(getInquiryProcessRequest());

			setResponse(response);
		}
		catch (Exception e)
		{
			getProcessSearchResult().setResult(Constants.JSON_FAIL);

		}
		return SUCCESS;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the process search result.
	 * 
	 * @return the process search result
	 */
	public SearchJsonResult getProcessSearchResult()
	{
		return processSearchResult;
	}

	/**
	 * Sets the process search result.
	 * 
	 * @param processSearchResult the new process search result
	 */
	public void setProcessSearchResult(SearchJsonResult processSearchResult)
	{
		this.processSearchResult = processSearchResult;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the success process.
	 * 
	 * @return the success process
	 */
	public String getSuccessProcess()
	{
		return successProcess;
	}

	/**
	 * Sets the success process.
	 * 
	 * @param successProcess the new success process
	 */
	public void setSuccessProcess(String successProcess)
	{
		this.successProcess = successProcess;
	}

	/**
	 * Gets the success process percent.
	 * 
	 * @return the success process percent
	 */
	public String getSuccessProcessPercent()
	{
		return successProcessPercent;
	}

	/**
	 * Sets the success process percent.
	 * 
	 * @param successProcessPercent the new success process percent
	 */
	public void setSuccessProcessPercent(String successProcessPercent)
	{
		this.successProcessPercent = successProcessPercent;
	}

	/**
	 * Gets the fail process.
	 * 
	 * @return the fail process
	 */
	public String getFailProcess()
	{
		return failProcess;
	}

	/**
	 * Sets the fail process.
	 * 
	 * @param failProcess the new fail process
	 */
	public void setFailProcess(String failProcess)
	{
		this.failProcess = failProcess;
	}

	/**
	 * Gets the fail process percent.
	 * 
	 * @return the fail process percent
	 */
	public String getFailProcessPercent()
	{
		return failProcessPercent;
	}

	/**
	 * Sets the fail process percent.
	 * 
	 * @param failProcessPercent the new fail process percent
	 */
	public void setFailProcessPercent(String failProcessPercent)
	{
		this.failProcessPercent = failProcessPercent;
	}

	/**
	 * Gets the completed time.
	 * 
	 * @return the completed time
	 */
	public String getCompletedTime()
	{
		return completedTime;
	}

	/**
	 * Sets the completed time.
	 * 
	 * @param completedTime the new completed time
	 */
	public void setCompletedTime(String completedTime)
	{
		this.completedTime = completedTime;
	}

	/**
	 * Sets the checks if is equal user logged.
	 * 
	 * @param isEqualUserLogged the new checks if is equal user logged
	 */
	public void setIsEqualUserLogged(Boolean isEqualUserLogged)
	{
		this.isEqualUserLogged = isEqualUserLogged;
	}

	/**
	 * Gets the checks if is equal user logged.
	 * 
	 * @return the checks if is equal user logged
	 */
	public Boolean getIsEqualUserLogged()
	{
		return isEqualUserLogged;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user the new user
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * Gets the summary json result.
	 * 
	 * @return the summaryJsonResult
	 */
	public SummaryJsonResult getSummaryJsonResult()
	{
		return summaryJsonResult;
	}

	/**
	 * Sets the summary json result.
	 * 
	 * @param summaryJsonResult the summaryJsonResult to set
	 */
	public void setSummaryJsonResult(SummaryJsonResult summaryJsonResult)
	{
		this.summaryJsonResult = summaryJsonResult;
	}

	/**
	 * Gets the inquiry process request.
	 * 
	 * @return the inquiryProcessRequest
	 */
	public InquiryProcessRequest getInquiryProcessRequest()
	{
		inquiryProcessRequest.setUserContext(getUserContext());
		return inquiryProcessRequest;
	}

	/**
	 * Sets the inquiry process request.
	 * 
	 * @param inquiryProcessRequest the inquiryProcessRequest to set
	 */
	public void setInquiryProcessRequest(InquiryProcessRequest inquiryProcessRequest)
	{
		this.inquiryProcessRequest = inquiryProcessRequest;
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
	 * Gets the checks if is monitored.
	 * 
	 * @return the isMonitored
	 */
	public boolean getIsMonitored()
	{
		return isMonitored;
	}

	/**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the isMonitored to set
	 */
	public void setIsMonitored(boolean isMonitored)
	{
		this.isMonitored = isMonitored;
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
	 * @return the processRequest
	 */
	public ProcessRequest getProcessRequest()
	{
		return processRequest;
	}

	/**
	 * @param processRequest the processRequest to set
	 */
	public void setProcessRequest(ProcessRequest processRequest)
	{
		this.processRequest = processRequest;
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
