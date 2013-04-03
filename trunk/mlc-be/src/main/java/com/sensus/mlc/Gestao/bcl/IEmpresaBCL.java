Memo1
package com.sensus.mlc.Empresa.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.Empresa.model.Empresa; 
import com.sensus.mlc.Empresa.model.request.EmpresaRequest; 
import com.sensus.mlc.Empresa.model.request.InquiryEmpresaRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IEmpresaBCL 
{   
    
	/**  
	 * Insert Empresa. 
	 * 
	 * @param EmpresaRequest the Empresa request  
	 * @return the Empresa response  
	 */ 
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest EmpresaRequest);
  
	/** 
	 * Update Empresa. 
	 *  
	 * @param EmpresaRequest the Empresa request 
	 * @return the Empresa response 
	 */  
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest EmpresaRequest); 
   
	/** 
	 * Delete Empresa. 
	 *   
	 * @param EmpresaRequest the Empresa request 
	 * @return the Empresa response  
	 */ 
	public InternalResponse deleteEmpresa(EmpresaRequest EmpresaRequest); 
  
	/** 
	 * Fetch all Empresa.
	 *   
	 * @param inquiryEmpresaRequest the inquiryEmpresa request 
	 * @return the inquiry Empresa response 
	 */  
	public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest);
  
	/** 
	 * Fetch Empresa by id. 
	 * 
	 * @param inquiryEmpresaRequest the inquiryEmpresa request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest EmpresaRequest); 
 
	/** 
	 * Fetch all Empresa types. 
	 * 
	 * @param request the request 
	 * @return the Empresa response 
	 */ 
	public InternalResultsResponse<Empresa> fetchAllEmpresaTypes(InquiryEmpresaRequest inquiryEmpresaRequest);  
  
	/** 
	 * Fetch all Empresa filial. 
	 *  
	 * @param request the request 
	 * @return the Empresa response 
	 */ 
	public InternalResultsResponse<Empresa> fetchAllEmpresaFilial(EmpresaRequest EmpresaRequest);
 
} 
