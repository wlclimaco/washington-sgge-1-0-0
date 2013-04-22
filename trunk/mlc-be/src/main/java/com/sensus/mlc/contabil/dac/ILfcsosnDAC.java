package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfcsosn.model.Lfcsosn
import com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.response.LfcsosnResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfcsosnDAC
{

/**
* Update lfcsosn.
*
* @param lfcsosnRequest the lfcsosn request
* @return the lfcsosn response
*/
public InternalResultsResponse<Lfcsosn> updateLfcsosn(LfcsosnRequest lfcsosnRequest);

/**
* Delete lfcsosn.
*
* @param lfcsosnRequest the lfcsosn request
* @return the lfcsosn response
*/
public InternalResponse deleteLfcsosn(LfcsosnRequest lfcsosnRequest);

/**
* Fetch all lfcsosn.
*
* @param inquirylfcsosnRequest the inquirylfcsosn request
* @return the inquiry lfcsosn response
*/
public InternalResultsResponse<Lfcsosn> fetchAllLfcsosn(InquiryLfcsosnRequest inquirylfcsosnRequest);

/**
* Fetch lfcsosn by id.
*
* @param inquirylfcsosnRequest the inquirylfcsosn request
* @return the internal results response
*/
public InternalResultsResponse<Lfcsosn> fetchLfcsosnById(LfcsosnRequest lfcsosnRequest);

/**
* Generate file csv.
*
* @param inquiryLfcsosnRequest the inquiry lfcsosn request
* @return the inquiry lfcsosn response
*/
public InternalResponse generateFileCSV(InquiryLfcsosnRequest inquiryLfcsosnRequest);

/**
* Fetch all lfcsosn types.
*
* @param request the request
* @return the lfcsosn response
*/
public LfcsosnResponse fetchAllLfcsosnTypes(Request request);

/**
* Fetch all lfcsosn filial.
*
* @param request the request
* @return the lfcsosn response
*/
public LfcsosnResponse fetchAllLfcsosnFilial(Request request);

public InternalResultsResponse<Lfcsosn> insertLfcsosn(LfcsosnRequest lfcsosnRequest);
}


