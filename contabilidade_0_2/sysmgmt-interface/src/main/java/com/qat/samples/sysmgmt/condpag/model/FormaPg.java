package com.qat.samples.sysmgmt.condpag.model;

import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class FormaPg.
 */
@SuppressWarnings("serial")
public class FormaPg extends ModelCosmeDamiao
{

	/** The id. */
	private Integer id;

	/** The descricao. */
	private String descricao;

	private String observacao;

	private Integer diasPg;

	private Integer parcelamentoMax;

	private Integer parcelamentoSemJuros;

	private Double juros;

	private Double taxaFixa;

	private Double descAvista;

	private Conta conta;

	private DoisValores tipoDoc;

	private Integer qntIntervalo;

	private Integer intervalo;

	/** The entrada. */
	private Integer entrada;


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getParcelamentoMax() {
		return parcelamentoMax;
	}

	public void setParcelamentoMax(Integer parcelamentoMax) {
		this.parcelamentoMax = parcelamentoMax;
	}

	public Integer getParcelamentoSemJuros() {
		return parcelamentoSemJuros;
	}

	public void setParcelamentoSemJuros(Integer parcelamentoSemJuros) {
		this.parcelamentoSemJuros = parcelamentoSemJuros;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	public Double getTaxaFixa() {
		return taxaFixa;
	}

	public void setTaxaFixa(Double taxaFixa) {
		this.taxaFixa = taxaFixa;
	}

	public Double getDescAvista() {
		return descAvista;
	}

	public void setDescAvista(Double descAvista) {
		this.descAvista = descAvista;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public DoisValores getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(DoisValores tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Integer getQntIntervalo() {
		return qntIntervalo;
	}

	public void setQntIntervalo(Integer qntIntervalo) {
		this.qntIntervalo = qntIntervalo;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	/** The dias pg. */


	public FormaPg(Integer id, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		setModelAction(modelAction);
	}

	public FormaPg(Integer id, String descricao, Integer diasPg, Integer entrada, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.descricao = descricao;
		this.diasPg = diasPg;
		this.entrada = entrada;
		setModelAction(modelAction);
	}

	public FormaPg()
	{
		super();
	}

	public FormaPg(int i, String string) {
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
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * Gets the dias pg.
	 *
	 * @return the diasPg
	 */
	public Integer getDiasPg()
	{
		return diasPg;
	}

	/**
	 * Sets the dias pg.
	 *
	 * @param diasPg the diasPg to set
	 */
	public void setDiasPg(Integer diasPg)
	{
		this.diasPg = diasPg;
	}

	public Integer getEntrada()
	{
		return entrada;
	}

	public void setEntrada(Integer entrada)
	{
		this.entrada = entrada;
	}

	@Override
	public String toString() {
		return "FormaPg [getObservacao()=" + getObservacao() + ", getParcelamentoMax()=" + getParcelamentoMax()
				+ ", getParcelamentoSemJuros()=" + getParcelamentoSemJuros() + ", getJuros()=" + getJuros()
				+ ", getTaxaFixa()=" + getTaxaFixa() + ", getDescAvista()=" + getDescAvista() + ", getConta()="
				+ getConta() + ", getTipoDoc()=" + getTipoDoc() + ", getQntIntervalo()=" + getQntIntervalo()
				+ ", getIntervalo()=" + getIntervalo() + ", getId()=" + getId() + ", getDescricao()=" + getDescricao()
				+ ", getDiasPg()=" + getDiasPg() + ", getEntrada()=" + getEntrada() + ", toString()=" + super.toString()
				+ "]";
	}

}
