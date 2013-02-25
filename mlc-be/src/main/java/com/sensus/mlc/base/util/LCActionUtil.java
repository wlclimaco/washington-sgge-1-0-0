package com.sensus.mlc.base.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;

/**
 * The Class LCActionUtil.
 */
public final class LCActionUtil
{

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The Constant OFF_LABEL_KEY. */
	private static final String OFF_LABEL_KEY = "sensus.mlc.mlc_action.label.set_intensity_off";

	/** The Constant ON_LABEL_KEY. */
	private static final String ON_LABEL_KEY = "sensus.mlc.mlc_action.label.set_intensity_on";

	/** The Constant DIM_LABEL_KEY. */
	private static final String DIM_LABEL_KEY = "sensus.mlc.mlc_action.label.set_intensity_dim";

	/** The Constant ACTION_LABEL_KEY_ADD_SMP_TO_GROUP. */
	private static final String ACTION_LABEL_KEY_ADD_SMP_TO_GROUP = "sensus.mlc.mlc_action.label.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP_BY_POLE_ID =
			"sensus.mlc.mlc_action.add_smp_to_grp_by_poleid";

	/** The Constant ACTION_LABEL_KEY_ADD_SMP_TO_TAG. */
	private static final String ACTION_LABEL_KEY_ADD_SMP_TO_TAG = "sensus.mlc.mlc_action.label.add_smp_to_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG = "sensus.mlc.mlc_action.add_smp_to_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG_BY_POLE_ID =
			"sensus.mlc.mlc_action.add_smp_to_tag_by_poleid";

	/** The Constant LOCKED_LABEL_KEY. */
	private static final String LOCKED_LABEL_KEY = "sensus.mlc.mlc_action.label.locked";

	/** The Constant UNLOCKED_LABEL_KEY. */
	private static final String UNLOCKED_LABEL_KEY = "sensus.mlc.mlc_action.label.unlocked";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_LOCK. */
	private static final String DESCRIPTION_LABEL_KEY_SET_LOCK = "sensus.mlc.mlc_action.set_locked";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_LOCK_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_LOCK_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_locked_by_poleid";

	/** The Constant ACTION_LABEL_KEY_DEL_GROUP. */
	private static final String ACTION_LABEL_KEY_DEL_GROUP = "sensus.mlc.mlc_action.label.del_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_GROUP = "sensus.mlc.mlc_action.del_grp";

	/** The Constant ACTION_LABEL_KEY_UPDATE_GROUP. */
	private static final String ACTION_LABEL_KEY_UPDATE_GROUP = "sensus.mlc.mlc_action.label.update_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_UPDATE_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_UPDATE_GROUP = "sensus.mlc.mlc_action.update_grp";

	/** The Constant ACTION_LABEL_KEY_DEL_SMP_FROM_GROUP. */
	private static final String ACTION_LABEL_KEY_DEL_SMP_FROM_GROUP = "sensus.mlc.mlc_action.label.del_smp_from_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_GROUP = "sensus.mlc.mlc_action.del_smp_from_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_GROUP_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_GROUP_BY_POLE_ID =
			"sensus.mlc.mlc_action.del_smp_from_grp_by_poleid";

	/** The Constant ACTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE. */
	private static final String ACTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE =
			"sensus.mlc.mlc_action.label.apply_smp_to_schedule";

	/** The Constant DESCRIPTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE. */
	private static final String DESCRIPTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE =
			"sensus.mlc.mlc_action.apply_smp_to_schedule";

	/** The Constant DESCRIPTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE_BY_POLE_ID =
			"sensus.mlc.mlc_action.apply_smp_to_schedule_by_poleid";

	/** The Constant ACTION_LABEL_KEY_DEL_SCHEDULE. */
	private static final String ACTION_LABEL_KEY_DEL_SCHEDULE = "sensus.mlc.mlc_action.label.del_schedule";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SCHEDULE. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SCHEDULE = "sensus.mlc.mlc_action.del_schedule";

	/** The Constant ACTION_LABEL_KEY_GET_LIGHT_STATUS. */
	private static final String ACTION_LABEL_KEY_GET_LIGHT_STATUS = "sensus.mlc.mlc_action.label.get_light_status";

