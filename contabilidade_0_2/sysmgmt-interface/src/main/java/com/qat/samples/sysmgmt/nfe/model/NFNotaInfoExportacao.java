/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoExportacao extends NFBase
{

    /** The econtabil ufEmbarqueProduto for the NFNotaInfoExportacao. */
    private String ufembarqueproduto;

    /** The econtabil localEmbarqueProdutos for the NFNotaInfoExportacao. */
    private String localembarqueprodutos;

    /** The econtabil localDespachoProdutos for the NFNotaInfoExportacao. */
    private String localdespachoprodutos;



    /**
     * Default constructor.
     */
    public NFNotaInfoExportacao()
    {
        super();
    }


    /**
     * Gets the ufembarqueproduto.
     *
     * @return the ufembarqueproduto
     */
    /**
     * Gets the ufembarqueproduto.
     *
     * @return the ufembarqueproduto
     */
    public String getUfEmbarqueProduto()
    {
        return ufembarqueproduto;
    }

    /**
     * Sets the ufembarqueproduto.
     *
* @param id the ufembarqueproduto to set
 */
public void setUfEmbarqueProduto(String ufembarqueproduto)
{
        this.ufembarqueproduto = ufembarqueproduto;
    }

    /**
     * Gets the localembarqueprodutos.
     *
     * @return the localembarqueprodutos
     */
    /**
     * Gets the localembarqueprodutos.
     *
     * @return the localembarqueprodutos
     */
    public String getLocalEmbarqueProdutos()
    {
        return localembarqueprodutos;
    }

    /**
     * Sets the localembarqueprodutos.
     *
* @param id the localembarqueprodutos to set
 */
public void setLocalEmbarqueProdutos(String localembarqueprodutos)
{
        this.localembarqueprodutos = localembarqueprodutos;
    }

    /**
     * Gets the localdespachoprodutos.
     *
     * @return the localdespachoprodutos
     */
    /**
     * Gets the localdespachoprodutos.
     *
     * @return the localdespachoprodutos
     */
    public String getLocalDespachoProdutos()
    {
        return localdespachoprodutos;
    }

    /**
     * Sets the localdespachoprodutos.
     *
* @param id the localdespachoprodutos to set
 */
public void setLocalDespachoProdutos(String localdespachoprodutos)
{
        this.localdespachoprodutos = localdespachoprodutos;
    }


	public String getUfembarqueproduto() {
		return ufembarqueproduto;
	}


	public void setUfembarqueproduto(String ufembarqueproduto) {
		this.ufembarqueproduto = ufembarqueproduto;
	}


	public String getLocalembarqueprodutos() {
		return localembarqueprodutos;
	}


	public void setLocalembarqueprodutos(String localembarqueprodutos) {
		this.localembarqueprodutos = localembarqueprodutos;
	}


	public String getLocaldespachoprodutos() {
		return localdespachoprodutos;
	}


	public void setLocaldespachoprodutos(String localdespachoprodutos) {
		this.localdespachoprodutos = localdespachoprodutos;
	}


	@Override
	public String toString() {
		return "NFNotaInfoExportacao [getUfEmbarqueProduto()=" + getUfEmbarqueProduto()
				+ ", getLocalEmbarqueProdutos()=" + getLocalEmbarqueProdutos() + ", getLocalDespachoProdutos()="
				+ getLocalDespachoProdutos() + ", getUfembarqueproduto()=" + getUfembarqueproduto()
				+ ", getLocalembarqueprodutos()=" + getLocalembarqueprodutos() + ", getLocaldespachoprodutos()="
				+ getLocaldespachoprodutos() + ", toString()=" + super.toString() + "]";
	}





 }
