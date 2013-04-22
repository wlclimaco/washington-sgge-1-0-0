package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfitclfiscal.model.request.LfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.request.InquiryLfitclfiscalRequest;
import com.sensus.mlc.lfitclfiscal.model.response.LfitclfiscalResponse;
import com.sensus.mlc.lfitclfiscal.model.response.InquiryLfitclfiscalResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfitclfiscalBCF.
 *
 * @author Washington.Costa
 */
public interface ILfitclfiscalBCF 
{

	/** 
	 * Insert lfitclfiscal.
	 *
	 * @param lfitclfiscalRequest the lfitclfiscal request
	 * @return the lfitclfiscal response 
	 */
	public LfitclfiscalResponse insertLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest);
  
	/**
	 * Update lfitclfiscal.
	 *
	 * @param lfitclfiscalRequest the lfitclfiscal request
	 * @return the lfitclfiscal response
	 */
	public LfitclfiscalResponse updateLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest);
  
	/**  
	 * Delete lfitclfiscal.  
	 *     
	 * @param lfitclfiscalRequest the lfitclfiscal request 
	 * @return the lfitclfiscal response
	 */
	public LfitclfiscalResponse deleteLfitclfiscal(LfitclfiscalRequest lfitclfiscalRequest); 
   
	/**  
	 * Fetch all lfitclfiscal. 
	 *  
	 * @param inquiryLfitclfiscalRequest the inquiryLfitclfiscal request   
	 * @return the inquiry lfitclfiscal response 
	 */ 
	public InquiryLfitclfiscalResponse fetchAllLfitclfiscal(InquiryLfitclfiscalRequest inquiryLfitclfiscalRequest); 
   
	/** 
	 * Fetch lfitclfiscal by id. 
	 *   
	 * @param LfitclfiscalRequest the lfitclfiscal request 
	 * @return the lfitclfiscal response  
	 */ 
	public LfitclfiscalResponse fetchLfitclfiscalById(LfitclfiscalRequest lfitclfiscalRequest); 
 
} 
