/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCana extends NFBase
{

    /** The econtabil safra for the NFNotaInfoCana. */
    private String safra;

    /** The econtabil referencia for the NFNotaInfoCana. */
    private String referencia;

    /** The econtabil fornecimentosDiario for the NFNotaInfoCana. */
    private List<List<NFNotaInfoCanaFornecimentoDiario>> fornecimentosdiario;

    /** The econtabil deducoes for the NFNotaInfoCana. */
    private List<List<NFNotaInfoCanaDeducao>> deducoes;

    /** The econtabil quantidadeTotalMes for the NFNotaInfoCana. */
    private String quantidadetotalmes;

    /** The econtabil quantidadeTotalAnterior for the NFNotaInfoCana. */
    private String quantidadetotalanterior;

    /** The econtabil quantidadeTotalGeral for the NFNotaInfoCana. */
    private String quantidadetotalgeral;

    /** The econtabil valorFornecimento for the NFNotaInfoCana. */
    private String valorfornecimento;

    /** The econtabil valorTotalDeducao for the NFNotaInfoCana. */
    private String valortotaldeducao;

    /** The econtabil valorLiquidoFornecimento for the NFNotaInfoCana. */
    private String valorliquidofornecimento;



    /**
     * Default constructor.
     */
    public NFNotaInfoCana()
    {
        super();
    }


    /**
     * Gets the safra.
     *
     * @return the safra
     */
    /**
     * Gets the safra.
     *
     * @return the safra
     */
    public String getSafra()
    {
        return safra;
    }

    /**
     * Sets the safra.
     *
* @param id the safra to set
 */
public void setSafra(String safra)
{
        this.safra = safra;
    }

    /**
     * Gets the referencia.
     *
     * @return the referencia
     */
    /**
     * Gets the referencia.
     *
     * @return the referencia
     */
    public String getReferencia()
    {
        return referencia;
    }

    /**
     * Sets the referencia.
     *
* @param id the referencia to set
 */
public void setReferencia(String referencia)
{
        this.referencia = referencia;
    }

    /**
     * Gets the fornecimentosdiario.
     *
     * @return the fornecimentosdiario
     */
    /**
     * Gets the fornecimentosdiario.
     *
     * @return the fornecimentosdiario
     */
    public List<List<NFNotaInfoCanaFornecimentoDiario>> getFornecimentosDiario()
    {
        return fornecimentosdiario;
    }

    /**
     * Sets the fornecimentosdiario.
     *
* @param id the fornecimentosdiario to set
 */
public void setFornecimentosDiario(List<List<NFNotaInfoCanaFornecimentoDiario>> fornecimentosdiario)
{
        this.fornecimentosdiario = fornecimentosdiario;
    }

    /**
     * Gets the deducoes.
     *
     * @return the deducoes
     */
    /**
     * Gets the deducoes.
     *
     * @return the deducoes
     */
    public List<List<NFNotaInfoCanaDeducao>> getDeducoes()
    {
        return deducoes;
    }

    /**
     * Sets the deducoes.
     *
* @param id the deducoes to set
 */
public void setDeducoes(List<List<NFNotaInfoCanaDeducao>> deducoes)
{
        this.deducoes = deducoes;
    }

    /**
     * Gets the quantidadetotalmes.
     *
     * @return the quantidadetotalmes
     */
    /**
     * Gets the quantidadetotalmes.
     *
     * @return the quantidadetotalmes
     */
    public String getQuantidadeTotalMes()
    {
        return quantidadetotalmes;
    }

    /**
     * Sets the quantidadetotalmes.
     *
* @param id the quantidadetotalmes to set
 */
public void setQuantidadeTotalMes(String quantidadetotalmes)
{
        this.quantidadetotalmes = quantidadetotalmes;
    }

    /**
     * Gets the quantidadetotalanterior.
     *
     * @return the quantidadetotalanterior
     */
    /**
     * Gets the quantidadetotalanterior.
     *
     * @return the quantidadetotalanterior
     */
    public String getQuantidadeTotalAnterior()
    {
        return quantidadetotalanterior;
    }

    /**
     * Sets the quantidadetotalanterior.
     *
* @param id the quantidadetotalanterior to set
 */
public void setQuantidadeTotalAnterior(String quantidadetotalanterior)
{
        this.quantidadetotalanterior = quantidadetotalanterior;
    }

    /**
     * Gets the quantidadetotalgeral.
     *
     * @return the quantidadetotalgeral
     */
    /**
     * Gets the quantidadetotalgeral.
     *
     * @return the quantidadetotalgeral
     */
    public String getQuantidadeTotalGeral()
    {
        return quantidadetotalgeral;
    }

    /**
     * Sets the quantidadetotalgeral.
     *
* @param id the quantidadetotalgeral to set
 */
public void setQuantidadeTotalGeral(String quantidadetotalgeral)
{
        this.quantidadetotalgeral = quantidadetotalgeral;
    }

    /**
     * Gets the valorfornecimento.
     *
     * @return the valorfornecimento
     */
    /**
     * Gets the valorfornecimento.
     *
     * @return the valorfornecimento
     */
    public String getValorFornecimento()
    {
        return valorfornecimento;
    }

    /**
     * Sets the valorfornecimento.
     *
* @param id the valorfornecimento to set
 */
public void setValorFornecimento(String valorfornecimento)
{
        this.valorfornecimento = valorfornecimento;
    }

    /**
     * Gets the valortotaldeducao.
     *
     * @return the valortotaldeducao
     */
    /**
     * Gets the valortotaldeducao.
     *
     * @return the valortotaldeducao
     */
    public String getValorTotalDeducao()
    {
        return valortotaldeducao;
    }

    /**
     * Sets the valortotaldeducao.
     *
* @param id the valortotaldeducao to set
 */
public void setValorTotalDeducao(String valortotaldeducao)
{
        this.valortotaldeducao = valortotaldeducao;
    }

    /**
     * Gets the valorliquidofornecimento.
     *
     * @return the valorliquidofornecimento
     */
    /**
     * Gets the valorliquidofornecimento.
     *
     * @return the valorliquidofornecimento
     */
    public String getValorLiquidoFornecimento()
    {
        return valorliquidofornecimento;
    }

    /**
     * Sets the valorliquidofornecimento.
     *
* @param id the valorliquidofornecimento to set
 */
public void setValorLiquidoFornecimento(String valorliquidofornecimento)
{
        this.valorliquidofornecimento = valorliquidofornecimento;
    }


	public List<List<NFNotaInfoCanaFornecimentoDiario>> getFornecimentosdiario() {
		return fornecimentosdiario;
	}


	public void setFornecimentosdiario(List<List<NFNotaInfoCanaFornecimentoDiario>> fornecimentosdiario) {
		this.fornecimentosdiario = fornecimentosdiario;
	}


	public String getQuantidadetotalmes() {
		return quantidadetotalmes;
	}


	public void setQuantidadetotalmes(String quantidadetotalmes) {
		this.quantidadetotalmes = quantidadetotalmes;
	}


	public String getQuantidadetotalanterior() {
		return quantidadetotalanterior;
	}


	public void setQuantidadetotalanterior(String quantidadetotalanterior) {
		this.quantidadetotalanterior = quantidadetotalanterior;
	}


	public String getQuantidadetotalgeral() {
		return quantidadetotalgeral;
	}


	public void setQuantidadetotalgeral(String quantidadetotalgeral) {
		this.quantidadetotalgeral = quantidadetotalgeral;
	}


	public String getValorfornecimento() {
		return valorfornecimento;
	}


	public void setValorfornecimento(String valorfornecimento) {
		this.valorfornecimento = valorfornecimento;
	}


	public String getValortotaldeducao() {
		return valortotaldeducao;
	}


	public void setValortotaldeducao(String valortotaldeducao) {
		this.valortotaldeducao = valortotaldeducao;
	}


	public String getValorliquidofornecimento() {
		return valorliquidofornecimento;
	}


	public void setValorliquidofornecimento(String valorliquidofornecimento) {
		this.valorliquidofornecimento = valorliquidofornecimento;
	}


	@Override
	public String toString() {
		return "NFNotaInfoCana [getSafra()=" + getSafra() + ", getReferencia()=" + getReferencia()
				+ ", getFornecimentosDiario()=" + getFornecimentosDiario() + ", getDeducoes()=" + getDeducoes()
				+ ", getQuantidadeTotalMes()=" + getQuantidadeTotalMes() + ", getQuantidadeTotalAnterior()="
				+ getQuantidadeTotalAnterior() + ", getQuantidadeTotalGeral()=" + getQuantidadeTotalGeral()
				+ ", getValorFornecimento()=" + getValorFornecimento() + ", getValorTotalDeducao()="
				+ getValorTotalDeducao() + ", getValorLiquidoFornecimento()=" + getValorLiquidoFornecimento()
				+ ", getFornecimentosdiario()=" + getFornecimentosdiario() + ", getQuantidadetotalmes()="
				+ getQuantidadetotalmes() + ", getQuantidadetotalanterior()=" + getQuantidadetotalanterior()
				+ ", getQuantidadetotalgeral()=" + getQuantidadetotalgeral() + ", getValorfornecimento()="
				+ getValorfornecimento() + ", getValortotaldeducao()=" + getValortotaldeducao()
				+ ", getValorliquidofornecimento()=" + getValorliquidofornecimento() + ", toString()="
				+ super.toString() + "]";
	}



 }
