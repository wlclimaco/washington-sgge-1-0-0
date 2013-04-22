package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm;

/**
 * The Class LfncmnbmResponse.
 *
 * @author - Washington
 */
public class LfncmnbmResponse extends Response
{

	/** The lfncmnbm. */
	private List<Lfncmnbm> lfncmnbm = new ArrayList<Lfncmnbm>();

	/**
	 * Sets the lfncmnbms.
	 * 
	 * @param lfncmnbmList the new lfncmnbm
	 */
	public void setLfncmnbms(List<Lfncmnbm> lfncmnbmList)
	{
		this.lfncmnbm = lfncmnbmList;
	}

	/**
	 * Gets the lfncmnbm.
	 * 
	 * @return the lfncmnbm
	 */
	public List<Lfncmnbm> getLfncmnbm()
	{
		return this.lfncmnbm;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfncmnbmResponse [getLfncmnbm()=" + this.getLfncmnbm() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

