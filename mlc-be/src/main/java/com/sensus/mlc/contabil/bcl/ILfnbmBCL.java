package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfnbm.model.Lfnbm; 
import com.sensus.mlc.lfnbm.model.request.LfnbmRequest; 
import com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfnbmBCL 
{   
    
	/**  
	 * Insert lfnbm. 
	 * 
	 * @param lfnbmRequest the lfnbm request  
	 * @return the lfnbm response  
	 */ 
	public InternalResultsResponse<Lfnbm> insertLfnbm(LfnbmRequest lfnbmRequest);
  
	/** 
	 * Update lfnbm. 
	 *  
	 * @param lfnbmRequest the lfnbm request 
	 * @return the lfnbm response 
	 */  
	public InternalResultsResponse<Lfnbm> updateLfnbm(LfnbmRequest lfnbmRequest); 
   
	/** 
	 * Delete lfnbm. 
	 *   
	 * @param lfnbmRequest the lfnbm request 
	 * @return the lfnbm response  
	 */ 
	public InternalResponse deleteLfnbm(LfnbmRequest lfnbmRequest); 
  
	/** 
	 * Fetch all lfnbm.
	 *   
	 * @param inquirylfnbmRequest the inquirylfnbm request 
	 * @return the inquiry lfnbm response 
	 */  
	public InternalResultsResponse<Lfnbm> fetchAllLfnbm(InquiryLfnbmRequest inquirylfnbmRequest);
  
	/** 
	 * Fetch lfnbm by id. 
	 * 
	 * @param inquirylfnbmRequest the inquirylfnbm request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfnbm> fetchLfnbmById(LfnbmRequest lfnbmRequest); 
 
	/** 
	 * Fetch all lfnbm types. 
	 * 
	 * @param request the request 
	 * @return the lfnbm response 
	 */ 
	public InternalResultsResponse<Lfnbm> fetchAllLfnbmTypes(InquiryLfnbmRequest inquirylfnbmRequest);  
  
	/** 
	 * Fetch all lfnbm filial. 
	 *  
	 * @param request the request 
	 * @return the lfnbm response 
	 */ 
	public InternalResultsResponse<Lfnbm> fetchAllLfnbmFilial(LfnbmRequest lfnbmRequest);
 
} 
