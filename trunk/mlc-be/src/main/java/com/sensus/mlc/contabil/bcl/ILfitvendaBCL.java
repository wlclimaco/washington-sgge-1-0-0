package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitvenda.model.Lfitvenda; 
import com.sensus.mlc.lfitvenda.model.request.LfitvendaRequest; 
import com.sensus.mlc.lfitvenda.model.request.InquiryLfitvendaRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfitvendaBCL 
{   
    
	/**  
	 * Insert lfitvenda. 
	 * 
	 * @param lfitvendaRequest the lfitvenda request  
	 * @return the lfitvenda response  
	 */ 
	public InternalResultsResponse<Lfitvenda> insertLfitvenda(LfitvendaRequest lfitvendaRequest);
  
	/** 
	 * Update lfitvenda. 
	 *  
	 * @param lfitvendaRequest the lfitvenda request 
	 * @return the lfitvenda response 
	 */  
	public InternalResultsResponse<Lfitvenda> updateLfitvenda(LfitvendaRequest lfitvendaRequest); 
   
	/** 
	 * Delete lfitvenda. 
	 *   
	 * @param lfitvendaRequest the lfitvenda request 
	 * @return the lfitvenda response  
	 */ 
	public InternalResponse deleteLfitvenda(LfitvendaRequest lfitvendaRequest); 
  
	/** 
	 * Fetch all lfitvenda.
	 *   
	 * @param inquirylfitvendaRequest the inquirylfitvenda request 
	 * @return the inquiry lfitvenda response 
	 */  
	public InternalResultsResponse<Lfitvenda> fetchAllLfitvenda(InquiryLfitvendaRequest inquirylfitvendaRequest);
  
	/** 
	 * Fetch lfitvenda by id. 
	 * 
	 * @param inquirylfitvendaRequest the inquirylfitvenda request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfitvenda> fetchLfitvendaById(LfitvendaRequest lfitvendaRequest); 
 
	/** 
	 * Fetch all lfitvenda types. 
	 * 
	 * @param request the request 
	 * @return the lfitvenda response 
	 */ 
	public InternalResultsResponse<Lfitvenda> fetchAllLfitvendaTypes(InquiryLfitvendaRequest inquirylfitvendaRequest);  
  
	/** 
	 * Fetch all lfitvenda filial. 
	 *  
	 * @param request the request 
	 * @return the lfitvenda response 
	 */ 
	public InternalResultsResponse<Lfitvenda> fetchAllLfitvendaFilial(LfitvendaRequest lfitvendaRequest);
 
} 
