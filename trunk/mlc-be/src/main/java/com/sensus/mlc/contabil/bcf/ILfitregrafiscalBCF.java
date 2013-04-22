package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfitregrafiscal.model.request.LfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.request.InquiryLfitregrafiscalRequest;
import com.sensus.mlc.lfitregrafiscal.model.response.LfitregrafiscalResponse;
import com.sensus.mlc.lfitregrafiscal.model.response.InquiryLfitregrafiscalResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfitregrafiscalBCF.
 *
 * @author Washington.Costa
 */
public interface ILfitregrafiscalBCF 
{

	/** 
	 * Insert lfitregrafiscal.
	 *
	 * @param lfitregrafiscalRequest the lfitregrafiscal request
	 * @return the lfitregrafiscal response 
	 */
	public LfitregrafiscalResponse insertLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest);
  
	/**
	 * Update lfitregrafiscal.
	 *
	 * @param lfitregrafiscalRequest the lfitregrafiscal request
	 * @return the lfitregrafiscal response
	 */
	public LfitregrafiscalResponse updateLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest);
  
	/**  
	 * Delete lfitregrafiscal.  
	 *     
	 * @param lfitregrafiscalRequest the lfitregrafiscal request 
	 * @return the lfitregrafiscal response
	 */
	public LfitregrafiscalResponse deleteLfitregrafiscal(LfitregrafiscalRequest lfitregrafiscalRequest); 
   
	/**  
	 * Fetch all lfitregrafiscal. 
	 *  
	 * @param inquiryLfitregrafiscalRequest the inquiryLfitregrafiscal request   
	 * @return the inquiry lfitregrafiscal response 
	 */ 
	public InquiryLfitregrafiscalResponse fetchAllLfitregrafiscal(InquiryLfitregrafiscalRequest inquiryLfitregrafiscalRequest); 
   
	/** 
	 * Fetch lfitregrafiscal by id. 
	 *   
	 * @param LfitregrafiscalRequest the lfitregrafiscal request 
	 * @return the lfitregrafiscal response  
	 */ 
	public LfitregrafiscalResponse fetchLfitregrafiscalById(LfitregrafiscalRequest lfitregrafiscalRequest); 
 
} 
