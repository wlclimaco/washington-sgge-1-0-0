package com.sensus.lc.dieta.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.dieta.model.Dietaitens;

/**
 * The Class InquiryDietaitensRequest.
 * 
 * @author Washington
 */
public class InquiryDietaitensRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The dietaitenss. */
	private List<Dietaitens> dietaitenss;

	/**
	 * Instantiates a new inquiry dietaitens request.
	 */
	public InquiryDietaitensRequest()
	{

	}

	/**
	 * Instantiates a new inquiry dietaitens request.
	 * 
	 * @param dietaitens the dietaitens
	 */
	public InquiryDietaitensRequest(Dietaitens dietaitens)
	{
		addDietaitens(dietaitens);
	}

	/**
	 * Gets the dietaitenss.
	 * 
	 * @return the dietaitenss
	 */
	public List<Dietaitens> getDietaitenss()
	{
		return dietaitenss;
	}

	/**
	 * Sets the dietaitenss.
	 * 
	 * @param dietaitenss the new dietaitenss
	 */
	public void setDietaitenss(List<Dietaitens> dietaitenss)
	{
		this.dietaitenss = dietaitenss;
	}

	/**
	 * Gets the first dietaitens.
	 * 
	 * @return the first dietaitens
	 */
	public Dietaitens getFirstDietaitens()
	{
		if ((getDietaitenss() != null) && !getDietaitenss().isEmpty())
		{
			return getDietaitenss().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the dietaitens.
	 * 
	 * @param dietaitens the dietaitens
	 */
	public void addDietaitens(Dietaitens dietaitens)
	{
		if (getDietaitenss() == null)
		{
			setDietaitenss(new ArrayList<Dietaitens>());
		}

		getDietaitenss().add(dietaitens);
	}

	@Override
	public String toString()
	{
		return "InquiryDietaitensRequest [getDietaitenss()=" + getDietaitenss() + ", getFirstDietaitens()="
				+ getFirstDietaitens()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