	/** The Constant ACTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION. */
	private static final String ACTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION =
			"sensus.mlc.mlc_action.label.setup_dimming_configuration";

	/** The Constant DESCRIPTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION. */
	private static final String DESCRIPTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION =
			"sensus.mlc.mlc_action.setup_dimming_configuration";

	/** The Constant DESCRIPTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION_BY_POLE_ID =
			"sensus.mlc.mlc_action.setup_dimming_configuration_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_GET_LIGHT_STATUS. */
	private static final String DESCRIPTION_LABEL_KEY_GET_LIGHT_STATUS = "sensus.mlc.mlc_action.get_light_status";

	/** The Constant DESCRIPTION_LABEL_KEY_GET_LIGHT_STATUS_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_GET_LIGHT_STATUS_BY_POLE_ID =
			"sensus.mlc.mlc_action.get_light_status_by_poleid";

	/** The Constant ACTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE. */
	private static final String ACTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE =
			"sensus.mlc.mlc_action.label.del_smp_from_schedule";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_EVENT. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_EVENT =
			"sensus.mlc.mlc_action.del_smp_from_schedule_event";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_EVENT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_EVENT_BY_POLE_ID =
			"sensus.mlc.mlc_action.del_smp_from_schedule_event_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_OFFSET. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_OFFSET =
			"sensus.mlc.mlc_action.del_smp_from_schedule_offset";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_OFFSET_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_OFFSET_BY_POLE_ID =
			"sensus.mlc.mlc_action.del_smp_from_schedule_offset_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT. */
	private static final String DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT =
			"sensus.mlc.mlc_action.set_intensity_by_light";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_TURN_ON_BY_LIGHT. */
	private static final String DESCRIPTION_LABEL_KEY_SET_TURN_ON_BY_LIGHT =
			"sensus.mlc.mlc_action.set_turn_on_by_light";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_TURN_OFF_BY_LIGHT. */
	private static final String DESCRIPTION_LABEL_KEY_SET_TURN_OFF_BY_LIGHT =
			"sensus.mlc.mlc_action.set_turn_off_by_light";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_intensity_by_light_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_TURN_ON_BY_LIGHT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_TURN_ON_BY_LIGHT_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_turn_on_by_light_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_TURN_OFF_BY_LIGHT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_TURN_OFF_BY_LIGHT_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_turn_off_by_light_by_poleid";

	/** The Constant ACTION_LABEL_KEY_INSERT_GROUP. */
	private static final String ACTION_LABEL_KEY_INSERT_GROUP = "sensus.mlc.mlc_action.label.insert_group";

	/** The Constant DESCRIPTION_LABEL_KEY_INSERT_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_INSERT_GROUP = "sensus.mlc.mlc_action.insert_group";

	/** The Constant ACTION_LABEL_KEY_INSERT_SCHEDULE. */
	private static final String ACTION_LABEL_KEY_INSERT_SCHEDULE = "sensus.mlc.mlc_action.label.insert_schedule";

	/** The Constant DESCRIPTION_LABEL_KEY_INSERT_SCHEDULE. */
	private static final String DESCRIPTION_LABEL_KEY_INSERT_SCHEDULE = "sensus.mlc.mlc_action.insert_schedule";

	/** The Constant ACTION_LABEL_KEY_UPDATE_SCHEDULE. */
	private static final String ACTION_LABEL_KEY_UPDATE_SCHEDULE = "sensus.mlc.mlc_action.label.update_schedule";

	/** The Constant DESCRIPTION_LABEL_KEY_UPDATE_SCHEDULE. */
	private static final String DESCRIPTION_LABEL_KEY_UPDATE_SCHEDULE = "sensus.mlc.mlc_action.update_schedule";

	/** The Constant ACTION_LABEL_KEY_INSERT_TAG. */
	private static final String ACTION_LABEL_KEY_INSERT_TAG = "sensus.mlc.mlc_action.label.insert_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_INSERT_TAG. */
	private static final String DESCRIPTION_LABEL_KEY_INSERT_TAG = "sensus.mlc.mlc_action.insert_tag";

	/** The Constant ACTION_LABEL_KEY_DELETE_TAG. */
	private static final String ACTION_LABEL_KEY_DELETE_TAG = "sensus.mlc.mlc_action.label.delete_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_DELETE_TAG. */
	private static final String DESCRIPTION_LABEL_KEY_DELETE_TAG = "sensus.mlc.mlc_action.delete_tag";

