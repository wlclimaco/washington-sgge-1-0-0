package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lffrete.model.request.LffreteRequest;
import com.sensus.mlc.lffrete.model.request.InquiryLffreteRequest;
import com.sensus.mlc.lffrete.model.response.LffreteResponse;
import com.sensus.mlc.lffrete.model.response.InquiryLffreteResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILffreteBCF.
 *
 * @author Washington.Costa
 */
public interface ILffreteBCF 
{

	/** 
	 * Insert lffrete.
	 *
	 * @param lffreteRequest the lffrete request
	 * @return the lffrete response 
	 */
	public LffreteResponse insertLffrete(LffreteRequest lffreteRequest);
  
	/**
	 * Update lffrete.
	 *
	 * @param lffreteRequest the lffrete request
	 * @return the lffrete response
	 */
	public LffreteResponse updateLffrete(LffreteRequest lffreteRequest);
  
	/**  
	 * Delete lffrete.  
	 *     
	 * @param lffreteRequest the lffrete request 
	 * @return the lffrete response
	 */
	public LffreteResponse deleteLffrete(LffreteRequest lffreteRequest); 
   
	/**  
	 * Fetch all lffrete. 
	 *  
	 * @param inquiryLffreteRequest the inquiryLffrete request   
	 * @return the inquiry lffrete response 
	 */ 
	public InquiryLffreteResponse fetchAllLffrete(InquiryLffreteRequest inquiryLffreteRequest); 
   
	/** 
	 * Fetch lffrete by id. 
	 *   
	 * @param LffreteRequest the lffrete request 
	 * @return the lffrete response  
	 */ 
	public LffreteResponse fetchLffreteById(LffreteRequest lffreteRequest); 
 
} 
