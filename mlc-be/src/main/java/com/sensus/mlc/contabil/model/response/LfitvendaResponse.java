package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfitvenda.model.Lfitvenda;

/**
 * The Class LfitvendaResponse.
 *
 * @author - Washington
 */
public class LfitvendaResponse extends Response
{

	/** The lfitvenda. */
	private List<Lfitvenda> lfitvenda = new ArrayList<Lfitvenda>();

	/**
	 * Sets the lfitvendas.
	 * 
	 * @param lfitvendaList the new lfitvenda
	 */
	public void setLfitvendas(List<Lfitvenda> lfitvendaList)
	{
		this.lfitvenda = lfitvendaList;
	}

	/**
	 * Gets the lfitvenda.
	 * 
	 * @return the lfitvenda
	 */
	public List<Lfitvenda> getLfitvenda()
	{
		return this.lfitvenda;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitvendaResponse [getLfitvenda()=" + this.getLfitvenda() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