	/** The Constant ACTION_LABEL_KEY_DEL_SMP_FROM_TAG. */
	private static final String ACTION_LABEL_KEY_DEL_SMP_FROM_TAG = "sensus.mlc.mlc_action.label.del_smp_from_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG = "sensus.mlc.mlc_action.del_smp_from_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG_BY_POLE_ID =
			"sensus.mlc.mlc_action.del_smp_from_tag_by_poleid";

	/** The Constant ACTION_LABEL_KEY_AUTO_GROUP. */
	private static final String ACTION_LABEL_KEY_AUTO_GROUP = "sensus.mlc.mlc_action.label.auto_group";

	/** The Constant DESCRIPTION_LABEL_KEY_AUTO_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_AUTO_GROUP = "sensus.mlc.mlc_action.auto_group";

	/** The Constant ACTION_LABEL_KEY_CLEAR_ALARM. */
	private static final String ACTION_LABEL_KEY_CLEAR_ALARM = "sensus.mlc.mlc_action.label.clear_alarm";

	/** The Constant ACTION_LABEL_KEY_CLEAR_ALL_ALARMS. */
	private static final String ACTION_LABEL_KEY_CLEAR_ALL_ALARMS = "sensus.mlc.mlc_action.label.clear_all_alarms";

	/** The Constant ACTION_LABEL_KEY_CLEAR. */
	private static final String ACTION_LABEL_KEY_CLEAR = "sensus.mlc.mlc_action.label.clear_by_poleid";

	/** The Constant ACTION_LABEL_KEY_CLEAR_ALL. */
	private static final String ACTION_LABEL_KEY_CLEAR_ALL = "sensus.mlc.mlc_action.label.clear_all";

	/** The Constant DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_LAT_LONG. */
	private static final String DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_LAT_LONG =
			"sensus.mlc.mlc_action.update_light_lat_long";

	/** The Constant DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_POLE_ID =
			"sensus.mlc.mlc_action.update_light_pole_id";

	/** The Constant ACTION_LABEL_KEY_UPDATE_LIGHT_LAT_LONG. */
	private static final String ACTION_LABEL_KEY_UPDATE_LIGHT_LAT_LONG =
			"sensus.mlc.mlc_action.label.update_light_lat_long";

	/** The Constant ACTION_LABEL_KEY_UPDATE_LIGHT_POLE_ID. */
	private static final String ACTION_LABEL_KEY_UPDATE_LIGHT_POLE_ID =
			"sensus.mlc.mlc_action.label.update_lat_pole_id";

	/** The Constant DESCRIPTION_LABEL_KEY_GENERATE_CSV_FILE. */
	private static final String DESCRIPTION_LABEL_KEY_GENERATE_CSV_FILE =
			"sensus.mlc.mlc_action.generate_csv_file";

	/** The Constant ACTION_LABEL_KEY_GENERATE_CSV_FILE. */
	private static final String ACTION_LABEL_KEY_GENERATE_CSV_FILE =
			"sensus.mlc.mlc_action.label.generate_csv_file";

	/** The Constant DESCRIPTION_LABEL_KEY_RESET_MIN_MAX_VALUE. */
	private static final String DESCRIPTION_LABEL_KEY_RESET_MIN_MAX_VALUE = "sensus.mlc.mlc_action.reset_min_max_value";

	/** The Constant DESCRIPTION_LABEL_KEY_RESET_MIN_MAX_VALUE_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_RESET_MIN_MAX_VALUE_BY_POLE_ID =
			"sensus.mlc.mlc_action.reset_min_max_value_by_poleid";

	/** The Constant ACTION_LABEL_KEY_RESET_MIN_MAX_VALUE. */
	private static final String ACTION_LABEL_KEY_RESET_MIN_MAX_VALUE =
			"sensus.mlc.mlc_action.label.reset_min_max_value";

	/** The Constant ACTION_LABEL_KEY_INSERT_USER. */
	private static final String ACTION_LABEL_KEY_INSERT_USER = "sensus.mlc.mlc_action.label.insert_user";

