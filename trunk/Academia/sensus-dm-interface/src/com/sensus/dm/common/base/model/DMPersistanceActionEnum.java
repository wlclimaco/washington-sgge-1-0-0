package com.sensus.dm.common.base.model;

/**
 * The Enum DMPersistanceActionEnum.
 */
public enum DMPersistanceActionEnum
{
	/** No action should be taken. */
	NONE,

	/** The object should be added to the data repository. */
	INSERT,

	/** The insert schedule. */
	INSERT_SCHEDULE,

	/** The insert tag. */
	INSERT_TAG,

	/** The object should be updated to the data repository. */
	UPDATE,

	/** The update schedule. */
	UPDATE_SCHEDULE,

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

	/** The ABORT. */
	ABORT,

	/** The fetch count monitored processes. */
	FETCH_COUNT_MONITORED_PROCESSES,

	/** The fetch all process. */
	FETCH_ALL_PROCESS,

	/** The fetch process by id. */
	FETCH_PROCESS_BY_ID,

	/** The FETC h_ toda y_ processes. */
	FETCH_TODAY_PROCESSES,

	/** Fetch a result set. */
	FETCH,

	/** The APPLY devices to action. */
	APPLY_DEVICES_TO_ACTION,

	/** The apply on demand. */
	APPLY_ON_DEMAND,

	/** Add Smart Points to Group *. */
	ADD_SMP_TO_GRP,

	/** The ADD_SM_FROM_FILE_TO_GRP. */
	ADD_SM_FROM_FILE_TO_GRP,

	/** The AD d_ s m_ fro m_ file. */
	ADD_SM_FROM_FILE,

	/** Delete Smart Points from Group *. */
	DEL_SMP_FROM_GRP,

	/** Add Smartpoints to Tag *. */
	ADD_SMP_TO_TAG,

	/** The Del Smartpoint from Tag. */
	DEL_SMP_FROM_TAG,

	/** The Fetch By Meter. */
	FETCH_BY_DEVICE,

	/** The Update meter property. */
	UPDATE_METER_PROPERTY,

	/** The Groups Fetch By Meter. */
	FETCH_GROUPS_BY_METER,

	/** The FETCH_ALL_GROUPS. */
	FETCH_ALL_GROUPS,

	/** The FETCH_ALL_GROUPS_BY_DEVICE. */
	FETCH_ALL_GROUPS_BY_DEVICE,

	/** The update meter locked for Smart point. */
	UPDATE_METER_LOCKED,

	/** The UPDATE_DEVICE_LAT_LNG. */
	UPDATE_DEVICE_LAT_LNG,

	/** The UPSERT_SETTINGS. */
	UPSERT_SETTINGS,

	/** The FETCH_USER_SETTINGS. */
	FETCH_USER_SETTINGS,

	/** The fetch updated load profile. */
	FETCH_UPDATED_LOAD_PROFILE,

	/** INSERT_USER. */
	INSERT_USER,

	/** The FETCH_ALL_USERS. */
	FETCH_ALL_USERS,

	/** The FETCH_ALL. */
	FETCH_ALL,

	/** The FETCH_SYSTEM_SETTINGS. */
	FETCH_SYSTEM_SETTINGS,

	/** The fetch all connection state. */
	FETCH_ALL_CONNECTION_STATE,

	/** The custom search object should be added to the data repository. */
	INSERT_CUSTOM_SEARCH,

	/** The INSERT_METER_NOTES. */
	INSERT_METER_NOTES,

	/** The DELETE_METER_NOTES. */
	DELETE_METER_NOTES,

	/** The custom search object should be removed from the data repository. */
	DELETE_CUSTOM_SEARCH,

	/** The UPDATE_SYSTEM_SETTINGS. */
	UPDATE_SYSTEM_SETTINGS,

	/** The UPDATE_CSV_DOWNLOADED. */
	UPDATE_CSV_DOWNLOADED,

