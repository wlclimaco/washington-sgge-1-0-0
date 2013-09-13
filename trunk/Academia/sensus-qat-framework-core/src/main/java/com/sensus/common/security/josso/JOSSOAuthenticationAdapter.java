package com.sensus.common.security.josso;

import java.util.ArrayList;
import java.util.List;

import org.josso.gateway.GatewayServiceLocator;
import org.josso.gateway.assertion.exceptions.AssertionNotValidException;
import org.josso.gateway.identity.SSORole;
import org.josso.gateway.identity.SSOUser;
import org.josso.gateway.identity.exceptions.IdentityProvisioningException;
import org.josso.gateway.identity.exceptions.NoSuchUserException;
import org.josso.gateway.identity.exceptions.SSOIdentityException;
import org.josso.gateway.identity.service.SSOIdentityManagerService;
import org.josso.gateway.identity.service.SSOIdentityProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sensus.common.security.IAuthenticationServiceAdapter;
import com.sensus.common.security.UserWithSsoSessionId;

/**
*
* This Authentication Adapter is used to provide {@link UserDetails} or {@link UsernamePasswordAuthenticationToken}
* by retrieving from a JOSSO gateway. The reference to this class from a Spring authentication-provider to
* a bean which would appear similar to the following sample:
*
* <pre>
* <code>
* &lt;bean id="authenticationdapter" class="com.sensus.seurity.authentication.JOSSOAuthenticationAdapter" &gt;
*  &lt;property name="gatewayServiceLocator" ref="jossoGatewayServiceLocator" /&gt;
* &lt;/bean&gt;
*
* &lt;bean id="jossoGatewayServiceLocator" class="org.josso.gateway.WebserviceGatewayServiceLocator" &gt;
*  &lt;property name="endpoint" value="localhost:8880" /&gt;
*  &lt;property name="transportSecurity" value="none" /&gt;
* &lt;/bean&gt;
*
*/
public class JOSSOAuthenticationAdapter implements IAuthenticationServiceAdapter, InitializingBean
{
	private static final String SECURITY_DOMAIN = "josso";

	private static final Logger LOG = LoggerFactory.getLogger(JOSSOAuthenticationAdapter.class);

	private GatewayServiceLocator serviceLocator;

	public GatewayServiceLocator getServiceLocator()
	{
		return serviceLocator;
	}

	public void setServiceLocator(GatewayServiceLocator serviceLocator)
	{
		this.serviceLocator = serviceLocator;
	}

	@Override
	public UserDetails retrieveUserDetails(String argUserName,
			UsernamePasswordAuthenticationToken argAuthToken)
	{
		UserDetails userDetails = null;
		String ssoSessionId = null;

		try
		{
			ssoSessionId = retrieveSsoSessionId(argUserName, argAuthToken);
		}
		catch (IdentityProvisioningException ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new BadCredentialsException(ex.getMessage(), ex);
		}
		catch (AssertionNotValidException ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException(ex.getMessage(), ex);
		}
		catch (Exception ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new AuthenticationServiceException(ex.getMessage(), ex);
		}

		try
		{
			userDetails = retrieveUserDetails(argUserName, argAuthToken, ssoSessionId);
		}
		catch (NoSuchUserException ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException(ex.getMessage(), ex);
		}
		catch (SSOIdentityException ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException(ex.getMessage(), ex);
		}
		catch (Exception ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new AuthenticationServiceException(ex.getMessage(), ex);
		}
		return userDetails;
	}

	@Override
	public UsernamePasswordAuthenticationToken createAuthentication(
			String ssoSessionId)
	{
		SSOUser theUser = null;
		SSORole[] theRoles = null;

		try
		{
			SSOIdentityManagerService managerService = getServiceLocator().getSSOIdentityManager();
			theUser = managerService.findUserInSession("", ssoSessionId);
			theRoles = managerService.findRolesBySSOSessionId("", ssoSessionId);
		}
		catch (NoSuchUserException ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException(ex.getMessage(), ex);
		}
		catch (SSOIdentityException ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException(ex.getMessage(), ex);
		}
		catch (Exception ex)
		{
			LOG.error(ex.getMessage(), ex);
			throw new AuthenticationServiceException(ex.getMessage(), ex);
		}

		UsernamePasswordAuthenticationToken authToken = createAuthenticationToken(theUser, theRoles);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("UsernamePasswordAuthenticationToken created from JOSSO SSO Session ID: [" + authToken
					+ "]");
		}

		return authToken;
	}

	protected UsernamePasswordAuthenticationToken createAuthenticationToken(SSOUser argUser, SSORole[] argRoles)
	{
		UsernamePasswordAuthenticationToken authToken = null;

		List<GrantedAuthority> authorities = createGrantedAuthoritiesList(argUser, argRoles);

		authToken = new UsernamePasswordAuthenticationToken(argUser.getName(), "NOT_REAL_PASSWORD", authorities);

		return authToken;
	}

	protected List<GrantedAuthority> createGrantedAuthoritiesList(SSOUser argUser, SSORole[] argRoles)
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(argRoles.length);

		for (SSORole role : argRoles)
		{
			String roleName = role.getName();
			authorities.add(new SimpleGrantedAuthority(roleName));
		}

		return authorities;
	}

	private String retrieveSsoSessionId(String argUserName,
			UsernamePasswordAuthenticationToken argAuthToken) throws Exception,
			IdentityProvisioningException, AssertionNotValidException
	{
		SSOIdentityProviderService providerService = getServiceLocator().getSSOIdentityProvider();

		if (providerService == null)
		{
			throw new ProviderNotFoundException(
					"The Gateway Service Locater returned a null SSOIdentityProviderService");
		}

		String result =
				providerService.assertIdentityWithSimpleAuthentication(argUserName, SECURITY_DOMAIN,
						(String)argAuthToken.getPrincipal(), (String)argAuthToken.getCredentials());

		if (result == null)
		{
			throw new AuthenticationServiceException("assertIdentity failed with " + argUserName + ", " + SECURITY_DOMAIN + ", "
					+ argAuthToken.getPrincipal() + ", " + argAuthToken.getCredentials());
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Result from assertIdentity=[" + result + "]");
		}
		String ssoSessionID = providerService.resolveAuthenticationAssertion(argUserName, result);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("SessionID=[" + ssoSessionID + "]");
		}

		return ssoSessionID;
	}

	private UserDetails retrieveUserDetails(String argUserName,
			UsernamePasswordAuthenticationToken argAuthToken,
			String ssoSessionID) throws Exception, NoSuchUserException,
			SSOIdentityException
	{
		SSOIdentityManagerService managerService = getServiceLocator().getSSOIdentityManager();

		SSOUser ssoUser = managerService.findUser(argUserName, SECURITY_DOMAIN, argUserName);

		SSORole[] ssoRoles = managerService.findRolesBySSOSessionId(argUserName, ssoSessionID);

		List<GrantedAuthority> authorities = createGrantedAuthoritiesList(ssoUser, ssoRoles);

		UserDetails userDetails =
				new UserWithSsoSessionId((String)argAuthToken.getPrincipal(),
						argAuthToken.getCredentials().toString(),
						true, true, true, true,
						authorities,
						ssoSessionID);

		return userDetails;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		if (getServiceLocator() == null)
		{
			throw new NullPointerException(getClass().getName() + ": serviceLocator property is null!");
		}
	}

}
