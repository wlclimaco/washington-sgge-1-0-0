package com.sensus.mlc.tabela.dac


import com.sensus.common.model.request.Request
import com.sensus.common.model.response.InternalResponse
import com.sensus.common.model.response.InternalResultsResponse
import com.sensus.mlc.atributos.model.Atributos
import com.sensus.mlc.atributos.model.request.AtributosRequest
import com.sensus.mlc.atributos.model.request.InquiryAtributosRequest
import com.sensus.mlc.atributos.model.response.AtributosResponse

/**
* The Interface IActionDAC.
*
* @author - QAT Brazil.
*
*/
public interface IAtributosDAC
{

/**
* Update atributos.
*
* @param atributosRequest the atributos request
* @return the atributos response
*/
public InternalResultsResponse<Atributos> updateAtributos(AtributosRequest atributosRequest)

/**
* Delete atributos.
*
* @param atributosRequest the atributos request
* @return the atributos response
*/
public InternalResponse deleteAtributos(AtributosRequest atributosRequest)

/**
* Fetch all atributos.
*
* @param inquiryatributosRequest the inquiryatributos request
* @return the inquiry atributos response
*/
public InternalResultsResponse<Atributos> fetchAllAtributos(InquiryAtributosRequest inquiryatributosRequest)

/**
* Fetch atributos by id.
*
* @param inquiryatributosRequest the inquiryatributos request
* @return the internal results response
*/
public InternalResultsResponse<Atributos> fetchAtributosById(AtributosRequest atributosRequest)

/**
* Generate file csv.
*
* @param inquiryAtributosRequest the inquiry atributos request
* @return the inquiry atributos response
*/
public InternalResponse generateFileCSV(InquiryAtributosRequest inquiryAtributosRequest)

/**
* Fetch all atributos types.
*
* @param request the request
* @return the atributos response
*/
public AtributosResponse fetchAllAtributosTypes(Request request)

/**
* Fetch all atributos filial.
*
* @param request the request
* @return the atributos response
*/
public AtributosResponse fetchAllAtributosFilial(Request request)

public InternalResultsResponse<Atributos> insertAtributos(AtributosRequest atributosRequest)
}


