Memo1
package com.sensus.mlc.Edit1.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.Edit1.model.request.Edit1Request;
import com.sensus.mlc.Edit1.model.request.InquiryEdit1Request;
import com.sensus.mlc.Edit1.model.response.Edit1Response;
import com.sensus.mlc.Edit1.model.response.InquiryEdit1Response;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IEdit1BCF.
 *
 * @author Washington.Costa
 */
public interface IEdit1BCF 
{

	/** 
	 * Insert Edit1.
	 *
	 * @param Edit1Request the Edit1 request
	 * @return the Edit1 response 
	 */
	public Edit1Response insertEdit1(Edit1Request Edit1Request);
  
	/**
	 * Update Edit1.
	 *
	 * @param Edit1Request the Edit1 request
	 * @return the Edit1 response
	 */
	public Edit1Response updateEdit1(Edit1Request Edit1Request);
  
	/**  
	 * Delete Edit1.  
	 *     
	 * @param Edit1Request the Edit1 request 
	 * @return the Edit1 response
	 */
	public Edit1Response deleteEdit1(Edit1Request Edit1Request); 
   
	/**  
	 * Fetch all Edit1. 
	 *  
	 * @param inquiryEdit1Request the inquiryEdit1 request   
	 * @return the inquiry Edit1 response 
	 */ 
	public InquiryEdit1Response fetchAllEdit1(InquiryEdit1Request inquiryEdit1Request); 
   
	/** 
	 * Fetch Edit1 by id. 
	 *   
	 * @param Edit1Request the Edit1 request 
	 * @return the Edit1 response  
	 */ 
	public Edit1Response fetchEdit1ById(Edit1Request Edit1Request); 
 
} 
