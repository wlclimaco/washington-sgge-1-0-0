package com.prosperitasglobal.sendsolv.integration.twilio.bac;

import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IIvrBAC
{
	public InternalResultsResponse<String> processIvrOption(IvrRequest request);

	public InternalResponse processDisconnect(IvrRequest request);
}
