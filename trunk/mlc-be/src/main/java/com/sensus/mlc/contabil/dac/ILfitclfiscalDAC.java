package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal
import com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.response.LfitclfiscalResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfitclfiscalDAC
{

/**
* Update lfitclfiscal.
*
* @param lfitclfiscalRequest the lfitclfiscal request
* @return the lfitclfiscal response
*/
public InternalResultsResponse<Lfitclfiscal> updateLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest);

/**
* Delete lfitclfiscal.
*
* @param lfitclfiscalRequest the lfitclfiscal request
* @return the lfitclfiscal response
*/
public InternalResponse deleteLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest);

/**
* Fetch all lfitclfiscal.
*
* @param inquirylfitclfiscalRequest the inquirylfitclfiscal request
* @return the inquiry lfitclfiscal response
*/
public InternalResultsResponse<Lfitclfiscal> fetchAllLfitclfiscal(InquiryLfitclfiscalRequest inquirylfitclfiscalRequest);

/**
* Fetch lfitclfiscal by id.
*
* @param inquirylfitclfiscalRequest the inquirylfitclfiscal request
* @return the internal results response
*/
public InternalResultsResponse<Lfitclfiscal> fetchLfitclfiscalById(LfitclfiscalRequest lfitclfiscalRequest);

/**
* Generate file csv.
*
* @param inquiryLfitclfiscalRequest the inquiry lfitclfiscal request
* @return the inquiry lfitclfiscal response
*/
public InternalResponse generateFileCSV(InquiryLfitclfiscalRequest inquiryLfitclfiscalRequest);

/**
* Fetch all lfitclfiscal types.
*
* @param request the request
* @return the lfitclfiscal response
*/
public LfitclfiscalResponse fetchAllLfitclfiscalTypes(Request request);

/**
* Fetch all lfitclfiscal filial.
*
* @param request the request
* @return the lfitclfiscal response
*/
public LfitclfiscalResponse fetchAllLfitclfiscalFilial(Request request);

public InternalResultsResponse<Lfitclfiscal> insertLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest);
}


