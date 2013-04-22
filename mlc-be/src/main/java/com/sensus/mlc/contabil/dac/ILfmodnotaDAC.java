package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfmodnota.model.Lfmodnota
import com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.response.LfmodnotaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfmodnotaDAC
{

/**
* Update lfmodnota.
*
* @param lfmodnotaRequest the lfmodnota request
* @return the lfmodnota response
*/
public InternalResultsResponse<Lfmodnota> updateLfmodnota(LfmodnotaRequest lfmodnotaRequest);

/**
* Delete lfmodnota.
*
* @param lfmodnotaRequest the lfmodnota request
* @return the lfmodnota response
*/
public InternalResponse deleteLfmodnota(LfmodnotaRequest lfmodnotaRequest);

/**
* Fetch all lfmodnota.
*
* @param inquirylfmodnotaRequest the inquirylfmodnota request
* @return the inquiry lfmodnota response
*/
public InternalResultsResponse<Lfmodnota> fetchAllLfmodnota(InquiryLfmodnotaRequest inquirylfmodnotaRequest);

/**
* Fetch lfmodnota by id.
*
* @param inquirylfmodnotaRequest the inquirylfmodnota request
* @return the internal results response
*/
public InternalResultsResponse<Lfmodnota> fetchLfmodnotaById(LfmodnotaRequest lfmodnotaRequest);

/**
* Generate file csv.
*
* @param inquiryLfmodnotaRequest the inquiry lfmodnota request
* @return the inquiry lfmodnota response
*/
public InternalResponse generateFileCSV(InquiryLfmodnotaRequest inquiryLfmodnotaRequest);

/**
* Fetch all lfmodnota types.
*
* @param request the request
* @return the lfmodnota response
*/
public LfmodnotaResponse fetchAllLfmodnotaTypes(Request request);

/**
* Fetch all lfmodnota filial.
*
* @param request the request
* @return the lfmodnota response
*/
public LfmodnotaResponse fetchAllLfmodnotaFilial(Request request);

public InternalResultsResponse<Lfmodnota> insertLfmodnota(LfmodnotaRequest lfmodnotaRequest);
}


