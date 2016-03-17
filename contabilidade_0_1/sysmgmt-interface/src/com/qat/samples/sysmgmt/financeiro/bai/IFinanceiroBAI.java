package com.qat.samples.sysmgmt.financeiro.bai;

import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

public interface IFinanceiroBAI
{

	public FinanceiroResponse insertFinanceiro(FinanceiroMaintenanceRequest request);

	public FinanceiroResponse updateFinanceiro(FinanceiroMaintenanceRequest request);

	public FinanceiroResponse deleteFinanceiro(FinanceiroMaintenanceRequest request);

	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest request);

	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest request);
	


}