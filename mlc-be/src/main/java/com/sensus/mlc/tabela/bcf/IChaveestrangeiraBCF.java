package com.sensus.mlc.chaveestrangeira.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.response.ChaveestrangeiraResponse;
import com.sensus.mlc.chaveestrangeira.model.response.InquiryChaveestrangeiraResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IChaveestrangeiraBCF.
 *
 * @author Washington.Costa
 */
public interface IChaveestrangeiraBCF 
{

	/** 
	 * Insert chaveestrangeira.
	 *
	 * @param chaveestrangeiraRequest the chaveestrangeira request
	 * @return the chaveestrangeira response 
	 */
	public ChaveestrangeiraResponse insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest);
  
	/**
	 * Update chaveestrangeira.
	 *
	 * @param chaveestrangeiraRequest the chaveestrangeira request
	 * @return the chaveestrangeira response
	 */
	public ChaveestrangeiraResponse updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest);
  
	/**  
	 * Delete chaveestrangeira.  
	 *     
	 * @param chaveestrangeiraRequest the chaveestrangeira request 
	 * @return the chaveestrangeira response
	 */
	public ChaveestrangeiraResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest); 
   
	/**  
	 * Fetch all chaveestrangeira. 
	 *  
	 * @param inquiryChaveestrangeiraRequest the inquiryChaveestrangeira request   
	 * @return the inquiry chaveestrangeira response 
	 */ 
	public InquiryChaveestrangeiraResponse fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquiryChaveestrangeiraRequest); 
   
	/** 
	 * Fetch chaveestrangeira by id. 
	 *   
	 * @param ChaveestrangeiraRequest the chaveestrangeira request 
	 * @return the chaveestrangeira response  
	 */ 
	public ChaveestrangeiraResponse fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest); 
 
} 
