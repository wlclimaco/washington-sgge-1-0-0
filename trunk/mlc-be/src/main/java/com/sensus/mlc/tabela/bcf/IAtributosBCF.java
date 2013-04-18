package com.sensus.mlc.tabela.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.atributos.model.request.AtributosRequest;
import com.sensus.mlc.atributos.model.request.InquiryAtributosRequest;
import com.sensus.mlc.atributos.model.response.AtributosResponse;
import com.sensus.mlc.atributos.model.response.InquiryAtributosResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IAtributosBCF.
 *
 * @author Washington.Costa
 */
public interface IAtributosBCF 
{

	/** 
	 * Insert atributos.
	 *
	 * @param atributosRequest the atributos request
	 * @return the atributos response 
	 */
	public AtributosResponse insertAtributos(AtributosRequest atributosRequest);
  
	/**
	 * Update atributos.
	 *
	 * @param atributosRequest the atributos request
	 * @return the atributos response
	 */
	public AtributosResponse updateAtributos(AtributosRequest atributosRequest);
  
	/**  
	 * Delete atributos.  
	 *     
	 * @param atributosRequest the atributos request 
	 * @return the atributos response
	 */
	public AtributosResponse deleteAtributos(AtributosRequest atributosRequest); 
   
	/**  
	 * Fetch all atributos. 
	 *  
	 * @param inquiryAtributosRequest the inquiryAtributos request   
	 * @return the inquiry atributos response 
	 */ 
	public InquiryAtributosResponse fetchAllAtributos(InquiryAtributosRequest inquiryAtributosRequest); 
   
	/** 
	 * Fetch atributos by id. 
	 *   
	 * @param AtributosRequest the atributos request 
	 * @return the atributos response  
	 */ 
	public AtributosResponse fetchAtributosById(AtributosRequest atributosRequest); 
 
} 
