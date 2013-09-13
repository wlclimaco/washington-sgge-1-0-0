package com.sensus.dm.common.process.bcl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.action.model.GenerateCSVFileAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseParticipationEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class MockProcessBCL.
 * 
 * @author QAT Global.
 */
public class MockProcessBCL extends AbstractMockBase implements IProcessBCL
{

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant STRING_ZERO. */
	private static final String STRING_ZERO = "0";

	/** The Constant STRING_FIVE. */
	private static final String STRING_FIVE = "5";

	/** The Constant STRING_ONE. */
	private static final String STRING_ONE = "1";

	/** The Constant NINETY_NINE. */
	private static final int NINETY_NINE = 99;

	/** The Constant NINETY_EIGHT. */
	private static final int NINETY_EIGHT = 98;

	/** The Constant RNI_EVENT_ID. */
	private static final int RNI_EVENT_ID = 40123456;

	/** The Constant METER_FLEXNET_ID. */
	private static final BigInteger METER_FLEXNET_ID = new BigInteger("46722565");

	/** The Constant METER_DEVICE_ID. */
	private static final String METER_DEVICE_ID = "1N6028953757";

	/** The Constant PROPERTY_VALUE. */
	private static final String PROPERTY_VALUE = "1352820380";

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant REPID_NOT_FOUND. */
	private static final String REPID_NOT_FOUND = "[RepidNotFound]";

	/** The Constant DECODE_FAILED. */
	public static final String DECODE_FAILED = "sensus.dm.common.process.nc.process.decode.failed ";

	/** The Constant DECODE_FAILED_WITHOUT_END_SPACE. */
	public static final String DECODE_FAILED_WITHOUT_END_SPACE = "sensus.dm.common.process.nc.process.decode.failed";

	/** The Constant ON_DEMAND. */
	private static final String ON_DEMAND = "[OnDemand|";

