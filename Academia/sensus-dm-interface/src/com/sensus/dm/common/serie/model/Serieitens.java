package com.sensus.dm.common.serie.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.exercicio.model.Exercicio;

@SuppressWarnings("serial")
public class Serieitens extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdserie;
	private List<Exercicio> exercicios;
	private Integer qtreped;
	private Float psinicial;
	private Float psfinal;
	private Date dttrein;

	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios)
	{
		this.exercicios = exercicios;
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
	 * Gets the qtreped.
	 * 
	 * @return the qtreped
	 */
	public Integer getQtreped()
	{
		return qtreped;
	}

	/**
	 * Sets the qtreped.
	 * 
	 * @param qtreped the new qtreped
	 */
	public void setQtreped(Integer qtreped)
	{
		this.qtreped = qtreped;
	}

	/**
	 * Gets the psinicial.
	 * 
	 * @return the psinicial
	 */
	public Float getPsinicial()
	{
		return psinicial;
	}

	/**
	 * Sets the psinicial.
	 * 
	 * @param psinicial the new psinicial
	 */
	public void setPsinicial(Float psinicial)
	{
		this.psinicial = psinicial;
	}

	/**
	 * Gets the psfinal.
	 * 
	 * @return the psfinal
	 */
	public Float getPsfinal()
	{
		return psfinal;
	}

	/**
	 * Sets the psfinal.
	 * 
	 * @param psfinal the new psfinal
	 */
	public void setPsfinal(Float psfinal)
	{
		this.psfinal = psfinal;
	}

	/**
	 * Gets the dttrein.
	 * 
	 * @return the dttrein
	 */
	public Date getDttrein()
	{
		return dttrein;
	}

	/**
	 * Sets the dttrein.
	 * 
	 * @param dttrein the new dttrein
	 */
	public void setDttrein(Date dttrein)
	{
		this.dttrein = dttrein;
	}

	/**
	 * Adds the Exercicio
	 * 
	 * @param exercicio the Exercicio
	 */
	public void addExercicio(Exercicio exercicio)
	{
		if (getExercicios() == null)
		{
			setExercicios(new ArrayList<Exercicio>());
		}

		getExercicios().add(exercicio);
	}

	/**
	 * Gets the first exercicio.
	 * 
	 * @return the first exercicio
	 */
	public Exercicio getFirstExercicio()
	{
		if ((getExercicios() != null) && !getExercicios().isEmpty())
		{
			return getExercicios().get(FIRST);
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
		return "Serieitens[ getCdserie()=" + getCdserie() + ", getCdexerc()=" + getExercicios() + ", getQtreped()="
				+ getQtreped() + ", getPsinicial()=" + getPsinicial() + ", getPsfinal()=" +
				getPsfinal() + ", getDttrein()=" + getDttrein() + ", getDttrein()=" + getDttrein()
				+ "]";
	}
}
