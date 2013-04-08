package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Uf.
 */
public class Uf extends SensusModel
{

    /** The siglauf. */
    private String siglauf;

    /** The codpais. */
    private Pais codpais;

    /** The nomeuf. */
    private String nomeuf;

    /** The nomecurto. */
    private String nomecurto;

    /** The coduf. */
    private Integer coduf;

    /** The regiaouf. */
    private String regiaouf;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

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
	 * Gets the nomeuf.
	 *
	 * @return the nomeuf
	 */
	public String getNomeuf() {
		return nomeuf;
	}

	/**
	 * Sets the nomeuf.
	 *
	 * @param nomeuf the new nomeuf
	 */
	public void setNomeuf(String nomeuf) {
		this.nomeuf = nomeuf;
	}

	/**
	 * Gets the nomecurto.
	 *
	 * @return the nomecurto
	 */
	public String getNomecurto() {
		return nomecurto;
	}

	/**
	 * Sets the nomecurto.
	 *
	 * @param nomecurto the new nomecurto
	 */
	public void setNomecurto(String nomecurto) {
		this.nomecurto = nomecurto;
	}

	/**
	 * Gets the coduf.
	 *
	 * @return the coduf
	 */
	public Integer getCoduf() {
		return coduf;
	}

	/**
	 * Sets the coduf.
	 *
	 * @param coduf the new coduf
	 */
	public void setCoduf(Integer coduf) {
		this.coduf = coduf;
	}

	/**
	 * Gets the regiaouf.
	 *
	 * @return the regiaouf
	 */
	public String getRegiaouf() {
		return regiaouf;
	}

	/**
	 * Sets the regiaouf.
	 *
	 * @param regiaouf the new regiaouf
	 */
	public void setRegiaouf(String regiaouf) {
		this.regiaouf = regiaouf;
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
		return "Uf [siglauf=" + siglauf + ", codpais=" + codpais + ", nomeuf="
				+ nomeuf + ", nomecurto=" + nomecurto + ", coduf=" + coduf
				+ ", regiaouf=" + regiaouf + ", listinsalt=" + listinsalt
				+ ", getSiglauf()=" + getSiglauf() + ", getCodpais()="
				+ getCodpais() + ", getNomeuf()=" + getNomeuf()
				+ ", getNomecurto()=" + getNomecurto() + ", getCoduf()="
				+ getCoduf() + ", getRegiaouf()=" + getRegiaouf()
				+ ", getListinsalt()=" + getListinsalt() + "]";
	}


}
