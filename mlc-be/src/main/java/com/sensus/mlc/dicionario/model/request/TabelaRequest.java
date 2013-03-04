package com.sensus.mlc.dicionario.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tenant.model.Tenant;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTagRequest.
 *
 * @author - Alex Barros - QAT
 */
public class TabelaRequest extends LightSelectionRequest
{

	/** The tag. */
	private List<Tela> tela;


	/**
	 * Instantiates a new tag request.
	 */
	public TabelaRequest()
	{
	}

	/**
	 * Instantiates a new tag request.
	 *
	 * @param userContext the user context
	 */
	public TabelaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new tag request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public TabelaRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the tela.
	 *
	 * @return the tela
	 */
	public List<Tela> getTela() {
		return tela;
	}

	/**
	 * Sets the tela.
	 *
	 * @param tela the new tela
	 */
	public void setTela(List<Tela> tela) {
		this.tela = tela;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightSelectionRequest#toString()
	 */
	@Override
	public String toString() {
		return "TelaRequest [tela=" + tela + ", getTela()=" + getTela() + "]";
	}


}
