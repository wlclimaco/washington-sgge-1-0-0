package com.sensus.mlc.ecomode.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;

/**
 * The Class InquiryEcoModeResponse.
 */
public class InquiryEcoModeResponse extends InquiryResponse
{
	/** The eco mode base list. */
	private List<EcoModeBaseline> ecoModeBaseList;

	/** The lights. */
	private List<LightConsumption> lightConsumptions;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the eco mode base list.
	 * 
	 * @return the eco mode base list
	 */
	public List<EcoModeBaseline> getEcoModeBaseList()
	{
		return this.ecoModeBaseList;
	}

	/**
	 * Sets the eco mode base list.
	 * 
	 * @param ecoModeBaseList the new eco mode base list
	 */
	public void setEcoModeBaseList(List<EcoModeBaseline> ecoModeBaseList)
	{
		this.ecoModeBaseList = ecoModeBaseList;
	}

	/**
	 * Gets the light consumptions.
	 * 
	 * @return the light consumptions
	 */
	public List<LightConsumption> getLightConsumptions()
	{
		return this.lightConsumptions;
	}

	/**
	 * Sets the light consumptions.
	 * 
	 * @param lightConsumptions the new light consumptions
	 */
	public void setLightConsumptions(List<LightConsumption> lightConsumptions)
	{
		this.lightConsumptions = lightConsumptions;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return this.fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addResults(Collection coll)
	{
		if (ValidationUtil.isNullOrEmpty(new ArrayList(coll)))
		{
			return;
		}

		Class<?> elementClass = coll.iterator().next().getClass();

		if (LightConsumption.class.isAssignableFrom(elementClass))
		{
			setLightConsumptions(new ArrayList<LightConsumption>(coll));
			return;
		}

		if (EcoModeBaseline.class.isAssignableFrom(elementClass))
		{
			setEcoModeBaseList(new ArrayList<EcoModeBaseline>(coll));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryEcoModeResponse [getEcoModeBaseList()=" + getEcoModeBaseList() + ", getLightConsumptions()="
				+ getLightConsumptions() + ", getFileName()=" + getFileName() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + ", getResponseTime()=" + getResponseTime() + "]";
	}

}
