package com.sensus.dm.common.base.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class TenantRequestValidator.
 * 
 * @author QAT Global
 */
public class TenantRequestValidator implements IValidator
{
	/** The Constant FILE_NAME_REQUIRED. */
	private static final String FILE_NAME_REQUIRED =
			"sensus.epm.tenantrequestvalidator.filename.required";

	/** The Constant FILE_NAME_INVALID. */
	private static final String FILE_NAME_INVALID =
			"sensus.epm.tenantrequestvalidator.filename.invalid";

	/** The Constant PROCESS_ID_REQUIRED. */
	private static final String PROCESS_ID_REQUIRED =
			"sensus.epm.tenantrequestvalidator.processid.required";

	/** The Constant TENANT_REQUEST_REQUIRED. */
	private static final String TENANT_REQUEST_REQUIRED =
			"sensus.epm.tenantrequestvalidator.tenantrequest.required";

	/** The Constant USER_ID_REQUIRED. */
	private static final String USER_ID_REQUIRED =
			"sensus.epm.tenantrequestvalidator.user.id.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	private static final String USER_CONTEXT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.user.context.required";

	/** The Constant LOCALE_REQUIRED. */
	private static final String LOCALE_REQUIRED =
			"sensus.epm.tenantrequestvalidator.locale.required";

	/** The Constant SERVICE_TYPE_ENUM_REQUIRED. */
	private static final String SERVICE_TYPE_ENUM_REQUIRED =
			"sensus.epm.tenantrequestvalidator.service.type.required";

	/** The Constant TENANT_REQUIRED. */
	private static final String TENANT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.tenant.required";

	/** The Constant CUSTOMER_ID_REQUIRED. */
	private static final String CUSTOMER_ID_REQUIRED =
			"sensus.epm.tenantrequestvalidator.customer.id.required";

	/** The Constant DATE_FORMAT_REQUIRED. */
	private static final String DATE_FORMAT_REQUIRED =
			"sensus.epm.tenantrequestvalidator.dateformat.required";

	/** The file name length. */
	private int fileNameLength;

	/**
	 * Gets the file name length.
	 * 
	 * @return the file name length
	 */
	public int getFileNameLength()
	{
		return fileNameLength;
	}

