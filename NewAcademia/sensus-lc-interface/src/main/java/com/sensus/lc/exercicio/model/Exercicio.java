package com.sensus.lc.exercicio.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.grupomuscular.model.Grupomuscular;

@SuppressWarnings("serial")
public class Exercicio extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdexerc;
	private String nmexerc;
	private String dsexerc;
	private String ftexerc;
	private List<Grupomuscular> grupomuscular;

	public List<Grupomuscular> getGrupomuscular()
	{
		return grupomuscular;
	}

	public void setGrupomuscular(List<Grupomuscular> grupomuscular)
	{
		this.grupomuscular = grupomuscular;
	}

	/**
	 * Gets the cdexerc.
	 * 
	 * @return the cdexerc
	 */
	public Integer getCdexerc()
	{
		return cdexerc;
	}

	/**
	 * Sets the cdexerc.
	 * 
	 * @param cdexerc the new cdexerc
	 */
	public void setCdexerc(Integer cdexerc)
	{
		this.cdexerc = cdexerc;
	}

	/**
	 * Gets the nmexerc.
	 * 
	 * @return the nmexerc
	 */
	public String getNmexerc()
	{
		return nmexerc;
	}

	/**
	 * Sets the nmexerc.
	 * 
	 * @param nmexerc the new nmexerc
	 */
	public void setNmexerc(String nmexerc)
	{
		this.nmexerc = nmexerc;
	}

	/**
	 * Gets the dsexerc.
	 * 
	 * @return the dsexerc
	 */
	public String getDsexerc()
	{
		return dsexerc;
	}

	/**
	 * Sets the dsexerc.
	 * 
	 * @param dsexerc the new dsexerc
	 */
	public void setDsexerc(String dsexerc)
	{
		this.dsexerc = dsexerc;
	}

	/**
	 * Gets the ftexerc.
	 * 
	 * @return the ftexerc
	 */
	public String getFtexerc()
	{
		return ftexerc;
	}

	/**
	 * Sets the ftexerc.
	 * 
	 * @param ftexerc the new ftexerc
	 */
	public void setFtexerc(String ftexerc)
	{
		this.ftexerc = ftexerc;
	}

	/**
	 * Adds the Grupomuscular
	 * 
	 * @param grupomuscular the Grupomuscular
	 */
	public void addGrupomuscular(Grupomuscular grupomuscular)
	{
		if (getGrupomuscular() == null)
		{
			setGrupomuscular(new ArrayList<Grupomuscular>());
		}

		getGrupomuscular().add(grupomuscular);
	}

	/**
	 * Gets the first grupomuscular.
	 * 
	 * @return the first grupomuscular
	 */
	public Grupomuscular getFirstGrupomuscular()
	{
		if ((getGrupomuscular() != null) && !getGrupomuscular().isEmpty())
		{
			return getGrupomuscular().get(FIRST);
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
		return "Exercicio[ getCdexerc()=" + getCdexerc() + ", getNmexerc()=" + getNmexerc() + ", getDsexerc()="
				+ getDsexerc() + ", getFtexerc()=" + getFtexerc() + ", getGrmusc()=" +
				getGrupomuscular()
				+ "]";
	}
}
