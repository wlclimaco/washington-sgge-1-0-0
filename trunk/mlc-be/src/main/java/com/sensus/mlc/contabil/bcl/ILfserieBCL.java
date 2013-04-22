package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfserie.model.Lfserie; 
import com.sensus.mlc.lfserie.model.request.LfserieRequest; 
import com.sensus.mlc.lfserie.model.request.InquiryLfserieRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfserieBCL 
{   
    
	/**  
	 * Insert lfserie. 
	 * 
	 * @param lfserieRequest the lfserie request  
	 * @return the lfserie response  
	 */ 
	public InternalResultsResponse<Lfserie> insertLfserie(LfserieRequest lfserieRequest);
  
	/** 
	 * Update lfserie. 
	 *  
	 * @param lfserieRequest the lfserie request 
	 * @return the lfserie response 
	 */  
	public InternalResultsResponse<Lfserie> updateLfserie(LfserieRequest lfserieRequest); 
   
	/** 
	 * Delete lfserie. 
	 *   
	 * @param lfserieRequest the lfserie request 
	 * @return the lfserie response  
	 */ 
	public InternalResponse deleteLfserie(LfserieRequest lfserieRequest); 
  
	/** 
	 * Fetch all lfserie.
	 *   
	 * @param inquirylfserieRequest the inquirylfserie request 
	 * @return the inquiry lfserie response 
	 */  
	public InternalResultsResponse<Lfserie> fetchAllLfserie(InquiryLfserieRequest inquirylfserieRequest);
  
	/** 
	 * Fetch lfserie by id. 
	 * 
	 * @param inquirylfserieRequest the inquirylfserie request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfserie> fetchLfserieById(LfserieRequest lfserieRequest); 
 
	/** 
	 * Fetch all lfserie types. 
	 * 
	 * @param request the request 
	 * @return the lfserie response 
	 */ 
	public InternalResultsResponse<Lfserie> fetchAllLfserieTypes(InquiryLfserieRequest inquirylfserieRequest);  
  
	/** 
	 * Fetch all lfserie filial. 
	 *  
	 * @param request the request 
	 * @return the lfserie response 
	 */ 
	public InternalResultsResponse<Lfserie> fetchAllLfserieFilial(LfserieRequest lfserieRequest);
 
} 
