package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lffrete;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLffreteRequest.
 *
 * @author - Washington
 */
public class LffreteRequest extends LightSelectionRequest
{

	/** The lffrete. */
	private Lffrete atributo;

	/** The lffrete. */
	private List<Lffrete> lffrete = new ArrayList<Lffrete>();

	/**
	 * Instantiates a new lffrete request.
	 */
	public LffreteRequest()
	{
	}

	/**
	 * Instantiates a new lffrete request.
	 *
	 * @param userContext the user context
	 */
	public LffreteRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lffrete getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lffrete atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lffrete.
	 *
	 * @return the lffrete
	 */
	public List<Lffrete> getLffrete() {
		return lffrete;
	}

	/**
	 * Sets the lffrete.
	 *
	 * @param lffrete the new lffrete
	 */
	public void setLffrete(List<Lffrete> lffrete) {
		this.lffrete = lffrete;
	}

	/**
	 * Instantiates a new lffrete request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LffreteRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lffrete.
	 *
	 * @param lffreteValue the lffrete value
	 */
	public void addToLffrete(Lffrete lffreteValue)
	{
		getLffrete().add(lffreteValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LffreteRequest [getLffrete()=" + getLffrete() + "getLffrete() = " + getLffrete()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

