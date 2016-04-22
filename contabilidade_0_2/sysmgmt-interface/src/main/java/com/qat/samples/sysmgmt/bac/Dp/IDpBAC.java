package com.qat.samples.sysmgmt.bac.Dp;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.Eventos;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.EventosMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDpBAC. (Business Area Component - BAC)
 */
public interface IDpBAC
{



//===================================### FUNCIONARIO ####======================================
	/**

	/**
	 * Insert funcionario.
	 *
* @param request the funcionario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request);

	/**
* Update funcionario.
*
* @param request the funcionario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request);

	/**
* Delete funcionario.
*
* @param request the funcionario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> deleteFuncionario(FuncionarioMaintenanceRequest request);

	/**
* Refresh funcionarios.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Funcionario> refreshFuncionarios(RefreshRequest request);

	/**
* Fetch funcionario by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	/**
* Fetch all funcionarios.
*
* @return the internal results response< funcionario>
*/
	public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario  funcionario);

	/**
* Fetch funcionarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request);


//===================================### EVENTOS ####======================================
	/**

	/**
	 * Insert eventos.
	 *
* @param request the eventos maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> insertEventos(EventosMaintenanceRequest request);

	/**
* Update eventos.
*
* @param request the eventos maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> updateEventos(EventosMaintenanceRequest request);

	/**
* Delete eventos.
*
* @param request the eventos maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> deleteEventos(EventosMaintenanceRequest request);

	/**
* Refresh eventoss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Eventos> refreshEventoss(RefreshRequest request);

	/**
* Fetch eventos by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request);

	/**
* Fetch all eventoss.
*
* @return the internal results response< eventos>
*/
	public InternalResultsResponse<Eventos> fetchAllEventoss(Eventos  eventos);

	/**
* Fetch eventoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Eventos> fetchEventossByRequest(EventoInquiryRequest request);


//===================================### BENEFICIOS ####======================================
	/**

	/**
	 * Insert beneficios.
	 *
* @param request the beneficios maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> insertBeneficios(BeneficiosMaintenanceRequest request);

	/**
* Update beneficios.
*
* @param request the beneficios maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> updateBeneficios(BeneficiosMaintenanceRequest request);

	/**
* Delete beneficios.
*
* @param request the beneficios maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> deleteBeneficios(BeneficiosMaintenanceRequest request);

	/**
* Refresh beneficioss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Beneficios> refreshBeneficioss(RefreshRequest request);

	/**
* Fetch beneficios by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> fetchBeneficiosById(FetchByIdRequest request);

	/**
* Fetch all beneficioss.
*
* @return the internal results response< beneficios>
*/
	public InternalResultsResponse<Beneficios> fetchAllBeneficioss(Beneficios  beneficios);

	/**
* Fetch beneficioss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Beneficios> fetchBeneficiossByRequest(BeneficiosInquiryRequest request);


//===================================### HORAFUNC ####======================================
	/**

	/**
	 * Insert horafunc.
	 *
* @param request the horafunc maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> insertHoraFunc(HoraFuncMaintenanceRequest request);

	/**
* Update horafunc.
*
* @param request the horafunc maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> updateHoraFunc(HoraFuncMaintenanceRequest request);

	/**
* Delete horafunc.
*
* @param request the horafunc maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> deleteHoraFunc(HoraFuncMaintenanceRequest request);

	/**
* Refresh horafuncs.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<HorarioFunc> refreshHoraFuncs(RefreshRequest request);

	/**
* Fetch horafunc by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> fetchHoraFuncById(FetchByIdRequest request);

	/**
* Fetch all horafuncs.
*
* @return the internal results response< horafunc>
*/
	public InternalResultsResponse<HorarioFunc> fetchAllHoraFuncs(HorarioFunc  horafunc);

	/**
* Fetch horafuncs by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<HorarioFunc> fetchHoraFuncsByRequest(HoraFuncInquiryRequest request);

}
