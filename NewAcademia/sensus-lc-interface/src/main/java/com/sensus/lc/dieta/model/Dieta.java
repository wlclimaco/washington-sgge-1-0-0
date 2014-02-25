package com.sensus.lc.dieta.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.PrincipalClass;

@SuppressWarnings("serial")
public class Dieta extends PrincipalClass
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cddiet;
	private Date dtinic;
	private Date dtfina;
	private String dsobj;
	private List<Dietaitens> dieta;

	/**
	 * Gets the cddiet.
	 * 
	 * @return the cddiet
	 */
	public Integer getCddiet()
	{
		return cddiet;
	}

	/**
	 * Sets the cddiet.
	 * 
	 * @param cddiet the new cddiet
	 */
	public void setCddiet(Integer cddiet)
	{
		this.cddiet = cddiet;
	}

	/**
	 * Gets the dtinic.
	 * 
	 * @return the dtinic
	 */
	public Date getDtinic()
	{
		return dtinic;
	}

	/**
	 * Sets the dtinic.
	 * 
	 * @param dtinic the new dtinic
	 */
	public void setDtinic(Date dtinic)
	{
		this.dtinic = dtinic;
	}

	/**
	 * Gets the dtfina.
	 * 
	 * @return the dtfina
	 */
	public Date getDtfina()
	{
		return dtfina;
	}

	/**
	 * Sets the dtfina.
	 * 
	 * @param dtfina the new dtfina
	 */
	public void setDtfina(Date dtfina)
	{
		this.dtfina = dtfina;
	}

	/**
	 * Gets the dsobj.
	 * 
	 * @return the dsobj
	 */
	public String getDsobj()
	{
		return dsobj;
	}

	/**
	 * Sets the dsobj.
	 * 
	 * @param dsobj the new dsobj
	 */
	public void setDsobj(String dsobj)
	{
		this.dsobj = dsobj;
	}

	/**
	 * Gets the dieta.
	 * 
	 * @return the dieta
	 */
	public List<Dietaitens> getDieta()
	{
		return dieta;
	}

	/**
	 * Sets the dieta.
	 * 
	 * @param dieta the new dieta
	 */
	public void setDieta(List<Dietaitens> dieta)
	{
		this.dieta = dieta;
	}

	/**
	 * Adds the Dietaitens
	 * 
	 * @param dietaitens the Dietaitens
	 */
	public void addDieta(Dietaitens dieta)
	{
		if (getDieta() == null)
		{
			setDieta(new ArrayList<Dietaitens>());
		}

		getDieta().add(dieta);
	}

	/**
	 * Gets the first dietaitens.
	 * 
	 * @return the first dietaitens
	 */
	public Dietaitens getFirstDietaitens()
	{
		if ((getDieta() != null) && !getDieta().isEmpty())
		{
			return getDieta().get(FIRST);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Dieta[ getCddiet()=" + getCddiet() + ", getDtinic()=" + getDtinic() + ", getDtfina()=" + getDtfina()
				+ ", getDsobj()=" + getDsobj() + ", getDieta()=" + getDieta()
				+ "]";
	}
}
