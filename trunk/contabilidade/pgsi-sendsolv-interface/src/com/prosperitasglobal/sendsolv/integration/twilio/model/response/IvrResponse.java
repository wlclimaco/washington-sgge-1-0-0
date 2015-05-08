package com.prosperitasglobal.sendsolv.integration.twilio.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class CallingCardMaintenanceRequest.
 */
public class IvrResponse extends InquiryResponse
{

	private List<String> twimlResponseList;

	/**
	 * The Constructor.
	 */
	public IvrResponse()
	{

	}

	public List<String> getTwimlResponseList()
	{
		return twimlResponseList;
	}

	public void setTwimlResponseList(List<String> twimlResponseList)
	{
		this.twimlResponseList = twimlResponseList;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setTwimlResponseList((List<String>)coll);
	}

	@Override
	public String toString()
	{
		return "IvrResponse [getTwimlResponseList()=" + getTwimlResponseList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageList()=" + getMessageList() + ", getMessageInfoList()="
				+ getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}