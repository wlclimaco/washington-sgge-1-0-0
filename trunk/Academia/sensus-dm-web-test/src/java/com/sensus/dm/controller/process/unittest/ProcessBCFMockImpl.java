package com.sensus.dm.controller.process.unittest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class ProcessBCFMockImpl.
 */
public class ProcessBCFMockImpl extends BaseMockImpl implements IProcessBCF
{

	/**
	 * Get new EPM Action object instance.
	 * 
	 * @param actionId the action id
	 * @param actionCategoryId the action category id
	 * @return the EPM Action
	 */
	public static DMAction getEPMAction(Integer actionId, Integer actionCategoryId)
	{
		DMAction epmAction = new DMAction(actionId);
		ActionType actionType = new ActionType(ActionTypeEnum.enumForValue(actionCategoryId));

		epmAction.setActionType(actionType);

		return epmAction;
	}

	/**
	 * Gets the processes.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the processes
	 */
	public static List<DMProcess> getProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		List<DMProcess> processes = new ArrayList<DMProcess>();
		DMProcess process;

		// monitored instance
		boolean isMonitoredInstance = false;

		if (!ValidationUtil.isNull(inquiryProcessRequest.getProcesses()) &&
				inquiryProcessRequest.getProcesses().get(0).isMonitoredInstance())
		{
			isMonitoredInstance = true;
		}

		// page size
		int processesCount = inquiryProcessRequest.getStartRow();

		if (inquiryProcessRequest.getPageSize() != null)
		{
			processesCount += inquiryProcessRequest.getPageSize();
		}
		else
		{
			processesCount += 25;
		}

		// create processes
		for (int i = inquiryProcessRequest.getStartRow(); i < processesCount; i++)
		{
			process = new DMProcess();

			process.setId(i + 1);
			process.setDescription("Description Process " + (i + 1));
			process.setTotalSmartpoints(10 * i);
			process.setFailedSmartpoints(2 * i);

			if (i < (processesCount / 5))
			{
				process.setAction(getEPMAction(1, i));
				process.setProcessStatusEnum(ProcessStatusEnum.COMMAND_SENT);
			}
			else if (i < ((processesCount / 5) * 2))
			{
				process.setAction(getEPMAction(2, i));

				if (isMonitoredInstance)
				{
					process.setProcessStatusEnum(ProcessStatusEnum.COMPLETED);
				}
				else
				{
					process.setProcessStatusEnum(ProcessStatusEnum.SCHEDULED);
				}
			}
			else if (i < ((processesCount / 5) * 3))
			{
				process.setAction(getEPMAction(3, i));
				process.setProcessStatusEnum(ProcessStatusEnum.IN_PROCESS);
			}
			else if (i < ((processesCount / 5) * 4))
			{
				process.setAction(getEPMAction(4, i));
				process.setProcessStatusEnum(ProcessStatusEnum.ABORTED);
			}
			else
			{
				process.setAction(getEPMAction(5, i));
				process.setProcessStatusEnum(ProcessStatusEnum.COMPLETED);
			}

			processes.add(process);
		}

