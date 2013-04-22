package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfitcompra;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfitcompraRequest.
 *
 * @author - Washington
 */
public class LfitcompraRequest extends LightSelectionRequest
{

	/** The lfitcompra. */
	private Lfitcompra atributo;

	/** The lfitcompra. */
	private List<Lfitcompra> lfitcompra = new ArrayList<Lfitcompra>();

	/**
	 * Instantiates a new lfitcompra request.
	 */
	public LfitcompraRequest()
	{
	}

	/**
	 * Instantiates a new lfitcompra request.
	 *
	 * @param userContext the user context
	 */
	public LfitcompraRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfitcompra getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfitcompra atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfitcompra.
	 *
	 * @return the lfitcompra
	 */
	public List<Lfitcompra> getLfitcompra() {
		return lfitcompra;
	}

	/**
	 * Sets the lfitcompra.
	 *
	 * @param lfitcompra the new lfitcompra
	 */
	public void setLfitcompra(List<Lfitcompra> lfitcompra) {
		this.lfitcompra = lfitcompra;
	}

	/**
	 * Instantiates a new lfitcompra request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfitcompraRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfitcompra.
	 *
	 * @param lfitcompraValue the lfitcompra value
	 */
	public void addToLfitcompra(Lfitcompra lfitcompraValue)
	{
		getLfitcompra().add(lfitcompraValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitcompraRequest [getLfitcompra()=" + getLfitcompra() + "getLfitcompra() = " + getLfitcompra()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

