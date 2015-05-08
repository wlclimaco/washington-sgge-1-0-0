package com.prosperitasglobal.sendsolv.integration.twilio.bac;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IIvrModel
{
	public String getViewName();

	public String getNextController();

	public void setNextController(String nextAction);

	public InternalResultsResponse<String> resolveAction(IvrRequest request, CallRecord callRecord);
}
