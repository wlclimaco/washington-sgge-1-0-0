package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC;
import com.prosperitasglobal.sendsolv.dac.IDailyCurrencyRateDAC;
import com.prosperitasglobal.sendsolv.dac.IPayerDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.CurrencyAvailability;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.PayerPaymentTypeCurrency;
import com.prosperitasglobal.sendsolv.model.PayerRegion;
import com.prosperitasglobal.sendsolv.model.PaymentType;
import com.prosperitasglobal.sendsolv.model.request.PayerInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PayerDACImpl.
 */
public class PayerDACImpl extends SqlSessionDaoSupport implements IPayerDAC
{
	/** The implementation of the {@link ICurrencyPurchaseDAC} interface. Injected by Spring. */
	private ICurrencyPurchaseDAC currencyPurchaseDAC;

	/** The implementation of the {@link IDailyCurrencyRateDAC} interface. Injected by Spring. */
	private IDailyCurrencyRateDAC dailyCurrencyRateDAC;

	/** The valid sort fields for a payer inquiry. Will be injected by Spring. */
	private Map<String, String> payerInquiryValidSortFields;

	/** The Constant PAYER_NAMESPACE. */
	private static final String PAYER_NAMESPACE = "PayerMap.";

	/** The Constant PAYER_STMT_FETCH_PAYER_BY_ID. */
	private static final String PAYER_STMT_FETCH_PAYER_BY_ID = PAYER_NAMESPACE + "fetchPayerById";

	/** The Constant PAYER_STMT_FETCH_ROW_COUNT. */
	private static final String PAYER_STMT_FETCH_ROW_COUNT = PAYER_NAMESPACE + "fetchPayerRowCount";

	/** The Constant PAYER_STMT_FETCH_BY_REQUEST. */
	private static final String PAYER_STMT_FETCH_BY_REQUEST = PAYER_NAMESPACE + "fetchPayerByRequest";

	/** The Constant PAYER_STMT_INSERT. */
	private static final String PAYER_STMT_INSERT = PAYER_NAMESPACE + "insertPayer";

	/** The Constant PAYER_STMT_UPDATE. */
	private static final String PAYER_STMT_UPDATE = PAYER_NAMESPACE + "updatePayer";

	/** The Constant PAYER_STMT_DELETE. */
	private static final String PAYER_STMT_DELETE = PAYER_NAMESPACE + "deletePayer";

	/** The Constant PAYER_PAYMENT_TYPE_CURRENCY_NAMESPACE. */
	private static final String PAYER_PAYMENT_TYPE_CURRENCY_NAMESPACE = "PayerPaymentTypeCurrencyMap.";

	/** The Constant PAYER_PAYMENT_TYPE_STMT_DELETE. */
	private static final String PAYER_PAYMENT_TYPE_CURRENCY_STMT_DELETE = PAYER_PAYMENT_TYPE_CURRENCY_NAMESPACE
			+ "deletePayerPaymentTypeCurrency";

	/** The Constant PAYER_PAYMENT_TYPE_STMT_INSERT. */
	private static final String PAYER_PAYMENT_TYPE_CURRENCY_STMT_INSERT = PAYER_PAYMENT_TYPE_CURRENCY_NAMESPACE
			+ "insertPayerPaymentTypeCurrency";

	/** The Constant PAYER_REGION_NAMESPACE. */
	private static final String PAYER_REGION_NAMESPACE = "PayerRegionMap.";

	/** The Constant PAYER_REGION_STMT_DELETE. */
	private static final String PAYER_REGION_STMT_DELETE = PAYER_REGION_NAMESPACE + "deletePayerRegion";

	/** The Constant PAYER_REGION_STMT_INSERT. */
	private static final String PAYER_REGION_STMT_INSERT = PAYER_REGION_NAMESPACE + "insertPayerRegion";

	/** The Constant CURRENCY_AVAILABILITY_NAMESPACE. */
	private static final String CURRENCY_AVAILABILITY_NAMESPACE = "CurrencyAvailabilityMap.";

	/** The Constant CURRENCY_AVAILABILITY_STMT_INSERT. */
	private static final String CURRENCY_AVAILABILITY_STMT_INSERT = CURRENCY_AVAILABILITY_NAMESPACE
			+ "insertCurrencyAvailability";

	/** The Constant CURRENCY_AVAILABILITY_STMT_UPDATE. */
	private static final String CURRENCY_AVAILABILITY_STMT_UPDATE = CURRENCY_AVAILABILITY_NAMESPACE
			+ "updateCurrencyAvailability";

