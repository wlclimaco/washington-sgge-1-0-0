package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lftipofisccli.model.Lftipofisccli; 
import com.sensus.mlc.lftipofisccli.model.request.LftipofisccliRequest; 
import com.sensus.mlc.lftipofisccli.model.request.InquiryLftipofisccliRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILftipofisccliBCL 
{   
    
	/**  
	 * Insert lftipofisccli. 
	 * 
	 * @param lftipofisccliRequest the lftipofisccli request  
	 * @return the lftipofisccli response  
	 */ 
	public InternalResultsResponse<Lftipofisccli> insertLftipofisccli(LftipofisccliRequest lftipofisccliRequest);
  
	/** 
	 * Update lftipofisccli. 
	 *  
	 * @param lftipofisccliRequest the lftipofisccli request 
	 * @return the lftipofisccli response 
	 */  
	public InternalResultsResponse<Lftipofisccli> updateLftipofisccli(LftipofisccliRequest lftipofisccliRequest); 
   
	/** 
	 * Delete lftipofisccli. 
	 *   
	 * @param lftipofisccliRequest the lftipofisccli request 
	 * @return the lftipofisccli response  
	 */ 
	public InternalResponse deleteLftipofisccli(LftipofisccliRequest lftipofisccliRequest); 
  
	/** 
	 * Fetch all lftipofisccli.
	 *   
	 * @param inquirylftipofisccliRequest the inquirylftipofisccli request 
	 * @return the inquiry lftipofisccli response 
	 */  
	public InternalResultsResponse<Lftipofisccli> fetchAllLftipofisccli(InquiryLftipofisccliRequest inquirylftipofisccliRequest);
  
	/** 
	 * Fetch lftipofisccli by id. 
	 * 
	 * @param inquirylftipofisccliRequest the inquirylftipofisccli request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lftipofisccli> fetchLftipofisccliById(LftipofisccliRequest lftipofisccliRequest); 
 
	/** 
	 * Fetch all lftipofisccli types. 
	 * 
	 * @param request the request 
	 * @return the lftipofisccli response 
	 */ 
	public InternalResultsResponse<Lftipofisccli> fetchAllLftipofisccliTypes(InquiryLftipofisccliRequest inquirylftipofisccliRequest);  
  
	/** 
	 * Fetch all lftipofisccli filial. 
	 *  
	 * @param request the request 
	 * @return the lftipofisccli response 
	 */ 
	public InternalResultsResponse<Lftipofisccli> fetchAllLftipofisccliFilial(LftipofisccliRequest lftipofisccliRequest);
 
} 
