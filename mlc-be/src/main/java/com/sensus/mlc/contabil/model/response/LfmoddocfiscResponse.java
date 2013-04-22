package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc;

/**
 * The Class LfmoddocfiscResponse.
 *
 * @author - Washington
 */
public class LfmoddocfiscResponse extends Response
{

	/** The lfmoddocfisc. */
	private List<Lfmoddocfisc> lfmoddocfisc = new ArrayList<Lfmoddocfisc>();

	/**
	 * Sets the lfmoddocfiscs.
	 * 
	 * @param lfmoddocfiscList the new lfmoddocfisc
	 */
	public void setLfmoddocfiscs(List<Lfmoddocfisc> lfmoddocfiscList)
	{
		this.lfmoddocfisc = lfmoddocfiscList;
	}

	/**
	 * Gets the lfmoddocfisc.
	 * 
	 * @return the lfmoddocfisc
	 */
	public List<Lfmoddocfisc> getLfmoddocfisc()
	{
		return this.lfmoddocfisc;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfmoddocfiscResponse [getLfmoddocfisc()=" + this.getLfmoddocfisc() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

