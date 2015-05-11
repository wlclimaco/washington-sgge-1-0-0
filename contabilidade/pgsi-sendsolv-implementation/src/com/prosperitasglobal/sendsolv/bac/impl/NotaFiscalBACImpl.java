package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IEmpresaBAC;
import com.prosperitasglobal.sendsolv.dac.IEmpresaDAC;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class EmpresaBACImpl.
 */
public class NotaFiscalBACImpl implements IEmpresaBAC
{

	/** The person dac. */
	private IEmpresaDAC empresaDAC;

	/**
	 * @return the empresaDAC
	 */
	public IEmpresaDAC getEmpresaDAC()
	{
		return empresaDAC;
	}

	/**
	 * @param empresaDAC the empresaDAC to set
	 */
	public void setEmpresaDAC(IEmpresaDAC empresaDAC)
	{
		this.empresaDAC = empresaDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#insertEmpresa(EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		response = getEmpresaDAC().insertEmpresa(request.getEmpresa());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#updateEmpresa(EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		response = getEmpresaDAC().updateEmpresa(request.getEmpresa());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#deleteEmpresa
	 * (com.prosperitasglobal.sendsolv.model.request.EmpresaRequest
	 * )
	 */
	@Override
	public InternalResponse deleteEmpresa(EmpresaMaintenanceRequest request)
	{
		return getEmpresaDAC().deleteEmpresa(request.getEmpresa());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#fetchEmpresaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
	{
		return getEmpresaDAC().fetchEmpresaById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#fetchEmpresaByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .EmpresaInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(EmpresaInquiryRequest request)
	{
		return getEmpresaDAC().fetchEmpresaByRequest(request);
	}
}
