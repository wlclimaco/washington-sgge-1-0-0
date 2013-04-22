package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest;
import com.sensus.mlc.lfitcompra.model.response.LfitcompraResponse;
import com.sensus.mlc.lfitcompra.model.response.InquiryLfitcompraResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfitcompraBCF.
 *
 * @author Washington.Costa
 */
public interface ILfitcompraBCF 
{

	/** 
	 * Insert lfitcompra.
	 *
	 * @param lfitcompraRequest the lfitcompra request
	 * @return the lfitcompra response 
	 */
	public LfitcompraResponse insertLfitcompra(LfitcompraRequest lfitcompraRequest);
  
	/**
	 * Update lfitcompra.
	 *
	 * @param lfitcompraRequest the lfitcompra request
	 * @return the lfitcompra response
	 */
	public LfitcompraResponse updateLfitcompra(LfitcompraRequest lfitcompraRequest);
  
	/**  
	 * Delete lfitcompra.  
	 *     
	 * @param lfitcompraRequest the lfitcompra request 
	 * @return the lfitcompra response
	 */
	public LfitcompraResponse deleteLfitcompra(LfitcompraRequest lfitcompraRequest); 
   
	/**  
	 * Fetch all lfitcompra. 
	 *  
	 * @param inquiryLfitcompraRequest the inquiryLfitcompra request   
	 * @return the inquiry lfitcompra response 
	 */ 
	public InquiryLfitcompraResponse fetchAllLfitcompra(InquiryLfitcompraRequest inquiryLfitcompraRequest); 
   
	/** 
	 * Fetch lfitcompra by id. 
	 *   
	 * @param LfitcompraRequest the lfitcompra request 
	 * @return the lfitcompra response  
	 */ 
	public LfitcompraResponse fetchLfitcompraById(LfitcompraRequest lfitcompraRequest); 
 
} 
