package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli;

/**
 * The Class LftipofisccliResponse.
 *
 * @author - Washington
 */
public class LftipofisccliResponse extends Response
{

	/** The lftipofisccli. */
	private List<Lftipofisccli> lftipofisccli = new ArrayList<Lftipofisccli>();

	/**
	 * Sets the lftipofiscclis.
	 * 
	 * @param lftipofisccliList the new lftipofisccli
	 */
	public void setLftipofiscclis(List<Lftipofisccli> lftipofisccliList)
	{
		this.lftipofisccli = lftipofisccliList;
	}

	/**
	 * Gets the lftipofisccli.
	 * 
	 * @return the lftipofisccli
	 */
	public List<Lftipofisccli> getLftipofisccli()
	{
		return this.lftipofisccli;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LftipofisccliResponse [getLftipofisccli()=" + this.getLftipofisccli() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

