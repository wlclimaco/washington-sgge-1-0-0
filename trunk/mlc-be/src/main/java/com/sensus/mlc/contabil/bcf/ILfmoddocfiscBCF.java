package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfmoddocfisc.model.request.LfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.request.InquiryLfmoddocfiscRequest;
import com.sensus.mlc.lfmoddocfisc.model.response.LfmoddocfiscResponse;
import com.sensus.mlc.lfmoddocfisc.model.response.InquiryLfmoddocfiscResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfmoddocfiscBCF.
 *
 * @author Washington.Costa
 */
public interface ILfmoddocfiscBCF 
{

	/** 
	 * Insert lfmoddocfisc.
	 *
	 * @param lfmoddocfiscRequest the lfmoddocfisc request
	 * @return the lfmoddocfisc response 
	 */
	public LfmoddocfiscResponse insertLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest);
  
	/**
	 * Update lfmoddocfisc.
	 *
	 * @param lfmoddocfiscRequest the lfmoddocfisc request
	 * @return the lfmoddocfisc response
	 */
	public LfmoddocfiscResponse updateLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest);
  
	/**  
	 * Delete lfmoddocfisc.  
	 *     
	 * @param lfmoddocfiscRequest the lfmoddocfisc request 
	 * @return the lfmoddocfisc response
	 */
	public LfmoddocfiscResponse deleteLfmoddocfisc(LfmoddocfiscRequest lfmoddocfiscRequest); 
   
	/**  
	 * Fetch all lfmoddocfisc. 
	 *  
	 * @param inquiryLfmoddocfiscRequest the inquiryLfmoddocfisc request   
	 * @return the inquiry lfmoddocfisc response 
	 */ 
	public InquiryLfmoddocfiscResponse fetchAllLfmoddocfisc(InquiryLfmoddocfiscRequest inquiryLfmoddocfiscRequest); 
   
	/** 
	 * Fetch lfmoddocfisc by id. 
	 *   
	 * @param LfmoddocfiscRequest the lfmoddocfisc request 
	 * @return the lfmoddocfisc response  
	 */ 
	public LfmoddocfiscResponse fetchLfmoddocfiscById(LfmoddocfiscRequest lfmoddocfiscRequest); 
 
} 
