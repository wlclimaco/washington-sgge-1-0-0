package com.sensus.mlc.tabela.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.response.TabelaResponse;
import com.sensus.mlc.tabela.model.response.InquiryTabelaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ITabelaBCF.
 *
 * @author Washington.Costa
 */
public interface ITabelaBCF 
{

	/** 
	 * Insert tabela.
	 *
	 * @param tabelaRequest the tabela request
	 * @return the tabela response 
	 */
	public TabelaResponse insertTabela(TabelaRequest tabelaRequest);
  
	/**
	 * Update tabela.
	 *
	 * @param tabelaRequest the tabela request
	 * @return the tabela response
	 */
	public TabelaResponse updateTabela(TabelaRequest tabelaRequest);
  
	/**  
	 * Delete tabela.  
	 *     
	 * @param tabelaRequest the tabela request 
	 * @return the tabela response
	 */
	public TabelaResponse deleteTabela(TabelaRequest tabelaRequest); 
   
	/**  
	 * Fetch all tabela. 
	 *  
	 * @param inquiryTabelaRequest the inquiryTabela request   
	 * @return the inquiry tabela response 
	 */ 
	public InquiryTabelaResponse fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest); 
   
	/** 
	 * Fetch tabela by id. 
	 *   
	 * @param TabelaRequest the tabela request 
	 * @return the tabela response  
	 */ 
	public TabelaResponse fetchTabelaById(TabelaRequest tabelaRequest); 
 
} 
