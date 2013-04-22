package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfcsosn.model.Lfcsosn; 
import com.sensus.mlc.lfcsosn.model.request.LfcsosnRequest; 
import com.sensus.mlc.lfcsosn.model.request.InquiryLfcsosnRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfcsosnBCL 
{   
    
	/**  
	 * Insert lfcsosn. 
	 * 
	 * @param lfcsosnRequest the lfcsosn request  
	 * @return the lfcsosn response  
	 */ 
	public InternalResultsResponse<Lfcsosn> insertLfcsosn(LfcsosnRequest lfcsosnRequest);
  
	/** 
	 * Update lfcsosn. 
	 *  
	 * @param lfcsosnRequest the lfcsosn request 
	 * @return the lfcsosn response 
	 */  
	public InternalResultsResponse<Lfcsosn> updateLfcsosn(LfcsosnRequest lfcsosnRequest); 
   
	/** 
	 * Delete lfcsosn. 
	 *   
	 * @param lfcsosnRequest the lfcsosn request 
	 * @return the lfcsosn response  
	 */ 
	public InternalResponse deleteLfcsosn(LfcsosnRequest lfcsosnRequest); 
  
	/** 
	 * Fetch all lfcsosn.
	 *   
	 * @param inquirylfcsosnRequest the inquirylfcsosn request 
	 * @return the inquiry lfcsosn response 
	 */  
	public InternalResultsResponse<Lfcsosn> fetchAllLfcsosn(InquiryLfcsosnRequest inquirylfcsosnRequest);
  
	/** 
	 * Fetch lfcsosn by id. 
	 * 
	 * @param inquirylfcsosnRequest the inquirylfcsosn request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfcsosn> fetchLfcsosnById(LfcsosnRequest lfcsosnRequest); 
 
	/** 
	 * Fetch all lfcsosn types. 
	 * 
	 * @param request the request 
	 * @return the lfcsosn response 
	 */ 
	public InternalResultsResponse<Lfcsosn> fetchAllLfcsosnTypes(InquiryLfcsosnRequest inquirylfcsosnRequest);  
  
	/** 
	 * Fetch all lfcsosn filial. 
	 *  
	 * @param request the request 
	 * @return the lfcsosn response 
	 */ 
	public InternalResultsResponse<Lfcsosn> fetchAllLfcsosnFilial(LfcsosnRequest lfcsosnRequest);
 
} 
