package com.sensus.mlc.dicionario.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ChaveEstrangeira.
 */
public class ChaveEstrangeira {

	/** The cd cha est. */
	Integer cdChaEst;

	/** The cd tab. */
	Tabela  cdTab;

	/** The tp cha est. */
	String  tpChaEst;

	/** The atributos. */
	List<Atributos> atributos;

	/**
	 * Gets the cd cha est.
	 *
	 * @return the cd cha est
	 */
	public Integer getCdChaEst() {
		return cdChaEst;
	}

	/**
	 * Sets the cd cha est.
	 *
	 * @param cdChaEst the new cd cha est
	 */
	public void setCdChaEst(Integer cdChaEst) {
		this.cdChaEst = cdChaEst;
	}

	/**
	 * Gets the cd tab.
	 *
	 * @return the cd tab
	 */
	public Tabela getCdTab() {
		return cdTab;
	}

	/**
	 * Sets the cd tab.
	 *
	 * @param cdTab the new cd tab
	 */
	public void setCdTab(Tabela cdTab) {
		this.cdTab = cdTab;
	}

	/**
	 * Gets the tp cha est.
	 *
	 * @return the tp cha est
	 */
	public String getTpChaEst() {
		return tpChaEst;
	}

	/**
	 * Sets the tp cha est.
	 *
	 * @param tpChaEst the new tp cha est
	 */
	public void setTpChaEst(String tpChaEst) {
		this.tpChaEst = tpChaEst;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChaveEstrangeira [cdChaEst=" + cdChaEst + ", cdTab=" + cdTab
				+ ", tpChaEst=" + tpChaEst + ", atributos=" + atributos
				+ ", getCdChaEst()=" + getCdChaEst() + ", getCdTab()="
				+ getCdTab() + ", getTpChaEst()=" + getTpChaEst()
				+ ", getAtributos()=" + getAtributos() + "]";
	}




}
