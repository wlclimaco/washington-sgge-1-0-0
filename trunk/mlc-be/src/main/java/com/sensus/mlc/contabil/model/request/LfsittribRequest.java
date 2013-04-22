package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfsittrib;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfsittribRequest.
 *
 * @author - Washington
 */
public class LfsittribRequest extends LightSelectionRequest
{

	/** The lfsittrib. */
	private Lfsittrib atributo;

	/** The lfsittrib. */
	private List<Lfsittrib> lfsittrib = new ArrayList<Lfsittrib>();

	/**
	 * Instantiates a new lfsittrib request.
	 */
	public LfsittribRequest()
	{
	}

	/**
	 * Instantiates a new lfsittrib request.
	 *
	 * @param userContext the user context
	 */
	public LfsittribRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfsittrib getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfsittrib atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfsittrib.
	 *
	 * @return the lfsittrib
	 */
	public List<Lfsittrib> getLfsittrib() {
		return lfsittrib;
	}

	/**
	 * Sets the lfsittrib.
	 *
	 * @param lfsittrib the new lfsittrib
	 */
	public void setLfsittrib(List<Lfsittrib> lfsittrib) {
		this.lfsittrib = lfsittrib;
	}

	/**
	 * Instantiates a new lfsittrib request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfsittribRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfsittrib.
	 *
	 * @param lfsittribValue the lfsittrib value
	 */
	public void addToLfsittrib(Lfsittrib lfsittribValue)
	{
		getLfsittrib().add(lfsittribValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfsittribRequest [getLfsittrib()=" + getLfsittrib() + "getLfsittrib() = " + getLfsittrib()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

