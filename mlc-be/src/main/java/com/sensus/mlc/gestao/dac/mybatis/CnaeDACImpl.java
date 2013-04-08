package com.sensus.mlc.cnae.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.cnae.model.Cnae
import com.sensus.mlc.cnae.model.request.CnaeRequest;
import com.sensus.mlc.cnae.model.request.InquiryCnaeRequest;
import com.sensus.mlc.cnae.model.response.CnaeResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface ICnaeDAC;
{

/**
* Update cnae.
*
* @param cnaeRequest the cnae request
* @return the cnae response
*/
public InternalResultsResponse<Cnae> updateCnae(CnaeRequest cnaeRequest);

/**
* Delete cnae.
*
* @param cnaeRequest the cnae request
* @return the cnae response
*/
public InternalResponse deleteCnae(CnaeRequest cnaeRequest);

/**
* Fetch all cnae.
*
* @param inquirycnaeRequest the inquirycnae request
* @return the inquiry cnae response
*/
public InternalResultsResponse<Cnae> fetchAllCnae(InquiryCnaeRequest inquirycnaeRequest);

/**
* Fetch cnae by id.
*
* @param inquirycnaeRequest the inquirycnae request
* @return the internal results response
*/
public InternalResultsResponse<Cnae> fetchCnaeById(CnaeRequest cnaeRequest);

/**
* Generate file csv.
*
* @param inquiryCnaeRequest the inquiry cnae request
* @return the inquiry cnae response
*/
public InternalResponse generateFileCSV(InquiryCnaeRequest inquiryCnaeRequest);

/**
* Fetch all cnae types.
*
* @param request the request
* @return the cnae response
*/
public CnaeResponse fetchAllCnaeTypes(Request request);

/**
* Fetch all cnae filial.
*
* @param request the request
* @return the cnae response
*/
public CnaeResponse fetchAllCnaeFilial(Request request);

public InternalResultsResponse<Cnae> insertCnae(CnaeRequest cnaeRequest);
}


