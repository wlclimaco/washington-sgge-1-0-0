package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Classificacao;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Deposito;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.Filial;
import com.prosperitasglobal.sendsolv.model.Plano;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PlanoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IEmpresaDAC.
 */
public interface IEmpresaDAC
{

	/**
	 * Update empresa.
	 *
	 * @param empresa the empresa
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> updateEmpresa(Empresa empresa);

	/**
	 * Insert empresa.
	 *
	 * @param empresa the empresa
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa);

	/**
	 * Delete empresa.
	 *
	 * @param empresa the empresa
	 * @return the internal response
	 */
	public InternalResponse deleteEmpresa(Empresa empresa);

	/**
	 * Fetch empresa by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	/**
	 * Fetch empresa by request.
	 *
	 * @param request the request
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(EmpresaInquiryRequest request);

	// filial
	public InternalResultsResponse<Filial> insertFilial(Filial filial);

	public InternalResultsResponse<Filial> updateFilial(Filial filial);

	public InternalResponse deleteFilial(Filial filial);

	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

	public InternalResultsResponse<Filial> fetchFilialByRequest(FilialInquiryRequest request);

	// deposito
	public InternalResultsResponse<Deposito> insertDeposito(Deposito deposito);

	public InternalResultsResponse<Deposito> updateDeposito(Deposito deposito);

	public InternalResponse deleteDeposito(Deposito deposito);

	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

	public InternalResultsResponse<Deposito> fetchDepositoByRequest(DepositoInquiryRequest request);

	// outros
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest cnae);

	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest regime);

	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest cidade);

	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest plano);

	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest classificacao);

}
