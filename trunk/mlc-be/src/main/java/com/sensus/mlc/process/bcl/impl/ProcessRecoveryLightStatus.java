package com.sensus.mlc.process.bcl.impl;

import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.mlcserver.type.DataType;
import com.sensus.mlc.mlcserver.type.InitiateReadLightStatusRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessRecoveryLightStatus.
 */
class ProcessRecoveryLightStatus extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process recovery light status.
	 */
	public ProcessRecoveryLightStatus()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.GET_LIGHT_STATUS));
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

		InitiateReadLightStatusRequest request = getFactory().createInitiateReadLightStatusRequest();
		request.setTransactionID(process.getRniCorrelationId());
		request.setCustomerID(tenant.getRniCode());
		request.getSmartpoint().addAll(wsSmartPointList);

		// Determine, based on LightDetailType list (stored in ActionParameters), the level of detail to be
		// requested
		for (LCActionParameter parm : process.getLcAction().getActionParameters())
		{
			Integer intParm = Integer.parseInt(parm.getActionValue());
			if (PropertyEnum.LIGHT_DETAIL_TYPE.equals(parm.getProperty()))
			{
				if (intParm.equals(LightDetailDataTypeEnum.CONFIGURATION.getValue()))
				{
					request.getDataType().add(DataType.CONFIGURATION_DATA);
				}
				if (intParm.equals(LightDetailDataTypeEnum.STATUS.getValue()))
				{
					request.getDataType().add(DataType.STATUS_DATA);
				}
			}
		}

		persistLog(processRequest, wsSmartPointList);

		// Call Web Service
		return getMlcGatewayWs().getLightStatus(request);
	}
}
