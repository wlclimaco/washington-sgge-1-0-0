package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfitvenda;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfitvendaRequest.
 *
 * @author - Washington
 */
public class LfitvendaRequest extends LightSelectionRequest
{

	/** The lfitvenda. */
	private Lfitvenda atributo;

	/** The lfitvenda. */
	private List<Lfitvenda> lfitvenda = new ArrayList<Lfitvenda>();

	/**
	 * Instantiates a new lfitvenda request.
	 */
	public LfitvendaRequest()
	{
	}

	/**
	 * Instantiates a new lfitvenda request.
	 *
	 * @param userContext the user context
	 */
	public LfitvendaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfitvenda getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfitvenda atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfitvenda.
	 *
	 * @return the lfitvenda
	 */
	public List<Lfitvenda> getLfitvenda() {
		return lfitvenda;
	}

	/**
	 * Sets the lfitvenda.
	 *
	 * @param lfitvenda the new lfitvenda
	 */
	public void setLfitvenda(List<Lfitvenda> lfitvenda) {
		this.lfitvenda = lfitvenda;
	}

	/**
	 * Instantiates a new lfitvenda request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfitvendaRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfitvenda.
	 *
	 * @param lfitvendaValue the lfitvenda value
	 */
	public void addToLfitvenda(Lfitvenda lfitvendaValue)
	{
		getLfitvenda().add(lfitvendaValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfitvendaRequest [getLfitvenda()=" + getLfitvenda() + "getLfitvenda() = " + getLfitvenda()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

