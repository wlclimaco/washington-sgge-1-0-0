package com.prosperitasglobal.sendsolv.bac.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.Assert;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC;
import com.prosperitasglobal.sendsolv.bad.MoneyTransferBAD;
import com.prosperitasglobal.sendsolv.dac.IDailyCurrencyRateDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferBatchDAC;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Account;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.FundingSummaryDTO;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAutoApprovalItem;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchDTO;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.criteria.DailyCurrencyRateCriteria;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;
import com.prosperitasglobal.sendsolv.model.request.DailyCurrencyRateInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferAutoApprovalRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferCreateRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferStatusMaintenanceRequest;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferBACImpl.
 */
public class MoneyTransferBACImpl implements IMoneyTransferBAC
{

	/** The account of the money transfer sender. */
	private Account senderAccount;

	/** The origin currency of the transfer. Injected by Spring. */
	private Currency originCurrency;

	/** The origin country of the transfer. Injected by Spring. */
	private Country originCountry;

	/** The PGSi default spread percentage. */
	private BigDecimal spreadPercentage;

	/** The money transfer dac. */
	private IMoneyTransferDAC moneyTransferDAC;

	/** The money transfer batch dac. */
	private IMoneyTransferBatchDAC moneyTransferBatchDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The daily currency rate dac. */
	private IDailyCurrencyRateDAC dailyCurrencyRateDAC;

	/** The note dac. */
	private INoteDAC noteDAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The Constant NUMBER_1. */
	private static final int NUMBER_1 = 1;

	/** The Constant Zero Dollars. */
	private static final BigDecimal ZERO = new BigDecimal("0.00");

	/**
	 * The message property used when problems are encountered updating the dynamic attributes.
	 */
	private static final String PROBLEM_UPDATING_DYNAMIC_ATTRIBUTES =
			"sendsolv.base.moneytransferbacimpl.dynamic.attribute.update.problem";

	/**
	 * Fetch the destination's daily currency rate detail for the {@link TransferSetting}.
	 *
	 * @param transferSetting The {@link TransferSetting}.
	 * @return The daily currency rate detail.
	 */
	private DailyCurrencyRateDetail fetchDailyCurrencyRateDetail(TransferSetting transferSetting)
	{
		DailyCurrencyRateDetail mostCurrentDailyCurrencyRateDetail = null;
		DailyCurrencyRate dailyCurrencyRate = fetchDailyCurrencyRate(transferSetting);

		if (ValidationUtil.isNull(dailyCurrencyRate)
				|| ValidationUtil.isNullOrEmpty(dailyCurrencyRate.getDailyCurrencyRateDetailList()))
		{
			return null;
		}

		for (DailyCurrencyRateDetail dbDailyCurrencyRateDetail : dailyCurrencyRate.getDailyCurrencyRateDetailList())
		{
			if (ValidationUtil.isNull(mostCurrentDailyCurrencyRateDetail))
			{
				mostCurrentDailyCurrencyRateDetail = dbDailyCurrencyRateDetail;
			}
			else
			{
				if (dbDailyCurrencyRateDetail.getCreateDateUTC() >= mostCurrentDailyCurrencyRateDetail
						.getCreateDateUTC())
				{
					mostCurrentDailyCurrencyRateDetail = dbDailyCurrencyRateDetail;
				}
			}
		}

		return mostCurrentDailyCurrencyRateDetail;
	}

	/**
	 * Fetch the {@link DailyCurrencyRate} for the {@link TransferSetting}'s currency and payer.
	 *
	 * @param transferSetting The transfer setting.
	 * @return The daily currency rate for the currency and payer.
	 */
	private DailyCurrencyRate fetchDailyCurrencyRate(TransferSetting transferSetting)
	{
		DailyCurrencyRateCriteria criteria = new DailyCurrencyRateCriteria();
		criteria.setCurrency(transferSetting.getProductPlanApplicability().getCurrency());
		criteria.setPayerId(transferSetting.getProductPlanApplicability().getPayer().getId());
		criteria.setDate(PGSiDateUtil.getCurrentDateMillisUTC());

		DailyCurrencyRateInquiryRequest request = new DailyCurrencyRateInquiryRequest();
		request.setCriteria(criteria);

		InternalResultsResponse<DailyCurrencyRate> fetchResponse =
				getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);

		if (fetchResponse.isInError() || ValidationUtil.isNullOrEmpty(fetchResponse.getResultsList()))
		{
			criteria = new DailyCurrencyRateCriteria();
			criteria.setCurrency(transferSetting.getProductPlanApplicability().getCurrency());
			criteria.setPayerId(transferSetting.getProductPlanApplicability().getPayer().getId());
			criteria.setDate(null);

			request = new DailyCurrencyRateInquiryRequest();
			request.setCriteria(criteria);

			fetchResponse = getDailyCurrencyRateDAC().fetchDailyCurrencyRateByRequest(request);

			if (fetchResponse.isInError() || ValidationUtil.isNullOrEmpty(fetchResponse.getResultsList()))
			{
				return null;
			}
		}

