package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfregrafiscal.model.Lfregrafiscal; 
import com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest; 
import com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfregrafiscalBCL 
{   
    
	/**  
	 * Insert lfregrafiscal. 
	 * 
	 * @param lfregrafiscalRequest the lfregrafiscal request  
	 * @return the lfregrafiscal response  
	 */ 
	public InternalResultsResponse<Lfregrafiscal> insertLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest);
  
	/** 
	 * Update lfregrafiscal. 
	 *  
	 * @param lfregrafiscalRequest the lfregrafiscal request 
	 * @return the lfregrafiscal response 
	 */  
	public InternalResultsResponse<Lfregrafiscal> updateLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest); 
   
	/** 
	 * Delete lfregrafiscal. 
	 *   
	 * @param lfregrafiscalRequest the lfregrafiscal request 
	 * @return the lfregrafiscal response  
	 */ 
	public InternalResponse deleteLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest); 
  
	/** 
	 * Fetch all lfregrafiscal.
	 *   
	 * @param inquirylfregrafiscalRequest the inquirylfregrafiscal request 
	 * @return the inquiry lfregrafiscal response 
	 */  
	public InternalResultsResponse<Lfregrafiscal> fetchAllLfregrafiscal(InquiryLfregrafiscalRequest inquirylfregrafiscalRequest);
  
	/** 
	 * Fetch lfregrafiscal by id. 
	 * 
	 * @param inquirylfregrafiscalRequest the inquirylfregrafiscal request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfregrafiscal> fetchLfregrafiscalById(LfregrafiscalRequest lfregrafiscalRequest); 
 
	/** 
	 * Fetch all lfregrafiscal types. 
	 * 
	 * @param request the request 
	 * @return the lfregrafiscal response 
	 */ 
	public InternalResultsResponse<Lfregrafiscal> fetchAllLfregrafiscalTypes(InquiryLfregrafiscalRequest inquirylfregrafiscalRequest);  
  
	/** 
	 * Fetch all lfregrafiscal filial. 
	 *  
	 * @param request the request 
	 * @return the lfregrafiscal response 
	 */ 
	public InternalResultsResponse<Lfregrafiscal> fetchAllLfregrafiscalFilial(LfregrafiscalRequest lfregrafiscalRequest);
 
} 
