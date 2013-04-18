package com.sensus.mlc.tabela.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tabela.model.Tabela; 
import com.sensus.mlc.tabela.model.request.TabelaRequest; 
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - Washington 
 * 
 */ 
public interface ITabelaBCL 
{   
    
	/**  
	 * Insert tabela. 
	 * 
	 * @param tabelaRequest the tabela request  
	 * @return the tabela response  
	 */ 
	public InternalResultsResponse<Tabela> insertTabela(TabelaRequest tabelaRequest);
  
	/** 
	 * Update tabela. 
	 *  
	 * @param tabelaRequest the tabela request 
	 * @return the tabela response 
	 */  
	public InternalResultsResponse<Tabela> updateTabela(TabelaRequest tabelaRequest); 
   
	/** 
	 * Delete tabela. 
	 *   
	 * @param tabelaRequest the tabela request 
	 * @return the tabela response  
	 */ 
	public InternalResponse deleteTabela(TabelaRequest tabelaRequest); 
  
	/** 
	 * Fetch all tabela.
	 *   
	 * @param inquirytabelaRequest the inquirytabela request 
	 * @return the inquiry tabela response 
	 */  
	public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquirytabelaRequest);
  
	/** 
	 * Fetch tabela by id. 
	 * 
	 * @param inquirytabelaRequest the inquirytabela request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Tabela> fetchTabelaById(TabelaRequest tabelaRequest); 
 
	/** 
	 * Fetch all tabela types. 
	 * 
	 * @param request the request 
	 * @return the tabela response 
	 */ 
	public InternalResultsResponse<Tabela> fetchAllTabelaTypes(InquiryTabelaRequest inquirytabelaRequest);  
  
	/** 
	 * Fetch all tabela filial. 
	 *  
	 * @param request the request 
	 * @return the tabela response 
	 */ 
	public InternalResultsResponse<Tabela> fetchAllTabelaFilial(TabelaRequest tabelaRequest);
 
} 
