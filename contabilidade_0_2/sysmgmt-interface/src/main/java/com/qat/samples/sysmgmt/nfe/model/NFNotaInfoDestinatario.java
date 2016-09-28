/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Endereco;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoDestinatario extends NFBase
{

    /** The econtabil cnpj for the NFNotaInfoDestinatario. */
    private String cnpj;

    /** The econtabil cpf for the NFNotaInfoDestinatario. */
    private String cpf;

    /** The econtabil idEstrangeiro for the NFNotaInfoDestinatario. */
    private String idestrangeiro;

    /** The econtabil razaoSocial for the NFNotaInfoDestinatario. */
    private String razaosocial;

    /** The econtabil endereco for the NFNotaInfoDestinatario. */
    private Endereco endereco;

    /** The econtabil indicadorIEDestinatario for the NFNotaInfoDestinatario. */
    private DoisValores indicadoriedestinatario;

    /** The econtabil inscricaoEstadual for the NFNotaInfoDestinatario. */
    private String inscricaoestadual;

    /** The econtabil inscricaoSuframa for the NFNotaInfoDestinatario. */
    private String inscricaosuframa;

    /** The econtabil inscricaoMunicipal for the NFNotaInfoDestinatario. */
    private String inscricaomunicipal;

    /** The econtabil email for the NFNotaInfoDestinatario. */
    private String email;



    /**
     * Default constructor.
     */
    public NFNotaInfoDestinatario()
    {
        super();
    }


    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj()
    {
        return cnpj;
    }

    /**
     * Sets the cnpj.
     *
* @param id the cnpj to set
 */
public void setCnpj(String cnpj)
{
        this.cnpj = cnpj;
    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf()
    {
        return cpf;
    }

    /**
     * Sets the cpf.
     *
* @param id the cpf to set
 */
public void setCpf(String cpf)
{
        this.cpf = cpf;
    }

    /**
     * Gets the idestrangeiro.
     *
     * @return the idestrangeiro
     */
    /**
     * Gets the idestrangeiro.
     *
     * @return the idestrangeiro
     */
    public String getIdEstrangeiro()
    {
        return idestrangeiro;
    }

    /**
     * Sets the idestrangeiro.
     *
* @param id the idestrangeiro to set
 */
public void setIdEstrangeiro(String idestrangeiro)
{
        this.idestrangeiro = idestrangeiro;
    }

    /**
     * Gets the razaosocial.
     *
     * @return the razaosocial
     */
    /**
     * Gets the razaosocial.
     *
     * @return the razaosocial
     */
    public String getRazaoSocial()
    {
        return razaosocial;
    }

    /**
     * Sets the razaosocial.
     *
* @param id the razaosocial to set
 */
public void setRazaoSocial(String razaosocial)
{
        this.razaosocial = razaosocial;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public Endereco getEndereco()
    {
        return endereco;
    }

    /**
     * Sets the endereco.
     *
* @param id the endereco to set
 */
public void setEndereco(Endereco endereco)
{
        this.endereco = endereco;
    }

    /**
     * Gets the indicadoriedestinatario.
     *
     * @return the indicadoriedestinatario
     */
    /**
     * Gets the indicadoriedestinatario.
     *
     * @return the indicadoriedestinatario
     */
    public DoisValores getIndicadorIEDestinatario()
    {
        return indicadoriedestinatario;
    }

    /**
     * Sets the indicadoriedestinatario.
     *
* @param id the indicadoriedestinatario to set
 */
public void setIndicadorIEDestinatario(DoisValores indicadoriedestinatario)
{
        this.indicadoriedestinatario = indicadoriedestinatario;
    }

    /**
     * Gets the inscricaoestadual.
     *
     * @return the inscricaoestadual
     */
    /**
     * Gets the inscricaoestadual.
     *
     * @return the inscricaoestadual
     */
    public String getInscricaoEstadual()
    {
        return inscricaoestadual;
    }

    /**
     * Sets the inscricaoestadual.
     *
* @param id the inscricaoestadual to set
 */
public void setInscricaoEstadual(String inscricaoestadual)
{
        this.inscricaoestadual = inscricaoestadual;
    }

    /**
     * Gets the inscricaosuframa.
     *
     * @return the inscricaosuframa
     */
    /**
     * Gets the inscricaosuframa.
     *
     * @return the inscricaosuframa
     */
    public String getInscricaoSuframa()
    {
        return inscricaosuframa;
    }

    /**
     * Sets the inscricaosuframa.
     *
* @param id the inscricaosuframa to set
 */
public void setInscricaoSuframa(String inscricaosuframa)
{
        this.inscricaosuframa = inscricaosuframa;
    }

    /**
     * Gets the inscricaomunicipal.
     *
     * @return the inscricaomunicipal
     */
    /**
     * Gets the inscricaomunicipal.
     *
     * @return the inscricaomunicipal
     */
    public String getInscricaoMunicipal()
    {
        return inscricaomunicipal;
    }

    /**
     * Sets the inscricaomunicipal.
     *
* @param id the inscricaomunicipal to set
 */
public void setInscricaoMunicipal(String inscricaomunicipal)
{
        this.inscricaomunicipal = inscricaomunicipal;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email.
     *
* @param id the email to set
 */
public void setEmail(String email)
{
        this.email = email;
    }


	public String getIdestrangeiro() {
		return idestrangeiro;
	}


	public void setIdestrangeiro(String idestrangeiro) {
		this.idestrangeiro = idestrangeiro;
	}


	public String getRazaosocial() {
		return razaosocial;
	}


	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}


	public DoisValores getIndicadoriedestinatario() {
		return indicadoriedestinatario;
	}


	public void setIndicadoriedestinatario(DoisValores indicadoriedestinatario) {
		this.indicadoriedestinatario = indicadoriedestinatario;
	}


	public String getInscricaoestadual() {
		return inscricaoestadual;
	}


	public void setInscricaoestadual(String inscricaoestadual) {
		this.inscricaoestadual = inscricaoestadual;
	}


	public String getInscricaosuframa() {
		return inscricaosuframa;
	}


	public void setInscricaosuframa(String inscricaosuframa) {
		this.inscricaosuframa = inscricaosuframa;
	}


	public String getInscricaomunicipal() {
		return inscricaomunicipal;
	}


	public void setInscricaomunicipal(String inscricaomunicipal) {
		this.inscricaomunicipal = inscricaomunicipal;
	}


	@Override
	public String toString() {
		return "NFNotaInfoDestinatario [getCnpj()=" + getCnpj() + ", getCpf()=" + getCpf() + ", getIdEstrangeiro()="
				+ getIdEstrangeiro() + ", getRazaoSocial()=" + getRazaoSocial() + ", getEndereco()=" + getEndereco()
				+ ", getIndicadorIEDestinatario()=" + getIndicadorIEDestinatario() + ", getInscricaoEstadual()="
				+ getInscricaoEstadual() + ", getInscricaoSuframa()=" + getInscricaoSuframa()
				+ ", getInscricaoMunicipal()=" + getInscricaoMunicipal() + ", getEmail()=" + getEmail()
				+ ", getIdestrangeiro()=" + getIdestrangeiro() + ", getRazaosocial()=" + getRazaosocial()
				+ ", getIndicadoriedestinatario()=" + getIndicadoriedestinatario() + ", getInscricaoestadual()="
				+ getInscricaoestadual() + ", getInscricaosuframa()=" + getInscricaosuframa()
				+ ", getInscricaomunicipal()=" + getInscricaomunicipal() + ", toString()=" + super.toString() + "]";
	}





 }
