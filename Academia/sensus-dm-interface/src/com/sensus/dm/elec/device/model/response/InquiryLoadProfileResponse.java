package com.sensus.dm.elec.device.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.elec.device.model.LoadProfile;

/**
 * The Class InquiryLoadProfileResponse.
 * 
 * @author QAT Global
 */
public class InquiryLoadProfileResponse extends InquiryResponse
{

	/** The notes. */
	private List<LoadProfile> loadProfiles;

	/**
	 * Gets the load profiles.
	 * 
	 * @return the load profiles
	 */
	public List<LoadProfile> getLoadProfiles()
	{
		return loadProfiles;
	}

	/**
	 * Sets the load profiles.
	 * 
	 * @param loadProfiles the new load profiles
	 */
	public void setLoadProfiles(List<LoadProfile> loadProfiles)
	{
		this.loadProfiles = loadProfiles;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	/**
	 * Adds the results.
	 * 
	 * @param coll the coll
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setLoadProfiles(new ArrayList<LoadProfile>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LoadProfileResponse [getLoadProfiles()=" + getLoadProfiles() + ", toString()=" + super.toString() + "]";
	}

}