package com.sensus.mlc.dicionario.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.user.model.Alterinse;

// TODO: Auto-generated Javadoc
/**
 * The Class Chaveprimaria.
 */
public class Chaveprimaria extends SensusModel
{

    /** The nmtabela. */
    private Tabela nmtabela;

    /** The nmatribu. */
    private Atributos nmatribu;

    /** The nrseqkey. */
    private Integer nrseqkey;

    /** The listinsalt. */
    private List<Alterinse> listinsalt;



	/**
	 * Instantiates a new chaveprimaria.
	 *
	 * @param nmtabela the nmtabela
	 * @param nmatribu the nmatribu
	 * @param nrseqkey the nrseqkey
	 * @param listinsalt the listinsalt
	 */
	public Chaveprimaria(Tabela nmtabela, Atributos nmatribu, Integer nrseqkey,
			List<Alterinse> listinsalt) {
		super();
		this.nmtabela = nmtabela;
		this.nmatribu = nmatribu;
		this.nrseqkey = nrseqkey;
		this.listinsalt = listinsalt;
	}

	/**
	 * Gets the nmtabela.
	 *
	 * @return the nmtabela
	 */
	public Tabela getNmtabela() {
		return nmtabela;
	}

	/**
	 * Sets the nmtabela.
	 *
	 * @param nmtabela the new nmtabela
	 */
	public void setNmtabela(Tabela nmtabela) {
		this.nmtabela = nmtabela;
	}

	/**
	 * Gets the nmatribu.
	 *
	 * @return the nmatribu
	 */
	public Atributos getNmatribu() {
		return nmatribu;
	}

	/**
	 * Sets the nmatribu.
	 *
	 * @param nmatribu the new nmatribu
	 */
	public void setNmatribu(Atributos nmatribu) {
		this.nmatribu = nmatribu;
	}

	/**
	 * Gets the nrseqkey.
	 *
	 * @return the nrseqkey
	 */
	public Integer getNrseqkey() {
		return nrseqkey;
	}

	/**
	 * Sets the nrseqkey.
	 *
	 * @param nrseqkey the new nrseqkey
	 */
	public void setNrseqkey(Integer nrseqkey) {
		this.nrseqkey = nrseqkey;
	}

	/**
	 * Gets the listinsalt.
	 *
	 * @return the listinsalt
	 */
	public List<Alterinse> getListinsalt() {
		return listinsalt;
	}

	/**
	 * Sets the listinsalt.
	 *
	 * @param listinsalt the new listinsalt
	 */
	public void setListinsalt(List<Alterinse> listinsalt) {
		this.listinsalt = listinsalt;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Chaveprimaria [nmtabela=" + nmtabela + ", nmatribu=" + nmatribu
				+ ", nrseqkey=" + nrseqkey + ", listinsalt=" + listinsalt
				+ ", getNmtabela()=" + getNmtabela() + ", getNmatribu()="
				+ getNmatribu() + ", getNrseqkey()=" + getNrseqkey()
				+ ", getListinsalt()=" + getListinsalt() + "]";
	}


}