	/** The Constant DESCRIPTION_LABEL_KEY_INSERT_USER. */
	private static final String DESCRIPTION_LABEL_KEY_INSERT_USER = "sensus.mlc.mlc_action.insert_user";

	/** The Constant ACTION_LABEL_KEY_SLOW_BLINK. */
	private static final String ACTION_LABEL_KEY_SLOW_BLINK =
			"sensus.mlc.mlc_action.label.set_slow_blink";

	/** The Constant ACTION_LABEL_KEY_FAST_BLINK. */
	private static final String ACTION_LABEL_KEY_FAST_BLINK =
			"sensus.mlc.mlc_action.label.set_fast_blink";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_BLINK_SLOW_BY_LIGHT. */
	private static final String DESCRIPTION_LABEL_KEY_SET_BLINK_SLOW_BY_LIGHT =
			"sensus.mlc.mlc_action.set_blink_slow_by_light";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_BLINK_SLOW_BY_LIGHT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_BLINK_SLOW_BY_LIGHT_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_blink_by_blink_slow_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_BLINK_FAST_BY_LIGHT. */
	private static final String DESCRIPTION_LABEL_KEY_SET_BLINK_FAST_BY_LIGHT =
			"sensus.mlc.mlc_action.set_blink_fast_by_light";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_BLINK_FAST_BY_LIGHT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_BLINK_FAST_BY_LIGHT_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_blink_by_blink_fast_by_poleid";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_CLEAR_OVERRIDE_BY_LIGHT. */
	private static final String DESCRIPTION_LABEL_KEY_SET_CLEAR_OVERRIDE_BY_LIGHT =
			"sensus.mlc.mlc_action.set_clear_override_by_light";

	/** The Constant DESCRIPTION_LABEL_KEY_SET_CLEAR_OVERRIDE_BY_LIGHT_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_SET_CLEAR_OVERRIDE_BY_LIGHT_BY_POLE_ID =
			"sensus.mlc.mlc_action.set_clear_override_by_poleid";

	/** The Constant ACTION_LABEL_KEY_CLEAR_OVERRIDE. */
	private static final String ACTION_LABEL_KEY_CLEAR_OVERRIDE =
			"sensus.mlc.mlc_action.label.set_clear_override";

	/** The Constant ACTION_LABEL_KEY_UPDATE_LIGHT_STATUS. */
	private static final String ACTION_LABEL_KEY_UPDATE_LIGHT_STATUS =
			"sensus.mlc.mlc_action.label.update_light_status";

	/** The Constant DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_STATUS. */
	private static final String DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_STATUS = "sensus.mlc.mlc_action.update_light_status";

	/** The Constant DESCRIPTION_EDIT_ECOMODE_BASELINE. */
	private static final String DESCRIPTION_EDIT_ECOMODE_BASELINE = "sensus.mlc.mlc_action.description.edit_ecomode";

	/** The Constant ACTION_LABEL_EDIT_ECOMODE_BASELINE. */
	private static final String ACTION_LABEL_EDIT_ECOMODE_BASELINE = "sensus.mlc.mlc_action.label.edit_ecomode";

	/** The Constant ACTION_LABEL_IMPORT_CSV_FILE. */
	private static final String ACTION_LABEL_IMPORT_CSV_FILE = "sensus.mlc.mlc_action.label.import_csv_file";

	/**
	 * Instantiates a new lC action util.
	 */
	private LCActionUtil()
	{

	}

	/**
	 * Generate description.
	 *
	 * @param process the process
	 * @param processItemAmount the process item amount
	 * @return the string
	 */
	public static String generateDescription(Process process, Integer processItemAmount)
	{
		if (processItemAmount == null)
		{
			processItemAmount = 0;
		}

		LCAction lcAction = process.getLcAction();
		LCActionTypeEnum actionType = lcAction.getActionType();
		switch (actionType)
		{
			case DIM:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT,
						lcAction,
						processItemAmount,
						0);

			case TURN_ON:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_SET_TURN_ON_BY_LIGHT_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_SET_TURN_ON_BY_LIGHT,
						lcAction,
						processItemAmount,
						0);