	/** The Constant CURRENCY_AVAILABILITY_STMT_DELETE. */
	private static final String CURRENCY_AVAILABILITY_STMT_DELETE = CURRENCY_AVAILABILITY_NAMESPACE
			+ "deleteCurrencyAvailability";

	/** The Constant CURRENCY_AVAILABILITY_STMT_FETCH_ROW_COUNT. */
	private static final String CURRENCY_AVAILABILITY_STMT_FETCH_VERSION = CURRENCY_AVAILABILITY_NAMESPACE
			+ "fetchCurrencyAvailabilityVersionNumber";

	/**
	 * Delete a {@link CurrencyAvailability} from the SendSolv system.
	 * <p>
	 * The delete action will enforce the Optimistic Locking of the row so that an incorrect delete isn't performed. If
	 * the correct version is being deleted, it will delete the {@link CurrencyAvailability}.
	 *
	 * @param currencyAvailability The currency availability to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemoveOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	private int deleteCurrencyAvailability(CurrencyAvailability currencyAvailability, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemoveOL(getSqlSession(), CURRENCY_AVAILABILITY_STMT_DELETE,
						currencyAvailability, CURRENCY_AVAILABILITY_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			currencyAvailability.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Delete a {@link PayerPaymentTypeCurrency} object.
	 *
	 * @param payerPaymentTypeCurrency The {@link PayerPaymentTypeCurrency} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int deletePayerPaymentTypeCurrency(PayerPaymentTypeCurrency payerPaymentTypeCurrency,
			InternalResponse response)
	{
		return QATMyBatisDacHelper
				.doRemove(getSqlSession(), PAYER_PAYMENT_TYPE_CURRENCY_STMT_DELETE, payerPaymentTypeCurrency, response);
	}

	/**
	 * Delete a {@link PayerRegion} object.
	 *
	 * @param payerRegion The {@link PayerRegion} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int deletePayerRegion(PayerRegion payerRegion, InternalResponse response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), PAYER_REGION_STMT_DELETE, payerRegion, response);
	}

	/**
	 * Insert a {@link CurrencyAvailability} object.
	 *
	 * @param currencyAvailability The {@link CurrencyAvailability} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertCurrencyAvailability(CurrencyAvailability currencyAvailability, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CURRENCY_AVAILABILITY_STMT_INSERT, currencyAvailability,
						response);

		if (count == 1)
		{
			currencyAvailability.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link PayerPaymentTypeCurrency} object.
	 *
	 * @param payerPaymentTypeCurrency The {@link PayerPaymentTypeCurrency} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertPayerPaymentTypeCurrency(PayerPaymentTypeCurrency payerPaymentTypeCurrency,
			InternalResponse response)
	{
		return QATMyBatisDacHelper
				.doInsert(getSqlSession(), PAYER_PAYMENT_TYPE_CURRENCY_STMT_INSERT, payerPaymentTypeCurrency, response);
	}

	/**
	 * Insert a {@link PayerRegion} object.
	 *
	 * @param payerRegion The {@link PayerRegion} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertPayerRegion(PayerRegion payerRegion, InternalResponse response)
	{
		return QATMyBatisDacHelper.doInsert(getSqlSession(), PAYER_REGION_STMT_INSERT, payerRegion, response);
	}

	/**
	 * Maintain the payers currency availability.
	 *
	 * @param payer The {@link Payer}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainCurrrencyAvailabilityList(Payer payer, InternalResponse response)
	{
		int count = 0;

		if (!ValidationUtil.isNullOrEmpty(payer.getCurrencyAvailabilityList()))
		{
			// For Each Currency Availability...
			for (CurrencyAvailability currencyAvailability : payer.getCurrencyAvailabilityList())
			{
				// Make sure we set the parent key
				currencyAvailability.setPayerId(payer.getId());

				switch (currencyAvailability.getModelAction())
				{
					case UPDATE:
						count += updateCurrencyAvailability(currencyAvailability, response);
						break;
					case INSERT:
						count += insertCurrencyAvailability(currencyAvailability, response);
						break;
					case DELETE:
						count += deleteCurrencyAvailability(currencyAvailability, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Maintain the payers daily currency rates.
	 *
	 * @param payer The {@link Payer}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainDailyCurrrencyRateList(Payer payer, InternalResponse response)
	{
		int count = 0;

		if (!ValidationUtil.isNullOrEmpty(payer.getDailyCurrencyRateList()))
		{
			// For Each Daily Currency Rate...
			for (DailyCurrencyRate dailyCurrencyRate : payer.getDailyCurrencyRateList())
			{
				// Make sure we set the parent key
				dailyCurrencyRate.setPayerId(payer.getId());
				InternalResponse dailyCurrentyRateResponse = new InternalResponse();

				switch (dailyCurrencyRate.getModelAction())
				{
					case UPDATE:
						dailyCurrentyRateResponse =
								getDailyCurrencyRateDAC().updateDailyCurrencyRate(dailyCurrencyRate);
						break;
					case INSERT:
						dailyCurrentyRateResponse =
								getDailyCurrencyRateDAC().insertDailyCurrencyRate(dailyCurrencyRate);
						break;
					case DELETE:
						dailyCurrentyRateResponse =
								getDailyCurrencyRateDAC().deleteDailyCurrencyRate(dailyCurrencyRate);
						break;
					default:
						break;
				}

				if (dailyCurrentyRateResponse.isInError())
				{
					response.merge(dailyCurrentyRateResponse);
					break;
				}

				count += 1;
			}
		}

		return count;
	}

	/**
	 * Maintain the {@link Payer} associations. A {@link Payer} has 2 main associations that are different than the
	 * normal SendSolv associations. There are 2 different associative database tables (
	 * <code>payer_region and payer_payment_type_currency</code>) that maintain the associations with the {@link Payer}.
	 * The other associations follow the standard association processing.
	 *
	 * @param payer The payer.
	 * @param response The response.
	 * @return The number of inserts performed.
	 */
	private int maintainPayerAssociations(Payer payer, InternalResponse response)
	{
		int count = maintainPayerPaymentTypeCurrency(payer, response);
		count += maintainPayerRegion(payer, response);
		count += maintainDailyCurrrencyRateList(payer, response);
		count += maintainCurrrencyAvailabilityList(payer, response);
		return count;
	}

