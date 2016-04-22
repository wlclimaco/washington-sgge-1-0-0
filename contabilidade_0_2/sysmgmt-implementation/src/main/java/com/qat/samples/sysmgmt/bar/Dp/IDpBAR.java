package com.qat.samples.sysmgmt.bar.Dp;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface DpBAR.. (Data Access Component - DAC)
 */
public interface IDpBAR
{

	/**
	 * Fetch funcionario by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	/**
* Insert funcionario.
*
* @param funcionario the funcionario
*
* @return the internal response
*/
	public InternalResponse insertFuncionario(Funcionario funcionario);

	/**
* Update funcionario.
*
* @param funcionario the funcionario
*
* @return the internal response
*/
	public InternalResponse updateFuncionario(Funcionario funcionario);

	/**
* Delete funcionario.
*
* @param funcionario the funcionario
*
* @return the internal response
*/
	public InternalResponse deleteFuncionarioById(Funcionario funcionario);

	/**
* Delete all funcionarios.
*
* @return the internal response
*/
	public InternalResponse deleteAllFuncionarios();

	/**
* Fetch all funcionarios.
*
* @return the list< funcionario>
*/
	public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario  funcionario);

	/**
* Fetch funcionarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request);

	/**
	 * Fetch eventos by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request);

	/**
* Insert eventos.
*
* @param eventos the eventos
*
* @return the internal response
*/
	public InternalResponse insertEventos(Eventos eventos);

	/**
* Update eventos.
*
* @param eventos the eventos
*
* @return the internal response
*/
	public InternalResponse updateEventos(Eventos eventos);

	/**
* Delete eventos.
*
* @param eventos the eventos
*
* @return the internal response
*/
	public InternalResponse deleteEventosById(Eventos eventos);

	/**
* Delete all eventoss.
*
* @return the internal response
*/
	public InternalResponse deleteAllEventos();

	/**
* Fetch all eventoss.
*
* @return the list< eventos>
*/
	public InternalResultsResponse<Eventos> fetchAllEventos(Eventos  eventos);

	/**
* Fetch eventoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> fetchEventosByRequest(EventoInquiryRequest request);

	/**
	 * Fetch beneficios by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request);

	/**
* Insert beneficios.
*
* @param beneficios the beneficios
*
* @return the internal response
*/
	public InternalResponse insertBeneficios(Beneficios beneficios);

	/**
* Update beneficios.
*
* @param beneficios the beneficios
*
* @return the internal response
*/
	public InternalResponse updateBeneficios(Beneficios beneficios);

	/**
* Delete beneficios.
*
* @param beneficios the beneficios
*
* @return the internal response
*/
	public InternalResponse deleteBeneficiosById(Beneficios beneficios);

	/**
* Delete all beneficioss.
*
* @return the internal response
*/
	public InternalResponse deleteAllBeneficioss();

	/**
* Fetch all beneficioss.
*
* @return the list< beneficios>
*/
	public InternalResultsResponse<Beneficios> fetchAllBeneficioss(Beneficios  beneficios);

	/**
* Fetch beneficioss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> fetchBeneficiossByRequest(BeneficiosInquiryRequest request);

	/**
	 * Fetch horafunc by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> fetchHorafuncById(FetchByIdRequest request);

	/**
* Insert horafunc.
*
* @param horafunc the horafunc
*
* @return the internal response
*/
	public InternalResponse insertHorafunc(HorarioFunc horafunc);

	/**
* Update horafunc.
*
* @param horafunc the horafunc
*
* @return the internal response
*/
	public InternalResponse updateHorafunc(HorarioFunc horafunc);

	/**
* Delete horafunc.
*
* @param horafunc the horafunc
*
* @return the internal response
*/
	public InternalResponse deleteHorafuncById(HorarioFunc horafunc);

	/**
* Delete all horafuncs.
*
* @return the internal response
*/
	public InternalResponse deleteAllHorafuncs();

	/**
* Fetch all horafuncs.
*
* @return the list< horafunc>
*/
	public InternalResultsResponse<HorarioFunc> fetchAllHorafuncs(HorarioFunc  horafunc);

	/**
* Fetch horafuncs by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> fetchHorafuncsByRequest(HoraFuncInquiryRequest request);

}
