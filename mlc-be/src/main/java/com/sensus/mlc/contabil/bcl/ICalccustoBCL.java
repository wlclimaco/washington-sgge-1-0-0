package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.calccusto.model.Calccusto; 
import com.sensus.mlc.calccusto.model.request.CalccustoRequest; 
import com.sensus.mlc.calccusto.model.request.InquiryCalccustoRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ICalccustoBCL 
{   
    
	/**  
	 * Insert calccusto. 
	 * 
	 * @param calccustoRequest the calccusto request  
	 * @return the calccusto response  
	 */ 
	public InternalResultsResponse<Calccusto> insertCalccusto(CalccustoRequest calccustoRequest);
  
	/** 
	 * Update calccusto. 
	 *  
	 * @param calccustoRequest the calccusto request 
	 * @return the calccusto response 
	 */  
	public InternalResultsResponse<Calccusto> updateCalccusto(CalccustoRequest calccustoRequest); 
   
	/** 
	 * Delete calccusto. 
	 *   
	 * @param calccustoRequest the calccusto request 
	 * @return the calccusto response  
	 */ 
	public InternalResponse deleteCalccusto(CalccustoRequest calccustoRequest); 
  
	/** 
	 * Fetch all calccusto.
	 *   
	 * @param inquirycalccustoRequest the inquirycalccusto request 
	 * @return the inquiry calccusto response 
	 */  
	public InternalResultsResponse<Calccusto> fetchAllCalccusto(InquiryCalccustoRequest inquirycalccustoRequest);
  
	/** 
	 * Fetch calccusto by id. 
	 * 
	 * @param inquirycalccustoRequest the inquirycalccusto request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Calccusto> fetchCalccustoById(CalccustoRequest calccustoRequest); 
 
	/** 
	 * Fetch all calccusto types. 
	 * 
	 * @param request the request 
	 * @return the calccusto response 
	 */ 
	public InternalResultsResponse<Calccusto> fetchAllCalccustoTypes(InquiryCalccustoRequest inquirycalccustoRequest);  
  
	/** 
	 * Fetch all calccusto filial. 
	 *  
	 * @param request the request 
	 * @return the calccusto response 
	 */ 
	public InternalResultsResponse<Calccusto> fetchAllCalccustoFilial(CalccustoRequest calccustoRequest);
 
} 
