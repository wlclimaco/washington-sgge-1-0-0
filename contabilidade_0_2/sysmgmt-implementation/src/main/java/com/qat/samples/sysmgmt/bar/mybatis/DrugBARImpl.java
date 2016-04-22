package com.qat.samples.sysmgmt.bar.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.BaseModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.IDrugBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PagedResultsBARD;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class DrugBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class DrugBARImpl extends SqlSessionDaoSupport implements IDrugBAR
{

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "DrugMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertNDC";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateNDC";

	/** The Constant STMT_DELETE. */
	private static final String STMT_DELETE = NAMESPACE + "deleteDrugByNDC";

	/** The Constant STMT_DELETE_ALL. */
	private static final String STMT_DELETE_ALL = NAMESPACE + "deleteAllDrugs";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchDrugByNDC";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllNDCs";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchDrugRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllDrugsRequest";

	/** The Constant STMT_INSERT_DP. */
	private static final String STMT_INSERT_DP = NAMESPACE + "insertNDCPrice";

	/** The Constant STMT_UPDATE_DP. */
	private static final String STMT_UPDATE_DP = NAMESPACE + "updateNDCPrice";

	/** The Constant STMT_DELETE_DP. */
	private static final String STMT_DELETE_DP = NAMESPACE + "deleteNDCPriceByNDC";

	/** The Constant STMT_DELETE_DP_SI. */
	private static final String STMT_DELETE_DP_SI = NAMESPACE + "deleteNDCPriceBySexInd";

	/** The Constant STMT_DELETE_ALL_DP. */
	private static final String STMT_DELETE_ALL_DP = NAMESPACE + "deleteAllDrugPrices";

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bar.IDrugBAR#fetchDrugByCode(java.lang.String)
	 */
	@Override
	public Drug fetchDrugByCode(String drugCode)
	{
		return (Drug)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH, drugCode);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IDrugBAR#insertDrug(com.qat.samples.sysmgmt.base.model.Drug)
	 */
	@Override
	public InternalResponse insertDrug(Drug drug)
	{
		InternalResponse response = new InternalResponse();
		// First insert the root
		// If the insert fails it will throw an exception so there is no need to check for the insert failing.
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT, drug, response);

		// Then the Drug Prices.
		if (!ValidationUtil.isNullOrEmpty(drug.getDrugPrices()))
		{
			// For Each DrugPrice...
			for (DrugPrice drugPrice : drug.getDrugPrices())
			{
				// insert DrugPrice
				drugPrice.setParentKey(drug.getCode());
				insertDrugPrice(drugPrice, response);

			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IDrugBAR#updateDrug(com.qat.samples.sysmgmt.base.model.Drug)
	 */
	@Override
	public InternalResponse updateDrug(Drug drug)
	{
		InternalResponse response = new InternalResponse();
		// First update the root if necessary.
		if (!ValidationUtil.isNull(drug.getModelAction())
				&& (drug.getModelAction() == BaseModel.PersistenceActionEnum.UPDATE))
		{
			MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE, drug, response);
		}

		if (!response.isInError())
		{
			updateDrugPrice(drug, response);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IDrugBAR#deleteDrug(com.qat.samples.sysmgmt.base.model.Drug)
	 */
	@Override
	public InternalResponse deleteDrug(Drug drug)
	{
		InternalResponse response = new InternalResponse();
		// First delete the root
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE, drug, response);

		// Then the Drug Prices.
		if (!response.isInError())
		{
			DrugPrice drugPrice = new DrugPrice();
			drugPrice.setParentKey(drug.getCode());
			deleteDrugPrice(drugPrice, response);
			if (response.isInError()
					&& (InternalResponse.BusinessErrorCategory.NoRowsRemoved == response.getBusinessError()))
			{
				// In this case clear the error because there may not have been children to delete and this is valid
				response.clearStatus();
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IDrugBAR#deleteAllDrugs()
	 */
	@Override
	public InternalResponse deleteAllDrugs()
	{
		InternalResponse response = new InternalResponse();
		// First delete all the Drug Roots
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ALL, response);

		// Then the Drug Prices.
		deleteAllDrugPrices(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bar.IDrugBAR#fetchAllDrugs()
	 */
	@Override
	public List<Drug> fetchAllDrugs()
	{
		return MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bar.IDrugBAR#fetchDrugsByRequest(com.qat.samples.sysmgmt.model.request.PagedInquiryRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Drug> fetchDrugsByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Drug> response = new InternalResultsResponse<Drug>();
		PagedResultsBARD.fetchObjectsByRequest(getSqlSession(), request, STMT_FETCH_COUNT, STMT_FETCH_ALL_REQUEST,
				response);
		return response;
	}

	/**
	 * Insert drug price.
	 *
	 * @param drugPrice the drug price
	 * @param response the o response
	 */
	private void insertDrugPrice(DrugPrice drugPrice, InternalResponse response)
	{
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_DP, drugPrice, response);
	}

	/**
	 * Update drug price.
	 *
	 * @param drugPrice the drug price
	 * @param response the o response
	 */
	private void updateDrugPrice(DrugPrice drugPrice, InternalResponse response)
	{
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_DP, drugPrice, response);
	}

	/**
	 * Delete all drug prices.
	 *
	 * @param response the o response
	 */
	private void deleteAllDrugPrices(InternalResponse response)
	{
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ALL_DP, response);
	}

	/**
	 * Delete drug price.
	 *
	 * @param drugPrice the drug price
	 * @param response the o response
	 */
	private void deleteDrugPrice(DrugPrice drugPrice, InternalResponse response)
	{
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DP, drugPrice, response);
	}

	/**
	 * Delete drug price by sex ind.
	 *
	 * @param drugPrice the drug price
	 * @param response the o response
	 */
	private void deleteDrugPriceBySexInd(DrugPrice drugPrice, InternalResponse response)
	{
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_DP_SI, drugPrice, response);
	}

	/**
	 * Update drug price.
	 *
	 * @param drug the drug
	 * @param response the response
	 */
	private void updateDrugPrice(Drug drug, InternalResponse response)
	{
		// Then the Drug Prices.
		if (!ValidationUtil.isNullOrEmpty(drug.getDrugPrices()))
		{
			// For Each DrugPrice...
			for (DrugPrice drugPrice : drug.getDrugPrices())
			{
				drugPrice.setParentKey(drug.getCode());
				switch (drugPrice.getModelAction())
				{
					case UPDATE:
					{
						updateDrugPrice(drugPrice, response);
						break;
					}
					case INSERT:
					{
						insertDrugPrice(drugPrice, response);
						break;
					}
					case DELETE:
					{
						deleteDrugPriceBySexInd(drugPrice, response);
						break;
					}
					default:
					{
						break;
					}
				}
			}
		}
	}
}
