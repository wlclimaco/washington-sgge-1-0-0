package com.sensus.mlc.tabela.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Chaveestrangeira;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryChaveestrangeiraRequest.
 *
 * @author - Washington
 */
public class ChaveestrangeiraRequest extends LightSelectionRequest
{

	/** The chaveestrangeira. */
	private Chaveestrangeira atributo;

	/** The chaveestrangeira. */
	private List<Chaveestrangeira> chaveestrangeira = new ArrayList<Chaveestrangeira>();

	/**
	 * Instantiates a new chaveestrangeira request.
	 */
	public ChaveestrangeiraRequest()
	{
	}

	/**
	 * Instantiates a new chaveestrangeira request.
	 *
	 * @param userContext the user context
	 */
	public ChaveestrangeiraRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Chaveestrangeira getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Chaveestrangeira atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the chaveestrangeira.
	 *
	 * @return the chaveestrangeira
	 */
	public List<Chaveestrangeira> getChaveestrangeira() {
		return chaveestrangeira;
	}

	/**
	 * Sets the chaveestrangeira.
	 *
	 * @param chaveestrangeira the new chaveestrangeira
	 */
	public void setChaveestrangeira(List<Chaveestrangeira> chaveestrangeira) {
		this.chaveestrangeira = chaveestrangeira;
	}

	/**
	 * Instantiates a new chaveestrangeira request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public ChaveestrangeiraRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to chaveestrangeira.
	 *
	 * @param chaveestrangeiraValue the chaveestrangeira value
	 */
	public void addToChaveestrangeira(Chaveestrangeira chaveestrangeiraValue)
	{
		getChaveestrangeira().add(chaveestrangeiraValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ChaveestrangeiraRequest [getChaveestrangeira()=" + getChaveestrangeira() + "getChaveestrangeira() = " + getChaveestrangeira()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

