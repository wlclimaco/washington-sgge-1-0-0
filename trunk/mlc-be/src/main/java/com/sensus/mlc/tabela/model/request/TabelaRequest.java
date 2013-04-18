package com.sensus.mlc.tabela.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTabelaRequest.
 *
 * @author - Washington
 */
public class TabelaRequest extends LightSelectionRequest
{

	/** The tabela. */
	private Tabela atributo;

	/** The tabela. */
	private List<Tabela> tabela = new ArrayList<Tabela>();

	/**
	 * Instantiates a new tabela request.
	 */
	public TabelaRequest()
	{
	}

	/**
	 * Instantiates a new tabela request.
	 *
	 * @param userContext the user context
	 */
	public TabelaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Tabela getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Tabela atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the tabela.
	 *
	 * @return the tabela
	 */
	public List<Tabela> getTabela() {
		return tabela;
	}

	/**
	 * Sets the tabela.
	 *
	 * @param tabela the new tabela
	 */
	public void setTabela(List<Tabela> tabela) {
		this.tabela = tabela;
	}

	/**
	 * Instantiates a new tabela request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public TabelaRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to tabela.
	 *
	 * @param tabelaValue the tabela value
	 */
	public void addToTabela(Tabela tabelaValue)
	{
		getTabela().add(tabelaValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "TabelaRequest [getTabela()=" + getTabela() + "getTabela() = " + getTabela()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

