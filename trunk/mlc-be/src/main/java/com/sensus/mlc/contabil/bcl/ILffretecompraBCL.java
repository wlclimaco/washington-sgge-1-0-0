package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lffretecompra.model.Lffretecompra; 
import com.sensus.mlc.lffretecompra.model.request.LffretecompraRequest; 
import com.sensus.mlc.lffretecompra.model.request.InquiryLffretecompraRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILffretecompraBCL 
{   
    
	/**  
	 * Insert lffretecompra. 
	 * 
	 * @param lffretecompraRequest the lffretecompra request  
	 * @return the lffretecompra response  
	 */ 
	public InternalResultsResponse<Lffretecompra> insertLffretecompra(LffretecompraRequest lffretecompraRequest);
  
	/** 
	 * Update lffretecompra. 
	 *  
	 * @param lffretecompraRequest the lffretecompra request 
	 * @return the lffretecompra response 
	 */  
	public InternalResultsResponse<Lffretecompra> updateLffretecompra(LffretecompraRequest lffretecompraRequest); 
   
	/** 
	 * Delete lffretecompra. 
	 *   
	 * @param lffretecompraRequest the lffretecompra request 
	 * @return the lffretecompra response  
	 */ 
	public InternalResponse deleteLffretecompra(LffretecompraRequest lffretecompraRequest); 
  
	/** 
	 * Fetch all lffretecompra.
	 *   
	 * @param inquirylffretecompraRequest the inquirylffretecompra request 
	 * @return the inquiry lffretecompra response 
	 */  
	public InternalResultsResponse<Lffretecompra> fetchAllLffretecompra(InquiryLffretecompraRequest inquirylffretecompraRequest);
  
	/** 
	 * Fetch lffretecompra by id. 
	 * 
	 * @param inquirylffretecompraRequest the inquirylffretecompra request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lffretecompra> fetchLffretecompraById(LffretecompraRequest lffretecompraRequest); 
 
	/** 
	 * Fetch all lffretecompra types. 
	 * 
	 * @param request the request 
	 * @return the lffretecompra response 
	 */ 
	public InternalResultsResponse<Lffretecompra> fetchAllLffretecompraTypes(InquiryLffretecompraRequest inquirylffretecompraRequest);  
  
	/** 
	 * Fetch all lffretecompra filial. 
	 *  
	 * @param request the request 
	 * @return the lffretecompra response 
	 */ 
	public InternalResultsResponse<Lffretecompra> fetchAllLffretecompraFilial(LffretecompraRequest lffretecompraRequest);
 
} 
