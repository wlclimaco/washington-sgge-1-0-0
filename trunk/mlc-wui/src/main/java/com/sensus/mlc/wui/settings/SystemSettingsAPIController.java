/*
 *
 */
package com.sensus.mlc.wui.settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.AllEnumsDefinitions;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;
import com.sensus.mlc.wui.model.SystemResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemSettingsAPIController.
 * 
 * @author loliveira
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

	/** The Constant ERROR_LOCAL_LANGUAGE. */
	private static final String ERROR_LOCAL_LANGUAGE = "Error opening file of local language";

	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";

	/** The app root. */
	private String appRoot;

	/** The response. */
	private Response response;

	/**
	 * The Schedule BCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The response. */
	private SystemResponse systemResponse;

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
		return this.settingsBCF;
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
	 * Gets the system response.
	 * 
	 * @return the systemResponse
	 */
	public SystemResponse getSystemResponse()
	{
		return systemResponse;
	}

	/**
	 * Sets the system response.
	 * 
	 * @param systemResponse the systemResponse to set
	 */
	public void setSystemResponse(SystemResponse systemResponse)
	{
		this.systemResponse = systemResponse;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return this.response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response
	 *            the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Gets the app root.
	 * 
	 * @return the appRoot
	 */
	public String getAppRoot()
	{
		return appRoot;
	}

	/**
	 * Sets the app root.
	 * 
	 * @param appRoot the appRoot to set
	 */
	public void setAppRoot(String appRoot)
	{
		this.appRoot = appRoot;
	}

	/**
	 * Open settings.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response openSettings(HttpServletRequest request)
	{
		SettingsResponse settingsResponse = new SettingsResponse();

		try
		{
			LightingControlRequest lightingControlRequest = new LightingControlRequest();
			setUserContext(lightingControlRequest, request);
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
	 * Save profile settings.
	 * 
	 * @param request the request
	 * @return the string
	 */
	/*
	 * public String saveSystemSettings() { try { SettingsRequest request =
	 * this.getSettingsRequest(); SettingsResponse resp =
	 * this.getSettingsBCF().upsertSettings(request); this.setResponse(resp); if
	 * (!ValidationUtil.isNull(resp) && resp.isOperationSuccess()) {
	 * PreferencesSessionUtil
	 * .setSession(this.getSettingsRequest().getSettings(),
	 * this.getServletRequest(), false); } } catch (Exception e) { if
	 * (LOG.isErrorEnabled()) { this.logger.error("Error Saving properties", e);
	 * } }
	 * return SUCCESS; }
	 * /** Fill Settings.
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

			UserContext userContext = (UserContext)RequestContextHolder
					.currentRequestAttributes().getAttribute("userContext",
							RequestAttributes.SCOPE_SESSION);

			systemResponse.setUserContext(userContext);

			LightingControlRequest LightingControlRequest = new LightingControlRequest(
					userContext);

			SettingsResponse settingsResponse = getSettingsBCF()
					.fetchUserSettings(LightingControlRequest);

			if (!ValidationUtil.isNull(settingsResponse.getMessageList()))
			{
				systemResponse.setMessageList(settingsResponse.getMessageList());
			}

			if (!ValidationUtil.isNullOrEmpty(settingsResponse.getSettings()))
			{

				for (Setting setting : settingsResponse.getSettings())
				{

					if ("TIME_ZONE".equals(setting.getPropertyEnum().toString()))
					{
						String diff = "";

						TimeZone tz = TimeZone.getTimeZone(setting.getPropertyValue());
						diff = String.valueOf(tz.getOffset((new Date()).getTime()) / 3600000);

						systemResponse.setTimeZoneHours(diff);
					}
				}
				systemResponse.setSetting(settingsResponse.getSettings());
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
					new InputStreamReader(inputStream));
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
			settingsResponse = getSettingsBCF().upsertSettings(settingsRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, settingsResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return settingsResponse;
	}

}
