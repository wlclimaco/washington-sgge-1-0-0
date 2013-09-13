package com.sensus.dm.controller.process.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.process.bcf.IProcessSummaryBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemHistory;
import com.sensus.dm.common.process.model.ProcessItemHistoryStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;

/**
 * The Class ProcessSummaryBCFMockImpl.
 */
public class ProcessSummaryBCFMockImpl extends BaseMockImpl implements IProcessSummaryBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchAllDemandResponseSetup(com.sensus.dm.common.process.
	 * model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMProcess> epmProcessList = new ArrayList<DMProcess>();

			DMAction epmAction =
					new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP), processRequest
							.getFirstProcess().getFirstProcessItem().getDevice());

			epmProcessList.add(new DMProcess(epmAction));

			response.setProcesses(epmProcessList);
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchDemandResponseProgramParticipation(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMProcess> epmProcessList = new ArrayList<DMProcess>();
			List<ProcessItem> processItemList = new ArrayList<ProcessItem>();
			List<ProcessItemHistory> processHistoryList = new ArrayList<ProcessItemHistory>();

			ProcessItemHistory processHistory = new ProcessItemHistory();
			processHistory.setProcessItemHistoryStatusEnum(ProcessItemHistoryStatusEnum.EVENTCOMPLETED);

			processHistoryList.add(processHistory);
			processItemList.add(new ProcessItem(processHistoryList));
			epmProcessList.add(new DMProcess(processItemList));

			response.setProcesses(epmProcessList);
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchDemandResponseSummary(com.sensus.dm.common.process.model
	 * .request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMProcess> processes = new ArrayList<DMProcess>();
			DMProcess newProcess;

			int i = 1;

			for (DMProcess process : processRequest.getProcessList())
			{
				newProcess = new DMProcess();
				newProcess.setId(process.getId());
				newProcess.setDescription("Description Process " + process.getId());
				newProcess.setTotalSmartpoints(2 * i);
				newProcess.setFailedSmartpoints(1 * i);

				newProcess.setProcessItems(ProcessBCFMockImpl.getProcessItems(newProcess));

				processes.add(newProcess);
				i++;
			}

			response.setProcesses(processes);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessSummaryBCF#fetchProcessSendHanTextMessage(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcessSendHanTextMessage(ProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMProcess> processes = new ArrayList<DMProcess>();
			DMProcess newProcess;
			processRequest.getProcessList().size();
			int i = 1;

			for (DMProcess process : processRequest.getProcessList())
			{
				newProcess = new DMProcess();
				newProcess.setId(process.getId());
				newProcess.setDescription("Sen Han Text Message Process " + process.getId());
				newProcess.setTotalSmartpoints(2 * i);
				newProcess.setFailedSmartpoints(0);

				newProcess.setProcessItems(ProcessBCFMockImpl.getProcessItems(newProcess));

				newProcess.setAction(ProcessBCFMockImpl.getEPMAction(1, i));
				newProcess.setProcessStatusEnum(ProcessStatusEnum.COMMAND_SENT);

				processes.add(newProcess);
				i++;
			}

			response.setProcesses(processes);
			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
	}
}
