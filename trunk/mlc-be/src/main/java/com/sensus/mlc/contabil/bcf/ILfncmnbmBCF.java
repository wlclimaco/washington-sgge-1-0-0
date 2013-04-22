package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfncmnbm.model.request.LfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.request.InquiryLfncmnbmRequest;
import com.sensus.mlc.lfncmnbm.model.response.LfncmnbmResponse;
import com.sensus.mlc.lfncmnbm.model.response.InquiryLfncmnbmResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfncmnbmBCF.
 *
 * @author Washington.Costa
 */
public interface ILfncmnbmBCF 
{

	/** 
	 * Insert lfncmnbm.
	 *
	 * @param lfncmnbmRequest the lfncmnbm request
	 * @return the lfncmnbm response 
	 */
	public LfncmnbmResponse insertLfncmnbm(LfncmnbmRequest lfncmnbmRequest);
  
	/**
	 * Update lfncmnbm.
	 *
	 * @param lfncmnbmRequest the lfncmnbm request
	 * @return the lfncmnbm response
	 */
	public LfncmnbmResponse updateLfncmnbm(LfncmnbmRequest lfncmnbmRequest);
  
	/**  
	 * Delete lfncmnbm.  
	 *     
	 * @param lfncmnbmRequest the lfncmnbm request 
	 * @return the lfncmnbm response
	 */
	public LfncmnbmResponse deleteLfncmnbm(LfncmnbmRequest lfncmnbmRequest); 
   
	/**  
	 * Fetch all lfncmnbm. 
	 *  
	 * @param inquiryLfncmnbmRequest the inquiryLfncmnbm request   
	 * @return the inquiry lfncmnbm response 
	 */ 
	public InquiryLfncmnbmResponse fetchAllLfncmnbm(InquiryLfncmnbmRequest inquiryLfncmnbmRequest); 
   
	/** 
	 * Fetch lfncmnbm by id. 
	 *   
	 * @param LfncmnbmRequest the lfncmnbm request 
	 * @return the lfncmnbm response  
	 */ 
	public LfncmnbmResponse fetchLfncmnbmById(LfncmnbmRequest lfncmnbmRequest); 
 
} 
