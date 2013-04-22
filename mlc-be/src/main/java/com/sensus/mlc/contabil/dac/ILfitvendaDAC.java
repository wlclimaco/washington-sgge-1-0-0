package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitvenda.model.Lfitvenda
import com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.response.LfitvendaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfitvendaDAC
{

/**
* Update lfitvenda.
*
* @param lfitvendaRequest the lfitvenda request
* @return the lfitvenda response
*/
public InternalResultsResponse<Lfitvenda> updateLfitvenda(LfitvendaRequest lfitvendaRequest);

/**
* Delete lfitvenda.
*
* @param lfitvendaRequest the lfitvenda request
* @return the lfitvenda response
*/
public InternalResponse deleteLfitvenda(LfitvendaRequest lfitvendaRequest);

/**
* Fetch all lfitvenda.
*
* @param inquirylfitvendaRequest the inquirylfitvenda request
* @return the inquiry lfitvenda response
*/
public InternalResultsResponse<Lfitvenda> fetchAllLfitvenda(InquiryLfitvendaRequest inquirylfitvendaRequest);

/**
* Fetch lfitvenda by id.
*
* @param inquirylfitvendaRequest the inquirylfitvenda request
* @return the internal results response
*/
public InternalResultsResponse<Lfitvenda> fetchLfitvendaById(LfitvendaRequest lfitvendaRequest);

/**
* Generate file csv.
*
* @param inquiryLfitvendaRequest the inquiry lfitvenda request
* @return the inquiry lfitvenda response
*/
public InternalResponse generateFileCSV(InquiryLfitvendaRequest inquiryLfitvendaRequest);

/**
* Fetch all lfitvenda types.
*
* @param request the request
* @return the lfitvenda response
*/
public LfitvendaResponse fetchAllLfitvendaTypes(Request request);

/**
* Fetch all lfitvenda filial.
*
* @param request the request
* @return the lfitvenda response
*/
public LfitvendaResponse fetchAllLfitvendaFilial(Request request);

public InternalResultsResponse<Lfitvenda> insertLfitvenda(LfitvendaRequest lfitvendaRequest);
}


