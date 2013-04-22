package com.sensus.mlc.tabela.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.dominios.model.Dominios;

/**
 * The Class DominiosResponse.
 *
 * @author - Washington
 */
public class DominiosResponse extends Response
{

	/** The dominios. */
	private List<Dominios> dominios = new ArrayList<Dominios>();

	/**
	 * Sets the dominioss.
	 * 
	 * @param dominiosList the new dominios
	 */
	public void setDominioss(List<Dominios> dominiosList)
	{
		this.dominios = dominiosList;
	}

	/**
	 * Gets the dominios.
	 * 
	 * @return the dominios
	 */
	public List<Dominios> getDominios()
	{
		return this.dominios;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DominiosResponse [getDominios()=" + this.getDominios() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

