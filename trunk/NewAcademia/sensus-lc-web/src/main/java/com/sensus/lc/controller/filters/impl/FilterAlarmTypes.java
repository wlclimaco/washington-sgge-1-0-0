package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.model.AlertSubTypeEnum;

/**
 * The Class FilterAlarmTypes.
 */
@Component
public class FilterAlarmTypes extends AbstractFilterBase
{

	/** The Constant ALARM_TYPE. */
	private static final String ALARM_TYPE = "ALARM_TYPE";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return ALARM_TYPE.equals(filter);
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

		records.put(AlertSubTypeEnum.LAMP_FAILURE.getValue().toString(),
				getText("sensus.mlc.alert_subtype.lampfailure", locale));
		records.put(AlertSubTypeEnum.POWER_FAILURE.getValue().toString(),
				getText("sensus.mlc.alert_subtype.powerfailure", locale));
		records.put(AlertSubTypeEnum.BOARD_FAILURE.getValue().toString(),
				getText("sensus.mlc.alert_subtype.boardfailure", locale));
		records.put(AlertSubTypeEnum.METROLOGY_ERROR.getValue().toString(),
				getText("sensus.mlc.alert_subtype.metrologyerror", locale));
		records.put(AlertSubTypeEnum.METROLOGY_COM_FAILURE.getValue().toString(),
				getText("sensus.mlc.alert_subtype.metrologycomfailure", locale));

		filtersResponse.setAlarm_type(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
