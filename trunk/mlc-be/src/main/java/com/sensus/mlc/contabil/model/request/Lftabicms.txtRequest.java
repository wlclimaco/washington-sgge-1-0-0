package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lftabicms.txt;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLftabicms.txtRequest.
 *
 * @author - Washington
 */
public class Lftabicms.txtRequest extends LightSelectionRequest
{

	/** The lftabicms.txt. */
	private Lftabicms.txt atributo;

	/** The lftabicms.txt. */
	private List<Lftabicms.txt> lftabicms.txt = new ArrayList<Lftabicms.txt>();

	/**
	 * Instantiates a new lftabicms.txt request.
	 */
	public Lftabicms.txtRequest()
	{
	}

	/**
	 * Instantiates a new lftabicms.txt request.
	 *
	 * @param userContext the user context
	 */
	public Lftabicms.txtRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lftabicms.txt getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lftabicms.txt atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lftabicms.txt.
	 *
	 * @return the lftabicms.txt
	 */
	public List<Lftabicms.txt> getLftabicms.txt() {
		return lftabicms.txt;
	}

	/**
	 * Sets the lftabicms.txt.
	 *
	 * @param lftabicms.txt the new lftabicms.txt
	 */
	public void setLftabicms.txt(List<Lftabicms.txt> lftabicms.txt) {
		this.lftabicms.txt = lftabicms.txt;
	}

	/**
	 * Instantiates a new lftabicms.txt request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public Lftabicms.txtRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lftabicms.txt.
	 *
	 * @param lftabicms.txtValue the lftabicms.txt value
	 */
	public void addToLftabicms.txt(Lftabicms.txt lftabicms.txtValue)
	{
		getLftabicms.txt().add(lftabicms.txtValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "Lftabicms.txtRequest [getLftabicms.txt()=" + getLftabicms.txt() + "getLftabicms.txt() = " + getLftabicms.txt()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

