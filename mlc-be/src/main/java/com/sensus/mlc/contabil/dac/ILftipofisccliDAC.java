package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli
import com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.response.LftipofisccliResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILftipofisccliDAC
{

/**
* Update lftipofisccli.
*
* @param lftipofisccliRequest the lftipofisccli request
* @return the lftipofisccli response
*/
public InternalResultsResponse<Lftipofisccli> updateLftipofisccli(LftipofisccliRequest lftipofisccliRequest);

/**
* Delete lftipofisccli.
*
* @param lftipofisccliRequest the lftipofisccli request
* @return the lftipofisccli response
*/
public InternalResponse deleteLftipofisccli(LftipofisccliRequest lftipofisccliRequest);

/**
* Fetch all lftipofisccli.
*
* @param inquirylftipofisccliRequest the inquirylftipofisccli request
* @return the inquiry lftipofisccli response
*/
public InternalResultsResponse<Lftipofisccli> fetchAllLftipofisccli(InquiryLftipofisccliRequest inquirylftipofisccliRequest);

/**
* Fetch lftipofisccli by id.
*
* @param inquirylftipofisccliRequest the inquirylftipofisccli request
* @return the internal results response
*/
public InternalResultsResponse<Lftipofisccli> fetchLftipofisccliById(LftipofisccliRequest lftipofisccliRequest);

/**
* Generate file csv.
*
* @param inquiryLftipofisccliRequest the inquiry lftipofisccli request
* @return the inquiry lftipofisccli response
*/
public InternalResponse generateFileCSV(InquiryLftipofisccliRequest inquiryLftipofisccliRequest);

/**
* Fetch all lftipofisccli types.
*
* @param request the request
* @return the lftipofisccli response
*/
public LftipofisccliResponse fetchAllLftipofisccliTypes(Request request);

/**
* Fetch all lftipofisccli filial.
*
* @param request the request
* @return the lftipofisccli response
*/
public LftipofisccliResponse fetchAllLftipofisccliFilial(Request request);

public InternalResultsResponse<Lftipofisccli> insertLftipofisccli(LftipofisccliRequest lftipofisccliRequest);
}


