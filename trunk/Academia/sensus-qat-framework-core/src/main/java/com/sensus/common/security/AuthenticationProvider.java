package com.sensus.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * This AuthenticationProvider is used to provide {@link UserDetails}. It does this by retrieving
 * them from the {@link UsernamePasswordAuthenticationToken} or, if that token
 * is not authenticated, by using the implementation of the {@link IAuthenticationServiceAdapter}
 * to authenticate information in the token.
 * <p>
 * The reference to this adapter from the authentication-provider reference
 * in the authentication-manager should be similar to the following:
 *
 * <pre>
 * <code>
 * &lt;authentication-manager alias="authenticationManager" erase-credentials="false"&gt;
 *  &lt;authentication-provider ref="authenticationProvider" /&gt;
 * &lt;/authentication-manager&gt;
 *
 * &lt;bean id="authenticationProvider" class="com.sensus.security.authentication.AuthenticationProvider" &gt;
 *  &lt;property name="authenticationServiceAdapter" ref="authenticationServiceAdapter" /&gt;
 * &lt;/bean&gt;
 *
 * &lt;bean id="authenticationServiceAdapter"
 *  class="com.sensus.security.authentication.JOSSOAuthenticationAdapter"&gt;
 *  &lt;property name="serviceLocator" ref="jossoGatewayServiceLocator" /&gt;
 *  &lt;/bean&gt;
 *
 * &lt;bean id="jossoGatewayServiceLocator" class="org.josso.gateway.WebserviceGatewayServiceLocator" &gt;
 *  &lt;property name="endpoint" value="localhost:8880" /&gt;
 *  &lt;property name="transportSecurity" value="none" /&gt;
 * &lt;/bean&gt;
 *
 */
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationProvider.class);

	private IAuthenticationServiceAdapter authenticationServiceAdapter;

	public IAuthenticationServiceAdapter getAuthenticationServiceAdapter()
	{
		return authenticationServiceAdapter;
	}

	public void setAuthenticationServiceAdapter(
			IAuthenticationServiceAdapter authenticationServiceAdapter)
	{
		this.authenticationServiceAdapter = authenticationServiceAdapter;
	}

	/**
	 * This method takes the SSO session ID from the argUserDetails {@link UserWithSsoSessionId} object and sets it on
	 * the {@link WebAuthenticationDetailsWithSso} argAuthToken object.
	 *
	 * @param argUserDetails - {@link UserDetails} containing the SSO Session ID.
	 * @param argAuthToken - {@link UsernamePasswordAuthenticationToken} which will have the SSO Sesison ID
	 *            added to its {@link WebAuthenticationDetailsWithSso} attribute.
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails argUserDetails,
			UsernamePasswordAuthenticationToken argAuthToken)
			throws AuthenticationException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Before UserPasswordAuthenticationToken=[" + argAuthToken.toString() + "]");
		}

		WebAuthenticationDetailsWithSso webDetails = (WebAuthenticationDetailsWithSso)argAuthToken.getDetails();

		UserWithSsoSessionId userDetails = (UserWithSsoSessionId)argUserDetails;
		webDetails.setSsoSessionId(userDetails.getSsoSessionId());

		if (LOG.isDebugEnabled())
		{
			LOG.debug("After UserPasswordAuthenticationToken=[" + argAuthToken.toString() + "]");
		}
	}

	/**
	 * If the {@link UsernamePasswordAuthenticationToken} argAuthToken is already authenticated, will retrieve the
	 * {@link UserDetails} from it. Otherwise, will use the principal and credentials from argAuthToken to authenticate
	 * using the {@link IAuthenticationServiceAdapter}.
	 *
	 * @param argUserName - The user name that should be the same as the principal
	 * @param argAuthToken - {@link UsernamePasswordAuthenticationToken} containing principal and credentials of user
	 *            to be authenticated.
	 * @return {@link UserDetails} of user that has been authenticated.
	 */
	@Override
	protected UserDetails retrieveUser(String argUserName, UsernamePasswordAuthenticationToken argAuthToken)
			throws AuthenticationException
	{
		UserDetails userDetails = null;

		if (argAuthToken == null)
		{
			throw new InsufficientAuthenticationException("UsernamePasswordAuthenticationToken is null");
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("UsernamePasswordAuthenticationToken: [" + argAuthToken
					+ "]");
		}

		// If SSO Authentication is being used, the authentication token may already be authenticated.
		// Just create the UserDetails in that case.
		if (argAuthToken.isAuthenticated())
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Authentication token is authenticated.  Create the UserDetails and we're done");
			}

			userDetails = userDetailsFromAuthenticatedToken(argAuthToken);
		}
		// If there was no SSO session ID for the UsernamePassworkAuthenticationFilter to enable creation
		// of the Authentication token, the following code will be used to authenticate a user through the
		// authentication service adapter.
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Authentication token is not authenticated.  Will attempt authentication with the JOSSO gateway.");
			}

			userDetails = getAuthenticationServiceAdapter().retrieveUserDetails(argUserName, argAuthToken);
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("User Details=[" + userDetails + "]");
		}

		return userDetails;
	}

	private UserDetails userDetailsFromAuthenticatedToken(
			UsernamePasswordAuthenticationToken argAuthToken)
	{
		String ssoSessionID = null;

		Object details = argAuthToken.getDetails();

		if ((details != null) && (details instanceof WebAuthenticationDetailsWithSso))
		{
			ssoSessionID = ((WebAuthenticationDetailsWithSso)details).getSsoSessionId();
		}

		UserDetails userDetails =
				new UserWithSsoSessionId((String)argAuthToken.getPrincipal(),
						argAuthToken.getCredentials().toString(),
						true, true, true, true,
						argAuthToken.getAuthorities(),
						ssoSessionID);

		return userDetails;
	}

}

