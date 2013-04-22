package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfmoddocfisc;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfmoddocfiscRequest.
 *
 * @author - Washington
 */
public class LfmoddocfiscRequest extends LightSelectionRequest
{

	/** The lfmoddocfisc. */
	private Lfmoddocfisc atributo;

	/** The lfmoddocfisc. */
	private List<Lfmoddocfisc> lfmoddocfisc = new ArrayList<Lfmoddocfisc>();

	/**
	 * Instantiates a new lfmoddocfisc request.
	 */
	public LfmoddocfiscRequest()
	{
	}

	/**
	 * Instantiates a new lfmoddocfisc request.
	 *
	 * @param userContext the user context
	 */
	public LfmoddocfiscRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfmoddocfisc getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfmoddocfisc atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfmoddocfisc.
	 *
	 * @return the lfmoddocfisc
	 */
	public List<Lfmoddocfisc> getLfmoddocfisc() {
		return lfmoddocfisc;
	}

	/**
	 * Sets the lfmoddocfisc.
	 *
	 * @param lfmoddocfisc the new lfmoddocfisc
	 */
	public void setLfmoddocfisc(List<Lfmoddocfisc> lfmoddocfisc) {
		this.lfmoddocfisc = lfmoddocfisc;
	}

	/**
	 * Instantiates a new lfmoddocfisc request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfmoddocfiscRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfmoddocfisc.
	 *
	 * @param lfmoddocfiscValue the lfmoddocfisc value
	 */
	public void addToLfmoddocfisc(Lfmoddocfisc lfmoddocfiscValue)
	{
		getLfmoddocfisc().add(lfmoddocfiscValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfmoddocfiscRequest [getLfmoddocfisc()=" + getLfmoddocfisc() + "getLfmoddocfisc() = " + getLfmoddocfisc()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

