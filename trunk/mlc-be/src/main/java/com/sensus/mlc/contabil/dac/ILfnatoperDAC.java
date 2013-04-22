package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfnatoper.model.Lfnatoper
import com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.response.LfnatoperResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfnatoperDAC
{

/**
* Update lfnatoper.
*
* @param lfnatoperRequest the lfnatoper request
* @return the lfnatoper response
*/
public InternalResultsResponse<Lfnatoper> updateLfnatoper(LfnatoperRequest lfnatoperRequest);

/**
* Delete lfnatoper.
*
* @param lfnatoperRequest the lfnatoper request
* @return the lfnatoper response
*/
public InternalResponse deleteLfnatoper(LfnatoperRequest lfnatoperRequest);

/**
* Fetch all lfnatoper.
*
* @param inquirylfnatoperRequest the inquirylfnatoper request
* @return the inquiry lfnatoper response
*/
public InternalResultsResponse<Lfnatoper> fetchAllLfnatoper(InquiryLfnatoperRequest inquirylfnatoperRequest);

/**
* Fetch lfnatoper by id.
*
* @param inquirylfnatoperRequest the inquirylfnatoper request
* @return the internal results response
*/
public InternalResultsResponse<Lfnatoper> fetchLfnatoperById(LfnatoperRequest lfnatoperRequest);

/**
* Generate file csv.
*
* @param inquiryLfnatoperRequest the inquiry lfnatoper request
* @return the inquiry lfnatoper response
*/
public InternalResponse generateFileCSV(InquiryLfnatoperRequest inquiryLfnatoperRequest);

/**
* Fetch all lfnatoper types.
*
* @param request the request
* @return the lfnatoper response
*/
public LfnatoperResponse fetchAllLfnatoperTypes(Request request);

/**
* Fetch all lfnatoper filial.
*
* @param request the request
* @return the lfnatoper response
*/
public LfnatoperResponse fetchAllLfnatoperFilial(Request request);

public InternalResultsResponse<Lfnatoper> insertLfnatoper(LfnatoperRequest lfnatoperRequest);
}


