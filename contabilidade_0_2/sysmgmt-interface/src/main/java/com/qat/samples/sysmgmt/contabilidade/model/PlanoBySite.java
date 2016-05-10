package com.qat.samples.sysmgmt.contabilidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class PlanoBySite extends ModelCosmeDamiao
{

	private Integer id;

	private Plano plano;



	public PlanoBySite(Integer id)
	{
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Plano getPlano() {
		return plano;
	}



	public void setPlano(Plano plano) {
		this.plano = plano;
	}

}