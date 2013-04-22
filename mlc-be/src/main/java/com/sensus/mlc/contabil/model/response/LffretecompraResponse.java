package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lffretecompra.model.Lffretecompra;

/**
 * The Class LffretecompraResponse.
 *
 * @author - Washington
 */
public class LffretecompraResponse extends Response
{

	/** The lffretecompra. */
	private List<Lffretecompra> lffretecompra = new ArrayList<Lffretecompra>();

	/**
	 * Sets the lffretecompras.
	 * 
	 * @param lffretecompraList the new lffretecompra
	 */
	public void setLffretecompras(List<Lffretecompra> lffretecompraList)
	{
		this.lffretecompra = lffretecompraList;
	}

	/**
	 * Gets the lffretecompra.
	 * 
	 * @return the lffretecompra
	 */
	public List<Lffretecompra> getLffretecompra()
	{
		return this.lffretecompra;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LffretecompraResponse [getLffretecompra()=" + this.getLffretecompra() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

