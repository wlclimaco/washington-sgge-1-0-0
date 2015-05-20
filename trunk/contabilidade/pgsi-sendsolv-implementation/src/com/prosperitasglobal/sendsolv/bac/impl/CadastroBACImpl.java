package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.ICadastroBAC;

/**
 * The Class CadastroBACImpl.
 */
public class CadastroBACImpl implements ICadastroBAC
{

	/** The person dac. */
	private ICadastroDAC CadastroDAC;

	/**
	 * @return the CadastroDAC
	 */
	public ICadastroDAC getCadastroDAC()
	{
		return CadastroDAC;
	}

	/**
	 * @param CadastroDAC the CadastroDAC to set
	 */
	public void setCadastroDAC(ICadastroDAC CadastroDAC)
	{
		this.CadastroDAC = CadastroDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ICadastroBAC#insertCadastro(CadastroRequest)
	 */
	@Override
	public InternalResultsResponse<Cadastro> insertCadastro(CadastroMaintenanceRequest request)
	{
		InternalResultsResponse<Cadastro> response = new InternalResultsResponse<Cadastro>();

		response = getCadastroDAC().insertCadastro(request.getCadastro());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ICadastroBAC#updateCadastro(CadastroRequest)
	 */
	@Override
	public InternalResultsResponse<Cadastro> updateCadastro(CadastroMaintenanceRequest request)
	{
		InternalResultsResponse<Cadastro> response = new InternalResultsResponse<Cadastro>();

		response = getCadastroDAC().updateCadastro(request.getCadastro());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICadastroBAC#deleteCadastro
	 * (com.prosperitasglobal.sendsolv.model.request.CadastroRequest
	 * )
	 */
	@Override
	public InternalResponse deleteCadastro(CadastroMaintenanceRequest request)
	{
		return getCadastroDAC().deleteCadastro(request.getCadastro());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ICadastroBAC#fetchCadastroById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Cadastro> fetchCadastroById(FetchByIdRequest request)
	{
		return getCadastroDAC().fetchCadastroById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICadastroBAC#fetchCadastroByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .CadastroInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cadastro> fetchCadastroByRequest(PagedInquiryRequest request)
	{
		return getCadastroDAC().fetchCadastroByRequest(request);
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cidade> insertCidade(
			CidadeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cidade> updateCidade(
			CidadeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cidade> fetchCidadeByRequest(
			CidadeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Estado> insertEstado(
			EstadoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Estado> updateEstado(
			EstadoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResponse deleteEstado(EstadoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Estado> fetchEstadoByRequest(
			EstadoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cnae> insertCnae(CnaeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cnae> updateCnae(CnaeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResponse deleteCnae(CnaeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cnae> fetchCnaeByRequest(
			CnaeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Regime> insertRegime(
			RegimeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Regime> updateRegime(
			RegimeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResponse deleteRegime(RegimeMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Regime> fetchRegimeByRequest(
			RegimeInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Csosn> insertCsosn(CsosnMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Csosn> updateCsosn(CsosnMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResponse deleteCsosn(CsosnMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Csosn> fetchCsosnByRequest(
			CsosnInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<UniMed> insertCsosn(
			UniMedMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<UniMed> updateCsosn(
			UniMedMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResponse deleteCsosn(UniMedMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<UniMed> fetchCsosnByRequest(
			UniMedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.prosperitasglobal.sendsolv.bac.InternalResultsResponse<Cfop> fetchCfopByRequest(
			CfopInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
