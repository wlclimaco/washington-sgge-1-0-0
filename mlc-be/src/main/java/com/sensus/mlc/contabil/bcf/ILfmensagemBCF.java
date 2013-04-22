package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfmensagem.model.request.LfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.request.InquiryLfmensagemRequest;
import com.sensus.mlc.lfmensagem.model.response.LfmensagemResponse;
import com.sensus.mlc.lfmensagem.model.response.InquiryLfmensagemResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfmensagemBCF.
 *
 * @author Washington.Costa
 */
public interface ILfmensagemBCF 
{

	/** 
	 * Insert lfmensagem.
	 *
	 * @param lfmensagemRequest the lfmensagem request
	 * @return the lfmensagem response 
	 */
	public LfmensagemResponse insertLfmensagem(LfmensagemRequest lfmensagemRequest);
  
	/**
	 * Update lfmensagem.
	 *
	 * @param lfmensagemRequest the lfmensagem request
	 * @return the lfmensagem response
	 */
	public LfmensagemResponse updateLfmensagem(LfmensagemRequest lfmensagemRequest);
  
	/**  
	 * Delete lfmensagem.  
	 *     
	 * @param lfmensagemRequest the lfmensagem request 
	 * @return the lfmensagem response
	 */
	public LfmensagemResponse deleteLfmensagem(LfmensagemRequest lfmensagemRequest); 
   
	/**  
	 * Fetch all lfmensagem. 
	 *  
	 * @param inquiryLfmensagemRequest the inquiryLfmensagem request   
	 * @return the inquiry lfmensagem response 
	 */ 
	public InquiryLfmensagemResponse fetchAllLfmensagem(InquiryLfmensagemRequest inquiryLfmensagemRequest); 
   
	/** 
	 * Fetch lfmensagem by id. 
	 *   
	 * @param LfmensagemRequest the lfmensagem request 
	 * @return the lfmensagem response  
	 */ 
	public LfmensagemResponse fetchLfmensagemById(LfmensagemRequest lfmensagemRequest); 
 
} 
