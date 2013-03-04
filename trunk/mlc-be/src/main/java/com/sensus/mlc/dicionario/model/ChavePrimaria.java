package com.sensus.mlc.dicionario.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ChavePrimaria.
 */
public class ChavePrimaria {

	/** The cd cha pri. */
	Integer cdChaPri;

	/** The cd tab. */
	Tabela cdTab;

	/** The cd atrib. */
	List<Atributos> cdAtrib;

	/** The nr seg key. */
	Integer nrSegKey;

	/**
	 * Gets the cd cha pri.
	 *
	 * @return the cd cha pri
	 */
	public Integer getCdChaPri() {
		return cdChaPri;
	}

	/**
	 * Sets the cd cha pri.
	 *
	 * @param cdChaPri the new cd cha pri
	 */
	public void setCdChaPri(Integer cdChaPri) {
		this.cdChaPri = cdChaPri;
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
	 * Gets the cd atrib.
	 *
	 * @return the cd atrib
	 */
	public List<Atributos> getCdAtrib() {
		return cdAtrib;
	}

	/**
	 * Sets the cd atrib.
	 *
	 * @param cdAtrib the new cd atrib
	 */
	public void setCdAtrib(List<Atributos> cdAtrib) {
		this.cdAtrib = cdAtrib;
	}

	/**
	 * Gets the nr seg key.
	 *
	 * @return the nr seg key
	 */
	public Integer getNrSegKey() {
		return nrSegKey;
	}

	/**
	 * Sets the nr seg key.
	 *
	 * @param nrSegKey the new nr seg key
	 */
	public void setNrSegKey(Integer nrSegKey) {
		this.nrSegKey = nrSegKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChavePrimaria [cdChaPri=" + cdChaPri + ", cdTab=" + cdTab
				+ ", cdAtrib=" + cdAtrib + ", nrSegKey=" + nrSegKey
				+ ", getCdChaPri()=" + getCdChaPri() + ", getCdTab()="
				+ getCdTab() + ", getCdAtrib()=" + getCdAtrib()
				+ ", getNrSegKey()=" + getNrSegKey() + "]";
	}

}
