package com.sensus.mlc.contabil.model.request;

import com.sensus.mlc.almoxarifado.model.Almoxarifado;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.tenant.model.request.TenantRequest;



/**
 * The Model Object Action.
 * 
 * @author - QAT Brazil
 */
public class AlmoxarifadoRequest extends TenantRequest
{

	/** The action. */
	private Almoxarifado almoxarifado;



	public Almoxarifado getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(Almoxarifado almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	/** The parent retry process. */
	private Integer parentRetry;

	/**
	 * @return the parentRetry
	 */
	public Integer getParentRetry()
	{
		return parentRetry;
	}

	/**
	 * @param parentRetry the parentRetry to set
	 */
	public void setParentRetry(Integer parentRetry)
	{
		this.parentRetry = parentRetry;
	}

}
