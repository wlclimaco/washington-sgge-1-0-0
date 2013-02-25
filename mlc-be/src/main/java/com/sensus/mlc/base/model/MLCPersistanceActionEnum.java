package com.sensus.mlc.base.model;

/**
 * The Enum MLCPersistanceActionEnum.
 */
public enum MLCPersistanceActionEnum
{

	/** No action should be taken. */
	NONE,

	/** The object should be added to the data repository. */
	INSERT,

	/** The object should be updated to the data repository. */
	UPDATE,

	/** The object should be updated to the data repository. */
	INITIATE_UPDATE,

	/** The object should be removed from the data repository. */
	DELETE,

	/** The object is in error. */
	ERROR,

	/** Fetch using primary key. */
	FETCH_BY_ID,

	/** Fetch using name. */
	FETCH_BY_NAME,

	/** The object should be skipped. */
	SKIP,

	/** Unmonitor a process. */
	UNMONITOR,

	/** The RETRY. */
	RETRY,

	/** The ABORT. */
	ABORT,

	/** Fetch using rniId (Process). */
	FETCH_BY_RNI_ID,

	/** Fetch using rniId (Process). */
	FETCH_TENANT_BY_RNI_ID,

	/** Fetch monitored processes. */
	FETCH_MONITORED_PROCESSES,

	/** Fetch processes. */
	FETCH_PROCESSES,

	/** Fetch a result set. */
	FETCH,

	/** Fetch a result set. */
	APPLY_SMP_SCHEDULE,

	/** Fetch a result set. */
	CLEAR_SMP_SCHEDULE,

	/** Gateway Specific. */
	GATEWAY_AUTO_DISCOVERY,

	/** Gateway Specific. */
	GATEWAY_ADD_SMP_TO_TAG,

	/** Gateway Specific. */
	GATEWAY_FORCED_STATUS,

	/** Gateway Specific. */
	GATEWAY_SET_LIGHT_INTENSITY,

	/** Gateway Specific. */
	GATEWAY_BINDING,

	/** Gateway Specific. */
	GATEWAY_ALARM,

	/** Gateway Specific. */
	GATEWAY_UNSOLICITED_STATUS,

	/** Gateway Specific. */
	GATEWAY_SETUP,

	/** Gateway Specific. */
	GATEWAY_VERIFY_RNI_ID,

	/** Gateway Specific. */
	GATEWAY_CLEAR_SCHEDULE,

	/** Gateway Specific. */
	GATEWAY_APPLY_SCHEDULE,

	/** Gateway Specific. */
	GATEWAY_CLEAR_ALARMS,

	/** The GATEWA y_ upser t_ ligh t_ property. */
	GATEWAY_UPSERT_LIGHT_PROPERTY,

	/** The GATEWA y_ appl y_ smartpoin t_ properties. */
	GATEWAY_APPLY_SMARTPOINT_PROPERTIES,

	/** The GATEWA y_ channe l_ setu p_ audit. */
	GATEWAY_CHANNEL_SETUP_AUDIT,

	/** Add Smart Points to Group *. */
	ADD_SMP_TO_GRP,

	/** Delete Smart Points from Group *. */
	DEL_SMP_FROM_GRP,

	/** Add Smartpoints to Tag *. */
	ADD_SMP_TO_TAG,

	/** The Del Smartpoint from Tag. */
	DEL_SMP_FROM_TAG,

	/** The Fetch Light Status. */
	FETCH_LIGHT_STATUS,

	/** The Fetch By Light. */
	FETCH_BY_LIGHT,

	/** The Fetch By SmartPoint. */
	FETCH_BY_SMARTPOINT,

	/** The UPDAT e_ ligh t_ property. */
	UPDATE_LIGHT_PROPERTY,

	/** The Update light_intensity for Group. */
	UPDATE_LIGHT_INTENSITY,

	/** The Groups Fetch By Light. */
	FETCH_GROUPS_BY_LIGHT,

	/** The fetch groups by id list. */
	FETCH_GROUPS_BY_ID_LIST,

	/** The Groups Fetch By SmartPoint. */
	FETCH_GROUPS_BY_SMARTPOINT,

	/** The Update light status for Smart point. */
	UPDATE_LIGHT_STATUS,

	/** The fetch light consumption for Smart point. */
	FETCH_LIGHT_CONSUMPTION,

	/** The fetch light voltage for Smart point. */
	FETCH_AVERAGE_LIGHT_VOLTAGE,

	/** The fetch light current for Smart point. */
	FETCH_AVERAGE_LIGHT_CURRENT,

	/** The fetch light consumption for Smart point. */
	FETCH_AVERAGE_LIGHT_CONSUMPTION,

