package com.qat.samples.sysmgmt.contabilidade.model;

import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class PlanoByServico extends ModelCosmeDamiao
{

	private Integer id;

	private Servico servico;



	public PlanoByServico()
	{
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Servico getServico() {
		return servico;
	}



	public void setServico(Servico servico) {
		this.servico = servico;
	}



	@Override
	public String toString() {
		return "PlanoByServico [getId()=" + getId() + ", getServico()=" + getServico() + ", toString()="
				+ super.toString() + "]";
	}


}