package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lffretevenda.model.Lffretevenda;

/**
 * The Class LffretevendaResponse.
 *
 * @author - Washington
 */
public class LffretevendaResponse extends Response
{

	/** The lffretevenda. */
	private List<Lffretevenda> lffretevenda = new ArrayList<Lffretevenda>();

	/**
	 * Sets the lffretevendas.
	 * 
	 * @param lffretevendaList the new lffretevenda
	 */
	public void setLffretevendas(List<Lffretevenda> lffretevendaList)
	{
		this.lffretevenda = lffretevendaList;
	}

	/**
	 * Gets the lffretevenda.
	 * 
	 * @return the lffretevenda
	 */
	public List<Lffretevenda> getLffretevenda()
	{
		return this.lffretevenda;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LffretevendaResponse [getLffretevenda()=" + this.getLffretevenda() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

