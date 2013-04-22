package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfncm.model.request.LfncmRequest;
import com.sensus.mlc.lfncm.model.request.InquiryLfncmRequest;
import com.sensus.mlc.lfncm.model.response.LfncmResponse;
import com.sensus.mlc.lfncm.model.response.InquiryLfncmResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfncmBCF.
 *
 * @author Washington.Costa
 */
public interface ILfncmBCF 
{

	/** 
	 * Insert lfncm.
	 *
	 * @param lfncmRequest the lfncm request
	 * @return the lfncm response 
	 */
	public LfncmResponse insertLfncm(LfncmRequest lfncmRequest);
  
	/**
	 * Update lfncm.
	 *
	 * @param lfncmRequest the lfncm request
	 * @return the lfncm response
	 */
	public LfncmResponse updateLfncm(LfncmRequest lfncmRequest);
  
	/**  
	 * Delete lfncm.  
	 *     
	 * @param lfncmRequest the lfncm request 
	 * @return the lfncm response
	 */
	public LfncmResponse deleteLfncm(LfncmRequest lfncmRequest); 
   
	/**  
	 * Fetch all lfncm. 
	 *  
	 * @param inquiryLfncmRequest the inquiryLfncm request   
	 * @return the inquiry lfncm response 
	 */ 
	public InquiryLfncmResponse fetchAllLfncm(InquiryLfncmRequest inquiryLfncmRequest); 
   
	/** 
	 * Fetch lfncm by id. 
	 *   
	 * @param LfncmRequest the lfncm request 
	 * @return the lfncm response  
	 */ 
	public LfncmResponse fetchLfncmById(LfncmRequest lfncmRequest); 
 
} 
