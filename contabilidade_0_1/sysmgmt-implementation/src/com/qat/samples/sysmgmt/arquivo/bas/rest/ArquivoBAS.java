package com.qat.samples.sysmgmt.arquivo.bas.rest;

import com.qat.samples.sysmgmt.arquivo.bas.IArquivoRESTBAS;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.response.ArquivoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class ArquivoBAS implements IArquivoRESTBAS
{

	@Override
	public ArquivoResponse insertArquivo(ArquivoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoResponse updateArquivo(ArquivoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoResponse deleteArquivo(ArquivoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoResponse fetchArquivoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoResponse fetchArquivoByRequest(ArquivoInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
