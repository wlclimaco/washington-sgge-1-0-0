package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal;

/**
 * The Class LfitclfiscalResponse.
 *
 * @author - Washington
 */
public class LfitclfiscalResponse extends Response
{

	/** The lfitclfiscal. */
	private List<Lfitclfiscal> lfitclfiscal = new ArrayList<Lfitclfiscal>();

	/**
	 * Sets the lfitclfiscals.
	 * 
	 * @param lfitclfiscalList the new lfitclfiscal
	 */
	public void setLfitclfiscals(List<Lfitclfiscal> lfitclfiscalList)
	{
		this.lfitclfiscal = lfitclfiscalList;
	}

	/**
	 * Gets the lfitclfiscal.
	 * 
	 * @return the lfitclfiscal
	 */
	public List<Lfitclfiscal> getLfitclfiscal()
	{
		return this.lfitclfiscal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitclfiscalResponse [getLfitclfiscal()=" + this.getLfitclfiscal() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

