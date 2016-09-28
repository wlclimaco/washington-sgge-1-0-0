/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFPessoaAutorizadaDownloadNFe extends NFBase
{

    /** The econtabil cnpj for the NFPessoaAutorizadaDownloadNFe. */
    private String cnpj;

    /** The econtabil cpf for the NFPessoaAutorizadaDownloadNFe. */
    private String cpf;



    /**
     * Default constructor.
     */
    public NFPessoaAutorizadaDownloadNFe()
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


	@Override
	public String toString() {
		return "NFPessoaAutorizadaDownloadNFe [getCnpj()=" + getCnpj() + ", getCpf()=" + getCpf() + ", toString()="
				+ super.toString() + "]";
	}



 }
