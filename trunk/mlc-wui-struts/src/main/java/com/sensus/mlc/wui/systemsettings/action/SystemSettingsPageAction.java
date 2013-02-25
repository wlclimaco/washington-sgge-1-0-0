package com.sensus.mlc.wui.systemsettings.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.wui.base.action.LayoutBase;

/**
 * Action rendering System-Settings-related pages. At this point, this page has no extra logic over what LayoutBase
 * already
 * provides.
 * 
 * @author Raphael Constantino
 * 
 */

@SuppressWarnings("serial")
public class SystemSettingsPageAction extends LayoutBase
{

	/** CONSTANTS *. */

	/** The Constant FETCH_ERROR_MESSAGE. */
	public static final String FETCH_ERROR_MESSAGE = "Error searching for properties";

	/** The system settings. */
	private Map<String, String> systemSettings = new HashMap<String, String>();

	/**
	 * The Schedule BCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The lc help. */
	private transient LCHelp lcHelp;

	/**
	 * The logger for this class.
	 */
	private final Log logger = LogFactory.getLog(this.getClass());

	/** The language. */
	private String language;

	/** The timezone. */
	private String timezone;

	/** The dateformat. */
	private String dateformat;

	/** The monitor. */
	private String monitor;

	/** The convert units. */
	private String convertUnits;

	/** The map time zone. */
	private Map<String, String> mapTimeZone = new LinkedHashMap<String, String>();

	/** The pageSize. */
	private String pageSize;

	/** The page size dialog enum. */
	private Integer pageSizeDialogShow;

	/** The response. */
	private Response response;

	/**
	 * Open profile page action.
	 * 
	 * @return the string
	 */

	/**
	 * Fetch system settings.
	 * 
	 * @return the string
	 */
	public String fetchSystemSettings()
	{
		/*
		 * try
		 * {
		 * LightingControlRequest request = new LightingControlRequest(getUserContext());
		 * List<Setting> settings = getSettingsBCF().fetchSystemSettings(request).getSettings();
		 * for (Setting setting : settings)
		 * {
		 * if (setting.getPropertyEnum().equals(PropertyEnum.LANGUAGE))
		 * {
		 * setLanguage(setting.getPropertyValue());
		 * }
		 * else if (setting.getPropertyEnum().equals(PropertyEnum.TIME_ZONE))
		 * {
		 * setTimezone(setting.getPropertyValue());
		 * }
		 * else if (setting.getPropertyEnum().equals(PropertyEnum.DATE_FORMAT))
		 * {
		 * setDateformat(setting.getPropertyValue());
		 * }
		 * else if (setting.getPropertyEnum().equals(PropertyEnum.PAGE_SIZE))
		 * {
		 * setPageSize(setting.getPropertyValue());
		 * }
		 * else if (setting.getPropertyEnum().equals(PropertyEnum.CONVERT_ENERGY_UNIT))
		 * {
		 * setConvertUnits(setting.getPropertyValue());
		 * }
		 * else if (setting.getPropertyEnum().equals(PropertyEnum.PAGE_SIZE_SHOW_DIALOG))
		 * {
		 * setPageSizeDialogShow(Integer
		 * .parseInt(setting.getPropertyValue()));
		 * }
		 * }
		 * }
		 * catch (Exception e)
		 * {
		 * if (LOG.isErrorEnabled())
		 * {
		 * logger.error(FETCH_ERROR_MESSAGE, e);
		 * }
		 * }
		 */
		return SUCCESS;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(String pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public String getPageSize()
	{
		return pageSize;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the settingsBCF to set
	 */
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settingsBCF
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the system settings.
	 * 
	 * @param systemSettings the systemSettings to set
	 */
	public void setSystemSettings(Map<String, String> systemSettings)
	{
		this.systemSettings = systemSettings;
	}

	/**
	 * Gets the system settings.
	 * 
	 * @return the systemSettings
	 */
	public Map<String, String> getSystemSettings()
	{
		return systemSettings;
	}

	/**
	 * Sets the language.
	 * 
	 * @param language the language to set
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Gets the language.
	 * 
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Sets the timezone.
	 * 
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	/**
	 * Gets the timezone.
	 * 
	 * @return the timezone
	 */
	public String getTimezone()
	{
		return timezone;
	}

	/**
	 * Sets the dateformat.
	 * 
	 * @param dateformat the dateformat to set
	 */
	public void setDateformat(String dateformat)
	{
		this.dateformat = dateformat;
	}

	/**
	 * Gets the dateformat.
	 * 
	 * @return the dateformat
	 */
	public String getDateformat()
	{
		return dateformat;
	}

	/**
	 * Sets the monitor.
	 * 
	 * @param monitor the monitor to set
	 */
	public void setMonitor(String monitor)
	{
		this.monitor = monitor;
	}

	/**
	 * Gets the monitor.
	 * 
	 * @return the monitor
	 */
	public String getMonitor()
	{
		return monitor;
	}

	/**
	 * Sets the convert units.
	 * 
	 * @param convertUnits the convertUnits to set
	 */
	public void setConvertUnits(String convertUnits)
	{
		this.convertUnits = convertUnits;
	}

	/**
	 * Gets the convert units.
	 * 
	 * @return the convertUnits
	 */
	public String getConvertUnits()
	{
		return convertUnits;
	}

	/**
	 * Sets the map time zone.
	 * 
	 * @param mapTimeZone the mapTimeZone to set
	 */
	public void setMapTimeZone(Map<String, String> mapTimeZone)
	{
		this.mapTimeZone = mapTimeZone;
	}

	/**
	 * Gets the map time zone.
	 * 
	 * @return the mapTimeZone
	 */
	public Map<String, String> getMapTimeZone()
	{
		return mapTimeZone;
	}

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the page size dialog show.
	 * 
	 * @return the pageSizeDialogShow
	 */
	public Integer getPageSizeDialogShow()
	{
		return pageSizeDialogShow;
	}

	/**
	 * Sets the page size dialog show.
	 * 
	 * @param pageSizeDialogShow the pageSizeDialogShow to set
	 */
	public void setPageSizeDialogShow(Integer pageSizeDialogShow)
	{
		this.pageSizeDialogShow = pageSizeDialogShow;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}
}
