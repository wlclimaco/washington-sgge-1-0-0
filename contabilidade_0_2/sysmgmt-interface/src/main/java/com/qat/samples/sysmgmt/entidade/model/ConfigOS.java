package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfigOS extends ModelCosmeDamiao
{
	private Integer id;
	private Integer impr2Via;
	private Integer imprAss;
	private Integer imprResumo;
	private Integer imprDetHorz;
	private Integer diasGarantia;
	private String  observ;



	public ConfigOS()
	{
		super();
	}

	public ConfigOS(Integer id)
	{
		setId(id);
	}

	public ConfigOS(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getImpr2Via() {
		return impr2Via;
	}

	public void setImpr2Via(Integer impr2Via) {
		this.impr2Via = impr2Via;
	}

	public Integer getImprAss() {
		return imprAss;
	}

	public void setImprAss(Integer imprAss) {
		this.imprAss = imprAss;
	}

	public Integer getImprResumo() {
		return imprResumo;
	}

	public void setImprResumo(Integer imprResumo) {
		this.imprResumo = imprResumo;
	}

	public Integer getImprDetHorz() {
		return imprDetHorz;
	}

	public void setImprDetHorz(Integer imprDetHorz) {
		this.imprDetHorz = imprDetHorz;
	}

	public Integer getDiasGarantia() {
		return diasGarantia;
	}

	public void setDiasGarantia(Integer diasGarantia) {
		this.diasGarantia = diasGarantia;
	}

	public String getObserv() {
		return observ;
	}

	public void setObserv(String observ) {
		this.observ = observ;
	}

	@Override
	public String toString() {
		return "ConfigOS [getId()=" + getId() + ", getImpr2Via()=" + getImpr2Via() + ", getImprAss()=" + getImprAss()
				+ ", getImprResumo()=" + getImprResumo() + ", getImprDetHorz()=" + getImprDetHorz()
				+ ", getDiasGarantia()=" + getDiasGarantia() + ", getObserv()=" + getObserv() + ", toString()="
				+ super.toString() + "]";
	}

}
