package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfseqserie.model.Lfseqserie;

/**
 * The Class LfseqserieResponse.
 *
 * @author - Washington
 */
public class LfseqserieResponse extends Response
{

	/** The lfseqserie. */
	private List<Lfseqserie> lfseqserie = new ArrayList<Lfseqserie>();

	/**
	 * Sets the lfseqseries.
	 * 
	 * @param lfseqserieList the new lfseqserie
	 */
	public void setLfseqseries(List<Lfseqserie> lfseqserieList)
	{
		this.lfseqserie = lfseqserieList;
	}

	/**
	 * Gets the lfseqserie.
	 * 
	 * @return the lfseqserie
	 */
	public List<Lfseqserie> getLfseqserie()
	{
		return this.lfseqserie;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfseqserieResponse [getLfseqserie()=" + this.getLfseqserie() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

