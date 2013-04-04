Memo1
package com.sensus.mlc.UnidMed.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest;
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest;
import com.sensus.mlc.UnidMed.model.response.UnidmedResponse;
import com.sensus.mlc.UnidMed.model.response.InquiryUnidmedResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IUnidmedBCF.
 *
 * @author Washington.Costa
 */
public interface IUnidmedBCF 
{

	/** 
	 * Insert UnidMed.
	 *
	 * @param UnidMedRequest the UnidMed request
	 * @return the UnidMed response 
	 */
	public UnidmedResponse insertUnidmed(UnidmedRequest UnidMedRequest);
  
	/**
	 * Update UnidMed.
	 *
	 * @param UnidMedRequest the UnidMed request
	 * @return the UnidMed response
	 */
	public UnidmedResponse updateUnidmed(UnidmedRequest UnidMedRequest);
  
	/**  
	 * Delete UnidMed.  
	 *     
	 * @param UnidMedRequest the UnidMed request 
	 * @return the UnidMed response
	 */
	public UnidmedResponse deleteUnidmed(UnidmedRequest UnidMedRequest); 
   
	/**  
	 * Fetch all UnidMed. 
	 *  
	 * @param inquiryUnidmedRequest the inquiryUnidmed request   
	 * @return the inquiry UnidMed response 
	 */ 
	public InquiryUnidmedResponse fetchAllUnidmed(InquiryUnidmedRequest inquiryUnidmedRequest); 
   
	/** 
	 * Fetch UnidMed by id. 
	 *   
	 * @param UnidmedRequest the UnidMed request 
	 * @return the UnidMed response  
	 */ 
	public UnidmedResponse fetchUnidmedById(UnidmedRequest UnidMedRequest); 
 
} 
