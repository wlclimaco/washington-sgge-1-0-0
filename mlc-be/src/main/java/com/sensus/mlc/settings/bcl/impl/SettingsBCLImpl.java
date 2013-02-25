package com.sensus.mlc.settings.bcl.impl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.bcl.ISettingsBCL;
import com.sensus.mlc.settings.dac.ISettingsDAC;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.tenant.dac.ITenantDAC;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.UserResponse;

/**
 * The Class SettingsBCLImpl.
 */
public class SettingsBCLImpl implements ISettingsBCL
{

	/** The user bcf. */
	private IUserBCF userBCF; // injected by Spring through setter

	/** The settings dac. */
	private ISettingsDAC settingsDAC; // injected by Spring through setter

	/** The tenant dac. */
	private ITenantDAC tenantDAC;

	/** The default language. */
	private String defaultLanguage; // injected by Spring - come from sensus-lc.properties;

	/** The default time zone. */
	private String defaultTimeZone; // injected by Spring - come from sensus-lc.properties;

	/** The default date format. */
	private String defaultDateFormat; // injected by Spring - come from sensus-lc.properties;

	/** The default monitor request. */
	private String defaultMonitorRequest; // injected by Spring - come from sensus-lc.properties;

	/** The default convert energy unit. */
	private String defaultConvertEnergyUnit; // injected by Spring - come from sensus-lc.properties;

	/** The default page size. */
	private String defaultPageSize; // injected by Spring - come from sensus-lc.properties;

	/** The default page size list. */
	private String defaultPageSizeList; // injected by Spring - come from sensus-lc.properties;

	/** The default page size show dialog. */
	private String defaultPageSizeShowDialog; // injected by Spring - come from sensus-lc.properties;

	/** The default polygon show dialog. */
	private String defaultPolygonShowDialog; // injected by Spring - come from sensus-lc.properties;

	/** The default latitude. */
	private Double defaultLatitude; // injected by Spring - come from sensus-lc.properties;

	/** The default longitude. */
	private Double defaultLongitude; // injected by Spring - come from sensus-lc.properties;

	/**
	 * Gets the default page size list.
	 *
	 * @return the default page size list
	 */
	public String getDefaultPageSizeList()
	{
		return this.defaultPageSizeList;
	}

	/**
	 * Sets the default page size list.
	 *
	 * @param defaultPageSizeList the new default page size list
	 */
	public void setDefaultPageSizeList(String defaultPageSizeList)
	{
		this.defaultPageSizeList = defaultPageSizeList;
	}

	/**
	 * Gets the user bcf.
	 *
	 * @return the user bcf
	 */
	public IUserBCF getUserBCF()
	{
		return this.userBCF;
	}

	/**
	 * Sets the user bcf.
	 *
	 * @param userBCF the new user bcf
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * Sets the settings dac.
	 *
	 * @param settingsDAC the new settings dac
	 */
	public void setSettingsDAC(ISettingsDAC settingsDAC)
	{
		this.settingsDAC = settingsDAC;
	}

	/**
	 * Gets the settings dac.
	 *
	 * @return the settings dac
	 */
	public ISettingsDAC getSettingsDAC()
	{
		return this.settingsDAC;
	}

	/**
	 * Gets the tenant dac.
	 *
	 * @return the tenant dac
	 */
	public ITenantDAC getTenantDAC()
	{
		return this.tenantDAC;
	}

	/**
	 * Sets the tenant dac.
	 *
	 * @param tenantDAC the new tenant dac
	 */
	public void setTenantDAC(ITenantDAC tenantDAC)
	{
		this.tenantDAC = tenantDAC;
	}

	/**
	 * Gets the default language.
	 *
	 * @return the default language
	 */
	public String getDefaultLanguage()
	{
		return this.defaultLanguage;
	}

