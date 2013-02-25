package com.sensus.mlc.filial.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.filial.model.Filial;


/**
 * The Class ActionResponse.
 *
 * @author - QAT Brazil.
 */
public class FilialResponse extends Response
{

	/** The actions. */
	private List<Filial> filial;

	/**
	 * @return the filial
	 */
	public List<Filial> getFilial()
	{
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(List<Filial> filial)
	{
		this.filial = filial;
	}

}
