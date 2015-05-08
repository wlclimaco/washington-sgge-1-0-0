package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.integration.twilio.util.PGSiDateUtil;
import com.prosperitasglobal.sendsolv.model.Account;
import com.prosperitasglobal.sendsolv.model.AccountTypeEnum;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAmount;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferDetail;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipant;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantAddress;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantPersonName;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.criteria.MoneyTransferCriteria;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse;
import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferDACTest. This test covers money transfers.
 */
public class MoneyTransferDACTest extends AbstractTestBaseDAC
{
	/** The big page size for fetching. */
	private static final Integer BIG_PAGE_SIZE = 100;
	/** The sort name for a member last name. */
	private static final String MEMBER_LAST_NAME_SORT_FIELD = "memberLastName";
	/** The number of money transfer batches to insert. */
	private static final int MONEY_TRANSFER_BATCHES_TO_INSERT = 3;
	/** The number of money transfer batches to insert for paging test. */
	private static final int MONEY_TRANSFER_BATCHES_TO_INSERT_PAGING = 6;
	/** The number of rows need to match reason. */
	private static final String NUMBER_OF_ROWS_NEED_TO_MATCH = "Number of rows need to match";
	/** The small page size for fetching. */
	private static final Integer PAGE_SIZE = 2;
	/** The sort name for the payer. */
	private static final String PAYER_NAME_SORT_FIELD = "payerName";
	/** The sort name for the status. */
	private static final String STATUS_SORT_FIELD = "status";

