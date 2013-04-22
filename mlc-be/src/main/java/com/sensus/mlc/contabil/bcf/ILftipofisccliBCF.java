package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest;
import com.sensus.mlc.lftipofisccli.model.response.LftipofisccliResponse;
import com.sensus.mlc.lftipofisccli.model.response.InquiryLftipofisccliResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILftipofisccliBCF.
 *
 * @author Washington.Costa
 */
public interface ILftipofisccliBCF 
{

	/** 
	 * Insert lftipofisccli.
	 *
	 * @param lftipofisccliRequest the lftipofisccli request
	 * @return the lftipofisccli response 
	 */
	public LftipofisccliResponse insertLftipofisccli(LftipofisccliRequest lftipofisccliRequest);
  
	/**
	 * Update lftipofisccli.
	 *
	 * @param lftipofisccliRequest the lftipofisccli request
	 * @return the lftipofisccli response
	 */
	public LftipofisccliResponse updateLftipofisccli(LftipofisccliRequest lftipofisccliRequest);
  
	/**  
	 * Delete lftipofisccli.  
	 *     
	 * @param lftipofisccliRequest the lftipofisccli request 
	 * @return the lftipofisccli response
	 */
	public LftipofisccliResponse deleteLftipofisccli(LftipofisccliRequest lftipofisccliRequest); 
   
	/**  
	 * Fetch all lftipofisccli. 
	 *  
	 * @param inquiryLftipofisccliRequest the inquiryLftipofisccli request   
	 * @return the inquiry lftipofisccli response 
	 */ 
	public InquiryLftipofisccliResponse fetchAllLftipofisccli(InquiryLftipofisccliRequest inquiryLftipofisccliRequest); 
   
	/** 
	 * Fetch lftipofisccli by id. 
	 *   
	 * @param LftipofisccliRequest the lftipofisccli request 
	 * @return the lftipofisccli response  
	 */ 
	public LftipofisccliResponse fetchLftipofisccliById(LftipofisccliRequest lftipofisccliRequest); 
 
} 