	/** The Constant PARENTHESES. */
	private static final String PARENTHESES = "[]";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#insertProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> insertProcess(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<DMProcess>)verifyOtherSituations();
		}

		response.addResult(new DMProcess(1, null, processRequest.getFirstProcess().getRniEventId()));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchMonitoredProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (ProcessItemStatusEnum.FAILED.equals(processRequest.getProcessItemStatusEnum()))
		{
			if (getSituationsEnum() == SituationsEnum.EXCEPTION)
			{
				DMProcess process = TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED, new DMAction(
						new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
				process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.FAILED,
						DECODE_FAILED_WITHOUT_END_SPACE
								+ METER_DEVICE_ID, 2));

				ProcessItem processItem = TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED
						, null, DeviceTypeEnum.ELECTRIC_METER);
				processItem.getDevice().setDeviceId("1010M");
				processItem.setProcessItemStatusEnum(null);
				process.addProcessItem(processItem);

				response.setStatus(Status.ExceptionError);
				response.addResult(process);

				return response;
			}
			if (getSituationsEnum() == SituationsEnum.SUCCESS)
			{
				DMProcess process = TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED, new DMAction(
						new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
				process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.FAILED, DECODE_FAILED
						+ METER_DEVICE_ID, 2));

				response.addResult(process);

				return response;
			}

			DMProcess process = TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED, new DMAction(
					new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
			process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.FAILED,
					REPID_NOT_FOUND, 2));
			process.addProcessItem(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED
					, null, DeviceTypeEnum.HAN_DEVICE));

			if (getSituationsEnum() == SituationsEnum.ERROR)
			{

				if (getSituationsEnum() == SituationsEnum.ERROR
						&& !ValidationUtil.isNull(process.getProcessItems().get(2))
						&& !ValidationUtil.isNull(process.getProcessItems().get(2).getDevice()))
				{
					((HanDevice)process.getProcessItems().get(2).getDevice()).setMacAddress(null);

					response.setStatus(Status.ExceptionError);
					response.addResult(process);

					return response;
				}
			}

			return new InternalResultsResponse<DMProcess>(process);
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			if (!ValidationUtil.isNull(processRequest.getFirstProcess())
					&& !ValidationUtil.isNullOrZero(processRequest.getFirstProcess().getId())
					&& processRequest.getFirstProcess().getId() == NINETY_NINE)
			{
				processRequest.getFirstProcess().addProcessItem(
						new ProcessItem(new Device(new Radio(METER_FLEXNET_ID))));
				processRequest.getFirstProcess().setProperties(new ArrayList<Property>());
				processRequest.getFirstProcess().addProperty(
						new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), STRING_ONE));
				processRequest.getFirstProcess().addProperty(
						new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(), STRING_ONE));
				processRequest.getFirstProcess().addProperty(
						new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(), STRING_ONE));

				response.addResult(processRequest.getFirstProcess());
				response.getFirstResult().setAction(
						new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT)));
				return response;
			}

			if (!ValidationUtil.isNull(processRequest.getFirstProcess())
					&& !ValidationUtil.isNullOrZero(processRequest.getFirstProcess().getId())
					&& processRequest.getFirstProcess().getId() == NINETY_EIGHT)
			{
				processRequest.getFirstProcess().addProcessItem(
						new ProcessItem(new Device(new Radio(METER_FLEXNET_ID))));
				processRequest.getFirstProcess().setProperties(new ArrayList<Property>());
				processRequest.getFirstProcess().addProperty(
						new Property(PropertyEnum.RELAY1_TAMPER_TIMEOUT.getValue(), STRING_ONE));

				response.addResult(processRequest.getFirstProcess());
				response.getFirstResult().setAction(
						new DMAction(new ActionType(ActionTypeEnum.SET_TAMPER_DETECT_TIMER)));
				return response;
			}
		}
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchTodayProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			HashMap<String, Integer> countMonitoredProcesses = new HashMap<String, Integer>();
			countMonitoredProcesses.put("count_monitored", 1);
			response.addResult(countMonitoredProcesses);
			return response;
		}

		return (InternalResultsResponse<HashMap<String, Integer>>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#checkLinkStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Boolean> checkLinkStatus(ProcessRequest processRequest)
	{
		return (InternalResultsResponse<Boolean>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#insertProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> insertProcessItems(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			if (!ValidationUtil.isNull(processRequest.getFirstProcess())
					&& !ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProcessItems()))
			{
				processRequest.getFirstProcess().getFirstProcessItem().setId(1);
			}
			else
			{
				processRequest.getFirstProcess().addProcessItem(new ProcessItem(1));
			}
		}

		response.addResult(processRequest.getFirstProcess().getFirstProcessItem());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#updateProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcessItems(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchCheckProcessing(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse fetchCheckProcessing(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return response;
		}

		return verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#summarizeProcess(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse summarizeProcess(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessItemsByDevice(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByDevice(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessItem>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchCommunicationSummary(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchCommunicationSummary(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = processResultsResponse();

		if (!ValidationUtil.isNullOrEmpty(response.getResultsList())
				&& !ValidationUtil.isNullOrEmpty(response.getResultsList().get(0).getProcessItems()))
		{
			response.getResultsList().get(0).getProcessItems().get(0)
					.setParticipation(DemandResponseParticipationEnum.OPT_OUT.getValue());

			if (response.getResultsList().get(0).getProcessItems().size() > 1)
			{
				response.getResultsList().get(0).getProcessItems().get(1)
						.setParticipation(DemandResponseParticipationEnum.PARTIAL_PARTICIPATION.getValue());
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchImportHanDeviceSummary(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchImportHanDeviceSummary(ProcessRequest processRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchDemandReadPingSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandReadPingSummary(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// first process
			DMProcess process = TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS, new DMAction(
					new ActionType(ActionTypeEnum.DEMAND_READ)));

			// process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, 2));
			process.addProcessItem(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED
					, null, DeviceTypeEnum.WATER_METER));
			process.getFirstProcessItem().setProperties(new ArrayList<Property>());
			process.getFirstProcessItem().getProperties()
					.add(new Property(PropertyEnum.LAST_READ_VALUE.getValue(), "254176.000 Gal"));
			process.getFirstProcessItem()
					.getProperties()
					.add(new Property(PropertyEnum.LAST_READ_TIME.getValue(), String.valueOf(DMConvertUtil
							.convertDateToInteger(new Date()))));
			process.addProcessItem(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED
					, null, DeviceTypeEnum.GAS_METER));
			response.addResult(process);
			return response;
		}
		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return (InternalResultsResponse<DMProcess>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchStartedProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchStartedProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return new InternalResultsResponse<DMProcess>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (!ValidationUtil.isNullOrEmpty(processRequest.getProcessList())
				&& !ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProcessItems())
				&& !ValidationUtil.isNull(processRequest.getFirstProcess().getFirstProcessItem().getDevice())
				&& !ValidationUtil
						.isNull(processRequest.getFirstProcess().getFirstProcessItem().getDevice().getRadio())
				&& OTA_METER_FLEXNET2_ID.equals(processRequest.getFirstProcess().getFirstProcessItem().getDevice()
						.getRadio().getFlexNetId()))
		{

			response.addResult(processRequest.getFirstProcess());

			return response;
		}
		else if (!ValidationUtil.isNullOrEmpty(processRequest.getProcessList())
				&& !ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProcessItems())
				&& ProcessItemStatusEnum.OPT_OUT.equals(processRequest.getFirstProcess().getFirstProcessItem()
						.getProcessItemStatusEnum()))
		{
			// processRequest.getFirstProcess().getFirstProcessItem().setId(ONE);
			// response.addResult(processRequest.getFirstProcess());
			return response;
		}

		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessesWithProcessItemInProcessing(com.sensus.dm.common.process
	 * .model
	 * .request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesWithProcessItemInProcessing(ProcessRequest request)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			if (!ValidationUtil.isNull(request.getFirstProcess())
					&& !ValidationUtil.isNullOrZero(request.getFirstProcess().getId())
					&& request.getFirstProcess().getId() == ONE)
			{
				DMProcess process =
						new DMProcess(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT))
								, new ProcessItem(ONE, TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER,
										METER_FLEXNET_ID, METER_DEVICE_ID)));
				process.setRniEventId(RNI_EVENT_ID);
				process.addProperty(new Property(PropertyEnum.ACTION_DATE.getValue(), PROPERTY_VALUE));
				process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), STRING_ONE));
				process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_DURATION.getValue(), STRING_ONE));
				process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_HEATING.getValue(), STRING_FIVE));
				process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(), STRING_ONE));
				process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_CRITICALITY_LEVEL.getValue(), STRING_ONE));
				process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(), STRING_ZERO));
				response.addResult(process);
			}
			else
			{
				DMProcess process =
						new DMProcess(new DMAction(new ActionType(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE))
								, new ProcessItem(ONE, TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER,
										METER_FLEXNET_ID, METER_DEVICE_ID)));
				process.setRniEventId(RNI_EVENT_ID);
				process.addProperty(new Property(PropertyEnum.ACTION_DATE.getValue(), PROPERTY_VALUE));
				process.addProperty(new Property(PropertyEnum.HAN_TEXT_MESSAGE_TEXT.getValue(), STRING_ONE));

				process.addProperty(new Property(PropertyEnum.HAN_TEXT_MESSAGE_DURATION.getValue(), STRING_ONE));
				response.addResult(process);
			}
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessByRniEventId(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessByRniEventId(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<DMProcess>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchAllProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessItem> fetchAllProcessItems(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<ProcessItem>(TestBaseUtil.createProcessItem(
					ProcessItemStatusEnum.RUNNING,
					"", 2));
		}

		return (InternalResultsResponse<ProcessItem>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#updateProcessItemsToExpire(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcessItemsToExpire(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcl.IProcessBCL#updateProcessStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcessStatus(ProcessRequest processRequest)
	{
		return new InternalResponse();
	}

	/**
	 * Process results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<DMProcess> processResultsResponse()
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// first process
			DMProcess process = TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS, new DMAction(
					new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
			// process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, 2));
			process.addProcessItem(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED
					, null, DeviceTypeEnum.HAN_DEVICE));
			process.addProcessItem(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.ABORTED
					, null, DeviceTypeEnum.HAN_DEVICE));

			// second process
			DMProcess process2 = TestBaseUtil.createProcess(ProcessStatusEnum.ABORTED, new DMAction(
					new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
			process2.setDescription(PARENTHESES + ON_DEMAND);
			process2.getProcessType().setDescription(GenerateCSVFileAction.ACTION);
			process2.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, 2));

			// third process
			DMProcess process3 = TestBaseUtil.createProcess(ProcessStatusEnum.STARTED, new DMAction(
					new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
			process3.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, 2));

			// fourth process
			DMProcess process4 = TestBaseUtil.createProcess(ProcessStatusEnum.COMMAND_SENT, new DMAction(
					new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
			process4.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, 2));

			// fifth process
			DMProcess process5 = TestBaseUtil.createProcess(ProcessStatusEnum.IN_PROCESS, new DMAction(
					new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
			process5.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED, null, 2));
			process5.setDescription(PARENTHESES + ON_DEMAND);
			process2.getProcessType().setDescription(GenerateCSVFileAction.ACTION);
			process5.getAction().setActionType(new ActionType());

			// add results to this response
			response.addResult(process);
			response.addResult(process2);
			response.addResult(process3);
			response.addResult(process4);
			response.addResult(process5);
			return response;
		}
		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return (InternalResultsResponse<DMProcess>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchRelays(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<ProcessItem> fetchRelays(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			response.setStatus(Status.OperationSuccess);
			LCM lcm = new LCM();
			lcm.setLcmRelays(TestBaseUtil.createLCMRelay());
			response.addResult(new ProcessItem(lcm));

			return response;
		}

		return (InternalResultsResponse<ProcessItem>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchRelaysByProcessId(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchRelaysByProcessId(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessItem>();
	}

	@Override
	public InternalResponse insertProcessProperty(ProcessRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}