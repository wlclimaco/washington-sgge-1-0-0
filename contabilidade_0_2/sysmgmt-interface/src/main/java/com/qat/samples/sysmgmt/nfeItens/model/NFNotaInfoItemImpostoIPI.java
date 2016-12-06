/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;


import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoIPI extends ModelCosmeDamiao
{

    /** The econtabil id for the NFNotaInfoItemImpostoIPI. */
    private Integer id;

    /** The econtabil classeEnquadramento for the NFNotaInfoItemImpostoIPI. */
    private String classeEnquadramento;

    /** The econtabil cnpjProdutor for the NFNotaInfoItemImpostoIPI. */
    private String cnpjProdutor;

    /** The econtabil codigoSelo for the NFNotaInfoItemImpostoIPI. */
    private String codigoSelo;

    /** The econtabil quantidadeSelo for the NFNotaInfoItemImpostoIPI. */
    private Integer quantidadeSelo;

    /** The econtabil codigoEnquadramento for the NFNotaInfoItemImpostoIPI. */
    private String codigoEnquadramento;

    /** The econtabil tributado for the NFNotaInfoItemImpostoIPI. */
    private NFNotaInfoItemImpostoIPITributado tributado;

    /** The econtabil naoTributado for the NFNotaInfoItemImpostoIPI. */
    private NFNotaInfoItemImpostoIPINaoTributado naoTributado;



    /**
     * Default constructor.
     */
    public NFNotaInfoItemImpostoIPI()
    {
        super();
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
* @param id the id to set
 */
public void setId(Integer id)
{
        this.id = id;
    }

    /**
     * Gets the classeEnquadramento.
     *
     * @return the classeEnquadramento
     */
    /**
     * Gets the classeEnquadramento.
     *
     * @return the classeEnquadramento
     */
    public String getClasseEnquadramento()
    {
        return classeEnquadramento;
    }

    /**
     * Sets the classeenquadramento.
     *
* @param id the classeenquadramento to set
 */
public void setClasseEnquadramento(String classeenquadramento)
{
        this.classeEnquadramento = classeenquadramento;
    }

    /**
     * Gets the cnpjProdutor.
     *
     * @return the cnpjProdutor
     */
    /**
     * Gets the cnpjProdutor.
     *
     * @return the cnpjProdutor
     */
    public String getCnpjProdutor()
    {
        return cnpjProdutor;
    }

    /**
     * Sets the cnpjprodutor.
     *
* @param id the cnpjprodutor to set
 */
public void setCnpjProdutor(String cnpjprodutor)
{
        this.cnpjProdutor = cnpjprodutor;
    }

    /**
     * Gets the codigoSelo.
     *
     * @return the codigoSelo
     */
    /**
     * Gets the codigoSelo.
     *
     * @return the codigoSelo
     */
    public String getCodigoSelo()
    {
        return codigoSelo;
    }

    /**
     * Sets the codigoselo.
     *
* @param id the codigoselo to set
 */
public void setCodigoSelo(String codigoselo)
{
        this.codigoSelo = codigoselo;
    }

    /**
     * Gets the quantidadeSelo.
     *
     * @return the quantidadeSelo
     */
    /**
     * Gets the quantidadeSelo.
     *
     * @return the quantidadeSelo
     */
    public Integer getQuantidadeSelo()
    {
        return quantidadeSelo;
    }

    /**
     * Sets the quantidadeselo.
     *
* @param id the quantidadeselo to set
 */
public void setQuantidadeSelo(Integer quantidadeselo)
{
        this.quantidadeSelo = quantidadeselo;
    }

    /**
     * Gets the codigoEnquadramento.
     *
     * @return the codigoEnquadramento
     */
    /**
     * Gets the codigoEnquadramento.
     *
     * @return the codigoEnquadramento
     */
    public String getCodigoEnquadramento()
    {
        return codigoEnquadramento;
    }

    /**
     * Sets the codigoenquadramento.
     *
* @param id the codigoenquadramento to set
 */
public void setCodigoEnquadramento(String codigoenquadramento)
{
        this.codigoEnquadramento = codigoenquadramento;
    }

    /**
     * Gets the tributado.
     *
     * @return the tributado
     */
    /**
     * Gets the tributado.
     *
     * @return the tributado
     */
    public NFNotaInfoItemImpostoIPITributado getTributado()
    {
        return tributado;
    }

    /**
     * Sets the tributado.
     *
* @param id the tributado to set
 */
public void setTributado(NFNotaInfoItemImpostoIPITributado tributado)
{
        this.tributado = tributado;
    }

    /**
     * Gets the naoTributado.
     *
     * @return the naoTributado
     */
    /**
     * Gets the naoTributado.
     *
     * @return the naoTributado
     */
    public NFNotaInfoItemImpostoIPINaoTributado getNaoTributado()
    {
        return naoTributado;
    }

    /**
     * Sets the naotributado.
     *
* @param id the naotributado to set
 */
public void setNaoTributado(NFNotaInfoItemImpostoIPINaoTributado naotributado)
{
        this.naoTributado = naotributado;
    }


	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoIPI [getId()=" + getId() + ", getClasseEnquadramento()=" + getClasseEnquadramento()
				+ ", getCnpjProdutor()=" + getCnpjProdutor() + ", getCodigoSelo()=" + getCodigoSelo()
				+ ", getQuantidadeSelo()=" + getQuantidadeSelo() + ", getCodigoEnquadramento()="
				+ getCodigoEnquadramento() + ", getTributado()=" + getTributado() + ", getNaoTributado()="
				+ getNaoTributado() + ", toString()=" + super.toString() + "]";
	}




 }
