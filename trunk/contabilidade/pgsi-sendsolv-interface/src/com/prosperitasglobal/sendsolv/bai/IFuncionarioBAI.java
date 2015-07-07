package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.BeneficiosInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EventoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.HoraFuncInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.BeneficiosResponse;
import com.prosperitasglobal.sendsolv.model.response.EventoResponse;
import com.prosperitasglobal.sendsolv.model.response.FuncionarioResponse;
import com.prosperitasglobal.sendsolv.model.response.HorarioFuncResponse;

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