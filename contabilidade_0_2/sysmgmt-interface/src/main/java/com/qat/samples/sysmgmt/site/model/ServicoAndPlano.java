package com.qat.samples.sysmgmt.site.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoBySite;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ServicoAndPlano extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Double valor;

	private Long dataInicio;

	private ServicoPlanoEnum servicoPlano;

	private Servico servicoList;

	private Plano planoList;

	public Integer getServicoPlanoEnumValue()
	{
		if (servicoPlano != null)
		{
			return servicoPlano.getValue();
		}
		return null;
	}

	public void setServicoPlanoEnumValue(Integer acaoTypeValue)
	{
		servicoPlano = ServicoPlanoEnum.enumForValue(acaoTypeValue);
	}

	public ServicoAndPlano()
	{

	}

	public ServicoAndPlano(int i, String string) {
		this.id = i;
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Long dataInicio) {
		this.dataInicio = dataInicio;
	}

	public ServicoPlanoEnum getServicoPlano() {
		return servicoPlano;
	}

	public void setServicoPlano(ServicoPlanoEnum servicoPlano) {
		this.servicoPlano = servicoPlano;
	}

	public Servico getServicoList() {
		return servicoList;
	}

	public void setServicoList(Servico servicoList) {
		this.servicoList = servicoList;
	}

	public Plano getPlanoList() {
		return planoList;
	}

	public void setPlanoList(Plano planoList) {
		this.planoList = planoList;
	}

	@Override
	public String toString() {
		return "ServicoAndPlano [getServicoPlanoEnumValue()=" + getServicoPlanoEnumValue() + ", getId()=" + getId()
				+ ", getValor()=" + getValor() + ", getDataInicio()=" + getDataInicio() + ", getServicoPlano()="
				+ getServicoPlano() + ", getServicoList()=" + getServicoList() + ", getPlanoList()=" + getPlanoList()
				+ ", toString()=" + super.toString() + "]";
	}

}
