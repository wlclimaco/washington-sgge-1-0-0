/** create by system gera-java version 1.0.0 29/09/2016 14:23 : 49*/
package com.qat.samples.sysmgmt.nfeItens.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoISSQNTotal extends ModelCosmeDamiao
{

    /** The econtabil id for the NFNotaInfoISSQNTotal. */
    private Integer id;

    /** The econtabil valorTotalServicosSobNaoIncidenciaNaoTributadosICMS for the NFNotaInfoISSQNTotal. */
    private String valorTotalServicosSobNaoIncidenciaNaoTributadosICMS;

    /** The econtabil baseCalculoISS for the NFNotaInfoISSQNTotal. */
    private String baseCalculoISS;

    /** The econtabil valorTotalISS for the NFNotaInfoISSQNTotal. */
    private String valorTotalISS;

    /** The econtabil valorPISsobreServicos for the NFNotaInfoISSQNTotal. */
    private String valorPISsobreServicos;

    /** The econtabil valorCOFINSsobreServicos for the NFNotaInfoISSQNTotal. */
    private String valorCOFINSsobreServicos;

    /** The econtabil dataPrestacaoServico for the NFNotaInfoISSQNTotal. */
    private Long dataPrestacaoServico;

    /** The econtabil valorDeducao for the NFNotaInfoISSQNTotal. */
    private String valorDeducao;

    /** The econtabil valorOutros for the NFNotaInfoISSQNTotal. */
    private String valorOutros;

    /** The econtabil valorTotalDescontoIncondicionado for the NFNotaInfoISSQNTotal. */
    private String valorTotalDescontoIncondicionado;

    /** The econtabil valorTotalDescontoCondicionado for the NFNotaInfoISSQNTotal. */
    private String valorTotalDescontoCondicionado;

    /** The econtabil valorTotalRetencaoISS for the NFNotaInfoISSQNTotal. */
    private String valorTotalRetencaoISS;

    /** The econtabil tributacao for the NFNotaInfoISSQNTotal. */
    private DoisValores tributacao;



    /**
     * Default constructor.
     */
    public NFNotaInfoISSQNTotal()
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
     * Gets the valorTotalServicosSobNaoIncidenciaNaoTributadosICMS.
     *
     * @return the valorTotalServicosSobNaoIncidenciaNaoTributadosICMS
     */
    /**
     * Gets the valorTotalServicosSobNaoIncidenciaNaoTributadosICMS.
     *
     * @return the valorTotalServicosSobNaoIncidenciaNaoTributadosICMS
     */
    public String getValorTotalServicosSobNaoIncidenciaNaoTributadosICMS()
    {
        return valorTotalServicosSobNaoIncidenciaNaoTributadosICMS;
    }

    /**
     * Sets the valortotalservicossobnaoincidencianaotributadosicms.
     *
* @param id the valortotalservicossobnaoincidencianaotributadosicms to set
 */
public void setValorTotalServicosSobNaoIncidenciaNaoTributadosICMS(String valortotalservicossobnaoincidencianaotributadosicms)
{
        this.valorTotalServicosSobNaoIncidenciaNaoTributadosICMS = valortotalservicossobnaoincidencianaotributadosicms;
    }

    /**
     * Gets the baseCalculoISS.
     *
     * @return the baseCalculoISS
     */
    /**
     * Gets the baseCalculoISS.
     *
     * @return the baseCalculoISS
     */
    public String getBaseCalculoISS()
    {
        return baseCalculoISS;
    }

    /**
     * Sets the basecalculoiss.
     *
* @param id the basecalculoiss to set
 */
public void setBaseCalculoISS(String basecalculoiss)
{
        this.baseCalculoISS = basecalculoiss;
    }

    /**
     * Gets the valorTotalISS.
     *
     * @return the valorTotalISS
     */
    /**
     * Gets the valorTotalISS.
     *
     * @return the valorTotalISS
     */
    public String getValorTotalISS()
    {
        return valorTotalISS;
    }

    /**
     * Sets the valortotaliss.
     *
* @param id the valortotaliss to set
 */
public void setValorTotalISS(String valortotaliss)
{
        this.valorTotalISS = valortotaliss;
    }

    /**
     * Gets the valorPISsobreServicos.
     *
     * @return the valorPISsobreServicos
     */
    /**
     * Gets the valorPISsobreServicos.
     *
     * @return the valorPISsobreServicos
     */
    public String getValorPISsobreServicos()
    {
        return valorPISsobreServicos;
    }

    /**
     * Sets the valorpissobreservicos.
     *
* @param id the valorpissobreservicos to set
 */
public void setValorPISsobreServicos(String valorpissobreservicos)
{
        this.valorPISsobreServicos = valorpissobreservicos;
    }

    /**
     * Gets the valorCOFINSsobreServicos.
     *
     * @return the valorCOFINSsobreServicos
     */
    /**
     * Gets the valorCOFINSsobreServicos.
     *
     * @return the valorCOFINSsobreServicos
     */
    public String getValorCOFINSsobreServicos()
    {
        return valorCOFINSsobreServicos;
    }

    /**
     * Sets the valorcofinssobreservicos.
     *
* @param id the valorcofinssobreservicos to set
 */
public void setValorCOFINSsobreServicos(String valorcofinssobreservicos)
{
        this.valorCOFINSsobreServicos = valorcofinssobreservicos;
    }

    /**
     * Gets the dataPrestacaoServico.
     *
     * @return the dataPrestacaoServico
     */
    /**
     * Gets the dataPrestacaoServico.
     *
     * @return the dataPrestacaoServico
     */
    public Long getDataPrestacaoServico()
    {
        return dataPrestacaoServico;
    }

    /**
     * Sets the dataprestacaoservico.
     *
* @param id the dataprestacaoservico to set
 */
public void setDataPrestacaoServico(Long dataprestacaoservico)
{
        this.dataPrestacaoServico = dataprestacaoservico;
    }

    /**
     * Gets the valorDeducao.
     *
     * @return the valorDeducao
     */
    /**
     * Gets the valorDeducao.
     *
     * @return the valorDeducao
     */
    public String getValorDeducao()
    {
        return valorDeducao;
    }

    /**
     * Sets the valordeducao.
     *
* @param id the valordeducao to set
 */
public void setValorDeducao(String valordeducao)
{
        this.valorDeducao = valordeducao;
    }

    /**
     * Gets the valorOutros.
     *
     * @return the valorOutros
     */
    /**
     * Gets the valorOutros.
     *
     * @return the valorOutros
     */
    public String getValorOutros()
    {
        return valorOutros;
    }

    /**
     * Sets the valoroutros.
     *
* @param id the valoroutros to set
 */
public void setValorOutros(String valoroutros)
{
        this.valorOutros = valoroutros;
    }

    /**
     * Gets the valorTotalDescontoIncondicionado.
     *
     * @return the valorTotalDescontoIncondicionado
     */
    /**
     * Gets the valorTotalDescontoIncondicionado.
     *
     * @return the valorTotalDescontoIncondicionado
     */
    public String getValorTotalDescontoIncondicionado()
    {
        return valorTotalDescontoIncondicionado;
    }

    /**
     * Sets the valortotaldescontoincondicionado.
     *
* @param id the valortotaldescontoincondicionado to set
 */
public void setValorTotalDescontoIncondicionado(String valortotaldescontoincondicionado)
{
        this.valorTotalDescontoIncondicionado = valortotaldescontoincondicionado;
    }

    /**
     * Gets the valorTotalDescontoCondicionado.
     *
     * @return the valorTotalDescontoCondicionado
     */
    /**
     * Gets the valorTotalDescontoCondicionado.
     *
     * @return the valorTotalDescontoCondicionado
     */
    public String getValorTotalDescontoCondicionado()
    {
        return valorTotalDescontoCondicionado;
    }

    /**
     * Sets the valortotaldescontocondicionado.
     *
* @param id the valortotaldescontocondicionado to set
 */
public void setValorTotalDescontoCondicionado(String valortotaldescontocondicionado)
{
        this.valorTotalDescontoCondicionado = valortotaldescontocondicionado;
    }

    /**
     * Gets the valorTotalRetencaoISS.
     *
     * @return the valorTotalRetencaoISS
     */
    /**
     * Gets the valorTotalRetencaoISS.
     *
     * @return the valorTotalRetencaoISS
     */
    public String getValorTotalRetencaoISS()
    {
        return valorTotalRetencaoISS;
    }

    /**
     * Sets the valortotalretencaoiss.
     *
* @param id the valortotalretencaoiss to set
 */
public void setValorTotalRetencaoISS(String valortotalretencaoiss)
{
        this.valorTotalRetencaoISS = valortotalretencaoiss;
    }

    /**
     * Gets the tributacao.
     *
     * @return the tributacao
     */
    /**
     * Gets the tributacao.
     *
     * @return the tributacao
     */
    public DoisValores getTributacao()
    {
        return tributacao;
    }

    /**
     * Sets the tributacao.
     *
* @param id the tributacao to set
 */
public void setTributacao(DoisValores tributacao)
{
        this.tributacao = tributacao;
    }


	@Override
	public String toString() {
		return "NFNotaInfoISSQNTotal [getId()=" + getId()
				+ ", getValorTotalServicosSobNaoIncidenciaNaoTributadosICMS()="
				+ getValorTotalServicosSobNaoIncidenciaNaoTributadosICMS() + ", getBaseCalculoISS()="
				+ getBaseCalculoISS() + ", getValorTotalISS()=" + getValorTotalISS() + ", getValorPISsobreServicos()="
				+ getValorPISsobreServicos() + ", getValorCOFINSsobreServicos()=" + getValorCOFINSsobreServicos()
				+ ", getDataPrestacaoServico()=" + getDataPrestacaoServico() + ", getValorDeducao()="
				+ getValorDeducao() + ", getValorOutros()=" + getValorOutros()
				+ ", getValorTotalDescontoIncondicionado()=" + getValorTotalDescontoIncondicionado()
				+ ", getValorTotalDescontoCondicionado()=" + getValorTotalDescontoCondicionado()
				+ ", getValorTotalRetencaoISS()=" + getValorTotalRetencaoISS() + ", getTributacao()=" + getTributacao()
				+ ", toString()=" + super.toString() + "]";
	}







 }
