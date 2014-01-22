package com.sensus.lc.light.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.Light;

public class FetchAllResponse extends InquiryResponse
{
	private List<Light> lightList = new ArrayList<Light>();

	/*
	 * Override this method so framework can work with this class.
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addResults(@SuppressWarnings("rawtypes") Collection coll)
	{
		getLightList().addAll(coll);
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

}
