package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfnbm.model.Lfnbm
import com.sensus.mlc.lfnbm.model.request.LfnbmRequest;
import com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest;
import com.sensus.mlc.lfnbm.model.response.LfnbmResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfnbmDAC
{

/**
* Update lfnbm.
*
* @param lfnbmRequest the lfnbm request
* @return the lfnbm response
*/
public InternalResultsResponse<Lfnbm> updateLfnbm(LfnbmRequest lfnbmRequest);

/**
* Delete lfnbm.
*
* @param lfnbmRequest the lfnbm request
* @return the lfnbm response
*/
public InternalResponse deleteLfnbm(LfnbmRequest lfnbmRequest);

/**
* Fetch all lfnbm.
*
* @param inquirylfnbmRequest the inquirylfnbm request
* @return the inquiry lfnbm response
*/
public InternalResultsResponse<Lfnbm> fetchAllLfnbm(InquiryLfnbmRequest inquirylfnbmRequest);

/**
* Fetch lfnbm by id.
*
* @param inquirylfnbmRequest the inquirylfnbm request
* @return the internal results response
*/
public InternalResultsResponse<Lfnbm> fetchLfnbmById(LfnbmRequest lfnbmRequest);

/**
* Generate file csv.
*
* @param inquiryLfnbmRequest the inquiry lfnbm request
* @return the inquiry lfnbm response
*/
public InternalResponse generateFileCSV(InquiryLfnbmRequest inquiryLfnbmRequest);

/**
* Fetch all lfnbm types.
*
* @param request the request
* @return the lfnbm response
*/
public LfnbmResponse fetchAllLfnbmTypes(Request request);

/**
* Fetch all lfnbm filial.
*
* @param request the request
* @return the lfnbm response
*/
public LfnbmResponse fetchAllLfnbmFilial(Request request);

public InternalResultsResponse<Lfnbm> insertLfnbm(LfnbmRequest lfnbmRequest);
}