	/**
	 * Sets the default language.
	 *
	 * @param defaultLanguage the new default language
	 */
	public void setDefaultLanguage(String defaultLanguage)
	{
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Gets the default time zone.
	 *
	 * @return the default time zone
	 */
	public String getDefaultTimeZone()
	{
		return this.defaultTimeZone;
	}

	/**
	 * Sets the default time zone.
	 *
	 * @param defaultTimeZone the new default time zone
	 */
	public void setDefaultTimeZone(String defaultTimeZone)
	{
		this.defaultTimeZone = defaultTimeZone;
	}

	/**
	 * Gets the default date format.
	 *
	 * @return the default date format
	 */
	public String getDefaultDateFormat()
	{
		return this.defaultDateFormat;
	}

	/**
	 * Sets the default date format.
	 *
	 * @param defaultDateFormat the new default date format
	 */
	public void setDefaultDateFormat(String defaultDateFormat)
	{
		this.defaultDateFormat = defaultDateFormat;
	}

	/**
	 * Gets the default monitor request.
	 *
	 * @return the default monitor request
	 */
	public String getDefaultMonitorRequest()
	{
		return this.defaultMonitorRequest;
	}

	/**
	 * Sets the default monitor request.
	 *
	 * @param defaultMonitorRequest the new default monitor request
	 */
	public void setDefaultMonitorRequest(String defaultMonitorRequest)
	{
		this.defaultMonitorRequest = defaultMonitorRequest;
	}

	/**
	 * Gets the default convert energy unit.
	 *
	 * @return the default convert energy unit
	 */
	public String getDefaultConvertEnergyUnit()
	{
		return this.defaultConvertEnergyUnit;
	}

	/**
	 * Sets the default convert energy unit.
	 *
	 * @param defaultConvertEnergyUnit the new default convert energy unit
	 */
	public void setDefaultConvertEnergyUnit(String defaultConvertEnergyUnit)
	{
		this.defaultConvertEnergyUnit = defaultConvertEnergyUnit;
	}

	/**
	 * Gets the default page size.
	 *
	 * @return the default page size
	 */
	public String getDefaultPageSize()
	{
		return this.defaultPageSize;
	}

	/**
	 * Sets the default page size.
	 *
	 * @param defaultPageSize the new default page size
	 */
	public void setDefaultPageSize(String defaultPageSize)
	{
		this.defaultPageSize = defaultPageSize;
	}

	/**
	 * Gets the default latitude.
	 *
	 * @return the default latitude
	 */
	public Double getDefaultLatitude()
	{
		return this.defaultLatitude;
	}

	/**
	 * Sets the default latitude.
	 *
	 * @param defaultLatitude the new default latitude
	 */
	public void setDefaultLatitude(Double defaultLatitude)
	{
		this.defaultLatitude = defaultLatitude;
	}

	/**
	 * Gets the default longitude.
	 *
	 * @return the default longitude
	 */
	public Double getDefaultLongitude()
	{
		return this.defaultLongitude;
	}

	/**
	 * Sets the default longitude.
	 *
	 * @param defaultLongitude the new default longitude
	 */
	public void setDefaultLongitude(Double defaultLongitude)
	{
		this.defaultLongitude = defaultLongitude;
	}

	/**
	 * Gets the default page size show dialog.
	 *
	 * @return the defaultPageSizeShowDialog
	 */
	public String getDefaultPageSizeShowDialog()
	{
		return this.defaultPageSizeShowDialog;
	}

	/**
	 * Sets the default page size show dialog.
	 *
	 * @param defaultPageSizeShowDialog the defaultPageSizeShowDialog to set
	 */
	public void setDefaultPageSizeShowDialog(String defaultPageSizeShowDialog)
	{
		this.defaultPageSizeShowDialog = defaultPageSizeShowDialog;
	}

	/**
	 * Gets the default polygon show dialog.
	 *
	 * @return the default polygon show dialog
	 */
	public String getDefaultPolygonShowDialog()
	{
		return this.defaultPolygonShowDialog;
	}

	/**
	 * Sets the default polygon show dialog.
	 *
	 * @param defaultPolygonShowDialog the new default polygon show dialog
	 */
	public void setDefaultPolygonShowDialog(String defaultPolygonShowDialog)
	{
		this.defaultPolygonShowDialog = defaultPolygonShowDialog;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcl.ISettingsBCL#upsertSettings(com.sensus.mlc.base.model.request.LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		if (!ValidationUtil.isNullOrEmpty(settingsRequest.getNewPassword())
				|| !ValidationUtil.isNullOrEmpty(settingsRequest.getActualPassword()))
		{
			response = this.updatePassword(settingsRequest);
		}
		if (!response.isInError())
		{
			response = this.getSettingsDAC().upsertSettings(settingsRequest);
		}

		return response;
	}

	/**
	 * Gets the default settings.
	 *
	 * @return the default settings
	 */
	private InternalResultsResponse<Setting> getDefaultSettings()
	{
		InternalResultsResponse<Setting> internalResult = new InternalResultsResponse<Setting>();
		internalResult.addResult(new Setting(PropertyEnum.LANGUAGE, this.getDefaultLanguage()));
		internalResult.addResult(new Setting(PropertyEnum.TIME_ZONE, this.getDefaultTimeZone()));
		internalResult.addResult(new Setting(PropertyEnum.DATE_FORMAT, this.getDefaultDateFormat()));
		internalResult.addResult(new Setting(PropertyEnum.MONITOR_REQUEST, this.getDefaultMonitorRequest()));
		internalResult.addResult(new Setting(PropertyEnum.CONVERT_ENERGY_UNIT, this.getDefaultConvertEnergyUnit()));
		internalResult.addResult(new Setting(PropertyEnum.PAGE_SIZE, this.getDefaultPageSize()));
		internalResult.addResult(new Setting(PropertyEnum.PAGE_SIZE_LIST, this.getDefaultPageSizeList()));
		internalResult.addResult(new Setting(PropertyEnum.PAGE_SIZE_SHOW_DIALOG, this.getDefaultPageSizeShowDialog()));
		internalResult.addResult(new Setting(PropertyEnum.SHOW_DIALOG_POLYGON, this.getDefaultPolygonShowDialog()));
		return internalResult;
	}

