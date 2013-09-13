package com.sensus.dm.elec.device.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * The Class TOURead.
 * 
 * @author QAT Brazil
 */
public class TOURead
{

	/** The tier datas. */
	private List<TierData> tierDatas;

	/** The distinct channel name. */
	private LinkedHashSet<String> distinctChannelName;

	/**
	 * Instantiates a new tOU read.
	 */
	public TOURead()
	{
	}

	/**
	 * Instantiates a new tOU read.
	 * 
	 * @param pDistinctChannelName the distinct channel name
	 * @param pTierDatas the tier datas
	 */
	public TOURead(LinkedHashSet<String> pDistinctChannelName, List<TierData> pTierDatas)
	{
		setDistinctChannelName(pDistinctChannelName);
		setTierDatas(pTierDatas);
	}

	/**
	 * Instantiates a new tOU read.
	 * 
	 * @param pTierDatas the tier datas
	 */
	public TOURead(List<TierData> pTierDatas)
	{
		setTierDatas(pTierDatas);
	}

	/**
	 * Gets the tier datas.
	 * 
	 * @return the tier datas
	 */
	public List<TierData> getTierDatas()
	{
		return tierDatas;
	}

	/**
	 * Gets the tier data.
	 * 
	 * @param tierId the tier id
	 * @return the tier data
	 */
	public TierData getTierData(String tierId)
	{
		List<TierData> getTierDatas = getTierDatas();

		if (getTierDatas != null)
		{
			int index = getTierDatas.indexOf(new TierData(tierId));

			if (index != -1)
			{
				return getTierDatas.get(index);
			}
		}

		return null;
	}

	/**
	 * Sets the tier datas.
	 * 
	 * @param tierDatas the new tier datas
	 */
	public void setTierDatas(List<TierData> tierDatas)
	{
		this.tierDatas = tierDatas;
	}

	/**
	 * Adds the tier data.
	 * 
	 * @param tierData the tier data
	 */
	public void addTierData(TierData tierData)
	{
		if (getTierDatas() == null)
		{
			setTierDatas(new ArrayList<TierData>());
		}

		getTierDatas().add(tierData);
	}

	/**
	 * Gets the distinct channel name as list.
	 * 
	 * @return the distinct channel name as list
	 */
	public List<String> getDistinctChannelNameAsList()
	{
		return new ArrayList<String>(getDistinctChannelName());
	}

	/**
	 * Adds the distinct channel name.
	 * 
	 * @param channelName the channel name
	 */
	public void addDistinctChannelName(String channelName)
	{
		if (getDistinctChannelName() == null)
		{
			setDistinctChannelName(new LinkedHashSet<String>());
		}

		getDistinctChannelName().add(channelName);
	}

	/**
	 * Gets the distinct channel name.
	 * 
	 * @return the distinct channel name
	 */
	public LinkedHashSet<String> getDistinctChannelName()
	{
		return distinctChannelName;
	}

	/**
	 * Sets the distinct channel name.
	 * 
	 * @param distinctChannelName the new distinct channel name
	 */
	public void setDistinctChannelName(LinkedHashSet<String> distinctChannelName)
	{
		this.distinctChannelName = distinctChannelName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TOURead [tierDatas=" + tierDatas + ", distinctChannelName=" + distinctChannelName + "]";
	}

}
