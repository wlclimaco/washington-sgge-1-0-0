package com.prosperitasglobal.sendsolv.ach.unittest.bac;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.ach.bac.IAchCommonBAC;
import com.prosperitasglobal.sendsolv.ach.bac.IAchTransactionBAC;
import com.prosperitasglobal.sendsolv.ach.bac.IAchTxnOrchestrationBAC;
import com.prosperitasglobal.sendsolv.ach.model.AchStatus;
import com.prosperitasglobal.sendsolv.ach.model.request.AchTransactionRequest;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.dac.IMoneyTransferDAC;
import com.prosperitasglobal.sendsolv.dac.IPayerDAC;
import com.prosperitasglobal.sendsolv.model.Account;
import com.prosperitasglobal.sendsolv.model.AccountTypeEnum;
import com.prosperitasglobal.sendsolv.model.CurrencyAvailability;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAmount;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferDetail;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipant;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.TransferParticipant;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.UserContextHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class AchTxnOrchestrationBACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/ach/unittest/bac/conf/achtxnorchestrationbacimpltest.xml"})
public class AchTxnOrchestrationBACImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(AchTxnOrchestrationBACImplTest.class);

	/** The mock automated clearing house transaction BAC. */
	private IAchTransactionBAC achTransactionBAC;

	/** The automated clearing house transaction orchestration BAC. */
	private IAchTxnOrchestrationBAC achTxnOrchestrationBAC;

	/** The mock money transfer DAC. */
	private IMoneyTransferDAC moneyTransferDAC;

	/** The mock payer DAC. */
	private IPayerDAC payerDAC;

	/** The calling card bac. */
	private ICallingCardBAC callingCardBAC;

	/**
	 * Assert that the money transfer submitted was processed correctly.
	 *
	 * @param response The response of the process.
	 * @param responseStatus Expected status of the response.
	 * @param moneyTransfer The {@link MoneyTransfer} submitted.
	 * @param expectedStatus The expected status.
	 * @param messageCode The message code.
	 */
	private void assertMoneyTransfer(InternalResponse response, Status responseStatus, MoneyTransfer moneyTransfer,
			MoneyTransferStatusEnum expectedStatus, String messageCode, String confirmationNumber)
	{
		Assert.assertEquals("Response status invalid", responseStatus, response.getStatus());
		Assert.assertEquals("MoneyTransfer confirmationNumber is unequal", confirmationNumber,
				moneyTransfer.getConfirmationNumber());
		assertMoneyTransferStatus(moneyTransfer, expectedStatus, messageCode);
	}

	private void assertCurrencyAvailability(MoneyTransfer transfer, PersistanceActionEnum modelAction,
			BigDecimal debitAmount)
	{
		Payer payer = transfer.getTransferSetting().getProductPlanApplicability().getPayer();

		int numberOfModelActions = 0;

		for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
		{
			if (availability.getModelAction().equals(modelAction))
			{
				numberOfModelActions++;
			}
			if (!availability.getModelAction().equals(PersistanceActionEnum.NONE))
			{
				Assert.assertEquals("Debit amount not equal", debitAmount, availability.getCurrencyDebitCumulative());
			}
		}

		if (!modelAction.equals(PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("Should have one " + modelAction, 1, numberOfModelActions);
		}
	}

	/**
	 * Assert that the account was built correctly on the transaction.
	 *
	 * @param expectedAccount Expected account.
	 * @param actualAccount Actual account.
	 */
	private void assertMoneyTransferAccount(Account expectedAccount, Account actualAccount)
	{
		Assert.assertEquals("Account type invalid", expectedAccount.getTypeValue(), actualAccount.getTypeValue());
		Assert.assertEquals("Account number invalid", expectedAccount.getNumber(), actualAccount.getNumber());
	}

	/**
	 * Assert that the money transfer amount was built correctly on the transaction.
	 *
	 * @param expectedAmount The expected amount.
	 * @param actualAmount The actual amount.
	 */
	private void assertMoneyTransferAmount(MoneyTransferAmount expectedAmount, MoneyTransferAmount actualAmount)
	{
		Assert.assertTrue("MoneyTransferAmount amount invalid",
				expectedAmount.getAmount().compareTo(actualAmount.getAmount()) == 0);
		Assert.assertEquals("MoneyTransferAmount country invalid", expectedAmount.getCountry().getCode(), actualAmount
				.getCountry().getCode());
		Assert.assertEquals("MoneyTransferAmount currency invalid", expectedAmount.getCurrency().getCode(),
				actualAmount.getCurrency().getCode());
		// Assert.assertEquals("MoneyTransferAmount stateProvinceRegion invalid",
		// expectedAmount.getStateProvinceRegion()
		// .getCode(), actualAmount.getStateProvinceRegion().getCode());
	}

	/**
	 * Assert that the response back from the process is what was expected.
	 *
	 * @param response The response.
	 * @param messageCode The expected message code.
	 */
	private void assertMoneyTransferProcessingResponse(MoneyTransferProcessingResponse response, String messageCode)
	{
		if (ValidationUtil.isNull(messageCode))
		{
			Assert.assertNull("MoneyTransferProcessingResponse is not null", response);
		}
		else
		{
			CommonTestRoutines.assertMessages(response, messageCode);
		}
	}

	/**
	 * Assert that the status history matches the expected status.
	 *
	 * @param moneyTransfer The money transfer submitted.
	 * @param expectedCurrentStatus The expected current status.
	 * @param messageCode The message code expected.
	 */
	private void assertMoneyTransferStatus(MoneyTransfer moneyTransfer, MoneyTransferStatusEnum expectedCurrentStatus,
			String messageCode)
	{
		MoneyTransferStatus currentStatus = moneyTransfer.getCurrentStatus();
		Assert.assertEquals("MoneyTransfer currentStatus invalid", expectedCurrentStatus, currentStatus.getStatus());
		assertMoneyTransferProcessingResponse(currentStatus.getResponse(), messageCode);

		if ((currentStatus.getStatus() == MoneyTransferStatusEnum.ORDER_SUBMITTED) ||
				(currentStatus.getStatus() == MoneyTransferStatusEnum.CANCELLATION_SUBMITTED) ||
				(currentStatus.getStatus() == MoneyTransferStatusEnum.MODIFICATION_SUBMITTED))
		{
			assertMoneyTransferTransaction(moneyTransfer, currentStatus.getMoneyTransferTransaction());
		}
	}

	/**
	 * Assert that the transaction was built correctly.
	 *
	 * @param moneyTransfer The money transfer submitted.
	 * @param moneyTransferTransaction The money transfer transaction built in the process.
	 */
	private void assertMoneyTransferTransaction(MoneyTransfer moneyTransfer,
			MoneyTransferTransaction moneyTransferTransaction)
	{
		Assert.assertEquals("MoneyTransferTransaction achPayeeCode invalid", moneyTransfer.getTransferSetting()
				.getProductPlanApplicability().getPayer().getAchPayeeCode(),
				moneyTransferTransaction.getAchPayeeCode());
		Assert.assertEquals("MoneyTransferTransaction confirmation number invalid",
				moneyTransfer.getConfirmationNumber(), moneyTransferTransaction.getConfirmationNumber());

		assertMoneyTransferAmount(moneyTransfer.getDestinationAmount(),
				moneyTransferTransaction.getDestinationAmount());
		Assert.assertTrue(
				"MoneyTransferTransaction foreignExchangeRate invalid",
				moneyTransfer
						.getForeignExchangeRateCustomer().compareTo(
								moneyTransferTransaction.getForeignExchangeRateCustomer()) == 0);
		assertMoneyTransferAmount(moneyTransfer.getOriginAmount(), moneyTransferTransaction.getOriginAmount());
		Assert.assertEquals("MoneyTransferTransaction paymentType invalid", moneyTransfer.getPaymentTypeValue(),
				moneyTransferTransaction.getPaymentTypeValue());

		Account senderAccount = new Account();
		senderAccount.setType(AccountTypeEnum.CHECKING);
		senderAccount.setNumber(new Integer(0));

		assertParticipant(moneyTransferTransaction.getSender(), moneyTransfer.getMoneyTransferDetail().getMember(),
				senderAccount);
		assertParticipant(moneyTransferTransaction.getRecipient(), moneyTransfer.getMoneyTransferDetail()
				.getRecipient(), moneyTransfer.getTransferSetting().getAccount());
	}

	/**
	 * Assert that the participant on the transaction was built correctly.
	 *
	 * @param moneyTransferParticipant The participant on the transaction.
	 * @param participant The participant on the money transfer.
	 * @param account The account.
	 */
	private void assertParticipant(MoneyTransferParticipant moneyTransferParticipant, TransferParticipant participant,
			Account account)
	{
		assertMoneyTransferAccount(account, moneyTransferParticipant.getAccount());
		Assert.assertEquals("Participant firstName invalid", participant.getFirstName(), moneyTransferParticipant
				.getName().getFirstName());
		Assert.assertEquals("Participant lastName invalid", participant.getLastName(), moneyTransferParticipant
				.getName().getLastName());

		if (!ValidationUtil.isNullOrEmpty(participant.getNameList()))
		{
			Assert.assertEquals("Participant motherMaidenName invalid",
					participant.getNameList().get(0).getOtherName(), moneyTransferParticipant.getName()
							.getMotherMaidenName());
		}

		Address address = null;
		for (Contact contact : participant.getContactList())
		{
			if (contact.getContactType().equals(ContactTypeEnum.POSTAL_HOME))
			{
				address = (Address)contact;
				Assert.assertEquals("Participant address line invalid", obtainAddressLines(address),
						moneyTransferParticipant.getAddress().getAddress());
				Assert.assertEquals("Participant address city invalid", address.getCityName(), moneyTransferParticipant
						.getAddress().getCity());
				Assert.assertEquals("Participant address stateProvinceRegion invalid", address.getStateProvinceRegion()
						.getCode(), moneyTransferParticipant.getAddress().getStateProvinceRegion().getCode());
				Assert.assertEquals("Participant address postalCode invalid", address.getPostalCode(),
						moneyTransferParticipant.getAddress().getPostalCode());
				Assert.assertEquals("Participant address country invalid", address.getCountry().getCode(),
						moneyTransferParticipant.getAddress().getCountry().getCode());
				break;
			}
		}

		Phone phone = null;
		// Attempt to get the mobile number first.
		for (Contact contact : participant.getContactList())
		{
			if (contact.getContactType().equals(ContactTypeEnum.MOBILE))
			{
				phone = (Phone)contact;
				break;
			}
		}

		// If no mobile number, attempt to get the other phone number.
		if (ValidationUtil.isNull(phone))
		{
			for (Contact contact : participant.getContactList())
			{
				if (contact.getContactType().equals(ContactTypeEnum.OTHER))
				{
					phone = (Phone)contact;
					break;
				}
			}
		}

		// If no other number, attempt to get the work phone number.
		if (ValidationUtil.isNull(phone))
		{
			for (Contact contact : participant.getContactList())
			{
				if (contact.getContactType().equals(ContactTypeEnum.PHONE_WORK))
				{
					phone = (Phone)contact;
					break;
				}
			}
		}

		Assert.assertEquals("Participant phoneNumber invalid", phone.getNumber(),
				moneyTransferParticipant.getPhoneNumber());
	}

	/**
	 * Obtain the line addresses as 1 String.
	 *
	 * @param address The address.
	 * @return The line address as 1 string.
	 */
	private String obtainAddressLines(Address address)
	{
		StringBuilder builder = new StringBuilder();

		if (!ValidationUtil.isNullOrEmpty(address.getAddressLine1()))
		{
			builder.append(address.getAddressLine1());
		}
		if (!ValidationUtil.isNullOrEmpty(address.getAddressLine2()))
		{
			builder.append(' ');
			builder.append(address.getAddressLine2());
		}
		if (!ValidationUtil.isNullOrEmpty(address.getAddressLine3()))
		{
			builder.append(' ');
			builder.append(address.getAddressLine3());
		}
		if (!ValidationUtil.isNullOrEmpty(address.getAddressLine4()))
		{
			builder.append(' ');
			builder.append(address.getAddressLine4());
		}

		return builder.toString();
	}

	/**
	 * Obtain a default MoneyTransfer object.
	 *
	 * @return The default object.
	 */
	private MoneyTransfer obtainDefaultMoneyTransfer()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();
		Location location = CommonTestRoutines.createDummyLocation(organization);
		MoneyTransferBatch moneyTransferBatch = CommonTestRoutines.createDummyMoneyTransferBatch(location);
		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		MoneyTransfer moneyTransfer = CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch, transferSetting);

		Member member = CommonTestRoutines.createDummyMember();
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		moneyTransfer.setMoneyTransferDetail(new MoneyTransferDetail());
		moneyTransfer.getMoneyTransferDetail().setMember(member);
		moneyTransfer.getMoneyTransferDetail().setRecipient(recipient);

		return moneyTransfer;
	}

	/**
	 * Obtain a mocked up {@link InternalResponse} containing the error code.
	 *
	 * @param errorCode The error code to contain.
	 * @param status The expected status.
	 * @return The mocked up response.
	 */
	private InternalResultsResponse<AchStatus> obtainMockInternalResponse(String errorCode,
			MoneyTransferStatusEnum status)
	{
		InternalResultsResponse<AchStatus> response = new InternalResultsResponse<AchStatus>();

		if (!ValidationUtil.isNull(errorCode))
		{
			response.addFieldErrorMessage(errorCode);
		}

		AchStatus achStatus = new AchStatus();
		achStatus.setMoneyTransferStatus(status);
		MoneyTransferProcessingResponse mtResponse = new MoneyTransferProcessingResponse();
		mtResponse.addMessage(errorCode, MessageSeverity.Info, MessageLevel.None);
		achStatus.setAchResponse(mtResponse);

		response.addResult(achStatus);

		return response;
	}

	/**
	 * Gets the implementation of the {@link IAchTransactionBAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public IAchTransactionBAC getAchTransactionBAC()
	{
		return achTransactionBAC;
	}

	/**
	 * Sets the implementation of the {@link IAchTransactionBAC} interface. Injected by Spring.
	 *
	 * @param achTransactionBAC The implementation to set.
	 */
	@Resource
	public void setAchTransactionBAC(IAchTransactionBAC achTransactionBAC)
	{
		this.achTransactionBAC = achTransactionBAC;
	}

	/**
	 * Get the implementation of the {@link IAchTxnOrchestrationBAC} interface. Injected by Spring.
	 *
	 * @return The implementation of the {@link IAchTxnOrchestrationBAC}.
	 */
	public IAchTxnOrchestrationBAC getAchTxnOrchestrationBAC()
	{
		return achTxnOrchestrationBAC;
	}

	/**
	 * Sets the implementation of the {@link IAchTxnOrchestrationBAC} interface. Injected by Spring.
	 *
	 * @param achTxnOrchestrationBAC The implementation to set.
	 */
	@Resource
	public void setAchTxnOrchestrationBAC(IAchTxnOrchestrationBAC achTxnOrchestrationBAC)
	{
		this.achTxnOrchestrationBAC = achTxnOrchestrationBAC;
	}

	/**
	 * Gets the implementation of the {@link IMoneyTransferDAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public IMoneyTransferDAC getMoneyTransferDAC()
	{
		return moneyTransferDAC;
	}

	/**
	 * Sets the implementation of the {@link IMoneyTransferDAC} interface. Injected by Spring.
	 *
	 * @param moneyTransferDAC The implementation to set.
	 */
	@Resource
	public void setMoneyTransferDAC(IMoneyTransferDAC moneyTransferDAC)
	{
		this.moneyTransferDAC = moneyTransferDAC;
	}

	/**
	 * Gets the implementation of the {@link IPayerDAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	public IPayerDAC getPayerDAC()
	{
		return payerDAC;
	}

	/**
	 * Sets the implementation of the {@link IPayerDAC} interface. Injected by Spring.
	 *
	 * @return The implementation.
	 */
	@Resource
	public void setPayerDAC(IPayerDAC payerDAC)
	{
		this.payerDAC = payerDAC;
	}

	/**
	 * @return the callingCardBAC
	 */
	public ICallingCardBAC getCallingCardBAC()
	{
		return callingCardBAC;
	}

	/**
	 * @param callingCardBAC the callingCardBAC to set
	 */
	@Resource
	public void setCallingCardBAC(ICallingCardBAC callingCardBAC)
	{
		this.callingCardBAC = callingCardBAC;
	}

	/**
	 * Initializes the environment before execution.
	 */
	@Before
	public void setUp()
	{
		UserContext userContext = new UserContext("TEST");
		UserContextHelper.setUserContext(userContext);

		Mockito.reset(getMoneyTransferDAC());
		Mockito.reset(getAchTransactionBAC());
		Mockito.reset(getPayerDAC());
		Mockito.reset(getCallingCardBAC());
	}

	/** Test creation of a money transfer transaction for a cancel order request. */
	@Test
	public void testCreateMoneyTransferTransactionCancelOrder()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResponse response = getAchTxnOrchestrationBAC().createCancelMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.CANCELLATION_SUBMITTED, null, request.getMoneyTransfer()
						.getConfirmationNumber());

		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test creation of a money transfer transaction for a cancel order request. */
	@Test
	public void testCreateMoneyTransferTransactionCancelOrderError()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse(Status.ExceptionError));

		InternalResponse response = getAchTxnOrchestrationBAC().createCancelMoneyTransferTransaction(request);
		Assert.assertEquals(Status.ExceptionError, response.getStatus());

		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test creation of a money transfer transaction for a modify order request. */
	@Test
	public void testCreateMoneyTransferTransactionModifyOrder()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResponse response = getAchTxnOrchestrationBAC().createModifyMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.MODIFICATION_SUBMITTED, null, request.getMoneyTransfer()
						.getConfirmationNumber());

		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test creation of a money transfer transaction for a cancel order request. */
	@Test
	public void testCreateMoneyTransferTransactionModifyOrderError()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse(Status.ExceptionError));

		InternalResponse response = getAchTxnOrchestrationBAC().createModifyMoneyTransferTransaction(request);
		Assert.assertEquals(Status.ExceptionError, response.getStatus());

		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmitted()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.ORDER_SUBMITTED, null, request.getMoneyTransfer().getConfirmationNumber());

		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));

	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedNewConfNumber()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());
		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();
		payer.setModelAction(PersistanceActionEnum.NONE);
		for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
		{
			availability.setModelAction(PersistanceActionEnum.NONE);
		}

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(new InternalResponse());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.addResult("11111111111");
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.ORDER_SUBMITTED, null, "11111111111");
		assertCurrencyAvailability(request.getMoneyTransfer(), PersistanceActionEnum.UPDATE, new BigDecimal("20.00"));

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.times(1)).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedPayerUpdateError()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());
		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();
		payer.setModelAction(PersistanceActionEnum.NONE);
		for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
		{
			availability.setModelAction(PersistanceActionEnum.NONE);
		}

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(
				new InternalResponse(Status.NoRowsUpdatedError));

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.addResult("11111111111");
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.NoRowsUpdatedError, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.PENDING_APPROVAL, null, null);
		assertCurrencyAvailability(request.getMoneyTransfer(), PersistanceActionEnum.UPDATE, new BigDecimal("20.00"));

		Mockito.verify(getAchTransactionBAC(), Mockito.never()).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.never()).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.times(1)).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedNewConfNumberError()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.setStatus(Status.ExceptionError);
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		CommonTestRoutines.assertMessagesInfo(response.getMessageInfoList(), LOG,
				IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_EXCEPTION_PGSI_ERROR);

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getPayerDAC(), Mockito.times(1)).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedNewCurrencyAvailability()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());
		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();
		payer.setModelAction(PersistanceActionEnum.NONE);

		int availabilityIndex = -1;

		CurrencyAvailability availability = null;

		for (int i = 0; i < payer.getCurrencyAvailabilityList().size(); i++)
		{
			availability = payer.getCurrencyAvailabilityList().get(i);

			if (availability.getCurrency().getCode().equals("MXP"))
			{
				availabilityIndex = i;
			}
			availability.setModelAction(PersistanceActionEnum.NONE);
		}
		payer.getCurrencyAvailabilityList().remove(availabilityIndex);

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(new InternalResponse());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.addResult("11111111111");
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.ORDER_SUBMITTED, null, "11111111111");
		assertCurrencyAvailability(request.getMoneyTransfer(), PersistanceActionEnum.INSERT, new BigDecimal("10.00"));

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.times(1)).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedCurrencyAvailabilityNegative()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());
		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();
		payer.setModelAction(PersistanceActionEnum.NONE);
		for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
		{
			availability.setModelAction(PersistanceActionEnum.NONE);
			if (availability.getCurrency().getCode().equals("MXP"))
			{
				availability.setCurrencyCreditCumulative(new BigDecimal("5.00"));
			}
		}

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(new InternalResponse());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.addResult("11111111111");
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.ORDER_SUBMITTED, null, "11111111111");
		assertCurrencyAvailability(request.getMoneyTransfer(), PersistanceActionEnum.UPDATE, new BigDecimal("20.00"));

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.times(1)).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedCurrencyAvailabilityPositivePrefund()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());
		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();
		payer.setModelAction(PersistanceActionEnum.NONE);
		payer.setPostfundAllowed(false);
		for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
		{
			availability.setModelAction(PersistanceActionEnum.NONE);
		}

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(new InternalResponse());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.addResult("11111111111");
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.ORDER_SUBMITTED, null, "11111111111");
		assertCurrencyAvailability(request.getMoneyTransfer(), PersistanceActionEnum.UPDATE, new BigDecimal("20.00"));

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.times(1)).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedCurrencyAvailabilityNegativePrefund()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().setConfirmationNumber(null);
		request.setUserContext(UserContextHelper.getUserContext());
		Payer payer = request.getMoneyTransfer().getTransferSetting().getProductPlanApplicability().getPayer();
		payer.setModelAction(PersistanceActionEnum.NONE);
		payer.setPostfundAllowed(false);
		for (CurrencyAvailability availability : payer.getCurrencyAvailabilityList())
		{
			availability.setModelAction(PersistanceActionEnum.NONE);
			if (availability.getCurrency().getCode().equals("MXP"))
			{
				availability.setCurrencyCreditCumulative(new BigDecimal("5.00"));
			}
		}

		Mockito.when(getPayerDAC().updatePayer(Matchers.any(Payer.class))).thenReturn(new InternalResponse());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		InternalResultsResponse<String> achIdentifierResponse = new InternalResultsResponse<String>();
		achIdentifierResponse.addResult("11111111111");
		Mockito.when(getAchTransactionBAC().obtainNewAchTransactionIdentifier(Matchers.any(Request.class))).thenReturn(
				achIdentifierResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.APPROVED_NOT_FUNDED, null, null);
		assertCurrencyAvailability(request.getMoneyTransfer(), PersistanceActionEnum.UPDATE, new BigDecimal("20.00"));

		Mockito.verify(getAchTransactionBAC(), Mockito.never()).obtainNewAchTransactionIdentifier(
				Matchers.any(Request.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test submission of a money transfer transaction for an order submit request. */
	@Test
	public void testCreateMoneyTransferTransactionOrderSubmittedOptimisticLocking()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse(Status.OptimisticLockingError));

		InternalResponse response = getAchTxnOrchestrationBAC().createTransferMoneyTransferTransaction(request);
		Assert.assertEquals(Status.OptimisticLockingError, response.getStatus());

		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test money Transfer Transfer with the order being cancelled. */
	@Test
	public void testSubmitMoneyTransferTransactionCancelOrder()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setStatus(MoneyTransferStatusEnum.CANCELLATION_SUBMITTED);
		request.setUserContext(UserContextHelper.getUserContext());
		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		Mockito.when(getAchTransactionBAC().cancel(Matchers.any(AchTransactionRequest.class))).thenReturn(
				obtainMockInternalResponse(IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER,
						MoneyTransferStatusEnum.NOT_PAID));

		Mockito.when(getCallingCardBAC().refillCard(Matchers.any(CallingCardMaintenanceRequest.class))).thenReturn(
				new InternalResultsResponse<CallingCardInfo>());

		InternalResponse response = getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.NOT_PAID, IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER, request
						.getMoneyTransfer().getConfirmationNumber());

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).cancel(Matchers.any(AchTransactionRequest.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
		Mockito.verify(getCallingCardBAC(), Mockito.never()).refillCard(
				Matchers.any(CallingCardMaintenanceRequest.class));
	}

	/** Test money Transfer Transfer with the order being cancelled. */
	@Test
	public void testSubmitMoneyTransferTransactionCancelOrderNoStatusReturned()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setStatus(MoneyTransferStatusEnum.CANCELLATION_SUBMITTED);
		request.setUserContext(UserContextHelper.getUserContext());

		InternalResultsResponse<AchStatus> mockResponse = new InternalResultsResponse<AchStatus>();
		mockResponse.setStatus(Status.SystemError);
		Mockito.when(getAchTransactionBAC().cancel(Matchers.any(AchTransactionRequest.class))).thenReturn(
				mockResponse);

		InternalResponse response = getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
		Assert.assertEquals(Status.SystemError, response.getStatus());

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).cancel(Matchers.any(AchTransactionRequest.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
	}

	/** Test money Transfer Transfer with the order being cancelled that doesn't have a transaction. */
	@Test
	public void testSubmitMoneyTransferTransactionCancelOrderNoTransaction()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setMoneyTransferTransaction(null);
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getAchTransactionBAC().transfer(Matchers.any(AchTransactionRequest.class))).thenThrow(
				new RuntimeException());

		try
		{
			getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
			Assert.assertFalse("Test should have thrown an exception for no transaction", true);
		}

		catch (Exception ex)
		{
			Mockito.verify(getAchTransactionBAC(), Mockito.times(1))
			.transfer(Matchers.any(AchTransactionRequest.class));
			Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
		}
	}

	/** Test money Transfer Transfer with the order being cancelled. */
	@Test
	public void testSubmitMoneyTransferTransactionCancelOrderNullStatus()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setStatus(MoneyTransferStatusEnum.CANCELLATION_SUBMITTED);
		request.setUserContext(UserContextHelper.getUserContext());

		InternalResultsResponse<AchStatus> mockResponse = new InternalResultsResponse<AchStatus>();
		mockResponse.setStatus(Status.SystemError);
		mockResponse.addResult(new AchStatus());
		Mockito.when(getAchTransactionBAC().cancel(Matchers.any(AchTransactionRequest.class))).thenReturn(
				mockResponse);

		try
		{
			getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
			Assert.assertFalse("Test should have thrown an exception", true);
		}

		catch (IllegalStateException ex)
		{
			Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).cancel(Matchers.any(AchTransactionRequest.class));
			Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
		}
	}

	/** Test money Transfer Transfer with the order being cancelled. */
	@Test
	public void testSubmitMoneyTransferTransactionCancelOrderOptimisticLocking()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setStatus(MoneyTransferStatusEnum.CANCELLATION_SUBMITTED);
		request.setUserContext(UserContextHelper.getUserContext());

		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse(Status.OptimisticLockingError));

		Mockito.when(getAchTransactionBAC().cancel(Matchers.any(AchTransactionRequest.class))).thenReturn(
				obtainMockInternalResponse(IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER,
						MoneyTransferStatusEnum.NOT_PAID));

		Mockito.when(getCallingCardBAC().refillCard(Matchers.any(CallingCardMaintenanceRequest.class))).thenReturn(
				new InternalResultsResponse<CallingCardInfo>());

		InternalResponse response = getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
		Assert.assertEquals(Status.OptimisticLockingError, response.getStatus());

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).cancel(Matchers.any(AchTransactionRequest.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
		Mockito.verify(getCallingCardBAC(), Mockito.never()).refillCard(
				Matchers.any(CallingCardMaintenanceRequest.class));
	}

	/** Test money Transfer Transfer with the order being modified. */
	@Test
	public void testSubmitMoneyTransferTransactionModifyOrder()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setStatus(MoneyTransferStatusEnum.MODIFICATION_SUBMITTED);
		request.setUserContext(UserContextHelper.getUserContext());
		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		Mockito.when(getAchTransactionBAC().modify(Matchers.any(AchTransactionRequest.class))).thenReturn(
				obtainMockInternalResponse(IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER,
						MoneyTransferStatusEnum.NOT_PAID));

		Mockito.when(getCallingCardBAC().refillCard(Matchers.any(CallingCardMaintenanceRequest.class))).thenReturn(
				new InternalResultsResponse<CallingCardInfo>());

		InternalResponse response = getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.NOT_PAID, IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER, request
						.getMoneyTransfer().getConfirmationNumber());

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).modify(Matchers.any(AchTransactionRequest.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
		Mockito.verify(getCallingCardBAC(), Mockito.never()).refillCard(
				Matchers.any(CallingCardMaintenanceRequest.class));
	}

	/** Test money Transfer Transfer with the order being modified. */
	@Test
	public void testSubmitMoneyTransferTransactionTransferOrder()
	{
		MoneyTransferMaintenanceRequest request = new MoneyTransferMaintenanceRequest();
		request.setMoneyTransfer(obtainDefaultMoneyTransfer());
		request.getMoneyTransfer().getCurrentStatus().setStatus(MoneyTransferStatusEnum.ORDER_SUBMITTED);
		request.setUserContext(UserContextHelper.getUserContext());
		Mockito.when(getMoneyTransferDAC().updateMoneyTransfer(Matchers.any(MoneyTransfer.class))).thenReturn(
				new InternalResponse());

		Mockito.when(getAchTransactionBAC().transfer(Matchers.any(AchTransactionRequest.class))).thenReturn(
				obtainMockInternalResponse(IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER,
						MoneyTransferStatusEnum.NOT_PAID));

		Mockito.when(getCallingCardBAC().refillCard(Matchers.any(CallingCardMaintenanceRequest.class))).thenReturn(
				new InternalResultsResponse<CallingCardInfo>());

		InternalResponse response = getAchTxnOrchestrationBAC().submitMoneyTransferTransaction(request);
		assertMoneyTransfer(response, Status.OperationSuccess, request.getMoneyTransfer(),
				MoneyTransferStatusEnum.NOT_PAID, IAchCommonBAC.SENDSOLV_INTEGRATION_ACH_ACCEPTED_ORDER, request
						.getMoneyTransfer().getConfirmationNumber());

		Mockito.verify(getAchTransactionBAC(), Mockito.times(1)).transfer(Matchers.any(AchTransactionRequest.class));
		Mockito.verify(getMoneyTransferDAC(), Mockito.times(1)).updateMoneyTransfer(Matchers.any(MoneyTransfer.class));
		Mockito.verify(getPayerDAC(), Mockito.never()).updatePayer(Matchers.any(Payer.class));
		Mockito.verify(getCallingCardBAC()).refillCard(Matchers.any(CallingCardMaintenanceRequest.class));
	}
}
