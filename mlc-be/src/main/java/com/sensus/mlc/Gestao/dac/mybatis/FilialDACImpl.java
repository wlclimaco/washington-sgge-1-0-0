Memo1
package com.sensus.mlc.Filial.dac


import com.sensus.common.model.request.Request
import com.sensus.common.model.response.InternalResponse
import com.sensus.common.model.response.InternalResultsResponse
import com.sensus.mlc.Filial.model.Filial
import com.sensus.mlc.Filial.model.request.FilialRequest
import com.sensus.mlc.Filial.model.request.InquiryFilialRequest
import com.sensus.mlc.Filial.model.response.FilialResponse

/**
* The Interface IActionDAC.
*
* @author - QAT Brazil.
*
*/
public interface IFilialDAC
{

/**
* Update Filial.
*
* @param FilialRequest the Filial request
* @return the Filial response
*/
public InternalResultsResponse<Filial> updateFilial(FilialRequest FilialRequest)

/**
* Delete Filial.
*
* @param FilialRequest the Filial request
* @return the Filial response
*/
public InternalResponse deleteFilial(FilialRequest FilialRequest)

/**
* Fetch all Filial.
*
* @param inquiryFilialRequest the inquiryFilial request
* @return the inquiry Filial response
*/
public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryFilialRequest)

/**
* Fetch Filial by id.
*
* @param inquiryFilialRequest the inquiryFilial request
* @return the internal results response
*/
public InternalResultsResponse<Filial> fetchFilialById(FilialRequest FilialRequest)

/**
* Generate file csv.
*
* @param inquiryFilialRequest the inquiry Filial request
* @return the inquiry Filial response
*/
public InternalResponse generateFileCSV(InquiryFilialRequest inquiryFilialRequest)

/**
* Fetch all Filial types.
*
* @param request the request
* @return the Filial response
*/
public FilialResponse fetchAllFilialTypes(Request request)

/**
* Fetch all Filial filial.
*
* @param request the request
* @return the Filial response
*/
public FilialResponse fetchAllFilialFilial(Request request)

public InternalResultsResponse<Filial> insertFilial(FilialRequest FilialRequest)
}


