package com.sensus.lc.base.validation;

import java.util.Comparator;

import com.sensus.common.model.SortExpression;

/**
 * The Class InquiryComparator.
 */
public class SortComparator implements Comparator<SortExpression>
{
	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(SortExpression exp1, SortExpression exp2)
	{
		if ((exp1.getPriority() != null) && (exp2.getPriority() != null))
		{
			return exp1.getPriority().compareTo(exp2.getPriority());
		}

		if ((exp1.getPriority() == null) && (exp2.getPriority() == null))
		{
			return 0;
		}

		if (exp1.getPriority() == null)
		{
			return 1;
		}
		return -1;
	}
}