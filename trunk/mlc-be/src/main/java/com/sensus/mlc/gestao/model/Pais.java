package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Pais.
 */
public class Pais extends SensusModel
{

    /** The codpais. */
    private Integer codpais;

    /** The nomepais. */
    private String nomepais;

    /** The sigla3cpais. */
    private String sigla3cpais;

    /** The ddipais. */
    private Integer ddipais;

    /** The sigla2cpais. */
    private String sigla2cpais;

    /** The codbacenpais. */
    private Integer codbacenpais;

    /** The codeanpais. */
    private String codeanpais;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codpais.
	 *
	 * @return the codpais
	 */
	public Integer getCodpais() {
		return codpais;
	}

	/**
	 * Sets the codpais.
	 *
	 * @param codpais the new codpais
	 */
	public void setCodpais(Integer codpais) {
		this.codpais = codpais;
	}

	/**
	 * Gets the nomepais.
	 *
	 * @return the nomepais
	 */
	public String getNomepais() {
		return nomepais;
	}

	/**
	 * Sets the nomepais.
	 *
	 * @param nomepais the new nomepais
	 */
	public void setNomepais(String nomepais) {
		this.nomepais = nomepais;
	}

	/**
	 * Gets the sigla3cpais.
	 *
	 * @return the sigla3cpais
	 */
	public String getSigla3cpais() {
		return sigla3cpais;
	}

	/**
	 * Sets the sigla3cpais.
	 *
	 * @param sigla3cpais the new sigla3cpais
	 */
	public void setSigla3cpais(String sigla3cpais) {
		this.sigla3cpais = sigla3cpais;
	}

	/**
	 * Gets the ddipais.
	 *
	 * @return the ddipais
	 */
	public Integer getDdipais() {
		return ddipais;
	}

	/**
	 * Sets the ddipais.
	 *
	 * @param ddipais the new ddipais
	 */
	public void setDdipais(Integer ddipais) {
		this.ddipais = ddipais;
	}

	/**
	 * Gets the sigla2cpais.
	 *
	 * @return the sigla2cpais
	 */
	public String getSigla2cpais() {
		return sigla2cpais;
	}

	/**
	 * Sets the sigla2cpais.
	 *
	 * @param sigla2cpais the new sigla2cpais
	 */
	public void setSigla2cpais(String sigla2cpais) {
		this.sigla2cpais = sigla2cpais;
	}

	/**
	 * Gets the codbacenpais.
	 *
	 * @return the codbacenpais
	 */
	public Integer getCodbacenpais() {
		return codbacenpais;
	}

	/**
	 * Sets the codbacenpais.
	 *
	 * @param codbacenpais the new codbacenpais
	 */
	public void setCodbacenpais(Integer codbacenpais) {
		this.codbacenpais = codbacenpais;
	}

	/**
	 * Gets the codeanpais.
	 *
	 * @return the codeanpais
	 */
	public String getCodeanpais() {
		return codeanpais;
	}

	/**
	 * Sets the codeanpais.
	 *
	 * @param codeanpais the new codeanpais
	 */
	public void setCodeanpais(String codeanpais) {
		this.codeanpais = codeanpais;
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
		return "Pais [codpais=" + codpais + ", nomepais=" + nomepais
				+ ", sigla3cpais=" + sigla3cpais + ", ddipais=" + ddipais
				+ ", sigla2cpais=" + sigla2cpais + ", codbacenpais="
				+ codbacenpais + ", codeanpais=" + codeanpais + ", listinsalt="
				+ listinsalt + ", getCodpais()=" + getCodpais()
				+ ", getNomepais()=" + getNomepais() + ", getSigla3cpais()="
				+ getSigla3cpais() + ", getDdipais()=" + getDdipais()
				+ ", getSigla2cpais()=" + getSigla2cpais()
				+ ", getCodbacenpais()=" + getCodbacenpais()
				+ ", getCodeanpais()=" + getCodeanpais() + ", getListinsalt()="
				+ getListinsalt() + "]";
	}


}
