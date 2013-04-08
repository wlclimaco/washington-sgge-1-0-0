package com.sensus.mlc.empresa.dac;


import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.empresa.model.Empresa
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.empresa.model.response.EmpresaResponse;

/**
* The Interface IActionDAC.
*
* @author - Washington
*
*/
public interface IEmpresaDAC;
{

/**
* Update empresa.
*
* @param empresaRequest the empresa request
* @return the empresa response
*/
public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest empresaRequest);

/**
* Delete empresa.
*
* @param empresaRequest the empresa request
* @return the empresa response
*/
public InternalResponse deleteEmpresa(EmpresaRequest empresaRequest);

/**
* Fetch all empresa.
*
* @param inquiryempresaRequest the inquiryempresa request
* @return the inquiry empresa response
*/
public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryempresaRequest);

/**
* Fetch empresa by id.
*
* @param inquiryempresaRequest the inquiryempresa request
* @return the internal results response
*/
public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest empresaRequest);

/**
* Generate file csv.
*
* @param inquiryEmpresaRequest the inquiry empresa request
* @return the inquiry empresa response
*/
public InternalResponse generateFileCSV(InquiryEmpresaRequest inquiryEmpresaRequest);

/**
* Fetch all empresa types.
*
* @param request the request
* @return the empresa response
*/
public EmpresaResponse fetchAllEmpresaTypes(Request request);

/**
* Fetch all empresa filial.
*
* @param request the request
* @return the empresa response
*/
public EmpresaResponse fetchAllEmpresaFilial(Request request);

public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest empresaRequest);
}


