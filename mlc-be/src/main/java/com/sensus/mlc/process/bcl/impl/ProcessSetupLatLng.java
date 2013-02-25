package com.sensus.mlc.process.bcl.impl;

import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.mlcserver.type.InitiateApplySmartpointPropertiesRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessSetupLatLng.
 */
class ProcessSetupLatLng extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process setup lat lng.
	 */
	public ProcessSetupLatLng()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.UPDATE_LIGHT_LAT_LONG));
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

		List<Smartpoint> wsSmartPointList = generateWsSmartPointList(process, tenant);

		if (ValidationUtil.isNullOrEmpty(wsSmartPointList))
		{
			return null;
		}

		InitiateApplySmartpointPropertiesRequest request =
				getFactory().createInitiateApplySmartpointPropertiesRequest();

		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getSmartpoint().addAll(wsSmartPointList);

		LCActionParameter latitude = process.getLcAction().getActionParameters().get(0);
		LCActionParameter longitude = process.getLcAction().getActionParameters().get(1);
		request.setLatitude(Double.valueOf(latitude.getActionValue()));
		request.setLongitude(Double.valueOf(longitude.getActionValue()));

		persistLog(processRequest, wsSmartPointList);

		// Call Web Service
		return getMlcGatewayWs().applySmartpointProperties(request);
	}
}
