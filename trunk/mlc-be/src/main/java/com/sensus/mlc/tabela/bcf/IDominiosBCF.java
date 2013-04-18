package com.sensus.mlc.tabela.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.dominios.model.request.DominiosRequest;
import com.sensus.mlc.dominios.model.request.InquiryDominiosRequest;
import com.sensus.mlc.dominios.model.response.DominiosResponse;
import com.sensus.mlc.dominios.model.response.InquiryDominiosResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IDominiosBCF.
 *
 * @author Washington.Costa
 */
public interface IDominiosBCF 
{

	/** 
	 * Insert dominios.
	 *
	 * @param dominiosRequest the dominios request
	 * @return the dominios response 
	 */
	public DominiosResponse insertDominios(DominiosRequest dominiosRequest);
  
	/**
	 * Update dominios.
	 *
	 * @param dominiosRequest the dominios request
	 * @return the dominios response
	 */
	public DominiosResponse updateDominios(DominiosRequest dominiosRequest);
  
	/**  
	 * Delete dominios.  
	 *     
	 * @param dominiosRequest the dominios request 
	 * @return the dominios response
	 */
	public DominiosResponse deleteDominios(DominiosRequest dominiosRequest); 
   
	/**  
	 * Fetch all dominios. 
	 *  
	 * @param inquiryDominiosRequest the inquiryDominios request   
	 * @return the inquiry dominios response 
	 */ 
	public InquiryDominiosResponse fetchAllDominios(InquiryDominiosRequest inquiryDominiosRequest); 
   
	/** 
	 * Fetch dominios by id. 
	 *   
	 * @param DominiosRequest the dominios request 
	 * @return the dominios response  
	 */ 
	public DominiosResponse fetchDominiosById(DominiosRequest dominiosRequest); 
 
} 
