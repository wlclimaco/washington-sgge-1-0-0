package com.sensus.mlc.dicionario.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Tela.
 */
public class Tela extends SensusModel{

	  /** The cod tela. */
  	public Integer codTela;

	  /** The Nm tela. */
  	public String nmTela;

	  /** The Horizontal. */
  	public Double horizontal;

	  /** The Vertical. */
  	public Double vertical;

  	/** The atributos. */
	  public List<Atributos> atributos;


	/**
	 * Gets the cod tela.
	 *
	 * @return the cod tela
	 */
	public Integer getCodTela() {
		return codTela;
	}

	/**
	 * Sets the cod tela.
	 *
	 * @param codTela the new cod tela
	 */
	public void setCodTela(Integer codTela) {
		this.codTela = codTela;
	}

	/**
	 * Gets the nm tela.
	 *
	 * @return the nm tela
	 */
	public String getNmTela() {
		return nmTela;
	}

	/**
	 * Sets the nm tela.
	 *
	 * @param nmTela the new nm tela
	 */
	public void setNmTela(String nmTela) {
		this.nmTela = nmTela;
	}

	/**
	 * Gets the horizontal.
	 *
	 * @return the horizontal
	 */
	public Double getHorizontal() {
		return horizontal;
	}

	/**
	 * Sets the horizontal.
	 *
	 * @param horizontal the new horizontal
	 */
	public void setHorizontal(Double horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Gets the vertical.
	 *
	 * @return the vertical
	 */
	public Double getVertical() {
		return vertical;
	}

	/**
	 * Sets the vertical.
	 *
	 * @param vertical the new vertical
	 */
	public void setVertical(Double vertical) {
		this.vertical = vertical;
	}

	/**
	 * Gets the atributos.
	 *
	 * @return the atributos
	 */
	public List<Atributos> getAtributos() {
		return atributos;
	}

	/**
	 * Sets the atributos.
	 *
	 * @param atributos the new atributos
	 */
	public void setAtributos(List<Atributos> atributos) {
		this.atributos = atributos;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Tela [codTela=" + codTela + ", nmTela=" + nmTela
				+ ", horizontal=" + horizontal + ", vertical=" + vertical
				+ ", atributos=" + atributos + ", getCodTela()=" + getCodTela()
				+ ", getNmTela()=" + getNmTela() + ", getHorizontal()="
				+ getHorizontal() + ", getVertical()=" + getVertical()
				+ ", getAtributos()=" + getAtributos() + "]";
	}


}
