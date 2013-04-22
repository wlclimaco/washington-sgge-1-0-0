package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfregrafiscal;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfregrafiscalRequest.
 *
 * @author - Washington
 */
public class LfregrafiscalRequest extends LightSelectionRequest
{

	/** The lfregrafiscal. */
	private Lfregrafiscal atributo;

	/** The lfregrafiscal. */
	private List<Lfregrafiscal> lfregrafiscal = new ArrayList<Lfregrafiscal>();

	/**
	 * Instantiates a new lfregrafiscal request.
	 */
	public LfregrafiscalRequest()
	{
	}

	/**
	 * Instantiates a new lfregrafiscal request.
	 *
	 * @param userContext the user context
	 */
	public LfregrafiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfregrafiscal getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfregrafiscal atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfregrafiscal.
	 *
	 * @return the lfregrafiscal
	 */
	public List<Lfregrafiscal> getLfregrafiscal() {
		return lfregrafiscal;
	}

	/**
	 * Sets the lfregrafiscal.
	 *
	 * @param lfregrafiscal the new lfregrafiscal
	 */
	public void setLfregrafiscal(List<Lfregrafiscal> lfregrafiscal) {
		this.lfregrafiscal = lfregrafiscal;
	}

	/**
	 * Instantiates a new lfregrafiscal request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfregrafiscalRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfregrafiscal.
	 *
	 * @param lfregrafiscalValue the lfregrafiscal value
	 */
	public void addToLfregrafiscal(Lfregrafiscal lfregrafiscalValue)
	{
		getLfregrafiscal().add(lfregrafiscalValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfregrafiscalRequest [getLfregrafiscal()=" + getLfregrafiscal() + "getLfregrafiscal() = " + getLfregrafiscal()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

