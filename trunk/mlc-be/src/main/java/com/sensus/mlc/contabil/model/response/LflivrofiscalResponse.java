package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal;

/**
 * The Class LflivrofiscalResponse.
 *
 * @author - Washington
 */
public class LflivrofiscalResponse extends Response
{

	/** The lflivrofiscal. */
	private List<Lflivrofiscal> lflivrofiscal = new ArrayList<Lflivrofiscal>();

	/**
	 * Sets the lflivrofiscals.
	 * 
	 * @param lflivrofiscalList the new lflivrofiscal
	 */
	public void setLflivrofiscals(List<Lflivrofiscal> lflivrofiscalList)
	{
		this.lflivrofiscal = lflivrofiscalList;
	}

	/**
	 * Gets the lflivrofiscal.
	 * 
	 * @return the lflivrofiscal
	 */
	public List<Lflivrofiscal> getLflivrofiscal()
	{
		return this.lflivrofiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LflivrofiscalResponse [getLflivrofiscal()=" + this.getLflivrofiscal() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

