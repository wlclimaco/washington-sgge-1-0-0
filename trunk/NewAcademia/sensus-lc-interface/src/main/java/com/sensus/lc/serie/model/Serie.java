package com.sensus.lc.serie.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.exercicio.model.Exercicio;

@SuppressWarnings("serial")
public class Serie extends PrincipalClass
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdserie;
	private Date dtinicio;
	private Date dtfinal;
	private List<Exercicio> exercicio;
	private String meta;

	public List<Exercicio> getExercicio()
	{
		return exercicio;
	}

	public void setExercicio(List<Exercicio> exercicio)
	{
		this.exercicio = exercicio;
	}

	/**
	 * Gets the cdserie.
	 * 
	 * @return the cdserie
	 */
	public Integer getCdserie()
	{
		return cdserie;
	}

	/**
	 * Sets the cdserie.
	 * 
	 * @param cdserie the new cdserie
	 */
	public void setCdserie(Integer cdserie)
	{
		this.cdserie = cdserie;
	}

	/**
	 * Gets the dtinicio.
	 * 
	 * @return the dtinicio
	 */
	public Date getDtinicio()
	{
		return dtinicio;
	}

	/**
	 * Sets the dtinicio.
	 * 
	 * @param dtinicio the new dtinicio
	 */
	public void setDtinicio(Date dtinicio)
	{
		this.dtinicio = dtinicio;
	}

	/**
	 * Gets the dtfinal.
	 * 
	 * @return the dtfinal
	 */
	public Date getDtfinal()
	{
		return dtfinal;
	}

	/**
	 * Sets the dtfinal.
	 * 
	 * @param dtfinal the new dtfinal
	 */
	public void setDtfinal(Date dtfinal)
	{
		this.dtfinal = dtfinal;
	}

	/**
	 * Gets the meta.
	 * 
	 * @return the meta
	 */
	public String getMeta()
	{
		return meta;
	}

	/**
	 * Sets the meta.
	 * 
	 * @param meta the new meta
	 */
	public void setMeta(String meta)
	{
		this.meta = meta;
	}

	/**
	 * Adds the Exercicio
	 * 
	 * @param exercicio the Exercicio
	 */
	public void addExercicio(Exercicio exercicio)
	{
		if (getExercicio() == null)
		{
			setExercicio(new ArrayList<Exercicio>());
		}

		getExercicio().add(exercicio);
	}

	/**
	 * Gets the first exercicio.
	 * 
	 * @return the first exercicio
	 */
	public Exercicio getFirstExercicio()
	{
		if ((getExercicio() != null) && !getExercicio().isEmpty())
		{
			return getExercicio().get(FIRST);
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
		return "Serie[ getCdserie()=" + getCdserie() + ", getDtinicio()=" + getDtinicio() + ", getDtfinal()="
				+ getDtfinal() + ", getCdexerc()=" + getExercicio() + ", getMeta()=" + getMeta()
				+ "]";
	}
}
