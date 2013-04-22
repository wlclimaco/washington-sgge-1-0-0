package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal
import com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.response.LflivrofiscalResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILflivrofiscalDAC
{

/**
* Update lflivrofiscal.
*
* @param lflivrofiscalRequest the lflivrofiscal request
* @return the lflivrofiscal response
*/
public InternalResultsResponse<Lflivrofiscal> updateLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest);

/**
* Delete lflivrofiscal.
*
* @param lflivrofiscalRequest the lflivrofiscal request
* @return the lflivrofiscal response
*/
public InternalResponse deleteLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest);

/**
* Fetch all lflivrofiscal.
*
* @param inquirylflivrofiscalRequest the inquirylflivrofiscal request
* @return the inquiry lflivrofiscal response
*/
public InternalResultsResponse<Lflivrofiscal> fetchAllLflivrofiscal(InquiryLflivrofiscalRequest inquirylflivrofiscalRequest);

/**
* Fetch lflivrofiscal by id.
*
* @param inquirylflivrofiscalRequest the inquirylflivrofiscal request
* @return the internal results response
*/
public InternalResultsResponse<Lflivrofiscal> fetchLflivrofiscalById(LflivrofiscalRequest lflivrofiscalRequest);

/**
* Generate file csv.
*
* @param inquiryLflivrofiscalRequest the inquiry lflivrofiscal request
* @return the inquiry lflivrofiscal response
*/
public InternalResponse generateFileCSV(InquiryLflivrofiscalRequest inquiryLflivrofiscalRequest);

/**
* Fetch all lflivrofiscal types.
*
* @param request the request
* @return the lflivrofiscal response
*/
public LflivrofiscalResponse fetchAllLflivrofiscalTypes(Request request);

/**
* Fetch all lflivrofiscal filial.
*
* @param request the request
* @return the lflivrofiscal response
*/
public LflivrofiscalResponse fetchAllLflivrofiscalFilial(Request request);

public InternalResultsResponse<Lflivrofiscal> insertLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest);
}


