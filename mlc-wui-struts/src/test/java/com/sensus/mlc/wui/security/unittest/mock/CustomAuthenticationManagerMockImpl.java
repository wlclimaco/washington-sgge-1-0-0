package com.sensus.mlc.wui.security.unittest.mock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.sensus.common.model.Authority;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;

// TODO: Auto-generated Javadoc
/**
 * A custom authentication manager that allows access if the user details
 * exist in the database and if the username and password are not the same.
 * Otherwise, throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManagerMockImpl implements AuthenticationManager
{

	/** User BCF. */
	private IUserBCF userBCF; // injected by Spring

	/** Settings BCF. */
	private ISettingsBCF settingsBCF; // injected by Spring

	/** The Constant GROUP_AUTHORIZATION_TYPE. */
	private static final String GROUP_AUTHORIZATION_TYPE = "GROUP";

	private static final int FIRST_ROLE = 0;

	/** SeverRequest. */
	private HttpServletRequest servletRequest;

	/**
	 * Gets the servlet request.
	 * 
	 * @return the servlet request
	 */
	public HttpServletRequest getServletRequest()
	{
		return servletRequest;
	}

	/**
	 * Sets the servlet request.
	 * 
	 * @param servletRequest the new servlet request
	 */
	public void setServletRequest(HttpServletRequest servletRequest)
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
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException
	{
		// Get Tenant based on the Server Name
		TenantRequest tenantRequest = new TenantRequest();
		tenantRequest.setServerName(getServletRequest().getServerName());
		// Retrieve the actual Tenant
		Tenant tenant = getSettingsBCF().fetchTenantByServerName(tenantRequest).getTenant();

		// Check if the logged user has access to this tenant and get the group authentication information.
		UserRequest userRequest = new UserRequest();
		userRequest.setTenant(tenant);
		com.sensus.mlc.user.model.User user = new com.sensus.mlc.user.model.User();
		user.setUserName(auth.getName());
		userRequest.setUser(user);
		// Get user complete user information
		user = getUserBCF().fetchUserByName(userRequest).getUser();

		// If user is null, it means the user does not have access to this tenant and also
		// Since We can have two users with the same with the same username in different tenants, confirm that
		// the password is for this tenant
		// also check if tenant is not null
		if (ValidationUtil.isNull(user))
		{
			throw new BadCredentialsException("User does not exist!");
		}
		else
		{
			storeUserContextOnSession(tenant, user);
			List<GrantedAuthorityImpl> grant = new ArrayList<GrantedAuthorityImpl>();
			grant.add(new GrantedAuthorityImpl("ROLE_Role.Admin"));
			return new UsernamePasswordAuthenticationToken("rod", "koala", grant);
		}
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
		userContext.setUserId(StringUtils.lowerCase(user.getUserName()));
		userContext.setId(user.getId());
		userContext.setTenant(tenant);
		userContext.setUserRole(user.getRole());
		// add the group list that the user has authorization to the Authority list on userContext.
		List<Authority> authorities = getGroupAuthoritiesByUser(user);
		userContext.setAuthorities(authorities);
		// put the user preferences object in the session
		HttpSession session = getServletRequest().getSession();
		session.setAttribute("userContext", userContext);

	}

	/**
	 * Gets the group authorities by user.
	 * 
	 * @param user the user
	 * @return the group authorities by user
	 */
	private List<Authority> getGroupAuthoritiesByUser(com.sensus.mlc.user.model.User user)
	{
		List<Authority> authorities = new ArrayList<Authority>();

		return authorities;
	}

	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

}