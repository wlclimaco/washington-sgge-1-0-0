package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfncm.model.Lfncm
import com.sensus.mlc.lfncm.model.request.LfncmRequest;
import com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest;
import com.sensus.mlc.lfncm.model.response.LfncmResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfncmDAC
{

/**
* Update lfncm.
*
* @param lfncmRequest the lfncm request
* @return the lfncm response
*/
public InternalResultsResponse<Lfncm> updateLfncm(LfncmRequest lfncmRequest);

/**
* Delete lfncm.
*
* @param lfncmRequest the lfncm request
* @return the lfncm response
*/
public InternalResponse deleteLfncm(LfncmRequest lfncmRequest);

/**
* Fetch all lfncm.
*
* @param inquirylfncmRequest the inquirylfncm request
* @return the inquiry lfncm response
*/
public InternalResultsResponse<Lfncm> fetchAllLfncm(InquiryLfncmRequest inquirylfncmRequest);

/**
* Fetch lfncm by id.
*
* @param inquirylfncmRequest the inquirylfncm request
* @return the internal results response
*/
public InternalResultsResponse<Lfncm> fetchLfncmById(LfncmRequest lfncmRequest);

/**
* Generate file csv.
*
* @param inquiryLfncmRequest the inquiry lfncm request
* @return the inquiry lfncm response
*/
public InternalResponse generateFileCSV(InquiryLfncmRequest inquiryLfncmRequest);

/**
* Fetch all lfncm types.
*
* @param request the request
* @return the lfncm response
*/
public LfncmResponse fetchAllLfncmTypes(Request request);

/**
* Fetch all lfncm filial.
*
* @param request the request
* @return the lfncm response
*/
public LfncmResponse fetchAllLfncmFilial(Request request);

public InternalResultsResponse<Lfncm> insertLfncm(LfncmRequest lfncmRequest);
}


