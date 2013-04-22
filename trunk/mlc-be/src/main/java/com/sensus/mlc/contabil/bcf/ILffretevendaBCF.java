package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest;
import com.sensus.mlc.lffretevenda.model.response.LffretevendaResponse;
import com.sensus.mlc.lffretevenda.model.response.InquiryLffretevendaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILffretevendaBCF.
 *
 * @author Washington.Costa
 */
public interface ILffretevendaBCF 
{

	/** 
	 * Insert lffretevenda.
	 *
	 * @param lffretevendaRequest the lffretevenda request
	 * @return the lffretevenda response 
	 */
	public LffretevendaResponse insertLffretevenda(LffretevendaRequest lffretevendaRequest);
  
	/**
	 * Update lffretevenda.
	 *
	 * @param lffretevendaRequest the lffretevenda request
	 * @return the lffretevenda response
	 */
	public LffretevendaResponse updateLffretevenda(LffretevendaRequest lffretevendaRequest);
  
	/**  
	 * Delete lffretevenda.  
	 *     
	 * @param lffretevendaRequest the lffretevenda request 
	 * @return the lffretevenda response
	 */
	public LffretevendaResponse deleteLffretevenda(LffretevendaRequest lffretevendaRequest); 
   
	/**  
	 * Fetch all lffretevenda. 
	 *  
	 * @param inquiryLffretevendaRequest the inquiryLffretevenda request   
	 * @return the inquiry lffretevenda response 
	 */ 
	public InquiryLffretevendaResponse fetchAllLffretevenda(InquiryLffretevendaRequest inquiryLffretevendaRequest); 
   
	/** 
	 * Fetch lffretevenda by id. 
	 *   
	 * @param LffretevendaRequest the lffretevenda request 
	 * @return the lffretevenda response  
	 */ 
	public LffretevendaResponse fetchLffretevendaById(LffretevendaRequest lffretevendaRequest); 
 
} 
