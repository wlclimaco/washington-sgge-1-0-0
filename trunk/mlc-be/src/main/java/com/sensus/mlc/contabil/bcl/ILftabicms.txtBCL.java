package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt; 
import com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest; 
import com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILftabicms.txtBCL 
{   
    
	/**  
	 * Insert lftabicms.txt. 
	 * 
	 * @param lftabicms.txtRequest the lftabicms.txt request  
	 * @return the lftabicms.txt response  
	 */ 
	public InternalResultsResponse<Lftabicms.txt> insertLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest);
  
	/** 
	 * Update lftabicms.txt. 
	 *  
	 * @param lftabicms.txtRequest the lftabicms.txt request 
	 * @return the lftabicms.txt response 
	 */  
	public InternalResultsResponse<Lftabicms.txt> updateLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest); 
   
	/** 
	 * Delete lftabicms.txt. 
	 *   
	 * @param lftabicms.txtRequest the lftabicms.txt request 
	 * @return the lftabicms.txt response  
	 */ 
	public InternalResponse deleteLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest); 
  
	/** 
	 * Fetch all lftabicms.txt.
	 *   
	 * @param inquirylftabicms.txtRequest the inquirylftabicms.txt request 
	 * @return the inquiry lftabicms.txt response 
	 */  
	public InternalResultsResponse<Lftabicms.txt> fetchAllLftabicms.txt(InquiryLftabicms.txtRequest inquirylftabicms.txtRequest);
  
	/** 
	 * Fetch lftabicms.txt by id. 
	 * 
	 * @param inquirylftabicms.txtRequest the inquirylftabicms.txt request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lftabicms.txt> fetchLftabicms.txtById(Lftabicms.txtRequest lftabicms.txtRequest); 
 
	/** 
	 * Fetch all lftabicms.txt types. 
	 * 
	 * @param request the request 
	 * @return the lftabicms.txt response 
	 */ 
	public InternalResultsResponse<Lftabicms.txt> fetchAllLftabicms.txtTypes(InquiryLftabicms.txtRequest inquirylftabicms.txtRequest);  
  
	/** 
	 * Fetch all lftabicms.txt filial. 
	 *  
	 * @param request the request 
	 * @return the lftabicms.txt response 
	 */ 
	public InternalResultsResponse<Lftabicms.txt> fetchAllLftabicms.txtFilial(Lftabicms.txtRequest lftabicms.txtRequest);
 
} 
