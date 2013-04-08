package com.sensus.mlc.chaveestrangeira.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira
import com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.response.ChaveestrangeiraResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IChaveestrangeiraDAC;
{

/**
* Update chaveestrangeira.
*
* @param chaveestrangeiraRequest the chaveestrangeira request
* @return the chaveestrangeira response
*/
public InternalResultsResponse<Chaveestrangeira> updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest);

/**
* Delete chaveestrangeira.
*
* @param chaveestrangeiraRequest the chaveestrangeira request
* @return the chaveestrangeira response
*/
public InternalResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest);

/**
* Fetch all chaveestrangeira.
*
* @param inquirychaveestrangeiraRequest the inquirychaveestrangeira request
* @return the inquiry chaveestrangeira response
*/
public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquirychaveestrangeiraRequest);

/**
* Fetch chaveestrangeira by id.
*
* @param inquirychaveestrangeiraRequest the inquirychaveestrangeira request
* @return the internal results response
*/
public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest);

/**
* Generate file csv.
*
* @param inquiryChaveestrangeiraRequest the inquiry chaveestrangeira request
* @return the inquiry chaveestrangeira response
*/
public InternalResponse generateFileCSV(InquiryChaveestrangeiraRequest inquiryChaveestrangeiraRequest);

/**
* Fetch all chaveestrangeira types.
*
* @param request the request
* @return the chaveestrangeira response
*/
public ChaveestrangeiraResponse fetchAllChaveestrangeiraTypes(Request request);

/**
* Fetch all chaveestrangeira filial.
*
* @param request the request
* @return the chaveestrangeira response
*/
public ChaveestrangeiraResponse fetchAllChaveestrangeiraFilial(Request request);

public InternalResultsResponse<Chaveestrangeira> insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest);
}


