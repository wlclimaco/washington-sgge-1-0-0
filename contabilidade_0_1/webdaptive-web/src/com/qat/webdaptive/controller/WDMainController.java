package com.qat.webdaptive.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.UserContext;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.validation.ValidationUtil;
//import com.qat.samples.sysmgmt.cbof.util.EnumDefinitionCollector;
import com.qat.samples.sysmgmt.util.model.response.UsuarioResponse;

/**
 * The PGSIMainController Class.
 * 
 * @author Flavio Tosta
 * 
 */

@Controller
public class WDMainController
{
	private static final String EN_US = "en_US";
	/** The URL mapping. */
	private static final String FETCH_BAR = "/main";
	private static final String FETCH_WRAPPER = "/wrapper";

	/** The view mapping constants . */
	private static final String VIEW_MAIN = "wd_main";
	private static final String VIEW_MAIN_BODY = "wd_wrapper";

	/** Request parameter constants. */
	private static final String PARAM_APP = "app";
	private static final String PARAM_USER = "user";

	/** The response Constants. */
	private static final String RESPONSE = "response";
	private static final String TIME_ZONES = "timeZones";
	private static final String LOCALE_MESSAGES = "localeMessages";

	/** I18N Constants. */
	private static final String LOCALE_MESSAGE = "locale.messages";
	private static final String MESSAGES_ENUMERATION = "wd-sendsolv-enumerationmessages";
	private static final String STRING_EQUAL = "=";
	private static final String STRING_COMMA = ",";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(WDMainController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "PGSIMainController";

	/** The injections made by Spring. */
	// private EnumDefinitionCollector enumCollector;

	/**
	 * Fetch main.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = {FETCH_BAR}, method = RequestMethod.GET)
	public ModelAndView fetchMain(HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_MAIN);
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			UsuarioResponse systemResponse = fillMainAppProperties();

			modelAndView.addObject(RESPONSE, mapper.writeValueAsString(systemResponse));
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return modelAndView;
	}

	/**
	 * Fetch main body.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_WRAPPER, method = RequestMethod.GET)
	public ModelAndView fetchMainBody(@RequestParam(value = PARAM_USER, required = false) Boolean fetchUserProperties,
			@RequestParam(value = PARAM_APP, required = false) Boolean fetchAppProperties,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_MAIN_BODY);
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			UsuarioResponse systemResponse = new UsuarioResponse();

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserContext userContext = new UserContext(auth.getName());

			if (fetchUserProperties)
			{
				// fillUserProperties(systemResponse, userContext);

				// LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
				// localeResolver.setLocale(request, null,
				// LocaleUtils.toLocale(systemResponse.getLanguage()));
			}

			if (fetchAppProperties)
			{
				// fillAppProperties(systemResponse, userContext);
				// modelAndView.addObject(TIME_ZONES, fillTimeZones());
				// modelAndView.addObject(LOCALE_MESSAGES, getMapper().writeValueAsString(fetchAppLangProperties()));
			}

			modelAndView.addObject(RESPONSE, mapper.writeValueAsString(systemResponse));

		}
		catch (Exception e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Fill user properties.
	 * 
	 * @param systemResponse the system response
	 */
	// private void fillUserProperties(SystemResponse systemResponse, UserContext userContext)
	// {
	// try
	// {
	// systemResponse.setUserContext(userContext);
	//
	// UserSettings userSettings = new UserSettings();
	//
	// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// String userDisplayName = extractUserDisplayName(auth);
	//
	// userSettings.setLanguage(EN_US);
	// userSettings.setUserName(userDisplayName);
	//
	// systemResponse.setUserSettings(userSettings);
	// }
	//
	// catch (Exception e)
	// {
	// LOG.error(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append("Error when filling user properties")
	// .append(e)
	// .toString());
	// }
	// }

	/**
	 * Fill Application properties.
	 * 
	 * @param systemResponse the system response
	 * @param request the request
	 */
	// private void fillAppProperties(SystemResponse systemResponse, UserContext userContext)
	// {
	//
	// try
	// {
	// Map<String, Object> uiSettings = new HashMap<String, Object>();
	// uiSettings.put("enums", getEnumCollector().getEnumDefinitionsMap());
	// systemResponse.setUiProperties(uiSettings);
	// }
	// catch (Exception e)
	// {
	// LOG.error(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append("Error when filling settings").append(e)
	// .toString());
	// }
	//
	// }

	/**
	 * Returns the SystemResponse object containing common used settings.
	 * 
	 * @param request
	 * @return SystemResponse
	 */
	public UsuarioResponse fillMainAppProperties()
	{
		UsuarioResponse systemResponse = new UsuarioResponse();

		try
		{
			System.out.println("TESTE TESTE TESTE TESTE");
			// Map<String, Object> uiSettings = new HashMap<String, Object>();
			// Properties prop = fetchProperties(getUiSettings());
			//
			// uiSettings.put("sessionId", RequestContextHolder.currentRequestAttributes().getSessionId());
			// uiSettings.put("sendsolvProduct", prop.getProperty("sendsolv.product"));
			// uiSettings.put("sendsolvVersion", prop.getProperty("sendsolv.release"));
			// uiSettings.put("sendsolvBuildVersion", prop.getProperty("sendsolv.build.number"));
			// uiSettings.put("sendsolvReportServerUrl", prop.getProperty("sendsolv.report.server.url"));
			//
			// systemResponse.setUiProperties(uiSettings);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return systemResponse;
	}

	/**
	 * This method checks whether the Principal of the Authentication parameter is an
	 * InetOrgPerson and if it is calls a function to extract based on that type of object.
	 * Otherwise, it returns the Name property from the Authentication object passed as the
	 * parameter.
	 * 
	 * @param auth - Authentication object parameter containing user Principal
	 * @return String containing a user name suitable for UI display
	 */
	private String extractUserDisplayName(Authentication auth)
	{
		Object principal = auth.getPrincipal();

		String displayName;

		if (isInetOrgPerson(principal))
		{
			displayName = extractInetOrgPersonDisplayName((InetOrgPerson)principal);
		}
		else
		{
			displayName = auth.getName();
		}

		return displayName;
	}

	/**
	 * 
	 * @param principal - type is Object
	 * @return true if the principal parameter is a InetOrgPerson object, false otherwise
	 */
	private boolean isInetOrgPerson(Object principal)
	{
		if (ValidationUtil.isNull(principal))
		{
			return false;
		}
		else if (principal instanceof InetOrgPerson)
		{
			return true;
		}
		return false;
	}

	/**
	 * This method extracts information from the InetOrgPerson parameter. It looks for
	 * certain attributes of an InetOrgPerson in the following order:
	 * 
	 * <pre>
	 * displayName
	 * CN or common name followed by SN or surname
	 * userName
	 * </pre>
	 * 
	 * The first of the above that is found is returned.
	 * <p>
	 * 
	 * @param principal - an InetOrgPerson object
	 * @return a String suitable for display as a user name
	 */
	private String extractInetOrgPersonDisplayName(InetOrgPerson principal)
	{
		String displayName = principal.getDisplayName();

		if (ValidationUtil.isNullOrEmpty(displayName))
		{
			StringBuilder displayNameBuilder = new StringBuilder();

			String[] cnArray = principal.getCn();

			if (!ValidationUtil.isNull(cnArray))
			{
				for (String aCn : cnArray)
				{
					if (!ValidationUtil.isNullOrEmpty(aCn))
					{
						displayNameBuilder.append(aCn);
						displayNameBuilder.append(' ');
					}
				}
			}

			String sn = principal.getSn();

			if (!ValidationUtil.isNullOrEmpty(sn))
			{
				displayNameBuilder.append(sn);
			}

			displayName = displayNameBuilder.toString();

			if (ValidationUtil.isNullOrEmpty(displayName))
			{
				displayName = principal.getUsername();
			}
		}

		return displayName.trim();
	}

	public String fetchMessages(Locale locale)
	{
		StringBuffer sb = new StringBuffer();

		// FE internationalization
		ResourceBundle messageBundle =
				ResourceBundle.getBundle(LOCALE_MESSAGE, locale);

		for (String key : messageBundle.keySet())
		{
			sb.append(key + STRING_EQUAL + messageBundle.getString(key)).append('\n');
		}

		// ENUM internationalization
		messageBundle =
				ResourceBundle.getBundle(MESSAGES_ENUMERATION, locale);

		for (String key : messageBundle.keySet())
		{
			sb.append(key + STRING_EQUAL + messageBundle.getString(key)).append('\n');
		}

		return sb.toString();
	}

	/**
	 * Fetch properties.
	 * 
	 * @param uiSettingPath the ui setting path
	 * @return the properties
	 */
	public static Properties fetchProperties(String uiSettingPath)
	{
		Properties prop = new Properties();
		try
		{
			prop.load(QATAppContext.getApplicationContext().getResource(uiSettingPath).getInputStream());
		}
		catch (IOException e)
		{
			LOG.error(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append("Error loading ui properties").append(e)
					.toString());
		}
		return prop;
	}

	/**
	 * Fill time zones.
	 * 
	 * @return the jSON object
	 * @throws Exception the exception
	 */

}