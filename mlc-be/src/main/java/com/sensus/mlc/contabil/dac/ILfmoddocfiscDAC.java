package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc
import com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.response.LfmoddocfiscResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfmoddocfiscDAC
{

/**
* Update lfmoddocfisc.
*
* @param lfmoddocfiscRequest the lfmoddocfisc request
* @return the lfmoddocfisc response
*/
public InternalResultsResponse<Lfmoddocfisc> updateLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest);

/**
* Delete lfmoddocfisc.
*
* @param lfmoddocfiscRequest the lfmoddocfisc request
* @return the lfmoddocfisc response
*/
public InternalResponse deleteLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest);

/**
* Fetch all lfmoddocfisc.
*
* @param inquirylfmoddocfiscRequest the inquirylfmoddocfisc request
* @return the inquiry lfmoddocfisc response
*/
public InternalResultsResponse<Lfmoddocfisc> fetchAllLfmoddocfisc(InquiryLfmoddocfiscRequest inquirylfmoddocfiscRequest);

/**
* Fetch lfmoddocfisc by id.
*
* @param inquirylfmoddocfiscRequest the inquirylfmoddocfisc request
* @return the internal results response
*/
public InternalResultsResponse<Lfmoddocfisc> fetchLfmoddocfiscById(LfmoddocfiscRequest lfmoddocfiscRequest);

/**
* Generate file csv.
*
* @param inquiryLfmoddocfiscRequest the inquiry lfmoddocfisc request
* @return the inquiry lfmoddocfisc response
*/
public InternalResponse generateFileCSV(InquiryLfmoddocfiscRequest inquiryLfmoddocfiscRequest);

/**
* Fetch all lfmoddocfisc types.
*
* @param request the request
* @return the lfmoddocfisc response
*/
public LfmoddocfiscResponse fetchAllLfmoddocfiscTypes(Request request);

/**
* Fetch all lfmoddocfisc filial.
*
* @param request the request
* @return the lfmoddocfisc response
*/
public LfmoddocfiscResponse fetchAllLfmoddocfiscFilial(Request request);

public InternalResultsResponse<Lfmoddocfisc> insertLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest);
}


