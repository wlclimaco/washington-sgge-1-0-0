package com.qat.samples.sysmgmt.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.bai.IDrugBAI;
import com.qat.samples.sysmgmt.bas.IDrugBAS;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByCodeRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;

/**
 * The Class DrugBAS.
 */
@WebService(targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt")
public class DrugBAS implements IDrugBAS
{

	/** The drug bai. */
	private IDrugBAI drugBAI; // injected by Spring through setter

	/**
	 * Gets the drug bai.
	 * 
	 * @return the drug bai
	 */
	public IDrugBAI getDrugBAI()
	{
		return drugBAI;
	}

	/**
	 * Sets the drug bai.
	 * 
	 * @param drugBAI the new drug bai
	 */
	public void setDrugBAI(IDrugBAI drugBAI)
	{
		this.drugBAI = drugBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bas.IDrugBAS#insertDrug(com.qat.samples.sysmgmt.base.model.DrugRequest)
	 */
	@Override
	public DrugResponse insertDrug(DrugMaintenanceRequest request)
	{
		return getDrugBAI().insertDrug(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IDrugBAS#updateDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public DrugResponse updateDrug(DrugMaintenanceRequest request)
	{
		return getDrugBAI().updateDrug(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IDrugBAS#deleteDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public DrugResponse deleteDrug(DrugMaintenanceRequest request)
	{
		return getDrugBAI().deleteDrug(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IDrugBAS#refreshDrugs(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public DrugResponse refreshDrugs(RefreshRequest request)
	{
		return getDrugBAI().refreshDrugs(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IDrugBAS#fetchAllDrugs(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public DrugResponse fetchAllDrugs(FetchAllRequest request)
	{
		return getDrugBAI().fetchAllDrugs(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IDrugBAS#fetchDrugByCode(com.qat.samples.sysmgmt.model.request.FetchByCodeRequest)
	 */
	@Override
	public DrugResponse fetchDrugByCode(FetchByCodeRequest request)
	{
		return getDrugBAI().fetchDrugByCode(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IDrugBAS#fetchDrugsByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest
	 * )
	 */
	@Override
	public DrugResponse fetchDrugsByRequest(PagedInquiryRequest request)
	{
		return getDrugBAI().fetchDrugsByRequest(request);
	}
}
