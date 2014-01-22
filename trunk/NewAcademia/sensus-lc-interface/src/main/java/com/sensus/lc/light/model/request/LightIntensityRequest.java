package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.lc.light.model.Light;

/**
 * The Class LightIntensityRequest.
 * 
 * @author Thiago
 */
public class LightIntensityRequest extends InquiryRequest
{

	private List<Light> lightList = new ArrayList<Light>();
	private Date messageDate;

	public LightIntensityRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * @return the lightList
	 */
	public List<Light> getLightList()
	{
		return lightList;
	}

	/**
	 * @param lightList the lightList to set
	 */
	public void setLightList(List<Light> lightList)
	{
		this.lightList = lightList;
	}

	/**
	 * Gets the message date.
	 * 
	 * @return the message date
	 */
	public Date getMessageDate()
	{
		return messageDate;
	}

	/**
	 * Sets the message date.
	 * 
	 * @param messageDate the new message date
	 */
	public void setMessageDate(Date messageDate)
	{
		this.messageDate = messageDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightIntensityRequest [getLightList()=" + getLightList() + ", getMessageDate()=" + getMessageDate()
				+ ", toString()=" + super.toString() + "]";
	}

}