	/** The COUNT_METERS. */
	COUNT_METERS,

	/** The GENERATE_FILE_CSV. */
	GENERATE_FILE_CSV,

	/** The Fetch Dashboard Resume. */
	FETCH_DASHBOARD_RESUME,

	/** The INSERT_CSV_PROCESS. */
	INSERT_CSV_PROCESS,

	/** The GENERATE_FILE_CSV_TOU. */
	GENERATE_FILE_CSV_TOU,

	/** The GENERATE_FILE_CSV_SNAPSHOT. */
	GENERATE_FILE_CSV_SNAPSHOT,

	/** The generate file csv load profile read. */
	GENERATE_FILE_CSV_LOAD_PROFILE_READ,

	/** The GENERATE_FILE_CSV_INTERVAL_READ. */
	GENERATE_FILE_CSV_INTERVAL_READ,

	/** The generate file csv peak read. */
	GENERATE_FILE_CSV_PEAK_READ,

	/** The generate file csv water data read. */
	GENERATE_FILE_CSV_WATER_GAS_DATA_READ,

	/** The FETCH_DEVICES_TO_MAP. */
	FETCH_DEVICES_TO_MAP,

	/** The FETCH devices. */
	FETCH_DEVICES,

	/** The UPDATE_PROCESS_STATUS. */
	UPDATE_PROCESS_STATUS,

	/** The FETCH_MONITORED_PROCESS. */
	FETCH_MONITORED_PROCESS,

	/** The FETCH_ACTION_TYPE_BY_DESCRIPTION. */
	FETCH_ACTION_TYPE_BY_DESCRIPTION,

	/** The FETCH_ALL_RNI_ACTION_TYPES. */
	FETCH_ALL_RNI_ACTION_TYPES,

	/** The APPLY_DEVICE_TO_ACTION. */
	APPLY_DEVICE_TO_ACTION,

	/** The FETCH_ALL_ACTION_TYPES. */
	FETCH_ALL_ACTION_TYPES,

	/** The FETCH_ALL_HAN_DECICES_BY_DEVICE. */
	FETCH_ALL_HAN_DECICES_BY_METER,

	/** FETCH_HAN_DECICES_BY_DEVICE. */
	FETCH_HAN_DEVICE_BY_ID,

	/** The FETCH_SCHEDULE_BY_GROUP. */
	FETCH_SCHEDULE_BY_GROUP,

	/** The fetch schedule by device. */
	FETCH_SCHEDULE_BY_DEVICE,

	/** The FETCH_DEVICE_BY_ID. */
	FETCH_DEVICE_BY_ID,

	/** The fetch device by id import. */
	FETCH_DEVICE_BY_ID_IMPORT,

	/** The fetch all water data read. */
	FETCH_ALL_WATER_GAS_DATA_READ,

	/** The FETCH_ALL_TOU_READ. */
	FETCH_ALL_TOU_READ,

	/** The fetch all interval read. */
	FETCH_ALL_INTERVAL_READ,

	/** The fetch all load profile read. */
	FETCH_ALL_LOAD_PROFILE_READ,

	/** The fetch all snapshot. */
	FETCH_ALL_SNAPSHOT,

	/** FETCH_ALL_COLUMN_FILTERS. */
	FETCH_ALL_COLUMN_FILTERS,

	/** INSERT_COLUMN_FILTERS. */
	UPDATE_COLUMN_FILTERS,

	/** FETCH_VALID_DEVICE_TYPES. */
	FETCH_VALID_DEVICE_TYPES,

	/** The INSER_HAN_DEVICE. */
	INSERT_HAN_DEVICE,

	/** FETCH_ALL_MANUFECTURE_BY_HAN_DECICE_TYPE. */
	FETCH_ALL_MANUFACTURE_BY_HAN_DEVICE_TYPE,

	/** FETCH_ALL_MODEL_BY_HAN_DECICE_TYPE. */
	FETCH_ALL_MODEL_BY_HAN_DEVICE_TYPE,

