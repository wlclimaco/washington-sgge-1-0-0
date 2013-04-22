package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfnbm.model.request.LfnbmRequest;
import com.sensus.mlc.lfnbm.model.request.InquiryLfnbmRequest;
import com.sensus.mlc.lfnbm.model.response.LfnbmResponse;
import com.sensus.mlc.lfnbm.model.response.InquiryLfnbmResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfnbmBCF.
 *
 * @author Washington.Costa
 */
public interface ILfnbmBCF 
{

	/** 
	 * Insert lfnbm.
	 *
	 * @param lfnbmRequest the lfnbm request
	 * @return the lfnbm response 
	 */
	public LfnbmResponse insertLfnbm(LfnbmRequest lfnbmRequest);
  
	/**
	 * Update lfnbm.
	 *
	 * @param lfnbmRequest the lfnbm request
	 * @return the lfnbm response
	 */
	public LfnbmResponse updateLfnbm(LfnbmRequest lfnbmRequest);
  
	/**  
	 * Delete lfnbm.  
	 *     
	 * @param lfnbmRequest the lfnbm request 
	 * @return the lfnbm response
	 */
	public LfnbmResponse deleteLfnbm(LfnbmRequest lfnbmRequest); 
   
	/**  
	 * Fetch all lfnbm. 
	 *  
	 * @param inquiryLfnbmRequest the inquiryLfnbm request   
	 * @return the inquiry lfnbm response 
	 */ 
	public InquiryLfnbmResponse fetchAllLfnbm(InquiryLfnbmRequest inquiryLfnbmRequest); 
   
	/** 
	 * Fetch lfnbm by id. 
	 *   
	 * @param LfnbmRequest the lfnbm request 
	 * @return the lfnbm response  
	 */ 
	public LfnbmResponse fetchLfnbmById(LfnbmRequest lfnbmRequest); 
 
} 
