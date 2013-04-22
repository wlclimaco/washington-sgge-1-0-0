package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal;

/**
 * The Class LfregrafiscalResponse.
 *
 * @author - Washington
 */
public class LfregrafiscalResponse extends Response
{

	/** The lfregrafiscal. */
	private List<Lfregrafiscal> lfregrafiscal = new ArrayList<Lfregrafiscal>();

	/**
	 * Sets the lfregrafiscals.
	 * 
	 * @param lfregrafiscalList the new lfregrafiscal
	 */
	public void setLfregrafiscals(List<Lfregrafiscal> lfregrafiscalList)
	{
		this.lfregrafiscal = lfregrafiscalList;
	}

	/**
	 * Gets the lfregrafiscal.
	 * 
	 * @return the lfregrafiscal
	 */
	public List<Lfregrafiscal> getLfregrafiscal()
	{
		return this.lfregrafiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfregrafiscalResponse [getLfregrafiscal()=" + this.getLfregrafiscal() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

