package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfmodnota;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfmodnotaRequest.
 *
 * @author - Washington
 */
public class LfmodnotaRequest extends LightSelectionRequest
{

	/** The lfmodnota. */
	private Lfmodnota atributo;

	/** The lfmodnota. */
	private List<Lfmodnota> lfmodnota = new ArrayList<Lfmodnota>();

	/**
	 * Instantiates a new lfmodnota request.
	 */
	public LfmodnotaRequest()
	{
	}

	/**
	 * Instantiates a new lfmodnota request.
	 *
	 * @param userContext the user context
	 */
	public LfmodnotaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfmodnota getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfmodnota atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfmodnota.
	 *
	 * @return the lfmodnota
	 */
	public List<Lfmodnota> getLfmodnota() {
		return lfmodnota;
	}

	/**
	 * Sets the lfmodnota.
	 *
	 * @param lfmodnota the new lfmodnota
	 */
	public void setLfmodnota(List<Lfmodnota> lfmodnota) {
		this.lfmodnota = lfmodnota;
	}

	/**
	 * Instantiates a new lfmodnota request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfmodnotaRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfmodnota.
	 *
	 * @param lfmodnotaValue the lfmodnota value
	 */
	public void addToLfmodnota(Lfmodnota lfmodnotaValue)
	{
		getLfmodnota().add(lfmodnotaValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfmodnotaRequest [getLfmodnota()=" + getLfmodnota() + "getLfmodnota() = " + getLfmodnota()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

