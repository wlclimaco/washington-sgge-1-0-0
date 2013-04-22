package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lffrete.model.Lffrete; 
import com.sensus.mlc.lffrete.model.request.LffreteRequest; 
import com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILffreteBCL 
{   
    
	/**  
	 * Insert lffrete. 
	 * 
	 * @param lffreteRequest the lffrete request  
	 * @return the lffrete response  
	 */ 
	public InternalResultsResponse<Lffrete> insertLffrete(LffreteRequest lffreteRequest);
  
	/** 
	 * Update lffrete. 
	 *  
	 * @param lffreteRequest the lffrete request 
	 * @return the lffrete response 
	 */  
	public InternalResultsResponse<Lffrete> updateLffrete(LffreteRequest lffreteRequest); 
   
	/** 
	 * Delete lffrete. 
	 *   
	 * @param lffreteRequest the lffrete request 
	 * @return the lffrete response  
	 */ 
	public InternalResponse deleteLffrete(LffreteRequest lffreteRequest); 
  
	/** 
	 * Fetch all lffrete.
	 *   
	 * @param inquirylffreteRequest the inquirylffrete request 
	 * @return the inquiry lffrete response 
	 */  
	public InternalResultsResponse<Lffrete> fetchAllLffrete(InquiryLffreteRequest inquirylffreteRequest);
  
	/** 
	 * Fetch lffrete by id. 
	 * 
	 * @param inquirylffreteRequest the inquirylffrete request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lffrete> fetchLffreteById(LffreteRequest lffreteRequest); 
 
	/** 
	 * Fetch all lffrete types. 
	 * 
	 * @param request the request 
	 * @return the lffrete response 
	 */ 
	public InternalResultsResponse<Lffrete> fetchAllLffreteTypes(InquiryLffreteRequest inquirylffreteRequest);  
  
	/** 
	 * Fetch all lffrete filial. 
	 *  
	 * @param request the request 
	 * @return the lffrete response 
	 */ 
	public InternalResultsResponse<Lffrete> fetchAllLffreteFilial(LffreteRequest lffreteRequest);
 
} 
