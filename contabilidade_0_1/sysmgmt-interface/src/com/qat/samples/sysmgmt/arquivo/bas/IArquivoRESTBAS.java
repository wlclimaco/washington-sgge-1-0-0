package com.qat.samples.sysmgmt.arquivo.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.response.ArquivoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IArquivoRESTBAS
{

	@POST
	@Path("/insertArquivo/")
	public ArquivoResponse insertArquivo(ArquivoMaintenanceRequest request);

	@POST
	@Path("/updateArquivo/")
	public ArquivoResponse updateArquivo(ArquivoMaintenanceRequest request);

	@POST
	@Path("/deleteArquivo/")
	public ArquivoResponse deleteArquivo(ArquivoMaintenanceRequest request);

	@POST
	@Path("/fetchArquivoById/")
	public ArquivoResponse fetchArquivoById(FetchByIdRequest request);

	@POST
	@Path("/fetchArquivoByRequest/")
	public ArquivoResponse fetchArquivoByRequest(ArquivoInquiryRequest request);

}
