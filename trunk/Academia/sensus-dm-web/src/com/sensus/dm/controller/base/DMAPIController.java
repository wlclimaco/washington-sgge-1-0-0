package com.sensus.dm.controller.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.AllEnumsDefinitions;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.tenant.bcf.ITenantBCF;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.tenant.model.response.TenantResponse;
import com.sensus.dm.controller.model.UserSettings;

/**
 * The Class DMAPIController.
 */
@Controller
public class DMAPIController extends BaseController
{
	/** The logger for this class. */
	private static final Log LOG = LogFactory.getLog(DMAPIController.class);

	/** The servlet context. */
	private @Autowired
	ServletContext servletContext;

	/** The Constant FILL_SETTINGS. */
	private static final String FILL_SETTINGS = "/fillSettings";

	/** The Constant FETCHMESSAGES. */
	private static final String FETCHMESSAGES = "/fetchmessages";

	/** The Constant FETCH_SERVICES_BY_DEVICE_TYPE. */
	private static final String FETCH_SERVICES_BY_DEVICE_TYPE = "/fetchServicesByDeviceType";

	/** The device bcf. */
	private IDeviceBCF deviceBCF;

	/** The tenant bcf. */
	private ITenantBCF tenantBCF;

	/** The ui settings. */
	private String uiSettings;

	/** The ui messages. */
	private String uiMessages;

	/**
	 * Gets the ui settings.
	 * 
	 * @return the uiSettings
	 */
	public String getUiSettings()
	{
		return uiSettings;
	}

	/**
	 * Sets the ui settings.
	 * 
	 * @param uiSettings the uiSettings to set
	 */
	@Resource
	public void setUiSettings(String uiSettings)
	{
		this.uiSettings = uiSettings;
	}

	/**
	 * Gets the ui messages.
	 * 
	 * @return the uiSettings
	 */
	public String getUiMessages()
	{
		return uiMessages;
	}

	/**
	 * Sets the ui messages.
	 * 
	 * @param uiMessages the new ui messages
	 */
	@Resource
	public void setUiMessages(String uiMessages)
	{
		this.uiMessages = uiMessages;
	}

	/**
	 * Gets the device bcf.
	 * 
	 * @return the device bcf
	 */
	public IDeviceBCF getDeviceBCF()
	{
		return deviceBCF;
	}

	/**
	 * Sets the device bcf.
	 * 
	 * @param deviceBCF the new device bcf
	 */
	@Resource
	public void setDeviceBCF(IDeviceBCF deviceBCF)
	{
		this.deviceBCF = deviceBCF;
	}

	/**
	 * Gets the tenant bcf.
	 * 
	 * @return the tenant bcf
	 */
	public ITenantBCF getTenantBCF()
	{
		return tenantBCF;
	}

	/**
	 * Sets the tenant bcf.
	 * 
	 * @param tenantBCF the new tenant bcf
	 */
	@Resource
	public void setTenantBCF(ITenantBCF tenantBCF)
	{
		this.tenantBCF = tenantBCF;
	}

