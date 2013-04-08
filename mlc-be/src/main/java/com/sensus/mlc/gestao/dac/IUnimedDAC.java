package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Unimed;
import com.sensus.mlc.gestao.model.request.InquiryUnimedRequest;
import com.sensus.mlc.gestao.model.request.UnimedRequest;
import com.sensus.mlc.gestao.model.response.UnimedResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IUnimedDAC
{

/**
* Update unimed.
*
* @param unimedRequest the unimed request
* @return the unimed response
*/
public InternalResultsResponse<Unimed> updateUnimed(UnimedRequest unimedRequest);

/**
* Delete unimed.
*
* @param unimedRequest the unimed request
* @return the unimed response
*/
public InternalResponse deleteUnimed(UnimedRequest unimedRequest);

/**
* Fetch all unimed.
*
* @param inquiryunimedRequest the inquiryunimed request
* @return the inquiry unimed response
*/
public InternalResultsResponse<Unimed> fetchAllUnimed(InquiryUnimedRequest inquiryunimedRequest);

/**
* Fetch unimed by id.
*
* @param inquiryunimedRequest the inquiryunimed request
* @return the internal results response
*/
public InternalResultsResponse<Unimed> fetchUnimedById(UnimedRequest unimedRequest);

/**
* Generate file csv.
*
* @param inquiryUnimedRequest the inquiry unimed request
* @return the inquiry unimed response
*/
public InternalResponse generateFileCSV(InquiryUnimedRequest inquiryUnimedRequest);

/**
* Fetch all unimed types.
*
* @param request the request
* @return the unimed response
*/
public UnimedResponse fetchAllUnimedTypes(Request request);

/**
* Fetch all unimed filial.
*
* @param request the request
* @return the unimed response
*/
public UnimedResponse fetchAllUnimedFilial(Request request);

public InternalResultsResponse<Unimed> insertUnimed(UnimedRequest unimedRequest);
}


