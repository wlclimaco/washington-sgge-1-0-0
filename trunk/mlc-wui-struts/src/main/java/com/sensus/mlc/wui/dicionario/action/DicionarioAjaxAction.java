package com.sensus.mlc.wui.dicionario.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.dicionario.bcf.IDicionarioBCF;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.response.TelaResponse;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.group.action.GroupAjaxAction;


@SuppressWarnings("serial")
public class DicionarioAjaxAction extends ActionBase
{

	private static final Log LOG = LogFactory.getLog(GroupAjaxAction.class);

	/**
	 * The Group BCF object.
	 */
	private IDicionarioBCF dicionarioBCF;

	/** The group request. */
	private TelaRequest dicionarioRequest;

	/** The response. */
	private Response response;

	/**
	 * Initiate delete group.
	 * 
	 * @return the string
	 */
	public String fetchTelaById()
	{
		try
		{
			
			TelaRequest dicionarioRequest = new TelaRequest();
			Tela tela = new Tela();
			tela.setId_tela(4);
			dicionarioRequest.setTela(tela);
			TelaResponse dicionarioResponse = getDicionarioBCF().fetchTelaById(dicionarioRequest);
			setResponse(dicionarioResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error initiating delete group", e);
			}

		}
		return SUCCESS;
	}

	public IDicionarioBCF getDicionarioBCF() {
		return dicionarioBCF;
	}

	public void setDicionarioBCF(IDicionarioBCF dicionarioBCF) {
		this.dicionarioBCF = dicionarioBCF;
	}

	public TelaRequest getDicionarioRequest() {
		return dicionarioRequest;
	}

	public void setDicionarioRequest(TelaRequest dicionarioRequest) {
		this.dicionarioRequest = dicionarioRequest;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
