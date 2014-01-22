package com.sensus.lc.process.bcl.impl;

import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.InitiateApplyDimmingConfigurationRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyDimmingConfigurationResponse;
import com.sensus.mlc.mlcserver.type.LightDimmingConfiguration;
import com.sensus.mlc.mlcserver.type.LightLevel;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.Smartpoint;

/**
 * The Class ProcessSetupDimmingConfiguration.
 */
public class ProcessSetupDimmingConfiguration extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process setup dimming configuration.
	 */
	public ProcessSetupDimmingConfiguration()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.SETUP_DIMMING_CONFIGURATION));
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

		InitiateApplyDimmingConfigurationRequest request =
				getFactory().createInitiateApplyDimmingConfigurationRequest();

		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getSmartpoint().addAll(wsSmartPointList);

		LightDimmingConfiguration lightDimmingConfiguration1 = new LightDimmingConfiguration();
		lightDimmingConfiguration1.setLightLevel(LightLevel.LEVEL_1);

		LightDimmingConfiguration lightDimmingConfiguration2 = new LightDimmingConfiguration();
		lightDimmingConfiguration2.setLightLevel(LightLevel.LEVEL_2);

		LightDimmingConfiguration lightDimmingConfiguration3 = new LightDimmingConfiguration();
		lightDimmingConfiguration3.setLightLevel(LightLevel.LEVEL_3);

		LightDimmingConfiguration lightDimmingConfiguration4 = new LightDimmingConfiguration();
		lightDimmingConfiguration4.setLightLevel(LightLevel.LEVEL_4);

		LightDimmingConfiguration lightDimmingConfiguration5 = new LightDimmingConfiguration();
		lightDimmingConfiguration5.setLightLevel(LightLevel.LEVEL_5);

		LightDimmingConfiguration lightDimmingConfiguration6 = new LightDimmingConfiguration();
		lightDimmingConfiguration6.setLightLevel(LightLevel.LEVEL_6);

		for (LCActionParameter parm : process.getLcAction().getActionParameters())
		{
			switchGetProperty(lightDimmingConfiguration1, lightDimmingConfiguration2, lightDimmingConfiguration3,
					lightDimmingConfiguration4, lightDimmingConfiguration5, lightDimmingConfiguration6, parm);
		}

		request.getDimmingConfiguration().add(lightDimmingConfiguration1);
		request.getDimmingConfiguration().add(lightDimmingConfiguration2);
		request.getDimmingConfiguration().add(lightDimmingConfiguration3);
		request.getDimmingConfiguration().add(lightDimmingConfiguration4);
		request.getDimmingConfiguration().add(lightDimmingConfiguration5);
		request.getDimmingConfiguration().add(lightDimmingConfiguration6);

		persistLog(processRequest, wsSmartPointList);
		InitiateApplyDimmingConfigurationResponse response = getMlcGatewayWs().applyDimmingConfiguration(request);

		return response;
	}

	private void switchGetProperty(LightDimmingConfiguration lightDimmingConfiguration1,
			LightDimmingConfiguration lightDimmingConfiguration2, LightDimmingConfiguration lightDimmingConfiguration3,
			LightDimmingConfiguration lightDimmingConfiguration4, LightDimmingConfiguration lightDimmingConfiguration5,
			LightDimmingConfiguration lightDimmingConfiguration6, LCActionParameter parm)
	{
		switch (parm.getProperty())
		{
			case DIM_ON_DELAY:
				lightDimmingConfiguration1.setBlinkSlotTime(Integer.parseInt(parm.getActionValue()));
				lightDimmingConfiguration2.setBlinkSlotTime(Integer.parseInt(parm.getActionValue()));
				lightDimmingConfiguration3.setBlinkSlotTime(Integer.parseInt(parm.getActionValue()));
				lightDimmingConfiguration4.setBlinkSlotTime(Integer.parseInt(parm.getActionValue()));
				lightDimmingConfiguration5.setBlinkSlotTime(Integer.parseInt(parm.getActionValue()));
				lightDimmingConfiguration6.setBlinkSlotTime(Integer.parseInt(parm.getActionValue()));
				break;
			case HARDWARE_SETTING_CONFIGURATION_1:
				lightDimmingConfiguration1.setHardwareSetting(Integer.parseInt(parm.getActionValue()));
				break;
			case CURRENT_SCALE_CONFIGURATION_1:
				lightDimmingConfiguration1.setCurrentScale(Integer.parseInt(parm.getActionValue()));
				break;
			case FULL_ON_REQUIRED_CONFIGURATION_1:
				lightDimmingConfiguration1
						.setFullOnRequired(Boolean.parseBoolean(parm.getActionValue()));
				break;
			case HARDWARE_SETTING_CONFIGURATION_2:
				lightDimmingConfiguration2.setHardwareSetting(Integer.parseInt(parm.getActionValue()));
				break;
			case CURRENT_SCALE_CONFIGURATION_2:
				lightDimmingConfiguration2.setCurrentScale(Integer.parseInt(parm.getActionValue()));
				break;
			case FULL_ON_REQUIRED_CONFIGURATION_2:
				lightDimmingConfiguration2
						.setFullOnRequired(Boolean.parseBoolean(parm.getActionValue()));
				break;
			case HARDWARE_SETTING_CONFIGURATION_3:
				lightDimmingConfiguration3.setHardwareSetting(Integer.parseInt(parm.getActionValue()));
				break;
			case CURRENT_SCALE_CONFIGURATION_3:
				lightDimmingConfiguration3.setCurrentScale(Integer.parseInt(parm.getActionValue()));
				break;
			case FULL_ON_REQUIRED_CONFIGURATION_3:
				lightDimmingConfiguration3
						.setFullOnRequired(Boolean.parseBoolean(parm.getActionValue()));
				break;
			case HARDWARE_SETTING_CONFIGURATION_4:
				lightDimmingConfiguration4.setHardwareSetting(Integer.parseInt(parm.getActionValue()));
				break;
			case CURRENT_SCALE_CONFIGURATION_4:
				lightDimmingConfiguration4.setCurrentScale(Integer.parseInt(parm.getActionValue()));
				break;
			case FULL_ON_REQUIRED_CONFIGURATION_4:
				lightDimmingConfiguration4
						.setFullOnRequired(Boolean.parseBoolean(parm.getActionValue()));
				break;
			case HARDWARE_SETTING_CONFIGURATION_5:
				lightDimmingConfiguration5.setHardwareSetting(Integer.parseInt(parm.getActionValue()));
				break;
			case CURRENT_SCALE_CONFIGURATION_5:
				lightDimmingConfiguration5.setCurrentScale(Integer.parseInt(parm.getActionValue()));
				break;
			case FULL_ON_REQUIRED_CONFIGURATION_5:
				lightDimmingConfiguration5
						.setFullOnRequired(Boolean.parseBoolean(parm.getActionValue()));
				break;
			case HARDWARE_SETTING_CONFIGURATION_6:
				lightDimmingConfiguration6.setHardwareSetting(Integer.parseInt(parm.getActionValue()));
				break;
			case CURRENT_SCALE_CONFIGURATION_6:
				lightDimmingConfiguration6.setCurrentScale(Integer.parseInt(parm.getActionValue()));
				break;
			case FULL_ON_REQUIRED_CONFIGURATION_6:
				lightDimmingConfiguration6
						.setFullOnRequired(Boolean.parseBoolean(parm.getActionValue()));
				break;
			default:
				break;
		}
	}
}
