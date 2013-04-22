package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfnatoper.model.Lfnatoper; 
import com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest; 
import com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfnatoperBCL 
{   
    
	/**  
	 * Insert lfnatoper. 
	 * 
	 * @param lfnatoperRequest the lfnatoper request  
	 * @return the lfnatoper response  
	 */ 
	public InternalResultsResponse<Lfnatoper> insertLfnatoper(LfnatoperRequest lfnatoperRequest);
  
	/** 
	 * Update lfnatoper. 
	 *  
	 * @param lfnatoperRequest the lfnatoper request 
	 * @return the lfnatoper response 
	 */  
	public InternalResultsResponse<Lfnatoper> updateLfnatoper(LfnatoperRequest lfnatoperRequest); 
   
	/** 
	 * Delete lfnatoper. 
	 *   
	 * @param lfnatoperRequest the lfnatoper request 
	 * @return the lfnatoper response  
	 */ 
	public InternalResponse deleteLfnatoper(LfnatoperRequest lfnatoperRequest); 
  
	/** 
	 * Fetch all lfnatoper.
	 *   
	 * @param inquirylfnatoperRequest the inquirylfnatoper request 
	 * @return the inquiry lfnatoper response 
	 */  
	public InternalResultsResponse<Lfnatoper> fetchAllLfnatoper(InquiryLfnatoperRequest inquirylfnatoperRequest);
  
	/** 
	 * Fetch lfnatoper by id. 
	 * 
	 * @param inquirylfnatoperRequest the inquirylfnatoper request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfnatoper> fetchLfnatoperById(LfnatoperRequest lfnatoperRequest); 
 
	/** 
	 * Fetch all lfnatoper types. 
	 * 
	 * @param request the request 
	 * @return the lfnatoper response 
	 */ 
	public InternalResultsResponse<Lfnatoper> fetchAllLfnatoperTypes(InquiryLfnatoperRequest inquirylfnatoperRequest);  
  
	/** 
	 * Fetch all lfnatoper filial. 
	 *  
	 * @param request the request 
	 * @return the lfnatoper response 
	 */ 
	public InternalResultsResponse<Lfnatoper> fetchAllLfnatoperFilial(LfnatoperRequest lfnatoperRequest);
 
} 
