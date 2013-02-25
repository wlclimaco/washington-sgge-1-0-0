package com.sensus.mlc.process.bcl.impl;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.sensus.mlc.mlcserver.type.InitiateUpdateLightStatusRequest;
import com.sensus.mlc.mlcserver.type.LightTopLevelState;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.SmartpointStateLevel;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessSetupLightStatus.
 */
public class ProcessSetupLightStatus extends AbstractProcessCommunicationGateway
{
	/**
	 * Instantiates a new process setup light status.
	 */
	public ProcessSetupLightStatus()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.UPDATE_LIGHT_STATUS));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#sendAction(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public MlcGatewayResponse sendAction(ProcessRequest processRequest)
	{
		Tenant tenant = processRequest.getTenant();
		Process process = processRequest.getProcess();
		setRemoteUserCredentials(tenant);

		if (isFailure(process.getProcessItems().get(0), process))
		{
			return null;
		}

		SmartpointStateLevel wsSmartPointState = new SmartpointStateLevel();
		wsSmartPointState.setRniId(process.getProcessItems().get(0).getLight().getRniId());
		wsSmartPointState.setCustomerID(tenant.getRniCode());

		LCActionParameter actionParameter = process.getLcAction().getActionParameters().get(0);

		if (actionParameter.getProperty().equals(PropertyEnum.ACTIVE))
		{
			wsSmartPointState.setLightTopLevelState(LightTopLevelState.ACTIVE);
		}
		else if (actionParameter.getProperty().equals(PropertyEnum.MAINTENANCE))
		{
			wsSmartPointState.setLightTopLevelState(LightTopLevelState.MAINTENANCE);
		}
		else if (actionParameter.getProperty().equals(PropertyEnum.DEACTIVATED))
		{
			process.setIsProcessComplete(true);
			process.setEndTime(getNewDateUTC());
			processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
			wsSmartPointState.setLightTopLevelState(LightTopLevelState.IDLE);
		}

		List<SmartpointStateLevel> wsSmartPointListState = new ArrayList<SmartpointStateLevel>();
		wsSmartPointListState.add(wsSmartPointState);

		InitiateUpdateLightStatusRequest request = getFactory().createInitiateUpdateLightStatusRequest();
		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getSmartpoint().addAll(wsSmartPointListState);

		persistLog(processRequest, wsSmartPointListState);

		// Call Web Service
		return getMlcGatewayWs().updateLightStatus(request);
	}
}
