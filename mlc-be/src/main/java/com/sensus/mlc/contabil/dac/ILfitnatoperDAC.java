package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper
import com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.response.LfitnatoperResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfitnatoperDAC
{

/**
* Update lfitnatoper.
*
* @param lfitnatoperRequest the lfitnatoper request
* @return the lfitnatoper response
*/
public InternalResultsResponse<Lfitnatoper> updateLfitnatoper(LfitnatoperRequest lfitnatoperRequest);

/**
* Delete lfitnatoper.
*
* @param lfitnatoperRequest the lfitnatoper request
* @return the lfitnatoper response
*/
public InternalResponse deleteLfitnatoper(LfitnatoperRequest lfitnatoperRequest);

/**
* Fetch all lfitnatoper.
*
* @param inquirylfitnatoperRequest the inquirylfitnatoper request
* @return the inquiry lfitnatoper response
*/
public InternalResultsResponse<Lfitnatoper> fetchAllLfitnatoper(InquiryLfitnatoperRequest inquirylfitnatoperRequest);

/**
* Fetch lfitnatoper by id.
*
* @param inquirylfitnatoperRequest the inquirylfitnatoper request
* @return the internal results response
*/
public InternalResultsResponse<Lfitnatoper> fetchLfitnatoperById(LfitnatoperRequest lfitnatoperRequest);

/**
* Generate file csv.
*
* @param inquiryLfitnatoperRequest the inquiry lfitnatoper request
* @return the inquiry lfitnatoper response
*/
public InternalResponse generateFileCSV(InquiryLfitnatoperRequest inquiryLfitnatoperRequest);

/**
* Fetch all lfitnatoper types.
*
* @param request the request
* @return the lfitnatoper response
*/
public LfitnatoperResponse fetchAllLfitnatoperTypes(Request request);

/**
* Fetch all lfitnatoper filial.
*
* @param request the request
* @return the lfitnatoper response
*/
public LfitnatoperResponse fetchAllLfitnatoperFilial(Request request);

public InternalResultsResponse<Lfitnatoper> insertLfitnatoper(LfitnatoperRequest lfitnatoperRequest);
}


