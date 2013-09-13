package com.sensus.dm.elec.settings.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.property.bcl.IPropertyBCL;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.elec.settings.bcl.ISettingsBCL;
import com.sensus.dm.elec.settings.dac.ISettingsDAC;

/**
 * The Class SettingsBCLImpl.
 * 
 * @author - QAT Brazil.
 */
public class SettingsBCLImpl implements ISettingsBCL
{
	/** The Constant SYSTEM_USER. */
	private static final String SYSTEM_USER = "0";

	/** The Constant APPLICATION_USER. */
	private static final String APPLICATION_USER = "EPM.User";

	/** The Constant STR_DATE_FORMAT. */
	private static final String STR_DATE_FORMAT = "DATE_FORMAT";

	/** The Constant STR_LANGUAGE. */
	private static final String STR_LANGUAGE = "LANGUAGE";

	/** The Constant STR_MONITOR_REQUEST. */
	private static final String STR_MONITOR_REQUEST = "MONITOR_REQUEST";

	/** The Constant STR_PAGE_SIZE. */
	private static final String STR_PAGE_SIZE = "PAGE_SIZE";

	/** The Constant STR_TIME_ZONE. */
	private static final String STR_TIME_ZONE = "TIME_ZONE";

	/** The Constant STR_TEMPERATURE. */
	private static final String STR_TEMPERATURE = "TEMPERATURE";

	/** The Constant STR_SHOW_DIALOG_POLYGON. */
	private static final String STR_SHOW_DIALOG_POLYGON = "SHOW_DIALOG_POLYGON";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The I settings dac. */
	private ISettingsDAC settingsDAC;

	/** The property bcl. */
	private IPropertyBCL propertyBCL;

	/** The default language. */
	private String defaultLanguage;

	/** The default time zone. */
	private String defaultTimeZone;

	/** The default date format. */
	private String defaultDateFormat;

	/** The default monitor request. */
	private String defaultMonitorRequest;

	/** The default page size. */
	private String defaultPageSize;

	/** The default temperatre. */
	private String defaultTemperatre;

	/** The default show dialog polygon. */
	private String defaultShowDialogPolygon;

