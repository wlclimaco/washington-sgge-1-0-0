package com.qat.samples.sysmgmt.cnae.bai;

import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IEmpresaBAI.
 */
public interface ICnaeBAI
{

	public EmpresaResponse insertCnae(EmpresaMaintenanceRequest request);

	public EmpresaResponse updateCnae(EmpresaMaintenanceRequest request);

	public EmpresaResponse deleteCnae(EmpresaMaintenanceRequest request);

	public EmpresaResponse fetchCnaeById(FetchByIdRequest request);

	public EmpresaResponse fetchCnaeByRequest(EmpresaInquiryRequest request);

}