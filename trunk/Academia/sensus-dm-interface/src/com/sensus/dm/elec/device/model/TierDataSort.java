package com.sensus.dm.elec.device.model;

import java.util.Comparator;

/**
 * The Class TierDataSort.
 * 
 * @author QAT Brazil.
 */
public class TierDataSort implements Comparator<TierData>
{
	@Override
	public int compare(TierData arg0, TierData arg1)
	{
		return arg0.getTierId().compareTo(arg1.getTierId());
	}

}
