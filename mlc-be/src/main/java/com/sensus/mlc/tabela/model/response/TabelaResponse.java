package com.sensus.mlc.tabela.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.tabela.model.Tabela;

/**
 * The Class TabelaResponse.
 *
 * @author - Washington
 */
public class TabelaResponse extends Response
{

	/** The tabela. */
	private List<Tabela> tabela = new ArrayList<Tabela>();

	/**
	 * Sets the tabelas.
	 * 
	 * @param tabelaList the new tabela
	 */
	public void setTabelas(List<Tabela> tabelaList)
	{
		this.tabela = tabelaList;
	}

	/**
	 * Gets the tabela.
	 * 
	 * @return the tabela
	 */
	public List<Tabela> getTabela()
	{
		return this.tabela;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TabelaResponse [getTabela()=" + this.getTabela() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

