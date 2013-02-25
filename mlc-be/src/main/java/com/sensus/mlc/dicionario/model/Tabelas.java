package com.sensus.mlc.dicionario.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Tabelas.
 */
public class Tabelas {

	  /** The cod tab. */
  	public Integer codTab;

	  /** The cod atrib. */
  	public Integer codAtrib;

	  /** The primary key. */
  	public Boolean primaryKey;

	  /** The forenkey. */
  	public Boolean forenkey;

	  /** The cod tabs. */
  	public Tabelas codTabs;

	  /** The descricao. */
  	public String descricao;

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
	 * Gets the cod atrib.
	 *
	 * @return the cod atrib
	 */
	public Integer getCodAtrib() {
		return codAtrib;
	}

	/**
	 * Sets the cod atrib.
	 *
	 * @param codAtrib the new cod atrib
	 */
	public void setCodAtrib(Integer codAtrib) {
		this.codAtrib = codAtrib;
	}

	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey the new primary key
	 */
	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the forenkey.
	 *
	 * @return the forenkey
	 */
	public Boolean getForenkey() {
		return forenkey;
	}

	/**
	 * Sets the forenkey.
	 *
	 * @param forenkey the new forenkey
	 */
	public void setForenkey(Boolean forenkey) {
		this.forenkey = forenkey;
	}

	/**
	 * Gets the cod tabs.
	 *
	 * @return the cod tabs
	 */
	public Tabelas getCodTabs() {
		return codTabs;
	}

	/**
	 * Sets the cod tabs.
	 *
	 * @param codTabs the new cod tabs
	 */
	public void setCodTabs(Tabelas codTabs) {
		this.codTabs = codTabs;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tabelas [codTab=" + codTab + ", codAtrib=" + codAtrib
				+ ", primaryKey=" + primaryKey + ", forenkey=" + forenkey
				+ ", codTabs=" + codTabs + ", descricao=" + descricao
				+ ", getCodTab()=" + getCodTab() + ", getCodAtrib()="
				+ getCodAtrib() + ", getPrimaryKey()=" + getPrimaryKey()
				+ ", getForenkey()=" + getForenkey() + ", getCodTabs()="
				+ getCodTabs() + ", getDescricao()=" + getDescricao() + "]";
	}

}
