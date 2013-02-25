package com.sensus.mlc.dicionario.model;

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

	  /** The Cod abas. */
  	public Abas codAbas;

	public Integer getCodTela() {
		return codTela;
	}

	public void setCodTela(Integer codTela) {
		this.codTela = codTela;
	}

	public String getNmTela() {
		return nmTela;
	}

	public void setNmTela(String nmTela) {
		this.nmTela = nmTela;
	}

	public Double getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(Double horizontal) {
		this.horizontal = horizontal;
	}

	public Double getVertical() {
		return vertical;
	}

	public void setVertical(Double vertical) {
		this.vertical = vertical;
	}

	public Abas getCodAbas() {
		return codAbas;
	}

	public void setCodAbas(Abas codAbas) {
		this.codAbas = codAbas;
	}

	@Override
	public String toString() {
		return "Tela [codTela=" + codTela + ", nmTela=" + nmTela
				+ ", horizontal=" + horizontal + ", vertical=" + vertical
				+ ", codAbas=" + codAbas + ", getCodTela()=" + getCodTela()
				+ ", getNmTela()=" + getNmTela() + ", getHorizontal()="
				+ getHorizontal() + ", getVertical()=" + getVertical()
				+ ", getCodAbas()=" + getCodAbas() + "]";
	}


}
