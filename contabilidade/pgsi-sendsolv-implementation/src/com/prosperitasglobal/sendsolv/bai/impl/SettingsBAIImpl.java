package com.prosperitasglobal.sendsolv.bai.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bac.ISettingsBAC;
import com.prosperitasglobal.sendsolv.bai.ISettingsBAI;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.settings.model.Setting;
import com.prosperitasglobal.sendsolv.settings.model.request.SettingsRequest;
import com.prosperitasglobal.sendsolv.settings.model.response.SettingsResponse;
import com.prosperitasglobal.sendsolv.tenant.model.Tenant;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.prosperitasglobal.sendsolv.tenant.model.response.TenantResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationController;

/**
 * The Class SettingsBCFimpl.
 */
public class SettingsBAIImpl implements ISettingsBAI
{
	/** The Constant SETTINGS_REQUEST_NAME. */
	private static final String SETTINGS_REQUEST_NAME = SettingsRequest.class.getSimpleName();

	/** The Constant SETTINGS_NAME. */
	private static final String TENANT_REQUEST_NAME = TenantRequest.class.getSimpleName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SettingsBAIImpl.class);

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.settingsbcfimpl.defaultexception";

	/** The settings bcl. */
	private ISettingsBAC settingsBAC; // injected by Spring through setter

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
	public void setSettingsBCL(ISettingsBAC settingsBCL)
	{
		settingsBAC = settingsBCL;
	}

	/**
	 * Gets the settings bcl.
	 *
	 * @return the settings bcl
	 */
	public ISettingsBAC getSettingsBCL()
	{
		return settingsBAC;
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

			internalResponse = getSettingsBCL().upsertSettings(settingsRequest);
			response.setSettings(internalResponse.getResultsList());

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
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#upsertSystemSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public SettingsResponse upsertSystemSettings(SettingsRequest settingsRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			internalResponse = getSettingsBCL().upsertSystemSettings(settingsRequest);

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
	public SettingsResponse fetchUserSettings(PagedInquiryRequest lightingControlRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			internalResponse = getSettingsBCL().fetchUserSettings(lightingControlRequest);
			response.setSettings(internalResponse.getResultsList());

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
	public SettingsResponse fetchSystemSettings(PagedInquiryRequest lightingControlRequest)
	{
		SettingsResponse response = new SettingsResponse();
		InternalResultsResponse<Setting> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			internalResponse = getSettingsBCL().fetchSystemSettings(lightingControlRequest);
			response.setSettings(internalResponse.getResultsList());

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

			internalResponse = getSettingsBCL().fetchTenantByServerName(tenantRequest);
			response.setTenant(internalResponse.getFirstResult());

			handleOperationStatusAndMessagesTenant(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	private void handleOperationStatusAndMessages(SettingsResponse response,
			InternalResultsResponse<Setting> internalResponse, List<MessageInfo> messages, boolean b)
	{
		// TODO Auto-generated method stub

	}

	private void handleException(Logger log2, SettingsResponse response, Exception ex, String defaultExceptionMsg)
	{
		// TODO Auto-generated method stub

	}

	private void handleOperationStatusAndMessagesTenant(TenantResponse response,
			InternalResultsResponse<Tenant> internalResponse, List<MessageInfo> messages, boolean b)
	{
		// TODO Auto-generated method stub

	}

	private void handleException(Logger log2, TenantResponse response, Exception ex, String defaultExceptionMsg)
	{
		// TODO Auto-generated method stub

	}
}