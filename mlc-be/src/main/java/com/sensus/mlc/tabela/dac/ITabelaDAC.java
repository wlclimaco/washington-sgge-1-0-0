package com.sensus.mlc.tabela.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.response.TabelaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington.
*
*/
public interface ITabelaDAC
{

/**
* Update Tabela.
*
* @param TabelaRequest the Tabela request
* @return the Tabela response
*/
public InternalResultsResponse<Tabela> updateTabela(TabelaRequest TabelaRequest);

/**
* Delete Tabela.
*
* @param TabelaRequest the Tabela request
* @return the Tabela response
*/
public InternalResponse deleteTabela(TabelaRequest TabelaRequest);

/**
* Fetch all Tabela.
*
* @param inquiryTabelaRequest the inquiryTabela request
* @return the inquiry Tabela response
*/
public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest);

/**
* Fetch Tabela by id.
*
* @param inquiryTabelaRequest the inquiryTabela request
* @return the internal results response
*/
public InternalResultsResponse<Tabela> fetchTabelaById(TabelaRequest TabelaRequest);

/**
* Generate file csv.
*
* @param inquiryTabelaRequest the inquiry Tabela request
* @return the inquiry Tabela response
*/
public InternalResponse generateFileCSV(InquiryTabelaRequest inquiryTabelaRequest);

/**
* Fetch all Tabela types.
*
* @param request the request
* @return the Tabela response
*/
public TabelaResponse fetchAllTabelaTypes(Request request);

/**
* Fetch all Tabela filial.
*
* @param request the request
* @return the Tabela response
*/
public TabelaResponse fetchAllTabelaFilial(Request request);

public InternalResultsResponse<Tabela> insertTabela(TabelaRequest TabelaRequest);
}


