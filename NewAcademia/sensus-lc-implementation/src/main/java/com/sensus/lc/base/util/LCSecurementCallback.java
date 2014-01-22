/**
 * 
 */
package com.sensus.lc.base.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;
import org.springframework.ws.soap.security.wss4j.Wss4jSecuritySecurementException;

/**
 * The Class LCSecurementCallback.
 * 
 * @author Alex Barros
 */
public class LCSecurementCallback implements CallbackHandler
{

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LCSecurementCallback.class);

	/*
	 * (non-Javadoc)
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
	{
		for (Callback c : callbacks)
		{
			if (c instanceof WSPasswordCallback)
			{
				RemoteUserContext userCtx = RemoteUserContextHolder.getContext();
				if (userCtx == null)
				{
					throw new Wss4jSecuritySecurementException("Could not find remote user context");
				}
				if (userCtx.getUserName() == null)
				{
					throw new Wss4jSecuritySecurementException("Could not find user name in remote context");
				}
				if (userCtx.getPassword() == null)
				{
					throw new Wss4jSecuritySecurementException("Could not find password in remote context");
				}
				WSPasswordCallback pwdCb = (WSPasswordCallback)c;
				pwdCb.setIdentifier(userCtx.getUserName());
				pwdCb.setPassword(userCtx.getPassword());
				if (LOG.isInfoEnabled())
				{
					LOG.info(String.format("Added username [%s] and password [%s] to WS-Security header",
							pwdCb.getIdentifier(), pwdCb.getPassword()));
				}
			}
		}
	}
}