	/**
	 * Compare the results of the fetch.
	 *
	 * @param response The response from the fetch.
	 * @param action The model action that was performed before the fetch.
	 */
	private void assertFetch(InternalResultsResponse<?> response, PersistanceActionEnum action)
	{
		if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
				|| (action == PersistanceActionEnum.NONE))
		{
			CommonTestRoutines.assertResultResponse(response);
			Assert.assertTrue("response.getResultsList().size() needs to be > 0", response.getResultsList().size() > 0);
			Assert.assertNotNull("response.getFirstResult() cannot be null", response.getFirstResult());
		}
		else if (action == PersistanceActionEnum.DELETE)
		{
			Assert.assertTrue("response.getResultsList().size() needs to be 0", response.getResultsList().size() == 0);
			Assert.assertNull("response.getFirstResult() must be null", response.getFirstResult());
		}
	}

	/**
	 * This method compares fields between two Account objects.
	 *
	 * @param account The Account inserted/updated.
	 * @param dbAccount The DB Account fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(Account account, Account dbAccount, PersistanceActionEnum action)
	{
		Assert.assertEquals("Account number", account.getNumber(), dbAccount.getNumber());
		Assert.assertEquals("Account type", account.getTypeValue(), dbAccount.getTypeValue());
	}

	/**
	 * This method compares fields between two Message objects.
	 *
	 * @param message The Message inserted/updated.
	 * @param dbMessage The DB Message fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(Message message, Message dbMessage, PersistanceActionEnum action)
	{
		Assert.assertEquals("Message text", message.getText(), dbMessage.getText());
		assertEquals(message.getMessageInfo(), dbMessage.getMessageInfo(), action);
	}

	/**
	 * This method compares fields between two MessageInfo objects.
	 *
	 * @param messageInfo The MessageInfo inserted/updated.
	 * @param dbMessageInfo The DB MessageInfo fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MessageInfo messageInfo, MessageInfo dbMessageInfo, PersistanceActionEnum action)
	{
		if (!ValidationUtil.isNull(messageInfo.getArguments()))
		{
			Assert.assertNotNull("MessageInfo arguments not null, db MessageInfo arguments is null",
					dbMessageInfo.getArguments());

			for (int i = 0; i < messageInfo.getArguments().length; i++)
			{
				Assert.assertTrue("MessageInfo arguments mismatch! Index: " + i,
						messageInfo.getArguments()[i].equals(dbMessageInfo.getArguments()[i]));
			}
		}
		else
		{
			Assert.assertNull("MessageInfo arguments null, db MessageInfo arguments is not null",
					dbMessageInfo.getArguments());
		}

		Assert.assertEquals("MessageInfo code", messageInfo.getCode(), dbMessageInfo.getCode());
		Assert.assertEquals("MessageInfo createDateUTC", messageInfo.getCreateDateUTC(),
				dbMessageInfo.getCreateDateUTC());
		Assert.assertEquals("MessageInfo level", messageInfo.getLevel(), dbMessageInfo.getLevel());
		Assert.assertEquals("MessageInfo relatedPropertyName", messageInfo.getRelatedPropertyName(),
				dbMessageInfo.getRelatedPropertyName());
		Assert.assertEquals("MessageInfo severity", messageInfo.getSeverity(), dbMessageInfo.getSeverity());
		Assert.assertEquals("MessageInfo traceInfo", messageInfo.getTraceInfo(), dbMessageInfo.getTraceInfo());
	}

	/**
	 * This method compares fields between two MoneyTransfer objects.
	 *
	 * @param moneyTransfer The money transfer inserted/updated.
	 * @param dbMoneyTransfer The DB money transfer fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransfer moneyTransfer, MoneyTransfer dbMoneyTransfer, PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModel(moneyTransfer, dbMoneyTransfer, action);

		if (action == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB MoneyTransfer id must not be null", dbMoneyTransfer.getId());
		}
		else if ((action == PersistanceActionEnum.UPDATE) || (action == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("MoneyTransfer id mismatch ", moneyTransfer.getId(), dbMoneyTransfer.getId());
		}

		Assert.assertNotNull("MoneyTransfer key", dbMoneyTransfer.getKey());
		assertEquals(moneyTransfer.getDestinationAmount(), dbMoneyTransfer.getDestinationAmount(), action);
		Assert.assertTrue("MoneyTransfer discountAmount",
				moneyTransfer.getDiscountAmount().compareTo(dbMoneyTransfer.getDiscountAmount()) == 0);
		Assert.assertTrue("MoneyTransfer originFlatFee",
				moneyTransfer.getOriginFlatFee().compareTo(dbMoneyTransfer.getOriginFlatFee()) == 0);
		Assert.assertTrue("MoneyTransfer originAutomatedClearingHouseFee",
				moneyTransfer.getOriginAutomatedClearingHouseFee().compareTo(
						dbMoneyTransfer.getOriginAutomatedClearingHouseFee()) == 0);
		Assert.assertTrue("MoneyTransfer originCallCreditAmount",
				moneyTransfer.getOriginCallCreditAmount().compareTo(dbMoneyTransfer.getOriginCallCreditAmount()) == 0);
		Assert.assertTrue("MoneyTransfer foreignExchangeRate",
				moneyTransfer.getForeignExchangeRate().compareTo(dbMoneyTransfer.getForeignExchangeRate()) == 0);
		Assert.assertTrue("MoneyTransfer foreignExchangeRateCustomer", moneyTransfer.getForeignExchangeRateCustomer()
				.compareTo(dbMoneyTransfer.getForeignExchangeRateCustomer()) == 0);
		Assert.assertEquals("MoneyTransfer moneyTransferBatchId", moneyTransfer.getMoneyTransferBatchId(),
				dbMoneyTransfer.getMoneyTransferBatchId());
		assertEquals(moneyTransfer.getOriginAmount(), dbMoneyTransfer.getOriginAmount(), action);
		Assert.assertEquals("MoneyTransfer confirmationNumber", moneyTransfer.getConfirmationNumber(),
				dbMoneyTransfer.getConfirmationNumber());
		Assert.assertEquals("MoneyTransfer paymentType", moneyTransfer.getPaymentTypeValue(),
				dbMoneyTransfer.getPaymentTypeValue());
		Assert.assertEquals("MoneyTransfer spreadPercentage", moneyTransfer.getSpreadPercentage(),
				dbMoneyTransfer.getSpreadPercentage());
		Assert.assertEquals("MoneyTransfer transferSettingId", moneyTransfer.getTransferSetting().getId(),
				dbMoneyTransfer.getTransferSetting().getId());
		Assert.assertEquals("MoneyTransfer releaseUser", moneyTransfer.getReleaseUser(),
				dbMoneyTransfer.getReleaseUser());
		assertEquals(moneyTransfer.getSenderAccount(), dbMoneyTransfer.getSenderAccount(), action);
		assertEquals(moneyTransfer.getRecipientAccount(), dbMoneyTransfer.getRecipientAccount(), action);
		assertEquals(moneyTransfer.getTransferSetting(), dbMoneyTransfer.getMoneyTransferDetail());

		for (MoneyTransferStatus moneyTransferStatus : moneyTransfer.getStatusList())
		{
			if (action == PersistanceActionEnum.INSERT)
			{
				assertEquals(dbMoneyTransfer.getStatusList(), moneyTransferStatus, action);
			}
			else
			{
				assertEquals(dbMoneyTransfer.getStatusList(), moneyTransferStatus, PersistanceActionEnum.NONE);
			}
		}

		for (Note note : moneyTransfer.getNoteList())
		{
			if (action == PersistanceActionEnum.INSERT)
			{
				assertEquals(dbMoneyTransfer.getNoteList(), note, action);
			}
			else
			{
				assertEquals(dbMoneyTransfer.getNoteList(), note, PersistanceActionEnum.NONE);
			}
		}
	}

	/**
	 * This method compares fields between two MoneyTransferAmount objects.
	 *
	 * @param moneyTransferAmount The moneyTransferAmount inserted/updated.
	 * @param dbMoneyTransferAmount The DB moneyTransferAmount fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferAmount moneyTransferAmount, MoneyTransferAmount dbMoneyTransferAmount,
			PersistanceActionEnum action)
	{
		Assert.assertTrue("MoneyTransferAmount amount",
				moneyTransferAmount.getAmount().compareTo(dbMoneyTransferAmount.getAmount()) == 0);
		Assert.assertEquals("MoneyTransferAmount currency", moneyTransferAmount.getCurrency().getCode(),
				dbMoneyTransferAmount.getCurrency().getCode());
		Assert.assertEquals("MoneyTransferAmount country", moneyTransferAmount.getCountry().getCode(),
				dbMoneyTransferAmount.getCountry().getCode());
	}

	/**
	 * This method compares fields for retrieval from the db.
	 *
	 * @param transferSetting The transfer setting inserted/updated.
	 * @param dbMoneyTransferDetail The moneyTransferAmount built from the transfer setting.
	 */
	private void assertEquals(TransferSetting transferSetting, MoneyTransferDetail dbMoneyTransferDetail)
	{
		Assert.assertEquals("MoneyTransferDetail member", transferSetting.getMemberId(), dbMoneyTransferDetail
				.getMember().getId());
		Assert.assertEquals("MoneyTransferDetail recipient", transferSetting.getRecipientId(), dbMoneyTransferDetail
				.getRecipient().getId());
	}

	/**
	 * This method compares 2 different status.
	 *
	 * @param expectedStatus Expected status.
	 * @param actualStatus Actual status.
	 */
	private void assertEquals(Status expectedStatus, Status actualStatus)
	{
		Assert.assertEquals("Expected status " + expectedStatus + "but got status " + actualStatus, expectedStatus,
				actualStatus);
	}

	/**
	 * This method compares fields between two MoneyTransferParticipant objects.
	 *
	 * @param moneyTransferParticipant The MoneyTransferParticipant inserted/updated.
	 * @param dbMoneyTransferParticipant The DB MoneyTransferParticipant fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferParticipant moneyTransferParticipant,
			MoneyTransferParticipant dbMoneyTransferParticipant, PersistanceActionEnum action)
	{
		Assert.assertEquals("MoneyTransferParticipant phoneNumber mismatch!",
				moneyTransferParticipant.getPhoneNumber(), dbMoneyTransferParticipant.getPhoneNumber());
		assertEquals(moneyTransferParticipant.getAddress(), dbMoneyTransferParticipant.getAddress(), action);
		assertEquals(moneyTransferParticipant.getName(), dbMoneyTransferParticipant.getName(), action);
		assertEquals(moneyTransferParticipant.getAccount(), dbMoneyTransferParticipant.getAccount(), action);
	}

	/**
	 * This method compares fields between two MoneyTransferParticipantPersonName objects.
	 *
	 * @param moneyTransferParticipantAddress The MoneyTransferParticipantAddress inserted/updated.
	 * @param dbMoneyTransferParticipantAddress The DB MoneyTransferParticipantAddress fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferParticipantAddress moneyTransferParticipantAddress,
			MoneyTransferParticipantAddress dbMoneyTransferParticipantAddress, PersistanceActionEnum action)
	{
		Assert.assertEquals("MoneyTransferParticipantAddress address", moneyTransferParticipantAddress.getAddress(),
				dbMoneyTransferParticipantAddress.getAddress());
		Assert.assertEquals("MoneyTransferParticipantAddress city", moneyTransferParticipantAddress.getCity(),
				dbMoneyTransferParticipantAddress.getCity());
		Assert.assertEquals("MoneyTransferParticipantAddress country", moneyTransferParticipantAddress.getCountry()
				.getCode(), dbMoneyTransferParticipantAddress.getCountry().getCode());
		Assert.assertEquals("MoneyTransferParticipantAddress postalCode",
				moneyTransferParticipantAddress.getPostalCode(), dbMoneyTransferParticipantAddress.getPostalCode());
		Assert.assertEquals("MoneyTransferParticipantAddress stateProvinceRegion", moneyTransferParticipantAddress
				.getStateProvinceRegion().getCode().trim(), dbMoneyTransferParticipantAddress.getStateProvinceRegion()
				.getCode().trim());
	}

	/**
	 * This method compares fields between two MoneyTransferParticipantPersonName objects.
	 *
	 * @param moneyTransferParticipantPersonName The MoneyTransferParticipantPersonName inserted/updated.
	 * @param dbMoneyTransferParticipantPersonName The DB MoneyTransferParticipantPersonName fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferParticipantPersonName moneyTransferParticipantPersonName,
			MoneyTransferParticipantPersonName dbMoneyTransferParticipantPersonName, PersistanceActionEnum action)
	{
		Assert.assertEquals("MoneyTransferParticipantPersonName firstName",
				moneyTransferParticipantPersonName.getFirstName(), dbMoneyTransferParticipantPersonName.getFirstName());
		Assert.assertEquals("MoneyTransferParticipantPersonName lastName",
				moneyTransferParticipantPersonName.getLastName(), dbMoneyTransferParticipantPersonName.getLastName());
		Assert.assertEquals("MoneyTransferParticipantPersonName motherMaidenName",
				moneyTransferParticipantPersonName.getMotherMaidenName(),
				dbMoneyTransferParticipantPersonName.getMotherMaidenName());
	}

	/**
	 * Test MoneyTransferStatus against database by id.
	 *
	 * @param dbMoneyTransferStatusList The list of MoneyTransferStatus from the db.
	 * @param moneyTransferStatus The MoneyTransferStatus inserted/updated.
	 * @param action The model action performed.
	 */
	private void assertEquals(List<MoneyTransferStatus> dbMoneyTransferStatusList,
			MoneyTransferStatus moneyTransferStatus, PersistanceActionEnum action)
	{
		boolean moneyTransferStatusFound = false;
		for (MoneyTransferStatus dbMoneyTransferStatus : dbMoneyTransferStatusList)
		{
			if (dbMoneyTransferStatus.getId().equals(moneyTransferStatus.getId()))
			{
				moneyTransferStatusFound = true;
				if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
						|| (action == PersistanceActionEnum.NONE))
				{
					assertEquals(moneyTransferStatus, dbMoneyTransferStatus, action);
					break;
				}
				else
				{
					Assert.assertTrue("DB MoneyTransferStatus still exists after DELETE", false);
				}
			}
		}

		if (!moneyTransferStatusFound
				&& ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE) ||
				(action == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB MoneyTransferStatus does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * Test MoneyTransferTransaction against database by id.
	 *
	 * @param dbMoneyTransferStatusList The list of MoneyTransferStatus from the db.
	 * @param moneyTransferTransaction The MoneyTransferTransaction deleted/inserted/updated.
	 * @param action The model action performed.
	 */
	private void assertEquals(List<MoneyTransferStatus> dbMoneyTransferStatusList,
			MoneyTransferTransaction moneyTransferTransaction, PersistanceActionEnum action)
	{
		boolean moneyTransferTransactionFound = false;
		for (MoneyTransferStatus dbMoneyTransferStatus : dbMoneyTransferStatusList)
		{
			if (!ValidationUtil.isNull(dbMoneyTransferStatus.getMoneyTransferTransaction()))
			{
				if (dbMoneyTransferStatus.getMoneyTransferTransaction().getId()
						.equals(moneyTransferTransaction.getId()))
				{
					moneyTransferTransactionFound = true;
					if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
							|| (action == PersistanceActionEnum.NONE))
					{
						assertEquals(moneyTransferTransaction, dbMoneyTransferStatus.getMoneyTransferTransaction(),
								action);
						break;
					}
					else
					{
						Assert.assertTrue("DB MoneyTransferTransaction still exists after DELETE", false);
					}
				}
			}
		}

		if (!moneyTransferTransactionFound
				&& ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE) ||
				(action == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB MoneyTransferTransaction does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two MoneyTransferStatus objects.
	 *
	 * @param moneyTransferStatus The MoneyTransferStatus inserted/updated.
	 * @param dbMoneyTransferStatus The DB MoneyTransferStatus fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferStatus moneyTransferStatus, MoneyTransferStatus dbMoneyTransferStatus,
			PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModel(moneyTransferStatus, dbMoneyTransferStatus, action);
		Assert.assertEquals("MoneyTransferStatus moneyTransferId", moneyTransferStatus.getMoneyTransferId(),
				dbMoneyTransferStatus.getMoneyTransferId());
		Assert.assertEquals("MoneyTransferStatus status", moneyTransferStatus.getStatusValue(),
				dbMoneyTransferStatus.getStatusValue());
		assertEquals(moneyTransferStatus.getMoneyTransferTransaction(),
				dbMoneyTransferStatus.getMoneyTransferTransaction(), action);
		assertEquals(moneyTransferStatus.getResponse(), dbMoneyTransferStatus.getResponse(), action);
		assertEquals(moneyTransferStatus.getMoneyTransferTransaction(),
				dbMoneyTransferStatus.getMoneyTransferTransaction(), action);
	}

	/**
	 * This method compares fields between two MoneyTransferTransaction objects.
	 *
	 * @param moneyTransferTransaction The MoneyTransferTransaction inserted/updated.
	 * @param dbMoneyTransferTransaction The DB MoneyTransferTransaction fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferTransaction moneyTransferTransaction,
			MoneyTransferTransaction dbMoneyTransferTransaction, PersistanceActionEnum action)
	{
		if (!ValidationUtil.isNull(moneyTransferTransaction))
		{
			Assert.assertNotNull("MoneyTransferTransaction DB is null, but expected one", dbMoneyTransferTransaction);

			CommonTestRoutines.assertFieldsQATModel(moneyTransferTransaction, dbMoneyTransferTransaction, action);
			Assert.assertEquals("MoneyTransferTransaction key", moneyTransferTransaction.getKey(),
					dbMoneyTransferTransaction.getKey());
			Assert.assertEquals("MoneyTransferTransaction achPayeeCode", moneyTransferTransaction.getAchPayeeCode(),
					dbMoneyTransferTransaction.getAchPayeeCode());
			Assert.assertEquals("MoneyTransferTransaction confirmationNumber",
					moneyTransferTransaction.getConfirmationNumber(),
					dbMoneyTransferTransaction.getConfirmationNumber());
			assertEquals(moneyTransferTransaction.getDestinationAmount(),
					dbMoneyTransferTransaction.getDestinationAmount(), action);
			Assert.assertTrue("MoneyTransferTransaction foreignExchangeRateCustomer", moneyTransferTransaction
					.getForeignExchangeRateCustomer()
					.compareTo(dbMoneyTransferTransaction.getForeignExchangeRateCustomer()) == 0);
			Assert.assertEquals("MoneyTransferTransaction moneyTransferStatusId",
					moneyTransferTransaction.getMoneyTransferStatusId(),
					dbMoneyTransferTransaction.getMoneyTransferStatusId());
			assertEquals(moneyTransferTransaction.getOriginAmount(), dbMoneyTransferTransaction.getOriginAmount(),
					action);
			Assert.assertEquals("MoneyTransferTransaction paymentType", moneyTransferTransaction.getPaymentTypeValue(),
					dbMoneyTransferTransaction.getPaymentTypeValue());
			Assert.assertEquals("MoneyTransferTransaction releaseUser", moneyTransferTransaction.getReleaseUser(),
					dbMoneyTransferTransaction.getReleaseUser());
			Assert.assertTrue("MoneyTransferAmount originFlatFee",
					moneyTransferTransaction.getOriginFlatFee()
							.compareTo(dbMoneyTransferTransaction.getOriginFlatFee()) == 0);
			// Currently, these attributes aren't carried on the db. As a result, we null them out here.
			moneyTransferTransaction.getRecipient().getAddress().setCity(null);
			moneyTransferTransaction.getRecipient().getAddress().setPostalCode(null);
			moneyTransferTransaction.getRecipient().getName().setMotherMaidenName(null);
			assertEquals(moneyTransferTransaction.getRecipient(), dbMoneyTransferTransaction.getRecipient(), action);
			// Currently, these attributes aren't carried on the db. As a result, we null them out here.
			moneyTransferTransaction.getSender().getName().setMotherMaidenName(null);
			assertEquals(moneyTransferTransaction.getSender(), dbMoneyTransferTransaction.getSender(), action);
		}
		else
		{
			Assert.assertNull("MoneyTransferTransaction DB is not null, but expected null", dbMoneyTransferTransaction);
		}
	}

	/**
	 * Test Note against database by id.
	 *
	 * @param dbNoteList The list of Note from the db.
	 * @param note The Note inserted/updated.
	 * @param action The model action performed.
	 */
	private void assertEquals(List<Note> dbNoteList, Note note, PersistanceActionEnum action)
	{
		boolean noteFound = false;
		for (Note dbNote : dbNoteList)
		{
			if (dbNote.getId().equals(note.getId()))
			{
				noteFound = true;
				if ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE)
						|| (action == PersistanceActionEnum.NONE))
				{
					assertEquals(note, dbNote, action);
					break;
				}
				else
				{
					Assert.assertTrue("DB Note still exists after DELETE", false);
				}
			}
		}

		if (!noteFound && ((action == PersistanceActionEnum.INSERT) || (action == PersistanceActionEnum.UPDATE) ||
				(action == PersistanceActionEnum.NONE)))
		{
			Assert.assertTrue("DB Note does not exists after INSERT/UPDATE/NONE", false);
		}
	}

	/**
	 * This method compares fields between two Note objects.
	 *
	 * @param note The Note inserted/updated.
	 * @param dbNote The DB Note fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(Note note, Note dbNote, PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModel(note, dbNote, action);
		Assert.assertEquals("Note noteText", note.getNoteText(), dbNote.getNoteText());
		Assert.assertEquals("Note parentKey", note.getParentKey(), dbNote.getParentKey());
	}

	/**
	 * This method compares fields between two MoneyTransfer objects.
	 *
	 * @param response The response inserted/updated.
	 * @param dbResponse The DB response fetched.
	 * @param action The model action performed.
	 */
	private void assertEquals(MoneyTransferProcessingResponse response, MoneyTransferProcessingResponse dbResponse,
			PersistanceActionEnum action)
	{
		if (!ValidationUtil.isNull(response))
		{
			if (!ValidationUtil.isNull(dbResponse))
			{
				Assert.assertEquals("Response operationSuccess", response.isOperationSuccess(),
						dbResponse.isOperationSuccess());

				if (!ValidationUtil.isNull(response.getMessageList()))
				{
					if (!ValidationUtil.isNull(dbResponse.getMessageList()))
					{
						Assert.assertTrue("Response messageList size", response.getMessageList().size() == dbResponse
								.getMessageList().size());

						int i = 0;
						for (Message message : response.getMessageList())
						{
							assertEquals(message, dbResponse.getMessageList().get(i), action);
							i++;
						}
					}
					else
					{
						Assert.assertTrue("Response messageList is not null, DB messagelist is null", false);
					}
				}
				else
				{
					Assert.assertNull("Response messageList is null, DB messagelist is not null",
							dbResponse.getMessageList());
				}
			}
			else
			{
				Assert.assertTrue("Response is not null, DB response is null", false);
			}
		}
		else
		{
			Assert.assertNull("Response is null, DB response is not null", dbResponse);
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria member.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransferList The list of MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransfer(MoneyTransferCriteria criteria, List<MoneyTransfer> moneyTransferList)
	{
		Assert.assertTrue("MoneyTransfer Fetch contains 0", moneyTransferList.size() > 0);

		for (MoneyTransfer moneyTransfer : moneyTransferList)
		{
			assertFilterMoneyTransferMember(criteria, moneyTransfer);
			assertFilterMoneyTransferConfirmationNumber(criteria, moneyTransfer);
			assertFilterMoneyTransferPayerId(criteria, moneyTransfer, moneyTransfer.getTransferSetting()
					.getProductPlanApplicability());
			assertFilterMoneyTransferStatusList(criteria, moneyTransfer);
			assertFilterMoneyTransferBatchDateUTC(criteria, moneyTransfer);
			assertFilterMoneyTransferBatchId(criteria, moneyTransfer);
			assertFilterMoneyTransferTransactionId(criteria, moneyTransfer);
			assertFilterMoneyTransferCurrencyCode(criteria, moneyTransfer);
			assertFilterMoneyTransferLocationName(criteria, moneyTransfer);
			assertFilterMoneyTransferOrganizationName(criteria, moneyTransfer);
			assertFilterMoneyTransferPrimaryPhoneNumber(criteria, moneyTransfer);
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria money transfer batch id attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferBatchId(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getMoneyTransferBatchId()))
		{
			Assert.assertEquals("MoneyTransferCriteria moneyTransferBatchId", criteria.getMoneyTransferBatchId(),
					moneyTransfer.getMoneyTransferBatchId());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria money transfer batch date utc (previous
	 * transaction).
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferBatchDateUTC(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getCreateDateUTC()))
		{
			Assert.assertNotEquals("MoneyTransferCriteria moneyTransferBatchDateUTC",
					criteria.getCreateDateUTC(), moneyTransfer.getCreateDateUTC());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria confirmation number attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferConfirmationNumber(MoneyTransferCriteria criteria,
			MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getConfirmationNumber()))
		{
			Assert.assertEquals("MoneyTransferCriteria confirmationNumber", criteria.getConfirmationNumber(),
					moneyTransfer.getConfirmationNumber());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria currency code attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferCurrencyCode(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getCurrencyCode()))
		{
			Assert.assertEquals("MoneyTransferCriteria currencyCode", criteria.getCurrencyCode(), moneyTransfer
					.getDestinationAmount().getCurrency().getCode());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria location name attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferLocationName(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getLocationName()))
		{
			Assert.assertEquals("MoneyTransferCriteria locationName", criteria.getLocationName(), moneyTransfer
					.getTransferSetting().getEmploymentInfo().getLocationName());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria member attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferMember(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getMember()))
		{
			if (!ValidationUtil.isNull(criteria.getMember().getId()))
			{
				Assert.assertEquals("MoneyTransferCriteria memberId", criteria.getMember().getId(), moneyTransfer
						.getMoneyTransferDetail().getMember().getId());
			}
			if (!ValidationUtil.isNull(criteria.getMember().getFirstName()))
			{
				Assert.assertEquals("MoneyTransferCriteria memberFirstName", criteria.getMember().getFirstName(),
						moneyTransfer.getMoneyTransferDetail().getMember().getFirstName());
			}
			if (!ValidationUtil.isNull(criteria.getMember().getLastName()))
			{
				Assert.assertEquals("MoneyTransferCriteria memberLastName", criteria.getMember().getLastName(),
						moneyTransfer.getMoneyTransferDetail().getMember().getLastName());
			}
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria organization name attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferOrganizationName(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getOrganizationName()))
		{
			Assert.assertEquals("MoneyTransferCriteria organizationName", criteria.getOrganizationName(), moneyTransfer
					.getTransferSetting().getEmploymentInfo().getOrganizationName());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria payer id attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 * @param productPlanApplicability The product plan applicability.
	 */
	private void assertFilterMoneyTransferPayerId(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer,
			ProductPlanApplicability productPlanApplicability)
	{
		if (!ValidationUtil.isNull(criteria.getPayerId()))
		{
			Assert.assertEquals("MoneyTransferCriteria payerId", criteria.getPayerId(), productPlanApplicability
					.getPayer().getId());
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria primary phone number attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferPrimaryPhoneNumber(MoneyTransferCriteria criteria,
			MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getPrimaryPhoneNumber()))
		{
			Boolean phoneMatch = false;
			for (Contact contact : moneyTransfer.getMoneyTransferDetail().getMember().getContactList())
			{
				switch (contact.getContactType())
				{
					case PHONE_WORK:
						if (((Phone)contact).getNumber().equals(criteria.getPrimaryPhoneNumber()))
						{
							phoneMatch = true;
						}
						break;
					default:
						break;
				}
			}
			Assert.assertTrue("MoneyTransferCriteria phone not matched", phoneMatch);
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria status list id attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferStatusList(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNullOrEmpty(criteria.getStatusList()))
		{
			boolean statusMatch = false;
			for (MoneyTransferStatusEnum status : criteria.getStatusList())
			{
				if (moneyTransfer.getCurrentStatus().getStatus() == status)
				{
					statusMatch = true;
					break;
				}
			}
			Assert.assertTrue("MoneyTransferCriteria status not matched", statusMatch);
		}
	}

	/**
	 * Compares the fetch result for a MoneyTransfer against the criteria transaction id attribute.
	 *
	 * @param criteria The criteria for the fetch.
	 * @param moneyTransfer The MoneyTransfer returned from the fetch.
	 */
	private void assertFilterMoneyTransferTransactionId(MoneyTransferCriteria criteria, MoneyTransfer moneyTransfer)
	{
		if (!ValidationUtil.isNull(criteria.getTransactionId()))
		{
			Assert.assertEquals("MoneyTransferCriteria transactionId", criteria.getTransactionId(),
					moneyTransfer.getKey());
		}
	}

	/**
	 * Asserts that the list of MoneyTransfer objects is sorted correctly.
	 *
	 * @param moneyTransferList The sorted list of MoneyTransfer objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertSortMoneyTransfer(List<MoneyTransfer> moneyTransferList, String fieldName, Direction direction)
	{
		boolean firstTime = true;
		MoneyTransfer previousMoneyTransfer = null;

		for (MoneyTransfer moneyTransfer : moneyTransferList)
		{
			if (!firstTime)
			{
				switch (fieldName)
				{
					case MEMBER_LAST_NAME_SORT_FIELD:
						CommonTestRoutines.assertAttributeSort(previousMoneyTransfer.getMoneyTransferDetail()
								.getMember()
								.getLastName(), moneyTransfer.getMoneyTransferDetail().getMember().getLastName(),
								direction);
						break;
					case PAYER_NAME_SORT_FIELD:
						CommonTestRoutines.assertAttributeSort(previousMoneyTransfer.getTransferSetting()
								.getProductPlanApplicability().getPayer().getName(), moneyTransfer.getTransferSetting()
								.getProductPlanApplicability().getPayer().getName(), direction);
						break;
					case STATUS_SORT_FIELD:
						CommonTestRoutines.assertAttributeSort(previousMoneyTransfer.getCurrentStatus()
								.getStatusValue().toString(), moneyTransfer.getCurrentStatus().getStatusValue()
								.toString(), direction);
						break;
					default:
						Assert.assertTrue("Unknown MoneyTransfer sort field: " + fieldName, false);
						break;
				}
			}

			firstTime = false;
			previousMoneyTransfer = moneyTransfer;
		}
	}

	/**
	 * Test MoneyTransfer against database by id.
	 *
	 * @param moneyTransfer The MoneyTransfer
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransfer moneyTransfer, PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferDAC().fetchMoneyTransferById(moneyTransfer.getId());
		assertFetch(response, action);
		if (action != PersistanceActionEnum.DELETE)
		{
			assertEquals(moneyTransfer, response.getFirstResult(), action);
		}
	}

	/**
	 * Test Note database by id.
	 *
	 * @param moneyTransfer The MoneyTransfer.
	 * @param note The Note.
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransfer moneyTransfer, Note note, PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferDAC().fetchMoneyTransferById(moneyTransfer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertEquals(response.getFirstResult().getNoteList(), note, action);
	}

	/**
	 * Test MoneyTransferStatus database by id.
	 *
	 * @param moneyTransfer The MoneyTransfer.
	 * @param moneyTransferStatus The MoneyTransferStatus.
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransfer moneyTransfer, MoneyTransferStatus moneyTransferStatus,
			PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferDAC().fetchMoneyTransferById(moneyTransfer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertEquals(response.getFirstResult().getStatusList(), moneyTransferStatus, action);
	}

	/**
	 * Test MoneyTransferStatus database by id.
	 *
	 * @param moneyTransfer The MoneyTransfer.
	 * @param moneyTransferTransaction The MoneyTransferTransaction.
	 * @param action The model action performed.
	 */
	private void compare(MoneyTransfer moneyTransfer, MoneyTransferTransaction moneyTransferTransaction,
			PersistanceActionEnum action)
	{
		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferDAC().fetchMoneyTransferById(moneyTransfer.getId());
		CommonTestRoutines.assertResultResponse(response);
		assertEquals(response.getFirstResult().getStatusList(), moneyTransferTransaction, action);
	}

	/**
	 * Insert MoneyTransferBatches.
	 *
	 * @param numberOfMoneyTransferBatchesToInsert The number of MoneyTransferBatches to insert
	 * @return The {@link List} of {@link MoneyTransferBatch}.
	 */
	private List<MoneyTransferBatch> insertMoneyTransferBatches(Integer numberOfMoneyTransferBatchesToInsert)
	{
		ArrayList<MoneyTransferBatch> moneyTransferBatchList = new ArrayList<MoneyTransferBatch>();
		MoneyTransferBatch moneyTransferBatch = null;
		for (int i = 0; i < numberOfMoneyTransferBatchesToInsert; i++)
		{
			moneyTransferBatch = insertMoneyTransferBatch(i + 1);
			if (!ValidationUtil.isNull(moneyTransferBatch))
			{
				moneyTransferBatchList.add(moneyTransferBatch);
			}
		}
		return moneyTransferBatchList;
	}

	/**
	 * Convenience method for returning a default inquiry request.
	 *
	 * @return The request.
	 */
	private MoneyTransferInquiryRequest obtainDefaultMoneyTransferInquiryRequest()
	{
		MoneyTransferInquiryRequest request = new MoneyTransferInquiryRequest();
		request.setPageSize(BIG_PAGE_SIZE);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();
		MoneyTransferCriteria criteria = new MoneyTransferCriteria();
		request.setCriteria(criteria);
		return request;
	}

	/** Sets up the database. */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
	}

	/** Test successful delete of a money transfer. */
	@Test
	public void testDeleteMoneyTransfer()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.setModelAction(PersistanceActionEnum.DELETE);
		getMoneyTransferDAC().deleteMoneyTransfer(moneyTransfer);
		compare(moneyTransfer, PersistanceActionEnum.DELETE);
	}

	/** Test a delete of a money transfer with an Optimistic Locking error. */
	@Test
	public void testDeleteMoneyTransferOptimisticLocking()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
		moneyTransfer.setModelAction(PersistanceActionEnum.DELETE);
		moneyTransfer.setVersion(0);
		InternalResponse response = getMoneyTransferDAC().deleteMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.OptimisticLockingError, response.getStatus());
	}

	/** Test successful fetch of a money transfer by id. */
	@Test
	public void testfetchMoneyTransferById()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(2);

		InternalResultsResponse<MoneyTransfer> response =
				getMoneyTransferDAC().fetchMoneyTransferById(
						moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getId());
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue("MoneyTransfer fetch by id must return exactly 1 object",
				response.getResultsList().size() == 1);
		compare(response.getFirstResult(), PersistanceActionEnum.FETCH);
	}

	/** Test successful fetch of money transfer by request, no criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequest()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/**
	 * Test successful fetch of money transfer batch by request, confirmation number is specified in the criteria
	 * specified.
	 */
	@Test
	public void testFetchMoneyTransferByRequestConfirmationNumber()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.getCriteria().setConfirmationNumber(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getConfirmationNumber());
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, member id specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestMemberId()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		Member member = new Member();
		member.setId(moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getTransferSetting()
				.getMemberId());
		request.getCriteria().setMember(member);

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, recipient id specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestRecipientId()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		request.getCriteria().setRecipientId(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getTransferSetting().getRecipientId());

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/**
	 * Test successful fetch of money transfer by request, money transfer batch id to ignore specified in the criteria
	 * specified.
	 */
	@Test
	public void testFetchMoneyTransferByRequestDateUTC()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		// add two days to the current date
		request.getCriteria().setCreateDateUTC(PGSiDateUtil.addDays(PGSiDateUtil.getCurrentDateMillisUTC(), 2));
		request.getCriteria().setMember(new Member());
		request.getCriteria().getMember().setId(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getTransferSetting().getMemberId());
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, payer id specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestPayerId()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.getCriteria().setPayerId(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getTransferSetting()
						.getProductPlanApplicability().getPayer().getId());
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, member first name specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestMemberFirstName()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		Member member = new Member();
		InternalResultsResponse<Member> memberResponse =
				getPersonDAC().fetchMemberById(
						new FetchByIdRequest(moneyTransferBatchList.get(0).getMoneyTransferList().get(0)
								.getTransferSetting()
								.getMemberId()));
		member.setFirstName(memberResponse.getFirstResult().getFirstName());
		request.getCriteria().setMember(member);

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, member last name specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestMemberLastName()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		Member member = new Member();
		InternalResultsResponse<Member> memberResponse =
				getPersonDAC().fetchMemberById(
						new FetchByIdRequest(moneyTransferBatchList.get(0).getMoneyTransferList().get(0)
								.getTransferSetting()
								.getMemberId()));
		member.setLastName(memberResponse.getFirstResult().getLastName());
		request.getCriteria().setMember(member);

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, transaction id specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestTransactionId()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		request.getCriteria().setTransactionId(moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getKey());

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, currency code specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestCurrencyCode()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		request.getCriteria().setCurrencyCode(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getDestinationAmount().getCurrency()
						.getCode());

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, locationName specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestLocationName()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		request.getCriteria().setLocationName(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getTransferSetting().getEmploymentInfo()
						.getLocationName());

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, organization name specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestOrganizationName()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		request.getCriteria().setOrganizationName(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getTransferSetting().getEmploymentInfo()
						.getOrganizationName());

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, primary phone number specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestPrimaryPhoneNumber()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();

		InternalResultsResponse<Member> memberResponse =
				getPersonDAC().fetchMemberById(
						new FetchByIdRequest(moneyTransferBatchList.get(0).getMoneyTransferList().get(0)
								.getTransferSetting()
								.getMemberId()));

		for (Contact contact : memberResponse.getFirstResult().getContactList())
		{
			switch (contact.getContactType())
			{
				case PHONE_WORK:
					Phone phone = (Phone)contact;
					request.getCriteria().setPrimaryPhoneNumber(phone.getNumber());
					break;
				default:
					break;
			}
		}

		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, sorted by default order. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByDefault()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer by request, sorted by member last name ascending order. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByMemberLastNameAsc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(MEMBER_LAST_NAME_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), MEMBER_LAST_NAME_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer by request, sorted by member last name descendingorder. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByMemberLastNameDesc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(MEMBER_LAST_NAME_SORT_FIELD, Direction.Descending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), MEMBER_LAST_NAME_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of money transfer by request, sorted by payer name ascending order. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByPayerNameAsc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(PAYER_NAME_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), PAYER_NAME_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer by request, sorted by payer name descending order. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByPayerNameDesc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(PAYER_NAME_SORT_FIELD, Direction.Descending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), PAYER_NAME_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of money transfer by request, sorted by status ascending order. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByStatusAsc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(STATUS_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test successful fetch of money transfer by request, sorted by status name ascending order, with
	 * paging.
	 */
	@Test
	public void testFetchMoneyTransferByRequestSortedByStatusAscPaging()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT_PAGING);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.setPageSize(PAGE_SIZE);
		request.addSortExpressions(new SortExpression(STATUS_SORT_FIELD, Direction.Ascending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
		MoneyTransfer lastMoneyTransferFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// go to next page
		request.setStartPage(1);
		response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		CommonTestRoutines.assertAttributeSort(lastMoneyTransferFetched.getCurrentStatus().getStatusValue().toString(),
				response.getFirstResult().getCurrentStatus().getStatusValue().toString(), Direction.Ascending);
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
		lastMoneyTransferFetched = response.getResultsList().get(PAGE_SIZE - 1);

		// one more page
		request.setStartPage(2);
		response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, PAGE_SIZE == response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
		CommonTestRoutines.assertAttributeSort(lastMoneyTransferFetched.getCurrentStatus().getStatusValue().toString(),
				response.getFirstResult().getCurrentStatus().getStatusValue().toString(), Direction.Ascending);
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Ascending);
	}

	/** Test successful fetch of money transfer by request, sorted by status descending order. */
	@Test
	public void testFetchMoneyTransferByRequestSortedByStatusDesc()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.addSortExpressions(new SortExpression(STATUS_SORT_FIELD, Direction.Descending));
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		Assert.assertTrue(NUMBER_OF_ROWS_NEED_TO_MATCH, BIG_PAGE_SIZE >= response.getResultsList().size());
		assertSortMoneyTransfer(response.getResultsList(), STATUS_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of money transfer by request, status specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestStatus()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.getCriteria().setStatusList(new ArrayList<MoneyTransferStatusEnum>());
		request.getCriteria().getStatusList()
				.add(moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getStatusList().get(0).getStatus());
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, money transfer specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestMoneyTransferBatchId()
	{
		List<MoneyTransferBatch> moneyTransferBatchList = insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);

		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.getCriteria().setStatusList(new ArrayList<MoneyTransferStatusEnum>());
		request.getCriteria().setMoneyTransferBatchId(
				moneyTransferBatchList.get(0).getMoneyTransferList().get(0).getMoneyTransferBatchId());
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMoneyTransfer(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of money transfer by request, status specified in the criteria but not found. */
	@Test
	public void testFetchMoneyTransferByRequestStatusNotFound()
	{
		insertMoneyTransferBatches(MONEY_TRANSFER_BATCHES_TO_INSERT);
		MoneyTransferInquiryRequest request = obtainDefaultMoneyTransferInquiryRequest();
		request.getCriteria().setStatusList(new ArrayList<MoneyTransferStatusEnum>());
		request.getCriteria().getStatusList().add(MoneyTransferStatusEnum.CANCELLED);
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().fetchMoneyTransferByRequest(request);
		Assert.assertEquals("MoneyTransfer Response status should be NoRowsFoundError", Status.NoRowsFoundError,
				response.getStatus());
		Assert.assertTrue("MoneyTransfer Response result count should be zero", response.getResultsList().size() == 0);
	}

	/** Test successful insert of a MoneyTransfer. */
	@Test
	public void testInsertMoneyTransfer()
	{
		compare(insertMoneyTransfer(1), PersistanceActionEnum.INSERT);
	}

	/** Test successful insert of a MoneyTransferStatus. */
	@Test
	public void testInsertMoneyTransferStatus()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		compare(moneyTransfer, PersistanceActionEnum.INSERT);

		MoneyTransferStatus moneyTransferStatus =
				CommonTestRoutines.createDummyMoneyTransferStatus(MoneyTransferStatusEnum.APPROVED);
		moneyTransferStatus.setMoneyTransferId(moneyTransfer.getId());

		InternalResponse response = getMoneyTransferDAC().insertMoneyTransferStatus(moneyTransferStatus);
		CommonTestRoutines.assertResponse(response);
		compare(moneyTransfer, moneyTransferStatus, PersistanceActionEnum.INSERT);
	}

	/** Test successful update of a money transfer. */
	@Test
	public void testUpdateMoneyTransfer()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getRecipientAccount().setType(AccountTypeEnum.INTEREST_CHECKING);
		CommonTestRoutines.assignQATModelUpdateFields(moneyTransfer);
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, PersistanceActionEnum.UPDATE);
	}

	/** Test successful update and fetch of a money transfer. */
	@Test
	public void testUpdateFetchMoneyTransfer()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getRecipientAccount().setType(AccountTypeEnum.INTEREST_CHECKING);
		CommonTestRoutines.assignQATModelUpdateFields(moneyTransfer);
		InternalResultsResponse<MoneyTransfer> response = getMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);
		compare(response.getFirstResult(), PersistanceActionEnum.UPDATE);
	}

	/** Test an update and fetch of a money transfer with an Optimistic Locking error. */
	@Test
	public void testUpdateFetchMoneyTransferOptimisticLocking()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.setVersion(0);
		InternalResponse response = getMoneyTransferDAC().updateFetchMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.OptimisticLockingError, response.getStatus());
	}

	/** Test successful update of a money transfer, deleting a transaction. */
	@Test
	public void testUpdateMoneyTransferDeleteMoneyTransferTransaction()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getStatusList().get(0).getMoneyTransferTransaction().setModelAction(PersistanceActionEnum.DELETE);
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, moneyTransfer.getStatusList().get(0).getMoneyTransferTransaction(),
				PersistanceActionEnum.DELETE);
	}

	/** Test successful update of a money transfer, deleting a note. */
	@Test
	public void testUpdateMoneyTransferDeleteNote()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getNoteList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, moneyTransfer.getNoteList().get(0), PersistanceActionEnum.DELETE);
	}

	/** Test successful update of a money transfer, deleting a status. */
	@Test
	public void testUpdateMoneyTransferDeleteStatus()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getStatusList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, moneyTransfer.getStatusList().get(0), PersistanceActionEnum.DELETE);
	}

	/** Test an update of a money transfer with an Optimistic Locking error. */
	@Test
	public void testUpdateMoneyTransferOptimisticLocking()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
		moneyTransfer.setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.setVersion(0);
		InternalResponse response = getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.OptimisticLockingError, response.getStatus());
	}

	/** Test successful update of a money transfer, and update of a transaction. */
	@Test
	public void testUpdateMoneyTransferUpdateMoneyTransferTransaction()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getStatusList().get(0).getMoneyTransferTransaction().setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.getStatusList().get(0).getMoneyTransferTransaction().setAchPayeeCode("999");
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, moneyTransfer.getStatusList().get(0), PersistanceActionEnum.UPDATE);
	}

	/** Test successful update of a money transfer, and update of a note. */
	@Test
	public void testUpdateMoneyTransferUpdateNote()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getNoteList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.getNoteList().get(0).setNoteText("Text");
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, moneyTransfer.getNoteList().get(0), PersistanceActionEnum.UPDATE);
	}

	/** Test successful update of a money transfer, and update of a note where it isn't found. */
	@Test
	public void testUpdateMoneyTransferUpdateNoteNotFound()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getNoteList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.getNoteList().get(0).setNoteText("TextA");
		moneyTransfer.getNoteList().get(0).setId(0);
		InternalResponse response = getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.NoRowsUpdatedError, response.getStatus());
	}

	/** Test successful update of a money transfer, and update of a status. */
	@Test
	public void testUpdateMoneyTransferUpdateStatus()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getStatusList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.getStatusList().get(0).setStatus(MoneyTransferStatusEnum.PAID);
		CommonTestRoutines.assertResponse(getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer));
		compare(moneyTransfer, moneyTransfer.getStatusList().get(0), PersistanceActionEnum.UPDATE);
	}

	/** Test successful update of a money transfer, and update of a status where it isn't found. */
	@Test
	public void testUpdateMoneyTransferUpdateStatusNotFound()
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);
		moneyTransfer.getStatusList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		moneyTransfer.getStatusList().get(0).setStatus(MoneyTransferStatusEnum.PAID);
		moneyTransfer.getStatusList().get(0).setId(0);
		InternalResponse response = getMoneyTransferDAC().updateMoneyTransfer(moneyTransfer);
		CommonTestRoutines.assertResponse(response);
		assertEquals(Status.NoRowsUpdatedError, response.getStatus());
	}
}