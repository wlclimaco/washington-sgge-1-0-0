package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lftrattrib.model.request.LftrattribRequest;
import com.sensus.mlc.lftrattrib.model.request.InquiryLftrattribRequest;
import com.sensus.mlc.lftrattrib.model.response.LftrattribResponse;
import com.sensus.mlc.lftrattrib.model.response.InquiryLftrattribResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILftrattribBCF.
 *
 * @author Washington.Costa
 */
public interface ILftrattribBCF 
{

	/** 
	 * Insert lftrattrib.
	 *
	 * @param lftrattribRequest the lftrattrib request
	 * @return the lftrattrib response 
	 */
	public LftrattribResponse insertLftrattrib(LftrattribRequest lftrattribRequest);
  
	/**
	 * Update lftrattrib.
	 *
	 * @param lftrattribRequest the lftrattrib request
	 * @return the lftrattrib response
	 */
	public LftrattribResponse updateLftrattrib(LftrattribRequest lftrattribRequest);
  
	/**  
	 * Delete lftrattrib.  
	 *     
	 * @param lftrattribRequest the lftrattrib request 
	 * @return the lftrattrib response
	 */
	public LftrattribResponse deleteLftrattrib(LftrattribRequest lftrattribRequest); 
   
	/**  
	 * Fetch all lftrattrib. 
	 *  
	 * @param inquiryLftrattribRequest the inquiryLftrattrib request   
	 * @return the inquiry lftrattrib response 
	 */ 
	public InquiryLftrattribResponse fetchAllLftrattrib(InquiryLftrattribRequest inquiryLftrattribRequest); 
   
	/** 
	 * Fetch lftrattrib by id. 
	 *   
	 * @param LftrattribRequest the lftrattrib request 
	 * @return the lftrattrib response  
	 */ 
	public LftrattribResponse fetchLftrattribById(LftrattribRequest lftrattribRequest); 
 
} 
