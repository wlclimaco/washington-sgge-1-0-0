package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto;

/**
 * The Class LfitcalccustoResponse.
 *
 * @author - Washington
 */
public class LfitcalccustoResponse extends Response
{

	/** The lfitcalccusto. */
	private List<Lfitcalccusto> lfitcalccusto = new ArrayList<Lfitcalccusto>();

	/**
	 * Sets the lfitcalccustos.
	 * 
	 * @param lfitcalccustoList the new lfitcalccusto
	 */
	public void setLfitcalccustos(List<Lfitcalccusto> lfitcalccustoList)
	{
		this.lfitcalccusto = lfitcalccustoList;
	}

	/**
	 * Gets the lfitcalccusto.
	 * 
	 * @return the lfitcalccusto
	 */
	public List<Lfitcalccusto> getLfitcalccusto()
	{
		return this.lfitcalccusto;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitcalccustoResponse [getLfitcalccusto()=" + this.getLfitcalccusto() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

