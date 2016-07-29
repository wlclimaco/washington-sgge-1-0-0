package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigGeral extends ModelCosmeDamiao
{
	private Integer id;
	private Integer fusoHorario;
	private Integer casasDecimais;
	private Integer diasCartaCobr;
	private Integer infPosicionarMouse;
	private Integer cnpjCPFUnico;
	private Integer impCodPersonalizado;
	private Integer logListRelImp;
	private Integer obsProdFinProd;

	public ConfigGeral()
	{
		super();
	}

	public ConfigGeral(Integer id)
	{
		setId(id);
	}

	public ConfigGeral(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFusoHorario() {
		return fusoHorario;
	}

	public void setFusoHorario(Integer fusoHorario) {
		this.fusoHorario = fusoHorario;
	}

	public Integer getCasasDecimais() {
		return casasDecimais;
	}

	public void setCasasDecimais(Integer casasDecimais) {
		this.casasDecimais = casasDecimais;
	}

	public Integer getDiasCartaCobr() {
		return diasCartaCobr;
	}

	public void setDiasCartaCobr(Integer diasCartaCobr) {
		this.diasCartaCobr = diasCartaCobr;
	}

	public Integer getInfPosicionarMouse() {
		return infPosicionarMouse;
	}

	public void setInfPosicionarMouse(Integer infPosicionarMouse) {
		this.infPosicionarMouse = infPosicionarMouse;
	}

	public Integer getCnpjCPFUnico() {
		return cnpjCPFUnico;
	}

	public void setCnpjCPFUnico(Integer cnpjCPFUnico) {
		this.cnpjCPFUnico = cnpjCPFUnico;
	}

	public Integer getImpCodPersonalizado() {
		return impCodPersonalizado;
	}

	public void setImpCodPersonalizado(Integer impCodPersonalizado) {
		this.impCodPersonalizado = impCodPersonalizado;
	}

	public Integer getLogListRelImp() {
		return logListRelImp;
	}

	public void setLogListRelImp(Integer logListRelImp) {
		this.logListRelImp = logListRelImp;
	}

	public Integer getObsProdFinProd() {
		return obsProdFinProd;
	}

	public void setObsProdFinProd(Integer obsProdFinProd) {
		this.obsProdFinProd = obsProdFinProd;
	}

	@Override
	public String toString() {
		return "ConfigGeral [getId()=" + getId() + ", getFusoHorario()=" + getFusoHorario() + ", getCasasDecimais()="
				+ getCasasDecimais() + ", getDiasCartaCobr()=" + getDiasCartaCobr() + ", getInfPosicionarMouse()="
				+ getInfPosicionarMouse() + ", getCnpjCPFUnico()=" + getCnpjCPFUnico() + ", getImpCodPersonalizado()="
				+ getImpCodPersonalizado() + ", getLogListRelImp()=" + getLogListRelImp() + ", getObsProdFinProd()="
				+ getObsProdFinProd() + ", toString()=" + super.toString() + "]";
	}

}
