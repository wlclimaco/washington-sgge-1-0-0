package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc; 
import com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest; 
import com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfmoddocfiscBCL 
{   
    
	/**  
	 * Insert lfmoddocfisc. 
	 * 
	 * @param lfmoddocfiscRequest the lfmoddocfisc request  
	 * @return the lfmoddocfisc response  
	 */ 
	public InternalResultsResponse<Lfmoddocfisc> insertLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest);
  
	/** 
	 * Update lfmoddocfisc. 
	 *  
	 * @param lfmoddocfiscRequest the lfmoddocfisc request 
	 * @return the lfmoddocfisc response 
	 */  
	public InternalResultsResponse<Lfmoddocfisc> updateLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest); 
   
	/** 
	 * Delete lfmoddocfisc. 
	 *   
	 * @param lfmoddocfiscRequest the lfmoddocfisc request 
	 * @return the lfmoddocfisc response  
	 */ 
	public InternalResponse deleteLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest); 
  
	/** 
	 * Fetch all lfmoddocfisc.
	 *   
	 * @param inquirylfmoddocfiscRequest the inquirylfmoddocfisc request 
	 * @return the inquiry lfmoddocfisc response 
	 */  
	public InternalResultsResponse<Lfmoddocfisc> fetchAllLfmoddocfisc(InquiryLfmoddocfiscRequest inquirylfmoddocfiscRequest);
  
	/** 
	 * Fetch lfmoddocfisc by id. 
	 * 
	 * @param inquirylfmoddocfiscRequest the inquirylfmoddocfisc request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfmoddocfisc> fetchLfmoddocfiscById(LfmoddocfiscRequest lfmoddocfiscRequest); 
 
	/** 
	 * Fetch all lfmoddocfisc types. 
	 * 
	 * @param request the request 
	 * @return the lfmoddocfisc response 
	 */ 
	public InternalResultsResponse<Lfmoddocfisc> fetchAllLfmoddocfiscTypes(InquiryLfmoddocfiscRequest inquirylfmoddocfiscRequest);  
  
	/** 
	 * Fetch all lfmoddocfisc filial. 
	 *  
	 * @param request the request 
	 * @return the lfmoddocfisc response 
	 */ 
	public InternalResultsResponse<Lfmoddocfisc> fetchAllLfmoddocfiscFilial(LfmoddocfiscRequest lfmoddocfiscRequest);
 
} 
