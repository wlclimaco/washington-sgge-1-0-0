package com.sensus.mlc.tabela.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira;

/**
 * The Class ChaveestrangeiraResponse.
 *
 * @author - Washington
 */
public class ChaveestrangeiraResponse extends Response
{

	/** The chaveestrangeira. */
	private List<Chaveestrangeira> chaveestrangeira = new ArrayList<Chaveestrangeira>();

	/**
	 * Sets the chaveestrangeiras.
	 * 
	 * @param chaveestrangeiraList the new chaveestrangeira
	 */
	public void setChaveestrangeiras(List<Chaveestrangeira> chaveestrangeiraList)
	{
		this.chaveestrangeira = chaveestrangeiraList;
	}

	/**
	 * Gets the chaveestrangeira.
	 * 
	 * @return the chaveestrangeira
	 */
	public List<Chaveestrangeira> getChaveestrangeira()
	{
		return this.chaveestrangeira;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ChaveestrangeiraResponse [getChaveestrangeira()=" + this.getChaveestrangeira() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

