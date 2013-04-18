package com.sensus.mlc.tabela.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira; 
import com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest; 
import com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface IChaveestrangeiraBCL 
{   
    
	/**  
	 * Insert chaveestrangeira. 
	 * 
	 * @param chaveestrangeiraRequest the chaveestrangeira request  
	 * @return the chaveestrangeira response  
	 */ 
	public InternalResultsResponse<Chaveestrangeira> insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest);
  
	/** 
	 * Update chaveestrangeira. 
	 *  
	 * @param chaveestrangeiraRequest the chaveestrangeira request 
	 * @return the chaveestrangeira response 
	 */  
	public InternalResultsResponse<Chaveestrangeira> updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest); 
   
	/** 
	 * Delete chaveestrangeira. 
	 *   
	 * @param chaveestrangeiraRequest the chaveestrangeira request 
	 * @return the chaveestrangeira response  
	 */ 
	public InternalResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest); 
  
	/** 
	 * Fetch all chaveestrangeira.
	 *   
	 * @param inquirychaveestrangeiraRequest the inquirychaveestrangeira request 
	 * @return the inquiry chaveestrangeira response 
	 */  
	public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquirychaveestrangeiraRequest);
  
	/** 
	 * Fetch chaveestrangeira by id. 
	 * 
	 * @param inquirychaveestrangeiraRequest the inquirychaveestrangeira request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest); 
 
	/** 
	 * Fetch all chaveestrangeira types. 
	 * 
	 * @param request the request 
	 * @return the chaveestrangeira response 
	 */ 
	public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeiraTypes(InquiryChaveestrangeiraRequest inquirychaveestrangeiraRequest);  
  
	/** 
	 * Fetch all chaveestrangeira filial. 
	 *  
	 * @param request the request 
	 * @return the chaveestrangeira response 
	 */ 
	public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeiraFilial(ChaveestrangeiraRequest chaveestrangeiraRequest);
 
} 