	/** The FETCH_LCM_RELAY_BY_DEVICE. */
	FETCH_LCM_RELAY_BY_DEVICE,

	/** The FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION_BY_DEVICE. */
	FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION_BY_DEVICE,

	/** The FETCH_DEMAND_RESPONSE_SETUP_BY_DEVICE. */
	FETCH_DEMAND_RESPONSE_SETUP_BY_DEVICE,

	/** The UPDATE_STATUS. */
	UPDATE_STATUS,

	/** The FETC h_ schedul e_ b y_ action. */
	FETCH_SCHEDULE_BY_ACTION,

	/** The FETCH_ALL_BY_PREMISE_ID. */
	FETCH_ALL_BY_PREMISE_ID,

	/** The FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION. */
	FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION,

	/** The fetch demand response summary. */
	FETCH_DEMAND_RESPONSE_SUMMARY,

	/** The FETC h_ communicatio n_ summary. */
	FETCH_COMMUNICATION_SUMMARY,

	/** The fetch import han summary. */
	FETCH_IMPORT_HAN_SUMMARY,

	/** The fetch demand read ping. */
	FETCH_DEMAND_READ_PING_SUMMARY,

	/** The fetch process send han text message. */
	FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE,

	/** The fetch process items by schedule. */
	FETCH_PROCESS_ITEMS_BY_SCHEDULE,

	/** The fetch all process category. */
	FETCH_ALL_PROCESS_CATEGORY,

	/** The fetch process items by process id. */
	FETCH_PROCESS_ITEMS_BY_PROCESS_ID,

	/** The fetch all schedule. */
	FETCH_ALL_SCHEDULE,

	/** The generate group file csv. */
	GENERATE_GROUP_FILE_CSV,

	/** The generate today file csv. */
	GENERATE_TODAY_FILE_CSV,

	/** The fetch all custom search. */
	FETCH_ALL_CUSTOM_SEARCH,

	/** The fetch all device type count. */
	FETCH_ALL_DEVICE_TYPE_COUNT,

	/** The insert group. */
	INSERT_GROUP,

	/** The fetch all tag. */
	FETCH_ALL_TAG,

	/** The fetch all peak demand. */
	FETCH_ALL_PEAK_DEMAND,

	/** The fetch services by device type. */
	FETCH_SERVICES_BY_DEVICE_TYPE,

	/** The fetch dash board water header. */
	FETCH_DASHBOARD_WATER_HEADER,

	/** The fetch dash board gas header. */
	FETCH_DASHBOARD_GAS_HEADER,

	/** The fetch alarm history. */
	FETCH_ALARM_HISTORY,

	/** The fetch tenant by description. */
	FETCH_TENANT_BY_DESCRIPTION,

	/** The fetch devices bounds to map. */
	FETCH_DEVICES_BOUNDS_TO_MAP,

	/** The fetch water leak report. */
	FETCH_WATER_LEAK_REPORT,

	/** The fetch lcm relays by device. */
	FETCH_LCM_RELAYS_BY_DEVICE,

	/** The generate water leak report csv. */
	GENERATE_WATER_LEAK_REPORT_CSV,

	/** The fetch device notes. */
	FETCH_DEVICE_NOTES,

	/** The insert opt out list. */
	INSERT_OPT_OUT_LIST,

	/** The delete opt out list. */
	DELETE_OPT_OUT_LIST,

	/** The expire process items. */
	EXPIRE_PROCESS_ITEMS,

	/** The fetch relay. */
	FETCH_RELAY,

	/** The FETCh relays by process id. */
	FETCH_RELAYS_BY_PROCESS_ID,

	/** The fetch quarantine count. */
	FETCH_QUARANTINE_COUNT;

	/**
	 * Gets the default name.
	 * 
	 * @return the default name
	 */
	public static String getDefaultName()
	{
		return DMPersistanceActionEnum.class.getSimpleName();
	}
}
