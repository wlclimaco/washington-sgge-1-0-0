package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfclfiscal;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfclfiscalRequest.
 *
 * @author - Washington
 */
public class LfclfiscalRequest extends LightSelectionRequest
{

	/** The lfclfiscal. */
	private Lfclfiscal atributo;

	/** The lfclfiscal. */
	private List<Lfclfiscal> lfclfiscal = new ArrayList<Lfclfiscal>();

	/**
	 * Instantiates a new lfclfiscal request.
	 */
	public LfclfiscalRequest()
	{
	}

	/**
	 * Instantiates a new lfclfiscal request.
	 *
	 * @param userContext the user context
	 */
	public LfclfiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfclfiscal getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfclfiscal atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfclfiscal.
	 *
	 * @return the lfclfiscal
	 */
	public List<Lfclfiscal> getLfclfiscal() {
		return lfclfiscal;
	}

	/**
	 * Sets the lfclfiscal.
	 *
	 * @param lfclfiscal the new lfclfiscal
	 */
	public void setLfclfiscal(List<Lfclfiscal> lfclfiscal) {
		this.lfclfiscal = lfclfiscal;
	}

	/**
	 * Instantiates a new lfclfiscal request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfclfiscalRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfclfiscal.
	 *
	 * @param lfclfiscalValue the lfclfiscal value
	 */
	public void addToLfclfiscal(Lfclfiscal lfclfiscalValue)
	{
		getLfclfiscal().add(lfclfiscalValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfclfiscalRequest [getLfclfiscal()=" + getLfclfiscal() + "getLfclfiscal() = " + getLfclfiscal()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

