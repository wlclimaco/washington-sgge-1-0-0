package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto
import com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.response.LfitcalccustoResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfitcalccustoDAC
{

/**
* Update lfitcalccusto.
*
* @param lfitcalccustoRequest the lfitcalccusto request
* @return the lfitcalccusto response
*/
public InternalResultsResponse<Lfitcalccusto> updateLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest);

/**
* Delete lfitcalccusto.
*
* @param lfitcalccustoRequest the lfitcalccusto request
* @return the lfitcalccusto response
*/
public InternalResponse deleteLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest);

/**
* Fetch all lfitcalccusto.
*
* @param inquirylfitcalccustoRequest the inquirylfitcalccusto request
* @return the inquiry lfitcalccusto response
*/
public InternalResultsResponse<Lfitcalccusto> fetchAllLfitcalccusto(InquiryLfitcalccustoRequest inquirylfitcalccustoRequest);

/**
* Fetch lfitcalccusto by id.
*
* @param inquirylfitcalccustoRequest the inquirylfitcalccusto request
* @return the internal results response
*/
public InternalResultsResponse<Lfitcalccusto> fetchLfitcalccustoById(LfitcalccustoRequest lfitcalccustoRequest);

/**
* Generate file csv.
*
* @param inquiryLfitcalccustoRequest the inquiry lfitcalccusto request
* @return the inquiry lfitcalccusto response
*/
public InternalResponse generateFileCSV(InquiryLfitcalccustoRequest inquiryLfitcalccustoRequest);

/**
* Fetch all lfitcalccusto types.
*
* @param request the request
* @return the lfitcalccusto response
*/
public LfitcalccustoResponse fetchAllLfitcalccustoTypes(Request request);

/**
* Fetch all lfitcalccusto filial.
*
* @param request the request
* @return the lfitcalccusto response
*/
public LfitcalccustoResponse fetchAllLfitcalccustoFilial(Request request);

public InternalResultsResponse<Lfitcalccusto> insertLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest);
}


