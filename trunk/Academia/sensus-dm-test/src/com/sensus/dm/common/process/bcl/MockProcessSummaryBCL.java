package com.sensus.dm.common.process.bcl;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.action.model.GenerateCSVFileAction;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseParticipationEnum;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The Class MockProcessSummaryBCL.
 * 
 * @author QAT Global.
 */
public class MockProcessSummaryBCL extends AbstractMockBase implements IProcessSummaryBCL
{

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

	/** The Constant NINETY_NINE. */
	protected static final Integer NINETY_NINE = 99;

	/** The Constant ONE_HUNDRED_ONE. */
	protected static final String ONE_HUNDRED_ONE = "101";

	/** The Constant ONE_HUNDRED_TWO. */
	protected static final String ONE_HUNDRED_TWO = "102";

	/** The Constant ONE_HUNDRED_THREE. */
	protected static final String ONE_HUNDRED_THREE = "103";

	/** The Constant SIXTEEN. */
	protected static final String SIXTEEN = "16";

	/** The Constant EIGHT. */
	protected static final String EIGHT = "8";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchDemandResponseSummary(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		DMProcess process = TestBaseUtil.createProcess(ProcessStatusEnum.COMPLETED, new DMAction(
				new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));

		process.setProcessItems(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.FAILED, REPID_NOT_FOUND, 2));
		process.getProcessItems().get(0).setParticipation(DemandResponseParticipationEnum.OPT_OUT.getValue());

		process.addProcessItem(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED
				, null, DeviceTypeEnum.HAN_DEVICE));
		process.getProcessItems().get(1)
				.setParticipation(DemandResponseParticipationEnum.PARTIAL_PARTICIPATION.getValue());

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			if (EPMAreaEnum.PROCESSCSV.equals(getAreaEnum())
					&& !ValidationUtil.isNull(process.getProcessItems().get(2))
					&& !ValidationUtil.isNull(process.getProcessItems().get(2).getDevice()))
			{
				((HanDevice)process.getProcessItems().get(2).getDevice()).setMacAddress(null);

				response.setStatus(Status.ExceptionError);
				response.addResult(process);
				return response;
			}

			return getInternalResultsResponseError();
		}

		response.addResult(process);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchDemandResponseProgramParticipation(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchAllDemandResponseSetup(com.sensus.dm.common.process.
	 * model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		if (NINETY_NINE.equals(processRequest.getProcessId()))
		{
			DMProcess process = new DMProcess();
			process.addProperty(new Property("RELAY1_ENROLLMENT_CODE", ONE_HUNDRED_ONE));
			process.addProperty(new Property("RELAY2_ENROLLMENT_CODE", ONE_HUNDRED_TWO));
			process.addProperty(new Property("RELAY3_ENROLLMENT_CODE", ONE_HUNDRED_THREE));
			process.addProperty(new Property("RELAY1_DEVICE_CLASS", SIXTEEN));
			process.addProperty(new Property("RELAY2_DEVICE_CLASS", EIGHT));
			process.addProperty(new Property("RELAY3_DEVICE_CLASS", SIXTEEN));

			return new InternalResultsResponse<DMProcess>(process);
		}
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchProcessItemsByConnectDisconnect(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByConnectDisconnect(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessItem>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchProcessSendHanTextMessage(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessSendHanTextMessage(ProcessRequest processRequest)
	{
		return processResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessesDemandResponseStatus(com.sensus.dm.common.process.model
	 * .
	 * request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesDemandResponseStatus(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (!ValidationUtil.isNullOrEmpty(processRequest.getProcessList())
				&& !ValidationUtil.isNull(processRequest.getFirstProcess().getAction())
				&& (ActionTypeEnum.GET_DEMAND_RESPONSE_SETUP_STATUS.equals(processRequest.getFirstProcess().getAction()
						.getActionType().getActionTypeEnum())
						|| ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS.equals(processRequest.getFirstProcess()
								.getAction()
								.getActionType().getActionTypeEnum())
						|| ActionTypeEnum.GET_HAN_CONNECTION_STATUS.equals(processRequest.getFirstProcess().getAction()
						.getActionType().getActionTypeEnum())))
		{
			DMProcess process = new DMProcess(ONE);
			process.setRniEventId(ONE);

			process.addProperty(new Property(PropertyEnum.ACTION_DATE.getValue(), "1372067707"));
			process.addProperty(new Property(PropertyEnum.RELAY1_ENROLLMENT_CODE.getValue(), String.valueOf(ONE)));
			process.addProperty(new Property(PropertyEnum.RELAY1_DEVICE_CLASS.getValue(), String.valueOf(ONE)));
			process.addProperty(new Property(PropertyEnum.RELAY1_RANDOMIZE_START.getValue(), String.valueOf(ONE)));
			process.addProperty(new Property(PropertyEnum.RELAY1_RANDOMIZE_END.getValue(), String.valueOf(ONE)));
			process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), String
					.valueOf(ONE)));
			process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(), String
					.valueOf(ONE)));
			process.addProperty(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(), String.valueOf(ONE)));

			response.addResult(process);
			return response;
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessSummaryBCL#fetchLastTamperDetectTimeout(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchLastTamperDetectTimeout(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<DMProcess>();
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
}