package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest;
import com.sensus.mlc.lffretecompra.model.response.LffretecompraResponse;
import com.sensus.mlc.lffretecompra.model.response.InquiryLffretecompraResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILffretecompraBCF.
 *
 * @author Washington.Costa
 */
public interface ILffretecompraBCF 
{

	/** 
	 * Insert lffretecompra.
	 *
	 * @param lffretecompraRequest the lffretecompra request
	 * @return the lffretecompra response 
	 */
	public LffretecompraResponse insertLffretecompra(LffretecompraRequest lffretecompraRequest);
  
	/**
	 * Update lffretecompra.
	 *
	 * @param lffretecompraRequest the lffretecompra request
	 * @return the lffretecompra response
	 */
	public LffretecompraResponse updateLffretecompra(LffretecompraRequest lffretecompraRequest);
  
	/**  
	 * Delete lffretecompra.  
	 *     
	 * @param lffretecompraRequest the lffretecompra request 
	 * @return the lffretecompra response
	 */
	public LffretecompraResponse deleteLffretecompra(LffretecompraRequest lffretecompraRequest); 
   
	/**  
	 * Fetch all lffretecompra. 
	 *  
	 * @param inquiryLffretecompraRequest the inquiryLffretecompra request   
	 * @return the inquiry lffretecompra response 
	 */ 
	public InquiryLffretecompraResponse fetchAllLffretecompra(InquiryLffretecompraRequest inquiryLffretecompraRequest); 
   
	/** 
	 * Fetch lffretecompra by id. 
	 *   
	 * @param LffretecompraRequest the lffretecompra request 
	 * @return the lffretecompra response  
	 */ 
	public LffretecompraResponse fetchLffretecompraById(LffretecompraRequest lffretecompraRequest); 
 
} 
