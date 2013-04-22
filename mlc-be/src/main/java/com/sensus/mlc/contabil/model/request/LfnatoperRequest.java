package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfnatoper;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfnatoperRequest.
 *
 * @author - Washington
 */
public class LfnatoperRequest extends LightSelectionRequest
{

	/** The lfnatoper. */
	private Lfnatoper atributo;

	/** The lfnatoper. */
	private List<Lfnatoper> lfnatoper = new ArrayList<Lfnatoper>();

	/**
	 * Instantiates a new lfnatoper request.
	 */
	public LfnatoperRequest()
	{
	}

	/**
	 * Instantiates a new lfnatoper request.
	 *
	 * @param userContext the user context
	 */
	public LfnatoperRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfnatoper getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfnatoper atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfnatoper.
	 *
	 * @return the lfnatoper
	 */
	public List<Lfnatoper> getLfnatoper() {
		return lfnatoper;
	}

	/**
	 * Sets the lfnatoper.
	 *
	 * @param lfnatoper the new lfnatoper
	 */
	public void setLfnatoper(List<Lfnatoper> lfnatoper) {
		this.lfnatoper = lfnatoper;
	}

	/**
	 * Instantiates a new lfnatoper request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfnatoperRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfnatoper.
	 *
	 * @param lfnatoperValue the lfnatoper value
	 */
	public void addToLfnatoper(Lfnatoper lfnatoperValue)
	{
		getLfnatoper().add(lfnatoperValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfnatoperRequest [getLfnatoper()=" + getLfnatoper() + "getLfnatoper() = " + getLfnatoper()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

