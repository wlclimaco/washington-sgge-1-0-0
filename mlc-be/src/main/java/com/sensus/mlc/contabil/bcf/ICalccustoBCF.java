package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.calccusto.model.request.CalccustoRequest;
import com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest;
import com.sensus.mlc.calccusto.model.response.CalccustoResponse;
import com.sensus.mlc.calccusto.model.response.InquiryCalccustoResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ICalccustoBCF.
 *
 * @author Washington.Costa
 */
public interface ICalccustoBCF 
{

	/** 
	 * Insert calccusto.
	 *
	 * @param calccustoRequest the calccusto request
	 * @return the calccusto response 
	 */
	public CalccustoResponse insertCalccusto(CalccustoRequest calccustoRequest);
  
	/**
	 * Update calccusto.
	 *
	 * @param calccustoRequest the calccusto request
	 * @return the calccusto response
	 */
	public CalccustoResponse updateCalccusto(CalccustoRequest calccustoRequest);
  
	/**  
	 * Delete calccusto.  
	 *     
	 * @param calccustoRequest the calccusto request 
	 * @return the calccusto response
	 */
	public CalccustoResponse deleteCalccusto(CalccustoRequest calccustoRequest); 
   
	/**  
	 * Fetch all calccusto. 
	 *  
	 * @param inquiryCalccustoRequest the inquiryCalccusto request   
	 * @return the inquiry calccusto response 
	 */ 
	public InquiryCalccustoResponse fetchAllCalccusto(InquiryCalccustoRequest inquiryCalccustoRequest); 
   
	/** 
	 * Fetch calccusto by id. 
	 *   
	 * @param CalccustoRequest the calccusto request 
	 * @return the calccusto response  
	 */ 
	public CalccustoResponse fetchCalccustoById(CalccustoRequest calccustoRequest); 
 
} 
