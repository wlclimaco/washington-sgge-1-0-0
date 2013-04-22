package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lftrattrib.model.Lftrattrib; 
import com.sensus.mlc.lftrattrib.model.request.LftrattribRequest; 
import com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILftrattribBCL 
{   
    
	/**  
	 * Insert lftrattrib. 
	 * 
	 * @param lftrattribRequest the lftrattrib request  
	 * @return the lftrattrib response  
	 */ 
	public InternalResultsResponse<Lftrattrib> insertLftrattrib(LftrattribRequest lftrattribRequest);
  
	/** 
	 * Update lftrattrib. 
	 *  
	 * @param lftrattribRequest the lftrattrib request 
	 * @return the lftrattrib response 
	 */  
	public InternalResultsResponse<Lftrattrib> updateLftrattrib(LftrattribRequest lftrattribRequest); 
   
	/** 
	 * Delete lftrattrib. 
	 *   
	 * @param lftrattribRequest the lftrattrib request 
	 * @return the lftrattrib response  
	 */ 
	public InternalResponse deleteLftrattrib(LftrattribRequest lftrattribRequest); 
  
	/** 
	 * Fetch all lftrattrib.
	 *   
	 * @param inquirylftrattribRequest the inquirylftrattrib request 
	 * @return the inquiry lftrattrib response 
	 */  
	public InternalResultsResponse<Lftrattrib> fetchAllLftrattrib(InquiryLftrattribRequest inquirylftrattribRequest);
  
	/** 
	 * Fetch lftrattrib by id. 
	 * 
	 * @param inquirylftrattribRequest the inquirylftrattrib request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lftrattrib> fetchLftrattribById(LftrattribRequest lftrattribRequest); 
 
	/** 
	 * Fetch all lftrattrib types. 
	 * 
	 * @param request the request 
	 * @return the lftrattrib response 
	 */ 
	public InternalResultsResponse<Lftrattrib> fetchAllLftrattribTypes(InquiryLftrattribRequest inquirylftrattribRequest);  
  
	/** 
	 * Fetch all lftrattrib filial. 
	 *  
	 * @param request the request 
	 * @return the lftrattrib response 
	 */ 
	public InternalResultsResponse<Lftrattrib> fetchAllLftrattribFilial(LftrattribRequest lftrattribRequest);
 
} 
