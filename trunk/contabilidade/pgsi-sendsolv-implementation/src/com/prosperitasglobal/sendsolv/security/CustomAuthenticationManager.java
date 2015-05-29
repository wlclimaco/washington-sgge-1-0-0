package com.prosperitasglobal.sendsolv.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.prosperitasglobal.sendsolv.bai.ISettingsBAI;
import com.prosperitasglobal.sendsolv.bai.IUserBAI;
import com.prosperitasglobal.sendsolv.security.util.LCSecurityHandler;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.prosperitasglobal.sendsolv.user.model.User;
import com.prosperitasglobal.sendsolv.user.model.request.UserRequest;
import com.qat.framework.model.Authority;
import com.qat.framework.model.Tenant;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * A custom authentication manager that allows access if the user details
 * exist in the database and if the username and password are not the same.
 * Otherwise, throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager
{

	/** The Constant GROUP_AUTHORIZATION_TYPE. */
	private static final String GROUP_AUTHORIZATION_TYPE = "GROUP";

	/** SeverRequest. */
	private HttpRequest servletRequest;

	/** Settings BAI. */
	private ISettingsBAI settingsBAI; // injected by Spring

	/** User BAI. */
	private IUserBAI userBAI; // injected by Spring

	/** The web sevice user name. */
	private String webSeviceUserName;

	/** The web sevice password. */
	private String webSevicePassword;

	/** The super user name. */
	private String superUserName;

	/** The super user password. */
	private String superUserPassword;

	/** The app. */
	private String app;

	/**
	 * Gets the app.
	 *
	 * @return the app
	 */
	public String getApp()
	{
		return app;
	}

	/**
	 * Sets the app.
	 *
	 * @param app the new app
	 */
	public void setApp(String app)
	{
		this.app = app;
	}

	/**
	 * Gets the web sevice user name.
	 *
	 * @return the web sevice user name
	 */
	public String getWebSeviceUserName()
	{
		return webSeviceUserName;
	}

	/**
	 * Sets the web sevice user name.
	 *
	 * @param webSeviceUserName the new web sevice user name
	 */
	public void setWebSeviceUserName(String webSeviceUserName)
	{
		this.webSeviceUserName = webSeviceUserName;
	}

	/**
	 * Gets the web sevice password.
	 *
	 * @return the web sevice password
	 */
	public String getWebSevicePassword()
	{
		return webSevicePassword;
	}

	/**
	 * Sets the web sevice password.
	 *
	 * @param webSevicePassword the new web sevice password
	 */
	public void setWebSevicePassword(String webSevicePassword)
	{
		this.webSevicePassword = webSevicePassword;
	}

	/**
	 * Gets the super user name.
	 *
	 * @return the super user name
	 */
	public String getSuperUserName()
	{
		return superUserName;
	}

	/**
	 * Sets the super user name.
	 *
	 * @param superUserName the new super user name
	 */
	public void setSuperUserName(String superUserName)
	{
		this.superUserName = superUserName;
	}

	/**
	 * Gets the super user password.
	 *
	 * @return the super user password
	 */
	public String getSuperUserPassword()
	{
		return superUserPassword;
	}

	/**
	 * Sets the super user password.
	 *
	 * @param superUserPassword the new super user password
	 */
	public void setSuperUserPassword(String superUserPassword)
	{
		this.superUserPassword = superUserPassword;
	}

	/**
	 * Gets the settings bcf.
	 *
	 * @return the settings bcf
	 */
	public ISettingsBAI getSettingsBAI()
	{
		return settingsBAI;
	}

	/**
	 * Sets the settings bcf.
	 *
	 * @param settingsBAI the new settings bcf
	 */
	public void setSettingsBAI(ISettingsBAI settingsBAI)
	{
		this.settingsBAI = settingsBAI;
	}

	/**
	 * Gets the user bcf.
	 *
	 * @return the user bcf
	 */
	public IUserBAI getUserBAI()
	{
		return userBAI;
	}

	/**
	 * Sets the user bcf.
	 *
	 * @param userBAI the new user bcf
	 */
	public void setUserBAI(IUserBAI userBAI)
	{
		this.userBAI = userBAI;
	}

	/**
	 * @return the servletRequest
	 */
	public HttpRequest getServletRequest()
	{
		return servletRequest;
	}

	/**
	 * @param servletRequest the servletRequest to set
	 */
	public void setServletRequest(HttpRequest servletRequest)
	{
		this.servletRequest = servletRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core
	 * .Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException
	{
		// Check if it is a webservice call username
		if ((getWebSeviceUserName() != null)
				&& (getWebSevicePassword() != null)
				&& LCSecurityHandler.validePassword(getWebSevicePassword(), String.valueOf(auth.getCredentials()),
						auth.getName()))
		{
			// We return with no authorities.
			return new UsernamePasswordAuthenticationToken(
					auth.getName(),
					auth.getCredentials());
		}

		if (ValidationUtil.isNull(getServletRequest()))
		{
			throw new BadCredentialsException("Not a valid request!");
		}

		// Get Tenant based on the Server Name
		TenantRequest tenantRequest = new TenantRequest();
		tenantRequest.setServerName("LocalHost");
		// Retrieve the actual Tenant
		Tenant tenant = new Tenant();
		tenant.setId(1);

		// Check if the logged user has access to this tenant and get the group authentication information.
		UserRequest userRequest = new UserRequest();
		// userRequest.setTenant(tenant);
		if (ValidationUtil.isNull(tenant))
		{
			throw new BadCredentialsException("Not a valid Tenant!");
		}

		User user = new User(auth.getName());
		userRequest.setUser(user);
		// Get user complete user information
		user = getUserBAI().fetchUserByName(userRequest).getUser();

		// If user is null, it means the user does not have access to this tenant and also
		// Since We can have two users with the same with the same username in different tenants, confirm that
		// the password is for this tenant
		// also check if tenant is not null
		if (ValidationUtil.isNull(user))
		{
			throw new BadCredentialsException("User does not exist!");
		}
		// check if it's an API user
		else if (getApp().equals("slc") && user.getRole().equals("ROLE_Role.API"))
		{
			throw new BadCredentialsException("User does not have permissions!");
		}
		else if (!LCSecurityHandler.validePassword(user.getPassword(), String.valueOf(auth.getCredentials()),
				auth.getName()))
		/*
		 * else if (!user.getPassword().equals(
		 * (LCSecurityHandler.encryptPassword(((String)auth.getCredentials()).toString(), auth.getName()))))
		 */
		{
			throw new BadCredentialsException("Bad Password for this tenant!");
		}
		// also check if it is a user limited by group and there is no group defined to it
		else if (!getApp().equals("api"))
		{
			storeUserContextOnSession(tenant, user);
		}
		return new UsernamePasswordAuthenticationToken(
				auth.getName(),
				auth.getCredentials(),
				getAuthorities(user.getRole()));
	}

	/**
	 * Store user context on session.
	 *
	 * @param tenant the tenant
	 * @param user the user
	 */
	private void storeUserContextOnSession(Tenant tenant, User user)
	{
		UserContext userContext = new UserContext();
		userContext.setUserId(user.getUserName());
		userContext.setId(user.getId());
		userContext.setTenant(tenant);
		if (!ValidationUtil.isNull(user.getRole()))
		{
			userContext.setUserRole(user.getRole());
		}
		// add the group list that the user has authorization to the Authority list on userContext.
		List<Authority> authorities = getGroupAuthoritiesByUser(user);
		userContext.setAuthorities(authorities);
		// put the user preferences object in the session
		// HttpSession session = getServletRequest().getSession();
		// session.setAttribute("userContext", userContext);

	}

	/**
	 * Gets the group authorities by user.
	 *
	 * @param user the user
	 * @return the group authorities by user
	 */
	private List<Authority> getGroupAuthoritiesByUser(User user)
	{
		List<Authority> authorities = new ArrayList<Authority>();

		return authorities;
	}

	/**
	 * Retrieves the correct ROLES.
	 *
	 * @param roles an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthorityImpl> getAuthorities(String role)
	{
		// Create a list of grants for this user
		List<GrantedAuthorityImpl> authList = new ArrayList<GrantedAuthorityImpl>();
		if (!ValidationUtil.isNull(role))
		{
			authList.add(new GrantedAuthorityImpl(role));
		}

		// Return list of granted authorities
		return authList;
	}

}