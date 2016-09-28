/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCanaDeducao extends NFBase
{

    /** The econtabil descricaoDeducao for the NFNotaInfoCanaDeducao. */
    private String descricaodeducao;

    /** The econtabil valorDeducao for the NFNotaInfoCanaDeducao. */
    private Double valordeducao;



    /**
     * Default constructor.
     */
    public NFNotaInfoCanaDeducao()
    {
        super();
    }


    /**
     * Gets the descricaodeducao.
     *
     * @return the descricaodeducao
     */
    /**
     * Gets the descricaodeducao.
     *
     * @return the descricaodeducao
     */
    public String getDescricaoDeducao()
    {
        return descricaodeducao;
    }

    /**
     * Sets the descricaodeducao.
     *
* @param id the descricaodeducao to set
 */
public void setDescricaoDeducao(String descricaodeducao)
{
        this.descricaodeducao = descricaodeducao;
    }

    /**
     * Gets the valordeducao.
     *
     * @return the valordeducao
     */
    /**
     * Gets the valordeducao.
     *
     * @return the valordeducao
     */
    public Double getValorDeducao()
    {
        return valordeducao;
    }

    /**
     * Sets the valordeducao.
     *
* @param id the valordeducao to set
 */
public void setValorDeducao(Double valordeducao)
{
        this.valordeducao = valordeducao;
    }


	public String getDescricaodeducao() {
		return descricaodeducao;
	}


	public void setDescricaodeducao(String descricaodeducao) {
		this.descricaodeducao = descricaodeducao;
	}


	public Double getValordeducao() {
		return valordeducao;
	}


	public void setValordeducao(Double valordeducao) {
		this.valordeducao = valordeducao;
	}


	@Override
	public String toString() {
		return "NFNotaInfoCanaDeducao [getDescricaoDeducao()=" + getDescricaoDeducao() + ", getValorDeducao()="
				+ getValorDeducao() + ", getDescricaodeducao()=" + getDescricaodeducao() + ", getValordeducao()="
				+ getValordeducao() + ", toString()=" + super.toString() + "]";
	}







 }
