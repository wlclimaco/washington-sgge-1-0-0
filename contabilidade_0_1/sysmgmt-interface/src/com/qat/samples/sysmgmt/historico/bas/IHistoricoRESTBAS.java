package com.qat.samples.sysmgmt.historico.bas;

import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.historico.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IHistoricoRESTBAS
{

	@POST
	@Path("/insertHistorico/")
	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request);

	@POST
	@Path("/updateHistorico/")
	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request);

	@POST
	@Path("/deleteHistorico/")
	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request);

	@POST
	@Path("/fetchHistoricoById/")
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request);

	@POST
	@Path("/fetchHistoricoByRequest/")
	public HistoricoResponse fetchHistoricoByRequest(HistoricoInquiryRequest request);

}
