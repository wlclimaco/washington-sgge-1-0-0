package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfmensagem.model.Lfmensagem; 
import com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest; 
import com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfmensagemBCL 
{   
    
	/**  
	 * Insert lfmensagem. 
	 * 
	 * @param lfmensagemRequest the lfmensagem request  
	 * @return the lfmensagem response  
	 */ 
	public InternalResultsResponse<Lfmensagem> insertLfmensagem(LfmensagemRequest lfmensagemRequest);
  
	/** 
	 * Update lfmensagem. 
	 *  
	 * @param lfmensagemRequest the lfmensagem request 
	 * @return the lfmensagem response 
	 */  
	public InternalResultsResponse<Lfmensagem> updateLfmensagem(LfmensagemRequest lfmensagemRequest); 
   
	/** 
	 * Delete lfmensagem. 
	 *   
	 * @param lfmensagemRequest the lfmensagem request 
	 * @return the lfmensagem response  
	 */ 
	public InternalResponse deleteLfmensagem(LfmensagemRequest lfmensagemRequest); 
  
	/** 
	 * Fetch all lfmensagem.
	 *   
	 * @param inquirylfmensagemRequest the inquirylfmensagem request 
	 * @return the inquiry lfmensagem response 
	 */  
	public InternalResultsResponse<Lfmensagem> fetchAllLfmensagem(InquiryLfmensagemRequest inquirylfmensagemRequest);
  
	/** 
	 * Fetch lfmensagem by id. 
	 * 
	 * @param inquirylfmensagemRequest the inquirylfmensagem request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfmensagem> fetchLfmensagemById(LfmensagemRequest lfmensagemRequest); 
 
	/** 
	 * Fetch all lfmensagem types. 
	 * 
	 * @param request the request 
	 * @return the lfmensagem response 
	 */ 
	public InternalResultsResponse<Lfmensagem> fetchAllLfmensagemTypes(InquiryLfmensagemRequest inquirylfmensagemRequest);  
  
	/** 
	 * Fetch all lfmensagem filial. 
	 *  
	 * @param request the request 
	 * @return the lfmensagem response 
	 */ 
	public InternalResultsResponse<Lfmensagem> fetchAllLfmensagemFilial(LfmensagemRequest lfmensagemRequest);
 
} 
