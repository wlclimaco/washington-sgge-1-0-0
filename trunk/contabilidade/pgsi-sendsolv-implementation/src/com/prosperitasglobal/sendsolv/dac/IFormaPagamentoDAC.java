package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IFormaPgDAC.
 */
public interface IFormaPagamentoDAC
{

	/**
	 * Update formaPg.
	 *
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateFormaPg(FormaPg formaPg, InternalResultsResponse<?> response);

	/**
	 * Insert formaPg.
	 *
	 * @param formaPg the formaPg
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertFormaPg(FormaPg formaPg, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business formaPg.
	 *
	 * @param formaPg the formaPg
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteFormaPg(FormaPg formaPg, InternalResultsResponse<?> response);

	/**
	 * Fetch formaPg by id.
	 *
	 * @param id the id
	 * @return the internal results response< formaPg>
	 */
	public InternalResultsResponse<FormaPg> fetchFormaPgById(Integer id);

}
