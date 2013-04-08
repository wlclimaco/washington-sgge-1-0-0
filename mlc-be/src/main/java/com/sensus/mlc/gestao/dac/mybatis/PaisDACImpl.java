package com.sensus.mlc.pais.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.pais.model.Pais
import com.sensus.mlc.pais.model.request.PaisRequest;
import com.sensus.mlc.pais.model.request.InquiryPaisRequest;
import com.sensus.mlc.pais.model.response.PaisResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IPaisDAC;
{

/**
* Update pais.
*
* @param paisRequest the pais request
* @return the pais response
*/
public InternalResultsResponse<Pais> updatePais(PaisRequest paisRequest);

/**
* Delete pais.
*
* @param paisRequest the pais request
* @return the pais response
*/
public InternalResponse deletePais(PaisRequest paisRequest);

/**
* Fetch all pais.
*
* @param inquirypaisRequest the inquirypais request
* @return the inquiry pais response
*/
public InternalResultsResponse<Pais> fetchAllPais(InquiryPaisRequest inquirypaisRequest);

/**
* Fetch pais by id.
*
* @param inquirypaisRequest the inquirypais request
* @return the internal results response
*/
public InternalResultsResponse<Pais> fetchPaisById(PaisRequest paisRequest);

/**
* Generate file csv.
*
* @param inquiryPaisRequest the inquiry pais request
* @return the inquiry pais response
*/
public InternalResponse generateFileCSV(InquiryPaisRequest inquiryPaisRequest);

/**
* Fetch all pais types.
*
* @param request the request
* @return the pais response
*/
public PaisResponse fetchAllPaisTypes(Request request);

/**
* Fetch all pais filial.
*
* @param request the request
* @return the pais response
*/
public PaisResponse fetchAllPaisFilial(Request request);

public InternalResultsResponse<Pais> insertPais(PaisRequest paisRequest);
}


