package com.qat.samples.sysmgmt.unittest.bas.ws.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler
{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
	{
		WSPasswordCallback callback = (WSPasswordCallback)callbacks[0];
		if (callback.getIdentifier().equals("scott"))
		{
			callback.setPassword("wombat");
		}
	}
}