package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfsittrib.model.Lfsittrib
import com.sensus.mlc.lfsittrib.model.request.LfsittribRequest;
import com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest;
import com.sensus.mlc.lfsittrib.model.response.LfsittribResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILfsittribDAC
{

/**
* Update lfsittrib.
*
* @param lfsittribRequest the lfsittrib request
* @return the lfsittrib response
*/
public InternalResultsResponse<Lfsittrib> updateLfsittrib(LfsittribRequest lfsittribRequest);

/**
* Delete lfsittrib.
*
* @param lfsittribRequest the lfsittrib request
* @return the lfsittrib response
*/
public InternalResponse deleteLfsittrib(LfsittribRequest lfsittribRequest);

/**
* Fetch all lfsittrib.
*
* @param inquirylfsittribRequest the inquirylfsittrib request
* @return the inquiry lfsittrib response
*/
public InternalResultsResponse<Lfsittrib> fetchAllLfsittrib(InquiryLfsittribRequest inquirylfsittribRequest);

/**
* Fetch lfsittrib by id.
*
* @param inquirylfsittribRequest the inquirylfsittrib request
* @return the internal results response
*/
public InternalResultsResponse<Lfsittrib> fetchLfsittribById(LfsittribRequest lfsittribRequest);

/**
* Generate file csv.
*
* @param inquiryLfsittribRequest the inquiry lfsittrib request
* @return the inquiry lfsittrib response
*/
public InternalResponse generateFileCSV(InquiryLfsittribRequest inquiryLfsittribRequest);

/**
* Fetch all lfsittrib types.
*
* @param request the request
* @return the lfsittrib response
*/
public LfsittribResponse fetchAllLfsittribTypes(Request request);

/**
* Fetch all lfsittrib filial.
*
* @param request the request
* @return the lfsittrib response
*/
public LfsittribResponse fetchAllLfsittribFilial(Request request);

public InternalResultsResponse<Lfsittrib> insertLfsittrib(LfsittribRequest lfsittribRequest);
}


