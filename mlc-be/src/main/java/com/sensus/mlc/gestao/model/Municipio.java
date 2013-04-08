package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Municipio.
 */
public class Municipio extends SensusModel
{

    /** The codmunic. */
    private String codmunic;

    /** The siglauf. */
    private String siglauf;

    /** The codpais. */
    private Pais codpais;

    /** The nomemunic. */
    private String nomemunic;

    /** The dddmunic. */
    private String dddmunic;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codmunic.
	 *
	 * @return the codmunic
	 */
	public String getCodmunic() {
		return codmunic;
	}

	/**
	 * Sets the codmunic.
	 *
	 * @param codmunic the new codmunic
	 */
	public void setCodmunic(String codmunic) {
		this.codmunic = codmunic;
	}

	/**
	 * Gets the siglauf.
	 *
	 * @return the siglauf
	 */
	public String getSiglauf() {
		return siglauf;
	}

	/**
	 * Sets the siglauf.
	 *
	 * @param siglauf the new siglauf
	 */
	public void setSiglauf(String siglauf) {
		this.siglauf = siglauf;
	}

	/**
	 * Gets the codpais.
	 *
	 * @return the codpais
	 */
	public Pais getCodpais() {
		return codpais;
	}

	/**
	 * Sets the codpais.
	 *
	 * @param codpais the new codpais
	 */
	public void setCodpais(Pais codpais) {
		this.codpais = codpais;
	}

	/**
	 * Gets the nomemunic.
	 *
	 * @return the nomemunic
	 */
	public String getNomemunic() {
		return nomemunic;
	}

	/**
	 * Sets the nomemunic.
	 *
	 * @param nomemunic the new nomemunic
	 */
	public void setNomemunic(String nomemunic) {
		this.nomemunic = nomemunic;
	}

	/**
	 * Gets the dddmunic.
	 *
	 * @return the dddmunic
	 */
	public String getDddmunic() {
		return dddmunic;
	}

	/**
	 * Sets the dddmunic.
	 *
	 * @param dddmunic the new dddmunic
	 */
	public void setDddmunic(String dddmunic) {
		this.dddmunic = dddmunic;
	}

	/**
	 * Gets the listinsalt.
	 *
	 * @return the listinsalt
	 */
	public List<Auditoria> getListinsalt() {
		return listinsalt;
	}

	/**
	 * Sets the listinsalt.
	 *
	 * @param listinsalt the new listinsalt
	 */
	public void setListinsalt(List<Auditoria> listinsalt) {
		this.listinsalt = listinsalt;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Municipio [codmunic=" + codmunic + ", siglauf=" + siglauf
				+ ", codpais=" + codpais + ", nomemunic=" + nomemunic
				+ ", dddmunic=" + dddmunic + ", listinsalt=" + listinsalt
				+ ", getCodmunic()=" + getCodmunic() + ", getSiglauf()="
				+ getSiglauf() + ", getCodpais()=" + getCodpais()
				+ ", getNomemunic()=" + getNomemunic() + ", getDddmunic()="
				+ getDddmunic() + ", getListinsalt()=" + getListinsalt() + "]";
	}

}
