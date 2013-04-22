package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfsittrib.model.request.LfsittribRequest;
import com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest;
import com.sensus.mlc.lfsittrib.model.response.LfsittribResponse;
import com.sensus.mlc.lfsittrib.model.response.InquiryLfsittribResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfsittribBCF.
 *
 * @author Washington.Costa
 */
public interface ILfsittribBCF 
{

	/** 
	 * Insert lfsittrib.
	 *
	 * @param lfsittribRequest the lfsittrib request
	 * @return the lfsittrib response 
	 */
	public LfsittribResponse insertLfsittrib(LfsittribRequest lfsittribRequest);
  
	/**
	 * Update lfsittrib.
	 *
	 * @param lfsittribRequest the lfsittrib request
	 * @return the lfsittrib response
	 */
	public LfsittribResponse updateLfsittrib(LfsittribRequest lfsittribRequest);
  
	/**  
	 * Delete lfsittrib.  
	 *     
	 * @param lfsittribRequest the lfsittrib request 
	 * @return the lfsittrib response
	 */
	public LfsittribResponse deleteLfsittrib(LfsittribRequest lfsittribRequest); 
   
	/**  
	 * Fetch all lfsittrib. 
	 *  
	 * @param inquiryLfsittribRequest the inquiryLfsittrib request   
	 * @return the inquiry lfsittrib response 
	 */ 
	public InquiryLfsittribResponse fetchAllLfsittrib(InquiryLfsittribRequest inquiryLfsittribRequest); 
   
	/** 
	 * Fetch lfsittrib by id. 
	 *   
	 * @param LfsittribRequest the lfsittrib request 
	 * @return the lfsittrib response  
	 */ 
	public LfsittribResponse fetchLfsittribById(LfsittribRequest lfsittribRequest); 
 
} 
