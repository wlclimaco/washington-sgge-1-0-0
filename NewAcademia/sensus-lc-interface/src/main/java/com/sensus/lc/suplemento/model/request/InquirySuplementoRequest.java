package com.sensus.lc.suplemento.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.suplemento.model.Suplemento;

/**
 * The Class InquirySuplementoRequest.
 * 
 * @author Washington
 */
public class InquirySuplementoRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The suplementos. */
	private List<Suplemento> suplementos;

	/**
	 * Instantiates a new inquiry suplemento request.
	 */
	public InquirySuplementoRequest()
	{

	}

	/**
	 * Instantiates a new inquiry suplemento request.
	 * 
	 * @param suplemento the suplemento
	 */
	public InquirySuplementoRequest(Suplemento suplemento)
	{
		addSuplemento(suplemento);
	}

	/**
	 * Gets the suplementos.
	 * 
	 * @return the suplementos
	 */
	public List<Suplemento> getSuplementos()
	{
		return suplementos;
	}

	/**
	 * Sets the suplementos.
	 * 
	 * @param suplementos the new suplementos
	 */
	public void setSuplementos(List<Suplemento> suplementos)
	{
		this.suplementos = suplementos;
	}

	/**
	 * Gets the first suplemento.
	 * 
	 * @return the first suplemento
	 */
	public Suplemento getFirstSuplemento()
	{
		if ((getSuplementos() != null) && !getSuplementos().isEmpty())
		{
			return getSuplementos().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the suplemento.
	 * 
	 * @param suplemento the suplemento
	 */
	public void addSuplemento(Suplemento suplemento)
	{
		if (getSuplementos() == null)
		{
			setSuplementos(new ArrayList<Suplemento>());
		}

		getSuplementos().add(suplemento);
	}

	@Override
	public String toString()
	{
		return "InquirySuplementoRequest [getSuplementos()=" + getSuplementos() + ", getFirstSuplemento()="
				+ getFirstSuplemento()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
