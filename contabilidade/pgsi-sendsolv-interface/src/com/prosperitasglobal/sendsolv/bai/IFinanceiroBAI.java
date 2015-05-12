package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.FinanceiroInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FinanceiroMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.FinanceiroResponse;

public interface IFinanceiroBAI
{

	public FinanceiroResponse insertFinanceiro(FinanceiroMaintenanceRequest request);

	public FinanceiroResponse updateFinanceiro(FinanceiroMaintenanceRequest request);

	public FinanceiroResponse deleteFinanceiro(FinanceiroMaintenanceRequest request);

	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest request);

	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest request);

}