package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfitcalccusto.model.request.LfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.request.InquiryLfitcalccustoRequest;
import com.sensus.mlc.lfitcalccusto.model.response.LfitcalccustoResponse;
import com.sensus.mlc.lfitcalccusto.model.response.InquiryLfitcalccustoResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfitcalccustoBCF.
 *
 * @author Washington.Costa
 */
public interface ILfitcalccustoBCF 
{

	/** 
	 * Insert lfitcalccusto.
	 *
	 * @param lfitcalccustoRequest the lfitcalccusto request
	 * @return the lfitcalccusto response 
	 */
	public LfitcalccustoResponse insertLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest);
  
	/**
	 * Update lfitcalccusto.
	 *
	 * @param lfitcalccustoRequest the lfitcalccusto request
	 * @return the lfitcalccusto response
	 */
	public LfitcalccustoResponse updateLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest);
  
	/**  
	 * Delete lfitcalccusto.  
	 *     
	 * @param lfitcalccustoRequest the lfitcalccusto request 
	 * @return the lfitcalccusto response
	 */
	public LfitcalccustoResponse deleteLfitcalccusto(LfitcalccustoRequest lfitcalccustoRequest); 
   
	/**  
	 * Fetch all lfitcalccusto. 
	 *  
	 * @param inquiryLfitcalccustoRequest the inquiryLfitcalccusto request   
	 * @return the inquiry lfitcalccusto response 
	 */ 
	public InquiryLfitcalccustoResponse fetchAllLfitcalccusto(InquiryLfitcalccustoRequest inquiryLfitcalccustoRequest); 
   
	/** 
	 * Fetch lfitcalccusto by id. 
	 *   
	 * @param LfitcalccustoRequest the lfitcalccusto request 
	 * @return the lfitcalccusto response  
	 */ 
	public LfitcalccustoResponse fetchLfitcalccustoById(LfitcalccustoRequest lfitcalccustoRequest); 
 
} 
