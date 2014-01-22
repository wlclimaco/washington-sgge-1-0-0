package com.sensus.lc.controller.settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.TimeZoneInfo;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.base.util.AllEnumsDefinitions;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.controller.model.SystemResponse;
import com.sensus.lc.controller.util.PreferencesSessionUtil;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.settings.bcf.ISettingsBCF;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.settings.model.response.SettingsResponse;

/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/settings")
public class SystemSettingsAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_FETCH_PROPERTIES. */
	private static final String MAP_FETCH_PROPERTIES = "/fetchproperties";

	/** The Constant MAP_FETCH_MESSAGES. */
	private static final String MAP_FETCH_MESSAGES = "/fetchmessages";

	/** The Constant MAP_UPSERT. */
	private static final String MAP_UPSERT = "/upsert";

	/** The Constant MAP_UPSERT_SYSTEM_SETTINGS. */
	private static final String MAP_UPSERT_SYSTEM_SETTINGS = "/upsertSystemSettings";

	/** The Constant INSERT_USER_COLUMN_FILTER. */
	private static final String INSERT_USER_COLUMN_FILTER = "/insertUserColumnFilter";

	/** The Constant ERROR_LOCAL_LANGUAGE. */
	private static final String ERROR_LOCAL_LANGUAGE = "Error opening file of local language";

	/** The Constant TIME_ZONE. */
	public static final String TIME_ZONE = "TIME_ZONE";

	/** The Constant DATE_MILLISECONDS. */
	private static final int DATE_MILLISECONDS = 3600000;

	/**
	 * The Schedule BCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SystemSettingsAPIController";

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF
	 *            the new settings bcf
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Open settings.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response openSettings(@RequestBody LightingControlRequest lightingControlRequest, HttpServletRequest request)
	{
		SettingsResponse settingsResponse = new SettingsResponse();

		try
		{
			setUserContext(lightingControlRequest, request);

			if (ValidationUtil.isNullOrZero(lightingControlRequest.getUserId()))
			{
				lightingControlRequest.setUserId(lightingControlRequest.getUserContext().getId());
			}

			settingsResponse = getSettingsBCF().fetchUserSettings(lightingControlRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, settingsResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return settingsResponse;

	}

	/**
	 * Insert user column filter.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @param request the request
	 * @return the column filter response
	 */
	@RequestMapping(value = INSERT_USER_COLUMN_FILTER, method = RequestMethod.POST)
	@ResponseBody
	public ColumnFilterResponse insertUserColumnFilter(@RequestBody ColumnFilterRequest columnFilterRequest,
			HttpServletRequest request)
	{

		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();
		try
		{
			setUserContext(columnFilterRequest, request);
			columnFilterResponse = getSettingsBCF().insertUserColumnFilter(columnFilterRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, columnFilterResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return columnFilterResponse;

	}

	/**
	 * Save profile settings.
	 * 
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = MAP_FETCH_PROPERTIES, method = RequestMethod.POST)
	@ResponseBody
	public SystemResponse fetchProperties(HttpServletRequest request)
	{

		SystemResponse systemResponse = new SystemResponse();
		try
		{

			systemResponse.setEnums(AllEnumsDefinitions
					.getSystemEnumsDefinitions());

			UserContext userContext =
					(UserContext)RequestContextHolder.currentRequestAttributes().getAttribute("userContext",
							RequestAttributes.SCOPE_SESSION);

			systemResponse.setUserContext(userContext);

			LightingControlRequest lightingControlRequest = new LightingControlRequest(userContext);
			lightingControlRequest.setUserId(lightingControlRequest.getUserContext().getId());

			SettingsResponse settingsResponse = getSettingsBCF().fetchUserSettings(lightingControlRequest);

			PreferencesSessionUtil.setSession(settingsResponse.getSettings(), request, null, null, null, null);

			if (!ValidationUtil.isNull(settingsResponse.getMessageList()))
			{
				systemResponse.setMessageList(settingsResponse.getMessageList());
			}

			if (!ValidationUtil.isNullOrEmpty(settingsResponse.getSettings()))
			{
				// Properties set by controller
				Map<String, String> systemProperties = new HashMap<String, String>();

				for (Setting setting : settingsResponse.getSettings())
				{

					if (TIME_ZONE.equals(setting.getPropertyEnum().toString()))
					{
						String diff = "";

						TimeZone tz = TimeZone.getTimeZone(setting.getPropertyValue());
						diff = String.valueOf(tz.getOffset(new Date().getTime()) / DATE_MILLISECONDS);
						Date date = new Date();
						DateFormat isoFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
						isoFormat.setTimeZone(tz);

						systemProperties.put("timeZoneHours", diff);
						systemProperties.put("timeZoneShort", new TimeZoneInfo(tz).getDisplayNameShort());
						systemProperties.put("responseDateTime", isoFormat.format(date));

					}
				}

				systemResponse.setSettings(settingsResponse.getSettings());

				// Load Properties form ui-settings.properties file
				Map<String, String> uiProperties = new HashMap<String, String>();
				ResourceBundle bundle = ResourceBundle.getBundle("sensus-lc");
				for (String key : bundle.keySet())
				{
					uiProperties.put(key, bundle.getString(key));
				}

				systemResponse.setSystemProperties(systemProperties);
				systemResponse.setUiProperties(uiProperties);

			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, systemResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return systemResponse;
	}

	/** The servlet context. */
	private @Autowired
	ServletContext servletContext;

	/**
	 * Fetch messages.
	 * 
	 * @param localeLanguage the locale language
	 * @param pathFile the path file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = MAP_FETCH_MESSAGES, method = RequestMethod.GET)
	@ResponseBody
	public String fetchMessages(@RequestParam(value = "localeLanguage", required = false) String localeLanguage,
			@RequestParam(value = "pathFile", required = false) String pathFile)
			throws IOException
	{
		String messages = "";

		try
		{
			StringBuffer sb = new StringBuffer();

			if (ValidationUtil.isNull(pathFile))
			{
				pathFile = "/WEB-INF/classes/locale/messages_";
			}

			InputStream inputStream =
					servletContext
							.getResourceAsStream(pathFile
									+ localeLanguage.replace("-", "_")
									+ ".properties");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, Charsets.ISO_8859_1));
			while (bufferedReader.ready())
			{

				sb.append(bufferedReader.readLine()).append('\n');

			}
			messages = sb.toString();
		}
		catch (Exception e)
		{
			LOG.error(ERROR_LOCAL_LANGUAGE);
		}

		return messages;
	}

	/**
	 * Save profile settings.
	 * 
	 * @param settingsRequest the settings request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_UPSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response saveProfileSettings(@RequestBody SettingsRequest settingsRequest, HttpServletRequest request)
	{
		SettingsResponse settingsResponse = new SettingsResponse();
		try
		{
			// UserContext object from session
			setUserContext(settingsRequest, request);

			if (ValidationUtil.isNullOrZero(settingsRequest.getUserId()))
			{
				settingsRequest.setUserId(settingsRequest.getUserContext().getId());
			}

			settingsResponse = getSettingsBCF().upsertSettings(settingsRequest);

			if (settingsResponse.isOperationSuccess() && ValidationUtil.isNull(settingsRequest.getAction()))
			{
				PreferencesSessionUtil.setSession(settingsResponse.getSettings(), request, null, null, null, null);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, settingsResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return settingsResponse;
	}

	/**
	 * Save System settings.
	 * 
	 * @param settingsRequest the settings request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_UPSERT_SYSTEM_SETTINGS, method = RequestMethod.POST)
	@ResponseBody
	public Response saveSystemSettings(@RequestBody SettingsRequest settingsRequest, HttpServletRequest request)
	{
		SettingsResponse settingsResponse = new SettingsResponse();
		try
		{
			// UserContext object from session
			setUserContext(settingsRequest, request);
			settingsResponse = getSettingsBCF().upsertSystemSettings(settingsRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, settingsResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return settingsResponse;
	}

}
