package com.qat.samples.sysmgmt.financeiro.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.financeiro.model.response.FinanceiroResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IFinanceiroRESTBAS
{

	@POST
	@Path("/insertFinanceiro/")
	public FinanceiroResponse insertFinanceiro(FinanceiroMaintenanceRequest request);

	@POST
	@Path("/updateFinanceiro/")
	public FinanceiroResponse updateFinanceiro(FinanceiroMaintenanceRequest request);

	@POST
	@Path("/deleteFinanceiro/")
	public FinanceiroResponse deleteFinanceiro(FinanceiroMaintenanceRequest request);

	@POST
	@Path("/fetchFinanceiroById/")
	public FinanceiroResponse fetchFinanceiroById(FetchByIdRequest request);

	@POST
	@Path("/fetchFinanceiroByRequest/")
	public FinanceiroResponse fetchFinanceiroByRequest(FinanceiroInquiryRequest request);

}
