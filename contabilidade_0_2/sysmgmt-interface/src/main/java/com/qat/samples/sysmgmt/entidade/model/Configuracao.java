package com.qat.samples.sysmgmt.entidade.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Configuracao extends ModelCosmeDamiao {

	/** The id. */
	private Integer id;

	/** The conf cabecalho. */
	private String confCabecalho;

	/** The conf geral. */
	private ConfigGeral confGeral;

	/** The conf fiscal. */
	private ConfigFiscal confFiscal;

	/** The conf prod. */
	private ConfigProduto confProd;

	/** The conf vendas. */
	private ConfigVendas confVendas;

	/** The conf SMTP. */
	private ConfigSMTP confSMTP;

	/** The config OS. */
	private ConfigOS configOS;

	/** The conf entrada. */
	private ConfigEntrada confEntrada;

	/** The conf carne. */
	private ConfigCarne confCarne;

	/** The conf N fe. */
	private ConfiguracaoNFe confNFe;

	/** The conf alertas. */
	private ConfigAlertas confAlertas;

	/** The conf blue soft. */
	private ConfigBlueSoft confBlueSoft;

	/** The boleto list. */
	private List<Boleto> boletoList;

	/**
	 * Instantiates a new configuracao.
	 */
	public Configuracao() {
		super();
	}

	/**
	 * Instantiates a new configuracao.
	 *
	 * @param id the id
	 */
	public Configuracao(Integer id) {
		setId(id);
	}

	/**
	 * Instantiates a new configuracao.
	 *
	 * @param i the i
	 * @param string the string
	 */
	public Configuracao(int i, String string) {
		// TODO Auto-generated constructor stub
	}

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
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the conf cabecalho.
	 *
	 * @return the conf cabecalho
	 */
	public String getConfCabecalho() {
		return confCabecalho;
	}

	/**
	 * Sets the conf cabecalho.
	 *
	 * @param confCabecalho the new conf cabecalho
	 */
	public void setConfCabecalho(String confCabecalho) {
		this.confCabecalho = confCabecalho;
	}

	/**
	 * Gets the conf geral.
	 *
	 * @return the conf geral
	 */
	public ConfigGeral getConfGeral() {
		return confGeral;
	}

	/**
	 * Sets the conf geral.
	 *
	 * @param confGeral the new conf geral
	 */
	public void setConfGeral(ConfigGeral confGeral) {
		this.confGeral = confGeral;
	}

	/**
	 * Gets the conf fiscal.
	 *
	 * @return the conf fiscal
	 */
	public ConfigFiscal getConfFiscal() {
		return confFiscal;
	}

	/**
	 * Sets the conf fiscal.
	 *
	 * @param confFiscal the new conf fiscal
	 */
	public void setConfFiscal(ConfigFiscal confFiscal) {
		this.confFiscal = confFiscal;
	}

	/**
	 * Gets the conf prod.
	 *
	 * @return the conf prod
	 */
	public ConfigProduto getConfProd() {
		return confProd;
	}

	/**
	 * Sets the conf prod.
	 *
	 * @param confProd the new conf prod
	 */
	public void setConfProd(ConfigProduto confProd) {
		this.confProd = confProd;
	}

	/**
	 * Gets the conf vendas.
	 *
	 * @return the conf vendas
	 */
	public ConfigVendas getConfVendas() {
		return confVendas;
	}

	/**
	 * Sets the conf vendas.
	 *
	 * @param confVendas the new conf vendas
	 */
	public void setConfVendas(ConfigVendas confVendas) {
		this.confVendas = confVendas;
	}

	/**
	 * Gets the conf SMTP.
	 *
	 * @return the conf SMTP
	 */
	public ConfigSMTP getConfSMTP() {
		return confSMTP;
	}

	/**
	 * Sets the conf SMTP.
	 *
	 * @param confSMTP the new conf SMTP
	 */
	public void setConfSMTP(ConfigSMTP confSMTP) {
		this.confSMTP = confSMTP;
	}

	/**
	 * Gets the config OS.
	 *
	 * @return the config OS
	 */
	public ConfigOS getConfigOS() {
		return configOS;
	}

	/**
	 * Sets the config OS.
	 *
	 * @param configOS the new config OS
	 */
	public void setConfigOS(ConfigOS configOS) {
		this.configOS = configOS;
	}

	/**
	 * Gets the conf entrada.
	 *
	 * @return the conf entrada
	 */
	public ConfigEntrada getConfEntrada() {
		return confEntrada;
	}

	/**
	 * Sets the conf entrada.
	 *
	 * @param confEntrada the new conf entrada
	 */
	public void setConfEntrada(ConfigEntrada confEntrada) {
		this.confEntrada = confEntrada;
	}

	/**
	 * Gets the conf carne.
	 *
	 * @return the conf carne
	 */
	public ConfigCarne getConfCarne() {
		return confCarne;
	}

	/**
	 * Sets the conf carne.
	 *
	 * @param confCarne the new conf carne
	 */
	public void setConfCarne(ConfigCarne confCarne) {
		this.confCarne = confCarne;
	}

	/**
	 * Gets the conf N fe.
	 *
	 * @return the conf N fe
	 */
	public ConfiguracaoNFe getConfNFe() {
		return confNFe;
	}

	/**
	 * Sets the conf N fe.
	 *
	 * @param confNFe the new conf N fe
	 */
	public void setConfNFe(ConfiguracaoNFe confNFe) {
		this.confNFe = confNFe;
	}

	/**
	 * Gets the conf alertas.
	 *
	 * @return the conf alertas
	 */
	public ConfigAlertas getConfAlertas() {
		return confAlertas;
	}

	/**
	 * Sets the conf alertas.
	 *
	 * @param confAlertas the new conf alertas
	 */
	public void setConfAlertas(ConfigAlertas confAlertas) {
		this.confAlertas = confAlertas;
	}

	/**
	 * Gets the conf blue soft.
	 *
	 * @return the conf blue soft
	 */
	public ConfigBlueSoft getConfBlueSoft() {
		return confBlueSoft;
	}

	/**
	 * Sets the conf blue soft.
	 *
	 * @param confBlueSoft the new conf blue soft
	 */
	public void setConfBlueSoft(ConfigBlueSoft confBlueSoft) {
		this.confBlueSoft = confBlueSoft;
	}

	/**
	 * Gets the boleto list.
	 *
	 * @return the boleto list
	 */
	public List<Boleto> getBoletoList() {
		return boletoList;
	}

	/**
	 * Sets the boleto list.
	 *
	 * @param boletoList the new boleto list
	 */
	public void setBoletoList(List<Boleto> boletoList) {
		this.boletoList = boletoList;
	}

	/* (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "Configuracao [getId()=" + getId() + ", getConfCabecalho()=" + getConfCabecalho() + ", getConfGeral()="
				+ getConfGeral() + ", getConfFiscal()=" + getConfFiscal() + ", getConfProd()=" + getConfProd()
				+ ", getConfVendas()=" + getConfVendas() + ", getConfSMTP()=" + getConfSMTP() + ", getConfigOS()="
				+ getConfigOS() + ", getConfEntrada()=" + getConfEntrada() + ", getConfCarne()=" + getConfCarne()
				+ ", getConfNFe()=" + getConfNFe() + ", getConfAlertas()=" + getConfAlertas() + ", getConfBlueSoft()="
				+ getConfBlueSoft() + ", getBoletoList()=" + getBoletoList() + ", toString()=" + super.toString() + "]";
	}

}
