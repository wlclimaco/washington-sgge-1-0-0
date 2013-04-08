package com.sensus.mlc.uf.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.uf.model.Uf
import com.sensus.mlc.uf.model.request.UfRequest;
import com.sensus.mlc.uf.model.request.InquiryUfRequest;
import com.sensus.mlc.uf.model.response.UfResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IUfDAC;
{

/**
* Update uf.
*
* @param ufRequest the uf request
* @return the uf response
*/
public InternalResultsResponse<Uf> updateUf(UfRequest ufRequest);

/**
* Delete uf.
*
* @param ufRequest the uf request
* @return the uf response
*/
public InternalResponse deleteUf(UfRequest ufRequest);

/**
* Fetch all uf.
*
* @param inquiryufRequest the inquiryuf request
* @return the inquiry uf response
*/
public InternalResultsResponse<Uf> fetchAllUf(InquiryUfRequest inquiryufRequest);

/**
* Fetch uf by id.
*
* @param inquiryufRequest the inquiryuf request
* @return the internal results response
*/
public InternalResultsResponse<Uf> fetchUfById(UfRequest ufRequest);

/**
* Generate file csv.
*
* @param inquiryUfRequest the inquiry uf request
* @return the inquiry uf response
*/
public InternalResponse generateFileCSV(InquiryUfRequest inquiryUfRequest);

/**
* Fetch all uf types.
*
* @param request the request
* @return the uf response
*/
public UfResponse fetchAllUfTypes(Request request);

/**
* Fetch all uf filial.
*
* @param request the request
* @return the uf response
*/
public UfResponse fetchAllUfFilial(Request request);

public InternalResultsResponse<Uf> insertUf(UfRequest ufRequest);
}


