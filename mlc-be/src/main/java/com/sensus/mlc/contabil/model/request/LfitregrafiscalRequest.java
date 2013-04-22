package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfitregrafiscal;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfitregrafiscalRequest.
 *
 * @author - Washington
 */
public class LfitregrafiscalRequest extends LightSelectionRequest
{

	/** The lfitregrafiscal. */
	private Lfitregrafiscal atributo;

	/** The lfitregrafiscal. */
	private List<Lfitregrafiscal> lfitregrafiscal = new ArrayList<Lfitregrafiscal>();

	/**
	 * Instantiates a new lfitregrafiscal request.
	 */
	public LfitregrafiscalRequest()
	{
	}

	/**
	 * Instantiates a new lfitregrafiscal request.
	 *
	 * @param userContext the user context
	 */
	public LfitregrafiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfitregrafiscal getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfitregrafiscal atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfitregrafiscal.
	 *
	 * @return the lfitregrafiscal
	 */
	public List<Lfitregrafiscal> getLfitregrafiscal() {
		return lfitregrafiscal;
	}

	/**
	 * Sets the lfitregrafiscal.
	 *
	 * @param lfitregrafiscal the new lfitregrafiscal
	 */
	public void setLfitregrafiscal(List<Lfitregrafiscal> lfitregrafiscal) {
		this.lfitregrafiscal = lfitregrafiscal;
	}

	/**
	 * Instantiates a new lfitregrafiscal request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfitregrafiscalRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfitregrafiscal.
	 *
	 * @param lfitregrafiscalValue the lfitregrafiscal value
	 */
	public void addToLfitregrafiscal(Lfitregrafiscal lfitregrafiscalValue)
	{
		getLfitregrafiscal().add(lfitregrafiscalValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitregrafiscalRequest [getLfitregrafiscal()=" + getLfitregrafiscal() + "getLfitregrafiscal() = " + getLfitregrafiscal()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

