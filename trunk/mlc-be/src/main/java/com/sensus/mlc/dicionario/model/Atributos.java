package com.sensus.mlc.dicionario.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.user.model.Alterinse;

// TODO: Auto-generated Javadoc
/**
 * The Class Atributos.
 */
public class Atributos extends SensusModel
{

    /** The nmtabela. */
    private Tabela nmtabela;

    /** The nmatribu. */
    private String nmatribu;

    /** The dsatribu. */
    private String dsatribu;

    /** The tpatribu. */
    private String tpatribu;

    /** The dsmascar. */
    private String dsmascar;

    /** The dshelp. */
    private String dshelp;

    /** The tmatribu. */
    private Integer tmatribu;

    /** The nrseqatr. */
    private Integer nrseqatr;

    /** The listinsalt. */
    private List<Alterinse> listinsalt;



	/**
	 * Instantiates a new atributos.
	 *
	 * @param nmtabela the nmtabela
	 * @param nmatribu the nmatribu
	 * @param dsatribu the dsatribu
	 * @param tpatribu the tpatribu
	 * @param dsmascar the dsmascar
	 * @param dshelp the dshelp
	 * @param tmatribu the tmatribu
	 * @param nrseqatr the nrseqatr
	 * @param listinsalt the listinsalt
	 */
	public Atributos(Tabela nmtabela, String nmatribu, String dsatribu,
			String tpatribu, String dsmascar, String dshelp, Integer tmatribu,
			Integer nrseqatr, List<Alterinse> listinsalt) {
		super();
		this.nmtabela = nmtabela;
		this.nmatribu = nmatribu;
		this.dsatribu = dsatribu;
		this.tpatribu = tpatribu;
		this.dsmascar = dsmascar;
		this.dshelp = dshelp;
		this.tmatribu = tmatribu;
		this.nrseqatr = nrseqatr;
		this.listinsalt = listinsalt;
	}

	/**
	 * Instantiates a new atributos.
	 *
	 * @param nmtabela the nmtabela
	 * @param nmatribu the nmatribu
	 * @param listinsalt the listinsalt
	 */
	public Atributos(Tabela nmtabela, String nmatribu,
			List<Alterinse> listinsalt) {
		super();
		this.nmtabela = nmtabela;
		this.nmatribu = nmatribu;
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
	public String getNmatribu() {
		return nmatribu;
	}

	/**
	 * Sets the nmatribu.
	 *
	 * @param nmatribu the new nmatribu
	 */
	public void setNmatribu(String nmatribu) {
		this.nmatribu = nmatribu;
	}

	/**
	 * Gets the dsatribu.
	 *
	 * @return the dsatribu
	 */
	public String getDsatribu() {
		return dsatribu;
	}

	/**
	 * Sets the dsatribu.
	 *
	 * @param dsatribu the new dsatribu
	 */
	public void setDsatribu(String dsatribu) {
		this.dsatribu = dsatribu;
	}

	/**
	 * Gets the tpatribu.
	 *
	 * @return the tpatribu
	 */
	public String getTpatribu() {
		return tpatribu;
	}

	/**
	 * Sets the tpatribu.
	 *
	 * @param tpatribu the new tpatribu
	 */
	public void setTpatribu(String tpatribu) {
		this.tpatribu = tpatribu;
	}

	/**
	 * Gets the dsmascar.
	 *
	 * @return the dsmascar
	 */
	public String getDsmascar() {
		return dsmascar;
	}

	/**
	 * Sets the dsmascar.
	 *
	 * @param dsmascar the new dsmascar
	 */
	public void setDsmascar(String dsmascar) {
		this.dsmascar = dsmascar;
	}

	/**
	 * Gets the dshelp.
	 *
	 * @return the dshelp
	 */
	public String getDshelp() {
		return dshelp;
	}

	/**
	 * Sets the dshelp.
	 *
	 * @param dshelp the new dshelp
	 */
	public void setDshelp(String dshelp) {
		this.dshelp = dshelp;
	}

	/**
	 * Gets the tmatribu.
	 *
	 * @return the tmatribu
	 */
	public Integer getTmatribu() {
		return tmatribu;
	}

	/**
	 * Sets the tmatribu.
	 *
	 * @param tmatribu the new tmatribu
	 */
	public void setTmatribu(Integer tmatribu) {
		this.tmatribu = tmatribu;
	}

	/**
	 * Gets the nrseqatr.
	 *
	 * @return the nrseqatr
	 */
	public Integer getNrseqatr() {
		return nrseqatr;
	}

	/**
	 * Sets the nrseqatr.
	 *
	 * @param nrseqatr the new nrseqatr
	 */
	public void setNrseqatr(Integer nrseqatr) {
		this.nrseqatr = nrseqatr;
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
		return "Atributos [nmtabela=" + nmtabela + ", nmatribu=" + nmatribu
				+ ", dsatribu=" + dsatribu + ", tpatribu=" + tpatribu
				+ ", dsmascar=" + dsmascar + ", dshelp=" + dshelp
				+ ", tmatribu=" + tmatribu + ", nrseqatr=" + nrseqatr
				+ ", listinsalt=" + listinsalt + ", getNmtabela()="
				+ getNmtabela() + ", getNmatribu()=" + getNmatribu()
				+ ", getDsatribu()=" + getDsatribu() + ", getTpatribu()="
				+ getTpatribu() + ", getDsmascar()=" + getDsmascar()
				+ ", getDshelp()=" + getDshelp() + ", getTmatribu()="
				+ getTmatribu() + ", getNrseqatr()=" + getNrseqatr()
				+ ", getListinsalt()=" + getListinsalt() + "]";
	}


}

