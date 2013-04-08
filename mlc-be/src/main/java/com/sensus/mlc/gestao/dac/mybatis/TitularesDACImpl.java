package com.sensus.mlc.titulares.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.titulares.model.Titulares
import com.sensus.mlc.titulares.model.request.TitularesRequest;
import com.sensus.mlc.titulares.model.request.InquiryTitularesRequest;
import com.sensus.mlc.titulares.model.response.TitularesResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ITitularesDAC;
{

/**
* Update titulares.
*
* @param titularesRequest the titulares request
* @return the titulares response
*/
public InternalResultsResponse<Titulares> updateTitulares(TitularesRequest titularesRequest);

/**
* Delete titulares.
*
* @param titularesRequest the titulares request
* @return the titulares response
*/
public InternalResponse deleteTitulares(TitularesRequest titularesRequest);

/**
* Fetch all titulares.
*
* @param inquirytitularesRequest the inquirytitulares request
* @return the inquiry titulares response
*/
public InternalResultsResponse<Titulares> fetchAllTitulares(InquiryTitularesRequest inquirytitularesRequest);

/**
* Fetch titulares by id.
*
* @param inquirytitularesRequest the inquirytitulares request
* @return the internal results response
*/
public InternalResultsResponse<Titulares> fetchTitularesById(TitularesRequest titularesRequest);

/**
* Generate file csv.
*
* @param inquiryTitularesRequest the inquiry titulares request
* @return the inquiry titulares response
*/
public InternalResponse generateFileCSV(InquiryTitularesRequest inquiryTitularesRequest);

/**
* Fetch all titulares types.
*
* @param request the request
* @return the titulares response
*/
public TitularesResponse fetchAllTitularesTypes(Request request);

/**
* Fetch all titulares filial.
*
* @param request the request
* @return the titulares response
*/
public TitularesResponse fetchAllTitularesFilial(Request request);

public InternalResultsResponse<Titulares> insertTitulares(TitularesRequest titularesRequest);
}


