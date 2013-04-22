package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfserie;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfserieRequest.
 *
 * @author - Washington
 */
public class LfserieRequest extends LightSelectionRequest
{

	/** The lfserie. */
	private Lfserie atributo;

	/** The lfserie. */
	private List<Lfserie> lfserie = new ArrayList<Lfserie>();

	/**
	 * Instantiates a new lfserie request.
	 */
	public LfserieRequest()
	{
	}

	/**
	 * Instantiates a new lfserie request.
	 *
	 * @param userContext the user context
	 */
	public LfserieRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfserie getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfserie atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfserie.
	 *
	 * @return the lfserie
	 */
	public List<Lfserie> getLfserie() {
		return lfserie;
	}

	/**
	 * Sets the lfserie.
	 *
	 * @param lfserie the new lfserie
	 */
	public void setLfserie(List<Lfserie> lfserie) {
		this.lfserie = lfserie;
	}

	/**
	 * Instantiates a new lfserie request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfserieRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfserie.
	 *
	 * @param lfserieValue the lfserie value
	 */
	public void addToLfserie(Lfserie lfserieValue)
	{
		getLfserie().add(lfserieValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfserieRequest [getLfserie()=" + getLfserie() + "getLfserie() = " + getLfserie()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

