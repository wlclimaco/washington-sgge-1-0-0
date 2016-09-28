/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCanaFornecimentoDiario extends NFBase
{

    /** The econtabil dia for the NFNotaInfoCanaFornecimentoDiario. */
    private Integer dia;

    /** The econtabil quantidade for the NFNotaInfoCanaFornecimentoDiario. */
    private Double quantidade;



    /**
     * Default constructor.
     */
    public NFNotaInfoCanaFornecimentoDiario()
    {
        super();
    }


    /**
     * Gets the dia.
     *
     * @return the dia
     */
    /**
     * Gets the dia.
     *
     * @return the dia
     */
    public Integer getDia()
    {
        return dia;
    }

    /**
     * Sets the dia.
     *
* @param id the dia to set
 */
public void setDia(Integer dia)
{
        this.dia = dia;
    }

    /**
     * Gets the quantidade.
     *
     * @return the quantidade
     */
    /**
     * Gets the quantidade.
     *
     * @return the quantidade
     */
    public Double getQuantidade()
    {
        return quantidade;
    }

    /**
     * Sets the quantidade.
     *
* @param id the quantidade to set
 */
public void setQuantidade(Double quantidade)
{
        this.quantidade = quantidade;
    }


	@Override
	public String toString() {
		return "NFNotaInfoCanaFornecimentoDiario [getDia()=" + getDia() + ", getQuantidade()=" + getQuantidade()
				+ ", toString()=" + super.toString() + "]";
	}





 }
