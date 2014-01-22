package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import com.sensus.lc.controller.filters.BaseFilterController;
import com.sensus.lc.controller.filters.IFilter;
import com.sensus.lc.process.model.LCActionCategoryEnum;

/**
 * The Class AbstractFilterBase.
 */
public abstract class AbstractFilterBase extends BaseFilterController implements IFilter
{
	/** The Constant RECORD_0. */
	protected static final String RECORD_0 = "0";

	/** The Constant RECORD_1. */
	protected static final String RECORD_1 = "1";

	/** The Constant TRUE. */
	protected static final String TRUE = "true";

	/** The Constant FALSE. */
	protected static final String FALSE = "false";

	/** The Constant FILTER_TYPE_TEXT. */
	protected static final String FILTER_TYPE_TEXT = "text";

	/** The Constant FILTER_TYPE_CHECKBOX. */
	protected static final String FILTER_TYPE_CHECKBOX = "checkbox";

	/** The Constant TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGETAGS. */
	protected static final String TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGETAGS = "process.page.filter.type.managetags";

	/** The Constant TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGEGROUPS. */
	protected static final String TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGEGROUPS = "process.page.filter.type.managegroups";

	/** The Constant TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGELIGHTS. */
	protected static final String TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGELIGHTS = "process.page.filter.type.managelights";

	/** The Constant TEXT_PROCESS_PAGE_FILTER_TYPE_CONTROLLIGHTS. */
	protected static final String TEXT_PROCESS_PAGE_FILTER_TYPE_CONTROLLIGHTS =
			"process.page.filter.type.controllights";

	/** The Constant TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGESCHEDULES. */
	protected static final String TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGESCHEDULES =
			"process.page.filter.type.manageschedules";

	/** The Constant TEXT_COMMONS_PAGES_START. */
	private static final String TEXT_COMMONS_PAGES_START = "commons.pages.start";

	/** The Constant TEXT_COMMONS_PAGES_END. */
	private static final String TEXT_COMMONS_PAGES_END = "commons.pages.end";

	/**
	 * Gets the common text inputs.
	 * 
	 * @return the common text inputs
	 */
	protected Map<String, Object> getCommonTextInputs()
	{
		Map<String, Object> records = new HashMap<String, Object>();
		String locale = getUserSettings().getLanguage();
		records.put(RECORD_0, getText(TEXT_COMMONS_PAGES_START, locale));
		records.put(RECORD_1, getText(TEXT_COMMONS_PAGES_END, locale));

		return records;
	}

	/**
	 * Gets the common text input.
	 * 
	 * @param text the text
	 * @return the common text input
	 */
	protected Map<String, Object> getCommonTextInput(String text)
	{
		Map<String, Object> records = new HashMap<String, Object>();
		String locale = getUserSettings().getLanguage();
		records.put(RECORD_0, getText(text, locale));

		return records;
	}

	/**
	 * Gets the event action types.
	 * 
	 * @return the event action types
	 */
	protected Map<String, Object> getEventActionTypes()
	{
		Map<String, Object> records = new HashMap<String, Object>();
		String locale = getUserSettings().getLanguage();

		records.put(LCActionCategoryEnum.CONTROL_LIGHTS.getValue().toString(),
				getText(TEXT_PROCESS_PAGE_FILTER_TYPE_CONTROLLIGHTS, locale));
		records.put(LCActionCategoryEnum.MANAGE_LIGHTS.getValue().toString(),
				getText(TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGELIGHTS, locale));
		records.put(LCActionCategoryEnum.MANAGE_GROUPS.getValue().toString(),
				getText(TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGEGROUPS, locale));
		records.put(LCActionCategoryEnum.MANAGE_TAGS.getValue().toString(),
				getText(TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGETAGS, locale));
		records.put(LCActionCategoryEnum.MANAGE_SCHEDULES.getValue().toString(),
				getText(TEXT_PROCESS_PAGE_FILTER_TYPE_MANAGESCHEDULES, locale));

		return records;
	}
}
