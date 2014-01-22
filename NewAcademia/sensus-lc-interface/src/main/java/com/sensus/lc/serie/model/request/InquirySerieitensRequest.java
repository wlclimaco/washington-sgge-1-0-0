package com.sensus.lc.serie.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.serie.model.Serieitens;

/**
 * The Class InquirySerieitensRequest.
 * 
 * @author Washington
 */
public class InquirySerieitensRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The serieitenss. */
	private List<Serieitens> serieitenss;

	/**
	 * Instantiates a new inquiry serieitens request.
	 */
	public InquirySerieitensRequest()
	{

	}

	/**
	 * Instantiates a new inquiry serieitens request.
	 * 
	 * @param serieitens the serieitens
	 */
	public InquirySerieitensRequest(Serieitens serieitens)
	{
		addSerieitens(serieitens);
	}

	/**
	 * Gets the serieitenss.
	 * 
	 * @return the serieitenss
	 */
	public List<Serieitens> getSerieitenss()
	{
		return serieitenss;
	}

	/**
	 * Sets the serieitenss.
	 * 
	 * @param serieitenss the new serieitenss
	 */
	public void setSerieitenss(List<Serieitens> serieitenss)
	{
		this.serieitenss = serieitenss;
	}

	/**
	 * Gets the first serieitens.
	 * 
	 * @return the first serieitens
	 */
	public Serieitens getFirstSerieitens()
	{
		if ((getSerieitenss() != null) && !getSerieitenss().isEmpty())
		{
			return getSerieitenss().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the serieitens.
	 * 
	 * @param serieitens the serieitens
	 */
	public void addSerieitens(Serieitens serieitens)
	{
		if (getSerieitenss() == null)
		{
			setSerieitenss(new ArrayList<Serieitens>());
		}

		getSerieitenss().add(serieitens);
	}

	@Override
	public String toString()
	{
		return "InquirySerieitensRequest [getSerieitenss()=" + getSerieitenss() + ", getFirstSerieitens()="
				+ getFirstSerieitens()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
