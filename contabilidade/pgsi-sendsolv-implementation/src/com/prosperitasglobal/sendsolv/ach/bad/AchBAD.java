package com.prosperitasglobal.sendsolv.ach.bad;

import org.springframework.util.Assert;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipant;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantAddress;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantPersonName;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.MoneyTransferMaintenanceRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.ValidationUtil;

public final class AchBAD
{
	/**
	 * Final class with only static methods should not be instantiated.
	 */
	private AchBAD()
	{
	}

	public static MoneyTransferTransaction createMoneyTransferTransaction(MoneyTransferMaintenanceRequest request)
	{
		MoneyTransferTransaction transaction = new MoneyTransferTransaction();

		applyModelObjectProperties(transaction, request.getUserContext());

		MoneyTransfer transfer = request.getMoneyTransfer();

		transaction.setKey(transfer.getKey());
		transaction.setAchPayeeCode(extractPayeeCode(transfer.getTransferSetting()));
		transaction.setConfirmationNumber(transfer.getConfirmationNumber());
		transaction.setDestinationAmount(transfer.getDestinationAmount());
		transaction.setForeignExchangeRateCustomer(transfer.getForeignExchangeRateCustomer());
		transaction.setOriginAmount(transfer.getOriginAmount());
		transaction.setPaymentType(transfer.getPaymentType());

		MoneyTransferParticipant recipient = createRecipient(request);

		transaction.setRecipient(recipient);

		MoneyTransferParticipant sender = createSender(request);

		transaction.setSender(sender);

		transaction.setOriginFlatFee(transfer.getOriginFlatFee());

		transaction.setReleaseUser(transfer.getReleaseUser());

		return transaction;
	}

	public static MoneyTransferStatus createMoneyTransferStatus(MoneyTransferMaintenanceRequest request,
			MoneyTransferStatusEnum status)
	{
		MoneyTransferStatus transferStatus = new MoneyTransferStatus();
		applyModelObjectProperties(transferStatus, request.getUserContext());
		transferStatus.setStatus(status);
		transferStatus.setMoneyTransferId(request.getMoneyTransfer().getId());
		return transferStatus;
	}

	public static MoneyTransferStatus createMoneyTransferStatus(MoneyTransferMaintenanceRequest request,
			MoneyTransferTransaction transaction, MoneyTransferStatusEnum status)
	{
		MoneyTransferStatus transferStatus = new MoneyTransferStatus();
		applyModelObjectProperties(transferStatus, request.getUserContext());
		transferStatus.setStatus(status);
		transferStatus.setMoneyTransferTransaction(transaction);
		transferStatus.setMoneyTransferId(request.getMoneyTransfer().getId());
		return transferStatus;
	}

	private static void applyModelObjectProperties(QATModel modelObject, UserContext userContext)
	{
		modelObject.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		modelObject.setCreateUser(userContext.getUserId());
		modelObject.setModelAction(PersistanceActionEnum.INSERT);
	}

	private static MoneyTransferParticipant createRecipient(MoneyTransferMaintenanceRequest request)
	{
		MoneyTransferParticipant mtRecipient = new MoneyTransferParticipant();

		applyModelObjectProperties(mtRecipient, request.getUserContext());

		MoneyTransfer transfer = request.getMoneyTransfer();

		mtRecipient.setAccount(transfer.getRecipientAccount());

		Recipient recipient = transfer.getMoneyTransferDetail().getRecipient();

		applyNameAndAddress(mtRecipient, request.getUserContext(), recipient);
		applyPhoneNumber(mtRecipient, request.getUserContext(), recipient);

		return mtRecipient;
	}

	private static MoneyTransferParticipant createSender(MoneyTransferMaintenanceRequest request)
	{
		MoneyTransferParticipant mtSender = new MoneyTransferParticipant();

		applyModelObjectProperties(mtSender, request.getUserContext());

		MoneyTransfer transfer = request.getMoneyTransfer();

		mtSender.setAccount(transfer.getSenderAccount());

		Member sender = transfer.getMoneyTransferDetail().getMember();

		applyNameAndAddress(mtSender, request.getUserContext(), sender);
		applyPhoneNumber(mtSender, request.getUserContext(), sender);

		return mtSender;
	}

	private static void applyNameAndAddress(MoneyTransferParticipant mtParticipant, UserContext userContext,
			Person person)
	{
		MoneyTransferParticipantPersonName name = new MoneyTransferParticipantPersonName();

		mtParticipant.setName(name);

		applyModelObjectProperties(name, userContext);

		name.setFirstName(person.getFirstName());

		name.setLastName(person.getLastName());

		if (!ValidationUtil.isNullOrEmpty(person.getNameList()))
		{
			name.setMotherMaidenName(person.getNameList().get(0).getOtherName());
		}

		Address address = null;

		for (Contact contact : person.getContactList())
		{
			if (contact.getContactType().equals(ContactTypeEnum.POSTAL_HOME))
			{
				address = (Address)contact;
				break;
			}
		}

		if (ValidationUtil.isNull(address))
		{
			for (Contact contact : person.getContactList())
			{
				if (contact.getContactType().equals(ContactTypeEnum.POSTAL_WORK))
				{
					address = (Address)contact;
					break;
				}
			}
		}

		if (!ValidationUtil.isNull(address))
		{
			MoneyTransferParticipantAddress mtAddress = new MoneyTransferParticipantAddress();

			mtAddress.setAddress(concatenateAddressLines(address));
			mtAddress.setCity(address.getCityName());
			mtAddress.setCountry(address.getCountry());
			mtAddress.setStateProvinceRegion(address.getStateProvinceRegion());
			mtAddress.setPostalCode(address.getPostalCode());

			mtParticipant.setAddress(mtAddress);
		}
	}

	private static void applyPhoneNumber(MoneyTransferParticipant mtParticipant, UserContext userContext, Person person)
	{
		Phone phone = null;

		// Attempt to get the mobile number first.
		for (Contact contact : person.getContactList())
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
			for (Contact contact : person.getContactList())
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
			for (Contact contact : person.getContactList())
			{
				if (contact.getContactType().equals(ContactTypeEnum.PHONE_WORK))
				{
					phone = (Phone)contact;
					break;
				}
			}
		}

		mtParticipant.setPhoneNumber(phone.getNumber());
	}

	private static String concatenateAddressLines(Address address)
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

	private static String extractPayeeCode(TransferSetting transferSetting)
	{
		Assert.notNull(transferSetting, "TransferSetting is null in AchBAD.extractPayeeCode");
		ProductPlanApplicability ppa = transferSetting.getProductPlanApplicability();
		Assert.notNull(ppa, "ProductPlanApplicability is null in AchBAD.extractPayeeCode");
		Payer payer = ppa.getPayer();
		Assert.notNull(payer, "Payer is null in AchBAD.extractPayeeCode");
		String payeeCode = payer.getAchPayeeCode();
		Assert.notNull(payeeCode, "AchPayeeCode is null in AchBAD.extractPayeeCode");

		return payeeCode;

	}

}
