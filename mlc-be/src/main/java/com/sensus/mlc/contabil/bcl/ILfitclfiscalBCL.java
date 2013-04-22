package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitclfiscal.model.Lfitclfiscal; 
import com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest; 
import com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfitclfiscalBCL 
{   
    
	/**  
	 * Insert lfitclfiscal. 
	 * 
	 * @param lfitclfiscalRequest the lfitclfiscal request  
	 * @return the lfitclfiscal response  
	 */ 
	public InternalResultsResponse<Lfitclfiscal> insertLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest);
  
	/** 
	 * Update lfitclfiscal. 
	 *  
	 * @param lfitclfiscalRequest the lfitclfiscal request 
	 * @return the lfitclfiscal response 
	 */  
	public InternalResultsResponse<Lfitclfiscal> updateLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest); 
   
	/** 
	 * Delete lfitclfiscal. 
	 *   
	 * @param lfitclfiscalRequest the lfitclfiscal request 
	 * @return the lfitclfiscal response  
	 */ 
	public InternalResponse deleteLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest); 
  
	/** 
	 * Fetch all lfitclfiscal.
	 *   
	 * @param inquirylfitclfiscalRequest the inquirylfitclfiscal request 
	 * @return the inquiry lfitclfiscal response 
	 */  
	public InternalResultsResponse<Lfitclfiscal> fetchAllLfitclfiscal(InquiryLfitclfiscalRequest inquirylfitclfiscalRequest);
  
	/** 
	 * Fetch lfitclfiscal by id. 
	 * 
	 * @param inquirylfitclfiscalRequest the inquirylfitclfiscal request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfitclfiscal> fetchLfitclfiscalById(LfitclfiscalRequest lfitclfiscalRequest); 
 
	/** 
	 * Fetch all lfitclfiscal types. 
	 * 
	 * @param request the request 
	 * @return the lfitclfiscal response 
	 */ 
	public InternalResultsResponse<Lfitclfiscal> fetchAllLfitclfiscalTypes(InquiryLfitclfiscalRequest inquirylfitclfiscalRequest);  
  
	/** 
	 * Fetch all lfitclfiscal filial. 
	 *  
	 * @param request the request 
	 * @return the lfitclfiscal response 
	 */ 
	public InternalResultsResponse<Lfitclfiscal> fetchAllLfitclfiscalFilial(LfitclfiscalRequest lfitclfiscalRequest);
 
} 
