package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfmodnota.model.Lfmodnota; 
import com.sensus.mlc.lfmodnota.model.request.LfmodnotaRequest; 
import com.sensus.mlc.lfmodnota.model.request.InquiryLfmodnotaRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfmodnotaBCL 
{   
    
	/**  
	 * Insert lfmodnota. 
	 * 
	 * @param lfmodnotaRequest the lfmodnota request  
	 * @return the lfmodnota response  
	 */ 
	public InternalResultsResponse<Lfmodnota> insertLfmodnota(LfmodnotaRequest lfmodnotaRequest);
  
	/** 
	 * Update lfmodnota. 
	 *  
	 * @param lfmodnotaRequest the lfmodnota request 
	 * @return the lfmodnota response 
	 */  
	public InternalResultsResponse<Lfmodnota> updateLfmodnota(LfmodnotaRequest lfmodnotaRequest); 
   
	/** 
	 * Delete lfmodnota. 
	 *   
	 * @param lfmodnotaRequest the lfmodnota request 
	 * @return the lfmodnota response  
	 */ 
	public InternalResponse deleteLfmodnota(LfmodnotaRequest lfmodnotaRequest); 
  
	/** 
	 * Fetch all lfmodnota.
	 *   
	 * @param inquirylfmodnotaRequest the inquirylfmodnota request 
	 * @return the inquiry lfmodnota response 
	 */  
	public InternalResultsResponse<Lfmodnota> fetchAllLfmodnota(InquiryLfmodnotaRequest inquirylfmodnotaRequest);
  
	/** 
	 * Fetch lfmodnota by id. 
	 * 
	 * @param inquirylfmodnotaRequest the inquirylfmodnota request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfmodnota> fetchLfmodnotaById(LfmodnotaRequest lfmodnotaRequest); 
 
	/** 
	 * Fetch all lfmodnota types. 
	 * 
	 * @param request the request 
	 * @return the lfmodnota response 
	 */ 
	public InternalResultsResponse<Lfmodnota> fetchAllLfmodnotaTypes(InquiryLfmodnotaRequest inquirylfmodnotaRequest);  
  
	/** 
	 * Fetch all lfmodnota filial. 
	 *  
	 * @param request the request 
	 * @return the lfmodnota response 
	 */ 
	public InternalResultsResponse<Lfmodnota> fetchAllLfmodnotaFilial(LfmodnotaRequest lfmodnotaRequest);
 
} 
