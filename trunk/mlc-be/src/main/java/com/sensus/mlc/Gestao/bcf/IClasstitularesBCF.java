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
package com.sensus.mlc.ClassTitulares.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest;
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest;
import com.sensus.mlc.ClassTitulares.model.response.ClasstitularesResponse;
import com.sensus.mlc.ClassTitulares.model.response.InquiryClasstitularesResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IClasstitularesBCF.
 *
 * @author Washington.Costa
 */
public interface IClasstitularesBCF 
{

	/** 
	 * Insert ClassTitulares.
	 *
	 * @param ClassTitularesRequest the ClassTitulares request
	 * @return the ClassTitulares response 
	 */
	public ClasstitularesResponse insertClasstitulares(ClasstitularesRequest ClassTitularesRequest);
  
	/**
	 * Update ClassTitulares.
	 *
	 * @param ClassTitularesRequest the ClassTitulares request
	 * @return the ClassTitulares response
	 */
	public ClasstitularesResponse updateClasstitulares(ClasstitularesRequest ClassTitularesRequest);
  
	/**  
	 * Delete ClassTitulares.  
	 *     
	 * @param ClassTitularesRequest the ClassTitulares request 
	 * @return the ClassTitulares response
	 */
	public ClasstitularesResponse deleteClasstitulares(ClasstitularesRequest ClassTitularesRequest); 
   
	/**  
	 * Fetch all ClassTitulares. 
	 *  
	 * @param inquiryClasstitularesRequest the inquiryClasstitulares request   
	 * @return the inquiry ClassTitulares response 
	 */ 
	public InquiryClasstitularesResponse fetchAllClasstitulares(InquiryClasstitularesRequest inquiryClasstitularesRequest); 
   
	/** 
	 * Fetch ClassTitulares by id. 
	 *   
	 * @param ClasstitularesRequest the ClassTitulares request 
	 * @return the ClassTitulares response  
	 */ 
	public ClasstitularesResponse fetchClasstitularesById(ClasstitularesRequest ClassTitularesRequest); 
 
} 
