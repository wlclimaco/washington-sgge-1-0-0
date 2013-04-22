package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfregrafiscal.model.request.LfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.request.InquiryLfregrafiscalRequest;
import com.sensus.mlc.lfregrafiscal.model.response.LfregrafiscalResponse;
import com.sensus.mlc.lfregrafiscal.model.response.InquiryLfregrafiscalResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfregrafiscalBCF.
 *
 * @author Washington.Costa
 */
public interface ILfregrafiscalBCF 
{

	/** 
	 * Insert lfregrafiscal.
	 *
	 * @param lfregrafiscalRequest the lfregrafiscal request
	 * @return the lfregrafiscal response 
	 */
	public LfregrafiscalResponse insertLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest);
  
	/**
	 * Update lfregrafiscal.
	 *
	 * @param lfregrafiscalRequest the lfregrafiscal request
	 * @return the lfregrafiscal response
	 */
	public LfregrafiscalResponse updateLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest);
  
	/**  
	 * Delete lfregrafiscal.  
	 *     
	 * @param lfregrafiscalRequest the lfregrafiscal request 
	 * @return the lfregrafiscal response
	 */
	public LfregrafiscalResponse deleteLfregrafiscal(LfregrafiscalRequest lfregrafiscalRequest); 
   
	/**  
	 * Fetch all lfregrafiscal. 
	 *  
	 * @param inquiryLfregrafiscalRequest the inquiryLfregrafiscal request   
	 * @return the inquiry lfregrafiscal response 
	 */ 
	public InquiryLfregrafiscalResponse fetchAllLfregrafiscal(InquiryLfregrafiscalRequest inquiryLfregrafiscalRequest); 
   
	/** 
	 * Fetch lfregrafiscal by id. 
	 *   
	 * @param LfregrafiscalRequest the lfregrafiscal request 
	 * @return the lfregrafiscal response  
	 */ 
	public LfregrafiscalResponse fetchLfregrafiscalById(LfregrafiscalRequest lfregrafiscalRequest); 
 
} 