	/**
	 * Maintain the {@link Payer}, {@link PaymentType}, and {@link Currency} association.
	 *
	 * @param payer The payer.
	 * @param response The response.
	 * @return The number of maintenance actions performed.
	 */
	private int maintainPayerPaymentTypeCurrency(Payer payer, InternalResponse response)
	{
		int count = 0;

		if (!ValidationUtil.isNullOrEmpty(payer.getPaymentTypeList()))
		{
			PayerPaymentTypeCurrency payerPaymentTypeCurrency = null;
			for (PaymentType paymentType : payer.getPaymentTypeList())
			{
				payerPaymentTypeCurrency = new PayerPaymentTypeCurrency();
				payerPaymentTypeCurrency.setPayerId(payer.getId());
				payerPaymentTypeCurrency.setModelAction(paymentType.getModelAction());
				payerPaymentTypeCurrency.setPaymentTypeCode(paymentType.getTypeValue());
				payerPaymentTypeCurrency.setCurrencyCode(paymentType.getCurrency().getCode());
				payerPaymentTypeCurrency.setCreateDateUTC(paymentType.getCreateDateUTC());
				payerPaymentTypeCurrency.setCreateUser(paymentType.getCreateUser());
				payerPaymentTypeCurrency.setModifyDateUTC(paymentType.getModifyDateUTC());
				payerPaymentTypeCurrency.setModifyUser(paymentType.getModifyUser());

				switch (payerPaymentTypeCurrency.getModelAction())
				{
					case INSERT:
						count += insertPayerPaymentTypeCurrency(payerPaymentTypeCurrency, response);
						break;
					case DELETE:
						count += deletePayerPaymentTypeCurrency(payerPaymentTypeCurrency, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Maintain the {@link Payer} to {@link StateProvinceRegion} association.
	 *
	 * @param payer The payer.
	 * @param response The response.
	 * @return The number of inserts performed.
	 */
	private int maintainPayerRegion(Payer payer, InternalResponse response)
	{
		int count = 0;

		if (!ValidationUtil.isNullOrEmpty(payer.getStateProvinceRegionList()))
		{
			PayerRegion payerRegion = null;
			for (StateProvinceRegion stateProvinceRegion : payer.getStateProvinceRegionList())
			{
				payerRegion = new PayerRegion();
				payerRegion.setModelAction(stateProvinceRegion.getModelAction());
				payerRegion.setPayerId(payer.getId());
				payerRegion.setStateProvinceRegionId(stateProvinceRegion.getId());
				payerRegion.setCreateDateUTC(stateProvinceRegion.getCreateDateUTC());
				payerRegion.setCreateUser(stateProvinceRegion.getCreateUser());
				payerRegion.setModifyDateUTC(stateProvinceRegion.getModifyDateUTC());
				payerRegion.setModifyUser(stateProvinceRegion.getModifyUser());

				switch (payerRegion.getModelAction())
				{
					case INSERT:
						count += insertPayerRegion(payerRegion, response);
						break;
					case DELETE:
						count += deletePayerRegion(payerRegion, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Update a {@link CurrencyAvailability} object.
	 *
	 * @param currencyAvailability The {@link CurrencyAvailability} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateCurrencyAvailability(CurrencyAvailability currencyAvailability, InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdateOL(getSqlSession(), CURRENCY_AVAILABILITY_STMT_UPDATE,
						currencyAvailability, CURRENCY_AVAILABILITY_STMT_FETCH_VERSION, response);

		if (count == 1)
		{
			currencyAvailability.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Get the implementation of {@link ICurrencyPurchaseDAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public ICurrencyPurchaseDAC getCurrencyPurchaseDAC()
	{
		return currencyPurchaseDAC;
	}

	/**
	 * Set the implementation of {@link ICurrencyPurchaseDAC} interface. Injected by Spring.
	 *
	 * @param currencyPurchaseDAC The implementation to set.
	 */
	public void setCurrencyPurchaseDAC(ICurrencyPurchaseDAC currencyPurchaseDAC)
	{
		this.currencyPurchaseDAC = currencyPurchaseDAC;
	}

	/**
	 * Get the implementation of {@link IDailyCurrencyRateDAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public IDailyCurrencyRateDAC getDailyCurrencyRateDAC()
	{
		return dailyCurrencyRateDAC;
	}

	/**
	 * Set the implementation of {@link IDailyCurrencyRateDAC} interface. Injected by Spring.
	 *
	 * @param dailyCurrencyRateDAC The implementation to set.
	 */
	public void setDailyCurrencyRateDAC(IDailyCurrencyRateDAC dailyCurrencyRateDAC)
	{
		this.dailyCurrencyRateDAC = dailyCurrencyRateDAC;
	}

	/**
	 * Get the valid sort fields for the payer inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the payer inquiry.
	 */
	public Map<String, String> getPayerInquiryValidSortFields()
	{
		return payerInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the payer inquiry. Attribute injected by Spring.
	 *
	 * @param payerInquiryValidSortFields The valid sort fields for the payer inquiry to set.
	 */
	public void setPayerInquiryValidSortFields(Map<String, String> payerInquiryValidSortFields)
	{
		this.payerInquiryValidSortFields = payerInquiryValidSortFields;
	}

	/**
	 * Delete the {@link Payer} from the SendSolv system.
	 *
	 * @param payer The payer to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemove(org.apache.ibatis.session.SqlSession, String, InternalResponse)
	 */
	@Override
	public InternalResponse deletePayer(Payer payer)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), PAYER_STMT_DELETE, payer, response);
		return response;
	}

	/**
	 * Fetch payer by id.
	 *
	 * @param id the id
	 * @return the internal results response< payer>
	 */
	@Override
	public InternalResultsResponse<Payer> fetchPayerById(Integer id)
	{
		InternalResultsResponse<Payer> response = new InternalResultsResponse<Payer>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PAYER_STMT_FETCH_PAYER_BY_ID, id, response);

		return response;
	}

	/**
	 * Fetch all payers matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link Payer}'s along with information
	 *         about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession,
	 *      com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest, String, String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<Payer> fetchPayerByRequest(PayerInquiryRequest request)
	{
		InternalResultsResponse<Payer> response = new InternalResultsResponse<Payer>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getPayerInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, PAYER_STMT_FETCH_ROW_COUNT,
				PAYER_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Insert a {@link Payer} into the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link Payer}. If that insert is successful, then all of the
	 * composite associations will be looked at and have their model action performed. Before the action is performed on
	 * them, the parent key will be updated to keep them in sync. If an error is taken during any of the actions, the
	 * method will stop and return with the error. It is assumed that the transaction is being maintained elsewhere, and
	 * that it will be rolled back upon getting an error.
	 *
	 * @param payer The payer to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertPayer(Payer payer)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), PAYER_STMT_INSERT, payer, response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainPayerAssociations(payer, response);

		if (!response.isInError() && (insertCount > 0))
		{
			payer.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link Payer} in the SendSolv system, along with all of its composite associated objects where the model
	 * action attribute has been marked.
	 * <p>
	 * Be careful with the associates. The system will attempt to perform the action specified. This could lead to an
	 * abnormal database method being applied to the object. Make sure that the correct model action has been specified
	 * for all of the composite associated objects.
	 *
	 * @param payer The payer to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdate(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse updatePayer(Payer payer)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// First update the root if it is marked for update
		if (payer.getModelAction().equals(PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), PAYER_STMT_UPDATE, payer, response);

			if (response.isInError())
			{
				return response;
			}
		}

		updateCount += maintainPayerAssociations(payer, response);

		if (!response.isInError() && (updateCount > 0))
		{
			payer.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}
}
