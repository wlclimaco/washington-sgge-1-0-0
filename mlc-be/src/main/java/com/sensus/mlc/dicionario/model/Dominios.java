package com.sensus.mlc.dicionario.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Dominios.
 */
public class Dominios {

	/** The cd tab. */
	Tabela cdTab;

	/** The cd atrib. */
	Atributos cdAtrib;

	/** The vr domi. */
	String vrDomi;

	/** The ds domi. */
	String dsDomi;

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
	 * Gets the cd atrib.
	 *
	 * @return the cd atrib
	 */
	public Atributos getCdAtrib() {
		return cdAtrib;
	}

	/**
	 * Sets the cd atrib.
	 *
	 * @param cdAtrib the new cd atrib
	 */
	public void setCdAtrib(Atributos cdAtrib) {
		this.cdAtrib = cdAtrib;
	}

	/**
	 * Gets the vr domi.
	 *
	 * @return the vr domi
	 */
	public String getVrDomi() {
		return vrDomi;
	}

	/**
	 * Sets the vr domi.
	 *
	 * @param vrDomi the new vr domi
	 */
	public void setVrDomi(String vrDomi) {
		this.vrDomi = vrDomi;
	}

	/**
	 * Gets the ds domi.
	 *
	 * @return the ds domi
	 */
	public String getDsDomi() {
		return dsDomi;
	}

	/**
	 * Sets the ds domi.
	 *
	 * @param dsDomi the new ds domi
	 */
	public void setDsDomi(String dsDomi) {
		this.dsDomi = dsDomi;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dominios [cdTab=" + cdTab + ", cdAtrib=" + cdAtrib
				+ ", vrDomi=" + vrDomi + ", dsDomi=" + dsDomi + ", getCdTab()="
				+ getCdTab() + ", getCdAtrib()=" + getCdAtrib()
				+ ", getVrDomi()=" + getVrDomi() + ", getDsDomi()="
				+ getDsDomi() + "]";
	}

}
