package com.qat.samples.sysmgmt.bas.ws.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * This class has been copied from the test project and is required here for the web project to work.
 * This is only appropriate for this project. It is not a standard and should not be repeated anywhere.
 */
public class ClientPasswordCallback implements CallbackHandler
{

	/*
	 * (non-Javadoc)
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
	{
		WSPasswordCallback callback = (WSPasswordCallback)callbacks[0];
		// this seems ridiculous, but is necessary for passing authentication on to Spring-Security.
		// we're essentially bypassing CXF's WSS4JInterceptor by ensuring that the password callback always matches the
		// client password.
		callback.setPassword("koala");
	}
}