package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Bairro;
import com.sensus.mlc.gestao.model.request.BairroRequest;
import com.sensus.mlc.gestao.model.request.InquiryBairroRequest;
import com.sensus.mlc.gestao.model.response.BairroResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IBairroDAC
{

/**
* Update bairro.
*
* @param bairroRequest the bairro request
* @return the bairro response
*/
public InternalResultsResponse<Bairro> updateBairro(BairroRequest bairroRequest);

/**
* Delete bairro.
*
* @param bairroRequest the bairro request
* @return the bairro response
*/
public InternalResponse deleteBairro(BairroRequest bairroRequest);

/**
* Fetch all bairro.
*
* @param inquirybairroRequest the inquirybairro request
* @return the inquiry bairro response
*/
public InternalResultsResponse<Bairro> fetchAllBairro(InquiryBairroRequest inquirybairroRequest);

/**
* Fetch bairro by id.
*
* @param inquirybairroRequest the inquirybairro request
* @return the internal results response
*/
public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest bairroRequest);

/**
* Generate file csv.
*
* @param inquiryBairroRequest the inquiry bairro request
* @return the inquiry bairro response
*/
public InternalResponse generateFileCSV(InquiryBairroRequest inquiryBairroRequest);

/**
* Fetch all bairro types.
*
* @param request the request
* @return the bairro response
*/
public BairroResponse fetchAllBairroTypes(Request request);

/**
* Fetch all bairro filial.
*
* @param request the request
* @return the bairro response
*/
public BairroResponse fetchAllBairroFilial(Request request);

public InternalResultsResponse<Bairro> insertBairro(BairroRequest bairroRequest);
}


