package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lflivrofiscal;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLflivrofiscalRequest.
 *
 * @author - Washington
 */
public class LflivrofiscalRequest extends LightSelectionRequest
{

	/** The lflivrofiscal. */
	private Lflivrofiscal atributo;

	/** The lflivrofiscal. */
	private List<Lflivrofiscal> lflivrofiscal = new ArrayList<Lflivrofiscal>();

	/**
	 * Instantiates a new lflivrofiscal request.
	 */
	public LflivrofiscalRequest()
	{
	}

	/**
	 * Instantiates a new lflivrofiscal request.
	 *
	 * @param userContext the user context
	 */
	public LflivrofiscalRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lflivrofiscal getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lflivrofiscal atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lflivrofiscal.
	 *
	 * @return the lflivrofiscal
	 */
	public List<Lflivrofiscal> getLflivrofiscal() {
		return lflivrofiscal;
	}

	/**
	 * Sets the lflivrofiscal.
	 *
	 * @param lflivrofiscal the new lflivrofiscal
	 */
	public void setLflivrofiscal(List<Lflivrofiscal> lflivrofiscal) {
		this.lflivrofiscal = lflivrofiscal;
	}

	/**
	 * Instantiates a new lflivrofiscal request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LflivrofiscalRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lflivrofiscal.
	 *
	 * @param lflivrofiscalValue the lflivrofiscal value
	 */
	public void addToLflivrofiscal(Lflivrofiscal lflivrofiscalValue)
	{
		getLflivrofiscal().add(lflivrofiscalValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LflivrofiscalRequest [getLflivrofiscal()=" + getLflivrofiscal() + "getLflivrofiscal() = " + getLflivrofiscal()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