	/**
	 * Sets the file name length.
	 * 
	 * @param fileNameLength the new file name length
	 */
	public void setFileNameLength(int fileNameLengthParam)
	{
		fileNameLength = fileNameLengthParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{

		TenantRequest tenantRequest =
				(TenantRequest)validationContext.getObjectToBeValidated(TenantRequest.class.getSimpleName());

		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(tenantRequest))
		{
			validationContext.getMessages().add(
					MessageInfo.createFieldValidationError(TENANT_REQUEST_REQUIRED));
			return;
		}

		switch (action)
		{
			case ADD_SM_FROM_FILE_TO_GRP:
				validateFileName(validationContext.getMessages(), tenantRequest.getFileName());
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case APPLY_DEVICE_TO_ACTION:
			case INSERT_HAN_DEVICE:
			case ADD_SMP_TO_TAG:
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case FETCH_ALL_PEAK_DEMAND:
			case FETCH_UPDATED_LOAD_PROFILE:
			case FETCH_ALL_INTERVAL_READ:
			case FETCH_ALL_LOAD_PROFILE_READ:
			case FETCH_ALL_SNAPSHOT:
			case FETCH_ALL_TOU_READ:
			case INSERT_USER:
			case FETCH_DEVICES:
			case DEL_SMP_FROM_GRP:
			case ADD_SMP_TO_GRP:
			case UPDATE:
			case INSERT:
			case FETCH_ALL_GROUPS_BY_DEVICE:
			case UPDATE_COLUMN_FILTERS:
			case FETCH_ALL_COLUMN_FILTERS:
			case DELETE_CUSTOM_SEARCH:
			case DELETE:
			case FETCH_SCHEDULE_BY_ACTION:
			case FETCH_SCHEDULE_BY_DEVICE:
			case FETCH_SCHEDULE_BY_GROUP:
			case FETCH_BY_ID:
			case FETCH_BY_DEVICE:
			case UPSERT_SETTINGS:
			case UPDATE_STATUS:
			case FETCH_PROCESS_ITEMS_BY_SCHEDULE:
			case FETCH_PROCESS_ITEMS_BY_PROCESS_ID:
			case UPDATE_CSV_DOWNLOADED:
			case FETCH_ACTION_TYPE_BY_DESCRIPTION:
			case FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE:
			case FETCH_ALL_PROCESS_CATEGORY:
			case FETCH_USER_SETTINGS:
			case INSERT_OPT_OUT_LIST:
			case EXPIRE_PROCESS_ITEMS:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				break;
			case FETCH_PROCESS_BY_ID:
			case FETCH_COMMUNICATION_SUMMARY:
			case FETCH_DEMAND_RESPONSE_SUMMARY:
			case FETCH_IMPORT_HAN_SUMMARY:
			case FETCH_DEMAND_READ_PING_SUMMARY:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateUserContextLocale(validationContext.getMessages(), tenantRequest.getUserContext());
				break;
			case FETCH_MONITORED_PROCESS:
			case FETCH_TODAY_PROCESSES:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateUserContextLocale(validationContext.getMessages(), tenantRequest.getUserContext());
				validateServiceTypeEnum(validationContext.getMessages(), tenantRequest);
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case GENERATE_FILE_CSV_PEAK_READ:
				validateDateFormat(validationContext.getMessages(), tenantRequest.getDateFormat());
			case ADD_SM_FROM_FILE:
				ValidationUtil.isNull(tenantRequest.getProcessId(), PROCESS_ID_REQUIRED,
						validationContext.getMessages());
				validateFileName(validationContext.getMessages(), tenantRequest.getFileName());
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case GENERATE_FILE_CSV_WATER_GAS_DATA_READ:
				ValidationUtil.isNull(tenantRequest.getProcessId(), PROCESS_ID_REQUIRED,
						validationContext.getMessages());
				validateFileName(validationContext.getMessages(), tenantRequest.getFileName());
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateUserContextLocale(validationContext.getMessages(), tenantRequest.getUserContext());
				validateServiceTypeEnum(validationContext.getMessages(), tenantRequest);
				break;
			case GENERATE_FILE_CSV_INTERVAL_READ:
			case GENERATE_FILE_CSV_LOAD_PROFILE_READ:
			case GENERATE_FILE_CSV_SNAPSHOT:
			case GENERATE_TODAY_FILE_CSV:
				validateDateFormat(validationContext.getMessages(), tenantRequest.getDateFormat());
			case GENERATE_FILE_CSV_TOU:
				ValidationUtil.isNull(tenantRequest.getProcessId(), PROCESS_ID_REQUIRED,
						validationContext.getMessages());
				validateFileName(validationContext.getMessages(), tenantRequest.getFileName());
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateUserContextLocale(validationContext.getMessages(), tenantRequest.getUserContext());
				break;
			case FETCH_BY_NAME:
			case FETCH_ALL_WATER_GAS_DATA_READ:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateServiceTypeEnum(validationContext.getMessages(), tenantRequest);
				break;
			case FETCH_ALL_DEVICE_TYPE_COUNT:
			case INSERT_SCHEDULE:
			case FETCH_ALL_SCHEDULE:
			case UPDATE_SCHEDULE:
			case FETCH_ALL_CUSTOM_SEARCH:
			case FETCH_ALL_GROUPS:
			case FETCH_ALL_TAG:
			case INSERT_CUSTOM_SEARCH:
			case INSERT_GROUP:
			case INSERT_TAG:
			case FETCH_ALL_PROCESS:
			case FETCH_COUNT_MONITORED_PROCESSES:
			case FETCH_QUARANTINE_COUNT:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateServiceTypeEnum(validationContext.getMessages(), tenantRequest);
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case FETCH_ALL:
			case FETCH_DASHBOARD_GAS_HEADER:
			case FETCH_DASHBOARD_WATER_HEADER:
			case INSERT_CSV_PROCESS:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case GENERATE_FILE_CSV:
			case GENERATE_GROUP_FILE_CSV:
				ValidationUtil.isNull(tenantRequest.getProcessId(), PROCESS_ID_REQUIRED,
						validationContext.getMessages());
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				validateFileName(validationContext.getMessages(), tenantRequest.getFileName());
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateUserContextLocale(validationContext.getMessages(), tenantRequest.getUserContext());
				break;
			case FETCH_TENANT_BY_DESCRIPTION:
			case FETCH_DEVICE_BY_ID_IMPORT:
			case FETCH_WATER_LEAK_REPORT:
			case GENERATE_WATER_LEAK_REPORT_CSV:
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			case ABORT:
				validateUserContext(validationContext.getMessages(), tenantRequest.getUserContext());
				validateUserContextLocale(validationContext.getMessages(), tenantRequest.getUserContext());
				validateTenant(validationContext.getMessages(), tenantRequest.getTenant());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate file name.
	 * 
	 * @param list the list
	 * @param fileName the file name
	 */
	private void validateFileName(List<MessageInfo> list, String fileName)
	{
		if (ValidationUtil.isNullOrEmpty(fileName))
		{
			list.add(MessageInfo.createFieldValidationError(FILE_NAME_REQUIRED));
			return;
		}

		if (fileName.length() > getFileNameLength())
		{
			list.add(MessageInfo.createFieldValidationError(FILE_NAME_INVALID, getFileNameLength()));
			return;
		}
	}

	/**
	 * Validate user context.
	 * 
	 * @param list the list
	 * @param userContext the user context
	 */
	private void validateUserContext(List<MessageInfo> list, UserContext userContext)
	{
		if (ValidationUtil.isNull(userContext))
		{
			list.add(MessageInfo.createFieldValidationError(USER_CONTEXT_REQUIRED));
			return;
		}

		ValidationUtil.isNullOrEmpty(userContext.getUserId(), USER_ID_REQUIRED, list);
	}

	/**
	 * Validate user context locale.
	 * 
	 * @param list the list
	 * @param userContext the user context
	 */
	private void validateUserContextLocale(List<MessageInfo> list, UserContext userContext)
	{
		if (!ValidationUtil.isNull(userContext) && ValidationUtil.isNull(userContext.getLocaleString()))
		{
			list.add(MessageInfo.createFieldValidationError(LOCALE_REQUIRED));
			return;
		}
	}

	/**
	 * Validate service type enum.
	 * 
	 * @param list the list
	 * @param tenantRequest the tenant request
	 */
	private void validateServiceTypeEnum(List<MessageInfo> list, TenantRequest tenantRequest)
	{
		ValidationUtil.isNull(tenantRequest.getServiceTypeEnum(), SERVICE_TYPE_ENUM_REQUIRED, list);
	}

	/**
	 * Validate tenant.
	 * 
	 * @param list the list
	 * @param tenant the tenant
	 */
	private void validateTenant(List<MessageInfo> list, DMTenant tenant)
	{
		if (ValidationUtil.isNull(tenant))
		{
			list.add(MessageInfo.createFieldValidationError(TENANT_REQUIRED));
			return;
		}

		ValidationUtil.isNullOrEmpty(tenant.getKey(), CUSTOMER_ID_REQUIRED, list);
	}

	/**
	 * Validate date format.
	 * 
	 * @param list the list
	 * @param dateFormat the date format
	 */
	private void validateDateFormat(List<MessageInfo> list, String dateFormat)
	{
		if (ValidationUtil.isNullOrEmpty(dateFormat))
		{
			list.add(MessageInfo.createFieldValidationError(DATE_FORMAT_REQUIRED));
			return;
		}
	}
}
