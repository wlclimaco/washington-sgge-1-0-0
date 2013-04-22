package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitcompra.model.Lfitcompra
import com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.response.LfitcompraResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfitcompraDAC
{

/**
* Update lfitcompra.
*
* @param lfitcompraRequest the lfitcompra request
* @return the lfitcompra response
*/
public InternalResultsResponse<Lfitcompra> updateLfitcompra(LfitcompraRequest lfitcompraRequest);

/**
* Delete lfitcompra.
*
* @param lfitcompraRequest the lfitcompra request
* @return the lfitcompra response
*/
public InternalResponse deleteLfitcompra(LfitcompraRequest lfitcompraRequest);

/**
* Fetch all lfitcompra.
*
* @param inquirylfitcompraRequest the inquirylfitcompra request
* @return the inquiry lfitcompra response
*/
public InternalResultsResponse<Lfitcompra> fetchAllLfitcompra(InquiryLfitcompraRequest inquirylfitcompraRequest);

/**
* Fetch lfitcompra by id.
*
* @param inquirylfitcompraRequest the inquirylfitcompra request
* @return the internal results response
*/
public InternalResultsResponse<Lfitcompra> fetchLfitcompraById(LfitcompraRequest lfitcompraRequest);

/**
* Generate file csv.
*
* @param inquiryLfitcompraRequest the inquiry lfitcompra request
* @return the inquiry lfitcompra response
*/
public InternalResponse generateFileCSV(InquiryLfitcompraRequest inquiryLfitcompraRequest);

/**
* Fetch all lfitcompra types.
*
* @param request the request
* @return the lfitcompra response
*/
public LfitcompraResponse fetchAllLfitcompraTypes(Request request);

/**
* Fetch all lfitcompra filial.
*
* @param request the request
* @return the lfitcompra response
*/
public LfitcompraResponse fetchAllLfitcompraFilial(Request request);

public InternalResultsResponse<Lfitcompra> insertLfitcompra(LfitcompraRequest lfitcompraRequest);
}


