package com.sensus.mlc.contabil.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lftrattrib.model.Lftrattrib
import com.sensus.mlc.lftrattrib.model.request.LftrattribRequest;
import com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest;
import com.sensus.mlc.lftrattrib.model.response.LftrattribResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ILftrattribDAC
{

/**
* Update lftrattrib.
*
* @param lftrattribRequest the lftrattrib request
* @return the lftrattrib response
*/
public InternalResultsResponse<Lftrattrib> updateLftrattrib(LftrattribRequest lftrattribRequest);

/**
* Delete lftrattrib.
*
* @param lftrattribRequest the lftrattrib request
* @return the lftrattrib response
*/
public InternalResponse deleteLftrattrib(LftrattribRequest lftrattribRequest);

/**
* Fetch all lftrattrib.
*
* @param inquirylftrattribRequest the inquirylftrattrib request
* @return the inquiry lftrattrib response
*/
public InternalResultsResponse<Lftrattrib> fetchAllLftrattrib(InquiryLftrattribRequest inquirylftrattribRequest);

/**
* Fetch lftrattrib by id.
*
* @param inquirylftrattribRequest the inquirylftrattrib request
* @return the internal results response
*/
public InternalResultsResponse<Lftrattrib> fetchLftrattribById(LftrattribRequest lftrattribRequest);

/**
* Generate file csv.
*
* @param inquiryLftrattribRequest the inquiry lftrattrib request
* @return the inquiry lftrattrib response
*/
public InternalResponse generateFileCSV(InquiryLftrattribRequest inquiryLftrattribRequest);

/**
* Fetch all lftrattrib types.
*
* @param request the request
* @return the lftrattrib response
*/
public LftrattribResponse fetchAllLftrattribTypes(Request request);

/**
* Fetch all lftrattrib filial.
*
* @param request the request
* @return the lftrattrib response
*/
public LftrattribResponse fetchAllLftrattribFilial(Request request);

public InternalResultsResponse<Lftrattrib> insertLftrattrib(LftrattribRequest lftrattribRequest);
}


