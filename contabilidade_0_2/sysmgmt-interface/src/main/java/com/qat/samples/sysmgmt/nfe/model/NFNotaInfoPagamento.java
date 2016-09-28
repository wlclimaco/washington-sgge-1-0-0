/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoPagamento extends NFBase
{

    /** The econtabil formaPagamentoMoeda for the NFNotaInfoPagamento. */
    private DoisValores formapagamentomoeda;

    /** The econtabil valorPagamento for the NFNotaInfoPagamento. */
    private String valorpagamento;

    /** The econtabil cartao for the NFNotaInfoPagamento. */
    private NFNotaInfoCartao cartao;



    /**
     * Default constructor.
     */
    public NFNotaInfoPagamento()
    {
        super();
    }


    /**
     * Gets the formapagamentomoeda.
     *
     * @return the formapagamentomoeda
     */
    /**
     * Gets the formapagamentomoeda.
     *
     * @return the formapagamentomoeda
     */
    public DoisValores getFormaPagamentoMoeda()
    {
        return formapagamentomoeda;
    }

    /**
     * Sets the formapagamentomoeda.
     *
* @param id the formapagamentomoeda to set
 */
public void setFormaPagamentoMoeda(DoisValores formapagamentomoeda)
{
        this.formapagamentomoeda = formapagamentomoeda;
    }

    /**
     * Gets the valorpagamento.
     *
     * @return the valorpagamento
     */
    /**
     * Gets the valorpagamento.
     *
     * @return the valorpagamento
     */
    public String getValorPagamento()
    {
        return valorpagamento;
    }

    /**
     * Sets the valorpagamento.
     *
* @param id the valorpagamento to set
 */
public void setValorPagamento(String valorpagamento)
{
        this.valorpagamento = valorpagamento;
    }

    /**
     * Gets the cartao.
     *
     * @return the cartao
     */
    /**
     * Gets the cartao.
     *
     * @return the cartao
     */
    public NFNotaInfoCartao getCartao()
    {
        return cartao;
    }

    /**
     * Sets the cartao.
     *
* @param id the cartao to set
 */
public void setCartao(NFNotaInfoCartao cartao)
{
        this.cartao = cartao;
    }


	public DoisValores getFormapagamentomoeda() {
		return formapagamentomoeda;
	}


	public void setFormapagamentomoeda(DoisValores formapagamentomoeda) {
		this.formapagamentomoeda = formapagamentomoeda;
	}


	public String getValorpagamento() {
		return valorpagamento;
	}


	public void setValorpagamento(String valorpagamento) {
		this.valorpagamento = valorpagamento;
	}


	@Override
	public String toString() {
		return "NFNotaInfoPagamento [getFormaPagamentoMoeda()=" + getFormaPagamentoMoeda() + ", getValorPagamento()="
				+ getValorPagamento() + ", getCartao()=" + getCartao() + ", getFormapagamentomoeda()="
				+ getFormapagamentomoeda() + ", getValorpagamento()=" + getValorpagamento() + ", toString()="
				+ super.toString() + "]";
	}





 }
