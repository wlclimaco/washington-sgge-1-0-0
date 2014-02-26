package com.sensus.lc.curtir.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.curtir.model.Curtir;

/**
 * The Class InquiryCurtirRequest.
 * 
 * @author Washington
 */
public class InquiryCurtirRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The curtirs. */
	private List<Curtir> curtirs;

	/**
	 * Instantiates a new inquiry foto request.
	 */
	public InquiryCurtirRequest()
	{

	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param foto the foto
	 */
	public InquiryCurtirRequest(Curtir foto)
	{
		addCurtir(foto);
	}

	/**
	 * Gets the curtirs.
	 * 
	 * @return the curtirs
	 */
	public List<Curtir> getCurtirs()
	{
		return curtirs;
	}

	/**
	 * Sets the curtirs.
	 * 
	 * @param curtirs the new curtirs
	 */
	public void setCurtirs(List<Curtir> curtirs)
	{
		this.curtirs = curtirs;
	}

	/**
	 * Gets the first foto.
	 * 
	 * @return the first foto
	 */
	public Curtir getFirstCurtir()
	{
		if ((getCurtirs() != null) && !getCurtirs().isEmpty())
		{
			return getCurtirs().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the foto.
	 * 
	 * @param foto the foto
	 */
	public void addCurtir(Curtir foto)
	{
		if (getCurtirs() == null)
		{
			setCurtirs(new ArrayList<Curtir>());
		}

		getCurtirs().add(foto);
	}

	@Override
	public String toString()
	{
		return "InquiryCurtirRequest [getCurtirs()=" + getCurtirs() + ", getFirstCurtir()=" + getFirstCurtir()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
