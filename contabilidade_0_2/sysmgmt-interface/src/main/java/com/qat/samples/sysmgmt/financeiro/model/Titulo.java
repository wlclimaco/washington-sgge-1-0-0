package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Titulo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String descricao;

	private String numero;

	private Integer parcela;

	private FormaPg formapg;

	/** The estado. */
	private Long dataEmissao;

	/** The bairro. */
	private Long dataVencimento;

	private Long dataPagamento;

	/** The numero. */
	private String docId;

	private DoisValores tipoDoc;

	private DoisValores formaCadastro;

	private DoisValores intervalo;

	private DoisValores categoria;

	private String observacao;

	private Double valor;

	private DoisValores situacao;

	private FinanceiroStatusEnum financeiroEnum;

	private List<BaixaTitulo> listBaixa;

	public Integer getFinanceiroEnumValue()
	{
		if (financeiroEnum != null)
		{
			return financeiroEnum.getValue();
		}
		return null;
	}

	public void setFinanceiroEnumValue(Integer acaoTypeValue)
	{
		financeiroEnum = FinanceiroStatusEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Default constructor.
	 */
	public Titulo()
	{
		super();
	}

	public Titulo(int i, String string) {
		// TODO Auto-generated constructor stub
	}

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

	public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public Integer getParcela()
	{
		return parcela;
	}

	public void setParcela(Integer parcela)
	{
		this.parcela = parcela;
	}

	public Long getDataEmissao()
	{
		return dataEmissao;
	}

	public void setDataEmissao(Long dataEmissao)
	{
		this.dataEmissao = dataEmissao;
	}

	public Long getDataVencimento()
	{
		return dataVencimento;
	}

	public void setDataVencimento(Long dataVencimento)
	{
		this.dataVencimento = dataVencimento;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public DoisValores getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(DoisValores tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getObservacao()
	{
		return observacao;
	}

	public void setObservacao(String observacao)
	{
		this.observacao = observacao;
	}

	public Double getValor()
	{
		return valor;
	}

	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	public FinanceiroStatusEnum getFinanceiroEnum()
	{
		return financeiroEnum;
	}

	public void setFinanceiroEnum(FinanceiroStatusEnum financeiroEnum)
	{
		this.financeiroEnum = financeiroEnum;
	}

	public List<BaixaTitulo> getListBaixa()
	{
		return listBaixa;
	}

	public void setListBaixa(List<BaixaTitulo> listBaixa)
	{
		this.listBaixa = listBaixa;
	}

	public DoisValores getCategoria() {
		return categoria;
	}

	public void setCategoria(DoisValores categoria) {
		this.categoria = categoria;
	}

	public FormaPg getFormapg() {
		return formapg;
	}

	public void setFormapg(FormaPg formapg) {
		this.formapg = formapg;
	}

	public DoisValores getFormaCadastro() {
		return formaCadastro;
	}

	public void setFormaCadastro(DoisValores formaCadastro) {
		this.formaCadastro = formaCadastro;
	}

	public DoisValores getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(DoisValores intervalo) {
		this.intervalo = intervalo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Long dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public DoisValores getSituacao() {
		return situacao;
	}

	public void setSituacao(DoisValores situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Titulo [getFinanceiroEnumValue()=" + getFinanceiroEnumValue() + ", getId()=" + getId()
				+ ", getNumero()=" + getNumero() + ", getParcela()=" + getParcela() + ", getDataEmissao()="
				+ getDataEmissao() + ", getDataVencimento()=" + getDataVencimento() + ", getDocId()=" + getDocId()
				+ ", getTipoDoc()=" + getTipoDoc() + ", getObservacao()=" + getObservacao() + ", getValor()="
				+ getValor() + ", getFinanceiroEnum()=" + getFinanceiroEnum() + ", getListBaixa()=" + getListBaixa()
				+ ", getCategoria()=" + getCategoria() + ", getFormapg()=" + getFormapg() + ", getFormaCadastro()="
				+ getFormaCadastro() + ", getIntervalo()=" + getIntervalo() + ", getDescricao()=" + getDescricao()
				+ ", getDataPagamento()=" + getDataPagamento() + ", getSituacao()=" + getSituacao() + ", toString()="
				+ super.toString() + "]";
	}








}
