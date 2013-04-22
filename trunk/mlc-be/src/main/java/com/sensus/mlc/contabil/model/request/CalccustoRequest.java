package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Calccusto;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryCalccustoRequest.
 *
 * @author - Washington
 */
public class CalccustoRequest extends LightSelectionRequest
{

	/** The calccusto. */
	private Calccusto atributo;

	/** The calccusto. */
	private List<Calccusto> calccusto = new ArrayList<Calccusto>();

	/**
	 * Instantiates a new calccusto request.
	 */
	public CalccustoRequest()
	{
	}

	/**
	 * Instantiates a new calccusto request.
	 *
	 * @param userContext the user context
	 */
	public CalccustoRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Calccusto getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Calccusto atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the calccusto.
	 *
	 * @return the calccusto
	 */
	public List<Calccusto> getCalccusto() {
		return calccusto;
	}

	/**
	 * Sets the calccusto.
	 *
	 * @param calccusto the new calccusto
	 */
	public void setCalccusto(List<Calccusto> calccusto) {
		this.calccusto = calccusto;
	}

	/**
	 * Instantiates a new calccusto request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public CalccustoRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to calccusto.
	 *
	 * @param calccustoValue the calccusto value
	 */
	public void addToCalccusto(Calccusto calccustoValue)
	{
		getCalccusto().add(calccustoValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "CalccustoRequest [getCalccusto()=" + getCalccusto() + "getCalccusto() = " + getCalccusto()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

