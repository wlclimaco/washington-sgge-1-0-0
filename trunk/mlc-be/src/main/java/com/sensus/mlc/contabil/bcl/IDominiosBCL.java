package com.sensus.mlc.tabela.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.dominios.model.Dominios; 
import com.sensus.mlc.dominios.model.request.DominiosRequest; 
import com.sensus.mlc.dominios.model.request.InquiryDominiosRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface IDominiosBCL 
{   
    
	/**  
	 * Insert dominios. 
	 * 
	 * @param dominiosRequest the dominios request  
	 * @return the dominios response  
	 */ 
	public InternalResultsResponse<Dominios> insertDominios(DominiosRequest dominiosRequest);
  
	/** 
	 * Update dominios. 
	 *  
	 * @param dominiosRequest the dominios request 
	 * @return the dominios response 
	 */  
	public InternalResultsResponse<Dominios> updateDominios(DominiosRequest dominiosRequest); 
   
	/** 
	 * Delete dominios. 
	 *   
	 * @param dominiosRequest the dominios request 
	 * @return the dominios response  
	 */ 
	public InternalResponse deleteDominios(DominiosRequest dominiosRequest); 
  
	/** 
	 * Fetch all dominios.
	 *   
	 * @param inquirydominiosRequest the inquirydominios request 
	 * @return the inquiry dominios response 
	 */  
	public InternalResultsResponse<Dominios> fetchAllDominios(InquiryDominiosRequest inquirydominiosRequest);
  
	/** 
	 * Fetch dominios by id. 
	 * 
	 * @param inquirydominiosRequest the inquirydominios request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Dominios> fetchDominiosById(DominiosRequest dominiosRequest); 
 
	/** 
	 * Fetch all dominios types. 
	 * 
	 * @param request the request 
	 * @return the dominios response 
	 */ 
	public InternalResultsResponse<Dominios> fetchAllDominiosTypes(InquiryDominiosRequest inquirydominiosRequest);  
  
	/** 
	 * Fetch all dominios filial. 
	 *  
	 * @param request the request 
	 * @return the dominios response 
	 */ 
	public InternalResultsResponse<Dominios> fetchAllDominiosFilial(DominiosRequest dominiosRequest);
 
} 
