/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoVeiculo extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProdutoVeiculo. */
	private Integer id;

	/** The econtabil tipoOperacao for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores tipoOperacao;

	/** The econtabil chassi for the NFNotaInfoItemProdutoVeiculo. */
	private String chassi;

	/** The econtabil codigoCor for the NFNotaInfoItemProdutoVeiculo. */
	private String codigoCor;

	/** The econtabil descricaoCor for the NFNotaInfoItemProdutoVeiculo. */
	private String descricaoCor;

	/** The econtabil potencia for the NFNotaInfoItemProdutoVeiculo. */
	private String potencia;

	/** The econtabil cilindrada for the NFNotaInfoItemProdutoVeiculo. */
	private String cilindrada;

	/** The econtabil pesoLiquido for the NFNotaInfoItemProdutoVeiculo. */
	private String pesoLiquido;

	/** The econtabil pesoBruto for the NFNotaInfoItemProdutoVeiculo. */
	private String pesoBruto;

	/** The econtabil numeroSerie for the NFNotaInfoItemProdutoVeiculo. */
	private String numeroSerie;

	/** The econtabil tipoCombustivel for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores tipoCombustivel;

	/** The econtabil numeroMotor for the NFNotaInfoItemProdutoVeiculo. */
	private String numeroMotor;

	/**
	 * The econtabil capacidadeMaximaTracao for the
	 * NFNotaInfoItemProdutoVeiculo.
	 */
	private String capacidadeMaximaTracao;

	/**
	 * The econtabil distanciaEntreEixos for the NFNotaInfoItemProdutoVeiculo.
	 */
	private Integer distanciaEntreEixos;

	/**
	 * The econtabil anoModeloFabricacao for the NFNotaInfoItemProdutoVeiculo.
	 */
	private Integer anoModeloFabricacao;

	/** The econtabil anoFabricacao for the NFNotaInfoItemProdutoVeiculo. */
	private Integer anoFabricacao;

	/** The econtabil tipoPintura for the NFNotaInfoItemProdutoVeiculo. */
	private String tipoPintura;

	/** The econtabil tipoVeiculo for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores tipoVeiculo;

	/** The econtabil especieVeiculo for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores especieVeiculo;

	/** The econtabil condicaoChassi for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores condicaoChassi;

	/** The econtabil condicao for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores condicao;

	/** The econtabil codigoMarcaModelo for the NFNotaInfoItemProdutoVeiculo. */
	private String codigoMarcaModelo;

	/** The econtabil corDENATRAN for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores corDENATRAN;

	/** The econtabil lotacao for the NFNotaInfoItemProdutoVeiculo. */
	private Integer lotacao;

	/** The econtabil restricao for the NFNotaInfoItemProdutoVeiculo. */
	private DoisValores restricao;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoVeiculo() {
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
	 * Gets the tipoOperacao.
	 *
	 * @return the tipoOperacao
	 */
	/**
	 * Gets the tipoOperacao.
	 *
	 * @return the tipoOperacao
	 */
	public DoisValores getTipoOperacao() {
		return tipoOperacao;
	}

	/**
	 * Sets the tipooperacao.
	 *
	 * @param id
	 *            the tipooperacao to set
	 */
	public void setTipoOperacao(DoisValores tipooperacao) {
		this.tipoOperacao = tipooperacao;
	}

	/**
	 * Gets the chassi.
	 *
	 * @return the chassi
	 */
	/**
	 * Gets the chassi.
	 *
	 * @return the chassi
	 */
	public String getChassi() {
		return chassi;
	}

	/**
	 * Sets the chassi.
	 *
	 * @param id
	 *            the chassi to set
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/**
	 * Gets the codigoCor.
	 *
	 * @return the codigoCor
	 */
	/**
	 * Gets the codigoCor.
	 *
	 * @return the codigoCor
	 */
	public String getCodigoCor() {
		return codigoCor;
	}

	/**
	 * Sets the codigocor.
	 *
	 * @param id
	 *            the codigocor to set
	 */
	public void setCodigoCor(String codigocor) {
		this.codigoCor = codigocor;
	}

	/**
	 * Gets the descricaoCor.
	 *
	 * @return the descricaoCor
	 */
	/**
	 * Gets the descricaoCor.
	 *
	 * @return the descricaoCor
	 */
	public String getDescricaoCor() {
		return descricaoCor;
	}

	/**
	 * Sets the descricaocor.
	 *
	 * @param id
	 *            the descricaocor to set
	 */
	public void setDescricaoCor(String descricaocor) {
		this.descricaoCor = descricaocor;
	}

	/**
	 * Gets the potencia.
	 *
	 * @return the potencia
	 */
	/**
	 * Gets the potencia.
	 *
	 * @return the potencia
	 */
	public String getPotencia() {
		return potencia;
	}

	/**
	 * Sets the potencia.
	 *
	 * @param id
	 *            the potencia to set
	 */
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	/**
	 * Gets the cilindrada.
	 *
	 * @return the cilindrada
	 */
	/**
	 * Gets the cilindrada.
	 *
	 * @return the cilindrada
	 */
	public String getCilindrada() {
		return cilindrada;
	}

	/**
	 * Sets the cilindrada.
	 *
	 * @param id
	 *            the cilindrada to set
	 */
	public void setCilindrada(String cilindrada) {
		this.cilindrada = cilindrada;
	}

	/**
	 * Gets the pesoLiquido.
	 *
	 * @return the pesoLiquido
	 */
	/**
	 * Gets the pesoLiquido.
	 *
	 * @return the pesoLiquido
	 */
	public String getPesoLiquido() {
		return pesoLiquido;
	}

	/**
	 * Sets the pesoliquido.
	 *
	 * @param id
	 *            the pesoliquido to set
	 */
	public void setPesoLiquido(String pesoliquido) {
		this.pesoLiquido = pesoliquido;
	}

	/**
	 * Gets the pesoBruto.
	 *
	 * @return the pesoBruto
	 */
	/**
	 * Gets the pesoBruto.
	 *
	 * @return the pesoBruto
	 */
	public String getPesoBruto() {
		return pesoBruto;
	}

	/**
	 * Sets the pesobruto.
	 *
	 * @param id
	 *            the pesobruto to set
	 */
	public void setPesoBruto(String pesobruto) {
		this.pesoBruto = pesobruto;
	}

	/**
	 * Gets the numeroSerie.
	 *
	 * @return the numeroSerie
	 */
	/**
	 * Gets the numeroSerie.
	 *
	 * @return the numeroSerie
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}

	/**
	 * Sets the numeroserie.
	 *
	 * @param id
	 *            the numeroserie to set
	 */
	public void setNumeroSerie(String numeroserie) {
		this.numeroSerie = numeroserie;
	}

	/**
	 * Gets the tipoCombustivel.
	 *
	 * @return the tipoCombustivel
	 */
	/**
	 * Gets the tipoCombustivel.
	 *
	 * @return the tipoCombustivel
	 */
	public DoisValores getTipoCombustivel() {
		return tipoCombustivel;
	}

	/**
	 * Sets the tipocombustivel.
	 *
	 * @param id
	 *            the tipocombustivel to set
	 */
	public void setTipoCombustivel(DoisValores tipocombustivel) {
		this.tipoCombustivel = tipocombustivel;
	}

	/**
	 * Gets the numeroMotor.
	 *
	 * @return the numeroMotor
	 */
	/**
	 * Gets the numeroMotor.
	 *
	 * @return the numeroMotor
	 */
	public String getNumeroMotor() {
		return numeroMotor;
	}

	/**
	 * Sets the numeromotor.
	 *
	 * @param id
	 *            the numeromotor to set
	 */
	public void setNumeroMotor(String numeromotor) {
		this.numeroMotor = numeromotor;
	}

	/**
	 * Gets the capacidadeMaximaTracao.
	 *
	 * @return the capacidadeMaximaTracao
	 */
	/**
	 * Gets the capacidadeMaximaTracao.
	 *
	 * @return the capacidadeMaximaTracao
	 */
	public String getCapacidadeMaximaTracao() {
		return capacidadeMaximaTracao;
	}

	/**
	 * Sets the capacidademaximatracao.
	 *
	 * @param id
	 *            the capacidademaximatracao to set
	 */
	public void setCapacidadeMaximaTracao(String capacidademaximatracao) {
		this.capacidadeMaximaTracao = capacidademaximatracao;
	}

	/**
	 * Gets the distanciaEntreEixos.
	 *
	 * @return the distanciaEntreEixos
	 */
	/**
	 * Gets the distanciaEntreEixos.
	 *
	 * @return the distanciaEntreEixos
	 */
	public Integer getDistanciaEntreEixos() {
		return distanciaEntreEixos;
	}

	/**
	 * Sets the distanciaentreeixos.
	 *
	 * @param id
	 *            the distanciaentreeixos to set
	 */
	public void setDistanciaEntreEixos(Integer distanciaentreeixos) {
		this.distanciaEntreEixos = distanciaentreeixos;
	}

	/**
	 * Gets the anoModeloFabricacao.
	 *
	 * @return the anoModeloFabricacao
	 */
	/**
	 * Gets the anoModeloFabricacao.
	 *
	 * @return the anoModeloFabricacao
	 */
	public Integer getAnoModeloFabricacao() {
		return anoModeloFabricacao;
	}

	/**
	 * Sets the anomodelofabricacao.
	 *
	 * @param id
	 *            the anomodelofabricacao to set
	 */
	public void setAnoModeloFabricacao(Integer anomodelofabricacao) {
		this.anoModeloFabricacao = anomodelofabricacao;
	}

	/**
	 * Gets the anoFabricacao.
	 *
	 * @return the anoFabricacao
	 */
	/**
	 * Gets the anoFabricacao.
	 *
	 * @return the anoFabricacao
	 */
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	/**
	 * Sets the anofabricacao.
	 *
	 * @param id
	 *            the anofabricacao to set
	 */
	public void setAnoFabricacao(Integer anofabricacao) {
		this.anoFabricacao = anofabricacao;
	}

	/**
	 * Gets the tipoPintura.
	 *
	 * @return the tipoPintura
	 */
	/**
	 * Gets the tipoPintura.
	 *
	 * @return the tipoPintura
	 */
	public String getTipoPintura() {
		return tipoPintura;
	}

	/**
	 * Sets the tipopintura.
	 *
	 * @param id
	 *            the tipopintura to set
	 */
	public void setTipoPintura(String tipopintura) {
		this.tipoPintura = tipopintura;
	}

	/**
	 * Gets the tipoVeiculo.
	 *
	 * @return the tipoVeiculo
	 */
	/**
	 * Gets the tipoVeiculo.
	 *
	 * @return the tipoVeiculo
	 */
	public DoisValores getTipoVeiculo() {
		return tipoVeiculo;
	}

	/**
	 * Sets the tipoveiculo.
	 *
	 * @param id
	 *            the tipoveiculo to set
	 */
	public void setTipoVeiculo(DoisValores tipoveiculo) {
		this.tipoVeiculo = tipoveiculo;
	}

	/**
	 * Gets the especieVeiculo.
	 *
	 * @return the especieVeiculo
	 */
	/**
	 * Gets the especieVeiculo.
	 *
	 * @return the especieVeiculo
	 */
	public DoisValores getEspecieVeiculo() {
		return especieVeiculo;
	}

	/**
	 * Sets the especieveiculo.
	 *
	 * @param id
	 *            the especieveiculo to set
	 */
	public void setEspecieVeiculo(DoisValores especieveiculo) {
		this.especieVeiculo = especieveiculo;
	}

	/**
	 * Gets the condicaoChassi.
	 *
	 * @return the condicaoChassi
	 */
	/**
	 * Gets the condicaoChassi.
	 *
	 * @return the condicaoChassi
	 */
	public DoisValores getCondicaoChassi() {
		return condicaoChassi;
	}

	/**
	 * Sets the condicaochassi.
	 *
	 * @param id
	 *            the condicaochassi to set
	 */
	public void setCondicaoChassi(DoisValores condicaochassi) {
		this.condicaoChassi = condicaochassi;
	}

	/**
	 * Gets the condicao.
	 *
	 * @return the condicao
	 */
	/**
	 * Gets the condicao.
	 *
	 * @return the condicao
	 */
	public DoisValores getCondicao() {
		return condicao;
	}

	/**
	 * Sets the condicao.
	 *
	 * @param id
	 *            the condicao to set
	 */
	public void setCondicao(DoisValores condicao) {
		this.condicao = condicao;
	}

	/**
	 * Gets the codigoMarcaModelo.
	 *
	 * @return the codigoMarcaModelo
	 */
	/**
	 * Gets the codigoMarcaModelo.
	 *
	 * @return the codigoMarcaModelo
	 */
	public String getCodigoMarcaModelo() {
		return codigoMarcaModelo;
	}

	/**
	 * Sets the codigomarcamodelo.
	 *
	 * @param id
	 *            the codigomarcamodelo to set
	 */
	public void setCodigoMarcaModelo(String codigomarcamodelo) {
		this.codigoMarcaModelo = codigomarcamodelo;
	}

	/**
	 * Gets the corDENATRAN.
	 *
	 * @return the corDENATRAN
	 */
	/**
	 * Gets the corDENATRAN.
	 *
	 * @return the corDENATRAN
	 */
	public DoisValores getCorDENATRAN() {
		return corDENATRAN;
	}

	/**
	 * Sets the cordenatran.
	 *
	 * @param id
	 *            the cordenatran to set
	 */
	public void setCorDENATRAN(DoisValores cordenatran) {
		this.corDENATRAN = cordenatran;
	}

	/**
	 * Gets the lotacao.
	 *
	 * @return the lotacao
	 */
	/**
	 * Gets the lotacao.
	 *
	 * @return the lotacao
	 */
	public Integer getLotacao() {
		return lotacao;
	}

	/**
	 * Sets the lotacao.
	 *
	 * @param id
	 *            the lotacao to set
	 */
	public void setLotacao(Integer lotacao) {
		this.lotacao = lotacao;
	}

	/**
	 * Gets the restricao.
	 *
	 * @return the restricao
	 */
	/**
	 * Gets the restricao.
	 *
	 * @return the restricao
	 */
	public DoisValores getRestricao() {
		return restricao;
	}

	/**
	 * Sets the restricao.
	 *
	 * @param id
	 *            the restricao to set
	 */
	public void setRestricao(DoisValores restricao) {
		this.restricao = restricao;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoVeiculo [getId()=" + getId() + ", getChassi()=" + getChassi() + ", getCodigoCor()="
				+ getCodigoCor() + ", getDescricaoCor()=" + getDescricaoCor() + ", getPotencia()=" + getPotencia()
				+ ", getCilindrada()=" + getCilindrada() + ", getPesoLiquido()=" + getPesoLiquido()
				+ ", getPesoBruto()=" + getPesoBruto() + ", getNumeroSerie()=" + getNumeroSerie()
				+ ", getTipoCombustivel()=" + getTipoCombustivel() + ", getNumeroMotor()=" + getNumeroMotor()
				+ ", getCapacidadeMaximaTracao()=" + getCapacidadeMaximaTracao() + ", getDistanciaEntreEixos()="
				+ getDistanciaEntreEixos() + ", getAnoModeloFabricacao()=" + getAnoModeloFabricacao()
				+ ", getAnoFabricacao()=" + getAnoFabricacao() + ", getTipoPintura()=" + getTipoPintura()
				+ ", getTipoVeiculo()=" + getTipoVeiculo() + ", getEspecieVeiculo()=" + getEspecieVeiculo()
				+ ", getCodigoMarcaModelo()=" + getCodigoMarcaModelo() + ", getLotacao()=" + getLotacao()
				+ ", toString()=" + super.toString() + "]";
	}

}
