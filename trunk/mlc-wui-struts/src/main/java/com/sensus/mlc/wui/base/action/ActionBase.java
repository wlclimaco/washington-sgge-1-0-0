package com.sensus.mlc.wui.base.action;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sensus.common.model.UserContext;

/**
 * Basic Action Implementation enabling various Struts Interceptors. The properties of this actions are either set by
 * Struts interceptors or injected by Spring.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings({"unchecked"})
public class ActionBase extends ActionSupport implements ParameterAware, CookiesAware, ApplicationAware,
		ServletRequestAware, ServletResponseAware, SessionAware
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4757131161864017921L;

	/**
	 * A map of HTTP servletRequest parameters. The value for each entry is an array of
	 * String objects.
	 */
	private Map<String, String[]> parameters;

	/**
	 * The HttpServletRequest object associated with this action.
	 */
	private transient HttpServletRequest servletRequest;

	/**
	 * The HttpServletResponse object associated with this action.
	 */
	private transient HttpServletResponse servletResponse;

	/**
	 * A map of the application variables associated with the current servletRequest's
	 * application.
	 */
	private Map application;

	/**
	 * A map of the session variables associated with the current servletRequest's
	 * session.
	 */
	private Map session;

	/**
	 * A map of the cookies available in the servletRequest.
	 */
	private Map<String, String> cookies;

	/**
	 * Application Properties object.
	 */
	private Properties settings;

	/**
	 * Returns the parameter map.
	 * 
	 * @return the parameter map
	 */
	public Map<String, String[]> getParameters()
	{
		return parameters;
	}

	/**
	 * Set the parameter map.
	 * 
	 * @param parameters the parameter map.
	 */
	public void setParameters(Map parameters)
	{
		this.parameters = parameters;
	}

	/**
	 * Retrieve a parameter by name. This method will always return a string array.
	 * 
	 * @param key the name of the parameter to retrieve
	 * @return the parameter value as string array. If the parameter originally was no array, it will be the first
	 *         element of the array.
	 */
	public String[] getParameters(String key)
	{
		return parameters.get(key);
	}

	/**
	 * Retrieve a parameter by name. This method will always return a string; if the parameter was a string array, it
	 * will return the first element.
	 * 
	 * @param key the name of the parameter to retrieve
	 * @return the parameter value as string
	 */
	public String getParameter(String key)
	{
		String[] params = getParameters(key);
		if (params == null)
		{
			return null;
		}
		return params[0];
	}

	/**
	 * Return a specific value from a parameter string array by index.
	 * 
	 * @param key the name of the parameter to retrieve
	 * @param index the index of the value to retrieve
	 * @return the parameter value as string
	 */
	public String getParameter(String key, int index)
	{
		return (parameters.get(key))[index];
	}

	/**
	 * Sets the application map.
	 * 
	 * @param applicationIn the new application
	 */
	public void setApplication(Map applicationIn)
	{
		application = applicationIn;
	}

	/**
	 * Get the application map.
	 * 
	 * @return the application map.
	 */
	public Map getApplication()
	{
		return application;
	}

	/**
	 * Get the HttpServletRequest object associated with this action instance.
	 * 
	 * @return the HttpServletRequest object associated with this action instance
	 */
	public HttpServletRequest getServletRequest()
	{
		return servletRequest;
	}

	/**
	 * Set the HttpServletRequest object associated with this action instance.
	 * 
	 * @param request the new servlet request
	 */
	public void setServletRequest(HttpServletRequest request)
	{
		servletRequest = request;
	}

	/**
	 * Set the HttpServletResponse object associated with this action instance.
	 * 
	 * @param responseIn the HttpServletResponse object associated with this action instance
	 */
	public void setServletResponse(HttpServletResponse responseIn)
	{
		servletResponse = responseIn;
	}

	/**
	 * Get the HttpServletResponse object associated with this action instance.
	 * 
	 * @return the HttpServletResponse object associated with this action instance
	 */
	public HttpServletResponse getServletResponse()
	{
		return servletResponse;
	}

	/**
	 * Get the session map (not HttpSession object!) associated with this action instance.
	 * 
	 * return the session map associated with this action instance
	 * 
	 * @return the session
	 */
	public Map getSession()
	{
		return session;
	}

	/**
	 * Set the session map (not HttpSession object!) associated with this action instance.
	 * 
	 * @param sessionIn the session map associated with this action instance
	 */
	public void setSession(Map sessionIn)
	{
		session = sessionIn;
	}

	/**
	 * Get the cookies map associated with this action instance.
	 * 
	 * return the cookies map associated with this action instance
	 * 
	 * @return the cookies map
	 */
	public Map<String, String> getCookiesMap()
	{
		return cookies;
	}

	/**
	 * Set the cookies map associated with this action instance.
	 * 
	 * @param cookiesMapIn the cookies map associated with this action instance
	 */
	public void setCookiesMap(Map cookiesMapIn)
	{
		cookies = cookiesMapIn;
	}

	/**
	 * Set the application properties. These are injected by Spring.
	 * 
	 * @param settingsIn the application properties to set
	 */
	public void setSettings(Properties settingsIn)
	{
		settings = settingsIn;
	}

	/**
	 * Get the application properties. These are injected by Spring.
	 * 
	 * @return the application properties.
	 */
	public Properties getSettings()
	{
		return settings;
	}

	/**
	 * Get the application tenant. This is according the user loged in.
	 * 
	 * @return the application tenant.
	 */
	public UserContext getUserContext()
	{
		HttpSession httpSession = getServletRequest().getSession(true);
		return (UserContext)httpSession.getAttribute("userContext");
	}

}
