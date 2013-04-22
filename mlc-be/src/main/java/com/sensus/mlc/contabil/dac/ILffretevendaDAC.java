package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lffretevenda.model.Lffretevenda
import com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.response.LffretevendaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILffretevendaDAC
{

/**
* Update lffretevenda.
*
* @param lffretevendaRequest the lffretevenda request
* @return the lffretevenda response
*/
public InternalResultsResponse<Lffretevenda> updateLffretevenda(LffretevendaRequest lffretevendaRequest);

/**
* Delete lffretevenda.
*
* @param lffretevendaRequest the lffretevenda request
* @return the lffretevenda response
*/
public InternalResponse deleteLffretevenda(LffretevendaRequest lffretevendaRequest);

/**
* Fetch all lffretevenda.
*
* @param inquirylffretevendaRequest the inquirylffretevenda request
* @return the inquiry lffretevenda response
*/
public InternalResultsResponse<Lffretevenda> fetchAllLffretevenda(InquiryLffretevendaRequest inquirylffretevendaRequest);

/**
* Fetch lffretevenda by id.
*
* @param inquirylffretevendaRequest the inquirylffretevenda request
* @return the internal results response
*/
public InternalResultsResponse<Lffretevenda> fetchLffretevendaById(LffretevendaRequest lffretevendaRequest);

/**
* Generate file csv.
*
* @param inquiryLffretevendaRequest the inquiry lffretevenda request
* @return the inquiry lffretevenda response
*/
public InternalResponse generateFileCSV(InquiryLffretevendaRequest inquiryLffretevendaRequest);

/**
* Fetch all lffretevenda types.
*
* @param request the request
* @return the lffretevenda response
*/
public LffretevendaResponse fetchAllLffretevendaTypes(Request request);

/**
* Fetch all lffretevenda filial.
*
* @param request the request
* @return the lffretevenda response
*/
public LffretevendaResponse fetchAllLffretevendaFilial(Request request);

public InternalResultsResponse<Lffretevenda> insertLffretevenda(LffretevendaRequest lffretevendaRequest);
}


