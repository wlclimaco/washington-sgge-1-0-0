package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lffretevenda;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLffretevendaRequest.
 *
 * @author - Washington
 */
public class LffretevendaRequest extends LightSelectionRequest
{

	/** The lffretevenda. */
	private Lffretevenda atributo;

	/** The lffretevenda. */
	private List<Lffretevenda> lffretevenda = new ArrayList<Lffretevenda>();

	/**
	 * Instantiates a new lffretevenda request.
	 */
	public LffretevendaRequest()
	{
	}

	/**
	 * Instantiates a new lffretevenda request.
	 *
	 * @param userContext the user context
	 */
	public LffretevendaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lffretevenda getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lffretevenda atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lffretevenda.
	 *
	 * @return the lffretevenda
	 */
	public List<Lffretevenda> getLffretevenda() {
		return lffretevenda;
	}

	/**
	 * Sets the lffretevenda.
	 *
	 * @param lffretevenda the new lffretevenda
	 */
	public void setLffretevenda(List<Lffretevenda> lffretevenda) {
		this.lffretevenda = lffretevenda;
	}

	/**
	 * Instantiates a new lffretevenda request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LffretevendaRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lffretevenda.
	 *
	 * @param lffretevendaValue the lffretevenda value
	 */
	public void addToLffretevenda(Lffretevenda lffretevendaValue)
	{
		getLffretevenda().add(lffretevendaValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LffretevendaRequest [getLffretevenda()=" + getLffretevenda() + "getLffretevenda() = " + getLffretevenda()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

