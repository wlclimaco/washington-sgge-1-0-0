package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfclfiscal.model.request.LfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.request.InquiryLfclfiscalRequest;
import com.sensus.mlc.lfclfiscal.model.response.LfclfiscalResponse;
import com.sensus.mlc.lfclfiscal.model.response.InquiryLfclfiscalResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfclfiscalBCF.
 *
 * @author Washington.Costa
 */
public interface ILfclfiscalBCF 
{

	/** 
	 * Insert lfclfiscal.
	 *
	 * @param lfclfiscalRequest the lfclfiscal request
	 * @return the lfclfiscal response 
	 */
	public LfclfiscalResponse insertLfclfiscal(LfclfiscalRequest lfclfiscalRequest);
  
	/**
	 * Update lfclfiscal.
	 *
	 * @param lfclfiscalRequest the lfclfiscal request
	 * @return the lfclfiscal response
	 */
	public LfclfiscalResponse updateLfclfiscal(LfclfiscalRequest lfclfiscalRequest);
  
	/**  
	 * Delete lfclfiscal.  
	 *     
	 * @param lfclfiscalRequest the lfclfiscal request 
	 * @return the lfclfiscal response
	 */
	public LfclfiscalResponse deleteLfclfiscal(LfclfiscalRequest lfclfiscalRequest); 
   
	/**  
	 * Fetch all lfclfiscal. 
	 *  
	 * @param inquiryLfclfiscalRequest the inquiryLfclfiscal request   
	 * @return the inquiry lfclfiscal response 
	 */ 
	public InquiryLfclfiscalResponse fetchAllLfclfiscal(InquiryLfclfiscalRequest inquiryLfclfiscalRequest); 
   
	/** 
	 * Fetch lfclfiscal by id. 
	 *   
	 * @param LfclfiscalRequest the lfclfiscal request 
	 * @return the lfclfiscal response  
	 */ 
	public LfclfiscalResponse fetchLfclfiscalById(LfclfiscalRequest lfclfiscalRequest); 
 
} 