	/**
	 * Gets the settings dac.
	 * 
	 * @return the settings dac
	 */
	public ISettingsDAC getSettingsDAC()
	{
		return settingsDAC;
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
	 * Gets the property bcl.
	 * 
	 * @return the property bcl
	 */
	public IPropertyBCL getPropertyBCL()
	{
		return propertyBCL;
	}

	/**
	 * Sets the property bcl.
	 * 
	 * @param propertyBCL the new property bcl
	 */
	public void setPropertyBCL(IPropertyBCL propertyBCL)
	{
		this.propertyBCL = propertyBCL;
	}

	/**
	 * Gets the default language.
	 * 
	 * @return the defaultLanguage
	 */
	public String getDefaultLanguage()
	{
		return defaultLanguage;
	}

	/**
	 * Sets the default language.
	 * 
	 * @param defaultLanguage the defaultLanguage to set
	 */
	public void setDefaultLanguage(String defaultLanguage)
	{
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Gets the default time zone.
	 * 
	 * @return the defaultTimeZone
	 */
	public String getDefaultTimeZone()
	{
		return defaultTimeZone;
	}

	/**
	 * Sets the default time zone.
	 * 
	 * @param defaultTimeZone the defaultTimeZone to set
	 */
	public void setDefaultTimeZone(String defaultTimeZone)
	{
		this.defaultTimeZone = defaultTimeZone;
	}

	/**
	 * Gets the default date format.
	 * 
	 * @return the defaultDateFormat
	 */
	public String getDefaultDateFormat()
	{
		return defaultDateFormat;
	}

	/**
	 * Sets the default date format.
	 * 
	 * @param defaultDateFormat the defaultDateFormat to set
	 */
	public void setDefaultDateFormat(String defaultDateFormat)
	{
		this.defaultDateFormat = defaultDateFormat;
	}

	/**
	 * Gets the default monitor request.
	 * 
	 * @return the defaultMonitorRequest
	 */
	public String getDefaultMonitorRequest()
	{
		return defaultMonitorRequest;
	}

	/**
	 * Sets the default monitor request.
	 * 
	 * @param defaultMonitorRequest the defaultMonitorRequest to set
	 */
	public void setDefaultMonitorRequest(String defaultMonitorRequest)
	{
		this.defaultMonitorRequest = defaultMonitorRequest;
	}

	/**
	 * Gets the default page size.
	 * 
	 * @return the defaultPageSize
	 */
	public String getDefaultPageSize()
	{
		return defaultPageSize;
	}

	/**
	 * Sets the default page size.
	 * 
	 * @param defaultPageSize the defaultPageSize to set
	 */
	public void setDefaultPageSize(String defaultPageSize)
	{
		this.defaultPageSize = defaultPageSize;
	}

	/**
	 * Gets the default temperatre.
	 * 
	 * @return the default temperatre
	 */
	public String getDefaultTemperatre()
	{
		return defaultTemperatre;
	}

	/**
	 * Sets the default temperatre.
	 * 
	 * @param defaultTemperatre the new default temperatre
	 */
	public void setDefaultTemperatre(String defaultTemperatre)
	{
		this.defaultTemperatre = defaultTemperatre;
	}

	/**
	 * Gets the default show dialog polygon.
	 * 
	 * @return the default show dialog polygon
	 */
	public String getDefaultShowDialogPolygon()
	{
		return defaultShowDialogPolygon;
	}

	/**
	 * Sets the default show dialog polygon.
	 * 
	 * @param defaultShowDialogPolygon the new default show dialog polygon
	 */
	public void setDefaultShowDialogPolygon(String defaultShowDialogPolygon)
	{
		this.defaultShowDialogPolygon = defaultShowDialogPolygon;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#fetchUserSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InternalResultsResponse<Property> fetchUserSettings(PropertyRequest propertyRequest)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(propertyRequest.getUserContext().getUserId()));

		propertyRequest.setProperties(properties);
		propertyRequest.setPropertyProviderType(APPLICATION_USER);

		return getPropertyBCL().fetchProperty(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#fetchSystemSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Property> fetchSystemSettings(PropertyRequest propertyRequest)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(SYSTEM_USER));

		propertyRequest.setProperties(properties);
		propertyRequest.setPropertyProviderType(APPLICATION_USER);

		InternalResultsResponse<Property> internalResult = getPropertyBCL().fetchProperty(propertyRequest);
		if (internalResult.isInError())
		{
			return internalResult;
		}

		if (ValidationUtil.isNullOrEmpty(internalResult.getResultsList()))
		{
			PropertyRequest insertPropertyRequest = new PropertyRequest();
			List<Property> propertyList = getDefaultSettings();

			insertPropertyRequest.setProperties(propertyList);
			insertPropertyRequest.setUserContext(propertyRequest.getUserContext());

			InternalResponse internalResponse = getPropertyBCL().upsertProperty(insertPropertyRequest);
			if (internalResponse.isInError())
			{
				internalResult.setStatus(internalResponse.getStatus());
				internalResult.addMessages(internalResponse.getMessageInfoList());
				return internalResult;
			}

			internalResult.addResults(propertyList);
		}

		return internalResult;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#fetchAllUsers(com.sensus.dm.elec.settings.model.request.PropertyRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<UserContext> fetchAllUsers(PropertyRequest propertyRequest)
	{
		return getSettingsDAC().fetchAllUser(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#upsertSystemSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse upsertSystemSettings(PropertyRequest propertyRequest)
	{
		propertyRequest.setPropertyProviderType(APPLICATION_USER);

		for (Property property : propertyRequest.getProperties())
		{
			property.setProviderId(SYSTEM_USER);
		}

		return getPropertyBCL().upsertProperty(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#upsertUserSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InternalResponse upsertUserSettings(PropertyRequest propertyRequest)
	{
		propertyRequest.setPropertyProviderType(APPLICATION_USER);

		for (Property property : propertyRequest.getProperties())
		{
			property.setProviderId(propertyRequest.getUserContext().getUserId());
		}

		return getPropertyBCL().upsertProperty(propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.settings.bcl.ISettingsBCL#insertUser(com.sensus.common.model.Request)
	 */
	@Override
	public InternalResponse insertUser(PropertyRequest propertyRequest)
	{
		if (!fetchCanUserBeInserted(propertyRequest))
		{
			return new InternalResponse();
		}

		// Insert Application User
		InternalResponse response = getSettingsDAC().insertUser(propertyRequest);
		if (response.isInError())
		{
			return response;
		}

		// Create System Settings - Provider and Property for User 0
		InternalResultsResponse<Property> internalResult = fetchSystemSettings(propertyRequest);
		if (internalResult.isInError())
		{
			response.setStatus(internalResult.getStatus());
			response.addMessages(internalResult.getMessageInfoList());
			return response;
		}

		// Create User Settings - Provider and Property for New User
		PropertyRequest insertPropertyRequest = new PropertyRequest();
		insertPropertyRequest.setProperties(internalResult.getResultsList());
		insertPropertyRequest.setUserContext(propertyRequest.getUserContext());

		InternalResponse internalResponse = upsertUserSettings(insertPropertyRequest);
		if (internalResponse.isInError())
		{
			response.setStatus(internalResponse.getStatus());
			response.addMessages(internalResponse.getMessageInfoList());
		}

		return response;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Gets the default settings.
	 * 
	 * @return the default settings
	 */
	private List<Property> getDefaultSettings()
	{
		List<Property> listProperty = new ArrayList<Property>();
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_LANGUAGE, getDefaultLanguage(), null,
				null));
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_TIME_ZONE, getDefaultTimeZone(), null,
				null));
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_DATE_FORMAT, getDefaultDateFormat(),
				null,
				null));
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_MONITOR_REQUEST, getDefaultMonitorRequest(),
				null, null));
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_PAGE_SIZE, getDefaultPageSize(), null,
				null));
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_TEMPERATURE, getDefaultTemperatre(),
				null, null));
		listProperty.add(new Property(APPLICATION_USER, SYSTEM_USER, STR_SHOW_DIALOG_POLYGON,
				getDefaultShowDialogPolygon(),
				null, null));
		return listProperty;
	}

	/**
	 * Fetch can user be inserted.
	 * 
	 * @param propertyRequest the property request
	 * @return the boolean
	 */
	private Boolean fetchCanUserBeInserted(PropertyRequest propertyRequest)
	{
		InternalResultsResponse<UserContext> responseUser = getSettingsDAC().fetchAllUser(propertyRequest);
		for (UserContext userContext : responseUser.getResultsList())
		{
			if (userContext.getUserId().equals(propertyRequest.getUserContext().getUserId()))
			{
				return false;
			}
		}
		return true;
	}

	// -------------------------------------------------------------------------
}
