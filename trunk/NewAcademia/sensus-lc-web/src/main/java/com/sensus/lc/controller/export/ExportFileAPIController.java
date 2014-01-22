package com.sensus.lc.controller.export;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.analytics.bcf.IAnalyticsBCF;
import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.ecomode.bcf.IEcoModeBCF;
import com.sensus.lc.ecomode.model.request.EcoModeCSVRequest;
import com.sensus.lc.light.bcf.ILightCSVBCF;
import com.sensus.lc.light.model.request.LightCSVRequest;
import com.sensus.lc.light.model.request.LightHistoryCSVRequest;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class ExportFileAPIController.
 *
 * @author emaia
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/export")
public class ExportFileAPIController extends BaseController
{

	/** The Constant GENERATE_LIGHT_CSV. */
	private static final String GENERATE_LIGHT_CSV = "/generateLightCSV";

	/** The Constant INSERT_CSV_PROCESS. */
	private static final String INSERT_CSV_PROCESS = "/insertCsvProcess";

	/** The Constant GENERATE_PROCESS_CSV. */
	public static final String GENERATE_PROCESS_CSV = "/generateProcessCSV";

	/** The Constant GENERATE_LIGHT_HISTORY_CSV. */
	public static final String GENERATE_LIGHT_HISTORY_CSV = "/generateLightHistoryCSV";

	/** The Constant GENERATE_ECO_MODE_CSV. */
	public static final String GENERATE_ECO_MODE_CSV = "/generateEcoModeCSV";

	/** The Constant GENERATE_SUMMARY_CSV. */
	public static final String GENERATE_SUMMARY_CSV = "/generateSummaryCSV";

	/** The Constant GENERATE_ANALYTICS_CSV. */
	public static final String GENERATE_ANALYTICS_CSV = "/generateAnalytics";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ExportFileAPIController";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ExportFileAPIController.class);

	/** The process bcf impl. */
	private IProcessBCF processBCF;

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/** The light csvbcf. */
	private ILightCSVBCF lightCSVBCF;

	/**
	 * Gets the light csvbcf.
	 * 
	 * @return the light csvbcf
	 */
	public ILightCSVBCF getLightCSVBCF()
	{
		return lightCSVBCF;
	}

	/**
	 * Sets the light csvbcf.
	 * 
	 * @param lightCSVBCF the new light csvbcf
	 */
	@Resource
	public void setLightCSVBCF(ILightCSVBCF lightCSVBCF)
	{
		this.lightCSVBCF = lightCSVBCF;
	}

	/**
	 * Gets the eco mode bcf.
	 * 
	 * @return the eco mode bcf
	 */
	public IEcoModeBCF getEcoModeBCF()
	{
		return ecoModeBCF;
	}

	/**
	 * Sets the eco mode bcf.
	 * 
	 * @param ecoModeBCF the new eco mode bcf
	 */
	@Resource
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the processBCFImpl
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
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the analytics bcf.
	 * 
	 * @return the analyticsBCF
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return analyticsBCF;
	}

	/**
	 * Sets the analytics bcf.
	 * 
	 * @param analyticsBCF the analyticsBCF to set
	 */
	@Resource
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
	}

	/**
	 * Generate light csv.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_LIGHT_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateLightCSV(@RequestBody LightCSVRequest lightCSVRequest,
			HttpServletRequest request)
	{

		CSVResponse response = new CSVResponse();
		try
		{
			setUserContext(lightCSVRequest, request);
			response = getLightCSVBCF().generateLightDetailFileCSV(lightCSVRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate process csv.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_PROCESS_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateProcessCSV(@RequestBody ProcessCSVRequest processCSVRequest,
			HttpServletRequest request)
	{

		CSVResponse response = new CSVResponse();
		try
		{
			setUserContext(processCSVRequest, request);
			response = getProcessBCF().generateFileCSV(processCSVRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate light history csv.
	 * 
	 * @param lightHistoryCSVRequest the light history csv request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_LIGHT_HISTORY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateLightHistoryCSV(@RequestBody LightHistoryCSVRequest lightHistoryCSVRequest,
			HttpServletRequest request)
	{

		CSVResponse response = new CSVResponse();
		try
		{
			setUserContext(lightHistoryCSVRequest, request);
			response = getLightCSVBCF().generateLightHistoryFileCSV(lightHistoryCSVRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate process csv.
	 * 
	 * @param inquiryEcoModeRequest the inquiry eco mode request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_ECO_MODE_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateEcoModeCSV(@RequestBody EcoModeCSVRequest ecoModeCSVRequest,
			HttpServletRequest request)
	{

		CSVResponse csvResponse = new CSVResponse();
		try
		{
			setUserContext(ecoModeCSVRequest, request);
			csvResponse = getEcoModeBCF().generateFileCSV(ecoModeCSVRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, csvResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return csvResponse;
	}

	/**
	 * Generate summary csv.
	 * 
	 * @param processRequest the process request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateSummaryCSV(@RequestBody ProcessRequest processRequest,
			HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();
		try
		{
			setUserContext(processRequest, request);
			processResponse = getProcessBCF().generateSummaryFileCSV(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return processResponse;
	}

	/**
	 * Generate light csv.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_ANALYTICS_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateAnalyticsCSV(@RequestBody AnalyticsCSVRequest analyticsCSVRequest,
			HttpServletRequest request)
	{

		CSVResponse csvResponse = new CSVResponse();

		try
		{
			setUserContext(analyticsCSVRequest, request);
			csvResponse = getAnalyticsBCF().generateFileCSV(analyticsCSVRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, csvResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return csvResponse;
	}

	/**
	 * Insert CSV process.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_CSV_PROCESS, method = RequestMethod.POST)
	@ResponseBody
	public Response insertCsvProcess(HttpServletRequest request)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(getUserContext(request));

			response = getProcessBCF().insertCSVProcess(inquiryPaginationRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}
}