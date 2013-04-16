package com.sensus.mlc.gestao.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Classcliente;
import com.sensus.mlc.gestao.model.request.ClassclienteRequest;
import com.sensus.mlc.gestao.model.request.InquiryClassclienteRequest;
import com.sensus.mlc.gestao.model.response.ClassclienteResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IClassclienteDAC
{

/**
* Update classcliente.
*
* @param classclienteRequest the classcliente request
* @return the classcliente response
*/
public InternalResponse updateClasscliente(ClassclienteRequest classclienteRequest);

/**
* Delete classcliente.
*
* @param classclienteRequest the classcliente request
* @return the classcliente response
*/
public InternalResponse deleteClasscliente(ClassclienteRequest classclienteRequest);

/**
* Fetch all classcliente.
*
* @param inquiryclassclienteRequest the inquiryclasscliente request
* @return the inquiry classcliente response
*/
public InternalResultsResponse<Classcliente> fetchAllClasscliente(InquiryClassclienteRequest inquiryclassclienteRequest);

/**
* Fetch classcliente by id.
*
* @param inquiryclassclienteRequest the inquiryclasscliente request
* @return the internal results response
*/
public InternalResultsResponse<Classcliente> fetchClassclienteById(ClassclienteRequest classclienteRequest);

/**
* Generate file csv.
*
* @param inquiryClassclienteRequest the inquiry classcliente request
* @return the inquiry classcliente response
*/
public InternalResponse generateFileCSV(InquiryClassclienteRequest inquiryClassclienteRequest);

/**
* Fetch all classcliente types.
*
* @param request the request
* @return the classcliente response
*/
public ClassclienteResponse fetchAllClassclienteTypes(Request request);

/**
* Fetch all classcliente filial.
*
* @param request the request
* @return the classcliente response
*/
public ClassclienteResponse fetchAllClassclienteFilial(Request request);

public InternalResultsResponse<Classcliente> insertClasscliente(ClassclienteRequest classclienteRequest);
}


