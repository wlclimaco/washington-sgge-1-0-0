package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfmodnota.model.Lfmodnota;

/**
 * The Class LfmodnotaResponse.
 *
 * @author - Washington
 */
public class LfmodnotaResponse extends Response
{

	/** The lfmodnota. */
	private List<Lfmodnota> lfmodnota = new ArrayList<Lfmodnota>();

	/**
	 * Sets the lfmodnotas.
	 * 
	 * @param lfmodnotaList the new lfmodnota
	 */
	public void setLfmodnotas(List<Lfmodnota> lfmodnotaList)
	{
		this.lfmodnota = lfmodnotaList;
	}

	/**
	 * Gets the lfmodnota.
	 * 
	 * @return the lfmodnota
	 */
	public List<Lfmodnota> getLfmodnota()
	{
		return this.lfmodnota;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfmodnotaResponse [getLfmodnota()=" + this.getLfmodnota() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

