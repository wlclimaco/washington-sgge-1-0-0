package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfsittrib.model.Lfsittrib; 
import com.sensus.mlc.lfsittrib.model.request.LfsittribRequest; 
import com.sensus.mlc.lfsittrib.model.request.InquiryLfsittribRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfsittribBCL 
{   
    
	/**  
	 * Insert lfsittrib. 
	 * 
	 * @param lfsittribRequest the lfsittrib request  
	 * @return the lfsittrib response  
	 */ 
	public InternalResultsResponse<Lfsittrib> insertLfsittrib(LfsittribRequest lfsittribRequest);
  
	/** 
	 * Update lfsittrib. 
	 *  
	 * @param lfsittribRequest the lfsittrib request 
	 * @return the lfsittrib response 
	 */  
	public InternalResultsResponse<Lfsittrib> updateLfsittrib(LfsittribRequest lfsittribRequest); 
   
	/** 
	 * Delete lfsittrib. 
	 *   
	 * @param lfsittribRequest the lfsittrib request 
	 * @return the lfsittrib response  
	 */ 
	public InternalResponse deleteLfsittrib(LfsittribRequest lfsittribRequest); 
  
	/** 
	 * Fetch all lfsittrib.
	 *   
	 * @param inquirylfsittribRequest the inquirylfsittrib request 
	 * @return the inquiry lfsittrib response 
	 */  
	public InternalResultsResponse<Lfsittrib> fetchAllLfsittrib(InquiryLfsittribRequest inquirylfsittribRequest);
  
	/** 
	 * Fetch lfsittrib by id. 
	 * 
	 * @param inquirylfsittribRequest the inquirylfsittrib request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfsittrib> fetchLfsittribById(LfsittribRequest lfsittribRequest); 
 
	/** 
	 * Fetch all lfsittrib types. 
	 * 
	 * @param request the request 
	 * @return the lfsittrib response 
	 */ 
	public InternalResultsResponse<Lfsittrib> fetchAllLfsittribTypes(InquiryLfsittribRequest inquirylfsittribRequest);  
  
	/** 
	 * Fetch all lfsittrib filial. 
	 *  
	 * @param request the request 
	 * @return the lfsittrib response 
	 */ 
	public InternalResultsResponse<Lfsittrib> fetchAllLfsittribFilial(LfsittribRequest lfsittribRequest);
 
} 
