Memo1
package com.sensus.mlc.Empresa.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.Empresa.model.request.EmpresaRequest;
import com.sensus.mlc.Empresa.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.Empresa.model.response.EmpresaResponse;
import com.sensus.mlc.Empresa.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IEmpresaBCF.
 *
 * @author Washington.Costa
 */
public interface IEmpresaBCF 
{

	/** 
	 * Insert Empresa.
	 *
	 * @param EmpresaRequest the Empresa request
	 * @return the Empresa response 
	 */
	public EmpresaResponse insertEmpresa(EmpresaRequest EmpresaRequest);
  
	/**
	 * Update Empresa.
	 *
	 * @param EmpresaRequest the Empresa request
	 * @return the Empresa response
	 */
	public EmpresaResponse updateEmpresa(EmpresaRequest EmpresaRequest);
  
	/**  
	 * Delete Empresa.  
	 *     
	 * @param EmpresaRequest the Empresa request 
	 * @return the Empresa response
	 */
	public EmpresaResponse deleteEmpresa(EmpresaRequest EmpresaRequest); 
   
	/**  
	 * Fetch all Empresa. 
	 *  
	 * @param inquiryEmpresaRequest the inquiryEmpresa request   
	 * @return the inquiry Empresa response 
	 */ 
	public InquiryEmpresaResponse fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest); 
   
	/** 
	 * Fetch Empresa by id. 
	 *   
	 * @param EmpresaRequest the Empresa request 
	 * @return the Empresa response  
	 */ 
	public EmpresaResponse fetchEmpresaById(EmpresaRequest EmpresaRequest); 
 
} 
