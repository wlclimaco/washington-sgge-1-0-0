package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm; 
import com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest; 
import com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfncmnbmBCL 
{   
    
	/**  
	 * Insert lfncmnbm. 
	 * 
	 * @param lfncmnbmRequest the lfncmnbm request  
	 * @return the lfncmnbm response  
	 */ 
	public InternalResultsResponse<Lfncmnbm> insertLfncmnbm(LfncmnbmRequest lfncmnbmRequest);
  
	/** 
	 * Update lfncmnbm. 
	 *  
	 * @param lfncmnbmRequest the lfncmnbm request 
	 * @return the lfncmnbm response 
	 */  
	public InternalResultsResponse<Lfncmnbm> updateLfncmnbm(LfncmnbmRequest lfncmnbmRequest); 
   
	/** 
	 * Delete lfncmnbm. 
	 *   
	 * @param lfncmnbmRequest the lfncmnbm request 
	 * @return the lfncmnbm response  
	 */ 
	public InternalResponse deleteLfncmnbm(LfncmnbmRequest lfncmnbmRequest); 
  
	/** 
	 * Fetch all lfncmnbm.
	 *   
	 * @param inquirylfncmnbmRequest the inquirylfncmnbm request 
	 * @return the inquiry lfncmnbm response 
	 */  
	public InternalResultsResponse<Lfncmnbm> fetchAllLfncmnbm(InquiryLfncmnbmRequest inquirylfncmnbmRequest);
  
	/** 
	 * Fetch lfncmnbm by id. 
	 * 
	 * @param inquirylfncmnbmRequest the inquirylfncmnbm request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfncmnbm> fetchLfncmnbmById(LfncmnbmRequest lfncmnbmRequest); 
 
	/** 
	 * Fetch all lfncmnbm types. 
	 * 
	 * @param request the request 
	 * @return the lfncmnbm response 
	 */ 
	public InternalResultsResponse<Lfncmnbm> fetchAllLfncmnbmTypes(InquiryLfncmnbmRequest inquirylfncmnbmRequest);  
  
	/** 
	 * Fetch all lfncmnbm filial. 
	 *  
	 * @param request the request 
	 * @return the lfncmnbm response 
	 */ 
	public InternalResultsResponse<Lfncmnbm> fetchAllLfncmnbmFilial(LfncmnbmRequest lfncmnbmRequest);
 
} 
