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
* @author - Washington
*
*/
public interface ITabelaDAC
{

/**
* Update tabela.
*
* @param tabelaRequest the tabela request
* @return the tabela response
*/
public InternalResultsResponse<Tabela> updateTabela(TabelaRequest tabelaRequest);

/**
* Delete tabela.
*
* @param tabelaRequest the tabela request
* @return the tabela response
*/
public InternalResponse deleteTabela(TabelaRequest tabelaRequest);

/**
* Fetch all tabela.
*
* @param inquirytabelaRequest the inquirytabela request
* @return the inquiry tabela response
*/
public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquirytabelaRequest);

/**
* Fetch tabela by id.
*
* @param inquirytabelaRequest the inquirytabela request
* @return the internal results response
*/
public InternalResultsResponse<Tabela> fetchTabelaById(TabelaRequest tabelaRequest);

/**
* Generate file csv.
*
* @param inquiryTabelaRequest the inquiry tabela request
* @return the inquiry tabela response
*/
public InternalResponse generateFileCSV(InquiryTabelaRequest inquiryTabelaRequest);

/**
* Fetch all tabela types.
*
* @param request the request
* @return the tabela response
*/
public TabelaResponse fetchAllTabelaTypes(Request request);

/**
* Fetch all tabela filial.
*
* @param request the request
* @return the tabela response
*/
public TabelaResponse fetchAllTabelaFilial(Request request);

public InternalResultsResponse<Tabela> insertTabela(TabelaRequest tabelaRequest);
}


