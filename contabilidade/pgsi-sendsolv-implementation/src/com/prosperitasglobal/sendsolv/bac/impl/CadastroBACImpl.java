package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.ICadastroBAC;

/**
 * The Class CadastroBACImpl.
 */
public class CadastroBACImpl implements ICadastroBAC
{

	@Override
	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		response = getCadastroDAC().insertCidade(request.getCadastro());

		return response;
	}

	@Override
	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		response = getCadastroDAC().updateCidade(request.getCidade());

		return response;
	}

	@Override
	public InternalResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		return getCadastroDAC().deleteCidade(request.getCidade());
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request)
	{
		return getCadastroDAC().fetchCidadeByRequest(request);
	}

	@Override
	public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request)
	{
		InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();

		response = getCadastroDAC().insertEstado(request.getEstado());

		return response;
	}

	@Override
	public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request)
	{
		InternalResultsResponse<Estado> response = new InternalResultsResponse<Estado>();

		response = getCadastroDAC().updateEstado(request.getEstado());

		return response;
	}

	@Override
	public InternalResponse deleteEstado(EstadoMaintenanceRequest request)
	{
		return getCadastroDAC().deleteEstado(request.getEstado());
	}

	@Override
	public InternalResultsResponse<Estado> fetchEstadoByRequest(EstadoInquiryRequest request)
	{
		return getCadastroDAC().fetchEstadoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		response = getCadastroDAC().insertCnae(request.getCnae());

		return response;
	}

	@Override
	public InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request)
	{
		InternalResultsResponse<Cnae> response = new InternalResultsResponse<Cnae>();

		response = getCadastroDAC().updateCnae(request.getCnae());

		return response;
	}

	@Override
	public InternalResponse deleteCnae(CnaeMaintenanceRequest request)
	{
		return getCadastroDAC().deleteCnae(request.getCnae());
	}

	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request)
	{
		return getCadastroDAC().fetchCnaeByRequest(request);
	}

	@Override
	public InternalResultsResponse<Regime> insertRegime(RegimeMaintenanceRequest request)
	{
		InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();

		response = getCadastroDAC().insertRegime(request.getRegime());

		return response;
	}

	@Override
	public InternalResultsResponse<Regime> updateRegime(RegimeMaintenanceRequest request)
	{
		InternalResultsResponse<Regime> response = new InternalResultsResponse<Regime>();

		response = getCadastroDAC().updateRegime(request.getRegime());

		return response;
	}

	@Override
	public InternalResponse deleteRegime(RegimeMaintenanceRequest request)
	{
		return getCadastroDAC().deleteRegime(request.getRegime());
	}

	@Override
	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request)
	{
		return getCadastroDAC().fetchRegimeByRequest(request);
	}

	@Override
	public InternalResultsResponse<Csosn> insertCsosn(CsosnMaintenanceRequest request)
	{
		InternalResultsResponse<Csosn> response = new InternalResultsResponse<Csosn>();

		response = getCadastroDAC().insertCsosn(request.getCsosn());

		return response;
	}

	@Override
	public InternalResultsResponse<Csosn> updateCsosn(CsosnMaintenanceRequest request)
	{
		InternalResultsResponse<Csosn> response = new InternalResultsResponse<Csosn>();

		response = getCadastroDAC().updateCsosn(request.getCsosn());

		return response;
	}

	@Override
	public InternalResponse deleteCsosn(CsosnMaintenanceRequest request)
	{
		return getCadastroDAC().deleteCsosn(request.getCsosn());
	}

	@Override
	public InternalResultsResponse<Csosn> fetchCsosnByRequest(CsosnInquiryRequest request)
	{
		return getCadastroDAC().fetchCsosnByRequest(request);
	}

	@Override
	public InternalResultsResponse<UniMed> insertUniMed(UniMedMaintenanceRequest request)
	{
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();

		response = getCadastroDAC().insertUniMed(request.getUniMed());

		return response;
	}

	@Override
	public InternalResultsResponse<UniMed> updateUniMed(UniMedMaintenanceRequest request)
	{
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();

		response = getCadastroDAC().updateUniMed(request.getUniMed());

		return response;
	}

	@Override
	public InternalResponse deleteUniMed(UniMedMaintenanceRequest request)
	{
		return getCadastroDAC().deleteUniMed(request.getUniMed());
	}

	@Override
	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request)
	{
		return getCadastroDAC().fetchUniMedByRequest(request);
	}

	@Override
	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request)
	{
		InternalResultsResponse<Cfop> response = new InternalResultsResponse<Cfop>();

		response = getCadastroDAC().insertCfop(request.getCfop());

		return response;
	}

	@Override
	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request)
	{
		InternalResultsResponse<Cadastro> response = new InternalResultsResponse<Cadastro>();

		response = getCadastroDAC().updateCadastro(request.getCadastro());

		return response;
	}

	@Override
	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request)
	{
		return getCadastroDAC().deleteCfop(request.getCfop());
	}

	@Override
	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request)
	{
		return getCadastroDAC().fetchCfopByRequest(request);
	}

}
