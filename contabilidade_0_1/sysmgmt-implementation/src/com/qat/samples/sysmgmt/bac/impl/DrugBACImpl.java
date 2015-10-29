package com.qat.samples.sysmgmt.bac.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IDrugBAC;
import com.qat.samples.sysmgmt.dac.IDrugDAC;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class DrugBACImpl.
 */
public class DrugBACImpl implements IDrugBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_DRUG_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_DRUG_BAC_EXCEPTION_MSG = "sysmgmt.base.drugbacimpl.defaultexception";

	/** The drug dac. */
	private IDrugDAC drugDAC; // injected by Spring through setter

	/**
	 * Sets the drug dac.
	 * 
	 * @param drugDAC the new drug dac
	 */
	public void setDrugDAC(IDrugDAC drugDAC)
	{
		this.drugDAC = drugDAC;
	}

	/**
	 * Gets the drug dac.
	 * 
	 * @return the drug dac
	 */
	public IDrugDAC getDrugDAC()
	{
		return drugDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IDrugBAC#insertDrug(com.qat.samples.sysmgmt.base.model.Drug)
	 */
	@Override
	public InternalResponse insertDrug(Drug drug)
	{
		return getDrugDAC().insertDrug(drug);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IDrugBAC#updateDrug(com.qat.samples.sysmgmt.base.model.Drug)
	 */
	@Override
	public InternalResponse updateDrug(Drug drug)
	{
		InternalResponse internalResponse = getDrugDAC().updateDrug(drug);
		// Check for error because in business case all non-success returns are failures (updating of zero rows) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_DRUG_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error, Message.MessageLevel.Object, new Object[] {internalResponse
					.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IDrugBAC#deleteDrug(com.qat.samples.sysmgmt.base.model.Drug)
	 */
	@Override
	public InternalResponse deleteDrug(Drug drug)
	{
		InternalResponse internalResponse = getDrugDAC().deleteDrug(drug);
		// Check for error because in business case all non-success returns are failures (removal of zero rows) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_DRUG_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error, Message.MessageLevel.Object, new Object[] {internalResponse
					.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IDrugBAC#refreshDrugs(int)
	 */
	@Override
	public void refreshDrugs(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getDrugDAC().deleteAllDrugs();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			Drug drug = new Drug("NDC" + i, "DrugDesc" + i);
			DrugPrice drugPrice = new DrugPrice("NDC" + i, "F", new BigDecimal(18.00 + i), new Date());
			drug.setDrugPrices(Arrays.asList(drugPrice));
			getDrugDAC().insertDrug(drug);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IDrugBAC#fetchAllDrugs()
	 */
	@Override
	public InternalResultsResponse<Drug> fetchAllDrugs()
	{
		InternalResultsResponse<Drug> response = new InternalResultsResponse<Drug>();
		response.getResultsList().addAll(getDrugDAC().fetchAllDrugs());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IDrugBAC#fetchDrugByCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Drug> fetchDrugByCode(String drugCode)
	{
		InternalResultsResponse<Drug> response = new InternalResultsResponse<Drug>();
		response.getResultsList().add(getDrugDAC().fetchDrugByCode(drugCode));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IDrugBAC#fetchDrugsByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Drug> fetchDrugsByRequest(PagedInquiryRequest request)
	{
		return getDrugDAC().fetchDrugsByRequest(request);
	}
}
