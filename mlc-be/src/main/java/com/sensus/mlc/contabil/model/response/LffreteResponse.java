package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lffrete.model.Lffrete;

/**
 * The Class LffreteResponse.
 *
 * @author - Washington
 */
public class LffreteResponse extends Response
{

	/** The lffrete. */
	private List<Lffrete> lffrete = new ArrayList<Lffrete>();

	/**
	 * Sets the lffretes.
	 * 
	 * @param lffreteList the new lffrete
	 */
	public void setLffretes(List<Lffrete> lffreteList)
	{
		this.lffrete = lffreteList;
	}

	/**
	 * Gets the lffrete.
	 * 
	 * @return the lffrete
	 */
	public List<Lffrete> getLffrete()
	{
		return this.lffrete;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LffreteResponse [getLffrete()=" + this.getLffrete() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

