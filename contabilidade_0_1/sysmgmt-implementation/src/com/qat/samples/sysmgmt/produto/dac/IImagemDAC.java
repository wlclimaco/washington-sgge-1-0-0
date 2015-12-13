package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.Imagem;

/**
 * The Interface IImagemDAC.
 */
public interface IImagemDAC
{

	/**
	 * Update imagem.
	 * 
	 * @param imagem the imagem
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateImagem(Imagem imagem, InternalResultsResponse<?> response);

	/**
	 * Insert imagem.
	 * 
	 * @param imagem the imagem
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertImagem(Imagem imagem, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business imagem.
	 * 
	 * @param imagem the imagem
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteImagem(Imagem imagem, InternalResultsResponse<?> response);

	/**
	 * Fetch imagem by id.
	 * 
	 * @param id the id
	 * @return the internal results response< imagem>
	 */
	public InternalResultsResponse<Imagem> fetchImagemById(Integer id);

}
