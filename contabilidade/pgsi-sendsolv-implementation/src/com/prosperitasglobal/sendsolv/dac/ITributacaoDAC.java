package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface ITributacaoDAC.
 */
public interface ITributacaoDAC
{

	/**
	 * Update tributacao.
	 *
	 * @param tributacao the tributacao
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTributacao(Tributacao tributacao, InternalResultsResponse<?> response);

	/**
	 * Insert tributacao.
	 *
	 * @param tributacao the tributacao
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTributacao(Tributacao tributacao, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business tributacao.
	 *
	 * @param tributacao the tributacao
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteTributacao(Tributacao tributacao, InternalResultsResponse<?> response);

	/**
	 * Fetch tributacao by id.
	 *
	 * @param id the id
	 * @return the internal results response< tributacao>
	 */
	public InternalResultsResponse<Tributacao> fetchTributacaoById(Integer id);

}
