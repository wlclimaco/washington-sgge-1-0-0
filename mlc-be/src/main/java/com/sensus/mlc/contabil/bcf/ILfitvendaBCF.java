package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest;
import com.sensus.mlc.lfitvenda.model.response.LfitvendaResponse;
import com.sensus.mlc.lfitvenda.model.response.InquiryLfitvendaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfitvendaBCF.
 *
 * @author Washington.Costa
 */
public interface ILfitvendaBCF 
{

	/** 
	 * Insert lfitvenda.
	 *
	 * @param lfitvendaRequest the lfitvenda request
	 * @return the lfitvenda response 
	 */
	public LfitvendaResponse insertLfitvenda(LfitvendaRequest lfitvendaRequest);
  
	/**
	 * Update lfitvenda.
	 *
	 * @param lfitvendaRequest the lfitvenda request
	 * @return the lfitvenda response
	 */
	public LfitvendaResponse updateLfitvenda(LfitvendaRequest lfitvendaRequest);
  
	/**  
	 * Delete lfitvenda.  
	 *     
	 * @param lfitvendaRequest the lfitvenda request 
	 * @return the lfitvenda response
	 */
	public LfitvendaResponse deleteLfitvenda(LfitvendaRequest lfitvendaRequest); 
   
	/**  
	 * Fetch all lfitvenda. 
	 *  
	 * @param inquiryLfitvendaRequest the inquiryLfitvenda request   
	 * @return the inquiry lfitvenda response 
	 */ 
	public InquiryLfitvendaResponse fetchAllLfitvenda(InquiryLfitvendaRequest inquiryLfitvendaRequest); 
   
	/** 
	 * Fetch lfitvenda by id. 
	 *   
	 * @param LfitvendaRequest the lfitvenda request 
	 * @return the lfitvenda response  
	 */ 
	public LfitvendaResponse fetchLfitvendaById(LfitvendaRequest lfitvendaRequest); 
 
} 
