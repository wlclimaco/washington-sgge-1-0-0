package com.sensus.lc.light.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.LightHistory;

/**
 * The Class LightHistoryResponse.
 */
public class LightHistoryResponse extends InquiryResponse
{

	/**
	 * Attributes.
	 */
	private List<LightHistory> lightHistory = new ArrayList<LightHistory>();

	/**
	 * Adds the results.
	 * 
	 * @param coll the coll
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addResults(@SuppressWarnings("rawtypes") Collection coll)
	{
		getLightHistory().addAll(coll);
	}

	/**
	 * @return the lightHistory
	 */
	public List<LightHistory> getLightHistory()
	{
		return lightHistory;
	}

	/**
	 * @param lightHistory the lightHistory to set
	 */
	public void setLightHistory(List<LightHistory> lightHistory)
	{
		this.lightHistory = lightHistory;
	}

}
