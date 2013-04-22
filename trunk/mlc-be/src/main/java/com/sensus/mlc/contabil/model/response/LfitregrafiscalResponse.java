package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal;

/**
 * The Class LfitregrafiscalResponse.
 *
 * @author - Washington
 */
public class LfitregrafiscalResponse extends Response
{

	/** The lfitregrafiscal. */
	private List<Lfitregrafiscal> lfitregrafiscal = new ArrayList<Lfitregrafiscal>();

	/**
	 * Sets the lfitregrafiscals.
	 * 
	 * @param lfitregrafiscalList the new lfitregrafiscal
	 */
	public void setLfitregrafiscals(List<Lfitregrafiscal> lfitregrafiscalList)
	{
		this.lfitregrafiscal = lfitregrafiscalList;
	}

	/**
	 * Gets the lfitregrafiscal.
	 * 
	 * @return the lfitregrafiscal
	 */
	public List<Lfitregrafiscal> getLfitregrafiscal()
	{
		return this.lfitregrafiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitregrafiscalResponse [getLfitregrafiscal()=" + this.getLfitregrafiscal() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

