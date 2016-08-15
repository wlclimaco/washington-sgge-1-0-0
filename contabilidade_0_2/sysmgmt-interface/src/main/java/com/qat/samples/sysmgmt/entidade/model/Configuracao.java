package com.qat.samples.sysmgmt.entidade.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Configuracao extends ModelCosmeDamiao
{
	private Integer id;

	private String confCabecalho;
	private ConfigGeral confGeral;
	private ConfigFiscal confFiscal;
	private ConfigProduto confProd;
	private ConfigVendas confVendas;
	private ConfigSMTP confSMTP;
	private ConfigOS configOS;
	private ConfigEntrada confEntrada;
	private ConfigCarne confCarne;
	private ConfiguracaoNFe confNFe;
	private ConfigAlertas confAlertas;
	private ConfigBlueSoft confBlueSoft;
	private List<Boleto> boletoList;


	public Configuracao()
	{
		super();
	}

	public Configuracao(Integer id)
	{
		setId(id);
	}

	public Configuracao(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Boleto> getBoletoList() {
		return boletoList;
	}

	public void setBoletoList(List<Boleto> boletoList) {
		this.boletoList = boletoList;
	}

	public ConfigGeral getConfGeral() {
		return confGeral;
	}

	public void setConfGeral(ConfigGeral confGeral) {
		this.confGeral = confGeral;
	}

	public ConfigFiscal getConfFiscal() {
		return confFiscal;
	}

	public void setConfFiscal(ConfigFiscal confFiscal) {
		this.confFiscal = confFiscal;
	}

	public ConfigProduto getConfProd() {
		return confProd;
	}

	public void setConfProd(ConfigProduto confProd) {
		this.confProd = confProd;
	}

	public ConfigVendas getConfVendas() {
		return confVendas;
	}

	public void setConfVendas(ConfigVendas confVendas) {
		this.confVendas = confVendas;
	}

	public ConfigSMTP getConfCMTP() {
		return confSMTP;
	}

	public void setConfCMTP(ConfigSMTP confCMTP) {
		this.confSMTP = confCMTP;
	}

	public ConfigEntrada getConfEntrada() {
		return confEntrada;
	}

	public void setConfEntrada(ConfigEntrada confEntrada) {
		this.confEntrada = confEntrada;
	}

	public ConfigCarne getConfCarne() {
		return confCarne;
	}

	public void setConfCarne(ConfigCarne confCarne) {
		this.confCarne = confCarne;
	}

	public ConfiguracaoNFe getConfNFe() {
		return confNFe;
	}

	public void setConfNFe(ConfiguracaoNFe confNFe) {
		this.confNFe = confNFe;
	}

	public String getConfCabecalho() {
		return confCabecalho;
	}

	public void setConfCabecalho(String confCabecalho) {
		this.confCabecalho = confCabecalho;
	}

	public ConfigOS getConfigOS() {
		return configOS;
	}

	public void setConfigOS(ConfigOS configOS) {
		this.configOS = configOS;
	}

	public ConfigAlertas getConfAlertas() {
		return confAlertas;
	}

	public void setConfAlertas(ConfigAlertas confAlertas) {
		this.confAlertas = confAlertas;
	}

	public ConfigBlueSoft getConfBlueSoft() {
		return confBlueSoft;
	}

	public void setConfBlueSoft(ConfigBlueSoft confBlueSoft) {
		this.confBlueSoft = confBlueSoft;
	}

	@Override
	public String toString() {
		return "Configuracao [getId()=" + getId() + ", getBoletoList()=" + getBoletoList() + ", getConfGeral()="
				+ getConfGeral() + ", getConfFiscal()=" + getConfFiscal() + ", getConfProd()=" + getConfProd()
				+ ", getConfVendas()=" + getConfVendas() + ", getConfCMTP()=" + getConfCMTP() + ", getConfEntrada()="
				+ getConfEntrada() + ", getConfCarne()=" + getConfCarne() + ", getConfNFe()=" + getConfNFe()
				+ ", getConfCabecalho()=" + getConfCabecalho() + ", getConfigOS()=" + getConfigOS()
				+ ", getConfAlertas()=" + getConfAlertas() + ", getConfBlueSoft()=" + getConfBlueSoft()
				+ ", toString()=" + super.toString() + "]";
	}




}
