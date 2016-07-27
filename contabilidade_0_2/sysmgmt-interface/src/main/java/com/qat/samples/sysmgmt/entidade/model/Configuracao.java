package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Configuracao extends ModelCosmeDamiao
{
	private Integer id;
	
	private ConfigGeral confGeral;
	private ConfigFiscal confFiscal;
	private ConfigProduto confProd;
	private ConfigVendas confVendas;
	private ConfigSMTP confCMTP;
	private ConfigEntrada confEntrada;
	private ConfigCarne confCarne;
	private ConfiguracaoNFe confNFe;
	private List<Boleto> boletoList;


	public Configuracao()
	{
		super();
	}

	public Configuracao(Integer id)
	{
		setId(id);
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
		return confCMTP;
	}

	public void setConfCMTP(ConfigSMTP confCMTP) {
		this.confCMTP = confCMTP;
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

	@Override
	public String toString() {
		return "Configuracao [getId()=" + getId() + ", getBoletoList()=" + getBoletoList() + ", getConfGeral()="
				+ getConfGeral() + ", getConfFiscal()=" + getConfFiscal() + ", getConfProd()=" + getConfProd()
				+ ", getConfVendas()=" + getConfVendas() + ", getConfCMTP()=" + getConfCMTP() + ", getConfEntrada()="
				+ getConfEntrada() + ", getConfCarne()=" + getConfCarne() + ", toString()=" + super.toString() + "]";
	}

	

	
}
