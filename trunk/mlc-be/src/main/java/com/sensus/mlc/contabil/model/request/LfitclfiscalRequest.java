package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfitclfiscal;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfitclfiscalRequest.
 *
 * @author - Washington
 */
public class LfitclfiscalRequest extends LightSelectionRequest
{

	/** The lfitclfiscal. */
	private Lfitclfiscal atributo;

	/** The lfitclfiscal. */
	private List<Lfitclfiscal> lfitclfiscal = new ArrayList<Lfitclfiscal>();

	/**
	 * Instantiates a new lfitclfiscal request.
	 */
	public LfitclfiscalRequest()
	{
	}

	/**
	 * Instantiates a new lfitclfiscal request.
	 *
	 * @param userContext the user context
	 */
	public LfitclfiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfitclfiscal getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfitclfiscal atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfitclfiscal.
	 *
	 * @return the lfitclfiscal
	 */
	public List<Lfitclfiscal> getLfitclfiscal() {
		return lfitclfiscal;
	}

	/**
	 * Sets the lfitclfiscal.
	 *
	 * @param lfitclfiscal the new lfitclfiscal
	 */
	public void setLfitclfiscal(List<Lfitclfiscal> lfitclfiscal) {
		this.lfitclfiscal = lfitclfiscal;
	}

	/**
	 * Instantiates a new lfitclfiscal request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfitclfiscalRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfitclfiscal.
	 *
	 * @param lfitclfiscalValue the lfitclfiscal value
	 */
	public void addToLfitclfiscal(Lfitclfiscal lfitclfiscalValue)
	{
		getLfitclfiscal().add(lfitclfiscalValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitclfiscalRequest [getLfitclfiscal()=" + getLfitclfiscal() + "getLfitclfiscal() = " + getLfitclfiscal()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

