package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Empresa.
 */
public class Empresa extends SensusModel
{

    /** The codemp. */
    private Integer codemp;

    /** The razemp. */
    private String razemp;

    /** The nomeemp. */
    private String nomeemp;

    /** The cnpjemp. */
    private String cnpjemp;

    /** The inscemp. */
    private String inscemp;

    /** The multialmoxemp. */
    private String multialmoxemp;

    /** The fotoemp. */
    private String fotoemp;

    /** The percissemp. */
    private Double percissemp;

    /** The codend. */
    private Endereco codend;

    /** The nrcertif. */
    private String nrcertif;

    /** The nrsencer. */
    private String nrsencer;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codemp.
	 *
	 * @return the codemp
	 */
	public Integer getCodemp() {
		return codemp;
	}

	/**
	 * Sets the codemp.
	 *
	 * @param codemp the new codemp
	 */
	public void setCodemp(Integer codemp) {
		this.codemp = codemp;
	}

	/**
	 * Gets the razemp.
	 *
	 * @return the razemp
	 */
	public String getRazemp() {
		return razemp;
	}

	/**
	 * Sets the razemp.
	 *
	 * @param razemp the new razemp
	 */
	public void setRazemp(String razemp) {
		this.razemp = razemp;
	}

	/**
	 * Gets the nomeemp.
	 *
	 * @return the nomeemp
	 */
	public String getNomeemp() {
		return nomeemp;
	}

	/**
	 * Sets the nomeemp.
	 *
	 * @param nomeemp the new nomeemp
	 */
	public void setNomeemp(String nomeemp) {
		this.nomeemp = nomeemp;
	}

	/**
	 * Gets the cnpjemp.
	 *
	 * @return the cnpjemp
	 */
	public String getCnpjemp() {
		return cnpjemp;
	}

	/**
	 * Sets the cnpjemp.
	 *
	 * @param cnpjemp the new cnpjemp
	 */
	public void setCnpjemp(String cnpjemp) {
		this.cnpjemp = cnpjemp;
	}

	/**
	 * Gets the inscemp.
	 *
	 * @return the inscemp
	 */
	public String getInscemp() {
		return inscemp;
	}

	/**
	 * Sets the inscemp.
	 *
	 * @param inscemp the new inscemp
	 */
	public void setInscemp(String inscemp) {
		this.inscemp = inscemp;
	}

	/**
	 * Gets the multialmoxemp.
	 *
	 * @return the multialmoxemp
	 */
	public String getMultialmoxemp() {
		return multialmoxemp;
	}

	/**
	 * Sets the multialmoxemp.
	 *
	 * @param multialmoxemp the new multialmoxemp
	 */
	public void setMultialmoxemp(String multialmoxemp) {
		this.multialmoxemp = multialmoxemp;
	}

	/**
	 * Gets the fotoemp.
	 *
	 * @return the fotoemp
	 */
	public String getFotoemp() {
		return fotoemp;
	}

	/**
	 * Sets the fotoemp.
	 *
	 * @param fotoemp the new fotoemp
	 */
	public void setFotoemp(String fotoemp) {
		this.fotoemp = fotoemp;
	}

	/**
	 * Gets the percissemp.
	 *
	 * @return the percissemp
	 */
	public Double getPercissemp() {
		return percissemp;
	}

	/**
	 * Sets the percissemp.
	 *
	 * @param percissemp the new percissemp
	 */
	public void setPercissemp(Double percissemp) {
		this.percissemp = percissemp;
	}

	/**
	 * Gets the codend.
	 *
	 * @return the codend
	 */
	public Endereco getCodend() {
		return codend;
	}

	/**
	 * Sets the codend.
	 *
	 * @param codend the new codend
	 */
	public void setCodend(Endereco codend) {
		this.codend = codend;
	}

	/**
	 * Gets the nrcertif.
	 *
	 * @return the nrcertif
	 */
	public String getNrcertif() {
		return nrcertif;
	}

	/**
	 * Sets the nrcertif.
	 *
	 * @param nrcertif the new nrcertif
	 */
	public void setNrcertif(String nrcertif) {
		this.nrcertif = nrcertif;
	}

	/**
	 * Gets the nrsencer.
	 *
	 * @return the nrsencer
	 */
	public String getNrsencer() {
		return nrsencer;
	}

	/**
	 * Sets the nrsencer.
	 *
	 * @param nrsencer the new nrsencer
	 */
	public void setNrsencer(String nrsencer) {
		this.nrsencer = nrsencer;
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
		return "Empresa [codemp=" + codemp + ", razemp=" + razemp
				+ ", nomeemp=" + nomeemp + ", cnpjemp=" + cnpjemp
				+ ", inscemp=" + inscemp + ", multialmoxemp=" + multialmoxemp
				+ ", fotoemp=" + fotoemp + ", percissemp=" + percissemp
				+ ", codend=" + codend + ", nrcertif=" + nrcertif
				+ ", nrsencer=" + nrsencer + ", listinsalt=" + listinsalt
				+ ", getCodemp()=" + getCodemp() + ", getRazemp()="
				+ getRazemp() + ", getNomeemp()=" + getNomeemp()
				+ ", getCnpjemp()=" + getCnpjemp() + ", getInscemp()="
				+ getInscemp() + ", getMultialmoxemp()=" + getMultialmoxemp()
				+ ", getFotoemp()=" + getFotoemp() + ", getPercissemp()="
				+ getPercissemp() + ", getCodend()=" + getCodend()
				+ ", getNrcertif()=" + getNrcertif() + ", getNrsencer()="
				+ getNrsencer() + ", getListinsalt()=" + getListinsalt() + "]";
	}


}
