package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfclfiscal.model.Lfclfiscal; 
import com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest; 
import com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfclfiscalBCL 
{   
    
	/**  
	 * Insert lfclfiscal. 
	 * 
	 * @param lfclfiscalRequest the lfclfiscal request  
	 * @return the lfclfiscal response  
	 */ 
	public InternalResultsResponse<Lfclfiscal> insertLfclfiscal(LfclfiscalRequest lfclfiscalRequest);
  
	/** 
	 * Update lfclfiscal. 
	 *  
	 * @param lfclfiscalRequest the lfclfiscal request 
	 * @return the lfclfiscal response 
	 */  
	public InternalResultsResponse<Lfclfiscal> updateLfclfiscal(LfclfiscalRequest lfclfiscalRequest); 
   
	/** 
	 * Delete lfclfiscal. 
	 *   
	 * @param lfclfiscalRequest the lfclfiscal request 
	 * @return the lfclfiscal response  
	 */ 
	public InternalResponse deleteLfclfiscal(LfclfiscalRequest lfclfiscalRequest); 
  
	/** 
	 * Fetch all lfclfiscal.
	 *   
	 * @param inquirylfclfiscalRequest the inquirylfclfiscal request 
	 * @return the inquiry lfclfiscal response 
	 */  
	public InternalResultsResponse<Lfclfiscal> fetchAllLfclfiscal(InquiryLfclfiscalRequest inquirylfclfiscalRequest);
  
	/** 
	 * Fetch lfclfiscal by id. 
	 * 
	 * @param inquirylfclfiscalRequest the inquirylfclfiscal request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfclfiscal> fetchLfclfiscalById(LfclfiscalRequest lfclfiscalRequest); 
 
	/** 
	 * Fetch all lfclfiscal types. 
	 * 
	 * @param request the request 
	 * @return the lfclfiscal response 
	 */ 
	public InternalResultsResponse<Lfclfiscal> fetchAllLfclfiscalTypes(InquiryLfclfiscalRequest inquirylfclfiscalRequest);  
  
	/** 
	 * Fetch all lfclfiscal filial. 
	 *  
	 * @param request the request 
	 * @return the lfclfiscal response 
	 */ 
	public InternalResultsResponse<Lfclfiscal> fetchAllLfclfiscalFilial(LfclfiscalRequest lfclfiscalRequest);
 
} 
