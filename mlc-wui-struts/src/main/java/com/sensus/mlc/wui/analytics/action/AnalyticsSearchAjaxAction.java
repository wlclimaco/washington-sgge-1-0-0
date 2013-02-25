package com.sensus.mlc.wui.analytics.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.GraphicJsonResult;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;

/**
 * The Class AnalyticsSearchAjaxAction is called when table is rendering.
 * 
 * @author Raphael Constantino
 */

@SuppressWarnings("serial")
public class AnalyticsSearchAjaxAction extends ActionBase
{

	/** CONSTANTS *. */

	/** The Constant HASH_TYPE. */
	public static final String HASH_TYPE = "ty";

	/** The Constant HASH_GROUP. */
	public static final String HASH_GROUP = "g";

	/** The Constant HASH_DATE_INIT. */
	public static final String HASH_DATE_INIT = "di";

	/** The Constant HASH_DATE_END. */
	public static final String HASH_DATE_END = "de";

	/** The Constant HASH_DATE_TYPE. */
	public static final String HASH_DATE_TYPE = "dt";

	/** The Constant FETCH_ERROR_MESSAGE. */
	public static final String FETCH_ERROR_MESSAGE = "Error searching for groups";

	/** The Constant ALL. */
	private static final String ALL = "all";

	/** The Constant ALL_GROUPS. */
	private static final String ALL_LIGHTS = "analytics.page.allLights";

	/** The Constant THREE. */
	public static final Integer THREE = 3;

	/** The Constant FOUR. */
	public static final Integer FOUR = 4;

	/** The Constant FIVE. */
	public static final Integer FIVE = 5;

	/** The Constant SIX. */
	public static final Integer SIX = 6;

	/** The Constant TEN. */
	public static final Integer TEN = 10;

	/** The Constant TWENTY_FOUR. */
	public static final Integer TWENTY_FOUR = 24;

	/** The Constant SIXTY. */
	public static final Integer SIXTY = 60;

	/** The Constant ONE_THOUUSAND. */
	public static final Integer ONE_THOUUSAND = 1000;

	/** The Constant THREE_DAYS. */
	public static final Integer THREE_DAYS = 2;

	/** The Constant ONE_WEEK. */
	public static final Integer ONE_WEEK = 6;

	/** The Constant ONE_MONTH. */
	public static final Integer ONE_MONTH = 29;

	/** The Constant THREE_MONTHS. */
	public static final Integer THREE_MONTHS = 89;

	/** The Constant ONE_YEAR. */
	public static final Integer ONE_YEAR = 364;

	/** The Constant DAY_GRAPHIC_FORMAT. */
	public static final String DAY_GRAPHIC_FORMAT = "EEE, MMM. d";

	/** The Constant FULL_YEAR_GRAPHIC_FORMAT. */
	public static final String FULL_YEAR_GRAPHIC_FORMAT = "MMM yyyy";

	/** The Constant YEAR_GRAPHIC_FORMAT. */
	public static final String YEAR_GRAPHIC_FORMAT = "MMM";

	/**
	 * The logger for this class.
	 */
	private final Log logger = LogFactory.getLog(this.getClass());

	/** The lc help. */
	private transient LCHelp lcHelp;

	/** The analytics search result. */
	private SearchJsonResult analyticsSearchResult;

	/** The analytics graphic result. */
	private GraphicJsonResult analyticsGraphicResult;

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The is monitored. */
	private Boolean isMonitored;

	/** The process id. */
	private Integer processId;

	/** The file name. */
	private String fileName;

	/** The inquiry analytics request. */
	private InquiryAnalyticsRequest inquiryAnalyticsRequest;

	/** The analytics request. */
	private AnalyticsRequest analyticsRequest;

	/** The light selection request. */
	private LightSelectionRequest lightSelectionRequest;

	/** The response. */
	private Response response;

	/**
	 * Search graphic.
	 * 
	 * @return the string
	 */
	public String searchGraphic()
	{
		try
		{
			AnalyticsResponse analyticsResp = getAnalyticsBCF().fetchAllAnalyticsByDate(getAnalyticsRequest());
			setResponse(analyticsResp);

		}
		catch (Exception e)
		{
			logger.error(FETCH_ERROR_MESSAGE, e);
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
			ProcessResponse analyticsResp = getAnalyticsBCF().insertCSVProcess(getLightSelectionRequest());
			setResponse(analyticsResp);
		}
		catch (Exception e)
		{
			logger.error(FETCH_ERROR_MESSAGE, e);
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

			InquiryAnalyticsResponse analyticsResp = getAnalyticsBCF().generateFileCSV(getInquiryAnalyticsRequest());
			setResponse(analyticsResp);
		}
		catch (Exception e)
		{
			getAnalyticsSearchResult().setResult(Constants.JSON_FAIL);
			logger.error(FETCH_ERROR_MESSAGE, e);
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

			InquiryAnalyticsResponse analyticsResp =
					getAnalyticsBCF().fetchAllAnalyticsByGroup(getInquiryAnalyticsRequest());

			setResponse(analyticsResp);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				logger.error(FETCH_ERROR_MESSAGE, e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Gets the analytics search result.
	 * 
	 * @return the analytics search result
	 */
	public SearchJsonResult getAnalyticsSearchResult()
	{
		return analyticsSearchResult;
	}

	/**
	 * Sets the analytics search result.
	 * 
	 * @param analyticsSearchResult the new analytics search result
	 */
	public void setAnalyticsSearchResult(SearchJsonResult analyticsSearchResult)
	{
		this.analyticsSearchResult = analyticsSearchResult;
	}

	/**
	 * Gets the analytics graphic result.
	 * 
	 * @return the analytics graphic result
	 */
	public GraphicJsonResult getAnalyticsGraphicResult()
	{
		return analyticsGraphicResult;
	}

	/**
	 * Sets the analytics graphic result.
	 * 
	 * @param analyticsGraphicResult the new analytics graphic result
	 */
	public void setAnalyticsGraphicResult(GraphicJsonResult analyticsGraphicResult)
	{
		this.analyticsGraphicResult = analyticsGraphicResult;
	}

	/**
	 * Gets the analytics bcf.
	 * 
	 * @return the analytics bcf
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return analyticsBCF;
	}

	/**
	 * Sets the analytics bcf.
	 * 
	 * @param analyticsBCF the new analytics bcf
	 */
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
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
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the inquiry analytics request.
	 * 
	 * @return the inquiryAnalyticsRequest
	 */
	public InquiryAnalyticsRequest getInquiryAnalyticsRequest()
	{
		return inquiryAnalyticsRequest;
	}

	/**
	 * Sets the inquiry analytics request.
	 * 
	 * @param inquiryAnalyticsRequest the inquiryAnalyticsRequest to set
	 */
	public void setInquiryAnalyticsRequest(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		this.inquiryAnalyticsRequest = inquiryAnalyticsRequest;
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
	 * Gets the analytics request.
	 * 
	 * @return the analyticsRequest
	 */
	public AnalyticsRequest getAnalyticsRequest()
	{
		return analyticsRequest;
	}

	/**
	 * Sets the analytics request.
	 * 
	 * @param analyticsRequest the analyticsRequest to set
	 */
	public void setAnalyticsRequest(AnalyticsRequest analyticsRequest)
	{
		this.analyticsRequest = analyticsRequest;
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
