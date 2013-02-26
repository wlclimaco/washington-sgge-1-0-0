package com.sensus.mlc.dicionario.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Tabela.
 */
public class Tabela {

	/** The cod tab. */
	Integer codTab;

	/** The nome tab. */
	String  nomeTab;

	/** The Ds tab. */
	String  DsTab;

	/**
	 * Gets the cod tab.
	 *
	 * @return the cod tab
	 */
	public Integer getCodTab() {
		return codTab;
	}

	/**
	 * Sets the cod tab.
	 *
	 * @param codTab the new cod tab
	 */
	public void setCodTab(Integer codTab) {
		this.codTab = codTab;
	}

	/**
	 * Gets the nome tab.
	 *
	 * @return the nome tab
	 */
	public String getNomeTab() {
		return nomeTab;
	}

	/**
	 * Sets the nome tab.
	 *
	 * @param nomeTab the new nome tab
	 */
	public void setNomeTab(String nomeTab) {
		this.nomeTab = nomeTab;
	}

	/**
	 * Gets the ds tab.
	 *
	 * @return the ds tab
	 */
	public String getDsTab() {
		return DsTab;
	}

	/**
	 * Sets the ds tab.
	 *
	 * @param dsTab the new ds tab
	 */
	public void setDsTab(String dsTab) {
		DsTab = dsTab;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tabela [codTab=" + codTab + ", nomeTab=" + nomeTab + ", DsTab="
				+ DsTab + ", getCodTab()=" + getCodTab() + ", getNomeTab()="
				+ getNomeTab() + ", getDsTab()=" + getDsTab() + "]";
	}
}
