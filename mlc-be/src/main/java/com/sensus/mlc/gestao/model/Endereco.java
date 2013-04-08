package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Endereco.
 */
public class Endereco extends SensusModel
{

    /** The codend. */
    private Integer codend;

    /** The endereco. */
    private String endereco;

    /** The numero. */
    private Integer numero;

    /** The complemento. */
    private String complemento;

    /** The bairro. */
    private String bairro;

    /** The cep. */
    private String cep;

    /** The cidade. */
    private String cidade;

    /** The uf. */
    private String uf;

    /** The fone1. */
    private String fone1;

    /** The fone2. */
    private String fone2;

    /** The fax. */
    private String fax;

    /** The email. */
    private String email;

    /** The www. */
    private String www;

    /** The coddist. */
    private Integer coddist;

    /** The codmunic. */
    private String codmunic;

    /** The siglauf. */
    private String siglauf;

    /** The codpais. */
    private Pais codpais;

    /** The codempuc. */
    private Integer codempuc;

    /** The codfilialuc. */
    private Integer codfilialuc;

    /** The codunifcod. */
    private Integer codunifcod;

    /** The inscmun. */
    private String inscmun;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codend.
	 *
	 * @return the codend
	 */
	public Integer getCodend() {
		return codend;
	}

	/**
	 * Sets the codend.
	 *
	 * @param codend the new codend
	 */
	public void setCodend(Integer codend) {
		this.codend = codend;
	}

	/**
	 * Gets the endereco.
	 *
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Sets the endereco.
	 *
	 * @param endereco the new endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Gets the complemento.
	 *
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Sets the complemento.
	 *
	 * @param complemento the new complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the new cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Sets the uf.
	 *
	 * @param uf the new uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Gets the fone1.
	 *
	 * @return the fone1
	 */
	public String getFone1() {
		return fone1;
	}

	/**
	 * Sets the fone1.
	 *
	 * @param fone1 the new fone1
	 */
	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	/**
	 * Gets the fone2.
	 *
	 * @return the fone2
	 */
	public String getFone2() {
		return fone2;
	}

	/**
	 * Sets the fone2.
	 *
	 * @param fone2 the new fone2
	 */
	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	/**
	 * Gets the fax.
	 *
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Sets the fax.
	 *
	 * @param fax the new fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the www.
	 *
	 * @return the www
	 */
	public String getWww() {
		return www;
	}

	/**
	 * Sets the www.
	 *
	 * @param www the new www
	 */
	public void setWww(String www) {
		this.www = www;
	}

	/**
	 * Gets the coddist.
	 *
	 * @return the coddist
	 */
	public Integer getCoddist() {
		return coddist;
	}

	/**
	 * Sets the coddist.
	 *
	 * @param coddist the new coddist
	 */
	public void setCoddist(Integer coddist) {
		this.coddist = coddist;
	}

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
	 * Gets the codempuc.
	 *
	 * @return the codempuc
	 */
	public Integer getCodempuc() {
		return codempuc;
	}

	/**
	 * Sets the codempuc.
	 *
	 * @param codempuc the new codempuc
	 */
	public void setCodempuc(Integer codempuc) {
		this.codempuc = codempuc;
	}

	/**
	 * Gets the codfilialuc.
	 *
	 * @return the codfilialuc
	 */
	public Integer getCodfilialuc() {
		return codfilialuc;
	}

	/**
	 * Sets the codfilialuc.
	 *
	 * @param codfilialuc the new codfilialuc
	 */
	public void setCodfilialuc(Integer codfilialuc) {
		this.codfilialuc = codfilialuc;
	}

	/**
	 * Gets the codunifcod.
	 *
	 * @return the codunifcod
	 */
	public Integer getCodunifcod() {
		return codunifcod;
	}

	/**
	 * Sets the codunifcod.
	 *
	 * @param codunifcod the new codunifcod
	 */
	public void setCodunifcod(Integer codunifcod) {
		this.codunifcod = codunifcod;
	}

	/**
	 * Gets the inscmun.
	 *
	 * @return the inscmun
	 */
	public String getInscmun() {
		return inscmun;
	}

	/**
	 * Sets the inscmun.
	 *
	 * @param inscmun the new inscmun
	 */
	public void setInscmun(String inscmun) {
		this.inscmun = inscmun;
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
		return "Endereco [codend=" + codend + ", endereco=" + endereco
				+ ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade
				+ ", uf=" + uf + ", fone1=" + fone1 + ", fone2=" + fone2
				+ ", fax=" + fax + ", email=" + email + ", www=" + www
				+ ", coddist=" + coddist + ", codmunic=" + codmunic
				+ ", siglauf=" + siglauf + ", codpais=" + codpais
				+ ", codempuc=" + codempuc + ", codfilialuc=" + codfilialuc
				+ ", codunifcod=" + codunifcod + ", inscmun=" + inscmun
				+ ", listinsalt=" + listinsalt + ", getCodend()=" + getCodend()
				+ ", getEndereco()=" + getEndereco() + ", getNumero()="
				+ getNumero() + ", getComplemento()=" + getComplemento()
				+ ", getBairro()=" + getBairro() + ", getCep()=" + getCep()
				+ ", getCidade()=" + getCidade() + ", getUf()=" + getUf()
				+ ", getFone1()=" + getFone1() + ", getFone2()=" + getFone2()
				+ ", getFax()=" + getFax() + ", getEmail()=" + getEmail()
				+ ", getWww()=" + getWww() + ", getCoddist()=" + getCoddist()
				+ ", getCodmunic()=" + getCodmunic() + ", getSiglauf()="
				+ getSiglauf() + ", getCodpais()=" + getCodpais()
				+ ", getCodempuc()=" + getCodempuc() + ", getCodfilialuc()="
				+ getCodfilialuc() + ", getCodunifcod()=" + getCodunifcod()
				+ ", getInscmun()=" + getInscmun() + ", getListinsalt()="
				+ getListinsalt() + "]";
	}


}
