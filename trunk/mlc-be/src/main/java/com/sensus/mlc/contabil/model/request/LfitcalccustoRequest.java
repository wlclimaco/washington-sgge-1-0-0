package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfitcalccusto;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfitcalccustoRequest.
 *
 * @author - Washington
 */
public class LfitcalccustoRequest extends LightSelectionRequest
{

	/** The lfitcalccusto. */
	private Lfitcalccusto atributo;

	/** The lfitcalccusto. */
	private List<Lfitcalccusto> lfitcalccusto = new ArrayList<Lfitcalccusto>();

	/**
	 * Instantiates a new lfitcalccusto request.
	 */
	public LfitcalccustoRequest()
	{
	}

	/**
	 * Instantiates a new lfitcalccusto request.
	 *
	 * @param userContext the user context
	 */
	public LfitcalccustoRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfitcalccusto getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfitcalccusto atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfitcalccusto.
	 *
	 * @return the lfitcalccusto
	 */
	public List<Lfitcalccusto> getLfitcalccusto() {
		return lfitcalccusto;
	}

	/**
	 * Sets the lfitcalccusto.
	 *
	 * @param lfitcalccusto the new lfitcalccusto
	 */
	public void setLfitcalccusto(List<Lfitcalccusto> lfitcalccusto) {
		this.lfitcalccusto = lfitcalccusto;
	}

	/**
	 * Instantiates a new lfitcalccusto request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfitcalccustoRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfitcalccusto.
	 *
	 * @param lfitcalccustoValue the lfitcalccusto value
	 */
	public void addToLfitcalccusto(Lfitcalccusto lfitcalccustoValue)
	{
		getLfitcalccusto().add(lfitcalccustoValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitcalccustoRequest [getLfitcalccusto()=" + getLfitcalccusto() + "getLfitcalccusto() = " + getLfitcalccusto()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

