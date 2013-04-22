package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfncmnbm;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfncmnbmRequest.
 *
 * @author - Washington
 */
public class LfncmnbmRequest extends LightSelectionRequest
{

	/** The lfncmnbm. */
	private Lfncmnbm atributo;

	/** The lfncmnbm. */
	private List<Lfncmnbm> lfncmnbm = new ArrayList<Lfncmnbm>();

	/**
	 * Instantiates a new lfncmnbm request.
	 */
	public LfncmnbmRequest()
	{
	}

	/**
	 * Instantiates a new lfncmnbm request.
	 *
	 * @param userContext the user context
	 */
	public LfncmnbmRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfncmnbm getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfncmnbm atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfncmnbm.
	 *
	 * @return the lfncmnbm
	 */
	public List<Lfncmnbm> getLfncmnbm() {
		return lfncmnbm;
	}

	/**
	 * Sets the lfncmnbm.
	 *
	 * @param lfncmnbm the new lfncmnbm
	 */
	public void setLfncmnbm(List<Lfncmnbm> lfncmnbm) {
		this.lfncmnbm = lfncmnbm;
	}

	/**
	 * Instantiates a new lfncmnbm request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfncmnbmRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfncmnbm.
	 *
	 * @param lfncmnbmValue the lfncmnbm value
	 */
	public void addToLfncmnbm(Lfncmnbm lfncmnbmValue)
	{
		getLfncmnbm().add(lfncmnbmValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfncmnbmRequest [getLfncmnbm()=" + getLfncmnbm() + "getLfncmnbm() = " + getLfncmnbm()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

