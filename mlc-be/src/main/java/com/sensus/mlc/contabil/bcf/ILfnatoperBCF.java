package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfnatoper.model.request.LfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.request.InquiryLfnatoperRequest;
import com.sensus.mlc.lfnatoper.model.response.LfnatoperResponse;
import com.sensus.mlc.lfnatoper.model.response.InquiryLfnatoperResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfnatoperBCF.
 *
 * @author Washington.Costa
 */
public interface ILfnatoperBCF 
{

	/** 
	 * Insert lfnatoper.
	 *
	 * @param lfnatoperRequest the lfnatoper request
	 * @return the lfnatoper response 
	 */
	public LfnatoperResponse insertLfnatoper(LfnatoperRequest lfnatoperRequest);
  
	/**
	 * Update lfnatoper.
	 *
	 * @param lfnatoperRequest the lfnatoper request
	 * @return the lfnatoper response
	 */
	public LfnatoperResponse updateLfnatoper(LfnatoperRequest lfnatoperRequest);
  
	/**  
	 * Delete lfnatoper.  
	 *     
	 * @param lfnatoperRequest the lfnatoper request 
	 * @return the lfnatoper response
	 */
	public LfnatoperResponse deleteLfnatoper(LfnatoperRequest lfnatoperRequest); 
   
	/**  
	 * Fetch all lfnatoper. 
	 *  
	 * @param inquiryLfnatoperRequest the inquiryLfnatoper request   
	 * @return the inquiry lfnatoper response 
	 */ 
	public InquiryLfnatoperResponse fetchAllLfnatoper(InquiryLfnatoperRequest inquiryLfnatoperRequest); 
   
	/** 
	 * Fetch lfnatoper by id. 
	 *   
	 * @param LfnatoperRequest the lfnatoper request 
	 * @return the lfnatoper response  
	 */ 
	public LfnatoperResponse fetchLfnatoperById(LfnatoperRequest lfnatoperRequest); 
 
} 
