package com.sensus.common.query;

import java.util.List;

/**
 * Base criteria class.
 */
public abstract class Criteria
{
	private Conjunction conjuction = Conjunction.AND;

	/**
	 * Utility method to handle strings within SQL.
	 *
	 * @param s the s
	 * @return the string
	 */
	protected String quote(String s)
	{
		if (s == null)
		{
			return "null";
		}
		StringBuffer str = new StringBuffer();
		str.append('\'');
		for (int i = 0; i < s.length(); i++)
		{
			if (s.charAt(i) == '\\' || s.charAt(i) == '\"' || s.charAt(i) == '\'')
			{
				str.append('\\');
			}
			str.append(s.charAt(i));
		}
		str.append('\'');
		return str.toString();
	}

	public abstract void validate(List<String> msgList);

	/**
	 * Gets the conjuction.
	 *
	 * @return the conjuction
	 */
	public Conjunction getConjuction()
	{
		return this.conjuction;
	}

	/**
	 * Sets the conjuction.
	 *
	 * @param conjuction the new conjuction
	 */
	public void setConjuction(Conjunction conjuction)
	{
		this.conjuction = conjuction;
	}
}
