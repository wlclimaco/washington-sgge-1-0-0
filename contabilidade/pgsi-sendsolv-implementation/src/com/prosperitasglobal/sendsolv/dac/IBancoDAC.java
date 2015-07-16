package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IBancoDAC.
 */
public interface IBancoDAC
{

	/**
	 * Update banco.
	 *
	 * @param banco the banco
	 * @return the internal results response< banco>
	 */
	public Integer updateBanco(Banco banco);

	/**
	 * Insert banco.
	 *
	 * @param banco the banco
	 * @return the internal results response< banco>
	 */
	public Integer insertBanco(Banco banco);

	/**
	 * Delete banco.
	 *
	 * @param banco the banco
	 * @return the internal response
	 */
	public Integer deleteBanco(Banco banco);

	/**
	 * Fetch banco by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request);

	/**
	 * Fetch all bancos.
	 *
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Banco> fetchAllBancos();

	/**
	 * Fetch banco by request.
	 *
	 * @param request the request
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Banco> fetchBancoByRequest(PagedInquiryRequest request);

	/**
	 * Update banco.
	 *
	 * @param banco the banco
	 * @return the internal results response< banco>
	 */
	public Integer updateBancoPessoa(BancoPessoa banco);

	/**
	 * Insert banco.
	 *
	 * @param banco the banco
	 * @return the internal results response< banco>
	 */
	public Integer insertBancoPessoa(BancoPessoa banco);

	/**
	 * Delete banco.
	 *
	 * @param banco the banco
	 * @return the internal response
	 */
	public Integer deleteBancoPessoa(BancoPessoa banco);

}
