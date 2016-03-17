package com.qat.samples.sysmgmt.financeiro.bas.rest;

import com.qat.samples.sysmgmt.financeiro.bai.IFinanceiroBAI;
import com.qat.samples.sysmgmt.financeiro.bas.IFinanceiroRESTBAS;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class FinanceiroBAS implements IFinanceiroRESTBAS
{

	/** The empresa bai. */
	private IFinanceiroBAI pessoaBAI; // injected by Spring through setter

	public IFinanceiroBAI getFinanceiroBAI()
	{
		return pessoaBAI;
	}

	public void setFinanceiroBAI(IFinanceiroBAI pessoaBAI)
	{
		this.pessoaBAI = pessoaBAI;
	}

	@Override
	public FinanceiroResponse insertFinanceiro(FinanceiroMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinanceiroResponse updateFinanceiro(FinanceiroMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinanceiroResponse deleteFinanceiro(FinanceiroMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
