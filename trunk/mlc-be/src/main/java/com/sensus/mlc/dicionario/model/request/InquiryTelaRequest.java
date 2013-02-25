package com.sensus.mlc.dicionario.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.dicionario.model.Tela;

// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTagRequest.
 *
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class InquiryTelaRequest extends InquiryPaginationRequest
{

	/** The tag. */
	private List<Tela> Tela;



	/**
	 * Instantiates a new inquiry tag request.
	 *
	 * @param userContext the user context
	 */
	public InquiryTelaRequest(UserContext userContext)
	{
		super(userContext);
	}



	/**
	 * Gets the tela.
	 *
	 * @return the tela
	 */
	public List<Tela> getTela() {
		return Tela;
	}



	/**
	 * Sets the tela.
	 *
	 * @param tela the new tela
	 */
	public void setTela(List<Tela> tela) {
		Tela = tela;
	}



	/* (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString() {
		return "InquiryTelaRequest [Tela=" + Tela + ", getTela()=" + getTela()
				+ "]";
	}


}
