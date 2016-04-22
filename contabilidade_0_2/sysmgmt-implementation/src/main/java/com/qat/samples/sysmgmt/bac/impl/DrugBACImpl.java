package com.qat.samples.sysmgmt.bac.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.MessageLevel;
import com.qat.framework.model.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bac.IDrugBAC;
import com.qat.samples.sysmgmt.bar.IDrugBAR;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Class DrugBACImpl.
 */
@Component
public class DrugBACImpl implements IDrugBAC
{

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_DRUG_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_DRUG_BAC_EXCEPTION_MSG = "sysmgmt.base.drugbacimpl.defaultexception";

	private static final String SYSMGMT_BASE_ID_REQUIRED = "sysmgmt.base.validator.id.required";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DrugBACImpl.class);

	/** The drug BAR. */
	private IDrugBAR drugBAR; // injected by Spring through setter

	private ValidationController validationController; // injected by Spring through setter

	/**
	 * Sets the drug BAR.
	 *
	 * @param drugBAR the new drug BAR
	 */
	public void setDrugBAR(IDrugBAR drugBAR)
	{
		this.drugBAR = drugBAR;
	}

	/**
	 * Gets the drug BAR.
	 *
	 * @return the drug BAR
	 */
	public IDrugBAR getDrugBAR()
	{
		return drugBAR;
	}

	public ValidationController getValidationController()
	{
		return validationController;
	}

	public void setValidationController(ValidationController validationController)
	{
		this.validationController = validationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IDrugBAC#insertDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Drug> insertDrug(DrugMaintenanceRequest request)
	{
		InternalResultsResponse<Drug> response =
				process(ValidationContextIndicator.INSERT, PersistenceActionEnum.INSERT, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IDrugBAC#updateDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Drug> updateDrug(DrugMaintenanceRequest request)
	{
		InternalResultsResponse<Drug> response =
				process(ValidationContextIndicator.UPDATE, PersistenceActionEnum.UPDATE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IDrugBAC#deleteDrug(com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Drug> deleteDrug(DrugMaintenanceRequest request)
	{
		InternalResultsResponse<Drug> response =
				process(ValidationContextIndicator.DELETE, PersistenceActionEnum.DELETE, request);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IDrugBAC#refreshDrugs(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public InternalResultsResponse<Drug> refreshDrugs(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getDrugBAR().deleteAllDrugs();

		int refreshNumber = request.getRefreshInt();

		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			Drug drug = new Drug("NDC" + i, "DrugDesc" + i);
			DrugPrice drugPrice = new DrugPrice("NDC" + i, "F", new BigDecimal(18.00 + i), new Date().getTime());
			drug.setDrugPrices(Arrays.asList(drugPrice));
			getDrugBAR().insertDrug(drug);
		}
		// Call maintain to see if we need to return the drug list and if so whether it should be paged or not
		return maintainReturnList(request.getReturnList(), request.getReturnListPaged());
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IDrugBAC#fetchAllDrugs()
	 */
	@Override
	public InternalResultsResponse<Drug> fetchAllDrugs()
	{
		InternalResultsResponse<Drug> response = new InternalResultsResponse<Drug>();
		response.getResultsList().addAll(getDrugBAR().fetchAllDrugs());
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

		if (ValidationUtil.isNullOrEmpty(drugCode))
		{
			response.addFieldErrorMessage(SYSMGMT_BASE_ID_REQUIRED);
			response.setStatus(SystemErrorCategory.SystemValidation);
		}
		else
		{
			response.getResultsList().add(getDrugBAR().fetchDrugByCode(drugCode));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IDrugBAC#fetchDrugsByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Drug> fetchDrugsByRequest(PagedInquiryRequest request)
	{
		return getDrugBAR().fetchDrugsByRequest(request);
	}

	/**
	 * Process.
	 *
	 * @param indicator the indicator
	 * @param persistType the persist type
	 * @param request the request
	 * @return the drug response
	 */
	private InternalResultsResponse<Drug> process(ValidationContextIndicator indicator,
			PersistenceActionEnum persistType,
			DrugMaintenanceRequest request)
			{
		InternalResultsResponse<Drug> response = null;

		// Validate
		ValidationContext context = new ValidationContext(Drug.class.getSimpleName(), request.getDrug(), indicator);
		if (!getValidationController().validate(context))
		{
			response = new InternalResultsResponse<Drug>();
			response.setStatus(SystemErrorCategory.SystemValidation);
			response.addMessages(context.getMessages());
			return response;
		}

		// Persist
		InternalResponse internalResponse = doPersistence(request.getDrug(), persistType);
		if (internalResponse.isInError())
		{
			response = new InternalResultsResponse<Drug>();
			response.setStatus(internalResponse.getError());
			response.addMessages(internalResponse.getMessageInfoList());
			response.addMessage(DEFAULT_DRUG_BAC_EXCEPTION_MSG, MessageSeverity.Error,
					MessageLevel.Object,
					new Object[] {internalResponse.errorToString()});

			return response;
		}

		// Call maintainReurnList to see if we need to return the county list and if so whether it should be paged or
		// not
		response = maintainReturnList(request.getReturnList(), request.getReturnListPaged());

		return response;
			}

	/**
	 * Do persistence.
	 *
	 * @param request the request
	 * @param updateType the update type
	 * @return the internal response
	 */
	private InternalResponse doPersistence(Drug drug, PersistenceActionEnum updateType)
	{
		switch (updateType)
		{
			case INSERT:
				return getDrugBAR().insertDrug(drug);

			case UPDATE:
				return getDrugBAR().updateDrug(drug);

			case DELETE:
				return getDrugBAR().deleteDrug(drug);
			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug("updateType missing!");
				}
				break;
		}

		return null;
	}

	/**
	 * Maintain return list.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private InternalResultsResponse<Drug> maintainReturnList(Boolean listIndicator, Boolean pageListIndicator)
	{
		// Fetch again if requested.
		if (listIndicator)
		{
			// Fetch Paged is requested.
			if (pageListIndicator)
			{
				PagedInquiryRequest request = new PagedInquiryRequest();
				request.setPreQueryCount(true);
				return fetchDrugsByRequest(request);
			}
			else
			{
				// otherwise return all rows not paged
				return fetchAllDrugs();
			}
		}
		else
		{
			return new InternalResultsResponse<Drug>();
		}
	}
}
