package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal
import com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.response.LfregrafiscalResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfregrafiscalDAC
{

/**
* Update lfregrafiscal.
*
* @param lfregrafiscalRequest the lfregrafiscal request
* @return the lfregrafiscal response
*/
public InternalResultsResponse<Lfregrafiscal> updateLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest);

/**
* Delete lfregrafiscal.
*
* @param lfregrafiscalRequest the lfregrafiscal request
* @return the lfregrafiscal response
*/
public InternalResponse deleteLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest);

/**
* Fetch all lfregrafiscal.
*
* @param inquirylfregrafiscalRequest the inquirylfregrafiscal request
* @return the inquiry lfregrafiscal response
*/
public InternalResultsResponse<Lfregrafiscal> fetchAllLfregrafiscal(InquiryLfregrafiscalRequest inquirylfregrafiscalRequest);

/**
* Fetch lfregrafiscal by id.
*
* @param inquirylfregrafiscalRequest the inquirylfregrafiscal request
* @return the internal results response
*/
public InternalResultsResponse<Lfregrafiscal> fetchLfregrafiscalById(LfregrafiscalRequest lfregrafiscalRequest);

/**
* Generate file csv.
*
* @param inquiryLfregrafiscalRequest the inquiry lfregrafiscal request
* @return the inquiry lfregrafiscal response
*/
public InternalResponse generateFileCSV(InquiryLfregrafiscalRequest inquiryLfregrafiscalRequest);

/**
* Fetch all lfregrafiscal types.
*
* @param request the request
* @return the lfregrafiscal response
*/
public LfregrafiscalResponse fetchAllLfregrafiscalTypes(Request request);

/**
* Fetch all lfregrafiscal filial.
*
* @param request the request
* @return the lfregrafiscal response
*/
public LfregrafiscalResponse fetchAllLfregrafiscalFilial(Request request);

public InternalResultsResponse<Lfregrafiscal> insertLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest);
}


