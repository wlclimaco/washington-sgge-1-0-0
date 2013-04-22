package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest;
import com.sensus.mlc.lfmodnota.model.response.LfmodnotaResponse;
import com.sensus.mlc.lfmodnota.model.response.InquiryLfmodnotaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfmodnotaBCF.
 *
 * @author Washington.Costa
 */
public interface ILfmodnotaBCF 
{

	/** 
	 * Insert lfmodnota.
	 *
	 * @param lfmodnotaRequest the lfmodnota request
	 * @return the lfmodnota response 
	 */
	public LfmodnotaResponse insertLfmodnota(LfmodnotaRequest lfmodnotaRequest);
  
	/**
	 * Update lfmodnota.
	 *
	 * @param lfmodnotaRequest the lfmodnota request
	 * @return the lfmodnota response
	 */
	public LfmodnotaResponse updateLfmodnota(LfmodnotaRequest lfmodnotaRequest);
  
	/**  
	 * Delete lfmodnota.  
	 *     
	 * @param lfmodnotaRequest the lfmodnota request 
	 * @return the lfmodnota response
	 */
	public LfmodnotaResponse deleteLfmodnota(LfmodnotaRequest lfmodnotaRequest); 
   
	/**  
	 * Fetch all lfmodnota. 
	 *  
	 * @param inquiryLfmodnotaRequest the inquiryLfmodnota request   
	 * @return the inquiry lfmodnota response 
	 */ 
	public InquiryLfmodnotaResponse fetchAllLfmodnota(InquiryLfmodnotaRequest inquiryLfmodnotaRequest); 
   
	/** 
	 * Fetch lfmodnota by id. 
	 *   
	 * @param LfmodnotaRequest the lfmodnota request 
	 * @return the lfmodnota response  
	 */ 
	public LfmodnotaResponse fetchLfmodnotaById(LfmodnotaRequest lfmodnotaRequest); 
 
} 
