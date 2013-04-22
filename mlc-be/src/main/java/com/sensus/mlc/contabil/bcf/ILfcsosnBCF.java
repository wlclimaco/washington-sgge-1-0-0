package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest;
import com.sensus.mlc.lfcsosn.model.response.LfcsosnResponse;
import com.sensus.mlc.lfcsosn.model.response.InquiryLfcsosnResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfcsosnBCF.
 *
 * @author Washington.Costa
 */
public interface ILfcsosnBCF 
{

	/** 
	 * Insert lfcsosn.
	 *
	 * @param lfcsosnRequest the lfcsosn request
	 * @return the lfcsosn response 
	 */
	public LfcsosnResponse insertLfcsosn(LfcsosnRequest lfcsosnRequest);
  
	/**
	 * Update lfcsosn.
	 *
	 * @param lfcsosnRequest the lfcsosn request
	 * @return the lfcsosn response
	 */
	public LfcsosnResponse updateLfcsosn(LfcsosnRequest lfcsosnRequest);
  
	/**  
	 * Delete lfcsosn.  
	 *     
	 * @param lfcsosnRequest the lfcsosn request 
	 * @return the lfcsosn response
	 */
	public LfcsosnResponse deleteLfcsosn(LfcsosnRequest lfcsosnRequest); 
   
	/**  
	 * Fetch all lfcsosn. 
	 *  
	 * @param inquiryLfcsosnRequest the inquiryLfcsosn request   
	 * @return the inquiry lfcsosn response 
	 */ 
	public InquiryLfcsosnResponse fetchAllLfcsosn(InquiryLfcsosnRequest inquiryLfcsosnRequest); 
   
	/** 
	 * Fetch lfcsosn by id. 
	 *   
	 * @param LfcsosnRequest the lfcsosn request 
	 * @return the lfcsosn response  
	 */ 
	public LfcsosnResponse fetchLfcsosnById(LfcsosnRequest lfcsosnRequest); 
 
} 