		return processes;
	}

	/**
	 * Gets the process items.
	 * 
	 * @param process the process
	 * @return the process items
	 */
	public static List<ProcessItem> getProcessItems(DMProcess process)
	{
		List<ProcessItem> processItemList = new ArrayList<ProcessItem>(process.getTotalSmartpoints());
		Device device;
		int deviceId = 1001;
		int i;

		// process items with status completed
		for (i = 0; i < (process.getTotalSmartpoints() - process.getFailedSmartpoints()); i++)
		{
			device = new Device(new Radio(BigInteger.valueOf(deviceId)), DeviceTypeEnum.ELECTRIC_METER);

			processItemList.add(new ProcessItem(device, ProcessItemStatusEnum.COMPLETED));
			deviceId++;
		}

		// process items with status failed
		for (i = 0; i < process.getFailedSmartpoints(); i++)
		{
			device = new Device(new Radio(BigInteger.valueOf(deviceId)), DeviceTypeEnum.ELECTRIC_METER);

			processItemList.add(new ProcessItem(device, ProcessItemStatusEnum.FAILED, "failed message"));
			deviceId++;
		}

		LCM lcm = new LCM(new Radio(new BigInteger("11759084")));
		processItemList.add(new ProcessItem(lcm, ProcessItemStatusEnum.COMPLETED));

		HanDevice hanDevice = new HanDevice(new Radio(new BigInteger("2153943262073407")));
		processItemList.add(new ProcessItem(hanDevice, ProcessItemStatusEnum.COMPLETED));

		return processItemList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(getProcesses(inquiryProcessRequest));
			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMProcess> processes = new ArrayList<DMProcess>();
			DMProcess newProcess;
			int processesCount = processRequest.getProcessList().size();
			int i = 1;

			for (DMProcess process : processRequest.getProcessList())
			{
				newProcess = new DMProcess();
				newProcess.setId(process.getId());
				newProcess.setDescription("Description Process " + process.getId());
				newProcess.setTotalSmartpoints(2 * i);
				newProcess.setFailedSmartpoints(1 * i);

				newProcess.setProcessItems(getProcessItems(newProcess));

				if (i < (processesCount / 5))
				{
					newProcess.setAction(getEPMAction(1, i));
					newProcess.setProcessStatusEnum(ProcessStatusEnum.COMMAND_SENT);
				}
				else if (i < ((processesCount / 5) * 2))
				{
					newProcess.setAction(getEPMAction(2, i));
					newProcess.setProcessStatusEnum(ProcessStatusEnum.COMPLETED);
				}
				else if (i < ((processesCount / 5) * 3))
				{
					newProcess.setAction(getEPMAction(3, i));
					newProcess.setProcessStatusEnum(ProcessStatusEnum.IN_PROCESS);
				}
				else if (i < ((processesCount / 5) * 4))
				{
					newProcess.setAction(getEPMAction(4, i));
					newProcess.setProcessStatusEnum(ProcessStatusEnum.ABORTED);
				}
				else
				{
					newProcess.setAction(getEPMAction(5, i));
					newProcess.setProcessStatusEnum(ProcessStatusEnum.COMPLETED);
				}

				processes.add(newProcess);
				i++;
			}

			response.setProcesses(processes);
			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchMonitoredProcess(com.sensus.dm.common.process.model.request
	 * .InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(getProcesses(inquiryProcessRequest));
			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.
	 * request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			HashMap<String, Integer> countMonitoredProcess = new HashMap<String, Integer>();
			countMonitoredProcess.put("count_monitored", 30);
			countMonitoredProcess.put("count_processing", 2);

			response.setCountMonitoredProcess(countMonitoredProcess);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#fetchTodayProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setProcesses(getProcesses(inquiryProcessRequest));
			return response;
		}

		return (InquiryProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#checkLinkStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse checkLinkStatus(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setLinkStatus(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchCommunicationSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCommunicationSummary(ProcessRequest processRequest)
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

				newProcess.setProcessItems(getProcessItems(newProcess));

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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchImportHanDeviceSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchImportHanDeviceSummary(ProcessRequest processRequest)
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

				newProcess.setProcessItems(getProcessItems(newProcess));

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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

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
				newProcess.setDescription("Scheduled Process " + process.getId());
				newProcess.setTotalSmartpoints(2 * i);
				newProcess.setFailedSmartpoints(0);

				newProcess.setProcessItems(getProcessItems(newProcess));

				newProcess.setAction(getEPMAction(1, i));
				newProcess.setProcessStatusEnum(ProcessStatusEnum.SCHEDULED);

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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.
	 * request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchDemandReadPingSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandReadPingSummary(ProcessRequest processRequest)
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

				newProcess.setProcessItems(getProcessItems(newProcess));

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
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#expireProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse updateProcessItemsToExpire(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse fetchAllProcessItems(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse fetchRelays(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse fetchRelaysByProcessId(ProcessRequest processRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