	/*
	 * @see
	 * com.sensus.mlc.settings.bcl.ISettingsBCL#fetchSettings(com.sensus.mlc.base.model.request.LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		InternalResultsResponse<Setting> systemResponse = this.fetchSystemSettings(lightingControlRequest);

		InternalResultsResponse<Setting> userResponse = this.getSettingsDAC().fetchUserSettings(lightingControlRequest);

		if (!ValidationUtil.isNull(userResponse))
		{
			// Doing a merge between system settings and user settings to avoid problems at login with missing
			// properties
			for (Setting systemSetting : systemResponse.getResultsList())
			{
				for (Setting userSetting : userResponse.getResultsList())
				{
					if (userSetting.getPropertyEnum() == systemSetting.getPropertyEnum())
					{
						systemSetting.setPropertyValue(userSetting.getPropertyValue());
						break;
					}
				}
			}
		}
		return systemResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcl.ISettingsBCL#fetchSystemSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		InternalResultsResponse<Setting> internalResult =
				this.getSettingsDAC().fetchSystemSettings(lightingControlRequest);
		if (ValidationUtil.isNullOrEmpty(internalResult.getResultsList()))
		{
			return this.getDefaultSettings();
		}
		return internalResult;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcl.ISettingsBCL#fetchTenantByServerName(com.sensus.mlc.settings.model.request.TenantRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Tenant> internalResultsResponse =
				this.getTenantDAC().fetchTenantByServerName(tenantRequest);
		Tenant tenant = internalResultsResponse.getFirstResult();
		if (!ValidationUtil.isNull(tenant))
		{
			if (ValidationUtil.isNull(tenant.getLatitude()))
			{
				tenant.setLatitude(this.getDefaultLatitude());
			}
			if (ValidationUtil.isNull(tenant.getLongitude()))
			{
				tenant.setLongitude(this.getDefaultLongitude());
			}
		}
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcl.ISettingsBCL#updateSystemSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> updateSystemSettings(SettingsRequest settingsRequest)
	{
		return this.getSettingsDAC().updateSystemSettings(settingsRequest);
	}

	/**
	 * Update password.
	 *
	 * @param settingsRequest the settings request
	 * @return the internal results response
	 */
	private InternalResultsResponse<Setting> updatePassword(SettingsRequest settingsRequest)
	{
		UserRequest userRequest = new UserRequest(settingsRequest.getUserContext());
		userRequest.setActualPassword(settingsRequest.getActualPassword());
		userRequest.setNewPassword(settingsRequest.getNewPassword());
		UserResponse userResponse = this.getUserBCF().changePassword(userRequest);
		InternalResultsResponse<Setting> internalResponse = new InternalResultsResponse<Setting>();

		if (!userResponse.isOperationSuccess() && !ValidationUtil.isNullOrEmpty(userResponse.getMessageInfoList()))
		{
			internalResponse.addMessages(userResponse.getMessageInfoList());
			internalResponse.setStatus(Status.PersistenceError);
		}

		return internalResponse;
	}

	/**
	 * Insert user filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	private InternalResponse insertUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();

		// Delete existing filters
		response = this.getSettingsDAC().deleteUserFilters(columnFilterRequest);

		if (!response.isInError())
		{
			response = this.getSettingsDAC().insertUserFilters(columnFilterRequest);
		}

		return response;
	}

	/**
	 * Insert user columns.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	public InternalResponse insertUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();

		// Delete existing Columns
		response = this.getSettingsDAC().deleteUserColumns(columnFilterRequest);

		if (!response.isInError())
		{
			response = this.getSettingsDAC().insertUserColumns(columnFilterRequest);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcl.ISettingsBCL#insertUserColumnFilter(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest)
	{

		InternalResponse response = null;

		if (!ValidationUtil.isNullOrEmpty(columnFilterRequest.getListColumn()))
		{
			response = this.insertUserColumns(columnFilterRequest);
		}

		if (!ValidationUtil.isNullOrEmpty(columnFilterRequest.getFilters()))
		{
			response = this.insertUserFilters(columnFilterRequest);
		}

		return response;
	}

	/**
	 * Calculate map center.
	 *
	 * @param tenant the tenant
	 */
	public void calculateMapCenter(Tenant tenant)
	{
		getSettingsDAC().calculateMapCenter(tenant);
	}

	/**
	 * Delete old data.
	 *
	 * @param tenant the tenant
	 */
	public void deleteOldData(Tenant tenant)
	{
		getSettingsDAC().deleteOldData(tenant);
	}
}
