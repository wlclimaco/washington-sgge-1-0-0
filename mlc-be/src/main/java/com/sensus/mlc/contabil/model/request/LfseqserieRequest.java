package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfseqserie;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfseqserieRequest.
 *
 * @author - Washington
 */
public class LfseqserieRequest extends LightSelectionRequest
{

	/** The lfseqserie. */
	private Lfseqserie atributo;

	/** The lfseqserie. */
	private List<Lfseqserie> lfseqserie = new ArrayList<Lfseqserie>();

	/**
	 * Instantiates a new lfseqserie request.
	 */
	public LfseqserieRequest()
	{
	}

	/**
	 * Instantiates a new lfseqserie request.
	 *
	 * @param userContext the user context
	 */
	public LfseqserieRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfseqserie getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfseqserie atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfseqserie.
	 *
	 * @return the lfseqserie
	 */
	public List<Lfseqserie> getLfseqserie() {
		return lfseqserie;
	}

	/**
	 * Sets the lfseqserie.
	 *
	 * @param lfseqserie the new lfseqserie
	 */
	public void setLfseqserie(List<Lfseqserie> lfseqserie) {
		this.lfseqserie = lfseqserie;
	}

	/**
	 * Instantiates a new lfseqserie request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfseqserieRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfseqserie.
	 *
	 * @param lfseqserieValue the lfseqserie value
	 */
	public void addToLfseqserie(Lfseqserie lfseqserieValue)
	{
		getLfseqserie().add(lfseqserieValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfseqserieRequest [getLfseqserie()=" + getLfseqserie() + "getLfseqserie() = " + getLfseqserie()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

