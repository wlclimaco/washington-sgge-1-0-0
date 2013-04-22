package com.sensus.mlc.contabil.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.lftabicms.txt.model.request.Lftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.request.InquiryLftabicms.txtRequest;
import com.sensus.mlc.lftabicms.txt.model.response.Lftabicms.txtResponse;
import com.sensus.mlc.lftabicms.txt.model.response.InquiryLftabicms.txtResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ILftabicms.txtBCF.
 *
 * @author Washington.Costa
 */
public interface ILftabicms.txtBCF 
{

	/** 
	 * Insert lftabicms.txt.
	 *
	 * @param lftabicms.txtRequest the lftabicms.txt request
	 * @return the lftabicms.txt response 
	 */
	public Lftabicms.txtResponse insertLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest);
  
	/**
	 * Update lftabicms.txt.
	 *
	 * @param lftabicms.txtRequest the lftabicms.txt request
	 * @return the lftabicms.txt response
	 */
	public Lftabicms.txtResponse updateLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest);
  
	/**  
	 * Delete lftabicms.txt.  
	 *     
	 * @param lftabicms.txtRequest the lftabicms.txt request 
	 * @return the lftabicms.txt response
	 */
	public Lftabicms.txtResponse deleteLftabicms.txt(Lftabicms.txtRequest lftabicms.txtRequest); 
   
	/**  
	 * Fetch all lftabicms.txt. 
	 *  
	 * @param inquiryLftabicms.txtRequest the inquiryLftabicms.txt request   
	 * @return the inquiry lftabicms.txt response 
	 */ 
	public InquiryLftabicms.txtResponse fetchAllLftabicms.txt(InquiryLftabicms.txtRequest inquiryLftabicms.txtRequest); 
   
	/** 
	 * Fetch lftabicms.txt by id. 
	 *   
	 * @param Lftabicms.txtRequest the lftabicms.txt request 
	 * @return the lftabicms.txt response  
	 */ 
	public Lftabicms.txtResponse fetchLftabicms.txtById(Lftabicms.txtRequest lftabicms.txtRequest); 
 
} 
