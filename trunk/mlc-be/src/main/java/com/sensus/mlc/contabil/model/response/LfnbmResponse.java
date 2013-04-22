package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfnbm.model.Lfnbm;

/**
 * The Class LfnbmResponse.
 *
 * @author - Washington
 */
public class LfnbmResponse extends Response
{

	/** The lfnbm. */
	private List<Lfnbm> lfnbm = new ArrayList<Lfnbm>();

	/**
	 * Sets the lfnbms.
	 * 
	 * @param lfnbmList the new lfnbm
	 */
	public void setLfnbms(List<Lfnbm> lfnbmList)
	{
		this.lfnbm = lfnbmList;
	}

	/**
	 * Gets the lfnbm.
	 * 
	 * @return the lfnbm
	 */
	public List<Lfnbm> getLfnbm()
	{
		return this.lfnbm;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfnbmResponse [getLfnbm()=" + this.getLfnbm() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

