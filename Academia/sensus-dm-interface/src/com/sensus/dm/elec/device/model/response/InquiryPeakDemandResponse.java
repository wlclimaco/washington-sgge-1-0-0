package com.sensus.dm.elec.device.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.elec.device.model.PeakDemand;

/**
 * The Class InquiryPeakDemandResponse.
 * 
 * @author - QAT Brazil.
 */

public class InquiryPeakDemandResponse extends InquiryResponse
{

	/** The peak demands. */
	private List<PeakDemand> peakDemands;

	/**
	 * Gets the peak demands.
	 * 
	 * @return the peak demands
	 */
	public List<PeakDemand> getPeakDemands()
	{
		return peakDemands;
	}

	/**
	 * Sets the peak demands.
	 * 
	 * @param peakDemands the new peak demands
	 */
	public void setPeakDemands(List<PeakDemand> peakDemands)
	{
		this.peakDemands = peakDemands;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setPeakDemands(new ArrayList<PeakDemand>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryPeakDemandResponse [getPeakDemands()=" + getPeakDemands() + "]";
	}
}
