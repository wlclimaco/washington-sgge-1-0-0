package com.sensus.dm.common.process.dac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The Class MockProcessDAC.
 * 
 * @author QAT Global.
 */
public class MockProcessSummaryDAC extends AbstractMockBase implements IProcessSummaryDAC
{

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant FOUR. */
	private static final int FOUR = 4;

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant HAN_DEVICE_FLEXNET_ID. */
	private static final String HAN_DEVICE_FLEXNET_ID = "2153943262073435";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchDemandResponseSummary(com.sensus.dm.common.process.model
	 * .request.ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		processRequest.getFirstProcess().setProperties(new ArrayList<Property>());

		DemandResponseEventAction demandResponse = new DemandResponseEventAction();
		demandResponse.setProcessId(ONE);
		demandResponse.setId(1);
		demandResponse.setCreateUser("rod");
		demandResponse.setIsMonitored(Boolean.TRUE);
		demandResponse.setOnDemand(Boolean.FALSE);
		demandResponse.setDevices(new ArrayList<Device>());
		demandResponse.getDevices().add(new HanDevice(new BigInteger(HAN_DEVICE_FLEXNET_ID)));
		demandResponse.getDevices().get(ZERO).setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		demandResponse.setEnrollmentCode(0);
		demandResponse.setDemandResponseDuration(FIVE);
		demandResponse.setActionTime(new Date());
		demandResponse.setRandomizeStart(Boolean.FALSE);
		demandResponse.setRandomizeEnd(Boolean.TRUE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(ALL);

		DMConvertUtil.convertActionToProperty(demandResponse, processRequest.getFirstProcess()
				.getProperties());

		processRequest.getFirstProcess().setAction(demandResponse);

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();
		response.addResult(processRequest.getFirstProcess());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchDemandResponseProgramParticipation(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		return processResultsResponse(ProcessStatusEnum.COMPLETED, ProcessItemStatusEnum.COMPLETED);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchAllDemandResponseSetup(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		processRequest.getFirstProcess().setProperties(new ArrayList<Property>());
		DemandResponseSetupAction setup = new DemandResponseSetupAction();

		setup.setEnrollmentCode(1);
		setup.setActionTime(new Date());

		DMConvertUtil.convertActionToProperty(setup, processRequest.getFirstProcess()
				.getProperties());

		processRequest.getFirstProcess().setAction(setup);

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();
		response.addResult(processRequest.getFirstProcess());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchProcessItemsByConnectDisconnect(com.sensus.dm.common
	 * .process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByConnectDisconnect(ProcessRequest processRequest)
	{
		return new InternalResultsResponse<ProcessItem>(TestBaseUtil.createProcessItem(ProcessItemStatusEnum.COMPLETED,
				null, FOUR));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchProcessSendHanTextMessage(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessSendHanTextMessage(ProcessRequest processRequest)
	{
		return processResultsResponse(ProcessStatusEnum.COMPLETED, ProcessItemStatusEnum.COMPLETED);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessesDemandResponseStatus(com.sensus.dm.common.process.model
	 * .request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesDemandResponseStatus(ProcessRequest processRequest)
	{
		return processResultsResponse(ProcessStatusEnum.COMPLETED, ProcessItemStatusEnum.COMPLETED);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessSummaryDAC#fetchLastTamperDetectTimeout(com.sensus.dm.common.process
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
	 * @param processStatusEnum the process status enum
	 * @param processItemStatusEnum the process item status enum
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<DMProcess> processResultsResponse(ProcessStatusEnum processStatusEnum,
			ProcessItemStatusEnum processItemStatusEnum)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		DMProcess process = TestBaseUtil.createProcess(processStatusEnum,
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
		process.setProcessItems(TestBaseUtil.createProcessItem(processItemStatusEnum, null, FOUR));
		process.setTotalSmartpoints(1);

		response.getResultsList().add(process);

		process = TestBaseUtil.createProcess(processStatusEnum,
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
		process.setProcessItems(TestBaseUtil.createProcessItem(processItemStatusEnum, null, FOUR));
		process.setTotalSmartpoints(1);

		response.getResultsList().add(process);

		return response;
	}
}