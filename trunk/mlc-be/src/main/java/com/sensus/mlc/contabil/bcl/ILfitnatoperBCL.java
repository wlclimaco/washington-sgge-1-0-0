package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper; 
import com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest; 
import com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfitnatoperBCL 
{   
    
	/**  
	 * Insert lfitnatoper. 
	 * 
	 * @param lfitnatoperRequest the lfitnatoper request  
	 * @return the lfitnatoper response  
	 */ 
	public InternalResultsResponse<Lfitnatoper> insertLfitnatoper(LfitnatoperRequest lfitnatoperRequest);
  
	/** 
	 * Update lfitnatoper. 
	 *  
	 * @param lfitnatoperRequest the lfitnatoper request 
	 * @return the lfitnatoper response 
	 */  
	public InternalResultsResponse<Lfitnatoper> updateLfitnatoper(LfitnatoperRequest lfitnatoperRequest); 
   
	/** 
	 * Delete lfitnatoper. 
	 *   
	 * @param lfitnatoperRequest the lfitnatoper request 
	 * @return the lfitnatoper response  
	 */ 
	public InternalResponse deleteLfitnatoper(LfitnatoperRequest lfitnatoperRequest); 
  
	/** 
	 * Fetch all lfitnatoper.
	 *   
	 * @param inquirylfitnatoperRequest the inquirylfitnatoper request 
	 * @return the inquiry lfitnatoper response 
	 */  
	public InternalResultsResponse<Lfitnatoper> fetchAllLfitnatoper(InquiryLfitnatoperRequest inquirylfitnatoperRequest);
  
	/** 
	 * Fetch lfitnatoper by id. 
	 * 
	 * @param inquirylfitnatoperRequest the inquirylfitnatoper request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfitnatoper> fetchLfitnatoperById(LfitnatoperRequest lfitnatoperRequest); 
 
	/** 
	 * Fetch all lfitnatoper types. 
	 * 
	 * @param request the request 
	 * @return the lfitnatoper response 
	 */ 
	public InternalResultsResponse<Lfitnatoper> fetchAllLfitnatoperTypes(InquiryLfitnatoperRequest inquirylfitnatoperRequest);  
  
	/** 
	 * Fetch all lfitnatoper filial. 
	 *  
	 * @param request the request 
	 * @return the lfitnatoper response 
	 */ 
	public InternalResultsResponse<Lfitnatoper> fetchAllLfitnatoperFilial(LfitnatoperRequest lfitnatoperRequest);
 
} 
