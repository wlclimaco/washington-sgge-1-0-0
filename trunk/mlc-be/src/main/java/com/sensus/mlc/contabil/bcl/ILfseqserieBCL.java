package com.sensus.mlc.contabil.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.lfseqserie.model.Lfseqserie; 
import com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest; 
import com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ILfseqserieBCL 
{   
    
	/**  
	 * Insert lfseqserie. 
	 * 
	 * @param lfseqserieRequest the lfseqserie request  
	 * @return the lfseqserie response  
	 */ 
	public InternalResultsResponse<Lfseqserie> insertLfseqserie(LfseqserieRequest lfseqserieRequest);
  
	/** 
	 * Update lfseqserie. 
	 *  
	 * @param lfseqserieRequest the lfseqserie request 
	 * @return the lfseqserie response 
	 */  
	public InternalResultsResponse<Lfseqserie> updateLfseqserie(LfseqserieRequest lfseqserieRequest); 
   
	/** 
	 * Delete lfseqserie. 
	 *   
	 * @param lfseqserieRequest the lfseqserie request 
	 * @return the lfseqserie response  
	 */ 
	public InternalResponse deleteLfseqserie(LfseqserieRequest lfseqserieRequest); 
  
	/** 
	 * Fetch all lfseqserie.
	 *   
	 * @param inquirylfseqserieRequest the inquirylfseqserie request 
	 * @return the inquiry lfseqserie response 
	 */  
	public InternalResultsResponse<Lfseqserie> fetchAllLfseqserie(InquiryLfseqserieRequest inquirylfseqserieRequest);
  
	/** 
	 * Fetch lfseqserie by id. 
	 * 
	 * @param inquirylfseqserieRequest the inquirylfseqserie request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Lfseqserie> fetchLfseqserieById(LfseqserieRequest lfseqserieRequest); 
 
	/** 
	 * Fetch all lfseqserie types. 
	 * 
	 * @param request the request 
	 * @return the lfseqserie response 
	 */ 
	public InternalResultsResponse<Lfseqserie> fetchAllLfseqserieTypes(InquiryLfseqserieRequest inquirylfseqserieRequest);  
  
	/** 
	 * Fetch all lfseqserie filial. 
	 *  
	 * @param request the request 
	 * @return the lfseqserie response 
	 */ 
	public InternalResultsResponse<Lfseqserie> fetchAllLfseqserieFilial(LfseqserieRequest lfseqserieRequest);
 
} 