	/**
	 * Fill settings.
	 * 
	 * @param request the request
	 * @return the map
	 */
	@RequestMapping(value = FILL_SETTINGS, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fillSettings(HttpServletRequest request)
	{
		HashMap<String, Object> settings = new HashMap<String, Object>();

		try
		{
			Properties prop = new Properties();
			prop.load(servletContext.getResourceAsStream(getUiSettings()));

			UserSettings userSettings = getUserSettings();

			settings.put("appRoot", request.getContextPath());
			settings.put("enums", AllEnumsDefinitions.getSystemEnumsDefinitions());
			settings.put("dmProduct", prop.getProperty("dm.product"));
			settings.put("dmVersion", prop.getProperty("dm.release"));
			settings.put("dmBuildVersion", prop.getProperty("dm.build.number"));

			TenantRequest tenantRequest = new TenantRequest();
			// Get tenant in user settings
			tenantRequest.setTenant(new DMTenant(getUserSettings().getTenant()));

			// Fetch Tenant Description
			TenantResponse tenantResponse = getTenantBCF().fetchTenantDescription(tenantRequest);

			if (!ValidationUtil.isNull(tenantResponse.getTenant())
					&& !ValidationUtil.isNull(tenantResponse.getTenant().get(0)))
			{
				settings.put("customer", tenantResponse.getTenant().get(0).getName());
			}

			if (!ValidationUtil.isNull(userSettings))
			{
				// Set the user preferences
				settings.put("baseLocale", userSettings.getBaseLocale());
				settings.put("language", userSettings.getLanguage());
				settings.put("timezone", userSettings.getTimezone());
				settings.put("timezoneShort", userSettings.getTimezoneShort());
				settings.put("monitor", String.valueOf(userSettings.getMonitor()));
				settings.put("dateFormatMask", userSettings.getDateFormatMask());
				settings.put("pageSize", userSettings.getPageSize());
				settings.put("defaultLng", userSettings.getDefaultLng());
				settings.put("defaultLat", userSettings.getDefaultLat());
				settings.put("timezoneHours", userSettings.getTimeZoneHours());
				settings.put("timezoneMinutes", userSettings.getTimeZoneMinutes());
				settings.put("serviceType", userSettings.getServiceType());
				settings.put("temperatureType", userSettings.getTemperatureType());
				settings.put("showDialogPolygon", userSettings.getShowDialogPolygon());

				// Set Map
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("aerialName", prop.getProperty("dm.map.layer.aerial.name"));
				map.put("aerialUrl1", prop.getProperty("dm.map.layer.aerial.1"));
				map.put("aerialUrl2", prop.getProperty("dm.map.layer.aerial.2"));
				map.put("aerialUrl3", prop.getProperty("dm.map.layer.aerial.3"));
				map.put("aerialUrl4", prop.getProperty("dm.map.layer.aerial.4"));
				map.put("osmName", prop.getProperty("dm.map.layer.osm.name"));
				map.put("osmUrl1", prop.getProperty("dm.map.layer.osm.1"));
				map.put("osmUrl2", prop.getProperty("dm.map.layer.osm.2"));
				map.put("osmUrl3", prop.getProperty("dm.map.layer.osm.3"));
				map.put("osmUrl4", prop.getProperty("dm.map.layer.osm.4"));

				settings.put("map", map);

				// Set system interval times
				HashMap<String, Integer> time = new HashMap<String, Integer>();
				time.put("checkRni", Integer.valueOf(prop.getProperty("dm.time.checkRni")));
				time.put("longRunningProcess", Integer.valueOf(prop.getProperty("dm.time.longRunningProcess")));
				time.put("sessionTimeout", Integer.valueOf(prop.getProperty("dm.time.sessionTimeout")));

				settings.put("time", time);
			}
		}
		catch (Exception ex)
		{
			LOG.error("Error when filling settings", ex);
		}

		return settings;
	}

	/**
	 * Fetch messages.
	 * TODO - Get this data using Property class, and make it reusable.
	 * 
	 * @param localeLanguage the locale language
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = FETCHMESSAGES, method = RequestMethod.GET)
	@ResponseBody
	public String fetchMessages(@RequestParam(value = "localeLanguage", required = false) String localeLanguage)
			throws IOException
	{
		InputStream inputStream = null;
		String messages = null;

		try
		{
			StringBuffer sb = new StringBuffer();
			inputStream =
					servletContext.getResourceAsStream(uiMessages + localeLanguage.replace("-", "_") + ".properties");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			while (bufferedReader.ready())
			{
				sb.append(bufferedReader.readLine()).append('\n');
			}

			messages = sb.toString();
		}
		finally
		{
			if (inputStream != null)
			{
				inputStream.close();
			}
		}

		return messages;
	}

	/**
	 * Fetch device type parameters.
	 * 
	 * @param servletRequest the servlet request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_SERVICES_BY_DEVICE_TYPE, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchDeviceTypeParameters(HttpServletRequest servletRequest)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{
			DeviceRequest deviceRequest = new DeviceRequest();

			UserSettings userSettings = getUserSettings();

			deviceRequest.setGrantedAuthorityList(userSettings.getRoles());

			response = getDeviceBCF().fetchServicesByDeviceType(deviceRequest);
		}
		catch (Exception e)
		{
			LOG.error("Error when create user profile", e);
		}

		return response;
	}
}