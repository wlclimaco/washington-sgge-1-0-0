package com.sensus.dm.controller.summary;

import java.util.ArrayList;
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
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.bcf.IProcessSummaryBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMUtil;

/**
 * The Class SummaryAPIController.
 */
@Controller
@RequestMapping("/api/summary")
public class SummaryAPIController extends BaseController
{

	/** The Constant DEMAND_READ. */
	private static final String DEMAND_READ = "demandRead";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SummaryAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "SummaryAPIController";

	/** The Constant DEMAND_RESPONSE. */
	private static final String DEMAND_RESPONSE = "demandResponse";

	/** The Constant COMMUNICATION_SUMMARY. */
	private static final String COMMUNICATION_SUMMARY = "communicationSummary";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant IMPORT_HAN. */
	private static final String IMPORT_HAN = "importHan";

	/** The Constant SETUP_DEMAND_RESPONSE. */
	private static final String SETUP_DEMAND_RESPONSE = "setupDemandResponse";

	/** The Constant NAME. */
	private static final String TYPE = "type";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The process summary bcf. */
	private IProcessSummaryBCF processSummaryBCF;

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
	 * Fetch.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, String> request)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			ProcessRequest processRequest = new ProcessRequest();

			// ADD user context to request
			addUserContextToRequest(processRequest);

			DMProcess process = new DMProcess(Integer.parseInt(request.get(ID)));
			List<DMProcess> processList = new ArrayList<DMProcess>();
			processList.add(process);

			processRequest.setProcessList(processList);
			processRequest.setProcessId(Integer.parseInt(request.get(ID)));

			// Fetch for ImportHan tab
			if (IMPORT_HAN.equals(request.get(TYPE)))
			{
				response = getProcessBCF().fetchImportHanDeviceSummary(processRequest);
			}

			// Fetch for CommunicationSummary Tab
			else if (COMMUNICATION_SUMMARY.equals(request.get(TYPE)))
			{
				response = getProcessBCF().fetchCommunicationSummary(processRequest);
			}

			// Fetch for DemandResponse Tab
			else if (DEMAND_RESPONSE.equals(request.get(TYPE)))
			{
				response = getProcessSummaryBCF().fetchDemandResponseSummary(processRequest);
			}

			// Fetch for Demand Read Tab
			else if (DEMAND_READ.equals(request.get(TYPE)))
			{
				response = getProcessBCF().fetchDemandReadPingSummary(processRequest);
			}

			// Fetch for Setup Demand Response Tab
			else if (SETUP_DEMAND_RESPONSE.equals(request.get(TYPE)))
			{
				response = getProcessBCF().fetchRelaysByProcessId(processRequest);
			}

			// Convert to MacAddress
			if (!ValidationUtil.isNull(response.getProcesses()))
			{
				for (DMProcess epmProcess : response.getProcesses())
				{
					if (!ValidationUtil.isNull(epmProcess.getProcessItems()))
					{
						for (ProcessItem processItem : epmProcess.getProcessItems())
						{
							if (!ValidationUtil.isNull(processItem.getDevice()))
							{
								DMUtil.convertEletricMacAddress(processItem.getDevice());
							}
						}
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
}