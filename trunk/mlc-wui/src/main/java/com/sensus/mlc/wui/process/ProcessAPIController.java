package com.sensus.mlc.wui.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

// TODO: Auto-generated Javadoc
/**
 * The Class DashBoardSearchAjaxAction is called when table is rendering.
 *
 * @author Lucas Oliveira
 */

/**
 * @author wsilva
 *
 */
@Controller
@RequestMapping("/api/process")
public class ProcessAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_FETCH_ALL. */
	private static final String MAP_FETCH_ALL = "/fetchAll";

	/** The Constant MAP_UPDATE. */
	private static final String MAP_UPDATE = "/update";

	/** The Constant MAP_CHECK_RNI_STATUS. */
	private static final String MAP_CHECK_RNI_STATUS = "/checkRNIStatus";

	/** The Constant MAP_RETRY. */
	private static final String MAP_RETRY = "/retry";

	/** The Constant MAP_ABORT. */
	private static final String MAP_ABORT = "/abort";

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant MONITORED. */
	private static final String MONITORED = "monitored";

	/** The Constant TABLE. */
	private static final String TABLE = "table";

	/** The Constant COUNT. */
	private static final String COUNT = "count";

	/** The Constant UPDATE_MONITOR_PROCESS. */
	private static final String UPDATE_MONITOR_PROCESS = "updateMonitorProcess";

	/** The Constant UPDATE_MONITOR_PROCESS. */
	private static final String UNMONITOR_PROCESS = "updateMonitoProcess";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ProcessAPIController";

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
	 * Fetch processes.
	 *
	 * @param inquiryProcessRequest the inquiry process request
	 * @param request the request
	 * @return the inquiry process response
	 */
	@RequestMapping(value = MAP_FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public InquiryProcessResponse fetchProcesses(@RequestBody InquiryProcessRequest inquiryProcessRequest,
			HttpServletRequest request)
	{

		InquiryProcessResponse inquiryProcessResponse = new InquiryProcessResponse();

		try
		{
			setUserContext(inquiryProcessRequest, request);
			inquiryProcessResponse = getProcessBCF().fetchProcesses(inquiryProcessRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryProcessResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryProcessResponse;

	}

	/**
	 * Fetch.
	 *
	 * @param mapRequest the map request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, Object> mapRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			ProcessRequest processRequest = new ProcessRequest();
			setUserContext(processRequest, request);

			String key = mapRequest.get(ACTION).toString();
			switch (key)
			{
				case ID:
					List<Process> processList = new ArrayList<Process>();
					Process process = new Process();
					process.setId(Integer.parseInt(mapRequest.get(ID).toString()));
					processList.add(process);
					processRequest.setProcessList(processList);

					processResponse = getProcessBCF().fetchProcessById(processRequest);
					break;

				case TABLE:

					// TODO
					processResponse = getProcessBCF().fetchMonitoredProcesses(processRequest);
					break;

				case COUNT:

					processResponse = getProcessBCF().fetchCountMonitoredProcesses(processRequest);
					break;

			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Unmonitor process.
	 *
	 * @param mapRequest the map request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse update(@RequestBody ProcessRequest processRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{

			setUserContext(processRequest, request);

			String key = ((LightingControlRequest)processRequest).getAction();
			switch (key)
			{
				case UPDATE_MONITOR_PROCESS:

					getProcessBCF().updateProcess(processRequest);
					break;

				case UNMONITOR_PROCESS:

					getProcessBCF().unmonitorProcess(processRequest);
					break;

				default:
					break;
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Fetch rni link status.
	 *
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = MAP_CHECK_RNI_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse fetchRniLinkStatus(HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			ProcessRequest processRequest = new ProcessRequest();
			setUserContext(processRequest, request);
			processResponse = getProcessBCF().fetchRniLinkStatus(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Retry.
	 *
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = MAP_RETRY, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse retry(HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			ProcessRequest processRequest = new ProcessRequest();
			setUserContext(processRequest, request);
			processResponse = getProcessBCF().retryProcess(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Abort.
	 *
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = MAP_ABORT, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse abort(@RequestBody ProcessRequest processRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			setUserContext(processRequest, request);
			processResponse = getProcessBCF().abortProcess(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

}
