package com.sensus.dm.elec.device.model;

import java.util.Comparator;

/**
 * The Class IntervalReadSort.
 * 
 * @author QAT Global.
 */
public class IntervalReadSort implements Comparator<IntervalRead>
{
	@Override
	public int compare(IntervalRead arg0, IntervalRead arg1)
	{
		return arg0.getReadingDate().compareTo(arg1.getReadingDate());
	}

}
