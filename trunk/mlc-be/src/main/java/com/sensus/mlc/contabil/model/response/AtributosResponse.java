package com.sensus.mlc.tabela.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.atributos.model.Atributos;

/**
 * The Class AtributosResponse.
 *
 * @author - Washington
 */
public class AtributosResponse extends Response
{

	/** The atributos. */
	private List<Atributos> atributos = new ArrayList<Atributos>();

	/**
	 * Sets the atributoss.
	 * 
	 * @param atributosList the new atributos
	 */
	public void setAtributoss(List<Atributos> atributosList)
	{
		this.atributos = atributosList;
	}

	/**
	 * Gets the atributos.
	 * 
	 * @return the atributos
	 */
	public List<Atributos> getAtributos()
	{
		return this.atributos;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AtributosResponse [getAtributos()=" + this.getAtributos() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

