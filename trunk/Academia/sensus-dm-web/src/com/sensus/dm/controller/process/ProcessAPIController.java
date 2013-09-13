package com.sensus.dm.controller.process;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.SearchTypeEnum;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.bcf.IProcessSummaryBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.controller.util.DMUtil;

/**
 * The Class ProcessAPIController.
 */
@Controller
@RequestMapping("/api/process")
public class ProcessAPIController extends BaseController
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ProcessAPIController";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant UPDATE. */
	private static final String UPDATE = "/update";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = "/fetchById";

	/** The Constant FETCH_SCHEDULED. */
	private static final String FETCH_SCHEDULED = "/fetchScheduled";

	/** The Constant FETCH_COUNT_MONITORED. */
	private static final String FETCH_COUNT_MONITORED = "/fetchCountMonitored";

	/** The Constant CHECK_RNI_STATUS. */
	private static final String CHECK_RNI_STATUS = "/checkRNIStatus";

	/** The Constant FETCH_HAN_TEXT_MESSAGE_INFO. */
	private static final String FETCH_HAN_TEXT_MESSAGE_INFO = "/fetchHanTextMessageInfo";

	/** The Constant EXPIRE. */
	private static final String EXPIRE = "/expire";

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "processID";

	/** The process BCF. */
	private IProcessBCF processBCF;

	/** The process summary bcf. */
	private IProcessSummaryBCF processSummaryBCF;

	/**
	 * Gets the process BCF.
	 * 
	 * @return the processBCF
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process BCF.
	 * 
	 * @param processBCF the processBCF to set
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the process summary bcf.
	 * 
	 * @return the process summary bcf
	 */
	public IProcessSummaryBCF getProcessSummaryBCF()
	{
		return processSummaryBCF;
	}

	/**
	 * Sets the process summary bcf.
	 * 
	 * @param processSummaryBCF the new process summary bcf
	 */
	@Resource
	public void setProcessSummaryBCF(IProcessSummaryBCF processSummaryBCF)
	{
		this.processSummaryBCF = processSummaryBCF;
	}

	/**
	 * Fetch Processes by Inquiry Process Request.
	 * 
	 * @param request the request
	 * @return Inquiry Process Response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody InquiryProcessRequest request)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			// Add user context to request
			addUserContextToRequest(request);

			request.setPreQueryCount(true);

			if (!ValidationUtil.isNull(request.getIsToday()) && request.getIsToday())
			{
				// Today page
				response = getProcessBCF().fetchTodayProcesses(request);
			}
			else if (!ValidationUtil.isNull(request.getProcesses()) &&
					request.getProcesses().get(FIRST).isMonitoredInstance())
			{
				// Recent Requests/Monitor Dialog
				response = getProcessBCF().fetchMonitoredProcess(request);
			}
			else
			{
				if (SearchTypeEnum.NETWORK_ADDRESS.equals(request.getProcessSearch().getSearchType()))
				{
					String networkAddress = testNetworkAddress(request.getProcessSearch().getSearchText());

					request.getProcessSearch().setSearchType(SearchTypeEnum.FLEXNET_ID);
					request.getProcessSearch().setSearchText(networkAddress);
				}

				response = getProcessBCF().fetchProcesses(request);
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
	 * Fetch Process by ID.
	 * 
	 * @param request the request
	 * @return Inquiry Process Response
	 */
	@RequestMapping(value = FETCH_BY_ID, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchById(@RequestBody Map<String, Integer> request)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			if (!ValidationUtil.isNull(request.get(PROCESS_ID)))
			{
				ProcessRequest processRequest = new ProcessRequest();

				addUserContextToRequest(processRequest);

				DMProcess process = new DMProcess(request.get(PROCESS_ID));

				List<DMProcess> processList = new ArrayList<DMProcess>();
				processList.add(process);

				processRequest.setProcessList(processList);

				response = getProcessBCF().fetchProcessById(processRequest);

				if (!ValidationUtil.isNull(response.getProcesses())
						&& !ValidationUtil.isNull(response.getProcesses().get(0).getProcessItems()))
				{
					for (ProcessItem processItem : response.getProcesses().get(0).getProcessItems())
					{
						DMUtil.convertEletricMacAddress(processItem.getDevice());
					}
				}
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
	 * Fetch process items by schedule.
	 * 
	 * @param request the request
	 * @return Process Response
	 */
	@RequestMapping(value = FETCH_SCHEDULED, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchScheduled(@RequestBody Map<String, Integer> request)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			if (!ValidationUtil.isNull(request.get(PROCESS_ID)))
			{
				ProcessRequest processRequest = new ProcessRequest();

				addUserContextToRequest(processRequest);

				DMProcess process = new DMProcess(request.get(PROCESS_ID));

				List<DMProcess> processList = new ArrayList<DMProcess>();
				processList.add(process);

				processRequest.setProcessList(processList);

				response = getProcessBCF().fetchProcessItemsBySchedule(processRequest);

				if (!ValidationUtil.isNull(response.getProcesses())
						&& !ValidationUtil.isNull(response.getProcesses().get(0).getProcessItems()))
				{
					for (ProcessItem processItem : response.getProcesses().get(0).getProcessItems())
					{
						DMUtil.convertEletricMacAddress(processItem.getDevice());
					}
				}
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
	 * Fetch HAN text message info.
	 * 
	 * @param request the request
	 * @return Inquiry Process Response
	 */
	@RequestMapping(value = FETCH_HAN_TEXT_MESSAGE_INFO, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchHanTextMessageInfo(@RequestBody Map<String, Integer> request)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			if (!ValidationUtil.isNull(request.get(PROCESS_ID)))
			{
				ProcessRequest processRequest = new ProcessRequest();
				addUserContextToRequest(processRequest);

				DMProcess process = new DMProcess(request.get(PROCESS_ID));

				List<DMProcess> processList = new ArrayList<DMProcess>();
				processList.add(process);

				processRequest.setProcessList(processList);

				response = getProcessSummaryBCF().fetchProcessSendHanTextMessage(processRequest);
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
	 * Fetch count monitored.
	 * 
	 * @return Process Response
	 */
	@RequestMapping(value = FETCH_COUNT_MONITORED, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchCountMonitored()
	{
		ProcessRequest processRequest = new ProcessRequest();
		ProcessResponse response = new ProcessResponse();

		try
		{
			addUserContextToRequest(processRequest);
			response = getProcessBCF().fetchCountMonitoredProcesses(processRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		// Set Current Time as UTC
		response.setProcessResponseTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));

		return response;
	}

	/**
	 * Check RNI.
	 * 
	 * @return Process Response
	 */
	@RequestMapping(value = CHECK_RNI_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public Response checkRni()
	{
		ProcessRequest request = new ProcessRequest();
		ProcessResponse response = new ProcessResponse();

		try
		{
			addUserContextToRequest(request);

			response = getProcessBCF().checkLinkStatus(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Update.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public Response update(@RequestBody ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessBCF().updateProcess(processRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Expire process.
	 * 
	 * @param requestMap the request map
	 * @return the response
	 */
	@RequestMapping(value = EXPIRE, method = RequestMethod.POST)
	@ResponseBody
	public Response expireProcess(@RequestBody ProcessRequest processRequest)
	{
		ProcessResponse processResponse = new ProcessResponse();

		try
		{

			addUserContextToRequest(processRequest);

			processResponse = getProcessBCF().updateProcessItemsToExpire(processRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;
	}

	/**
	 * Test network address.
	 * 
	 * @param networkAddress the network address
	 * @param device the device
	 */
	private String testNetworkAddress(String networkAddress)
	{
		BigInteger flexNet = DMUtil.convertMacAddress(networkAddress);

		if (ValidationUtil.isNull(flexNet))
		{
			return networkAddress;
		}

		return flexNet.toString();
	}
}