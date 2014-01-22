package com.sensus.lc.process.bcl.impl;

import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.InitiateApplySmartpointPropertiesRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.Smartpoint;

/**
 * The Class ProcessSetupLatLng.
 */
public class ProcessSetupLatLng extends AbstractProcessCommunicationGateway
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
		Tenant tenant = processRequest.getUserContext().<Tenant> getTenant();
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

		Double latitude = process.getProcessItems().get(0).getLight().getRadio().getLocation().getLatitude();
		Double longitude = process.getProcessItems().get(0).getLight().getRadio().getLocation().getLongitude();
		request.setLatitude(latitude);
		request.setLongitude(longitude);

		persistLog(processRequest, wsSmartPointList);

		// Call Web Service
		return getMlcGatewayWs().applySmartpointProperties(request);
	}
}
