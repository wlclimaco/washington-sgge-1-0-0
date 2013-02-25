package com.sensus.mlc.endereco.model;

import java.sql.Date;

import com.sensus.common.model.SensusModel;


// TODO: Auto-generated Javadoc
/**
 * * The Model Object Action.
 *
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class Endereco extends SensusModel
{

	/** The codemp. */
	private Integer codend;

	/** The emdemp. */
	private String endereco;

	/** The numemp. */
	private Integer numero;

	/** The complemp. */
	private String complemento;

	/** The bairemp. */
	private String bairro;

	/** The cepemp. */
	private String cep;

	/** The cidemp. */
	private String cidade;

	/** The ufemp. */
	private String uf;

	/** The dddemp. */
	private String ddd;

	/** The foneemp. */
	private String fone1;

	/** The faxemp. */
	private String fone2;

	/** The emailemp. */
	private String email;

	/** The wwwemp. */
	private String site;

	/** The codeanemp. */
	private String cel1;

	/** The nomecontemp. */
	private String cel2;

	/** The multialmoxemp. */
	private String codpais;

	private String nomecontemp;

	/** The codmunic. */
	private String codmunic;

	/** The siglauf. */
	private String siglauf;


	/** The dtins. */
	private Date dtins;

	/** The hins. */
	private Date hins;

	/** The idusuins. */
	private Integer idusuins;

	/** The dtalt. */
	private Date dtalt;

	/** The halt. */
	private Date halt;

	/** The idusualt. */
	private String idusualt;

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
	 * Gets the ddd.
	 *
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Sets the ddd.
	 *
	 * @param ddd the new ddd
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
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
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Gets the cel1.
	 *
	 * @return the cel1
	 */
	public String getCel1() {
		return cel1;
	}

	/**
	 * Sets the cel1.
	 *
	 * @param cel1 the new cel1
	 */
	public void setCel1(String cel1) {
		this.cel1 = cel1;
	}

	/**
	 * Gets the cel2.
	 *
	 * @return the cel2
	 */
	public String getCel2() {
		return cel2;
	}

	/**
	 * Sets the cel2.
	 *
	 * @param cel2 the new cel2
	 */
	public void setCel2(String cel2) {
		this.cel2 = cel2;
	}

	/**
	 * Gets the codpais.
	 *
	 * @return the codpais
	 */
	public String getCodpais() {
		return codpais;
	}

	/**
	 * Sets the codpais.
	 *
	 * @param codpais the new codpais
	 */
	public void setCodpais(String codpais) {
		this.codpais = codpais;
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
	 * Gets the dtins.
	 *
	 * @return the dtins
	 */
	public Date getDtins() {
		return dtins;
	}

	/**
	 * Sets the dtins.
	 *
	 * @param dtins the new dtins
	 */
	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}

	/**
	 * Gets the hins.
	 *
	 * @return the hins
	 */
	public Date getHins() {
		return hins;
	}

	/**
	 * Sets the hins.
	 *
	 * @param hins the new hins
	 */
	public void setHins(Date hins) {
		this.hins = hins;
	}

	/**
	 * Gets the idusuins.
	 *
	 * @return the idusuins
	 */
	public Integer getIdusuins() {
		return idusuins;
	}

	/**
	 * Sets the idusuins.
	 *
	 * @param idusuins the new idusuins
	 */
	public void setIdusuins(Integer idusuins) {
		this.idusuins = idusuins;
	}

	/**
	 * Gets the dtalt.
	 *
	 * @return the dtalt
	 */
	public Date getDtalt() {
		return dtalt;
	}

	/**
	 * Sets the dtalt.
	 *
	 * @param dtalt the new dtalt
	 */
	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}

	/**
	 * Gets the halt.
	 *
	 * @return the halt
	 */
	public Date getHalt() {
		return halt;
	}

	/**
	 * Sets the halt.
	 *
	 * @param halt the new halt
	 */
	public void setHalt(Date halt) {
		this.halt = halt;
	}

	/**
	 * Gets the idusualt.
	 *
	 * @return the idusualt
	 */
	public String getIdusualt() {
		return idusualt;
	}

	/**
	 * Sets the idusualt.
	 *
	 * @param idusualt the new idusualt
	 */
	public void setIdusualt(String idusualt) {
		this.idusualt = idusualt;
	}

	public String getNomecontemp() {
		return nomecontemp;
	}

	public void setNomecontemp(String nomecontemp) {
		this.nomecontemp = nomecontemp;
	}

	@Override
	public String toString() {
		return "Endereco [codend=" + codend + ", endereco=" + endereco
				+ ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade
				+ ", uf=" + uf + ", ddd=" + ddd + ", fone1=" + fone1
				+ ", fone2=" + fone2 + ", email=" + email + ", site=" + site
				+ ", cel1=" + cel1 + ", cel2=" + cel2 + ", codpais=" + codpais
				+ ", nomecontemp=" + nomecontemp + ", codmunic=" + codmunic
				+ ", siglauf=" + siglauf + ", dtins=" + dtins + ", hins="
				+ hins + ", idusuins=" + idusuins + ", dtalt=" + dtalt
				+ ", halt=" + halt + ", idusualt=" + idusualt
				+ ", getCodend()=" + getCodend() + ", getEndereco()="
				+ getEndereco() + ", getNumero()=" + getNumero()
				+ ", getComplemento()=" + getComplemento() + ", getBairro()="
				+ getBairro() + ", getCep()=" + getCep() + ", getCidade()="
				+ getCidade() + ", getUf()=" + getUf() + ", getDdd()="
				+ getDdd() + ", getFone1()=" + getFone1() + ", getFone2()="
				+ getFone2() + ", getEmail()=" + getEmail() + ", getSite()="
				+ getSite() + ", getCel1()=" + getCel1() + ", getCel2()="
				+ getCel2() + ", getCodpais()=" + getCodpais()
				+ ", getCodmunic()=" + getCodmunic() + ", getSiglauf()="
				+ getSiglauf() + ", getDtins()=" + getDtins() + ", getHins()="
				+ getHins() + ", getIdusuins()=" + getIdusuins()
				+ ", getDtalt()=" + getDtalt() + ", getHalt()=" + getHalt()
				+ ", getIdusualt()=" + getIdusualt() + ", getNomecontemp()="
				+ getNomecontemp() + "]";
	}



}
