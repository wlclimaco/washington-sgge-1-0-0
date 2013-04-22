package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal
import com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.response.LfitregrafiscalResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfitregrafiscalDAC
{

/**
* Update lfitregrafiscal.
*
* @param lfitregrafiscalRequest the lfitregrafiscal request
* @return the lfitregrafiscal response
*/
public InternalResultsResponse<Lfitregrafiscal> updateLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest);

/**
* Delete lfitregrafiscal.
*
* @param lfitregrafiscalRequest the lfitregrafiscal request
* @return the lfitregrafiscal response
*/
public InternalResponse deleteLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest);

/**
* Fetch all lfitregrafiscal.
*
* @param inquirylfitregrafiscalRequest the inquirylfitregrafiscal request
* @return the inquiry lfitregrafiscal response
*/
public InternalResultsResponse<Lfitregrafiscal> fetchAllLfitregrafiscal(InquiryLfitregrafiscalRequest inquirylfitregrafiscalRequest);

/**
* Fetch lfitregrafiscal by id.
*
* @param inquirylfitregrafiscalRequest the inquirylfitregrafiscal request
* @return the internal results response
*/
public InternalResultsResponse<Lfitregrafiscal> fetchLfitregrafiscalById(LfitregrafiscalRequest lfitregrafiscalRequest);

/**
* Generate file csv.
*
* @param inquiryLfitregrafiscalRequest the inquiry lfitregrafiscal request
* @return the inquiry lfitregrafiscal response
*/
public InternalResponse generateFileCSV(InquiryLfitregrafiscalRequest inquiryLfitregrafiscalRequest);

/**
* Fetch all lfitregrafiscal types.
*
* @param request the request
* @return the lfitregrafiscal response
*/
public LfitregrafiscalResponse fetchAllLfitregrafiscalTypes(Request request);

/**
* Fetch all lfitregrafiscal filial.
*
* @param request the request
* @return the lfitregrafiscal response
*/
public LfitregrafiscalResponse fetchAllLfitregrafiscalFilial(Request request);

public InternalResultsResponse<Lfitregrafiscal> insertLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest);
}


