package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfclfiscal.model.Lfclfiscal
import com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.response.LfclfiscalResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfclfiscalDAC
{

/**
* Update lfclfiscal.
*
* @param lfclfiscalRequest the lfclfiscal request
* @return the lfclfiscal response
*/
public InternalResultsResponse<Lfclfiscal> updateLfclfiscal(LfclfiscalRequest lfclfiscalRequest);

/**
* Delete lfclfiscal.
*
* @param lfclfiscalRequest the lfclfiscal request
* @return the lfclfiscal response
*/
public InternalResponse deleteLfclfiscal(LfclfiscalRequest lfclfiscalRequest);

/**
* Fetch all lfclfiscal.
*
* @param inquirylfclfiscalRequest the inquirylfclfiscal request
* @return the inquiry lfclfiscal response
*/
public InternalResultsResponse<Lfclfiscal> fetchAllLfclfiscal(InquiryLfclfiscalRequest inquirylfclfiscalRequest);

/**
* Fetch lfclfiscal by id.
*
* @param inquirylfclfiscalRequest the inquirylfclfiscal request
* @return the internal results response
*/
public InternalResultsResponse<Lfclfiscal> fetchLfclfiscalById(LfclfiscalRequest lfclfiscalRequest);

/**
* Generate file csv.
*
* @param inquiryLfclfiscalRequest the inquiry lfclfiscal request
* @return the inquiry lfclfiscal response
*/
public InternalResponse generateFileCSV(InquiryLfclfiscalRequest inquiryLfclfiscalRequest);

/**
* Fetch all lfclfiscal types.
*
* @param request the request
* @return the lfclfiscal response
*/
public LfclfiscalResponse fetchAllLfclfiscalTypes(Request request);

/**
* Fetch all lfclfiscal filial.
*
* @param request the request
* @return the lfclfiscal response
*/
public LfclfiscalResponse fetchAllLfclfiscalFilial(Request request);

public InternalResultsResponse<Lfclfiscal> insertLfclfiscal(LfclfiscalRequest lfclfiscalRequest);
}


