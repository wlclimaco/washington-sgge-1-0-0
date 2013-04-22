package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfncm;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfncmRequest.
 *
 * @author - Washington
 */
public class LfncmRequest extends LightSelectionRequest
{

	/** The lfncm. */
	private Lfncm atributo;

	/** The lfncm. */
	private List<Lfncm> lfncm = new ArrayList<Lfncm>();

	/**
	 * Instantiates a new lfncm request.
	 */
	public LfncmRequest()
	{
	}

	/**
	 * Instantiates a new lfncm request.
	 *
	 * @param userContext the user context
	 */
	public LfncmRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfncm getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfncm atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfncm.
	 *
	 * @return the lfncm
	 */
	public List<Lfncm> getLfncm() {
		return lfncm;
	}

	/**
	 * Sets the lfncm.
	 *
	 * @param lfncm the new lfncm
	 */
	public void setLfncm(List<Lfncm> lfncm) {
		this.lfncm = lfncm;
	}

	/**
	 * Instantiates a new lfncm request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfncmRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfncm.
	 *
	 * @param lfncmValue the lfncm value
	 */
	public void addToLfncm(Lfncm lfncmValue)
	{
		getLfncm().add(lfncmValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfncmRequest [getLfncm()=" + getLfncm() + "getLfncm() = " + getLfncm()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

