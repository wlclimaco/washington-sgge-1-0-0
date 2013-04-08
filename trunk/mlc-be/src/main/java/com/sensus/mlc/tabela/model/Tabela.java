package com.sensus.mlc.tabela.model;

import com.sensus.common.model.SensusModel;


// TODO: Auto-generated Javadoc
/**
 * * The Model Object Tag.
 *
 * @author - Washington
 */
@SuppressWarnings("serial")
public class Tabela extends SensusModel
{

    /** The nmtabela. */
    private String nmtabela;

    /** The dstabela. */
    private String dstabela;


	/**
	 * Instantiates a new tabela.
	 *
	 * @param nmtabela the nmtabela
	 * @param dstabela the dstabela
	 */
	public Tabela(String nmtabela, String dstabela) {
		super();
		this.nmtabela = nmtabela;
		this.dstabela = dstabela;
	}

	/**
	 * Gets the nmtabela.
	 *
	 * @return the nmtabela
	 */
	public String getNmtabela() {
		return nmtabela;
	}

	/**
	 * Sets the nmtabela.
	 *
	 * @param nmtabela the new nmtabela
	 */
	public void setNmtabela(String nmtabela) {
		this.nmtabela = nmtabela;
	}

	/**
	 * Gets the dstabela.
	 *
	 * @return the dstabela
	 */
	public String getDstabela() {
		return dstabela;
	}

	/**
	 * Sets the dstabela.
	 *
	 * @param dstabela the new dstabela
	 */
	public void setDstabela(String dstabela) {
		this.dstabela = dstabela;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Tabela [nmtabela=" + nmtabela + ", dstabela=" + dstabela
				+ ", getNmtabela()=" + getNmtabela() + ", getDstabela()="
				+ getDstabela() + "]";
	}

}