			case TURN_OFF:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_SET_TURN_OFF_BY_LIGHT_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_SET_TURN_OFF_BY_LIGHT,
						lcAction,
						processItemAmount,
						0);

			case SET_INTENSITY_BY_GRP:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_SET_INTENSITY_BY_LIGHT, lcAction, 1);

			case INSERT_SCHEDULE:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_INSERT_SCHEDULE, lcAction, 1);

			case UPDATE_SCHEDULE:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_UPDATE_SCHEDULE, lcAction, 1);

			case ADD_SMP_TO_SCHEDULE_EVENT:
			case ADD_SMP_TO_SCHEDULE_OFFSET:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE,
						lcAction,
						processItemAmount,
						1);

			case DEL_SCHEDULE:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_DEL_SCHEDULE, lcAction, 1);

			case DEL_SMP_FROM_SCHEDULE_EVENT:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_EVENT_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_EVENT,
						processItemAmount);

			case DEL_SMP_FROM_SCHEDULE_OFFSET:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_OFFSET_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE_OFFSET,
						processItemAmount);

			case GET_LIGHT_STATUS:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_GET_LIGHT_STATUS_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_GET_LIGHT_STATUS,
						processItemAmount);

			case SET_PROTECTED:
				return createDescriptionToSetProtected(processItemAmount, lcAction);

			case INSERT_GROUP:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_INSERT_GROUP, lcAction, 1);

			case DEL_GRP:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_DEL_GROUP, lcAction, 1);

			case UPDATE_GROUP:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_UPDATE_GROUP, lcAction, 1);

			case ADD_SMP_TO_GRP:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP,
						lcAction,
						processItemAmount,
						1);

			case DEL_SMP_FROM_GRP:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_GROUP_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_GROUP,
						lcAction,
						processItemAmount,
						1);

			case AUTO_GROUP:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_AUTO_GROUP, lcAction, 1);

			case INSERT_TAG:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_INSERT_TAG, lcAction, 1);

			case ADD_SMP_TO_TAG:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG,
						lcAction,
						processItemAmount,
						1);

			case DEL_SMP_FROM_TAG:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG,
						lcAction,
						processItemAmount,
						1);

			case DELETE_TAG:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_DELETE_TAG, lcAction, 1);

			case CLEAR_ALARM:
				return createMessageWarningOther(ACTION_LABEL_KEY_CLEAR).getText();

			case CLEAR_ALL_ALARMS:
				return createGenericDescription(ACTION_LABEL_KEY_CLEAR, ACTION_LABEL_KEY_CLEAR_ALL, processItemAmount);

			case UPDATE_LIGHT_LAT_LONG:
				return createMessageWarningOther(DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_LAT_LONG).getText();

			case UPDATE_LIGHT_POLE_ID:
				return createMessageWarningOther(DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_POLE_ID).getText();

			case GENERATE_CSV_FILE:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_GENERATE_CSV_FILE, lcAction, 0);

			case SETUP_DIMMING_CONFIGURATION:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION,
						processItemAmount);

			case RESET_LIGHT_MIN_MAX:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_RESET_MIN_MAX_VALUE_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_RESET_MIN_MAX_VALUE,
						processItemAmount);

			case INSERT_USER:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_INSERT_USER, lcAction, 1);

			case UPDATE_LIGHT_STATUS:
				return createGenericDescription(DESCRIPTION_LABEL_KEY_UPDATE_LIGHT_STATUS, lcAction, 0);

			case SET_BLINK_BY_LIGHT:
				return generateDescriptionSetBlinkByLight(processItemAmount, lcAction);

			case SET_CLEAR_OVERRIDE_BY_LIGHT:
				return createGenericDescription(
						DESCRIPTION_LABEL_KEY_SET_CLEAR_OVERRIDE_BY_LIGHT_BY_POLE_ID,
						DESCRIPTION_LABEL_KEY_SET_CLEAR_OVERRIDE_BY_LIGHT,
						processItemAmount);

			case CONFIGURATION:
				return createMessageWarningOther(DESCRIPTION_EDIT_ECOMODE_BASELINE, processItemAmount).getText();

			case IMPORT_CSV_FILE:
				return createMessageWarningOther(DESCRIPTION_EDIT_ECOMODE_BASELINE, processItemAmount).getText();

			default:
				return null;
		}
	}

	/**
	 * Generate description set blink by light.
	 *
	 * @param processItemAmount the process item amount
	 * @param lcAction the lc action
	 * @return the string
	 */
	private static String generateDescriptionSetBlinkByLight(Integer processItemAmount, LCAction lcAction)
	{
		if (String.valueOf(LightBlinkEnum.SLOW.getValue()).equals(lcAction.getActionParameters().get(1).getActionValue()))
		{
			return createGenericDescription(
					DESCRIPTION_LABEL_KEY_SET_BLINK_SLOW_BY_LIGHT_BY_POLE_ID,
					DESCRIPTION_LABEL_KEY_SET_BLINK_SLOW_BY_LIGHT,
					lcAction,
					processItemAmount,
					1);
		}
		return createGenericDescription(
				DESCRIPTION_LABEL_KEY_SET_BLINK_FAST_BY_LIGHT_BY_POLE_ID,
				DESCRIPTION_LABEL_KEY_SET_BLINK_FAST_BY_LIGHT,
				lcAction,
				processItemAmount,
				1);
	}

	/**
	 * Generate action description.
	 *
	 * @param process the process
	 * @return the string
	 */
	public static String generateActionDescription(Process process)
	{
		LCAction lcAction = process.getLcAction();
		LCActionTypeEnum actionType = lcAction.getActionType();
		switch (actionType)
		{
			case DIM:
			case TURN_ON:
			case TURN_OFF:

				return createActionDescriptionToSetIntensity(lcAction.getActionParameters().get(0));

			case SET_INTENSITY_BY_GRP:

				return createActionDescriptionToSetIntensity(lcAction.getActionParameters().get(1));

			case INSERT_SCHEDULE:

				return createGenericActionDescription(ACTION_LABEL_KEY_INSERT_SCHEDULE);

			case UPDATE_SCHEDULE:

				return createGenericActionDescription(ACTION_LABEL_KEY_UPDATE_SCHEDULE);

			case ADD_SMP_TO_SCHEDULE_EVENT:
			case ADD_SMP_TO_SCHEDULE_OFFSET:

				return createGenericActionDescription(ACTION_LABEL_KEY_APPLY_SMP_TO_SCHEDULE);

			case DEL_SCHEDULE:

				return createGenericActionDescription(ACTION_LABEL_KEY_DEL_SCHEDULE);
			case DEL_SMP_FROM_SCHEDULE_EVENT:
			case DEL_SMP_FROM_SCHEDULE_OFFSET:

				return createGenericActionDescription(ACTION_LABEL_KEY_DEL_SMP_FROM_SCHEDULE);

			case GET_LIGHT_STATUS:

				return createGenericActionDescription(ACTION_LABEL_KEY_GET_LIGHT_STATUS);

			case SETUP_DIMMING_CONFIGURATION:

				return createGenericActionDescription(ACTION_LABEL_KEY_SETUP_DIMMING_CONFIGURATION);

			case SET_PROTECTED:

				return createActionDescriptionToSetProtected(lcAction);

			case INSERT_GROUP:

				return createGenericActionDescription(ACTION_LABEL_KEY_INSERT_GROUP);

			case DEL_GRP:

				return createGenericActionDescription(ACTION_LABEL_KEY_DEL_GROUP);

			case UPDATE_GROUP:

				return createGenericActionDescription(ACTION_LABEL_KEY_UPDATE_GROUP);

			case ADD_SMP_TO_GRP:

				return createGenericActionDescription(ACTION_LABEL_KEY_ADD_SMP_TO_GROUP);

			case DEL_SMP_FROM_GRP:

				return createGenericActionDescription(ACTION_LABEL_KEY_DEL_SMP_FROM_GROUP);

			case AUTO_GROUP:

				return createGenericActionDescription(ACTION_LABEL_KEY_AUTO_GROUP);

			case INSERT_TAG:

				return createGenericActionDescription(ACTION_LABEL_KEY_INSERT_TAG);

			case ADD_SMP_TO_TAG:

				return createGenericActionDescription(ACTION_LABEL_KEY_ADD_SMP_TO_TAG);

			case DEL_SMP_FROM_TAG:

				return createGenericActionDescription(ACTION_LABEL_KEY_DEL_SMP_FROM_TAG);

			case DELETE_TAG:

				return createGenericActionDescription(ACTION_LABEL_KEY_DELETE_TAG);

			case CLEAR_ALARM:

				return createGenericActionDescription(ACTION_LABEL_KEY_CLEAR_ALARM);

			case CLEAR_ALL_ALARMS:

				return createGenericActionDescription(ACTION_LABEL_KEY_CLEAR_ALL_ALARMS);

			case UPDATE_LIGHT_LAT_LONG:

				return createGenericActionDescription(ACTION_LABEL_KEY_UPDATE_LIGHT_LAT_LONG);

			case UPDATE_LIGHT_POLE_ID:

				return createGenericActionDescription(ACTION_LABEL_KEY_UPDATE_LIGHT_POLE_ID);

			case GENERATE_CSV_FILE:

				return createGenericActionDescription(ACTION_LABEL_KEY_GENERATE_CSV_FILE);

			case RESET_LIGHT_MIN_MAX:

				return createGenericActionDescription(ACTION_LABEL_KEY_RESET_MIN_MAX_VALUE);

			case INSERT_USER:

				return createGenericActionDescription(ACTION_LABEL_KEY_INSERT_USER);

			case UPDATE_LIGHT_STATUS:

				return createGenericActionDescription(ACTION_LABEL_KEY_UPDATE_LIGHT_STATUS);

			case SET_BLINK_BY_LIGHT:

				return createActionDescriptionToSetBlinkByLight(lcAction);

			case SET_CLEAR_OVERRIDE_BY_LIGHT:

				return createGenericActionDescription(ACTION_LABEL_KEY_CLEAR_OVERRIDE);

			case CONFIGURATION:

				return createGenericActionDescription(ACTION_LABEL_EDIT_ECOMODE_BASELINE);

			case IMPORT_CSV_FILE:

				return createGenericActionDescription(ACTION_LABEL_IMPORT_CSV_FILE);

			default:
				return null;
		}
	}

	/**
	 * Creates the message warning other.
	 *
	 * @param code the code
	 * @param complements the complements
	 * @return the message
	 */
	public static Message createMessageWarningOther(String code, Object... complements)
	{
		return new Message(code, MessageSeverity.Warning, MessageLevel.Other, complements);
	}

	/**
	 * Creates the message info none.
	 *
	 * @param code the code
	 * @param complements the complements
	 * @return the message
	 */
	public static Message createMessageInfoNone(String code, Object... complements)
	{
		return new Message(code, MessageSeverity.Info, MessageLevel.None, complements);
	}

	/**
	 * Creates the action description to set blink by light.
	 *
	 * @param lcAction the lc action
	 * @return the string
	 */
	private static String createActionDescriptionToSetBlinkByLight(LCAction lcAction)
	{
		String actionValue = lcAction.getActionParameters().get(1).getActionValue();
		if (String.valueOf(LightBlinkEnum.SLOW.getValue()).equals(actionValue))
		{
			return createMessageWarningOther(ACTION_LABEL_KEY_SLOW_BLINK).getText();
		}
		return createMessageWarningOther(ACTION_LABEL_KEY_FAST_BLINK).getText();
	}

	/**
	 * Creates the action description to set protected.
	 *
	 * @param lcAction the lc action
	 * @return the string
	 */
	private static String createActionDescriptionToSetProtected(LCAction lcAction)
	{
		String actionValue = lcAction.getActionParameters().get(0).getActionValue();
		if (TRUE.equalsIgnoreCase(actionValue))
		{
			return createMessageWarningOther(LOCKED_LABEL_KEY).getText();
		}
		return createMessageWarningOther(UNLOCKED_LABEL_KEY).getText();
	}

	/**
	 * Creates the action description to set intensity.
	 *
	 * @param lcActionParameter the lc action parameter
	 * @return the string
	 */
	private static String createActionDescriptionToSetIntensity(LCActionParameter lcActionParameter)
	{
		String actionValue = lcActionParameter.getActionValue();
		if (String.valueOf(LightIntensityEnum.LEVEL_0.getPercentage()).equals(actionValue))
		{
			return createMessageWarningOther(OFF_LABEL_KEY).getText();
		}
		else if (String.valueOf(LightIntensityEnum.LEVEL_5.getPercentage()).equals(actionValue))
		{
			return createMessageWarningOther(ON_LABEL_KEY).getText();
		}
		return createMessageWarningOther(DIM_LABEL_KEY).getText();
	}

	/**
	 * Creates the description to set protected.
	 *
	 * @param processItemAmount the process item amount
	 * @param lcAction the lc action
	 * @return the string
	 */
	private static String createDescriptionToSetProtected(Integer processItemAmount, LCAction lcAction)
	{
		String locked = createMessageWarningOther(LOCKED_LABEL_KEY).getText();
		String unlocked = createMessageWarningOther(UNLOCKED_LABEL_KEY).getText();
		if ((processItemAmount != null) && (processItemAmount > 0))
		{
			if (lcAction.getActionParameters().get(0).getActionValue().equalsIgnoreCase(TRUE))
			{
				return createMessageWarningOther(DESCRIPTION_LABEL_KEY_SET_LOCK, locked, processItemAmount).getText();
			}

			return createMessageWarningOther(DESCRIPTION_LABEL_KEY_SET_LOCK, unlocked, processItemAmount).getText();
		}

		if (lcAction.getActionParameters().get(0).getActionValue().equalsIgnoreCase(TRUE))
		{
			return createMessageWarningOther(DESCRIPTION_LABEL_KEY_SET_LOCK_BY_POLE_ID, locked).getText();
		}

		return createMessageWarningOther(DESCRIPTION_LABEL_KEY_DEL_SMP_FROM_TAG, unlocked).getText();
	}

	/**
	 * Creates the generic description.
	 *
	 * @param code the code
	 * @param lcAction the lc action
	 * @param parameterPosition the parameter position
	 * @return the string
	 */
	private static String createGenericDescription(String code, LCAction lcAction, int parameterPosition)
	{
		if (isNull(lcAction)
				|| isNullOrEmpty(lcAction.getActionParameters())
				|| (parameterPosition >= lcAction.getActionParameters().size()))
		{
			return createMessageWarningOther(code).getText();
		}

		List<LCActionParameter> actionParameters = lcAction.getActionParameters();
		LCActionParameter lcActionParameter = actionParameters.get(parameterPosition);
		return createMessageWarningOther(code, lcActionParameter.getActionValue()).getText();
	}

	/**
	 * Creates the generic description.
	 *
	 * @param codeOneFlexnetId the code one flexnet id
	 * @param codeGeneral the code general
	 * @param lcAction the lc action
	 * @param processItemAmount the process item amount
	 * @param parameterPosition the parameter position
	 * @return the string
	 */
	private static String createGenericDescription(String codeOneFlexnetId,
			String codeGeneral,
			LCAction lcAction,
			int processItemAmount,
			int parameterPosition)
	{
		if (isNull(lcAction)
				|| isNullOrEmpty(lcAction.getActionParameters())
				|| (parameterPosition >= lcAction.getActionParameters().size()))
		{
			return createMessageWarningOther(codeGeneral, processItemAmount).getText();
		}

		List<LCActionParameter> actionParameters = lcAction.getActionParameters();
		LCActionParameter lcActionParameter = actionParameters.get(parameterPosition);
		String actionValue = lcActionParameter.getActionValue();

		if (processItemAmount == 1)
		{
			return createMessageWarningOther(codeOneFlexnetId, actionValue).getText();
		}
		return createMessageWarningOther(codeGeneral, processItemAmount, actionValue).getText();
	}

	/**
	 * Creates the generic description.
	 *
	 * @param codeOneFlexnetId the code one flexnet id
	 * @param codeGeneral the code general
	 * @param processItemAmount the process item amount
	 * @return the string
	 */
	private static String createGenericDescription(String codeOneFlexnetId,
			String codeGeneral,
			int processItemAmount)
	{
		if (processItemAmount > 1)
		{
			return createMessageWarningOther(codeGeneral, processItemAmount).getText();
		}
		return createMessageWarningOther(codeOneFlexnetId).getText();
	}

	/**
	 * Creates the generic action description.
	 *
	 * @param actionLabel the action label
	 * @return the string
	 */
	private static String createGenericActionDescription(String actionLabel)
	{
		return new Message(actionLabel, MessageSeverity.Info, MessageLevel.Other, new Object[] {}).getText();
	}
}
