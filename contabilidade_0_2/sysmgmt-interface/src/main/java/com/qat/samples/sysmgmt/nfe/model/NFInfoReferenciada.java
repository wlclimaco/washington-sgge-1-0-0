/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFInfoReferenciada extends NFBase
{

    /** The econtabil chaveAcesso for the NFInfoReferenciada. */
    private String chaveacesso;

    /** The econtabil modelo1por1Referenciada for the NFInfoReferenciada. */
    private Integer modelo1por1referenciada;

    /** The econtabil infoNFProdutorRuralReferenciada for the NFInfoReferenciada. */
    private NFInfoProdutorRuralReferenciada infonfprodutorruralreferenciada;

    /** The econtabil chaveAcessoCTReferenciada for the NFInfoReferenciada. */
    private String chaveacessoctreferenciada;

    /** The econtabil cupomFiscalReferenciado for the NFInfoReferenciada. */
    private NFInfoCupomFiscalReferenciado cupomfiscalreferenciado;



    /**
     * Default constructor.
     */
    public NFInfoReferenciada()
    {
        super();
    }


    /**
     * Gets the chaveacesso.
     *
     * @return the chaveacesso
     */
    /**
     * Gets the chaveacesso.
     *
     * @return the chaveacesso
     */
    public String getChaveAcesso()
    {
        return chaveacesso;
    }

    /**
     * Sets the chaveacesso.
     *
* @param id the chaveacesso to set
 */
public void setChaveAcesso(String chaveacesso)
{
        this.chaveacesso = chaveacesso;
    }

    /**
     * Gets the modelo1por1referenciada.
     *
     * @return the modelo1por1referenciada
     */
    /**
     * Gets the modelo1por1referenciada.
     *
     * @return the modelo1por1referenciada
     */
    public Integer getModelo1por1Referenciada()
    {
        return modelo1por1referenciada;
    }

    /**
     * Sets the modelo1por1referenciada.
     *
* @param id the modelo1por1referenciada to set
 */
public void setModelo1por1Referenciada(Integer modelo1por1referenciada)
{
        this.modelo1por1referenciada = modelo1por1referenciada;
    }

    /**
     * Gets the infonfprodutorruralreferenciada.
     *
     * @return the infonfprodutorruralreferenciada
     */
    /**
     * Gets the infonfprodutorruralreferenciada.
     *
     * @return the infonfprodutorruralreferenciada
     */
    public NFInfoProdutorRuralReferenciada getInfoNFProdutorRuralReferenciada()
    {
        return infonfprodutorruralreferenciada;
    }

    /**
     * Sets the infonfprodutorruralreferenciada.
     *
* @param id the infonfprodutorruralreferenciada to set
 */
public void setInfoNFProdutorRuralReferenciada(NFInfoProdutorRuralReferenciada infonfprodutorruralreferenciada)
{
        this.infonfprodutorruralreferenciada = infonfprodutorruralreferenciada;
    }

    /**
     * Gets the chaveacessoctreferenciada.
     *
     * @return the chaveacessoctreferenciada
     */
    /**
     * Gets the chaveacessoctreferenciada.
     *
     * @return the chaveacessoctreferenciada
     */
    public String getChaveAcessoCTReferenciada()
    {
        return chaveacessoctreferenciada;
    }

    /**
     * Sets the chaveacessoctreferenciada.
     *
* @param id the chaveacessoctreferenciada to set
 */
public void setChaveAcessoCTReferenciada(String chaveacessoctreferenciada)
{
        this.chaveacessoctreferenciada = chaveacessoctreferenciada;
    }

    /**
     * Gets the cupomfiscalreferenciado.
     *
     * @return the cupomfiscalreferenciado
     */
    /**
     * Gets the cupomfiscalreferenciado.
     *
     * @return the cupomfiscalreferenciado
     */
    public NFInfoCupomFiscalReferenciado getCupomFiscalReferenciado()
    {
        return cupomfiscalreferenciado;
    }

    /**
     * Sets the cupomfiscalreferenciado.
     *
* @param id the cupomfiscalreferenciado to set
 */
public void setCupomFiscalReferenciado(NFInfoCupomFiscalReferenciado cupomfiscalreferenciado)
{
        this.cupomfiscalreferenciado = cupomfiscalreferenciado;
    }


	public String getChaveacesso() {
		return chaveacesso;
	}


	public void setChaveacesso(String chaveacesso) {
		this.chaveacesso = chaveacesso;
	}


	public Integer getModelo1por1referenciada() {
		return modelo1por1referenciada;
	}


	public void setModelo1por1referenciada(Integer modelo1por1referenciada) {
		this.modelo1por1referenciada = modelo1por1referenciada;
	}


	public NFInfoProdutorRuralReferenciada getInfonfprodutorruralreferenciada() {
		return infonfprodutorruralreferenciada;
	}


	public void setInfonfprodutorruralreferenciada(NFInfoProdutorRuralReferenciada infonfprodutorruralreferenciada) {
		this.infonfprodutorruralreferenciada = infonfprodutorruralreferenciada;
	}


	public String getChaveacessoctreferenciada() {
		return chaveacessoctreferenciada;
	}


	public void setChaveacessoctreferenciada(String chaveacessoctreferenciada) {
		this.chaveacessoctreferenciada = chaveacessoctreferenciada;
	}


	public NFInfoCupomFiscalReferenciado getCupomfiscalreferenciado() {
		return cupomfiscalreferenciado;
	}


	public void setCupomfiscalreferenciado(NFInfoCupomFiscalReferenciado cupomfiscalreferenciado) {
		this.cupomfiscalreferenciado = cupomfiscalreferenciado;
	}


	@Override
	public String toString() {
		return "NFInfoReferenciada [getChaveAcesso()=" + getChaveAcesso() + ", getModelo1por1Referenciada()="
				+ getModelo1por1Referenciada() + ", getInfoNFProdutorRuralReferenciada()="
				+ getInfoNFProdutorRuralReferenciada() + ", getChaveAcessoCTReferenciada()="
				+ getChaveAcessoCTReferenciada() + ", getCupomFiscalReferenciado()=" + getCupomFiscalReferenciado()
				+ ", getChaveacesso()=" + getChaveacesso() + ", getModelo1por1referenciada()="
				+ getModelo1por1referenciada() + ", getInfonfprodutorruralreferenciada()="
				+ getInfonfprodutorruralreferenciada() + ", getChaveacessoctreferenciada()="
				+ getChaveacessoctreferenciada() + ", getCupomfiscalreferenciado()=" + getCupomfiscalreferenciado()
				+ ", toString()=" + super.toString() + "]";
	}






 }
