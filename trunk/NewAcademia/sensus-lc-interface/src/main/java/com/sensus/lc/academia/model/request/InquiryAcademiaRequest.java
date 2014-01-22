package com.sensus.lc.academia.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.academia.model.Academia;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;

/**
 * The Class InquiryAcademiaRequest.
 * 
 * @author Washington
 */
public class InquiryAcademiaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The academias. */
	private List<Academia> academias;

	/**
	 * Instantiates a new inquiry academia request.
	 */
	public InquiryAcademiaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry academia request.
	 * 
	 * @param academia the academia
	 */
	public InquiryAcademiaRequest(Academia academia)
	{
		addAcademia(academia);
	}

	/**
	 * Gets the academias.
	 * 
	 * @return the academias
	 */
	public List<Academia> getAcademias()
	{
		return academias;
	}

	/**
	 * Sets the academias.
	 * 
	 * @param academias the new academias
	 */
	public void setAcademias(List<Academia> academias)
	{
		this.academias = academias;
	}

	/**
	 * Gets the first academia.
	 * 
	 * @return the first academia
	 */
	public Academia getFirstAcademia()
	{
		if ((getAcademias() != null) && !getAcademias().isEmpty())
		{
			return getAcademias().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the academia.
	 * 
	 * @param academia the academia
	 */
	public void addAcademia(Academia academia)
	{
		if (getAcademias() == null)
		{
			setAcademias(new ArrayList<Academia>());
		}

		getAcademias().add(academia);
	}

	@Override
	public String toString()
	{
		return "InquiryAcademiaRequest [getAcademias()=" + getAcademias() + ", getFirstAcademia()="
				+ getFirstAcademia() + "]";
	}

}
