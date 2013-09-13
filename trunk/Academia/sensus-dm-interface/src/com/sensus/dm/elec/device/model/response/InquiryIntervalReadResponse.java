package com.sensus.dm.elec.device.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.elec.device.model.IntervalRead;

/**
 * The Class InquiryIntevalReadResponse.
 * 
 * @author - QAT Brazil.
 */
public class InquiryIntervalReadResponse extends InquiryResponse
{

	/** The meters. */
	private List<IntervalRead> intervalReads;

	/**
	 * Gets the interval reads.
	 * 
	 * @return the interval reads
	 */
	public List<IntervalRead> getIntervalReads()
	{
		return intervalReads;
	}

	/**
	 * Sets the interval reads.
	 * 
	 * @param intervalReads the new interval reads
	 */
	public void setIntervalReads(List<IntervalRead> intervalReads)
	{
		this.intervalReads = intervalReads;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setIntervalReads(new ArrayList<IntervalRead>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryIntervalReadResponse [getIntervalReads()=" + getIntervalReads() + "]";
	}

}
