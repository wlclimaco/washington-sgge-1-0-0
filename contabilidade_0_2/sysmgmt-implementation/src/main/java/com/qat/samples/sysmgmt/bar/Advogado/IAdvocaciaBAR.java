package com.qat.samples.sysmgmt.bar.Advogado;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface AdvogadoBAR.. (Data Access Component - DAC)
 */
public interface IAdvocaciaBAR
{

	/**
	 * Fetch advogado by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request);

	/**
* Insert advogado.
*
* @param advogado the advogado
*
* @return the internal response
*/
	public InternalResponse insertAdvogado(Advogado advogado);

	/**
* Update advogado.
*
* @param advogado the advogado
*
* @return the internal response
*/
	public InternalResponse updateAdvogado(Advogado advogado);

	/**
* Delete advogado.
*
* @param advogado the advogado
*
* @return the internal response
*/
	public InternalResponse deleteAdvogadoById(Advogado advogado);

	/**
* Delete all advogados.
*
* @return the internal response
*/
	public InternalResponse deleteAllAdvogados();

	/**
* Fetch all advogados.
*
* @return the list< advogado>
*/
	public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado  advogado);

	/**
* Fetch advogados by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request);

	/**
	 * Fetch processo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request);

	/**
* Insert processo.
*
* @param processo the processo
*
* @return the internal response
*/
	public InternalResponse insertProcesso(Processo processo);

	/**
* Update processo.
*
* @param processo the processo
*
* @return the internal response
*/
	public InternalResponse updateProcesso(Processo processo);

	/**
* Delete processo.
*
* @param processo the processo
*
* @return the internal response
*/
	public InternalResponse deleteProcessoById(Processo processo);

	/**
* Delete all processos.
*
* @return the internal response
*/
	public InternalResponse deleteAllProcessos();

	/**
* Fetch all processos.
*
* @return the list< processo>
*/
	public InternalResultsResponse<Processo> fetchAllProcessos(Processo  processo);

	/**
* Fetch processos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Processo> fetchProcessosByRequest(ProcessoInquiryRequest request);

}
