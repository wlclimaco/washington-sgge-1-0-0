/** create by system gera-java version 1.0.0 28/04/2016 20:29 : 56*/
package com.qat.samples.sysmgmt.bar.Advogado;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface AdvocaciaBAR.. (Data Access Component - DAC)
 */
public interface IAdvocaciaBAR
{

	/**
	 * Fetch advogado by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Advogado fetchAdvogadoById(FetchByIdRequest request);

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
	 * Fetch audiencia by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Audiencia fetchAudienciaById(FetchByIdRequest request);

	/**
* Insert audiencia.
*
* @param audiencia the audiencia
*
* @return the internal response
*/
	public InternalResponse insertAudiencia(Audiencia audiencia);

	/**
* Update audiencia.
*
* @param audiencia the audiencia
*
* @return the internal response
*/
	public InternalResponse updateAudiencia(Audiencia audiencia);

	/**
* Delete audiencia.
*
* @param audiencia the audiencia
*
* @return the internal response
*/
	public InternalResponse deleteAudienciaById(Audiencia audiencia);

	/**
* Delete all audiencias.
*
* @return the internal response
*/
	public InternalResponse deleteAllAudiencias();

	/**
* Fetch all audiencias.
*
* @return the list< audiencia>
*/
	public InternalResultsResponse<Audiencia> fetchAllAudiencias(Audiencia  audiencia);

	/**
* Fetch audiencias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Audiencia> fetchAudienciasByRequest(AudienciaInquiryRequest request);

	/**
	 * Fetch processo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Processo fetchProcessoById(FetchByIdRequest request);

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
