package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lftipofisccli;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLftipofisccliRequest.
 *
 * @author - Washington
 */
public class LftipofisccliRequest extends LightSelectionRequest
{

	/** The lftipofisccli. */
	private Lftipofisccli atributo;

	/** The lftipofisccli. */
	private List<Lftipofisccli> lftipofisccli = new ArrayList<Lftipofisccli>();

	/**
	 * Instantiates a new lftipofisccli request.
	 */
	public LftipofisccliRequest()
	{
	}

	/**
	 * Instantiates a new lftipofisccli request.
	 *
	 * @param userContext the user context
	 */
	public LftipofisccliRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lftipofisccli getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lftipofisccli atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lftipofisccli.
	 *
	 * @return the lftipofisccli
	 */
	public List<Lftipofisccli> getLftipofisccli() {
		return lftipofisccli;
	}

	/**
	 * Sets the lftipofisccli.
	 *
	 * @param lftipofisccli the new lftipofisccli
	 */
	public void setLftipofisccli(List<Lftipofisccli> lftipofisccli) {
		this.lftipofisccli = lftipofisccli;
	}

	/**
	 * Instantiates a new lftipofisccli request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LftipofisccliRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lftipofisccli.
	 *
	 * @param lftipofisccliValue the lftipofisccli value
	 */
	public void addToLftipofisccli(Lftipofisccli lftipofisccliValue)
	{
		getLftipofisccli().add(lftipofisccliValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LftipofisccliRequest [getLftipofisccli()=" + getLftipofisccli() + "getLftipofisccli() = " + getLftipofisccli()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

