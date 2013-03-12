package com.sensus.mlc.wui.export;

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
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.wui.BaseController;




/**
 * The Class ExportFileAPIController.
 *
 * @author wSilva
 */
@Controller
@RequestMapping("/api/export")
public class ExportFileAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	private static final String MAP_DOWNLOAD = "/download";
	private static final String MAP_INITIATE = "/initiate";

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/** The smart point updater bcf. */
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ExportFileAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ExportFileAPIController";

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
	@Resource
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
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
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
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
	@Resource
	public void setSmartPointUpdaterBCF(ISmartPointUpdaterBCF smartPointUpdaterBCF)
	{
		this.smartPointUpdaterBCF = smartPointUpdaterBCF;
	}

	/**
	 * Download.
	 *
	 * @param fileRequest the file request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_DOWNLOAD, method = RequestMethod.POST)
	@ResponseBody
	public Response download(@RequestBody InquiryPaginationRequest fileRequest, HttpServletRequest request)
	{

		Response response = new Response();

		try
		{

			switch (((LightingControlRequest)fileRequest).getAction())
			{
				case "updateCSVDownloaded":
					response = (ProcessResponse)getProcessBCF().updateCSVDownloaded(new ProcessRequest());
					break;
				case "download":
					response = (InquiryProcessResponse)getProcessBCF().generateFileCSV(new InquiryProcessRequest());
				default:
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	/**
	 * Initiate.
	 *
	 * @param lightSelectionRequest the light selection request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_INITIATE, method = RequestMethod.POST)
	@ResponseBody
	public Response initiate(@RequestBody LightSelectionRequest lightSelectionRequest, HttpServletRequest request)
	{

		Response response = new Response();
		try
		{
			setUserContext(lightSelectionRequest, request);

			switch (((LightingControlRequest)lightSelectionRequest).getAction())
			{
				case "updateCSVDownloaded":
					ProcessRequest processRequest = new ProcessRequest();
					setUserContext(processRequest, request);

					response = (ProcessResponse)getProcessBCF().updateCSVDownloaded(processRequest);
					break;
				case "eventHistory":
					response = (ProcessResponse)getProcessBCF().insertCSVProcess(lightSelectionRequest);
					break;
				case "analytics":
					response = (ProcessResponse)getAnalyticsBCF().insertCSVProcess(lightSelectionRequest);
					break;
				case "ecoMode":
					response = (ProcessResponse)getEcoModeBCF().insertCSVProcess(lightSelectionRequest);
					break;
				case "smartPointUpdate":
					response = (ProcessResponse)getSmartPointUpdaterBCF().insertCSVProcess(lightSelectionRequest);
					break;
				case "generateFileCSV":
					response =
							(InquiryAnalyticsResponse)getAnalyticsBCF().generateFileCSV(
									(InquiryAnalyticsRequest)lightSelectionRequest);

					break;

				default:
					break;
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

}
