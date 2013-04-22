package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfitcompra.model.Lfitcompra;

/**
 * The Class LfitcompraResponse.
 *
 * @author - Washington
 */
public class LfitcompraResponse extends Response
{

	/** The lfitcompra. */
	private List<Lfitcompra> lfitcompra = new ArrayList<Lfitcompra>();

	/**
	 * Sets the lfitcompras.
	 * 
	 * @param lfitcompraList the new lfitcompra
	 */
	public void setLfitcompras(List<Lfitcompra> lfitcompraList)
	{
		this.lfitcompra = lfitcompraList;
	}

	/**
	 * Gets the lfitcompra.
	 * 
	 * @return the lfitcompra
	 */
	public List<Lfitcompra> getLfitcompra()
	{
		return this.lfitcompra;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitcompraResponse [getLfitcompra()=" + this.getLfitcompra() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

