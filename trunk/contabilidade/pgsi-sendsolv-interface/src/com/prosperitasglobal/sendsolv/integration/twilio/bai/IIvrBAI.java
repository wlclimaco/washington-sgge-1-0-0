package com.prosperitasglobal.sendsolv.integration.twilio.bai;

import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.model.response.IvrResponse;

/**
 * The Interface ISdnCheckerBAI.
 */
public interface IIvrBAI
{
	public IvrResponse verifyIdentity(IvrRequest request);

	public IvrResponse processAction(IvrRequest request);

	public IvrResponse processDisconnect(IvrRequest request);
}
