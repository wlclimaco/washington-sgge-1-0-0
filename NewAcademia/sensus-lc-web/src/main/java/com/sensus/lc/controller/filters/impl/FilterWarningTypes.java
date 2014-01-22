package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.model.AlertSubTypeEnum;

/**
 * The Class FilterWarningTypes.
 */
@Component
public class FilterWarningTypes extends AbstractFilterBase
{
	/** The Constant WARNING_TYPE. */
	private static final String WARNING_TYPE = "WARNING_TYPE";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return WARNING_TYPE.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		Map<String, Object> records = new HashMap<String, Object>();
		String locale = getUserSettings().getLanguage();

		records.put(AlertSubTypeEnum.POWER_SURGE_DETECTED.getValue().toString(),
				getText("sensus.mlc.alert_subtype.powersurgedetected", locale));
		records.put(AlertSubTypeEnum.BROWN_OUT_DETECTED.getValue().toString(),
				getText("sensus.mlc.alert_subtype.brownoutdetected", locale));
		records.put(AlertSubTypeEnum.COMMUNICATION_FAIL.getValue().toString(),
				getText("sensus.mlc.alert_subtype.communicationfail", locale));
		records.put(AlertSubTypeEnum.HIGH_CURRENT.getValue().toString(),
				getText("sensus.mlc.alert_subtype.highcurrent", locale));
		records.put(AlertSubTypeEnum.LOW_CURRENT.getValue().toString(),
				getText("sensus.mlc.alert_subtype.lowcurrent", locale));
		records.put(AlertSubTypeEnum.REVERSE_ENERGY.getValue().toString(),
				getText("sensus.mlc.alert_subtype.reverseenergy", locale));
		records.put(AlertSubTypeEnum.METROLOGY_RESET.getValue().toString(),
				getText("sensus.mlc.alert_subtype.metrologyreset", locale));

		filtersResponse.setWarning_type(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
