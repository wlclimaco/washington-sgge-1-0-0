package com.sensus.mlc.dicionario.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.user.model.Alterinse;

// TODO: Auto-generated Javadoc
/**
 * The Class Chaveestrangeira.
 */
public class Chaveestrangeira extends SensusModel
{

    /** The nmchaest. */
    private Atributos nmchaest;

    /** The nmtabela. */
    private Tabela nmtabela;

    /** The nmtabest. */
    private Tabela nmtabest;

    /** The tpchaest. */
    private String tpchaest;

    /** The listinsalt. */
    private List<Alterinse> listinsalt;

	/**
	 * Instantiates a new chaveestrangeira.
	 *
	 * @param nmchaest the nmchaest
	 * @param nmtabela the nmtabela
	 * @param nmtabest the nmtabest
	 * @param tpchaest the tpchaest
	 * @param listinsalt the listinsalt
	 */
	public Chaveestrangeira(Atributos nmchaest, Tabela nmtabela,
			Tabela nmtabest, String tpchaest, List<Alterinse> listinsalt) {
		super();
		this.nmchaest = nmchaest;
		this.nmtabela = nmtabela;
		this.nmtabest = nmtabest;
		this.tpchaest = tpchaest;
		this.listinsalt = listinsalt;
	}

	/**
	 * Gets the nmchaest.
	 *
	 * @return the nmchaest
	 */
	public Atributos getNmchaest() {
		return nmchaest;
	}

	/**
	 * Sets the nmchaest.
	 *
	 * @param nmchaest the new nmchaest
	 */
	public void setNmchaest(Atributos nmchaest) {
		this.nmchaest = nmchaest;
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
	 * Gets the nmtabest.
	 *
	 * @return the nmtabest
	 */
	public Tabela getNmtabest() {
		return nmtabest;
	}

	/**
	 * Sets the nmtabest.
	 *
	 * @param nmtabest the new nmtabest
	 */
	public void setNmtabest(Tabela nmtabest) {
		this.nmtabest = nmtabest;
	}

	/**
	 * Gets the tpchaest.
	 *
	 * @return the tpchaest
	 */
	public String getTpchaest() {
		return tpchaest;
	}

	/**
	 * Sets the tpchaest.
	 *
	 * @param tpchaest the new tpchaest
	 */
	public void setTpchaest(String tpchaest) {
		this.tpchaest = tpchaest;
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
		return "Chaveestrangeira [nmchaest=" + nmchaest + ", nmtabela="
				+ nmtabela + ", nmtabest=" + nmtabest + ", tpchaest="
				+ tpchaest + ", listinsalt=" + listinsalt + ", getNmchaest()="
				+ getNmchaest() + ", getNmtabela()=" + getNmtabela()
				+ ", getNmtabest()=" + getNmtabest() + ", getTpchaest()="
				+ getTpchaest() + ", getListinsalt()=" + getListinsalt() + "]";
	}


}
