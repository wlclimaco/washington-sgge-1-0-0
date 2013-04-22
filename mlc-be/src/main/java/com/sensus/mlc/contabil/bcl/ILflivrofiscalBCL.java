package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lflivrofiscal.model.Lflivrofiscal; 
import com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest; 
import com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILflivrofiscalBCL 
{   
    
	/**  
	 * Insert lflivrofiscal. 
	 * 
	 * @param lflivrofiscalRequest the lflivrofiscal request  
	 * @return the lflivrofiscal response  
	 */ 
	public InternalResultsResponse<Lflivrofiscal> insertLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest);
  
	/** 
	 * Update lflivrofiscal. 
	 *  
	 * @param lflivrofiscalRequest the lflivrofiscal request 
	 * @return the lflivrofiscal response 
	 */  
	public InternalResultsResponse<Lflivrofiscal> updateLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest); 
   
	/** 
	 * Delete lflivrofiscal. 
	 *   
	 * @param lflivrofiscalRequest the lflivrofiscal request 
	 * @return the lflivrofiscal response  
	 */ 
	public InternalResponse deleteLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest); 
  
	/** 
	 * Fetch all lflivrofiscal.
	 *   
	 * @param inquirylflivrofiscalRequest the inquirylflivrofiscal request 
	 * @return the inquiry lflivrofiscal response 
	 */  
	public InternalResultsResponse<Lflivrofiscal> fetchAllLflivrofiscal(InquiryLflivrofiscalRequest inquirylflivrofiscalRequest);
  
	/** 
	 * Fetch lflivrofiscal by id. 
	 * 
	 * @param inquirylflivrofiscalRequest the inquirylflivrofiscal request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lflivrofiscal> fetchLflivrofiscalById(LflivrofiscalRequest lflivrofiscalRequest); 
 
	/** 
	 * Fetch all lflivrofiscal types. 
	 * 
	 * @param request the request 
	 * @return the lflivrofiscal response 
	 */ 
	public InternalResultsResponse<Lflivrofiscal> fetchAllLflivrofiscalTypes(InquiryLflivrofiscalRequest inquirylflivrofiscalRequest);  
  
	/** 
	 * Fetch all lflivrofiscal filial. 
	 *  
	 * @param request the request 
	 * @return the lflivrofiscal response 
	 */ 
	public InternalResultsResponse<Lflivrofiscal> fetchAllLflivrofiscalFilial(LflivrofiscalRequest lflivrofiscalRequest);
 
} 
