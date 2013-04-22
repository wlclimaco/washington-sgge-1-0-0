package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfnbm;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfnbmRequest.
 *
 * @author - Washington
 */
public class LfnbmRequest extends LightSelectionRequest
{

	/** The lfnbm. */
	private Lfnbm atributo;

	/** The lfnbm. */
	private List<Lfnbm> lfnbm = new ArrayList<Lfnbm>();

	/**
	 * Instantiates a new lfnbm request.
	 */
	public LfnbmRequest()
	{
	}

	/**
	 * Instantiates a new lfnbm request.
	 *
	 * @param userContext the user context
	 */
	public LfnbmRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfnbm getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfnbm atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfnbm.
	 *
	 * @return the lfnbm
	 */
	public List<Lfnbm> getLfnbm() {
		return lfnbm;
	}

	/**
	 * Sets the lfnbm.
	 *
	 * @param lfnbm the new lfnbm
	 */
	public void setLfnbm(List<Lfnbm> lfnbm) {
		this.lfnbm = lfnbm;
	}

	/**
	 * Instantiates a new lfnbm request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfnbmRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfnbm.
	 *
	 * @param lfnbmValue the lfnbm value
	 */
	public void addToLfnbm(Lfnbm lfnbmValue)
	{
		getLfnbm().add(lfnbmValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfnbmRequest [getLfnbm()=" + getLfnbm() + "getLfnbm() = " + getLfnbm()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

