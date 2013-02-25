package com.sensus.mlc.wui.dicionario.action;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.dicionario.bcf.IDicionarioBCF;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.dicionario.model.response.InquiryTelaResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;

/**
 * The Class SmartpointSearchAjaxAction is called when table is rendering.
 * 
 * @author Anke Doerfel-Parker
 */

public class DicionarioSearchAjaxAction extends ActionBase
{

	/** CONSTANTS *. */

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;

	/**
	 * The parameter name for the "tab" search parameter. Part of request parameter stack.
	 */
	public static final String HASH_TAB = "tb";

	/** The Constant HASH_LONG_RUNNING_PROCESS. */
	public static final String HASH_LONG_RUNNING_PROCESS = "process";

	/** The Constant HASH_SEARCH_VALUE. */
	public static final String HASH_SEARCH_VALUE = "query";

	/** The Constant PIPE. */
	public static final String PIPE = "[|]";

	/** The Constant COMA. */
	public static final String COMA = ",";

	/* Response Parameters */
	/**
	 * The result object that will be returned as JSON to the caller.
	 */
	private SearchJsonResult smartpointSearchResult;

	/* Internal Fields */
	/**
	 * The logger for this class.
	 */
	private static transient Log LOG = LogFactory.getLog(DicionarioSearchAjaxAction.class);



	
	private IDicionarioBCF dicionarioBCF;



	/** The response. */
	private Response response;


	
	private InquiryPaginationRequest inquiryPaginationRequest;

	/** The light selection request. */


	/** The list column. */
	private String listColumn;

	/**
	 * Search.
	 * 
	 * @return the string
	 */
	public String search()
	{
		try
		{
			InquiryTelaRequest inquiryDicionarioRequest = new InquiryTelaRequest();
			inquiryDicionarioRequest.setTelas(new ArrayList<Tela>());
			Tela tela = new Tela();
			tela.setId_tela(4);
			inquiryDicionarioRequest.getTelas().add(tela);
			InquiryTelaResponse inquiryDicionarioResponse =
					getDicionarioBCF().fetchAllTelas(inquiryDicionarioRequest);
			setResponse(inquiryDicionarioResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search SmartPoints", ex);
			}
		}

		return SUCCESS;
	}



	public SearchJsonResult getSmartpointSearchResult() {
		return smartpointSearchResult;
	}



	public void setSmartpointSearchResult(SearchJsonResult smartpointSearchResult) {
		this.smartpointSearchResult = smartpointSearchResult;
	}



	public IDicionarioBCF getDicionarioBCF() {
		return dicionarioBCF;
	}



	public void setDicionarioBCF(IDicionarioBCF dicionarioBCF) {
		this.dicionarioBCF = dicionarioBCF;
	}



	public Response getResponse() {
		return response;
	}



	public void setResponse(Response response) {
		this.response = response;
	}



	public InquiryPaginationRequest getInquiryPaginationRequest() {
		return inquiryPaginationRequest;
	}



	public void setInquiryPaginationRequest(
			InquiryPaginationRequest inquiryPaginationRequest) {
		this.inquiryPaginationRequest = inquiryPaginationRequest;
	}



	public String getListColumn() {
		return listColumn;
	}



	public void setListColumn(String listColumn) {
		this.listColumn = listColumn;
	}


}
