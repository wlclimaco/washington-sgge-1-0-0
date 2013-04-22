package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfitnatoper.model.request.LfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.request.InquiryLfitnatoperRequest;
import com.sensus.mlc.lfitnatoper.model.response.LfitnatoperResponse;
import com.sensus.mlc.lfitnatoper.model.response.InquiryLfitnatoperResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfitnatoperBCF.
 *
 * @author Washington.Costa
 */
public interface ILfitnatoperBCF 
{

	/** 
	 * Insert lfitnatoper.
	 *
	 * @param lfitnatoperRequest the lfitnatoper request
	 * @return the lfitnatoper response 
	 */
	public LfitnatoperResponse insertLfitnatoper(LfitnatoperRequest lfitnatoperRequest);
  
	/**
	 * Update lfitnatoper.
	 *
	 * @param lfitnatoperRequest the lfitnatoper request
	 * @return the lfitnatoper response
	 */
	public LfitnatoperResponse updateLfitnatoper(LfitnatoperRequest lfitnatoperRequest);
  
	/**  
	 * Delete lfitnatoper.  
	 *     
	 * @param lfitnatoperRequest the lfitnatoper request 
	 * @return the lfitnatoper response
	 */
	public LfitnatoperResponse deleteLfitnatoper(LfitnatoperRequest lfitnatoperRequest); 
   
	/**  
	 * Fetch all lfitnatoper. 
	 *  
	 * @param inquiryLfitnatoperRequest the inquiryLfitnatoper request   
	 * @return the inquiry lfitnatoper response 
	 */ 
	public InquiryLfitnatoperResponse fetchAllLfitnatoper(InquiryLfitnatoperRequest inquiryLfitnatoperRequest); 
   
	/** 
	 * Fetch lfitnatoper by id. 
	 *   
	 * @param LfitnatoperRequest the lfitnatoper request 
	 * @return the lfitnatoper response  
	 */ 
	public LfitnatoperResponse fetchLfitnatoperById(LfitnatoperRequest lfitnatoperRequest); 
 
} 
