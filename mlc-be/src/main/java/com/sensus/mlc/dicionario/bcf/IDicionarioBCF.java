package com.sensus.mlc.dicionario.bcf;

import com.sensus.mlc.dicionario.model.request.InquiryTabelaRequest;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.dicionario.model.request.TabelaRequest;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.response.InquiryTabelaResponse;
import com.sensus.mlc.dicionario.model.response.InquiryTelaResponse;
import com.sensus.mlc.dicionario.model.response.TabelaResponse;
import com.sensus.mlc.dicionario.model.response.TelaResponse;



// TODO: Auto-generated Javadoc
/**
 * The Interface IGroupBCF.
 */
public interface IDicionarioBCF
{

	/**
	 * Insert dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the group response
	 */
	public TelaResponse insertTela(TelaRequest dicionarioRequest);

	/**
	 * Insert dominio.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse insertDominio(TelaRequest dicionarioRequest);

	/**
	 * Insert validacao.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse insertValidacao(TelaRequest dicionarioRequest);

	/**
	 * Insert atributo.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse insertAtributo(TelaRequest dicionarioRequest);

	/**
	 * Insert tabela.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse insertTabela(TelaRequest dicionarioRequest);

	/**
	 * Insert query.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse insertQuery(TelaRequest dicionarioRequest);

	/**
	 * Update dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the group response
	 */
	public TelaResponse updateTela(TelaRequest dicionarioRequest);

	/**
	 * Update dominio.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse updateDominio(TelaRequest dicionarioRequest);

	/**
	 * Update validacao.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse updateValidacao(TelaRequest dicionarioRequest);

	/**
	 * Update atributo.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse updateAtributo(TelaRequest dicionarioRequest);

	/**
	 * Update tabela.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse updateTabela(TelaRequest dicionarioRequest);

	/**
	 * Update query.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse updateQuery(TelaRequest dicionarioRequest);

	/**
	 * Delete dicionario.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the Dicionario response
	 */
	public TelaResponse deleteTela(TelaRequest dicionarioRequest);

	/**
	 * Delete dominio.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse deleteDominio(TelaRequest dicionarioRequest);

	/**
	 * Delete validacao.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse deleteValidacao(TelaRequest dicionarioRequest);

	/**
	 * Delete atributo.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse deleteAtributo(TelaRequest dicionarioRequest);

	/**
	 * Delete tabela.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse deleteTabela(TelaRequest dicionarioRequest);

	/**
	 * Delete query.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the tela response
	 */
	public TelaResponse deleteQuery(TelaRequest dicionarioRequest);

	/**
	 * Fetch all dicionarios.
	 *
	 * @param inquiryDicionarioRequest the inquiry dicionario request
	 * @return the inquiry dicionario response
	 */
	public InquiryTelaResponse fetchAllTelas(InquiryTelaRequest inquiryDicionarioRequest);

	/**
	 * Fetch dicionario by id.
	 *
	 * @param dicionarioRequest the dicionario request
	 * @return the Dicionario response
	 */
	public TelaResponse fetchTelaById(TelaRequest dicionarioRequest);


	/**
	 * Fetch tabela by id.
	 *
	 * @param tabelaRequest the tabela request
	 * @return the tabela response
	 */
	public TabelaResponse fetchTabelaById(TabelaRequest tabelaRequest);

	/**
	 * Fetch all tabelas.
	 *
	 * @param inquiryTabelaRequest the inquiry tabela request
	 * @return the inquiry tabela response
	 */
	public InquiryTabelaResponse fetchAllTabelas(InquiryTabelaRequest inquiryTabelaRequest);




}
