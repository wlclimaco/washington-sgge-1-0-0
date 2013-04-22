package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lftrattrib;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLftrattribRequest.
 *
 * @author - Washington
 */
public class LftrattribRequest extends LightSelectionRequest
{

	/** The lftrattrib. */
	private Lftrattrib atributo;

	/** The lftrattrib. */
	private List<Lftrattrib> lftrattrib = new ArrayList<Lftrattrib>();

	/**
	 * Instantiates a new lftrattrib request.
	 */
	public LftrattribRequest()
	{
	}

	/**
	 * Instantiates a new lftrattrib request.
	 *
	 * @param userContext the user context
	 */
	public LftrattribRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lftrattrib getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lftrattrib atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lftrattrib.
	 *
	 * @return the lftrattrib
	 */
	public List<Lftrattrib> getLftrattrib() {
		return lftrattrib;
	}

	/**
	 * Sets the lftrattrib.
	 *
	 * @param lftrattrib the new lftrattrib
	 */
	public void setLftrattrib(List<Lftrattrib> lftrattrib) {
		this.lftrattrib = lftrattrib;
	}

	/**
	 * Instantiates a new lftrattrib request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LftrattribRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lftrattrib.
	 *
	 * @param lftrattribValue the lftrattrib value
	 */
	public void addToLftrattrib(Lftrattrib lftrattribValue)
	{
		getLftrattrib().add(lftrattribValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LftrattribRequest [getLftrattrib()=" + getLftrattrib() + "getLftrattrib() = " + getLftrattrib()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

