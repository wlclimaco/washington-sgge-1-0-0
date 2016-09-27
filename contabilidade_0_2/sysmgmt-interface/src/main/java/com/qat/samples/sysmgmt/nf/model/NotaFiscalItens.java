/** create by system gera-java version 1.0.0 27/09/2016 12:23 : 27*/
package com.qat.samples.sysmgmt.nf.model;


import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NotaFiscalItens extends ModelCosmeDamiao
{

    /** The econtabil id for the NotaFiscalItens. */
    private Integer id;

    /** The econtabil dataInicio for the NotaFiscalItens. */
    private Long datainicio;

    /** The econtabil valor for the NotaFiscalItens. */
    private Double valor;

    /** The econtabil servicoPlanoEnumValue for the NotaFiscalItens. */
    private Integer servicoplanoenumvalue;

    /** The econtabil servicoList for the NotaFiscalItens. */
    private Servico servicolist;

    /** The econtabil planoList for the NotaFiscalItens. */
    private Plano planolist;

    /** The econtabil numeroRegistro for the NotaFiscalItens. */
    private String numeroregistro;

    /** The econtabil dataRegistro for the NotaFiscalItens. */
    private Long dataregistro;

    /** The econtabil localDesembaraco for the NotaFiscalItens. */
    private String localdesembaraco;

    /** The econtabil ufDesembaraco for the NotaFiscalItens. */
    private Estado ufdesembaraco;

    /** The econtabil dataDesembaraco for the NotaFiscalItens. */
    private Long datadesembaraco;

    /** The econtabil transporteInternacional for the NotaFiscalItens. */
    private Integer transporteinternacional;

    /** The econtabil valorAFRMM for the NotaFiscalItens. */
    private Double valorafrmm;

    /** The econtabil tpIntermedio for the NotaFiscalItens. */
    private Integer tpintermedio;

    /** The econtabil cnpj for the NotaFiscalItens. */
    private String cnpj;

    /** The econtabil ufTerceiro for the NotaFiscalItens. */
    private Integer ufterceiro;

    /** The econtabil codigoExportador for the NotaFiscalItens. */
    private Integer codigoexportador;

    /** The econtabil descricao for the NotaFiscalItens. */
    private String descricao;

    /** The econtabil produto for the NotaFiscalItens. */
    private ProdutoEmpresa produto;

    /** The econtabil cfop for the NotaFiscalItens. */
    private Cfop cfop;

    /** The econtabil valorUnitario for the NotaFiscalItens. */
    private Double valorunitario;

    /** The econtabil valorTotalBruto for the NotaFiscalItens. */
    private Double valortotalbruto;

    /** The econtabil valorFrete for the NotaFiscalItens. */
    private Double valorfrete;

    /** The econtabil valorSeguro for the NotaFiscalItens. */
    private Double valorseguro;

    /** The econtabil valorDesconto for the NotaFiscalItens. */
    private Double valordesconto;

    /** The econtabil valorOutrasDespesasAcessorias for the NotaFiscalItens. */
    private Double valoroutrasdespesasacessorias;

    /** The econtabil numeroRECOPI for the NotaFiscalItens. */
    private Integer numerorecopi;



    /**
     * Default constructor.
     */
    public NotaFiscalItens()
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
     * Gets the datainicio.
     *
     * @return the datainicio
     */
    /**
     * Gets the datainicio.
     *
     * @return the datainicio
     */
    public Long getDataInicio()
    {
        return datainicio;
    }

    /**
     * Sets the datainicio.
     *
* @param id the datainicio to set
 */
public void setDataInicio(Long datainicio)
{
        this.datainicio = datainicio;
    }

    /**
     * Gets the valor.
     *
     * @return the valor
     */
    /**
     * Gets the valor.
     *
     * @return the valor
     */
    public Double getValor()
    {
        return valor;
    }

    /**
     * Sets the valor.
     *
* @param id the valor to set
 */
public void setValor(Double valor)
{
        this.valor = valor;
    }

    /**
     * Gets the servicoplanoenumvalue.
     *
     * @return the servicoplanoenumvalue
     */
    /**
     * Gets the servicoplanoenumvalue.
     *
     * @return the servicoplanoenumvalue
     */
    public Integer getServicoPlanoEnumValue()
    {
        return servicoplanoenumvalue;
    }

    /**
     * Sets the servicoplanoenumvalue.
     *
* @param id the servicoplanoenumvalue to set
 */
public void setServicoPlanoEnumValue(Integer servicoplanoenumvalue)
{
        this.servicoplanoenumvalue = servicoplanoenumvalue;
    }

    /**
     * Gets the servicolist.
     *
     * @return the servicolist
     */
    /**
     * Gets the servicolist.
     *
     * @return the servicolist
     */
    public Servico getServicoList()
    {
        return servicolist;
    }

    /**
     * Sets the servicolist.
     *
* @param id the servicolist to set
 */
public void setServicoList(Servico servicolist)
{
        this.servicolist = servicolist;
    }

    /**
     * Gets the planolist.
     *
     * @return the planolist
     */
    /**
     * Gets the planolist.
     *
     * @return the planolist
     */
    public Plano getPlanoList()
    {
        return planolist;
    }

    /**
     * Sets the planolist.
     *
* @param id the planolist to set
 */
public void setPlanoList(Plano planolist)
{
        this.planolist = planolist;
    }

    /**
     * Gets the numeroregistro.
     *
     * @return the numeroregistro
     */
    /**
     * Gets the numeroregistro.
     *
     * @return the numeroregistro
     */
    public String getNumeroRegistro()
    {
        return numeroregistro;
    }

    /**
     * Sets the numeroregistro.
     *
* @param id the numeroregistro to set
 */
public void setNumeroRegistro(String numeroregistro)
{
        this.numeroregistro = numeroregistro;
    }

    /**
     * Gets the dataregistro.
     *
     * @return the dataregistro
     */
    /**
     * Gets the dataregistro.
     *
     * @return the dataregistro
     */
    public Long getDataRegistro()
    {
        return dataregistro;
    }

    /**
     * Sets the dataregistro.
     *
* @param id the dataregistro to set
 */
public void setDataRegistro(Long dataregistro)
{
        this.dataregistro = dataregistro;
    }

    /**
     * Gets the localdesembaraco.
     *
     * @return the localdesembaraco
     */
    /**
     * Gets the localdesembaraco.
     *
     * @return the localdesembaraco
     */
    public String getLocalDesembaraco()
    {
        return localdesembaraco;
    }

    /**
     * Sets the localdesembaraco.
     *
* @param id the localdesembaraco to set
 */
public void setLocalDesembaraco(String localdesembaraco)
{
        this.localdesembaraco = localdesembaraco;
    }

    /**
     * Gets the ufdesembaraco.
     *
     * @return the ufdesembaraco
     */
    /**
     * Gets the ufdesembaraco.
     *
     * @return the ufdesembaraco
     */
    public Estado getUfDesembaraco()
    {
        return ufdesembaraco;
    }

    /**
     * Sets the ufdesembaraco.
     *
* @param id the ufdesembaraco to set
 */
public void setUfDesembaraco(Estado ufdesembaraco)
{
        this.ufdesembaraco = ufdesembaraco;
    }

    /**
     * Gets the datadesembaraco.
     *
     * @return the datadesembaraco
     */
    /**
     * Gets the datadesembaraco.
     *
     * @return the datadesembaraco
     */
    public Long getDataDesembaraco()
    {
        return datadesembaraco;
    }

    /**
     * Sets the datadesembaraco.
     *
* @param id the datadesembaraco to set
 */
public void setDataDesembaraco(Long datadesembaraco)
{
        this.datadesembaraco = datadesembaraco;
    }

    /**
     * Gets the transporteinternacional.
     *
     * @return the transporteinternacional
     */
    /**
     * Gets the transporteinternacional.
     *
     * @return the transporteinternacional
     */
    public Integer getTransporteInternacional()
    {
        return transporteinternacional;
    }

    /**
     * Sets the transporteinternacional.
     *
* @param id the transporteinternacional to set
 */
public void setTransporteInternacional(Integer transporteinternacional)
{
        this.transporteinternacional = transporteinternacional;
    }

    /**
     * Gets the valorafrmm.
     *
     * @return the valorafrmm
     */
    /**
     * Gets the valorafrmm.
     *
     * @return the valorafrmm
     */
    public Double getValorAFRMM()
    {
        return valorafrmm;
    }

    /**
     * Sets the valorafrmm.
     *
* @param id the valorafrmm to set
 */
public void setValorAFRMM(Double valorafrmm)
{
        this.valorafrmm = valorafrmm;
    }

    /**
     * Gets the tpintermedio.
     *
     * @return the tpintermedio
     */
    /**
     * Gets the tpintermedio.
     *
     * @return the tpintermedio
     */
    public Integer getTpIntermedio()
    {
        return tpintermedio;
    }

    /**
     * Sets the tpintermedio.
     *
* @param id the tpintermedio to set
 */
public void setTpIntermedio(Integer tpintermedio)
{
        this.tpintermedio = tpintermedio;
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
     * Gets the ufterceiro.
     *
     * @return the ufterceiro
     */
    /**
     * Gets the ufterceiro.
     *
     * @return the ufterceiro
     */
    public Integer getUfTerceiro()
    {
        return ufterceiro;
    }

    /**
     * Sets the ufterceiro.
     *
* @param id the ufterceiro to set
 */
public void setUfTerceiro(Integer ufterceiro)
{
        this.ufterceiro = ufterceiro;
    }

    /**
     * Gets the codigoexportador.
     *
     * @return the codigoexportador
     */
    /**
     * Gets the codigoexportador.
     *
     * @return the codigoexportador
     */
    public Integer getCodigoExportador()
    {
        return codigoexportador;
    }

    /**
     * Sets the codigoexportador.
     *
* @param id the codigoexportador to set
 */
public void setCodigoExportador(Integer codigoexportador)
{
        this.codigoexportador = codigoexportador;
    }

    /**
     * Gets the descricao.
     *
     * @return the descricao
     */
    /**
     * Gets the descricao.
     *
     * @return the descricao
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * Sets the descricao.
     *
* @param id the descricao to set
 */
public void setDescricao(String descricao)
{
        this.descricao = descricao;
    }

    /**
     * Gets the produto.
     *
     * @return the produto
     */
    /**
     * Gets the produto.
     *
     * @return the produto
     */
    public ProdutoEmpresa getProduto()
    {
        return produto;
    }

    /**
     * Sets the produto.
     *
* @param id the produto to set
 */
public void setProduto(ProdutoEmpresa produto)
{
        this.produto = produto;
    }

    /**
     * Gets the cfop.
     *
     * @return the cfop
     */
    /**
     * Gets the cfop.
     *
     * @return the cfop
     */
    public Cfop getCfop()
    {
        return cfop;
    }

    /**
     * Sets the cfop.
     *
* @param id the cfop to set
 */
public void setCfop(Cfop cfop)
{
        this.cfop = cfop;
    }

    /**
     * Gets the valorunitario.
     *
     * @return the valorunitario
     */
    /**
     * Gets the valorunitario.
     *
     * @return the valorunitario
     */
    public Double getValorUnitario()
    {
        return valorunitario;
    }

    /**
     * Sets the valorunitario.
     *
* @param id the valorunitario to set
 */
public void setValorUnitario(Double valorunitario)
{
        this.valorunitario = valorunitario;
    }

    /**
     * Gets the valortotalbruto.
     *
     * @return the valortotalbruto
     */
    /**
     * Gets the valortotalbruto.
     *
     * @return the valortotalbruto
     */
    public Double getValorTotalBruto()
    {
        return valortotalbruto;
    }

    /**
     * Sets the valortotalbruto.
     *
* @param id the valortotalbruto to set
 */
public void setValorTotalBruto(Double valortotalbruto)
{
        this.valortotalbruto = valortotalbruto;
    }

    /**
     * Gets the valorfrete.
     *
     * @return the valorfrete
     */
    /**
     * Gets the valorfrete.
     *
     * @return the valorfrete
     */
    public Double getValorFrete()
    {
        return valorfrete;
    }

    /**
     * Sets the valorfrete.
     *
* @param id the valorfrete to set
 */
public void setValorFrete(Double valorfrete)
{
        this.valorfrete = valorfrete;
    }

    /**
     * Gets the valorseguro.
     *
     * @return the valorseguro
     */
    /**
     * Gets the valorseguro.
     *
     * @return the valorseguro
     */
    public Double getValorSeguro()
    {
        return valorseguro;
    }

    /**
     * Sets the valorseguro.
     *
* @param id the valorseguro to set
 */
public void setValorSeguro(Double valorseguro)
{
        this.valorseguro = valorseguro;
    }

    /**
     * Gets the valordesconto.
     *
     * @return the valordesconto
     */
    /**
     * Gets the valordesconto.
     *
     * @return the valordesconto
     */
    public Double getValorDesconto()
    {
        return valordesconto;
    }

    /**
     * Sets the valordesconto.
     *
* @param id the valordesconto to set
 */
public void setValorDesconto(Double valordesconto)
{
        this.valordesconto = valordesconto;
    }

    /**
     * Gets the valoroutrasdespesasacessorias.
     *
     * @return the valoroutrasdespesasacessorias
     */
    /**
     * Gets the valoroutrasdespesasacessorias.
     *
     * @return the valoroutrasdespesasacessorias
     */
    public Double getValorOutrasDespesasAcessorias()
    {
        return valoroutrasdespesasacessorias;
    }

    /**
     * Sets the valoroutrasdespesasacessorias.
     *
* @param id the valoroutrasdespesasacessorias to set
 */
public void setValorOutrasDespesasAcessorias(Double valoroutrasdespesasacessorias)
{
        this.valoroutrasdespesasacessorias = valoroutrasdespesasacessorias;
    }

    /**
     * Gets the numerorecopi.
     *
     * @return the numerorecopi
     */
    /**
     * Gets the numerorecopi.
     *
     * @return the numerorecopi
     */
    public Integer getNumeroRECOPI()
    {
        return numerorecopi;
    }

    /**
     * Sets the numerorecopi.
     *
* @param id the numerorecopi to set
 */
public void setNumeroRECOPI(Integer numerorecopi)
{
        this.numerorecopi = numerorecopi;
    }


	@Override
	public String toString() {
		return "NotaFiscalItens [getId()=" + getId() + ", getDataInicio()=" + getDataInicio() + ", getValor()="
				+ getValor() + ", getServicoPlanoEnumValue()=" + getServicoPlanoEnumValue() + ", getServicoList()="
				+ getServicoList() + ", getPlanoList()=" + getPlanoList() + ", getNumeroRegistro()="
				+ getNumeroRegistro() + ", getDataRegistro()=" + getDataRegistro() + ", getLocalDesembaraco()="
				+ getLocalDesembaraco() + ", getUfDesembaraco()=" + getUfDesembaraco() + ", getDataDesembaraco()="
				+ getDataDesembaraco() + ", getTransporteInternacional()=" + getTransporteInternacional()
				+ ", getValorAFRMM()=" + getValorAFRMM() + ", getTpIntermedio()=" + getTpIntermedio() + ", getCnpj()="
				+ getCnpj() + ", getUfTerceiro()=" + getUfTerceiro() + ", getCodigoExportador()="
				+ getCodigoExportador() + ", getDescricao()=" + getDescricao() + ", getProduto()=" + getProduto()
				+ ", getCfop()=" + getCfop() + ", getValorUnitario()=" + getValorUnitario() + ", getValorTotalBruto()="
				+ getValorTotalBruto() + ", getValorFrete()=" + getValorFrete() + ", getValorSeguro()="
				+ getValorSeguro() + ", getValorDesconto()=" + getValorDesconto()
				+ ", getValorOutrasDespesasAcessorias()=" + getValorOutrasDespesasAcessorias() + ", getNumeroRECOPI()="
				+ getNumeroRECOPI() + ", toString()=" + super.toString() + "]";
	}





 }
