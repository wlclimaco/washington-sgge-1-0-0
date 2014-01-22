package com.sensus.lc.grupomuscular.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.grupomuscular.model.Grupomuscular;

/**
 * The Class InquiryGrupomuscularRequest.
 * 
 * @author Washington
 */
public class InquiryGrupomuscularRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The grupomusculars. */
	private List<Grupomuscular> grupomusculars;

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 */
	public InquiryGrupomuscularRequest()
	{

	}

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 * 
	 * @param grupomuscular the grupomuscular
	 */
	public InquiryGrupomuscularRequest(Grupomuscular grupomuscular)
	{
		addGrupomuscular(grupomuscular);
	}

	/**
	 * Gets the grupomusculars.
	 * 
	 * @return the grupomusculars
	 */
	public List<Grupomuscular> getGrupomusculars()
	{
		return grupomusculars;
	}

	/**
	 * Sets the grupomusculars.
	 * 
	 * @param grupomusculars the new grupomusculars
	 */
	public void setGrupomusculars(List<Grupomuscular> grupomusculars)
	{
		this.grupomusculars = grupomusculars;
	}

	/**
	 * Gets the first grupomuscular.
	 * 
	 * @return the first grupomuscular
	 */
	public Grupomuscular getFirstGrupomuscular()
	{
		if ((getGrupomusculars() != null) && !getGrupomusculars().isEmpty())
		{
			return getGrupomusculars().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the grupomuscular.
	 * 
	 * @param grupomuscular the grupomuscular
	 */
	public void addGrupomuscular(Grupomuscular grupomuscular)
	{
		if (getGrupomusculars() == null)
		{
			setGrupomusculars(new ArrayList<Grupomuscular>());
		}

		getGrupomusculars().add(grupomuscular);
	}

	@Override
	public String toString()
	{
		return "InquiryGrupomuscularRequest [getGrupomusculars()=" + getGrupomusculars() + ", getFirstGrupomuscular()="
				+ getFirstGrupomuscular() + ", toString()="
				+ super.toString() + "]";
	}

}
