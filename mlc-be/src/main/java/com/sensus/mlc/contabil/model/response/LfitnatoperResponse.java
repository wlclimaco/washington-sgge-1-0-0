package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper;

/**
 * The Class LfitnatoperResponse.
 *
 * @author - Washington
 */
public class LfitnatoperResponse extends Response
{

	/** The lfitnatoper. */
	private List<Lfitnatoper> lfitnatoper = new ArrayList<Lfitnatoper>();

	/**
	 * Sets the lfitnatopers.
	 * 
	 * @param lfitnatoperList the new lfitnatoper
	 */
	public void setLfitnatopers(List<Lfitnatoper> lfitnatoperList)
	{
		this.lfitnatoper = lfitnatoperList;
	}

	/**
	 * Gets the lfitnatoper.
	 * 
	 * @return the lfitnatoper
	 */
	public List<Lfitnatoper> getLfitnatoper()
	{
		return this.lfitnatoper;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitnatoperResponse [getLfitnatoper()=" + this.getLfitnatoper() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

