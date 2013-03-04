package com.sensus.mlc.dicionario.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.dicionario.model.Tabela;
import com.sensus.mlc.dicionario.model.Tela;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTagRequest.
 *
 * @author - Washington Costa
 */
@SuppressWarnings("serial")
public class InquiryTabelaRequest extends InquiryPaginationRequest
{

	/** The tag. */
	private List<Tabela> Tela;



	/**
	 * Instantiates a new inquiry tag request.
	 *
	 * @param userContext the user context
	 */
	public InquiryTabelaRequest(UserContext userContext)
	{
		super(userContext);
	}



	/**
	 * Gets the tela.
	 *
	 * @return the tela
	 */
	public List<Tabela> getTela() {
		return Tela;
	}



	/**
	 * Sets the tela.
	 *
	 * @param tela the new tela
	 */
	public void setTela(List<Tabela> tela) {
		Tela = tela;
	}



	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryTabelaRequest [Tela=" + Tela + ", getTela()="
				+ getTela() + "]";
	}


}
