package com.sensus.lc.ecomode.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.Consumption;

/**
 * The Class InquiryEcoModeResponse.
 */
public class InquiryEcoModeResponse extends InquiryResponse
{
	/** The lights. */
	private List<Consumption> lightConsumptions;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the light consumptions.
	 *
	 * @return the light consumptions
	 */
	public List<Consumption> getLightConsumptions()
	{
		return lightConsumptions;
	}

	/**
	 * Sets the light consumptions.
	 *
	 * @param lightConsumptions the new light consumptions
	 */
	public void setLightConsumptions(List<Consumption> lightConsumptions)
	{
		this.lightConsumptions = lightConsumptions;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setLightConsumptions(new ArrayList<Consumption>(coll));
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryEcoModeResponse [getLightConsumptions()=" + getLightConsumptions() + ", getFileName()="
				+ getFileName() + ", toString()=" + super.toString() + "]";
	}

}
