package com.sensus.lc.dieta.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.dieta.model.Dieta;

/**
 * The Class InquiryDietaRequest.
 * 
 * @author Washington
 */
public class InquiryDietaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The dietas. */
	private List<Dieta> dietas;

	/**
	 * Instantiates a new inquiry dieta request.
	 */
	public InquiryDietaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry dieta request.
	 * 
	 * @param dieta the dieta
	 */
	public InquiryDietaRequest(Dieta dieta)
	{
		addDieta(dieta);
	}

	/**
	 * Gets the dietas.
	 * 
	 * @return the dietas
	 */
	public List<Dieta> getDietas()
	{
		return dietas;
	}

	/**
	 * Sets the dietas.
	 * 
	 * @param dietas the new dietas
	 */
	public void setDietas(List<Dieta> dietas)
	{
		this.dietas = dietas;
	}

	/**
	 * Gets the first dieta.
	 * 
	 * @return the first dieta
	 */
	public Dieta getFirstDieta()
	{
		if ((getDietas() != null) && !getDietas().isEmpty())
		{
			return getDietas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the dieta.
	 * 
	 * @param dieta the dieta
	 */
	public void addDieta(Dieta dieta)
	{
		if (getDietas() == null)
		{
			setDietas(new ArrayList<Dieta>());
		}

		getDietas().add(dieta);
	}

	@Override
	public String toString()
	{
		return "InquiryDietaRequest [ getDietas()=" + getDietas() + ", getFirstDieta()=" + getFirstDieta()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
