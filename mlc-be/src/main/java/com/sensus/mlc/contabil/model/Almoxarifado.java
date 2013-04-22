package com.sensus.mlc.almoxarifado.model;

import java.sql.Blob;
import java.sql.Date;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * * The Model Object Action.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class Almoxarifado extends SensusModel
{

	/** The codfilial. */
	private Integer codfilial;

	/** The numemp. */
	private Integer codAlmoxarifado;

	/** The complemp. */
	private String descricao;

	/**
	 * Gets the codfilial.
	 *
	 * @return the codfilial
	 */
	public Integer getCodfilial() {
		return codfilial;
	}

	/**
	 * Sets the codfilial.
	 *
	 * @param codfilial the new codfilial
	 */
	public void setCodfilial(Integer codfilial) {
		this.codfilial = codfilial;
	}

	/**
	 * Gets the cod almoxarifado.
	 *
	 * @return the cod almoxarifado
	 */
	public Integer getCodAlmoxarifado() {
		return codAlmoxarifado;
	}

	/**
	 * Sets the cod almoxarifado.
	 *
	 * @param codAlmoxarifado the new cod almoxarifado
	 */
	public void setCodAlmoxarifado(Integer codAlmoxarifado) {
		this.codAlmoxarifado = codAlmoxarifado;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao the new descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Instantiates a new almoxarifado.
	 *
	 * @param codfilial the codfilial
	 * @param codAlmoxarifado the cod almoxarifado
	 * @param descricao the descricao
	 */
	public Almoxarifado(Integer codfilial, Integer codAlmoxarifado,
			String descricao) {
		super();
		this.codfilial = codfilial;
		this.codAlmoxarifado = codAlmoxarifado;
		this.descricao = descricao;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Almoxarifado [codfilial=" + codfilial + ", codAlmoxarifado="
				+ codAlmoxarifado + ", descricao=" + descricao
				+ ", getCodfilial()=" + getCodfilial()
				+ ", getCodAlmoxarifado()=" + getCodAlmoxarifado()
				+ ", getDescricao()=" + getDescricao() + "]";
	}

}
