Memo1
package com.sensus.mlc.Filial.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.Filial.model.Filial; 
import com.sensus.mlc.Filial.model.request.FilialRequest; 
import com.sensus.mlc.Filial.model.request.InquiryFilialRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IFilialBCL 
{   
    
	/**  
	 * Insert Filial. 
	 * 
	 * @param FilialRequest the Filial request  
	 * @return the Filial response  
	 */ 
	public InternalResultsResponse<Filial> insertFilial(FilialRequest FilialRequest);
  
	/** 
	 * Update Filial. 
	 *  
	 * @param FilialRequest the Filial request 
	 * @return the Filial response 
	 */  
	public InternalResultsResponse<Filial> updateFilial(FilialRequest FilialRequest); 
   
	/** 
	 * Delete Filial. 
	 *   
	 * @param FilialRequest the Filial request 
	 * @return the Filial response  
	 */ 
	public InternalResponse deleteFilial(FilialRequest FilialRequest); 
  
	/** 
	 * Fetch all Filial.
	 *   
	 * @param inquiryFilialRequest the inquiryFilial request 
	 * @return the inquiry Filial response 
	 */  
	public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryFilialRequest);
  
	/** 
	 * Fetch Filial by id. 
	 * 
	 * @param inquiryFilialRequest the inquiryFilial request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest FilialRequest); 
 
	/** 
	 * Fetch all Filial types. 
	 * 
	 * @param request the request 
	 * @return the Filial response 
	 */ 
	public InternalResultsResponse<Filial> fetchAllFilialTypes(InquiryFilialRequest inquiryFilialRequest);  
  
	/** 
	 * Fetch all Filial filial. 
	 *  
	 * @param request the request 
	 * @return the Filial response 
	 */ 
	public InternalResultsResponse<Filial> fetchAllFilialFilial(FilialRequest FilialRequest);
 
} 
