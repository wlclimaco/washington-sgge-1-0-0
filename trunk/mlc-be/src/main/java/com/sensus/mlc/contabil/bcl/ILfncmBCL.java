package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfncm.model.Lfncm; 
import com.sensus.mlc.lfncm.model.request.LfncmRequest; 
import com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfncmBCL 
{   
    
	/**  
	 * Insert lfncm. 
	 * 
	 * @param lfncmRequest the lfncm request  
	 * @return the lfncm response  
	 */ 
	public InternalResultsResponse<Lfncm> insertLfncm(LfncmRequest lfncmRequest);
  
	/** 
	 * Update lfncm. 
	 *  
	 * @param lfncmRequest the lfncm request 
	 * @return the lfncm response 
	 */  
	public InternalResultsResponse<Lfncm> updateLfncm(LfncmRequest lfncmRequest); 
   
	/** 
	 * Delete lfncm. 
	 *   
	 * @param lfncmRequest the lfncm request 
	 * @return the lfncm response  
	 */ 
	public InternalResponse deleteLfncm(LfncmRequest lfncmRequest); 
  
	/** 
	 * Fetch all lfncm.
	 *   
	 * @param inquirylfncmRequest the inquirylfncm request 
	 * @return the inquiry lfncm response 
	 */  
	public InternalResultsResponse<Lfncm> fetchAllLfncm(InquiryLfncmRequest inquirylfncmRequest);
  
	/** 
	 * Fetch lfncm by id. 
	 * 
	 * @param inquirylfncmRequest the inquirylfncm request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfncm> fetchLfncmById(LfncmRequest lfncmRequest); 
 
	/** 
	 * Fetch all lfncm types. 
	 * 
	 * @param request the request 
	 * @return the lfncm response 
	 */ 
	public InternalResultsResponse<Lfncm> fetchAllLfncmTypes(InquiryLfncmRequest inquirylfncmRequest);  
  
	/** 
	 * Fetch all lfncm filial. 
	 *  
	 * @param request the request 
	 * @return the lfncm response 
	 */ 
	public InternalResultsResponse<Lfncm> fetchAllLfncmFilial(LfncmRequest lfncmRequest);
 
} 
