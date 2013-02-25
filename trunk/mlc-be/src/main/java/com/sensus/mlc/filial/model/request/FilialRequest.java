package com.sensus.mlc.filial.model.request;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.filial.model.Filial;



/**
 * The Model Object Action.
 * 
 * @author - QAT Brazil
 */
public class FilialRequest extends LightSelectionRequest
{

	/** The action. */
	private Filial filial;

	/**
	 * @return the filial
	 */
	public Filial getFilial()
	{
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(Filial filial)
	{
		this.filial = filial;
	}

	/** The parent retry process. */
	private Integer parentRetry;

	/**
	 * @return the parentRetry
	 */
	public Integer getParentRetry()
	{
		return parentRetry;
	}

	/**
	 * @param parentRetry the parentRetry to set
	 */
	public void setParentRetry(Integer parentRetry)
	{
		this.parentRetry = parentRetry;
	}

}
