package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfnatoper.model.Lfnatoper;

/**
 * The Class LfnatoperResponse.
 *
 * @author - Washington
 */
public class LfnatoperResponse extends Response
{

	/** The lfnatoper. */
	private List<Lfnatoper> lfnatoper = new ArrayList<Lfnatoper>();

	/**
	 * Sets the lfnatopers.
	 * 
	 * @param lfnatoperList the new lfnatoper
	 */
	public void setLfnatopers(List<Lfnatoper> lfnatoperList)
	{
		this.lfnatoper = lfnatoperList;
	}

	/**
	 * Gets the lfnatoper.
	 * 
	 * @return the lfnatoper
	 */
	public List<Lfnatoper> getLfnatoper()
	{
		return this.lfnatoper;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfnatoperResponse [getLfnatoper()=" + this.getLfnatoper() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

