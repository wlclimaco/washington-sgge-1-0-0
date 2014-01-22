package com.sensus.lc.light.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.LightFilterValue;

/**
 * The Class LightFilterResponse.
 */
public class LightFilterResponse extends InquiryResponse
{

	/** The light filter value list. */
	private List<LightFilterValue> lightFilterValueList;

	/**
	 * Gets the light filter value list.
	 * 
	 * @return the light filter value list
	 */
	public List<LightFilterValue> getLightFilterValueList()
	{
		return lightFilterValueList;
	}

	/**
	 * Sets the light filter value list.
	 * 
	 * @param lightFilterValueList the new light filter value list
	 */
	public void setLightFilterValueList(List<LightFilterValue> lightFilterValueList)
	{
		this.lightFilterValueList = lightFilterValueList;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setLightFilterValueList(new ArrayList<LightFilterValue>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightFilterResponse [getLightFilterValueList()=" + getLightFilterValueList() + ", toString()="
				+ super.toString() + "]";
	}
}
