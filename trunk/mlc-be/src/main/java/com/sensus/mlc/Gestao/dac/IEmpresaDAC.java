Memo1
package com.sensus.mlc.Empresa.dac


import com.sensus.common.model.request.Request
import com.sensus.common.model.response.InternalResponse
import com.sensus.common.model.response.InternalResultsResponse
import com.sensus.mlc.Empresa.model.Empresa
import com.sensus.mlc.Empresa.model.request.EmpresaRequest
import com.sensus.mlc.Empresa.model.request.InquiryEmpresaRequest
import com.sensus.mlc.Empresa.model.response.EmpresaResponse

/**
* The Interface IActionDAC.
*
* @author - QAT Brazil.
*
*/
public interface IEmpresaDAC
{

/**
* Update Empresa.
*
* @param EmpresaRequest the Empresa request
* @return the Empresa response
*/
public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest EmpresaRequest)

/**
* Delete Empresa.
*
* @param EmpresaRequest the Empresa request
* @return the Empresa response
*/
public InternalResponse deleteEmpresa(EmpresaRequest EmpresaRequest)

/**
* Fetch all Empresa.
*
* @param inquiryEmpresaRequest the inquiryEmpresa request
* @return the inquiry Empresa response
*/
public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest)

/**
* Fetch Empresa by id.
*
* @param inquiryEmpresaRequest the inquiryEmpresa request
* @return the internal results response
*/
public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest EmpresaRequest)

/**
* Generate file csv.
*
* @param inquiryEmpresaRequest the inquiry Empresa request
* @return the inquiry Empresa response
*/
public InternalResponse generateFileCSV(InquiryEmpresaRequest inquiryEmpresaRequest)

/**
* Fetch all Empresa types.
*
* @param request the request
* @return the Empresa response
*/
public EmpresaResponse fetchAllEmpresaTypes(Request request)

/**
* Fetch all Empresa filial.
*
* @param request the request
* @return the Empresa response
*/
public EmpresaResponse fetchAllEmpresaFilial(Request request)

public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest EmpresaRequest)
}


