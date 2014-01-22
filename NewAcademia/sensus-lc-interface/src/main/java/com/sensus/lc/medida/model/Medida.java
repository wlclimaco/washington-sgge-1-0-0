package com.sensus.lc.medida.model;

import java.sql.Date;

import com.sensus.common.model.SensusModel;

@SuppressWarnings("serial")
public class Medida extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdmed;
	private Float altura;
	private Float peso;
	private Float busto;
	private Float bracoesq;
	private Float bracodir;
	private Float abdomen;
	private Float costa;
	private Float cintura;
	private Float quadril;
	private Float culote;
	private Float coxdir;
	private Float coxesq;
	private Float pantuesq;
	private Float pantudir;
	private Date dtmedida;
	private Float imc;
	private Float bs;

	/**
	 * Gets the cdmed.
	 * 
	 * @return the cdmed
	 */
	public Integer getCdmed()
	{
		return cdmed;
	}

	/**
	 * Sets the cdmed.
	 * 
	 * @param cdmed the new cdmed
	 */
	public void setCdmed(Integer cdmed)
	{
		this.cdmed = cdmed;
	}

	/**
	 * Gets the altura.
	 * 
	 * @return the altura
	 */
	public Float getAltura()
	{
		return altura;
	}

	/**
	 * Sets the altura.
	 * 
	 * @param altura the new altura
	 */
	public void setAltura(Float altura)
	{
		this.altura = altura;
	}

	/**
	 * Gets the peso.
	 * 
	 * @return the peso
	 */
	public Float getPeso()
	{
		return peso;
	}

	/**
	 * Sets the peso.
	 * 
	 * @param peso the new peso
	 */
	public void setPeso(Float peso)
	{
		this.peso = peso;
	}

	/**
	 * Gets the busto.
	 * 
	 * @return the busto
	 */
	public Float getBusto()
	{
		return busto;
	}

	/**
	 * Sets the busto.
	 * 
	 * @param busto the new busto
	 */
	public void setBusto(Float busto)
	{
		this.busto = busto;
	}

	/**
	 * Gets the bracoesq.
	 * 
	 * @return the bracoesq
	 */
	public Float getBracoesq()
	{
		return bracoesq;
	}

	/**
	 * Sets the bracoesq.
	 * 
	 * @param bracoesq the new bracoesq
	 */
	public void setBracoesq(Float bracoesq)
	{
		this.bracoesq = bracoesq;
	}

	/**
	 * Gets the bracodir.
	 * 
	 * @return the bracodir
	 */
	public Float getBracodir()
	{
		return bracodir;
	}

	/**
	 * Sets the bracodir.
	 * 
	 * @param bracodir the new bracodir
	 */
	public void setBracodir(Float bracodir)
	{
		this.bracodir = bracodir;
	}

	/**
	 * Gets the abdomen.
	 * 
	 * @return the abdomen
	 */
	public Float getAbdomen()
	{
		return abdomen;
	}

	/**
	 * Sets the abdomen.
	 * 
	 * @param abdomen the new abdomen
	 */
	public void setAbdomen(Float abdomen)
	{
		this.abdomen = abdomen;
	}

	/**
	 * Gets the costa.
	 * 
	 * @return the costa
	 */
	public Float getCosta()
	{
		return costa;
	}

	/**
	 * Sets the costa.
	 * 
	 * @param costa the new costa
	 */
	public void setCosta(Float costa)
	{
		this.costa = costa;
	}

	/**
	 * Gets the cintura.
	 * 
	 * @return the cintura
	 */
	public Float getCintura()
	{
		return cintura;
	}

	/**
	 * Sets the cintura.
	 * 
	 * @param cintura the new cintura
	 */
	public void setCintura(Float cintura)
	{
		this.cintura = cintura;
	}

	/**
	 * Gets the quadril.
	 * 
	 * @return the quadril
	 */
	public Float getQuadril()
	{
		return quadril;
	}

	/**
	 * Sets the quadril.
	 * 
	 * @param quadril the new quadril
	 */
	public void setQuadril(Float quadril)
	{
		this.quadril = quadril;
	}

	/**
	 * Gets the culote.
	 * 
	 * @return the culote
	 */
	public Float getCulote()
	{
		return culote;
	}

	/**
	 * Sets the culote.
	 * 
	 * @param culote the new culote
	 */
	public void setCulote(Float culote)
	{
		this.culote = culote;
	}

	/**
	 * Gets the coxdir.
	 * 
	 * @return the coxdir
	 */
	public Float getCoxdir()
	{
		return coxdir;
	}

	/**
	 * Sets the coxdir.
	 * 
	 * @param coxdir the new coxdir
	 */
	public void setCoxdir(Float coxdir)
	{
		this.coxdir = coxdir;
	}

	/**
	 * Gets the coxesq.
	 * 
	 * @return the coxesq
	 */
	public Float getCoxesq()
	{
		return coxesq;
	}

	/**
	 * Sets the coxesq.
	 * 
	 * @param coxesq the new coxesq
	 */
	public void setCoxesq(Float coxesq)
	{
		this.coxesq = coxesq;
	}

	/**
	 * Gets the pantuesq.
	 * 
	 * @return the pantuesq
	 */
	public Float getPantuesq()
	{
		return pantuesq;
	}

	/**
	 * Sets the pantuesq.
	 * 
	 * @param pantuesq the new pantuesq
	 */
	public void setPantuesq(Float pantuesq)
	{
		this.pantuesq = pantuesq;
	}

	/**
	 * Gets the pantudir.
	 * 
	 * @return the pantudir
	 */
	public Float getPantudir()
	{
		return pantudir;
	}

	/**
	 * Sets the pantudir.
	 * 
	 * @param pantudir the new pantudir
	 */
	public void setPantudir(Float pantudir)
	{
		this.pantudir = pantudir;
	}

	/**
	 * Gets the dtmedida.
	 * 
	 * @return the dtmedida
	 */
	public Date getDtmedida()
	{
		return dtmedida;
	}

	/**
	 * Sets the dtmedida.
	 * 
	 * @param dtmedida the new dtmedida
	 */
	public void setDtmedida(Date dtmedida)
	{
		this.dtmedida = dtmedida;
	}

	/**
	 * Gets the imc.
	 * 
	 * @return the imc
	 */
	public Float getImc()
	{
		return imc;
	}

	/**
	 * Sets the imc.
	 * 
	 * @param imc the new imc
	 */
	public void setImc(Float imc)
	{
		this.imc = imc;
	}

	/**
	 * Gets the bs.
	 * 
	 * @return the bs
	 */
	public Float getBs()
	{
		return bs;
	}

	/**
	 * Sets the bs.
	 * 
	 * @param bs the new bs
	 */
	public void setBs(Float bs)
	{
		this.bs = bs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Medida[ getCdmed()=" + getCdmed() + ", getAltura()=" + getAltura() + ", getPeso()=" + getPeso()
				+ ", getBusto()=" + getBusto() + ", getBracoesq()=" + getBracoesq() + ",getBracodir()=" + getBracodir()
				+ ", getAbdomen()=" + getAbdomen() + ", getCosta()=" + getCosta() + ", getCintura()=" + getCintura()
				+ ", getQuadril()=" + getQuadril() + ", getCulote()="
				+ getCulote() + ", getCoxdir()=" + getCoxdir() + ", getCoxesq()=" + getCoxesq() + ", getPantuesq()="
				+ getPantuesq() + ", getPantudir()=" + getPantudir() + ", getDtmedida()=" +
				getDtmedida() + ", getImc()=" + getImc() + ", getBs()=" + getBs()
				+ "]";
	}
}
