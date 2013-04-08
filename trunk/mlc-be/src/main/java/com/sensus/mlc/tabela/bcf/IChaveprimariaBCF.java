package com.sensus.mlc.chaveprimaria.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.response.ChaveprimariaResponse;
import com.sensus.mlc.chaveprimaria.model.response.InquiryChaveprimariaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IChaveprimariaBCF.
 *
 * @author Washington.Costa
 */
public interface IChaveprimariaBCF 
{

	/** 
	 * Insert chaveprimaria.
	 *
	 * @param chaveprimariaRequest the chaveprimaria request
	 * @return the chaveprimaria response 
	 */
	public ChaveprimariaResponse insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest);
  
	/**
	 * Update chaveprimaria.
	 *
	 * @param chaveprimariaRequest the chaveprimaria request
	 * @return the chaveprimaria response
	 */
	public ChaveprimariaResponse updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest);
  
	/**  
	 * Delete chaveprimaria.  
	 *     
	 * @param chaveprimariaRequest the chaveprimaria request 
	 * @return the chaveprimaria response
	 */
	public ChaveprimariaResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest); 
   
	/**  
	 * Fetch all chaveprimaria. 
	 *  
	 * @param inquiryChaveprimariaRequest the inquiryChaveprimaria request   
	 * @return the inquiry chaveprimaria response 
	 */ 
	public InquiryChaveprimariaResponse fetchAllChaveprimaria(InquiryChaveprimariaRequest inquiryChaveprimariaRequest); 
   
	/** 
	 * Fetch chaveprimaria by id. 
	 *   
	 * @param ChaveprimariaRequest the chaveprimaria request 
	 * @return the chaveprimaria response  
	 */ 
	public ChaveprimariaResponse fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest); 
 
} 
