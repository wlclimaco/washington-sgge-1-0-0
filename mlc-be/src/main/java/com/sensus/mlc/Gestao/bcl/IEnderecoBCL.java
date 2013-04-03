Memo1
package com.sensus.mlc.Edit1.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.Edit1.model.Edit1; 
import com.sensus.mlc.Edit1.model.request.Edit1Request; 
import com.sensus.mlc.Edit1.model.request.InquiryEdit1Request; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IEdit1BCL 
{   
    
	/**  
	 * Insert Edit1. 
	 * 
	 * @param Edit1Request the Edit1 request  
	 * @return the Edit1 response  
	 */ 
	public InternalResultsResponse<Edit1> insertEdit1(Edit1Request Edit1Request);
  
	/** 
	 * Update Edit1. 
	 *  
	 * @param Edit1Request the Edit1 request 
	 * @return the Edit1 response 
	 */  
	public InternalResultsResponse<Edit1> updateEdit1(Edit1Request Edit1Request); 
   
	/** 
	 * Delete Edit1. 
	 *   
	 * @param Edit1Request the Edit1 request 
	 * @return the Edit1 response  
	 */ 
	public InternalResponse deleteEdit1(Edit1Request Edit1Request); 
  
	/** 
	 * Fetch all Edit1.
	 *   
	 * @param inquiryEdit1Request the inquiryEdit1 request 
	 * @return the inquiry Edit1 response 
	 */  
	public InternalResultsResponse<Edit1> fetchAllEdit1(InquiryEdit1Request inquiryEdit1Request);
  
	/** 
	 * Fetch Edit1 by id. 
	 * 
	 * @param inquiryEdit1Request the inquiryEdit1 request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Edit1> fetchEdit1ById(Edit1Request Edit1Request); 
 
	/** 
	 * Fetch all Edit1 types. 
	 * 
	 * @param request the request 
	 * @return the Edit1 response 
	 */ 
	public InternalResultsResponse<Edit1> fetchAllEdit1Types(InquiryEdit1Request inquiryEdit1Request);  
  
	/** 
	 * Fetch all Edit1 filial. 
	 *  
	 * @param request the request 
	 * @return the Edit1 response 
	 */ 
	public InternalResultsResponse<Edit1> fetchAllEdit1Filial(Edit1Request Edit1Request);
 
} 
