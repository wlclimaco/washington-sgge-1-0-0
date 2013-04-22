package com.sensus.mlc.tabela.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Dominios;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryDominiosRequest.
 *
 * @author - Washington
 */
public class DominiosRequest extends LightSelectionRequest
{

	/** The dominios. */
	private Dominios atributo;

	/** The dominios. */
	private List<Dominios> dominios = new ArrayList<Dominios>();

	/**
	 * Instantiates a new dominios request.
	 */
	public DominiosRequest()
	{
	}

	/**
	 * Instantiates a new dominios request.
	 *
	 * @param userContext the user context
	 */
	public DominiosRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Dominios getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Dominios atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the dominios.
	 *
	 * @return the dominios
	 */
	public List<Dominios> getDominios() {
		return dominios;
	}

	/**
	 * Sets the dominios.
	 *
	 * @param dominios the new dominios
	 */
	public void setDominios(List<Dominios> dominios) {
		this.dominios = dominios;
	}

	/**
	 * Instantiates a new dominios request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public DominiosRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to dominios.
	 *
	 * @param dominiosValue the dominios value
	 */
	public void addToDominios(Dominios dominiosValue)
	{
		getDominios().add(dominiosValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "DominiosRequest [getDominios()=" + getDominios() + "getDominios() = " + getDominios()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

