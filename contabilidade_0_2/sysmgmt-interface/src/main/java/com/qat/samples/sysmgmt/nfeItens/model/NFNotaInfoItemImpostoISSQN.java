/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoISSQN extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoISSQN. */
	private Integer id;

	/** The econtabil valorBaseCalculo for the NFNotaInfoItemImpostoISSQN. */
	private String valorBaseCalculo;

	/** The econtabil valorAliquota for the NFNotaInfoItemImpostoISSQN. */
	private String valorAliquota;

	/** The econtabil valor for the NFNotaInfoItemImpostoISSQN. */
	private String valor;

	/** The econtabil codigoMunicipio for the NFNotaInfoItemImpostoISSQN. */
	private Integer codigoMunicipio;

	/** The econtabil itemListaServicos for the NFNotaInfoItemImpostoISSQN. */
	private String itemListaServicos;

	/** The econtabil valorDeducao for the NFNotaInfoItemImpostoISSQN. */
	private String valorDeducao;

	/** The econtabil valorOutro for the NFNotaInfoItemImpostoISSQN. */
	private String valorOutro;

	/**
	 * The econtabil valorDescontoIncondicionado for the
	 * NFNotaInfoItemImpostoISSQN.
	 */
	private String valorDescontoIncondicionado;

	/**
	 * The econtabil valorDescontoCondicionado for the
	 * NFNotaInfoItemImpostoISSQN.
	 */
	private String valorDescontoCondicionado;

	/** The econtabil valorRetencaoISS for the NFNotaInfoItemImpostoISSQN. */
	private String valorRetencaoISS;

	/**
	 * The econtabil indicadorExigibilidadeISS for the
	 * NFNotaInfoItemImpostoISSQN.
	 */
	private DoisValores indicadorExigibilidadeISS;

	/** The econtabil codigoServico for the NFNotaInfoItemImpostoISSQN. */
	private String codigoServico;

	/**
	 * The econtabil codigoMunicipioIncidenciaImposto for the
	 * NFNotaInfoItemImpostoISSQN.
	 */
	private String codigoMunicipioIncidenciaImposto;

	/** The econtabil codigoPais for the NFNotaInfoItemImpostoISSQN. */
	private String codigoPais;

	/** The econtabil numeroProcesso for the NFNotaInfoItemImpostoISSQN. */
	private String numeroProcesso;

	/**
	 * The econtabil indicadorIncentivoFiscal for the
	 * NFNotaInfoItemImpostoISSQN.
	 */
	private DoisValores indicadorIncentivoFiscal;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoISSQN() {
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
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the valorBaseCalculo.
	 *
	 * @return the valorBaseCalculo
	 */
	/**
	 * Gets the valorBaseCalculo.
	 *
	 * @return the valorBaseCalculo
	 */
	public String getValorBaseCalculo() {
		return valorBaseCalculo;
	}

	/**
	 * Sets the valorbasecalculo.
	 *
	 * @param id
	 *            the valorbasecalculo to set
	 */
	public void setValorBaseCalculo(String valorbasecalculo) {
		this.valorBaseCalculo = valorbasecalculo;
	}

	/**
	 * Gets the valorAliquota.
	 *
	 * @return the valorAliquota
	 */
	/**
	 * Gets the valorAliquota.
	 *
	 * @return the valorAliquota
	 */
	public String getValorAliquota() {
		return valorAliquota;
	}

	/**
	 * Sets the valoraliquota.
	 *
	 * @param id
	 *            the valoraliquota to set
	 */
	public void setValorAliquota(String valoraliquota) {
		this.valorAliquota = valoraliquota;
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
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param id
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Gets the codigoMunicipio.
	 *
	 * @return the codigoMunicipio
	 */
	/**
	 * Gets the codigoMunicipio.
	 *
	 * @return the codigoMunicipio
	 */
	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	/**
	 * Sets the codigomunicipio.
	 *
	 * @param id
	 *            the codigomunicipio to set
	 */
	public void setCodigoMunicipio(Integer codigomunicipio) {
		this.codigoMunicipio = codigomunicipio;
	}

	/**
	 * Gets the itemListaServicos.
	 *
	 * @return the itemListaServicos
	 */
	/**
	 * Gets the itemListaServicos.
	 *
	 * @return the itemListaServicos
	 */
	public String getItemListaServicos() {
		return itemListaServicos;
	}

	/**
	 * Sets the itemlistaservicos.
	 *
	 * @param id
	 *            the itemlistaservicos to set
	 */
	public void setItemListaServicos(String itemlistaservicos) {
		this.itemListaServicos = itemlistaservicos;
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
	public String getValorDeducao() {
		return valorDeducao;
	}

	/**
	 * Sets the valordeducao.
	 *
	 * @param id
	 *            the valordeducao to set
	 */
	public void setValorDeducao(String valordeducao) {
		this.valorDeducao = valordeducao;
	}

	/**
	 * Gets the valorOutro.
	 *
	 * @return the valorOutro
	 */
	/**
	 * Gets the valorOutro.
	 *
	 * @return the valorOutro
	 */
	public String getValorOutro() {
		return valorOutro;
	}

	/**
	 * Sets the valoroutro.
	 *
	 * @param id
	 *            the valoroutro to set
	 */
	public void setValorOutro(String valoroutro) {
		this.valorOutro = valoroutro;
	}

	/**
	 * Gets the valorDescontoIncondicionado.
	 *
	 * @return the valorDescontoIncondicionado
	 */
	/**
	 * Gets the valorDescontoIncondicionado.
	 *
	 * @return the valorDescontoIncondicionado
	 */
	public String getValorDescontoIncondicionado() {
		return valorDescontoIncondicionado;
	}

	/**
	 * Sets the valordescontoincondicionado.
	 *
	 * @param id
	 *            the valordescontoincondicionado to set
	 */
	public void setValorDescontoIncondicionado(String valordescontoincondicionado) {
		this.valorDescontoIncondicionado = valordescontoincondicionado;
	}

	/**
	 * Gets the valorDescontoCondicionado.
	 *
	 * @return the valorDescontoCondicionado
	 */
	/**
	 * Gets the valorDescontoCondicionado.
	 *
	 * @return the valorDescontoCondicionado
	 */
	public String getValorDescontoCondicionado() {
		return valorDescontoCondicionado;
	}

	/**
	 * Sets the valordescontocondicionado.
	 *
	 * @param id
	 *            the valordescontocondicionado to set
	 */
	public void setValorDescontoCondicionado(String valordescontocondicionado) {
		this.valorDescontoCondicionado = valordescontocondicionado;
	}

	/**
	 * Gets the valorRetencaoISS.
	 *
	 * @return the valorRetencaoISS
	 */
	/**
	 * Gets the valorRetencaoISS.
	 *
	 * @return the valorRetencaoISS
	 */
	public String getValorRetencaoISS() {
		return valorRetencaoISS;
	}

	/**
	 * Sets the valorretencaoiss.
	 *
	 * @param id
	 *            the valorretencaoiss to set
	 */
	public void setValorRetencaoISS(String valorretencaoiss) {
		this.valorRetencaoISS = valorretencaoiss;
	}

	/**
	 * Gets the indicadorExigibilidadeISS.
	 *
	 * @return the indicadorExigibilidadeISS
	 */
	/**
	 * Gets the indicadorExigibilidadeISS.
	 *
	 * @return the indicadorExigibilidadeISS
	 */
	public DoisValores getIndicadorExigibilidadeISS() {
		return indicadorExigibilidadeISS;
	}

	/**
	 * Sets the indicadorexigibilidadeiss.
	 *
	 * @param id
	 *            the indicadorexigibilidadeiss to set
	 */
	public void setIndicadorExigibilidadeISS(DoisValores indicadorexigibilidadeiss) {
		this.indicadorExigibilidadeISS = indicadorexigibilidadeiss;
	}

	/**
	 * Gets the codigoServico.
	 *
	 * @return the codigoServico
	 */
	/**
	 * Gets the codigoServico.
	 *
	 * @return the codigoServico
	 */
	public String getCodigoServico() {
		return codigoServico;
	}

	/**
	 * Sets the codigoservico.
	 *
	 * @param id
	 *            the codigoservico to set
	 */
	public void setCodigoServico(String codigoservico) {
		this.codigoServico = codigoservico;
	}

	/**
	 * Gets the codigoMunicipioIncidenciaImposto.
	 *
	 * @return the codigoMunicipioIncidenciaImposto
	 */
	/**
	 * Gets the codigoMunicipioIncidenciaImposto.
	 *
	 * @return the codigoMunicipioIncidenciaImposto
	 */
	public String getCodigoMunicipioIncidenciaImposto() {
		return codigoMunicipioIncidenciaImposto;
	}

	/**
	 * Sets the codigomunicipioincidenciaimposto.
	 *
	 * @param id
	 *            the codigomunicipioincidenciaimposto to set
	 */
	public void setCodigoMunicipioIncidenciaImposto(String codigomunicipioincidenciaimposto) {
		this.codigoMunicipioIncidenciaImposto = codigomunicipioincidenciaimposto;
	}

	/**
	 * Gets the codigoPais.
	 *
	 * @return the codigoPais
	 */
	/**
	 * Gets the codigoPais.
	 *
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigopais.
	 *
	 * @param id
	 *            the codigopais to set
	 */
	public void setCodigoPais(String codigopais) {
		this.codigoPais = codigopais;
	}

	/**
	 * Gets the numeroProcesso.
	 *
	 * @return the numeroProcesso
	 */
	/**
	 * Gets the numeroProcesso.
	 *
	 * @return the numeroProcesso
	 */
	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	/**
	 * Sets the numeroprocesso.
	 *
	 * @param id
	 *            the numeroprocesso to set
	 */
	public void setNumeroProcesso(String numeroprocesso) {
		this.numeroProcesso = numeroprocesso;
	}

	/**
	 * Gets the indicadorIncentivoFiscal.
	 *
	 * @return the indicadorIncentivoFiscal
	 */
	/**
	 * Gets the indicadorIncentivoFiscal.
	 *
	 * @return the indicadorIncentivoFiscal
	 */
	public DoisValores getIndicadorIncentivoFiscal() {
		return indicadorIncentivoFiscal;
	}

	/**
	 * Sets the indicadorincentivofiscal.
	 *
	 * @param id
	 *            the indicadorincentivofiscal to set
	 */
	public void setIndicadorIncentivoFiscal(DoisValores indicadorincentivofiscal) {
		this.indicadorIncentivoFiscal = indicadorincentivofiscal;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoISSQN [getId()=" + getId() + ", getValorBaseCalculo()=" + getValorBaseCalculo()
				+ ", getValorAliquota()=" + getValorAliquota() + ", getValor()=" + getValor()
				+ ", getCodigoMunicipio()=" + getCodigoMunicipio() + ", getItemListaServicos()="
				+ getItemListaServicos() + ", getValorDeducao()=" + getValorDeducao() + ", getValorOutro()="
				+ getValorOutro() + ", getValorDescontoIncondicionado()=" + getValorDescontoIncondicionado()
				+ ", getValorDescontoCondicionado()=" + getValorDescontoCondicionado() + ", getValorRetencaoISS()="
				+ getValorRetencaoISS() + ", getIndicadorExigibilidadeISS()=" + getIndicadorExigibilidadeISS()
				+ ", getCodigoServico()=" + getCodigoServico() + ", getCodigoMunicipioIncidenciaImposto()="
				+ getCodigoMunicipioIncidenciaImposto() + ", getCodigoPais()=" + getCodigoPais()
				+ ", getNumeroProcesso()=" + getNumeroProcesso() + ", getIndicadorIncentivoFiscal()="
				+ getIndicadorIncentivoFiscal() + ", toString()=" + super.toString() + "]";
	}

}
