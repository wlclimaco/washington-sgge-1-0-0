package com.sensus.mlc.tabela.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.dominios.model.Dominios
import com.sensus.mlc.dominios.model.request.DominiosRequest;
import com.sensus.mlc.dominios.model.request.InquiryDominiosRequest;
import com.sensus.mlc.dominios.model.response.DominiosResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IDominiosDAC
{

/**
* Update dominios.
*
* @param dominiosRequest the dominios request
* @return the dominios response
*/
public InternalResultsResponse<Dominios> updateDominios(DominiosRequest dominiosRequest);

/**
* Delete dominios.
*
* @param dominiosRequest the dominios request
* @return the dominios response
*/
public InternalResponse deleteDominios(DominiosRequest dominiosRequest);

/**
* Fetch all dominios.
*
* @param inquirydominiosRequest the inquirydominios request
* @return the inquiry dominios response
*/
public InternalResultsResponse<Dominios> fetchAllDominios(InquiryDominiosRequest inquirydominiosRequest);

/**
* Fetch dominios by id.
*
* @param inquirydominiosRequest the inquirydominios request
* @return the internal results response
*/
public InternalResultsResponse<Dominios> fetchDominiosById(DominiosRequest dominiosRequest);

/**
* Generate file csv.
*
* @param inquiryDominiosRequest the inquiry dominios request
* @return the inquiry dominios response
*/
public InternalResponse generateFileCSV(InquiryDominiosRequest inquiryDominiosRequest);

/**
* Fetch all dominios types.
*
* @param request the request
* @return the dominios response
*/
public DominiosResponse fetchAllDominiosTypes(Request request);

/**
* Fetch all dominios filial.
*
* @param request the request
* @return the dominios response
*/
public DominiosResponse fetchAllDominiosFilial(Request request);

public InternalResultsResponse<Dominios> insertDominios(DominiosRequest dominiosRequest);
}


