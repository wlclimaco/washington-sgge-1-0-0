/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;


import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFImpostoDevolvido extends ModelCosmeDamiao
{

    /** The econtabil id for the NFImpostoDevolvido. */
    private Integer id;

    /** The econtabil percentualDevolucao for the NFImpostoDevolvido. */
    private String percentualDevolucao;

    /** The econtabil informacaoIPIDevolvido for the NFImpostoDevolvido. */
    private NFInformacaoImpostoDevolvido informacaoIPIDevolvido;



    /**
     * Default constructor.
     */
    public NFImpostoDevolvido()
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
     * Gets the percentualDevolucao.
     *
     * @return the percentualDevolucao
     */
    /**
     * Gets the percentualDevolucao.
     *
     * @return the percentualDevolucao
     */
    public String getPercentualDevolucao()
    {
        return percentualDevolucao;
    }

    /**
     * Sets the percentualdevolucao.
     *
* @param id the percentualdevolucao to set
 */
public void setPercentualDevolucao(String percentualdevolucao)
{
        this.percentualDevolucao = percentualdevolucao;
    }

    /**
     * Gets the informacaoIPIDevolvido.
     *
     * @return the informacaoIPIDevolvido
     */
    /**
     * Gets the informacaoIPIDevolvido.
     *
     * @return the informacaoIPIDevolvido
     */
    public NFInformacaoImpostoDevolvido getInformacaoIPIDevolvido()
    {
        return informacaoIPIDevolvido;
    }

    /**
     * Sets the informacaoipidevolvido.
     *
* @param id the informacaoipidevolvido to set
 */
public void setInformacaoIPIDevolvido(NFInformacaoImpostoDevolvido informacaoipidevolvido)
{
        this.informacaoIPIDevolvido = informacaoipidevolvido;
    }


	@Override
	public String toString() {
		return "NFImpostoDevolvido [getId()=" + getId() + ", getPercentualDevolucao()=" + getPercentualDevolucao()
				+ ", toString()=" + super.toString() + "]";
	}






 
 }
