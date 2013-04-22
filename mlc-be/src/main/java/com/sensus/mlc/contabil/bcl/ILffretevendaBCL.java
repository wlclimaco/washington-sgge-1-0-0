package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lffretevenda.model.Lffretevenda; 
import com.sensus.mlc.lffretevenda.model.request.LffretevendaRequest; 
import com.sensus.mlc.lffretevenda.model.request.InquiryLffretevendaRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILffretevendaBCL 
{   
    
	/**  
	 * Insert lffretevenda. 
	 * 
	 * @param lffretevendaRequest the lffretevenda request  
	 * @return the lffretevenda response  
	 */ 
	public InternalResultsResponse<Lffretevenda> insertLffretevenda(LffretevendaRequest lffretevendaRequest);
  
	/** 
	 * Update lffretevenda. 
	 *  
	 * @param lffretevendaRequest the lffretevenda request 
	 * @return the lffretevenda response 
	 */  
	public InternalResultsResponse<Lffretevenda> updateLffretevenda(LffretevendaRequest lffretevendaRequest); 
   
	/** 
	 * Delete lffretevenda. 
	 *   
	 * @param lffretevendaRequest the lffretevenda request 
	 * @return the lffretevenda response  
	 */ 
	public InternalResponse deleteLffretevenda(LffretevendaRequest lffretevendaRequest); 
  
	/** 
	 * Fetch all lffretevenda.
	 *   
	 * @param inquirylffretevendaRequest the inquirylffretevenda request 
	 * @return the inquiry lffretevenda response 
	 */  
	public InternalResultsResponse<Lffretevenda> fetchAllLffretevenda(InquiryLffretevendaRequest inquirylffretevendaRequest);
  
	/** 
	 * Fetch lffretevenda by id. 
	 * 
	 * @param inquirylffretevendaRequest the inquirylffretevenda request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lffretevenda> fetchLffretevendaById(LffretevendaRequest lffretevendaRequest); 
 
	/** 
	 * Fetch all lffretevenda types. 
	 * 
	 * @param request the request 
	 * @return the lffretevenda response 
	 */ 
	public InternalResultsResponse<Lffretevenda> fetchAllLffretevendaTypes(InquiryLffretevendaRequest inquirylffretevendaRequest);  
  
	/** 
	 * Fetch all lffretevenda filial. 
	 *  
	 * @param request the request 
	 * @return the lffretevenda response 
	 */ 
	public InternalResultsResponse<Lffretevenda> fetchAllLffretevendaFilial(LffretevendaRequest lffretevendaRequest);
 
} 
