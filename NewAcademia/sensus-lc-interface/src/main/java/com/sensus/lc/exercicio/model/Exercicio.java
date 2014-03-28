package com.sensus.lc.exercicio.model;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.grupomuscular.model.Grupomuscular;

/**
 * The Class Exercicio.
 */
@SuppressWarnings("serial")
public class Exercicio extends PrincipalClass
{

	/** The cdexerc. */
	private Integer cdexerc;

	/** The nmexerc. */
	private String nmexerc;

	/** The dsexerc. */
	private String dsexerc;

	/** The grupomuscular. */
	private Grupomuscular grupomuscular;

	/**
	 * Instantiates a new exercicio.
	 */
	public Exercicio()
	{
		super();
	}

	/**
	 * Instantiates a new exercicio.
	 * 
	 * @param cdexerc the cdexerc
	 */
	public Exercicio(Integer cdexerc)
	{
		super();
		this.cdexerc = cdexerc;
	}

	/**
	 * Gets the grupomuscular.
	 * 
	 * @return the grupomuscular
	 */
	public Grupomuscular getGrupomuscular()
	{
		return grupomuscular;
	}

	/**
	 * Sets the grupomuscular.
	 * 
	 * @param grupomuscular the new grupomuscular
	 */
	public void setGrupomuscular(Grupomuscular grupomuscular)
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

	@Override
	public String toString()
	{
		return "Exercicio [getGrupomuscular()=" + getGrupomuscular() + ", getCdexerc()="
				+ getCdexerc() + ", getNmexerc()=" + getNmexerc() + ", getDsexerc()=" + getDsexerc() + ", toString()="
				+ super.toString() + "]";
	}

}
