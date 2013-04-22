package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfserie.model.request.LfserieRequest;
import com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest;
import com.sensus.mlc.lfserie.model.response.LfserieResponse;
import com.sensus.mlc.lfserie.model.response.InquiryLfserieResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfserieBCF.
 *
 * @author Washington.Costa
 */
public interface ILfserieBCF 
{

	/** 
	 * Insert lfserie.
	 *
	 * @param lfserieRequest the lfserie request
	 * @return the lfserie response 
	 */
	public LfserieResponse insertLfserie(LfserieRequest lfserieRequest);
  
	/**
	 * Update lfserie.
	 *
	 * @param lfserieRequest the lfserie request
	 * @return the lfserie response
	 */
	public LfserieResponse updateLfserie(LfserieRequest lfserieRequest);
  
	/**  
	 * Delete lfserie.  
	 *     
	 * @param lfserieRequest the lfserie request 
	 * @return the lfserie response
	 */
	public LfserieResponse deleteLfserie(LfserieRequest lfserieRequest); 
   
	/**  
	 * Fetch all lfserie. 
	 *  
	 * @param inquiryLfserieRequest the inquiryLfserie request   
	 * @return the inquiry lfserie response 
	 */ 
	public InquiryLfserieResponse fetchAllLfserie(InquiryLfserieRequest inquiryLfserieRequest); 
   
	/** 
	 * Fetch lfserie by id. 
	 *   
	 * @param LfserieRequest the lfserie request 
	 * @return the lfserie response  
	 */ 
	public LfserieResponse fetchLfserieById(LfserieRequest lfserieRequest); 
 
} 
