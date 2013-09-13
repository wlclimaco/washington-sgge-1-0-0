package com.sensus.dm.water.device.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;

/**
 * The Class WaterGasMeterConfigurationValidator.
 * 
 * @author QAT Global.
 */
public class WaterGasMeterConfigurationValidator implements IValidator
{

	/** The Constant CONFIGURATION_REQUIRED. */
	private static final String CONFIGURATION_REQUIRED =
			"sensus.epm.electricmetervalidator.elecmeter.configuration.required";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		WaterGasMeterConfiguration waterGasMeterConfiguration =
				(WaterGasMeterConfiguration)validationContext.getObjectToBeValidated(WaterGasMeterConfiguration.class
						.getSimpleName());

		if (ValidationUtil.isNull(waterGasMeterConfiguration))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(CONFIGURATION_REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALL:
			case GENERATE_FILE_CSV:
				validateFirmware(validationContext.getMessages(), waterGasMeterConfiguration);
				break;

			default:
				break;
		}
	}

	/**
	 * Validate firmware.
	 * 
	 * @param list the list
	 * @param configuration the configuration
	 */
	private void validateFirmware(List<MessageInfo> list, WaterGasMeterConfiguration configuration)
	{
		if (!ValidationUtil.isNull(configuration.getFirmwareMeter()))
		{
			configuration.setFirmwareMeter(DMConvertUtil.convertFirmware(configuration.getFirmwareMeter()));
		}
	}
}
