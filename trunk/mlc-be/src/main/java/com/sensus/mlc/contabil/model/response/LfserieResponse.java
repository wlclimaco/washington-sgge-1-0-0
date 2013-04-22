package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfserie.model.Lfserie;

/**
 * The Class LfserieResponse.
 *
 * @author - Washington
 */
public class LfserieResponse extends Response
{

	/** The lfserie. */
	private List<Lfserie> lfserie = new ArrayList<Lfserie>();

	/**
	 * Sets the lfseries.
	 * 
	 * @param lfserieList the new lfserie
	 */
	public void setLfseries(List<Lfserie> lfserieList)
	{
		this.lfserie = lfserieList;
	}

	/**
	 * Gets the lfserie.
	 * 
	 * @return the lfserie
	 */
	public List<Lfserie> getLfserie()
	{
		return this.lfserie;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfserieResponse [getLfserie()=" + this.getLfserie() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

