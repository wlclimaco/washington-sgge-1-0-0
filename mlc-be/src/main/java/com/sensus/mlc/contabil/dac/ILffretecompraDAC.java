package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lffretecompra.model.Lffretecompra
import com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.response.LffretecompraResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILffretecompraDAC
{

/**
* Update lffretecompra.
*
* @param lffretecompraRequest the lffretecompra request
* @return the lffretecompra response
*/
public InternalResultsResponse<Lffretecompra> updateLffretecompra(LffretecompraRequest lffretecompraRequest);

/**
* Delete lffretecompra.
*
* @param lffretecompraRequest the lffretecompra request
* @return the lffretecompra response
*/
public InternalResponse deleteLffretecompra(LffretecompraRequest lffretecompraRequest);

/**
* Fetch all lffretecompra.
*
* @param inquirylffretecompraRequest the inquirylffretecompra request
* @return the inquiry lffretecompra response
*/
public InternalResultsResponse<Lffretecompra> fetchAllLffretecompra(InquiryLffretecompraRequest inquirylffretecompraRequest);

/**
* Fetch lffretecompra by id.
*
* @param inquirylffretecompraRequest the inquirylffretecompra request
* @return the internal results response
*/
public InternalResultsResponse<Lffretecompra> fetchLffretecompraById(LffretecompraRequest lffretecompraRequest);

/**
* Generate file csv.
*
* @param inquiryLffretecompraRequest the inquiry lffretecompra request
* @return the inquiry lffretecompra response
*/
public InternalResponse generateFileCSV(InquiryLffretecompraRequest inquiryLffretecompraRequest);

/**
* Fetch all lffretecompra types.
*
* @param request the request
* @return the lffretecompra response
*/
public LffretecompraResponse fetchAllLffretecompraTypes(Request request);

/**
* Fetch all lffretecompra filial.
*
* @param request the request
* @return the lffretecompra response
*/
public LffretecompraResponse fetchAllLffretecompraFilial(Request request);

public InternalResultsResponse<Lffretecompra> insertLffretecompra(LffretecompraRequest lffretecompraRequest);
}


