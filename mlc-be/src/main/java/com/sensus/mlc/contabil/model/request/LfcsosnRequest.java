package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfcsosn;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfcsosnRequest.
 *
 * @author - Washington
 */
public class LfcsosnRequest extends LightSelectionRequest
{

	/** The lfcsosn. */
	private Lfcsosn atributo;

	/** The lfcsosn. */
	private List<Lfcsosn> lfcsosn = new ArrayList<Lfcsosn>();

	/**
	 * Instantiates a new lfcsosn request.
	 */
	public LfcsosnRequest()
	{
	}

	/**
	 * Instantiates a new lfcsosn request.
	 *
	 * @param userContext the user context
	 */
	public LfcsosnRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfcsosn getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfcsosn atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfcsosn.
	 *
	 * @return the lfcsosn
	 */
	public List<Lfcsosn> getLfcsosn() {
		return lfcsosn;
	}

	/**
	 * Sets the lfcsosn.
	 *
	 * @param lfcsosn the new lfcsosn
	 */
	public void setLfcsosn(List<Lfcsosn> lfcsosn) {
		this.lfcsosn = lfcsosn;
	}

	/**
	 * Instantiates a new lfcsosn request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfcsosnRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfcsosn.
	 *
	 * @param lfcsosnValue the lfcsosn value
	 */
	public void addToLfcsosn(Lfcsosn lfcsosnValue)
	{
		getLfcsosn().add(lfcsosnValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfcsosnRequest [getLfcsosn()=" + getLfcsosn() + "getLfcsosn() = " + getLfcsosn()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

