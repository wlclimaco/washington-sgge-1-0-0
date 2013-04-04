Memo1
package com.sensus.mlc.UnidMed.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.UnidMed.model.Unidmed; 
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest; 
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IUnidmedBCL 
{   
    
	/**  
	 * Insert UnidMed. 
	 * 
	 * @param UnidMedRequest the UnidMed request  
	 * @return the UnidMed response  
	 */ 
	public InternalResultsResponse<Unidmed> insertUnidmed(UnidmedRequest UnidMedRequest);
  
	/** 
	 * Update UnidMed. 
	 *  
	 * @param UnidMedRequest the UnidMed request 
	 * @return the UnidMed response 
	 */  
	public InternalResultsResponse<Unidmed> updateUnidmed(UnidmedRequest UnidMedRequest); 
   
	/** 
	 * Delete UnidMed. 
	 *   
	 * @param UnidMedRequest the UnidMed request 
	 * @return the UnidMed response  
	 */ 
	public InternalResponse deleteUnidmed(UnidmedRequest UnidMedRequest); 
  
	/** 
	 * Fetch all UnidMed.
	 *   
	 * @param inquiryUnidMedRequest the inquiryUnidMed request 
	 * @return the inquiry UnidMed response 
	 */  
	public InternalResultsResponse<Unidmed> fetchAllUnidmed(InquiryUnidmedRequest inquiryUnidMedRequest);
  
	/** 
	 * Fetch UnidMed by id. 
	 * 
	 * @param inquiryUnidMedRequest the inquiryUnidMed request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Unidmed> fetchUnidmedById(UnidmedRequest UnidMedRequest); 
 
	/** 
	 * Fetch all UnidMed types. 
	 * 
	 * @param request the request 
	 * @return the UnidMed response 
	 */ 
	public InternalResultsResponse<Unidmed> fetchAllUnidmedTypes(InquiryUnidmedRequest inquiryUnidMedRequest);  
  
	/** 
	 * Fetch all UnidMed filial. 
	 *  
	 * @param request the request 
	 * @return the UnidMed response 
	 */ 
	public InternalResultsResponse<Unidmed> fetchAllUnidmedFilial(UnidmedRequest UnidMedRequest);
 
} 
package com.sensus.mlc.ClassTitulares.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.ClassTitulares.model.Classtitulares; 
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest; 
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IClasstitularesBCL 
{   
    
	/**  
	 * Insert ClassTitulares. 
	 * 
	 * @param ClassTitularesRequest the ClassTitulares request  
	 * @return the ClassTitulares response  
	 */ 
	public InternalResultsResponse<Classtitulares> insertClasstitulares(ClasstitularesRequest ClassTitularesRequest);
  
	/** 
	 * Update ClassTitulares. 
	 *  
	 * @param ClassTitularesRequest the ClassTitulares request 
	 * @return the ClassTitulares response 
	 */  
	public InternalResultsResponse<Classtitulares> updateClasstitulares(ClasstitularesRequest ClassTitularesRequest); 
   
	/** 
	 * Delete ClassTitulares. 
	 *   
	 * @param ClassTitularesRequest the ClassTitulares request 
	 * @return the ClassTitulares response  
	 */ 
	public InternalResponse deleteClasstitulares(ClasstitularesRequest ClassTitularesRequest); 
  
	/** 
	 * Fetch all ClassTitulares.
	 *   
	 * @param inquiryClassTitularesRequest the inquiryClassTitulares request 
	 * @return the inquiry ClassTitulares response 
	 */  
	public InternalResultsResponse<Classtitulares> fetchAllClasstitulares(InquiryClasstitularesRequest inquiryClassTitularesRequest);
  
	/** 
	 * Fetch ClassTitulares by id. 
	 * 
	 * @param inquiryClassTitularesRequest the inquiryClassTitulares request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Classtitulares> fetchClasstitularesById(ClasstitularesRequest ClassTitularesRequest); 
 
	/** 
	 * Fetch all ClassTitulares types. 
	 * 
	 * @param request the request 
	 * @return the ClassTitulares response 
	 */ 
	public InternalResultsResponse<Classtitulares> fetchAllClasstitularesTypes(InquiryClasstitularesRequest inquiryClassTitularesRequest);  
  
	/** 
	 * Fetch all ClassTitulares filial. 
	 *  
	 * @param request the request 
	 * @return the ClassTitulares response 
	 */ 
	public InternalResultsResponse<Classtitulares> fetchAllClasstitularesFilial(ClasstitularesRequest ClassTitularesRequest);
 
} 
