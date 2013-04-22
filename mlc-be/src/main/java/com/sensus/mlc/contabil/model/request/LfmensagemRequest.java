package com.sensus.mlc.contabil.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.tabela.model.Lfmensagem;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryLfmensagemRequest.
 *
 * @author - Washington
 */
public class LfmensagemRequest extends LightSelectionRequest
{

	/** The lfmensagem. */
	private Lfmensagem atributo;

	/** The lfmensagem. */
	private List<Lfmensagem> lfmensagem = new ArrayList<Lfmensagem>();

	/**
	 * Instantiates a new lfmensagem request.
	 */
	public LfmensagemRequest()
	{
	}

	/**
	 * Instantiates a new lfmensagem request.
	 *
	 * @param userContext the user context
	 */
	public LfmensagemRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the atributo.
	 *
	 * @return the atributo
	 */
	public Lfmensagem getAtributo() {
		return atributo;
	}

	/**
	 * Sets the atributo.
	 *
	 * @param atributo the new atributo
	 */
	public void setAtributo(Lfmensagem atributo) {
		this.atributo = atributo;
	}

	/**
	 * Gets the lfmensagem.
	 *
	 * @return the lfmensagem
	 */
	public List<Lfmensagem> getLfmensagem() {
		return lfmensagem;
	}

	/**
	 * Sets the lfmensagem.
	 *
	 * @param lfmensagem the new lfmensagem
	 */
	public void setLfmensagem(List<Lfmensagem> lfmensagem) {
		this.lfmensagem = lfmensagem;
	}

	/**
	 * Instantiates a new lfmensagem request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LfmensagemRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}


	/**
	 * Adds the to lfmensagem.
	 *
	 * @param lfmensagemValue the lfmensagem value
	 */
	public void addToLfmensagem(Lfmensagem lfmensagemValue)
	{
		getLfmensagem().add(lfmensagemValue);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LfmensagemRequest [getLfmensagem()=" + getLfmensagem() + "getLfmensagem() = " + getLfmensagem()
				+ ", getIsMonitored()=" + isMonitored() + ", getSearchLight()="
				+ getSearchLight() + ", getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}

