Memo1
package com.sensus.mlc.Filial.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.Filial.model.request.FilialRequest;
import com.sensus.mlc.Filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.Filial.model.response.FilialResponse;
import com.sensus.mlc.Filial.model.response.InquiryFilialResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IFilialBCF.
 *
 * @author Washington.Costa
 */
public interface IFilialBCF 
{

	/** 
	 * Insert Filial.
	 *
	 * @param FilialRequest the Filial request
	 * @return the Filial response 
	 */
	public FilialResponse insertFilial(FilialRequest FilialRequest);
  
	/**
	 * Update Filial.
	 *
	 * @param FilialRequest the Filial request
	 * @return the Filial response
	 */
	public FilialResponse updateFilial(FilialRequest FilialRequest);
  
	/**  
	 * Delete Filial.  
	 *     
	 * @param FilialRequest the Filial request 
	 * @return the Filial response
	 */
	public FilialResponse deleteFilial(FilialRequest FilialRequest); 
   
	/**  
	 * Fetch all Filial. 
	 *  
	 * @param inquiryFilialRequest the inquiryFilial request   
	 * @return the inquiry Filial response 
	 */ 
	public InquiryFilialResponse fetchAllFilial(InquiryFilialRequest inquiryFilialRequest); 
   
	/** 
	 * Fetch Filial by id. 
	 *   
	 * @param FilialRequest the Filial request 
	 * @return the Filial response  
	 */ 
	public FilialResponse fetchFilialById(FilialRequest FilialRequest); 
 
} 
