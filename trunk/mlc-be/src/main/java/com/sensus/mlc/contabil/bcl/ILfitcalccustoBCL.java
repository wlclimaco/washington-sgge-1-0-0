package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto; 
import com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest; 
import com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfitcalccustoBCL 
{   
    
	/**  
	 * Insert lfitcalccusto. 
	 * 
	 * @param lfitcalccustoRequest the lfitcalccusto request  
	 * @return the lfitcalccusto response  
	 */ 
	public InternalResultsResponse<Lfitcalccusto> insertLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest);
  
	/** 
	 * Update lfitcalccusto. 
	 *  
	 * @param lfitcalccustoRequest the lfitcalccusto request 
	 * @return the lfitcalccusto response 
	 */  
	public InternalResultsResponse<Lfitcalccusto> updateLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest); 
   
	/** 
	 * Delete lfitcalccusto. 
	 *   
	 * @param lfitcalccustoRequest the lfitcalccusto request 
	 * @return the lfitcalccusto response  
	 */ 
	public InternalResponse deleteLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest); 
  
	/** 
	 * Fetch all lfitcalccusto.
	 *   
	 * @param inquirylfitcalccustoRequest the inquirylfitcalccusto request 
	 * @return the inquiry lfitcalccusto response 
	 */  
	public InternalResultsResponse<Lfitcalccusto> fetchAllLfitcalccusto(InquiryLfitcalccustoRequest inquirylfitcalccustoRequest);
  
	/** 
	 * Fetch lfitcalccusto by id. 
	 * 
	 * @param inquirylfitcalccustoRequest the inquirylfitcalccusto request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfitcalccusto> fetchLfitcalccustoById(LfitcalccustoRequest lfitcalccustoRequest); 
 
	/** 
	 * Fetch all lfitcalccusto types. 
	 * 
	 * @param request the request 
	 * @return the lfitcalccusto response 
	 */ 
	public InternalResultsResponse<Lfitcalccusto> fetchAllLfitcalccustoTypes(InquiryLfitcalccustoRequest inquirylfitcalccustoRequest);  
  
	/** 
	 * Fetch all lfitcalccusto filial. 
	 *  
	 * @param request the request 
	 * @return the lfitcalccusto response 
	 */ 
	public InternalResultsResponse<Lfitcalccusto> fetchAllLfitcalccustoFilial(LfitcalccustoRequest lfitcalccustoRequest);
 
} 
