package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitregrafiscal.model.Lfitregrafiscal; 
import com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest; 
import com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfitregrafiscalBCL 
{   
    
	/**  
	 * Insert lfitregrafiscal. 
	 * 
	 * @param lfitregrafiscalRequest the lfitregrafiscal request  
	 * @return the lfitregrafiscal response  
	 */ 
	public InternalResultsResponse<Lfitregrafiscal> insertLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest);
  
	/** 
	 * Update lfitregrafiscal. 
	 *  
	 * @param lfitregrafiscalRequest the lfitregrafiscal request 
	 * @return the lfitregrafiscal response 
	 */  
	public InternalResultsResponse<Lfitregrafiscal> updateLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest); 
   
	/** 
	 * Delete lfitregrafiscal. 
	 *   
	 * @param lfitregrafiscalRequest the lfitregrafiscal request 
	 * @return the lfitregrafiscal response  
	 */ 
	public InternalResponse deleteLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest); 
  
	/** 
	 * Fetch all lfitregrafiscal.
	 *   
	 * @param inquirylfitregrafiscalRequest the inquirylfitregrafiscal request 
	 * @return the inquiry lfitregrafiscal response 
	 */  
	public InternalResultsResponse<Lfitregrafiscal> fetchAllLfitregrafiscal(InquiryLfitregrafiscalRequest inquirylfitregrafiscalRequest);
  
	/** 
	 * Fetch lfitregrafiscal by id. 
	 * 
	 * @param inquirylfitregrafiscalRequest the inquirylfitregrafiscal request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfitregrafiscal> fetchLfitregrafiscalById(LfitregrafiscalRequest lfitregrafiscalRequest); 
 
	/** 
	 * Fetch all lfitregrafiscal types. 
	 * 
	 * @param request the request 
	 * @return the lfitregrafiscal response 
	 */ 
	public InternalResultsResponse<Lfitregrafiscal> fetchAllLfitregrafiscalTypes(InquiryLfitregrafiscalRequest inquirylfitregrafiscalRequest);  
  
	/** 
	 * Fetch all lfitregrafiscal filial. 
	 *  
	 * @param request the request 
	 * @return the lfitregrafiscal response 
	 */ 
	public InternalResultsResponse<Lfitregrafiscal> fetchAllLfitregrafiscalFilial(LfitregrafiscalRequest lfitregrafiscalRequest);
 
} 
