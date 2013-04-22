package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lffretecompra;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLffretecompraRequest.
 *
 * @author - Washington
 */
public class LffretecompraRequest extends LightSelectionRequest
{

	/** The lffretecompra. */
	private Lffretecompra atributo;

	/** The lffretecompra. */
	private List<Lffretecompra> lffretecompra = new ArrayList<Lffretecompra>();

	/**
	 * Instantiates a new lffretecompra request.
	 */
	public LffretecompraRequest()
	{
	}

	/**
	 * Instantiates a new lffretecompra request.
	 *
	 * @param userContext the user context
	 */
	public LffretecompraRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lffretecompra getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lffretecompra atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lffretecompra.
	 *
	 * @return the lffretecompra
	 */
	public List<Lffretecompra> getLffretecompra() {
		return lffretecompra;
	}

	/**
	 * Sets the lffretecompra.
	 *
	 * @param lffretecompra the new lffretecompra
	 */
	public void setLffretecompra(List<Lffretecompra> lffretecompra) {
		this.lffretecompra = lffretecompra;
	}

	/**
	 * Instantiates a new lffretecompra request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LffretecompraRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lffretecompra.
	 *
	 * @param lffretecompraValue the lffretecompra value
	 */
	public void addToLffretecompra(Lffretecompra lffretecompraValue)
	{
		getLffretecompra().add(lffretecompraValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LffretecompraRequest [getLffretecompra()=" + getLffretecompra() + "getLffretecompra() = " + getLffretecompra()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

