package com.sensus.mlc.chaveprimaria.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria; 
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest; 
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface IChaveprimariaBCL 
{   
    
	/**  
	 * Insert chaveprimaria. 
	 * 
	 * @param chaveprimariaRequest the chaveprimaria request  
	 * @return the chaveprimaria response  
	 */ 
	public InternalResultsResponse<Chaveprimaria> insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest);
  
	/** 
	 * Update chaveprimaria. 
	 *  
	 * @param chaveprimariaRequest the chaveprimaria request 
	 * @return the chaveprimaria response 
	 */  
	public InternalResultsResponse<Chaveprimaria> updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest); 
   
	/** 
	 * Delete chaveprimaria. 
	 *   
	 * @param chaveprimariaRequest the chaveprimaria request 
	 * @return the chaveprimaria response  
	 */ 
	public InternalResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest); 
  
	/** 
	 * Fetch all chaveprimaria.
	 *   
	 * @param inquirychaveprimariaRequest the inquirychaveprimaria request 
	 * @return the inquiry chaveprimaria response 
	 */  
	public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimaria(InquiryChaveprimariaRequest inquirychaveprimariaRequest);
  
	/** 
	 * Fetch chaveprimaria by id. 
	 * 
	 * @param inquirychaveprimariaRequest the inquirychaveprimaria request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Chaveprimaria> fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest); 
 
	/** 
	 * Fetch all chaveprimaria types. 
	 * 
	 * @param request the request 
	 * @return the chaveprimaria response 
	 */ 
	public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimariaTypes(InquiryChaveprimariaRequest inquirychaveprimariaRequest);  
  
	/** 
	 * Fetch all chaveprimaria filial. 
	 *  
	 * @param request the request 
	 * @return the chaveprimaria response 
	 */ 
	public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimariaFilial(ChaveprimariaRequest chaveprimariaRequest);
 
} 
