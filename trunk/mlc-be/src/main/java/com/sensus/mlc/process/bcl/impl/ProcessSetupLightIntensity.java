package com.sensus.mlc.process.bcl.impl;

import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.mlcserver.type.DimmedSmartpoint;
import com.sensus.mlc.mlcserver.type.InitiateApplyLightLevelRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessSetupLightIntensity.
 */
class ProcessSetupLightIntensity extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process setup light intensity.
	 */
	public ProcessSetupLightIntensity()
	{
		setSupportedActions(EnumSet.of(
				LCActionTypeEnum.DIM,
				LCActionTypeEnum.SET_BLINK_BY_LIGHT,
				LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT,
				LCActionTypeEnum.TURN_ON,
				LCActionTypeEnum.TURN_OFF));
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

		List<DimmedSmartpoint> wsDimmedSmartPointList = generateWsDimmedSmartPointList(process, tenant);
		if (ValidationUtil.isNullOrEmpty(wsDimmedSmartPointList))
		{
			return null;
		}

		InitiateApplyLightLevelRequest request = getFactory().createInitiateApplyLightLevelRequest();
		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getDimmedSmartpoint().addAll(wsDimmedSmartPointList);

		persistLog(processRequest, wsDimmedSmartPointList);

		// Call Web Service
		return getMlcGatewayWs().setLightIntensity(request);
	}
}
