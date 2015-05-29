package com.prosperitasglobal.sendsolv.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;

import com.prosperitasglobal.sendsolv.util.RemoteUserContext;
import com.prosperitasglobal.sendsolv.util.RemoteUserContextHolder;

/**
 * The Class LCNotClientSecurementCallback.
 */
public class LCNotClientSecurementCallback implements CallbackHandler
{

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LCNotClientSecurementCallback.class);

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
					try
					{
						throw new Exception("Could not find remote user context");
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (userCtx.getUserName() == null)
				{
					try
					{
						throw new Exception("Could not find user name in remote context");
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (userCtx.getPassword() == null)
				{
					try
					{
						throw new Exception("Could not find password in remote context");
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
