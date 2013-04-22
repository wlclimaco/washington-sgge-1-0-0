package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm
import com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.response.LfncmnbmResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfncmnbmDAC
{

/**
* Update lfncmnbm.
*
* @param lfncmnbmRequest the lfncmnbm request
* @return the lfncmnbm response
*/
public InternalResultsResponse<Lfncmnbm> updateLfncmnbm(LfncmnbmRequest lfncmnbmRequest);

/**
* Delete lfncmnbm.
*
* @param lfncmnbmRequest the lfncmnbm request
* @return the lfncmnbm response
*/
public InternalResponse deleteLfncmnbm(LfncmnbmRequest lfncmnbmRequest);

/**
* Fetch all lfncmnbm.
*
* @param inquirylfncmnbmRequest the inquirylfncmnbm request
* @return the inquiry lfncmnbm response
*/
public InternalResultsResponse<Lfncmnbm> fetchAllLfncmnbm(InquiryLfncmnbmRequest inquirylfncmnbmRequest);

/**
* Fetch lfncmnbm by id.
*
* @param inquirylfncmnbmRequest the inquirylfncmnbm request
* @return the internal results response
*/
public InternalResultsResponse<Lfncmnbm> fetchLfncmnbmById(LfncmnbmRequest lfncmnbmRequest);

/**
* Generate file csv.
*
* @param inquiryLfncmnbmRequest the inquiry lfncmnbm request
* @return the inquiry lfncmnbm response
*/
public InternalResponse generateFileCSV(InquiryLfncmnbmRequest inquiryLfncmnbmRequest);

/**
* Fetch all lfncmnbm types.
*
* @param request the request
* @return the lfncmnbm response
*/
public LfncmnbmResponse fetchAllLfncmnbmTypes(Request request);

/**
* Fetch all lfncmnbm filial.
*
* @param request the request
* @return the lfncmnbm response
*/
public LfncmnbmResponse fetchAllLfncmnbmFilial(Request request);

public InternalResultsResponse<Lfncmnbm> insertLfncmnbm(LfncmnbmRequest lfncmnbmRequest);
}


