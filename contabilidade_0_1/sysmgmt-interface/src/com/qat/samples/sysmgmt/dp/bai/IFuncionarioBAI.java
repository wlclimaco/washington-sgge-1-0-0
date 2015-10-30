package com.qat.samples.sysmgmt.dp.bai;

import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.model.response.BeneficiosResponse;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.response.EventoResponse;
import com.qat.samples.sysmgmt.dp.model.response.FuncionarioResponse;
import com.qat.samples.sysmgmt.dp.model.response.HorarioFuncResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FuncionarioMaintenanceRequest;

/**
 * The Interface IFuncionarioBAI.
 */
public interface IFuncionarioBAI
{

	/**
	 * Insert funcionario.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse insertFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Update funcionario.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse updateFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Delete funcionario.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse deleteFuncionario(FuncionarioMaintenanceRequest request);

	/**
	 * Fetch funcionario by id.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse fetchFuncionarioById(FetchByIdRequest request);

	/**
	 * Fetch funcionario by request.
	 *
	 * @param request the request
	 * @return the funcionario response
	 */
	public FuncionarioResponse fetchFuncionarioByRequest(FuncionarioInquiryRequest request);

	/**
	 * Fetch beneficios by request.
	 *
	 * @param request the request
	 * @return the beneficios response
	 */
	public BeneficiosResponse fetchBeneficiosByRequest(BeneficiosInquiryRequest request);

	/**
	 * Fetch hora func by request.
	 *
	 * @param request the request
	 * @return the hora func response
	 */
	public HorarioFuncResponse fetchHoraFuncByRequest(HoraFuncInquiryRequest request);

	/**
	 * Fetch evento by request.
	 *
	 * @param request the request
	 * @return the evento response
	 */
	public EventoResponse fetchEventoByRequest(EventoInquiryRequest request);

}