	/** The update light protected for Smart point. */
	UPDATE_LIGHT_PROTECTED,

	/** The Delete Alarm protected for Smart point. */
	DELETE_ALERT,

	/** The Upsert Light Property for Smart point. */
	UPSERT_LIGHT_PROPERTY,

	/** The UPDAT e_ ligh t_ la t_ lng. */
	UPDATE_LIGHT_LAT_LNG,

	/** The UPSER t_ settings. */
	UPSERT_SETTINGS,

	/** The upsert ecomode. */
	UPSERT_ECOMODE,

	/** The FETC h_ use r_ settings. */
	FETCH_USER_SETTINGS,

	/** The FETC h_ syste m_ settings. */
	FETCH_SYSTEM_SETTINGS,

	/** The FETC h_ tenan t_ b y_ serve r_ name. */
	FETCH_TENANT_BY_SERVER_NAME,

	/** The custom search object should be added to the data repository. */
	INSERT_CUSTOM_SEARCH,

	/** The custom search object should be removed from the data repository. */
	DELETE_CUSTOM_SEARCH,

	/** The FETC h_ statu s_ messag e_ b y_ light. */
	FETCH_STATUS_MESSAGE_BY_LIGHT,

	/** The UPDAT e_ syste m_ settings. */
	UPDATE_SYSTEM_SETTINGS,

	/** The FETC h_ statu s_ messag e_ b y_ ligh t_ id. */
	FETCH_STATUS_MESSAGE_BY_LIGHT_ID,

	/** The UPDAT e_ cs v_ downloaded. */
	UPDATE_CSV_DOWNLOADED,

	/** The SUMMAR y_ fil e_ csv. */
	SUMMARY_FILE_CSV,

	/** The APPL y_ dimmin g_ configuration. */
	APPLY_DIMMING_CONFIGURATION,

	/** The FETC h_ analytic s_ b y_ group. */
	FETCH_ANALYTICS_BY_GROUP,

	/** The FETC h_ dashboar d_ resume. */
	FETCH_DASHBOARD_RESUME,

	/** The FETC h_ analytic s_ b y_ date. */
	FETCH_ANALYTICS_BY_DATE,

	/** The FETC h_ analytic s_ b y_ statu s_ id. */
	FETCH_ANALYTICS_BY_STATUS_ID,

	/** The FETC h_ dashboar d_ header. */
	FETCH_DASHBOARD_HEADER,

	/** The COUN t_ lights. */
	COUNT_LIGHTS,

	/** The FETC h_ updat e_ ligh t_ status. */
	FETCH_UPDATE_LIGHT_STATUS,

	/** The INITIAT e_ delet e_ schedule. */
	INITIATE_DELETE_SCHEDULE,

	/** The FETC h_ al l_ custo m_ search. */
	FETCH_ALL_CUSTOM_SEARCH,

	/** The GENERAT e_ fil e_ csv. */
	GENERATE_FILE_CSV,

	/** The insert process csv. */
	INSERT_PROCESS_CSV,

	/** The FETC h_ ligthin g_ configuration s_ b y_ par t_ number. */
	FETCH_LIGTHING_CONFIGURATIONS_BY_PART_NUMBER,

	/** The UPSER t_ user. */
	UPSERT_USER,

	/** The FETC h_ ligh t_ history. */
	FETCH_LIGHT_HISTORY,

	/** The Fetch smartpoint by group to map. */
	FETCH_SMARTPOINT_BY_GROUP_TO_MAP,

	/** The fetch count lights from groups. */
	FETCH_COUNT_LIGHTS_FROM_GROUPS,

	/** The Fetch smartpoint by schedule to map. */
	FETCH_SMARTPOINT_BY_SCHEDULE_TO_MAP,

	/** The RESE t_ mi n_ max. */
	RESET_MIN_MAX,

	/** The FETC h_ b y_ propert y_ id. */
	FETCH_BY_PROPERTY_ID,

	/** The FETC h_ al l_ users. */
	FETCH_ALL_USERS,

	/** The INSER t_ use r_ columns. */
	INSERT_USER_COLUMNS,

	/** The INSER t_ use r_ filters. */
	INSERT_USER_FILTERS,

	/** The CHANG e_ password. */
	CHANGE_PASSWORD,

	/** The import csv file. */
	IMPORT_CSV_FILE,

	FETCH_SMARTPOINT_MAP,

	INSERT_COLUMN_FILTERS,

	FETCH_STATUS_MESSAGE_BY_ID,

	FETCH_COLUMN_FILTERS;

	public static String getSlcActionName()
	{
		return "SLC_ACTION_NAME";
	}
}