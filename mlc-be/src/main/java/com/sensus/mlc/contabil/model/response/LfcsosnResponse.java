package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfcsosn.model.Lfcsosn;

/**
 * The Class LfcsosnResponse.
 *
 * @author - Washington
 */
public class LfcsosnResponse extends Response
{

	/** The lfcsosn. */
	private List<Lfcsosn> lfcsosn = new ArrayList<Lfcsosn>();

	/**
	 * Sets the lfcsosns.
	 * 
	 * @param lfcsosnList the new lfcsosn
	 */
	public void setLfcsosns(List<Lfcsosn> lfcsosnList)
	{
		this.lfcsosn = lfcsosnList;
	}

	/**
	 * Gets the lfcsosn.
	 * 
	 * @return the lfcsosn
	 */
	public List<Lfcsosn> getLfcsosn()
	{
		return this.lfcsosn;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfcsosnResponse [getLfcsosn()=" + this.getLfcsosn() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

