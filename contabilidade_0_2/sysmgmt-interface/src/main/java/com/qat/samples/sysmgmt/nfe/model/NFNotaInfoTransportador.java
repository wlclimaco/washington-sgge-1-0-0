/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoTransportador extends NFBase
{

    /** The econtabil cnpj for the NFNotaInfoTransportador. */
    private String cnpj;

    /** The econtabil cpf for the NFNotaInfoTransportador. */
    private String cpf;

    /** The econtabil razaoSocial for the NFNotaInfoTransportador. */
    private String razaosocial;

    /** The econtabil inscricaoEstadual for the NFNotaInfoTransportador. */
    private String inscricaoestadual;

    /** The econtabil enderecoComplemento for the NFNotaInfoTransportador. */
    private String enderecocomplemento;

    /** The econtabil nomeMunicipio for the NFNotaInfoTransportador. */
    private String nomemunicipio;

    /** The econtabil uf for the NFNotaInfoTransportador. */
    private String uf;



    /**
     * Default constructor.
     */
    public NFNotaInfoTransportador()
    {
        super();
    }



	public String getCnpj() {
		return cnpj;
	}



	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getRazaosocial() {
		return razaosocial;
	}



	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}



	public String getInscricaoestadual() {
		return inscricaoestadual;
	}



	public void setInscricaoestadual(String inscricaoestadual) {
		this.inscricaoestadual = inscricaoestadual;
	}



	public String getEnderecocomplemento() {
		return enderecocomplemento;
	}



	public void setEnderecocomplemento(String enderecocomplemento) {
		this.enderecocomplemento = enderecocomplemento;
	}



	public String getNomemunicipio() {
		return nomemunicipio;
	}



	public void setNomemunicipio(String nomemunicipio) {
		this.nomemunicipio = nomemunicipio;
	}



	public String getUf() {
		return uf;
	}



	public void setUf(String uf) {
		this.uf = uf;
	}



	@Override
	public String toString() {
		return "NFNotaInfoTransportador [getCnpj()=" + getCnpj() + ", getCpf()=" + getCpf() + ", getRazaosocial()="
				+ getRazaosocial() + ", getInscricaoestadual()=" + getInscricaoestadual()
				+ ", getEnderecocomplemento()=" + getEnderecocomplemento() + ", getNomemunicipio()="
				+ getNomemunicipio() + ", getUf()=" + getUf() + ", toString()=" + super.toString() + "]";
	}


 }
