package com.sensus.mlc.settings.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_TENANT_BY_SERVER_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT_USER_COLUMNS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE_SYSTEM_SETTINGS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPSERT_SETTINGS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.bcl.ISettingsBCL;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class SettingsBCFimpl.
 */
public class SettingsBCFImpl extends AbstractBaseBCF implements ISettingsBCF
{
	/** The Constant SETTINGS_REQUEST_NAME. */
	private static final String SETTINGS_REQUEST_NAME = SettingsRequest.class.getSimpleName();

	/** The Constant SETTINGS_NAME. */
	private static final String TENANT_REQUEST_NAME = TenantRequest.class.getSimpleName();

	/** The Constant COLUMN_FILTER_REQUEST_NAME. */
	private static final String COLUMN_FILTER_REQUEST_NAME = ColumnFilterRequest.class.getSimpleName();

	/** The Constant LIGHTING_CONTROL_REQUEST_NAME. */
	private static final String LIGHTING_CONTROL_REQUEST_NAME = LightingControlRequest.class.getSimpleName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SettingsBCFImpl.class);

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.settingsbcfimpl.defaultexception";

	/** The smart point bcl. */
	private ISettingsBCL settingsBCL; // injected by Spring through setter

	/** The setting list validation controler. */
	private ValidationController settingListValidationController;

	/** The tenant request validator controller. */
	private ValidationController tenantRequestValidationController;

	/** The column filter validation controller. */
	private ValidationController columnFilterValidationController;

	/**
	 * Gets the setting list validation controller.
	 * 
	 * @return the setting list validation controller
	 */
	public ValidationController getSettingListValidationController()
	{
		return settingListValidationController;
	}

	/**
	 * Sets the setting list validation controller.
	 * 
	 * @param settingListValidationController the new setting list validation controller
	 */
	public void setSettingListValidationController(ValidationController settingListValidationController)
	{
		this.settingListValidationController = settingListValidationController;
	}

	/**
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the tenant request validation controller.
	 * 
	 * @param tenantRequestValidationController the new tenant request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/**
	 * Gets the column filter validation controller.
	 * 
	 * @return the column filter validation controller
	 */
	public ValidationController getColumnFilterValidationController()
	{
		return columnFilterValidationController;
	}

	/**
	 * Sets the column filter validation controller.
	 * 
	 * @param columnFilterValidationController the new column filter validation controller
	 */
	public void setColumnFilterValidationController(ValidationController columnFilterValidationController)
	{
		this.columnFilterValidationController = columnFilterValidationController;
	}

	/**
	 * Sets the settings bcl.
	 * 
	 * @param settingsBCL the new settings bcl
	 */
	public void setSettingsBCL(ISettingsBCL settingsBCL)
	{
		this.settingsBCL = settingsBCL;
	}

	/**
	 * Gets the settings bcl.
	 * 
	 * @return the settings bcl
	 */
	public ISettingsBCL getSettingsBCL()
	{
		return settingsBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#upsertSettings(com.sensus.mlc.base.model.request.LightingControlRequest)
	 */
	@Override
	public SettingsResponse upsertSettings(SettingsRequest settingsRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPSERT_SETTINGS);
			context.putObjectToBeValidated(SETTINGS_REQUEST_NAME, settingsRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.SETTING_LIST.getValue(),
					settingsRequest.getSettings());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.ACTUAL_PASSWORD.getValue(),
					settingsRequest.getActualPassword());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.NEW_PASSWORD.getValue(),
					settingsRequest.getNewPassword());

			if (getLightingControlRequestValidationController().validate(context) &&
					getSettingListValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().upsertSettings(settingsRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#updateSystemSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public SettingsResponse updateSystemSettings(SettingsRequest settingsRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_SYSTEM_SETTINGS);
			context.putObjectToBeValidated(SETTINGS_REQUEST_NAME, settingsRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.SETTING_LIST.getValue(),
					settingsRequest.getSettings());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.ACTUAL_PASSWORD.getValue(),
					settingsRequest.getActualPassword());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.NEW_PASSWORD.getValue(),
					settingsRequest.getNewPassword());

			if (getLightingControlRequestValidationController().validate(context) &&
					getSettingListValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().updateSystemSettings(settingsRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#fetchUserSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public SettingsResponse fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LIGHTING_CONTROL_REQUEST_NAME, lightingControlRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().fetchUserSettings(lightingControlRequest);
				response.setSettings(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#fetchSystemSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public SettingsResponse fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LIGHTING_CONTROL_REQUEST_NAME, lightingControlRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().fetchSystemSettings(lightingControlRequest);
				response.setSettings(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#fetchTenantByServerName(com.sensus.mlc.settings.model.request.TenantRequest
	 * )
	 */
	@Override
	public TenantResponse fetchTenantByServerName(TenantRequest tenantRequest)
	{
		TenantResponse response = new TenantResponse();
		InternalResultsResponse<Tenant> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_TENANT_BY_SERVER_NAME);
			context.putObjectToBeValidated(TENANT_REQUEST_NAME, tenantRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getTenantRequestValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().fetchTenantByServerName(tenantRequest);
				response.setTenant(internalResponse.getFirstResult());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcf.ISettingsBCF#insertUserColumns(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT_USER_COLUMNS);
			context.putObjectToBeValidated(COLUMN_FILTER_REQUEST_NAME, columnFilterRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.FILTER_LIST.getValue(),
					columnFilterRequest.getFilters());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.COLUMN_LIST.getValue(),
					columnFilterRequest.getListColumn());

			if (getLightingControlRequestValidationController().validate(context) &&
					getColumnFilterValidationController().validate(context))
			{
				internalResponse = getSettingsBCL().insertUserColumnFilter(columnFilterRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}
}