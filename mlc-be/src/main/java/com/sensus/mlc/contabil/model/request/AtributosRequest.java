package com.sensus.mlc.tabela.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Atributos;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryAtributosRequest.
 *
 * @author - Washington
 */
public class AtributosRequest extends LightSelectionRequest
{

	/** The atributos. */
	private Atributos atributo;

	/** The atributos. */
	private List<Atributos> atributos = new ArrayList<Atributos>();

	/**
	 * Instantiates a new atributos request.
	 */
	public AtributosRequest()
	{
	}

	/**
	 * Instantiates a new atributos request.
	 *
	 * @param userContext the user context
	 */
	public AtributosRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Atributos getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Atributos atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the atributos.
	 *
	 * @return the atributos
	 */
	public List<Atributos> getAtributos() {
		return atributos;
	}

	/**
	 * Sets the atributos.
	 *
	 * @param atributos the new atributos
	 */
	public void setAtributos(List<Atributos> atributos) {
		this.atributos = atributos;
	}

	/**
	 * Instantiates a new atributos request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public AtributosRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to atributos.
	 *
	 * @param atributosValue the atributos value
	 */
	public void addToAtributos(Atributos atributosValue)
	{
		getAtributos().add(atributosValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "AtributosRequest [getAtributos()=" + getAtributos() + "getAtributos() = " + getAtributos()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

