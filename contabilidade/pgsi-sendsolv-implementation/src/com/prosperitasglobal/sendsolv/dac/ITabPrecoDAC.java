package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface ITabPrecoDAC.
 */
public interface ITabPrecoDAC
{

	/**
	 * Update tabPreco.
	 *
	 * @param tabPreco the tabPreco
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTabPreco(TabPreco tabPreco, InternalResultsResponse<?> response);

	/**
	 * Insert tabPreco.
	 *
	 * @param tabPreco the tabPreco
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTabPreco(TabPreco tabPreco, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business tabPreco.
	 *
	 * @param tabPreco the tabPreco
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteTabPreco(TabPreco tabPreco, InternalResultsResponse<?> response);

	/**
	 * Fetch tabPreco by id.
	 *
	 * @param id the id
	 * @return the internal results response< tabPreco>
	 */
	public InternalResultsResponse<TabPreco> fetchTabPrecoById(Integer id);

}