		return fetchMostCurrentDailyCurrencyRate(fetchResponse.getResultsList());
	}

	/**
	 * Fetch the most current destination daily currency rate.
	 *
	 * @param dailyCurrencyRateList The list of daily currency rates.
	 * @return The most current destination daily currency rate.
	 */
	private DailyCurrencyRate fetchMostCurrentDailyCurrencyRate(List<DailyCurrencyRate> dailyCurrencyRateList)
	{
		DailyCurrencyRate dailyCurrencyRate = null;

		for (DailyCurrencyRate dbDailyCurrencyRate : dailyCurrencyRateList)
		{
			if (ValidationUtil.isNull(dailyCurrencyRate))
			{
				dailyCurrencyRate = dbDailyCurrencyRate;
			}
			else
			{
				if (PGSiDateUtil.compareDates(dbDailyCurrencyRate.getValidForDate(),
						dailyCurrencyRate.getValidForDate()) >= 0)
				{
					dailyCurrencyRate = dbDailyCurrencyRate;
				}
			}
		}

		return dailyCurrencyRate;
	}

	/**
	 * This method will merge the parameter <code>mergeThisResponse</code> into the parameter <code>response</code>.
	 * <p>
	 * The <code>response</code> will end up matching that of the <code></code> with the exception that the
	 * <code>resultsList</code> will not be merged.
	 *
	 * @param response The response.
	 * @param mergeThisResponse The response to merge into the response.
	 */
	private void mergeResponse(InternalResultsResponse<?> response, InternalResultsResponse<?> mergeThisResponse)
	{
		response.merge(mergeThisResponse);
		response.getResultsSetInfo().setMoreRowsAvailable(mergeThisResponse.getResultsSetInfo().isMoreRowsAvailable());
		response.getResultsSetInfo().setPageSize(mergeThisResponse.getResultsSetInfo().getPageSize());
		response.getResultsSetInfo().setStartPage(mergeThisResponse.getResultsSetInfo().getStartPage());
		response.getResultsSetInfo().setTotalRowsAvailable(
				mergeThisResponse.getResultsSetInfo().getTotalRowsAvailable());
	}

	/**
	 * This method will update the dynamic attributes of all the {@link MoneyTransfer} objects in the {@link List}
	 * passed in as the parameter.
	 * <p>
	 * The dynamic attributes are attributes that are updated every time the object is fetched from the database. This
	 * is done, because the value of these attributes are dependent on the fluctuation of the
	 * {@link DailyCurrencyRateDetail} database. Once the {@link MoneyTransfer} is in a status of
	 * {@link MoneyTransferStatusEnum#APPROVED}, the dynamic updating will be stopped, and the attributes will remain
	 * constant.
	 *
	 * @param currentResponse The current response of {@link MoneyTransfer} objects before the call to this method.
	 * @return The list of money transfers updated.
	 */
	private InternalResultsResponse<MoneyTransfer> updateDynamicAttributes(
			InternalResultsResponse<MoneyTransfer> currentResponse)
	{
		// Setup a new response that will contain the updated money transfers.
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		mergeResponse(response, currentResponse);

		InternalResultsResponse<MoneyTransfer> updateResponse =
				updateDynamicAttributes(currentResponse.getResultsList());

		if (updateResponse.isInError())
		{
			response.merge(updateResponse);
			return response;
		}

		response.addResults(updateResponse.getResultsList());

		Assert.isTrue(currentResponse.getResultsList().size() == response.getResultsList().size(), getClass()
				.getSimpleName() + ": Error in updating the dynamic attributes of the MoneyTransfer!");

		return response;
	}

	/**
	 * This method will update the dynamic attributes of all the {@link MoneyTransfer} objects in the {@link List}
	 * passed in as the parameter.
	 * <p>
	 * The dynamic attributes are attributes that are updated every time the object is fetched from the database. This
	 * is done, because the value of these attributes are dependent on the fluctuation of the
	 * {@link DailyCurrencyRateDetail} database. Once the {@link MoneyTransfer} is in a status of
	 * {@link MoneyTransferStatusEnum#APPROVED}, the dynamic updating will be stopped, and the attributes will remain
	 * constant.
	 *
	 * @param moneyTransferList The list of {@link MoneyTransfer} objects to update.
	 * @return The list of money transfers updated.
	 */
	private InternalResultsResponse<MoneyTransfer> updateDynamicAttributes(List<MoneyTransfer> moneyTransferList)
	{
		// Setup a new response that will contain the updated money transfers.
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();

		InternalResultsResponse<MoneyTransfer> moneyTransferUpdateResponse = null;

		for (MoneyTransfer moneyTransfer : moneyTransferList)
		{
			moneyTransferUpdateResponse = updateDynamicAttributes(moneyTransfer);

			if (moneyTransferUpdateResponse.isInError())
			{
				return moneyTransferUpdateResponse;
			}

			response.addResults(moneyTransferUpdateResponse.getResultsList());
		}

		return response;
	}

	/**
	 * This method will update the dynamic attributes of the {@link MoneyTransfer} object.
	 * <p>
	 * The dynamic attributes are attributes that are updated every time the object is fetched from the database. This
	 * is done, because the value of these attributes are dependent on the fluctuation of the
	 * {@link DailyCurrencyRateDetail} database. Once the {@link MoneyTransfer} is in a status of
	 * {@link MoneyTransferStatusEnum#APPROVED}, the dynamic updating will be stopped, and the attributes will remain
	 * constant.
	 *
	 * @param moneyTransfer The {@link MoneyTransfer} object to update.
	 * @return The list of money transfers updated.
	 */
	private InternalResultsResponse<MoneyTransfer> updateDynamicAttributes(MoneyTransfer moneyTransfer)
	{
		/* Setup a new response that will contain the updated money transfer. */
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();
		response.addResult(moneyTransfer);

		DailyCurrencyRateDetail dailyCurrencyRateDetail =
				fetchDailyCurrencyRateDetail(moneyTransfer.getTransferSetting());

		/* Update the dynamic attributes of the money transfer. */
		MoneyTransferBAD.updateDynamicAttributes(moneyTransfer, originCurrency, originCountry, dailyCurrencyRateDetail);

		/*
		 * If no updated was done, then return the money transfer as it was. No update will performed to the
		 * database.
		 */
		if (moneyTransfer.getModelAction() != PersistanceActionEnum.UPDATE)
		{
			return response;
		}

		/*
		 * The updated money transfer must be written back out the database to capture exactly what is being returned
		 * from the method call. This method call will do the update, and then fetch it and return it.
		 */
		response = getMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransfer);

		if (response.isInError())
		{
			response.addMessage(PROBLEM_UPDATING_DYNAMIC_ATTRIBUTES, MessageSeverity.Fatal, MessageLevel.Object,
					new Object[] {moneyTransfer.getId(), response.getStatus()});
			return response;
		}

		return response;
	}

	/**
	 * This method will update the dynamic attributes of all the {@link MoneyTransfer} objects associated with the
	 * {@link MoneyTransferBatch} passed in as the parameter.
	 * <p>
	 * The dynamic attributes are attributes that are updated every time the object is fetched from the database. This
	 * is done, because the value of these attributes are dependent on the fluctuation of the
	 * {@link DailyCurrencyRateDetail} database. Once the {@link MoneyTransfer} is in a status of
	 * {@link MoneyTransferStatusEnum#APPROVED}, the dynamic updating will be stopped, and the attributes will remain
	 * constant.
	 *
	 * @param moneyTransferBatch The {@link MoneyTransferBatch} object containing the {@link List} of
	 *            {@link MoneyTransfer}s to update.
	 * @return The list of money transfer batches updated.
	 */
	private InternalResultsResponse<MoneyTransferBatch> updateDynamicAttributes(MoneyTransferBatch moneyTransferBatch)
	{
		// Setup a new response that will contain the updated money transfer batch.
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		response.addResult(moneyTransferBatch);

		InternalResultsResponse<MoneyTransfer> moneyTransferUpdateResponse =
				updateDynamicAttributes(moneyTransferBatch.getMoneyTransferList());

		if (moneyTransferUpdateResponse.isInError())
		{
			response.merge(moneyTransferUpdateResponse);
			return response;
		}

		// Replace the old list of money transfers, with the new list of money transfers that have been updated.
		moneyTransferBatch.setMoneyTransferList(moneyTransferUpdateResponse.getResultsList());
		return response;
	}

	/**
	 * This method will update the dynamic attributes of all the {@link MoneyTransfer} objects associated with the
	 * {@link MoneyTransferBatch}s found in the {@link List} passed in as the parameter.
	 * <p>
	 * The dynamic attributes are attributes that are updated every time the object is fetched from the database. This
	 * is done, because the value of these attributes are dependent on the fluctuation of the
	 * {@link DailyCurrencyRateDetail} database. Once the {@link MoneyTransfer} is in a status of
	 * {@link MoneyTransferStatusEnum#APPROVED}, the dynamic updating will be stopped, and the attributes will remain
	 * constant.
	 *
	 * @param currentResponse The current response of {@link MoneyTransferBatch} objects before the call to this method.
	 * @return The list of money transfer batches updated.
	 */
	private InternalResultsResponse<MoneyTransferBatch> updateDynamicAttributesList(
			InternalResultsResponse<MoneyTransferBatch> currentResponse)
	{
		// Setup a new response that will contain the list of updated money transfer batches.
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		mergeResponse(response, currentResponse);

		InternalResultsResponse<MoneyTransferBatch> moneyTransferBatchUpdateResponse = null;

		for (MoneyTransferBatch moneyTransferBatch : currentResponse.getResultsList())
		{
			moneyTransferBatchUpdateResponse = updateDynamicAttributes(moneyTransferBatch);

			if (moneyTransferBatchUpdateResponse.isInError())
			{
				return moneyTransferBatchUpdateResponse;
			}

			response.addResults(moneyTransferBatchUpdateResponse.getResultsList());
		}

		Assert.isTrue(currentResponse.getResultsList().size() == response.getResultsList().size(), getClass()
				.getSimpleName() + ": Error in updating the dynamic attributes of the MoneyTransferBatch!");

		return response;
	}

	/**
	 * Gets the account of the sender.
	 *
	 * @return The account.
	 */
	public Account getSenderAccount()
	{
		return senderAccount;
	}

	/**
	 * Sets the account of the sender.
	 *
	 * @param senderAccount The account to set.
	 */
	public void setSenderAccount(Account senderAccount)
	{
		this.senderAccount = senderAccount;
	}

	/**
	 * Gets the origin country for the transfer.
	 *
	 * @return The country.
	 */
	public Country getOriginCountry()
	{
		return originCountry;
	}

	/**
	 * Sets the origin country for the transfer. Injected by Spring.
	 *
	 * @param originCountry The country to set.
	 */
	public void setOriginCountry(Country originCountry)
	{
		this.originCountry = originCountry;
	}

	/**
	 * Gets the origin currency for the transfer.
	 *
	 * @return The currency.
	 */
	public Currency getOriginCurrency()
	{
		return originCurrency;
	}

	/**
	 * Sets the origin currency for the transfer. Injected by Spring.
	 *
	 * @param originCurrency The currency to set.
	 */
	public void setOriginCurrency(Currency originCurrency)
	{
		this.originCurrency = originCurrency;
	}

	/**
	 * Gets the PGSi default spread percentage. Injected by Spring.
	 *
	 * @return The default spread.
	 */
	public BigDecimal getSpreadPercentage()
	{
		return spreadPercentage;
	}

	/**
	 * Sets the PGSi default spread percentage. Injected by Spring.
	 *
	 * @param spreadPercentage The spread to set.
	 */
	public void setSpreadPercentage(BigDecimal spreadPercentage)
	{
		this.spreadPercentage = spreadPercentage;
	}

	/**
	 * Gets the daily currency rate dac.
	 *
	 * @return the daily currency rate dac
	 */
	public IDailyCurrencyRateDAC getDailyCurrencyRateDAC()
	{
		return dailyCurrencyRateDAC;
	}

	/**
	 * Sets the daily currency rate dac.
	 *
	 * @param dailyCurrencyRateDAC the daily currency rate dac
	 */
	public void setDailyCurrencyRateDAC(IDailyCurrencyRateDAC dailyCurrencyRateDAC)
	{
		this.dailyCurrencyRateDAC = dailyCurrencyRateDAC;
	}

	/**
	 * Gets the money transfer batch dac.
	 *
	 * @return the money transfer batch dac
	 */
	public IMoneyTransferBatchDAC getMoneyTransferBatchDAC()
	{
		return moneyTransferBatchDAC;
	}

	/**
	 * Sets the money transfer batch dac.
	 *
	 * @param moneyTransferBatchDAC the money transfer batch dac
	 */
	public void setMoneyTransferBatchDAC(IMoneyTransferBatchDAC moneyTransferBatchDAC)
	{
		this.moneyTransferBatchDAC = moneyTransferBatchDAC;
	}

	/**
	 * Gets the money transfer dac.
	 *
	 * @return the money transfer dac
	 */
	public IMoneyTransferDAC getMoneyTransferDAC()
	{
		return moneyTransferDAC;
	}

	/**
	 * Sets the money transfer dac.
	 *
	 * @param moneyTransferDAC the money transfer dac
	 */
	public void setMoneyTransferDAC(IMoneyTransferDAC moneyTransferDAC)
	{
		this.moneyTransferDAC = moneyTransferDAC;
	}

	/**
	 * Gets the note dac.
	 *
	 * @return the noteDAC
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Gets the participant id dac.
	 *
	 * @return the participant id dac
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Sets the note dac.
	 *
	 * @param noteDAC the noteDAC to set
	 */
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participant id dac
	 */
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/**
	 * @return the personDAC
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * @param personDAC the personDAC to set
	 */
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#createMoneyTransfer(com.prosperitasglobal.sendsolv.
	 * model.request.MoneyTransferCreateRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> createMoneyTransfer(MoneyTransferCreateRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = new InternalResultsResponse<MoneyTransfer>();

		MoneyTransfer moneyTransfer =
				MoneyTransferBAD.createMoneyTransfer(request.getMoneyTransferBatch(), request.getTransferSetting(),
						getOriginCurrency(), getOriginCountry(), getSpreadPercentage());
		moneyTransfer.setSenderAccount(getSenderAccount());

		InternalResultsResponse<MoneyTransferParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextMoneyTransferParticipantId();

		if (participantIdResponse.isInError())
		{
			// merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		else
		{
			moneyTransfer.setKey(CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
			response.addResult(moneyTransfer);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#createMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.LocationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> createMoneyTransferBatch(MoneyTransferBatchCreateRequest request)
	{
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();

		MoneyTransferBatch moneyTransferBatch = MoneyTransferBAD.createMoneyTransferBatch(request.getLocation(),
				request.getPaymentPreparationDate(), request.getPayDate(), getOriginCurrency(), getOriginCountry());

		InternalResultsResponse<MoneyTransferBatchParticipantId> participantIdResponse =
				getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId();
		if (participantIdResponse.isInError())
		{
			// merge in the errors for fetching the next participant id.
			response.merge(participantIdResponse);
			response.setStatus(participantIdResponse.getStatus());
			return response;
		}
		else
		{
			moneyTransferBatch.setKey(CommonRoutines.formatParticipantId(participantIdResponse.getFirstResult()));
			response.addResult(moneyTransferBatch);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#deleteMoneyTransfer(com.prosperitasglobal.sendsolv.model
	 * .request.MoneyTransferMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteMoneyTransfer(MoneyTransferMaintenanceRequest request)
	{
		return getMoneyTransferDAC().deleteMoneyTransfer(request.getMoneyTransfer());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#deleteMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.MoneyTransferBatchMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request)
	{
		return getMoneyTransferBatchDAC().deleteMoneyTransferBatch(request.getMoneyTransferBatch());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#fetchMoneyTransferById(com.prosperitasglobal.cbof.model.
	 * request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferById(FetchByIdRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferById(request.getId());

		if (!response.isInError())
		{
			return updateDynamicAttributes(response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#fetchMoneyTransferBatchById(com.prosperitasglobal.cbof.model
	 * .request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchById(FetchByIdRequest request)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId());

		if (!response.isInError())
		{
			return updateDynamicAttributesList(response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#fetchMoneyTransferByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.MoneyTransferInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> fetchMoneyTransferByRequest(MoneyTransferInquiryRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);

		if (!response.isInError())
		{
			return updateDynamicAttributes(response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#fetchMoneyTransferBatchByRequest
	 * (com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> fetchMoneyTransferBatchByRequest(
			MoneyTransferBatchInquiryRequest request)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchByRequest(request);

		if (!response.isInError())
		{
			return updateDynamicAttributesList(response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#insertMoneyTransfer(com.prosperitasglobal.sendsolv.model
	 * .request.MoneyTransferMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertMoneyTransfer(MoneyTransferMaintenanceRequest request)
	{
		return getMoneyTransferDAC().insertMoneyTransfer(request.getMoneyTransfer());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#insertMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.MoneyTransferBatchMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request)
	{
		return getMoneyTransferBatchDAC().insertMoneyTransferBatch(request.getMoneyTransferBatch());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#updateMoneyTransfer(com.prosperitasglobal.sendsolv.model
	 * .request.MoneyTransferMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateMoneyTransfer(MoneyTransferMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		for (MoneyTransfer moneyTransfer : request.getMoneyTransferList())
		{
			if (hasApprovedStatus(moneyTransfer.getStatusList()))
			{
				UserContext userContext = request.getUserContext();
				if (!userContext.getUserId().equals(moneyTransfer.getReleaseUser()))
				{
					moneyTransfer.setReleaseUser(userContext.getUserId());
					if (PersistanceActionEnum.NONE.equals(moneyTransfer.getModelAction()))
					{
						moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
					}
				}
			}

			response = getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);

			if (response.isInError())
			{
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#updateMoneyTransferBatch(com.prosperitasglobal.sendsolv.
	 * model.request.MoneyTransferBatchMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateMoneyTransferBatch(MoneyTransferBatchMaintenanceRequest request)
	{
		MoneyTransferBatch batch = request.getMoneyTransferBatch();

		if (hasReleasedBatchStatus(request))
		{
			UserContext userContext = request.getUserContext();
			if (!userContext.getUserId().equals(batch.getReleaseUser()))
			{
				batch.setReleaseUser(userContext.getUserId());
				if (PersistanceActionEnum.NONE.equals(batch.getModelAction()))
				{
					batch.setModelAction(PersistanceActionEnum.UPDATE);
				}
			}
		}

		return getMoneyTransferBatchDAC().updateMoneyTransferBatch(batch);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#insertMoneyTransferBatchStatus
	 * (com.prosperitasglobal.sendsolv.model.request.MoneyTransferBatchStatusMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertMoneyTransferBatchStatus(MoneyTransferBatchStatusMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		// The request can have more than one money transfer batch status due to the "bulk action".
		for (MoneyTransferBatchStatus moneyTransferBatchStatus : request.getMoneyTransferBatchStatusList())
		{
			/*
			 * Verify if the MoneyTransferBatch is already in the status that the application is trying to include, if
			 * so, the current status will be kept, otherwise a new one will be included.
			 */
			if (!isMoneyTransferBatchAlreadyOnThisStatus(moneyTransferBatchStatus.getStatus(),
					moneyTransferBatchStatus.getMoneyTransferBatchId(), response))
			{
				response = getMoneyTransferBatchDAC().insertMoneyTransferBatchStatus(moneyTransferBatchStatus);

				if (response.isInError())
				{
					return response;
				}

				// In case of this status has one note, one register will be included with that information for the
				// money
				// transfer batch.
				if (!ValidationUtil.isNullOrEmpty(request.getNote()))
				{
					InternalResultsResponse<Note> result =
							getNoteDAC().insertNote(
									createNote(moneyTransferBatchStatus.getMoneyTransferBatchId(),
											BusinessTypeEnum.MONEY_TRANSFER_BATCH, request.getNote(),
											request.getUserContext()));
					response.merge(result);

					if (result.isInError())
					{
						return response;
					}
				}

				// The release user property needs to be updated when a user releases a MoneyTransferBatch
				// This is the last time a "real" user touches it before the batch submission may take it.
				if (isReleasedBatchStatus(moneyTransferBatchStatus))
				{
					InternalResponse result =
							updateMoneyTransferBatchReleaseUser(moneyTransferBatchStatus, request.getUserContext());

					response.merge(result);

					if (result.isInError())
					{
						return response;
					}
				}
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#fetchMoneyTransferBatchWithSummaryById(com.prosperitasglobal
	 * .cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatchDTO> fetchMoneyTransferBatchWithSummaryById(
			FetchByIdRequest request)
	{
		MoneyTransferBatchDTO moneyTransferBatchDTO = new MoneyTransferBatchDTO();
		InternalResultsResponse<MoneyTransferBatch> response = new InternalResultsResponse<MoneyTransferBatch>();
		InternalResultsResponse<MoneyTransferBatchDTO> responseDTO =
				new InternalResultsResponse<MoneyTransferBatchDTO>();

		/*
		 * get the money transfer batch. This call must be made to the fetch method found in this class, not directly
		 * to the DAC. The reason is that dynamic attributes are updated in this BAC, and to insure we get the right
		 * data back, call this classes method.
		 */
		response = getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getId());

		if (response.isInError())
		{
			responseDTO.merge(response);
			return responseDTO;
		}

		moneyTransferBatchDTO.setMoneyTransferBatch(response.getFirstResult());
		moneyTransferBatchDTO.setFundingSummary(createFundingSummaryDTO(response.getFirstResult()));

		responseDTO.addResult(moneyTransferBatchDTO);
		return responseDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#insertMoneyTransferStatus(com.prosperitasglobal.sendsolv
	 * .model.request.MoneyTransferStatusMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertMoneyTransferStatus(MoneyTransferStatusMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		// The request can have more than one money transfer status due to the "bulk action" (i.e: The user should be
		// able to approve/disapprove a whole batch or individual transactions.
		for (MoneyTransferStatus moneyTransferStatus : request.getMoneyTransferStatusList())
		{
			/*
			 * Verify if the MoneyTransfer is already in the status that the application is trying to include, if so,
			 * the current status will be kept, otherwise a new one will be included.
			 */
			if (!isMoneyTransferAlreadyOnThisStatus(moneyTransferStatus.getStatus(),
					moneyTransferStatus.getMoneyTransferId(), response))
			{
				if (response.isInError())
				{
					return response;
				}

				response = getMoneyTransferDAC().insertMoneyTransferStatus(moneyTransferStatus);

				if (response.isInError())
				{
					return response;
				}

				// In case of this status has one note, one register will be included with that information for the
				// money
				// transfer batch.
				if (!ValidationUtil.isNullOrEmpty(request.getNote()))
				{
					InternalResultsResponse<Note> result =
							getNoteDAC().insertNote(
									createNote(moneyTransferStatus.getMoneyTransferId(),
											BusinessTypeEnum.MONEY_TRANSFER,
											request.getNote(), request.getUserContext()));
					response.merge(result);

					if (result.isInError())
					{
						return response;
					}
				}

				// The release user property needs to be updated when a user approves a MoneyTransfer
				// This is the last time a "real" user touches it before the batch submission may take it.
				if (isApprovedStatus(moneyTransferStatus))
				{
					InternalResponse result =
							updateMoneyTransferReleaseUser(moneyTransferStatus, request.getUserContext());
					response.merge(result);

					if (result.isInError())
					{
						return response;
					}
				}
			}

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#createOnOffMoneyTransferBatch(com.prosperitasglobal.sendsolv
	 * .model.request.MoneyTransferBatchCreateRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatch> createOnOffMoneyTransferBatch(
			MoneyTransferBatchCreateRequest request)
	{
		InternalResultsResponse<MoneyTransferBatch> response = createMoneyTransferBatch(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResponse statusResponse =
				insertMoneyTransferBatchStatus(createMoneyTransferBatchStatusMaintenanceRequest(
						response.getFirstResult(), request.getUserContext()));

		response.merge(statusResponse);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IMoneyTransferBAC#createOnOffMoneyTransfer(com.prosperitasglobal.sendsolv.
	 * model.request.MoneyTransferCreateRequest)
	 */
	@Override
	public InternalResultsResponse<MoneyTransfer> createOnOffMoneyTransfer(MoneyTransferCreateRequest request)
	{
		InternalResultsResponse<MoneyTransfer> response = createMoneyTransfer(request);

		if (response.isInError())
		{
			return response;
		}

		InternalResultsResponse<MoneyTransferBatch> batchResponse =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getMoneyTransferBatch().getId());

		if (batchResponse.isInError())
		{
			response.merge(batchResponse);
			return response;
		}

		if (ValidationUtil.isNullOrEmpty(batchResponse.getFirstResult().getMoneyTransferList()))
		{
			return response;
		}

		BigDecimal totalOriginAmount = new BigDecimal("0.00");

		for (MoneyTransfer moneyTransfer : batchResponse.getFirstResult().getMoneyTransferList())
		{
			totalOriginAmount = totalOriginAmount.add(moneyTransfer.getOriginAmount().getAmount());
		}

		batchResponse.getFirstResult().getOriginAmount().setAmount(totalOriginAmount);
		batchResponse.getFirstResult().setModelAction(PersistanceActionEnum.UPDATE);

		InternalResponse updateResponse =
				getMoneyTransferBatchDAC().updateMoneyTransferBatch(batchResponse.getFirstResult());
		response.merge(updateResponse);

		return response;

	}

	@Override
	public InternalResultsResponse<Integer> fetchMoneyTransferAutoApprovalByTransferSetting(
			MoneyTransferAutoApprovalRequest request)
	{
		InternalResultsResponse<Integer> resultsResponse = new InternalResultsResponse<Integer>();

		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(request.getMoneyTransferBatchId());

		if (response.isInError())
		{
			resultsResponse.merge(response);
			return resultsResponse;
		}

		if (!ValidationUtil.isNullOrEmpty(response.getFirstResult().getMoneyTransferList()))
		{
			List<MoneyTransferAutoApprovalItem> moneyTransferAutoApprovalItemList =
					new ArrayList<MoneyTransferAutoApprovalItem>();

			for (MoneyTransfer moneyTransfer : response.getFirstResult().getMoneyTransferList())
			{
				if (!ValidationUtil.isNull(moneyTransfer.getOriginAmount())
						&& !ValidationUtil.isNull(moneyTransfer.getTransferSetting()))
				{
					// -- create the MoneyTransferAutoApprovalItem that will hold all the informations that are needed
					MoneyTransferAutoApprovalItem autoApprovalItem = new MoneyTransferAutoApprovalItem();

					// -- get the total value of pay per period for the transaction's member.
					BigDecimal totalPerPeriod =
							fetchTotalPayPerPeriodByMember(moneyTransfer.getTransferSetting().getMemberId());

					// -- verify if the total per period is greater then the transfer value.
					if (!ValidationUtil.isNull(moneyTransfer.getOriginAmount().getAmount())
							&& (totalPerPeriod.setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(
									moneyTransfer.getOriginAmount().getAmount().setScale(2, BigDecimal.ROUND_HALF_UP))
								== 1))
					{
						autoApprovalItem.setId(moneyTransfer.getId());
						autoApprovalItem.setIsNetPayGreaterTransValue(Boolean.TRUE);
					}

					// -- get the previous transaction for that member.
					MoneyTransfer previousMoneyTransfer =
							fetchPreviousMoneyTransferByTransferSetting(moneyTransfer.getTransferSetting().getId(),
									moneyTransfer.getCreateDateUTC());

					if (!ValidationUtil.isNull(previousMoneyTransfer))
					{
						// -- verify if the previous transaction has the status == approved.
						if (!ValidationUtil.isNullOrEmpty(previousMoneyTransfer.getStatusList())
								&& (previousMoneyTransfer.getStatusList()
										.get(previousMoneyTransfer.getStatusList().size() - 1)
										.getStatus() == MoneyTransferStatusEnum.APPROVED))
						{
							autoApprovalItem.setId(moneyTransfer.getId());
							autoApprovalItem.setIsPreviousTransactionApproved(Boolean.TRUE);
						}

						// -- verify if the previous transaction has the same amount of the current one.
						if (!ValidationUtil.isNull(previousMoneyTransfer.getOriginAmount().getAmount())
								&& (previousMoneyTransfer
										.getOriginAmount()
										.getAmount()
										.setScale(2, BigDecimal.ROUND_HALF_UP)
										.compareTo(moneyTransfer.getOriginAmount().getAmount()
												.setScale(2, BigDecimal.ROUND_HALF_UP)) == 0))
						{
							autoApprovalItem.setId(moneyTransfer.getId());
							autoApprovalItem.setIsSameAmountPreviousTransaction(Boolean.TRUE);
						}
					}

					if (!ValidationUtil.isNullOrZero(autoApprovalItem.getId()))
					{
						moneyTransferAutoApprovalItemList.add(autoApprovalItem);
					}
				}
			}

			resultsResponse.addResults(verifyIdsToAutoApproval(request, moneyTransferAutoApprovalItemList));
		}

		return resultsResponse;
	}

	/**
	 * Creates the money transfer batch status maintenance request.
	 *
	 * @param moneyTransferBatch the money transfer batch
	 * @param userContext the user context
	 * @return the money transfer batch status maintenance request
	 */
	private MoneyTransferBatchStatusMaintenanceRequest createMoneyTransferBatchStatusMaintenanceRequest(
			MoneyTransferBatch moneyTransferBatch, UserContext userContext)
	{
		MoneyTransferBatchStatusMaintenanceRequest statusRequest =
				new MoneyTransferBatchStatusMaintenanceRequest();

		MoneyTransferBatchStatus moneyTransferBatchStatus = new MoneyTransferBatchStatus();
		moneyTransferBatchStatus.setStatus(MoneyTransferBatchStatusEnum.PENDING_APPROVAL);
		moneyTransferBatchStatus.setMoneyTransferBatchId(moneyTransferBatch.getId());
		moneyTransferBatchStatus.setCreateDateUTC(moneyTransferBatch.getCreateDateUTC());
		moneyTransferBatchStatus.setCreateUser(moneyTransferBatch.getCreateUser());

		statusRequest.setMoneyTransferBatchStatusList(Arrays.asList(moneyTransferBatchStatus));
		statusRequest.setUserContext(userContext);

		return statusRequest;
	}

	/**
	 * Create the Funding Summary with all the calculations (Batch Total (USD), Payroll Amount (USD), Transfer Total
	 * (USD), Prefunds (USD) and Postfunds (USD)).
	 *
	 * @param moneyTranferBatch The money transfer batch.
	 * @return FundingSummaryDTO The funding summary dto.
	 */
	private FundingSummaryDTO createFundingSummaryDTO(MoneyTransferBatch moneyTranferBatch)
	{
		BigDecimal batchTotal = ZERO;
		BigDecimal payrollAmount = ZERO;
		BigDecimal transferTotal = ZERO;
		BigDecimal prefunds = ZERO;
		BigDecimal postfunds = ZERO;

		for (MoneyTransfer moneyTransfer : moneyTranferBatch.getMoneyTransferList())
		{
			if (!ValidationUtil.isNull(moneyTransfer.getOriginAmount())
					&& !ValidationUtil.isNull(moneyTransfer.getOriginAmount().getAmount())
					&& !ValidationUtil.isNullOrEmpty(moneyTransfer.getStatusList()))
			{
				/*
				 * Sum of total amount of all transactions
				 */
				batchTotal = batchTotal.add(moneyTransfer.getOriginAmount().getAmount());

				/*
				 * Sum of total amount of all approved transactions (i.e. the amount that should be sent to the ACH and
				 * needs to be funded in foreign currency).
				 */
				if (isTransactionApproved(moneyTransfer)
						|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.ON_HOLD)))
				{
					payrollAmount = payrollAmount.add(moneyTransfer.getOriginAmount().getAmount());
				}

				/*
				 * Sum of total amount of all approved transactions (i.e. the amount that should be sent to the ACH and
				 * needs to be funded in foreign currency).
				 */
				if (isTransactionApproved(moneyTransfer))
				{
					transferTotal = transferTotal.add(moneyTransfer.getOriginAmount().getAmount());
				}

				/*
				 * Sum of total amount of all approved transactions belonging to prefund payers. Show only for batches
				 * in Pending Approval and PGSi Prefunding.
				 */
				if ((moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.PENDING_APPROVAL))
						&& (moneyTransfer.getTransferSetting().getProductPlanApplicability().getPayer()
								.isPostfundAllowed()))
				{
					prefunds = prefunds.add(moneyTransfer.getOriginAmount().getAmount());
				}

				/*
				 * Sum of total amount of all submitted transactions belonging to postfund payers. Show only for batches
				 * in
				 * ACH Processing/ACH Errors and PGSi Postfunding
				 */
				if ((moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.ORDER_SUBMITTED))
						&& (moneyTransfer.getTransferSetting().getProductPlanApplicability().getPayer()
								.isPostfundAllowed()))
				{
					postfunds = postfunds.add(moneyTransfer.getOriginAmount().getAmount());
				}
			}
		}

		return new FundingSummaryDTO(batchTotal,
				payrollAmount,
				transferTotal,
				prefunds,
				postfunds);
	}

	/**
	 * Sum the total per period by Member.
	 *
	 * @param memberId the member id
	 * @return BigDecimal The total pay per period by member.
	 */
	private BigDecimal fetchTotalPayPerPeriodByMember(Integer memberId)
	{
		BigDecimal total = ZERO;

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(new FetchByIdRequest(memberId));

		if (response.isInError())
		{
			return total;
		}

		if (!ValidationUtil.isNull(response.getFirstResult())
				&& (!ValidationUtil.isNullOrEmpty(response.getFirstResult().getEmploymentInfoList())))
		{
			for (EmploymentInfo employmentInfo : response.getFirstResult().getEmploymentInfoList())
			{
				if (!ValidationUtil.isNull(employmentInfo.getPayPerPeriod()))
				{
					total = total.add(employmentInfo.getPayPerPeriod());
				}
			}
		}

		return total;
	}

	/**
	 * Fetch the previous transaction for the member.
	 *
	 * @param member The member.
	 * @param createDateUTC the create date utc
	 * @return The previous money transfer for the member.
	 */
	private MoneyTransfer fetchPreviousMoneyTransferByTransferSetting(Integer transferSettingId, Long createDateUTC)
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();
		MoneyTransferCriteria moneyTransferCriteria = new MoneyTransferCriteria();
		moneyTransferCriteria.setCreateDateUTC(createDateUTC);
		moneyTransferCriteria.setTransferSettingId(transferSettingId);
		request.setCriteria(moneyTransferCriteria);
		request.addSortExpressions(new SortExpression("createDateUTC", Direction.Descending));
		request.setPageSize(NUMBER_1);

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);

		if (response.isInError())
		{
			return null;
		}

		return response.getFirstResult();
	}

	/**
	 * Create a new {@link Note} that will be associate to the new money transfer batch status. *
	 *
	 * @param parentKey - the money transfer id
	 * @param businessType The type of business.
	 * @param noteText The note.
	 * @param userContext The user context.
	 * @return The formatted note.
	 */
	private Note createNote(Integer parentKey, BusinessTypeEnum businessType, String noteText, UserContext userContext)
	{
		Note note = new Note();
		note.setNoteText(noteText);
		note.setParentKey(parentKey);
		note.setParentKeyType(businessType);
		note.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		note.setCreateUser(userContext.getUserId());

		return note;
	}

	/**
	 * Need to update the release user in the batch so we know who released the batch.
	 *
	 * @param batchStatus The batch status to find the release user on.
	 * @param userContext The user context.
	 * @return The response.
	 */
	private InternalResponse updateMoneyTransferBatchReleaseUser(MoneyTransferBatchStatus batchStatus,
			UserContext userContext)
	{
		InternalResultsResponse<MoneyTransferBatch> batchResponse =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(batchStatus.getMoneyTransferBatchId());

		if (batchResponse.isInError())
		{
			return batchResponse;
		}

		MoneyTransferBatch batch = batchResponse.getFirstResult();

		if (!userContext.getUserId().equals(batch.getReleaseUser()))
		{
			batch.setReleaseUser(userContext.getUserId());
			batch.setModelAction(PersistanceActionEnum.UPDATE);
		}

		return getMoneyTransferBatchDAC().updateMoneyTransferBatch(batch);
	}

	/**
	 * Need to update the release user in the money transfer so we know who approved it.
	 *
	 * @param status The status of the money transfer.
	 * @param userContext The user context.
	 * @return The response.
	 */
	private InternalResponse updateMoneyTransferReleaseUser(MoneyTransferStatus status, UserContext userContext)
	{
		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferDAC().fetchMoneyTransferById(status.getMoneyTransferId());

		if (response.isInError())
		{
			return response;
		}

		MoneyTransfer transfer = response.getFirstResult();

		if (!userContext.getUserId().equals(transfer.getReleaseUser()))
		{
			transfer.setReleaseUser(userContext.getUserId());
			transfer.setModelAction(PersistanceActionEnum.UPDATE);
		}

		return getMoneyTransferDAC().updateMoneyTransfer(transfer);
	}

	/**
	 * Checks if there are any RELEASED statuses in the batch.
	 *
	 * @param request - the request containing the MoneyTransferBatch
	 * @return true if there are RELEASED statuses.
	 */
	private boolean hasReleasedBatchStatus(MoneyTransferBatchMaintenanceRequest request)
	{
		MoneyTransferBatch batch = request.getMoneyTransferBatch();

		if (ValidationUtil.isNull(batch))
		{
			return false;
		}

		List<MoneyTransferBatchStatus> batchStatusList = batch.getStatusList();

		return hasReleasedBatchStatus(batchStatusList);
	}

	/**
	 * Checks the MoneyTransferBatchStatus List if there are any RELEASED statuses.
	 *
	 * @param batchStatusList - contains a List of MoneyTransferBatchStatus
	 * @return true if any in the List is RELEASED.
	 */
	private boolean hasReleasedBatchStatus(List<MoneyTransferBatchStatus> batchStatusList)
	{
		if (ValidationUtil.isNullOrEmpty(batchStatusList))
		{
			return false;
		}

		for (MoneyTransferBatchStatus batchStatus : batchStatusList)
		{
			if (MoneyTransferBatchStatusEnum.RELEASED.equals(batchStatus.getStatus()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if the status is RELEASED and returns true is it is.
	 *
	 * @param transferBatchStatus The batch status.
	 * @return True if status is RELEASED.
	 */
	private boolean isReleasedBatchStatus(MoneyTransferBatchStatus transferBatchStatus)
	{
		if (MoneyTransferBatchStatusEnum.RELEASED.equals(transferBatchStatus.getStatus()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks if there are any of the statuses that are one of the APPROVED
	 * statuses (APPROVED, MODIFICATION_APPROVED, CANCELLATION_APPROVED).
	 *
	 * @param statusList - the list of MoneyTransferStatus.
	 * @return true if there are any of the APPROVED statuses to be INSERTed.
	 */
	private boolean hasApprovedStatus(List<MoneyTransferStatus> statusList)
	{
		if (ValidationUtil.isNullOrEmpty(statusList))
		{
			return false;
		}

		for (MoneyTransferStatus status : statusList)
		{
			if (MoneyTransferStatusEnum.APPROVED.equals(status.getStatus()) ||
					MoneyTransferStatusEnum.MODIFICATION_APPROVED.equals(status.getStatus()) ||
					MoneyTransferStatusEnum.CANCELLATION_APPROVED.equals(status.getStatus()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if the transferStatus is APPROVED.
	 *
	 * @param transferStatus The transfer status.
	 * @return true if APPROVED
	 */
	private boolean isApprovedStatus(MoneyTransferStatus transferStatus)
	{
		if (MoneyTransferStatusEnum.APPROVED.equals(transferStatus.getStatus()) ||
				MoneyTransferStatusEnum.MODIFICATION_APPROVED.equals(transferStatus.getStatus()) ||
				MoneyTransferStatusEnum.CANCELLATION_APPROVED.equals(transferStatus.getStatus()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Verify if the transaction is in a approved status
	 *
	 * @param moneyTransfer
	 * @return true or false depending on the transaction status
	 */
	private Boolean isTransactionApproved(MoneyTransfer moneyTransfer)
	{
		if ((moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.APPROVED))
				|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.ORDER_SUBMITTED))
				|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.MODIFICATION_APPROVED))
				|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.PENDING_ACH))
				|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.NOT_PAID))
				|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.MODIFICATION_SUBMITTED))
				|| (moneyTransfer.getCurrentStatus().getStatus().equals(MoneyTransferStatusEnum.PAID)))
		{
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * Verify if the {@link MoneyTransfer} is already in the status that the application is trying to include, if so,
	 * the current status will be kept, otherwise a new one will be included.
	 *
	 * @param status
	 * @param moneyTransferId
	 * @return
	 */
	private Boolean isMoneyTransferAlreadyOnThisStatus(MoneyTransferStatusEnum status, Integer moneyTransferId,
			InternalResponse internalResponse)
	{
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferById(moneyTransferId);

		if (response.isInError())
		{
			internalResponse.merge(response);
			return null;
		}

		if (!ValidationUtil.isNull(response.getFirstResult())
				&& !ValidationUtil.isNull(response.getFirstResult().getCurrentStatus())
				&& response.getFirstResult().getCurrentStatus().getStatus().equals(status))
		{
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * Verify if the {@link MoneyTransferBatch} is already in the status that the application is trying to include, if
	 * so, the current status will be kept, otherwise a new one will be included.
	 *
	 * @param status
	 * @param moneyTransferBatchId
	 * @param internalResponse
	 * @return
	 */
	private Boolean isMoneyTransferBatchAlreadyOnThisStatus(MoneyTransferBatchStatusEnum status,
			Integer moneyTransferBatchId, InternalResponse internalResponse)
	{
		InternalResultsResponse<MoneyTransferBatch> response =
				getMoneyTransferBatchDAC().fetchMoneyTransferBatchById(moneyTransferBatchId);

		if (response.isInError())
		{
			internalResponse.merge(response);
			return null;
		}

		if (!ValidationUtil.isNull(response.getFirstResult())
				&& !ValidationUtil.isNull(response.getFirstResult().getCurrentStatus())
				&& response.getFirstResult().getCurrentStatus().getStatus().equals(status))
		{
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * Verify ids to auto approval based on the user selection.
	 *
	 * @param request the request
	 * @param autoApprovalList the auto approval list
	 * @return the list
	 */
	private List<Integer> verifyIdsToAutoApproval(MoneyTransferAutoApprovalRequest request,
			List<MoneyTransferAutoApprovalItem> autoApprovalList)
	{
		List<Integer> consolidatedList = new ArrayList<Integer>();

		for (MoneyTransferAutoApprovalItem moneyTransferAutoApprovalItem : autoApprovalList)
		{

			if (request.getIsSameAmountPreviousTransaction() && !request.getIsPreviousTransactionApproved()
					&& !request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsSameAmountPreviousTransaction())
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}

			if (!request.getIsSameAmountPreviousTransaction() && !request.getIsPreviousTransactionApproved()
					&& request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsNetPayGreaterTransValue())
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}

			if (!request.getIsSameAmountPreviousTransaction() && request.getIsPreviousTransactionApproved()
					&& !request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsPreviousTransactionApproved())
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}

			if (request.getIsSameAmountPreviousTransaction() && request.getIsPreviousTransactionApproved()
					&& !request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsPreviousTransactionApproved().equals(Boolean.TRUE) &&
						moneyTransferAutoApprovalItem.getIsSameAmountPreviousTransaction().equals(Boolean.TRUE))
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}

			if (request.getIsSameAmountPreviousTransaction() && !request.getIsPreviousTransactionApproved()
					&& request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsNetPayGreaterTransValue().equals(Boolean.TRUE) &&
						moneyTransferAutoApprovalItem.getIsSameAmountPreviousTransaction().equals(Boolean.TRUE))
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}

			if (!request.getIsSameAmountPreviousTransaction() && request.getIsPreviousTransactionApproved()
					&& request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsNetPayGreaterTransValue().equals(Boolean.TRUE) &&
						moneyTransferAutoApprovalItem.getIsPreviousTransactionApproved().equals(Boolean.TRUE))
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}

			if (request.getIsSameAmountPreviousTransaction() && request.getIsPreviousTransactionApproved()
					&& request.getIsNetPayGreaterTransValue())
			{
				if (moneyTransferAutoApprovalItem.getIsNetPayGreaterTransValue().equals(Boolean.TRUE) &&
						moneyTransferAutoApprovalItem.getIsPreviousTransactionApproved().equals(Boolean.TRUE) &&
						moneyTransferAutoApprovalItem.getIsSameAmountPreviousTransaction().equals(Boolean.TRUE))
				{
					consolidatedList.add(moneyTransferAutoApprovalItem.getId());
					continue;
				}
			}
		}

		return consolidatedList;

	}

}
