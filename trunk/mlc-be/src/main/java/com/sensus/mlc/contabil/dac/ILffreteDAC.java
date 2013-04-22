package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lffrete.model.Lffrete
import com.sensus.mlc.lffrete.model.request.LffreteRequest;
import com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest;
import com.sensus.mlc.lffrete.model.response.LffreteResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILffreteDAC
{

/**
* Update lffrete.
*
* @param lffreteRequest the lffrete request
* @return the lffrete response
*/
public InternalResultsResponse<Lffrete> updateLffrete(LffreteRequest lffreteRequest);

/**
* Delete lffrete.
*
* @param lffreteRequest the lffrete request
* @return the lffrete response
*/
public InternalResponse deleteLffrete(LffreteRequest lffreteRequest);

/**
* Fetch all lffrete.
*
* @param inquirylffreteRequest the inquirylffrete request
* @return the inquiry lffrete response
*/
public InternalResultsResponse<Lffrete> fetchAllLffrete(InquiryLffreteRequest inquirylffreteRequest);

/**
* Fetch lffrete by id.
*
* @param inquirylffreteRequest the inquirylffrete request
* @return the internal results response
*/
public InternalResultsResponse<Lffrete> fetchLffreteById(LffreteRequest lffreteRequest);

/**
* Generate file csv.
*
* @param inquiryLffreteRequest the inquiry lffrete request
* @return the inquiry lffrete response
*/
public InternalResponse generateFileCSV(InquiryLffreteRequest inquiryLffreteRequest);

/**
* Fetch all lffrete types.
*
* @param request the request
* @return the lffrete response
*/
public LffreteResponse fetchAllLffreteTypes(Request request);

/**
* Fetch all lffrete filial.
*
* @param request the request
* @return the lffrete response
*/
public LffreteResponse fetchAllLffreteFilial(Request request);

public InternalResultsResponse<Lffrete> insertLffrete(LffreteRequest lffreteRequest);
}


