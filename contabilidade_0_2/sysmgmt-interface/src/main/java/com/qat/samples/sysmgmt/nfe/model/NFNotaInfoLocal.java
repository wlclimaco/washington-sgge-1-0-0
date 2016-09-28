/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoLocal extends NFBase
{

    /** The econtabil cnpj for the NFNotaInfoLocal. */
    private String cnpj;

    /** The econtabil cpf for the NFNotaInfoLocal. */
    private String cpf;

    /** The econtabil logradouro for the NFNotaInfoLocal. */
    private String logradouro;

    /** The econtabil numero for the NFNotaInfoLocal. */
    private String numero;

    /** The econtabil complemento for the NFNotaInfoLocal. */
    private String complemento;

    /** The econtabil bairro for the NFNotaInfoLocal. */
    private String bairro;

    /** The econtabil codigoMunicipio for the NFNotaInfoLocal. */
    private String codigomunicipio;

    /** The econtabil nomeMunicipio for the NFNotaInfoLocal. */
    private String nomemunicipio;

    /** The econtabil uf for the NFNotaInfoLocal. */
    private String uf;



    /**
     * Default constructor.
     */
    public NFNotaInfoLocal()
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
     * Gets the logradouro.
     *
     * @return the logradouro
     */
    /**
     * Gets the logradouro.
     *
     * @return the logradouro
     */
    public String getLogradouro()
    {
        return logradouro;
    }

    /**
     * Sets the logradouro.
     *
* @param id the logradouro to set
 */
public void setLogradouro(String logradouro)
{
        this.logradouro = logradouro;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero()
    {
        return numero;
    }

    /**
     * Sets the numero.
     *
* @param id the numero to set
 */
public void setNumero(String numero)
{
        this.numero = numero;
    }

    /**
     * Gets the complemento.
     *
     * @return the complemento
     */
    /**
     * Gets the complemento.
     *
     * @return the complemento
     */
    public String getComplemento()
    {
        return complemento;
    }

    /**
     * Sets the complemento.
     *
* @param id the complemento to set
 */
public void setComplemento(String complemento)
{
        this.complemento = complemento;
    }

    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    public String getBairro()
    {
        return bairro;
    }

    /**
     * Sets the bairro.
     *
* @param id the bairro to set
 */
public void setBairro(String bairro)
{
        this.bairro = bairro;
    }

    /**
     * Gets the codigomunicipio.
     *
     * @return the codigomunicipio
     */
    /**
     * Gets the codigomunicipio.
     *
     * @return the codigomunicipio
     */
    public String getCodigoMunicipio()
    {
        return codigomunicipio;
    }

    /**
     * Sets the codigomunicipio.
     *
* @param id the codigomunicipio to set
 */
public void setCodigoMunicipio(String codigomunicipio)
{
        this.codigomunicipio = codigomunicipio;
    }

    /**
     * Gets the nomemunicipio.
     *
     * @return the nomemunicipio
     */
    /**
     * Gets the nomemunicipio.
     *
     * @return the nomemunicipio
     */
    public String getNomeMunicipio()
    {
        return nomemunicipio;
    }

    /**
     * Sets the nomemunicipio.
     *
* @param id the nomemunicipio to set
 */
public void setNomeMunicipio(String nomemunicipio)
{
        this.nomemunicipio = nomemunicipio;
    }

    /**
     * Gets the uf.
     *
     * @return the uf
     */
    /**
     * Gets the uf.
     *
     * @return the uf
     */
    public String getUf()
    {
        return uf;
    }

    /**
     * Sets the uf.
     *
* @param id the uf to set
 */
public void setUf(String uf)
{
        this.uf = uf;
    }


	public String getCodigomunicipio() {
		return codigomunicipio;
	}


	public void setCodigomunicipio(String codigomunicipio) {
		this.codigomunicipio = codigomunicipio;
	}


	public String getNomemunicipio() {
		return nomemunicipio;
	}


	public void setNomemunicipio(String nomemunicipio) {
		this.nomemunicipio = nomemunicipio;
	}


	@Override
	public String toString() {
		return "NFNotaInfoLocal [getCnpj()=" + getCnpj() + ", getCpf()=" + getCpf() + ", getLogradouro()="
				+ getLogradouro() + ", getNumero()=" + getNumero() + ", getComplemento()=" + getComplemento()
				+ ", getBairro()=" + getBairro() + ", getCodigoMunicipio()=" + getCodigoMunicipio()
				+ ", getNomeMunicipio()=" + getNomeMunicipio() + ", getUf()=" + getUf() + ", getCodigomunicipio()="
				+ getCodigomunicipio() + ", getNomemunicipio()=" + getNomemunicipio() + ", toString()="
				+ super.toString() + "]";
	}



 }
