package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lflivrofiscal.model.request.LflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.request.InquiryLflivrofiscalRequest;
import com.sensus.mlc.lflivrofiscal.model.response.LflivrofiscalResponse;
import com.sensus.mlc.lflivrofiscal.model.response.InquiryLflivrofiscalResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILflivrofiscalBCF.
 *
 * @author Washington.Costa
 */
public interface ILflivrofiscalBCF 
{

	/** 
	 * Insert lflivrofiscal.
	 *
	 * @param lflivrofiscalRequest the lflivrofiscal request
	 * @return the lflivrofiscal response 
	 */
	public LflivrofiscalResponse insertLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest);
  
	/**
	 * Update lflivrofiscal.
	 *
	 * @param lflivrofiscalRequest the lflivrofiscal request
	 * @return the lflivrofiscal response
	 */
	public LflivrofiscalResponse updateLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest);
  
	/**  
	 * Delete lflivrofiscal.  
	 *     
	 * @param lflivrofiscalRequest the lflivrofiscal request 
	 * @return the lflivrofiscal response
	 */
	public LflivrofiscalResponse deleteLflivrofiscal(LflivrofiscalRequest lflivrofiscalRequest); 
   
	/**  
	 * Fetch all lflivrofiscal. 
	 *  
	 * @param inquiryLflivrofiscalRequest the inquiryLflivrofiscal request   
	 * @return the inquiry lflivrofiscal response 
	 */ 
	public InquiryLflivrofiscalResponse fetchAllLflivrofiscal(InquiryLflivrofiscalRequest inquiryLflivrofiscalRequest); 
   
	/** 
	 * Fetch lflivrofiscal by id. 
	 *   
	 * @param LflivrofiscalRequest the lflivrofiscal request 
	 * @return the lflivrofiscal response  
	 */ 
	public LflivrofiscalResponse fetchLflivrofiscalById(LflivrofiscalRequest lflivrofiscalRequest); 
 
} 
