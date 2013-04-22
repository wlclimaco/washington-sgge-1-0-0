package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfncm.model.Lfncm;

/**
 * The Class LfncmResponse.
 *
 * @author - Washington
 */
public class LfncmResponse extends Response
{

	/** The lfncm. */
	private List<Lfncm> lfncm = new ArrayList<Lfncm>();

	/**
	 * Sets the lfncms.
	 * 
	 * @param lfncmList the new lfncm
	 */
	public void setLfncms(List<Lfncm> lfncmList)
	{
		this.lfncm = lfncmList;
	}

	/**
	 * Gets the lfncm.
	 * 
	 * @return the lfncm
	 */
	public List<Lfncm> getLfncm()
	{
		return this.lfncm;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfncmResponse [getLfncm()=" + this.getLfncm() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

