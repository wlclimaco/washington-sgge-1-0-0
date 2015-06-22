package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IClassificacaoDAC.
 */
public interface IClassificacaoDAC
{

	/**
	 * Update classificacao.
	 *
	 * @param classificacao the classificacao
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateClassificacao(Classificacao classificacao, InternalResultsResponse<?> response);

	/**
	 * Insert classificacao.
	 *
	 * @param classificacao the classificacao
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertClassificacao(Classificacao classificacao, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business classificacao.
	 *
	 * @param classificacao the classificacao
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteClassificacao(Classificacao classificacao, InternalResultsResponse<?> response);

	/**
	 * Fetch classificacao by id.
	 *
	 * @param id the id
	 * @return the internal results response< classificacao>
	 */
	public InternalResultsResponse<Classificacao> fetchClassificacaoById(Integer id);

}
