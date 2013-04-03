package com.sensus.mlc.dicionario.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.user.model.Alterinse;

// TODO: Auto-generated Javadoc
/**
 * The Class Tabelad001.
 */
public class Tabela extends SensusModel
{

    /** The nmtabela. */
    private String nmtabela;

    /** The dstabela. */
    private String dstabela;

    /** The listinsalt. */
    private List<Alterinse> listinsalt;



	/**
	 * Instantiates a new tabelad001.
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

	/**
	 * Gets the listinsalt.
	 *
	 * @return the listinsalt
	 */
	public List<ALTERINSE> getListinsalt() {
		return listinsalt;
	}

	/**
	 * Sets the listinsalt.
	 *
	 * @param listinsalt the new listinsalt
	 */
	public void setListinsalt(List<ALTERINSE> listinsalt) {
		this.listinsalt = listinsalt;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Tabelad001 [nmtabela=" + nmtabela + ", dstabela=" + dstabela
				+ ", getNmtabela()=" + getNmtabela() + ", getDstabela()="
				+ getDstabela() + "]";
	}

}
