package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfclfiscal.model.Lfclfiscal;

/**
 * The Class LfclfiscalResponse.
 *
 * @author - Washington
 */
public class LfclfiscalResponse extends Response
{

	/** The lfclfiscal. */
	private List<Lfclfiscal> lfclfiscal = new ArrayList<Lfclfiscal>();

	/**
	 * Sets the lfclfiscals.
	 * 
	 * @param lfclfiscalList the new lfclfiscal
	 */
	public void setLfclfiscals(List<Lfclfiscal> lfclfiscalList)
	{
		this.lfclfiscal = lfclfiscalList;
	}

	/**
	 * Gets the lfclfiscal.
	 * 
	 * @return the lfclfiscal
	 */
	public List<Lfclfiscal> getLfclfiscal()
	{
		return this.lfclfiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfclfiscalResponse [getLfclfiscal()=" + this.getLfclfiscal() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

