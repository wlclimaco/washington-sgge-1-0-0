package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitcompra.model.Lfitcompra; 
import com.sensus.mlc.lfitcompra.model.request.LfitcompraRequest; 
import com.sensus.mlc.lfitcompra.model.request.InquiryLfitcompraRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfitcompraBCL 
{   
    
	/**  
	 * Insert lfitcompra. 
	 * 
	 * @param lfitcompraRequest the lfitcompra request  
	 * @return the lfitcompra response  
	 */ 
	public InternalResultsResponse<Lfitcompra> insertLfitcompra(LfitcompraRequest lfitcompraRequest);
  
	/** 
	 * Update lfitcompra. 
	 *  
	 * @param lfitcompraRequest the lfitcompra request 
	 * @return the lfitcompra response 
	 */  
	public InternalResultsResponse<Lfitcompra> updateLfitcompra(LfitcompraRequest lfitcompraRequest); 
   
	/** 
	 * Delete lfitcompra. 
	 *   
	 * @param lfitcompraRequest the lfitcompra request 
	 * @return the lfitcompra response  
	 */ 
	public InternalResponse deleteLfitcompra(LfitcompraRequest lfitcompraRequest); 
  
	/** 
	 * Fetch all lfitcompra.
	 *   
	 * @param inquirylfitcompraRequest the inquirylfitcompra request 
	 * @return the inquiry lfitcompra response 
	 */  
	public InternalResultsResponse<Lfitcompra> fetchAllLfitcompra(InquiryLfitcompraRequest inquirylfitcompraRequest);
  
	/** 
	 * Fetch lfitcompra by id. 
	 * 
	 * @param inquirylfitcompraRequest the inquirylfitcompra request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfitcompra> fetchLfitcompraById(LfitcompraRequest lfitcompraRequest); 
 
	/** 
	 * Fetch all lfitcompra types. 
	 * 
	 * @param request the request 
	 * @return the lfitcompra response 
	 */ 
	public InternalResultsResponse<Lfitcompra> fetchAllLfitcompraTypes(InquiryLfitcompraRequest inquirylfitcompraRequest);  
  
	/** 
	 * Fetch all lfitcompra filial. 
	 *  
	 * @param request the request 
	 * @return the lfitcompra response 
	 */ 
	public InternalResultsResponse<Lfitcompra> fetchAllLfitcompraFilial(LfitcompraRequest lfitcompraRequest);
 
} 
