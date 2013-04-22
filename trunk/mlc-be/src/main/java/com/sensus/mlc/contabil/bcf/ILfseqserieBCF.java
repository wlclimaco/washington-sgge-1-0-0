package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lfseqserie.model.request.LfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.request.InquiryLfseqserieRequest;
import com.sensus.mlc.lfseqserie.model.response.LfseqserieResponse;
import com.sensus.mlc.lfseqserie.model.response.InquiryLfseqserieResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILfseqserieBCF.
 *
 * @author Washington.Costa
 */
public interface ILfseqserieBCF 
{

	/** 
	 * Insert lfseqserie.
	 *
	 * @param lfseqserieRequest the lfseqserie request
	 * @return the lfseqserie response 
	 */
	public LfseqserieResponse insertLfseqserie(LfseqserieRequest lfseqserieRequest);
  
	/**
	 * Update lfseqserie.
	 *
	 * @param lfseqserieRequest the lfseqserie request
	 * @return the lfseqserie response
	 */
	public LfseqserieResponse updateLfseqserie(LfseqserieRequest lfseqserieRequest);
  
	/**  
	 * Delete lfseqserie.  
	 *     
	 * @param lfseqserieRequest the lfseqserie request 
	 * @return the lfseqserie response
	 */
	public LfseqserieResponse deleteLfseqserie(LfseqserieRequest lfseqserieRequest); 
   
	/**  
	 * Fetch all lfseqserie. 
	 *  
	 * @param inquiryLfseqserieRequest the inquiryLfseqserie request   
	 * @return the inquiry lfseqserie response 
	 */ 
	public InquiryLfseqserieResponse fetchAllLfseqserie(InquiryLfseqserieRequest inquiryLfseqserieRequest); 
   
	/** 
	 * Fetch lfseqserie by id. 
	 *   
	 * @param LfseqserieRequest the lfseqserie request 
	 * @return the lfseqserie response  
	 */ 
	public LfseqserieResponse fetchLfseqserieById(LfseqserieRequest lfseqserieRequest); 
 
} 
