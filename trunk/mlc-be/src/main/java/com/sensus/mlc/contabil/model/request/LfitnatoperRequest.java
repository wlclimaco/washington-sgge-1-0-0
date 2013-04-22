package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfitnatoper;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfitnatoperRequest.
 *
 * @author - Washington
 */
public class LfitnatoperRequest extends LightSelectionRequest
{

	/** The lfitnatoper. */
	private Lfitnatoper atributo;

	/** The lfitnatoper. */
	private List<Lfitnatoper> lfitnatoper = new ArrayList<Lfitnatoper>();

	/**
	 * Instantiates a new lfitnatoper request.
	 */
	public LfitnatoperRequest()
	{
	}

	/**
	 * Instantiates a new lfitnatoper request.
	 *
	 * @param userContext the user context
	 */
	public LfitnatoperRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfitnatoper getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfitnatoper atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfitnatoper.
	 *
	 * @return the lfitnatoper
	 */
	public List<Lfitnatoper> getLfitnatoper() {
		return lfitnatoper;
	}

	/**
	 * Sets the lfitnatoper.
	 *
	 * @param lfitnatoper the new lfitnatoper
	 */
	public void setLfitnatoper(List<Lfitnatoper> lfitnatoper) {
		this.lfitnatoper = lfitnatoper;
	}

	/**
	 * Instantiates a new lfitnatoper request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfitnatoperRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfitnatoper.
	 *
	 * @param lfitnatoperValue the lfitnatoper value
	 */
	public void addToLfitnatoper(Lfitnatoper lfitnatoperValue)
	{
		getLfitnatoper().add(lfitnatoperValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitnatoperRequest [getLfitnatoper()=" + getLfitnatoper() + "getLfitnatoper() = " + getLfitnatoper()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

