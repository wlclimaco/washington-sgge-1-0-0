package com.sensus.mlc.contabil.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.calccusto.model.Calccusto;

/**
 * The Class CalccustoResponse.
 *
 * @author - Washington
 */
public class CalccustoResponse extends Response
{

	/** The calccusto. */
	private List<Calccusto> calccusto = new ArrayList<Calccusto>();

	/**
	 * Sets the calccustos.
	 * 
	 * @param calccustoList the new calccusto
	 */
	public void setCalccustos(List<Calccusto> calccustoList)
	{
		this.calccusto = calccustoList;
	}

	/**
	 * Gets the calccusto.
	 * 
	 * @return the calccusto
	 */
	public List<Calccusto> getCalccusto()
	{
		return this.calccusto;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CalccustoResponse [getCalccusto()=" + this.getCalccusto() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}

