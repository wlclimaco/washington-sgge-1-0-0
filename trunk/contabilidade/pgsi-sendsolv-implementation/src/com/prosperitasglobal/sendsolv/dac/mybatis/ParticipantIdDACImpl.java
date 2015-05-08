package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.model.ContactParticipantId;
import com.prosperitasglobal.sendsolv.model.LocationParticipantId;
import com.prosperitasglobal.sendsolv.model.MemberParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantId;
import com.prosperitasglobal.sendsolv.model.OrganizationParticipantId;
import com.prosperitasglobal.sendsolv.model.RecipientParticipantId;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivityParticipantId;
import com.prosperitasglobal.sendsolv.model.TransferSettingParticipantId;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * Class is this link between the SendSolv system and the datastore which contains the last participant id for a member,
 * recipient, location, organization, and money transfer batch. Its sole purpose is to get the next available
 * participation id for those identities.
 */
public class ParticipantIdDACImpl extends SqlSessionDaoSupport implements IParticipantIdDAC
{
	/** Initial contact participant id. Will be injected by Spring. */
	private ContactParticipantId initialContactParticipantId;

	/** Initial location participant id. Will be injected by Spring. */
	private LocationParticipantId initialLocationParticipantId;

	/** Initial money transfer batch participant id. Will be injected by Spring. */
	private MoneyTransferBatchParticipantId initialMoneyTransferBatchParticipantId;

	/** Initial money transfer participant id. Will be injected by Spring. */
	private MoneyTransferParticipantId initialMoneyTransferParticipantId;

	/** Initial member participant id. Will be injected by Spring. */
	private MemberParticipantId initialMemberParticipantId;

	/** Initial organization participant id. Will be injected by Spring. */
	private OrganizationParticipantId initialOrganizationParticipantId;

	/** Initial member participant id. Will be injected by Spring. */
	private RecipientParticipantId initialRecipientParticipantId;

	/** Initial transfer setting participant id. Will be injected by Spring. */
	private TransferSettingParticipantId initialTransferSettingParticipantId;

	/** Initial suspicious activity participant id. Will be injected by Spring. */
	private SuspiciousActivityParticipantId initialSuspiciousActivityParticipantId;

	/** ParticipantId name space. */
	private static final String PARTICIPANT_ID_NAMESPACE = "ParticipantIdMap.";

	/** ParticipantId insert statement. */
	private static final String PARTICIPANT_ID_STMT_INSERT = PARTICIPANT_ID_NAMESPACE + "insertParticipantId";

	/** ParticipantId update statement. */
	private static final String PARTICIPANT_ID_STMT_UPDATE = PARTICIPANT_ID_NAMESPACE + "updateParticipantId";

	/** ContactParticipantId statements. */
	private static final String CONTACT_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchContactIdForUpdate";

	/** LocationParticipantId statements. */
	private static final String LOCATION_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchLocationIdForUpdate";

	/** MemberParticipantId statements. */
	private static final String MEMBER_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchMemberIdForUpdate";

	/** OrganizationParticipantId statements. */
	private static final String ORGANIZATION_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchOrganizationIdForUpdate";

	/** RecipientParticipantId statements. */
	private static final String RECIPIENT_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchRecipientIdForUpdate";

	/*** MoneyTransferBatchParticipantId statements. */
	private static final String MONEY_TRANSFER_BATCH_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchMoneyTransferBatchIdForUpdate";

	/** MoneyTransferParticipantId statements. */
	private static final String MONEY_TRANSFER_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchMoneyTransferIdForUpdate";

	/** TransferSettingParticipantId statements. */
	private static final String TRANSFER_SETTING_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchTransferSettingIdForUpdate";

	/** The Constant SUSPICIOUS_ACTIVITY_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE. */
	private static final String SUSPICIOUS_ACTIVITY_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE = PARTICIPANT_ID_NAMESPACE
			+ "fetchSuspiciousActivityIdForUpdate";

	/**
	 * Get the initial contact participant id. This value will be injected by Spring.
	 *
	 * @return The initial contact participant id.
	 */
	public ContactParticipantId getInitialContactParticipantId()
	{
		return initialContactParticipantId;
	}

	/**
	 * Get the initial location participant id. This value will be injected by Spring.
	 *
	 * @return The initial location participant id.
	 */
	public LocationParticipantId getInitialLocationParticipantId()
	{
		return initialLocationParticipantId;
	}

	/**
	 * Get the initial organization participant id. This value will be injected by Spring.
	 *
	 * @return The initial organization participant id.
	 */
	public OrganizationParticipantId getInitialOrganizationParticipantId()
	{
		return initialOrganizationParticipantId;
	}

	/**
	 * Get the initial money transfer batch participant id. This value will be injected by Spring.
	 *
	 * @return The initial money transfer batch participant id.
	 */
	public MoneyTransferBatchParticipantId getInitialMoneyTransferBatchParticipantId()
	{
		return initialMoneyTransferBatchParticipantId;
	}

	/**
	 * Get the initial money transfer participant id. This value will be injected by Spring.
	 *
	 * @return The initial money transfer participant id.
	 */
	public MoneyTransferParticipantId getInitialMoneyTransferParticipantId()
	{
		return initialMoneyTransferParticipantId;
	}

	/**
	 * Get the initial member participant id. This value will be injected by Spring.
	 *
	 * @return The initial member participant id.
	 */
	public MemberParticipantId getInitialMemberParticipantId()
	{
		return initialMemberParticipantId;
	}

	/**
	 * Get the initial recipient participant id. This value will be injected by Spring.
	 *
	 * @return The initial recipient participant id.
	 */
	public RecipientParticipantId getInitialRecipientParticipantId()
	{
		return initialRecipientParticipantId;
	}

	/**
	 * Get the initial transfer setting participant id. This value will be injected by Spring.
	 *
	 * @return The initial transfer setting participant id.
	 */
	public TransferSettingParticipantId getInitialTransferSettingParticipantId()
	{
		return initialTransferSettingParticipantId;
	}

	/**
	 * Get the initial suspicious activity participant id. This value will be injected by Spring.
	 *
	 * @return The initial suspicious activity participant id.
	 */
	public SuspiciousActivityParticipantId getInitialSuspiciousActivityParticipantId()
	{
		return initialSuspiciousActivityParticipantId;
	}

	/**
	 * Set the initial contact participant id. This value will be injected by Spring.
	 *
	 * @param initialContactParticipantId The initial contact participant id to set.
	 */
	public void setInitialContactParticipantId(ContactParticipantId initialContactParticipantId)
	{
		this.initialContactParticipantId = initialContactParticipantId;
	}

	/**
	 * Set the initial location participant id. This value will be injected by Spring.
	 *
	 * @param initialLocationParticipantId The initial location participant id to set.
	 */
	public void setInitialLocationParticipantId(LocationParticipantId initialLocationParticipantId)
	{
		this.initialLocationParticipantId = initialLocationParticipantId;
	}

	/**
	 * Set the initial money transfer batch participant id. This value will be injected by Spring.
	 *
	 * @param initialMoneyTransferBatchParticipantId The initial money transfer batch participant id to set.
	 */
	public void setInitialMoneyTransferBatchParticipantId(
			MoneyTransferBatchParticipantId initialMoneyTransferBatchParticipantId)
	{
		this.initialMoneyTransferBatchParticipantId = initialMoneyTransferBatchParticipantId;
	}

	/**
	 * Set the initial money transfer participant id. This value will be injected by Spring.
	 *
	 * @param initialMoneyTransferParticipantId The initial money transfer participant id to set.
	 */
	public void setInitialMoneyTransferParticipantId(MoneyTransferParticipantId initialMoneyTransferParticipantId)
	{
		this.initialMoneyTransferParticipantId = initialMoneyTransferParticipantId;
	}

	/**
	 * Set the initial member participant id. This value will be injected by Spring.
	 *
	 * @param initialMemberParticipantId The initial member participant id to set.
	 */
	public void setInitialMemberParticipantId(MemberParticipantId initialMemberParticipantId)
	{
		this.initialMemberParticipantId = initialMemberParticipantId;
	}

	/**
	 * Set the initial organization participant id. This value will be injected by Spring.
	 *
	 * @param initialOrganizationParticipantId The initial organization participant id to set.
	 */
	public void setInitialOrganizationParticipantId(OrganizationParticipantId initialOrganizationParticipantId)
	{
		this.initialOrganizationParticipantId = initialOrganizationParticipantId;
	}

	/**
	 * Set the initial recipient participant id. This value will be injected by Spring.
	 *
	 * @param initialRecipientParticipantId The initial recipient participant id to set.
	 */
	public void setInitialRecipientParticipantId(RecipientParticipantId initialRecipientParticipantId)
	{
		this.initialRecipientParticipantId = initialRecipientParticipantId;
	}

	/**
	 * Set the initial transfer setting participant id. This value will be injected by Spring.
	 *
	 * @param initialTransferSettingParticipantId The initial transfer setting participant id to set.
	 */
	public void setInitialTransferSettingParticipantId(TransferSettingParticipantId initialTransferSettingParticipantId)
	{
		this.initialTransferSettingParticipantId = initialTransferSettingParticipantId;
	}

	/**
	 * Set the initial suspicious activity participant id. This value will be injected by Spring.
	 *
	 * @param initialSuspiciousActivityParticipantId The initial suspicious activity participant id to set.
	 */
	public void setInitialSuspiciousActivityParticipantId(
			SuspiciousActivityParticipantId initialSuspiciousActivityParticipantId)
	{
		this.initialSuspiciousActivityParticipantId = initialSuspiciousActivityParticipantId;
	}

	/**
	 * Fetch the next available contact participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link ContactParticipantId}.
	 */
	@Override
	public InternalResultsResponse<ContactParticipantId> fetchNextContactParticipantId()
	{
		InternalResultsResponse<ContactParticipantId> response = new InternalResultsResponse<ContactParticipantId>();

		ContactParticipantId contactId =
				(ContactParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						CONTACT_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE, new ContactParticipantId());

		// If no contact participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(contactId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialContactParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialContactParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(contactId, response);

			// If the next contact id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, contactId,
								response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(contactId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available location participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link LocationParticipantId}.
	 */
	@Override
	public InternalResultsResponse<LocationParticipantId> fetchNextLocationParticipantId()
	{
		InternalResultsResponse<LocationParticipantId> response = new InternalResultsResponse<LocationParticipantId>();

		LocationParticipantId locationId =
				(LocationParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						LOCATION_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE, new LocationParticipantId());

		// If no location participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(locationId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialLocationParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialLocationParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(locationId, response);

			// If the next location id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, locationId,
								response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(locationId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available money transfer batch participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link MoneyTransferBatchParticipantId}.
	 */
	@Override
	public InternalResultsResponse<MoneyTransferBatchParticipantId> fetchNextMoneyTransferBatchParticipantId()
	{
		InternalResultsResponse<MoneyTransferBatchParticipantId> response =
				new InternalResultsResponse<MoneyTransferBatchParticipantId>();

		MoneyTransferBatchParticipantId moneyTransferBatchId =
				(MoneyTransferBatchParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						MONEY_TRANSFER_BATCH_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE,
						new MoneyTransferBatchParticipantId());

		// If no money transfer batch participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(moneyTransferBatchId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialMoneyTransferBatchParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialMoneyTransferBatchParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(moneyTransferBatchId, response);

			// If the next money transfer batch id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, moneyTransferBatchId,
								response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(moneyTransferBatchId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available money transfer participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link MoneyTransferParticipantId}.
	 */
	@Override
	public InternalResultsResponse<MoneyTransferParticipantId> fetchNextMoneyTransferParticipantId()
	{
		InternalResultsResponse<MoneyTransferParticipantId> response =
				new InternalResultsResponse<MoneyTransferParticipantId>();

		MoneyTransferParticipantId moneyTransferId =
				(MoneyTransferParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						MONEY_TRANSFER_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE,
						new MoneyTransferParticipantId());

		// If no money transfer participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(moneyTransferId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialMoneyTransferParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialMoneyTransferParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(moneyTransferId, response);

			// If the next money transfer id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, moneyTransferId,
								response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(moneyTransferId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available member participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link MemberParticipantId}.
	 */
	@Override
	public InternalResultsResponse<MemberParticipantId> fetchNextMemberParticipantId()
	{
		InternalResultsResponse<MemberParticipantId> response = new InternalResultsResponse<MemberParticipantId>();

		MemberParticipantId memberId =
				(MemberParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						MEMBER_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE, new MemberParticipantId());

		// If no member participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(memberId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialMemberParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialMemberParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(memberId, response);

			// If the next member id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, memberId,
								response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(memberId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available organization participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link OrganizationParticipantId}.
	 */
	@Override
	public InternalResultsResponse<OrganizationParticipantId> fetchNextOrganizationParticipantId()
	{
		InternalResultsResponse<OrganizationParticipantId> response =
				new InternalResultsResponse<OrganizationParticipantId>();

		OrganizationParticipantId organizationId =
				(OrganizationParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						ORGANIZATION_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE, new OrganizationParticipantId());

		// If no organization participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(organizationId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialOrganizationParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialOrganizationParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(organizationId, response);

			// If the next organization id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, organizationId,
								response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(organizationId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available recipient participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link RecipientParticipantId}.
	 */
	@Override
	public InternalResultsResponse<RecipientParticipantId> fetchNextRecipientParticipantId()
	{
		InternalResultsResponse<RecipientParticipantId> response =
				new InternalResultsResponse<RecipientParticipantId>();

		RecipientParticipantId recipientId =
				(RecipientParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						RECIPIENT_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE, new RecipientParticipantId());

		// If no recipient participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(recipientId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialRecipientParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialRecipientParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(recipientId, response);

			// If the next recipient id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper
						.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, recipientId, response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(recipientId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available transfer setting participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link TransferSettingParticipantId}.
	 */
	@Override
	public InternalResultsResponse<TransferSettingParticipantId> fetchNextTransferSettingParticipantId()
	{
		InternalResultsResponse<TransferSettingParticipantId> response =
				new InternalResultsResponse<TransferSettingParticipantId>();

		TransferSettingParticipantId transferSettingId =
				(TransferSettingParticipantId)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						TRANSFER_SETTING_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE, new TransferSettingParticipantId());

		// If no transfer setting participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(transferSettingId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialTransferSettingParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialTransferSettingParticipantId());
			}
		}
		// Update the existing one returned from the database.
		else
		{
			CommonRoutines.incrementParticipantId(transferSettingId, response);

			// If the next transfer setting id was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper
						.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, transferSettingId, response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(transferSettingId);
				}
			}
		}

		return response;
	}

	/**
	 * Fetch the next available suspicious activity participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link SuspiciousActivityParticipantId}.
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivityParticipantId> fetchNextSuspiciousActivityParticipantId()
	{
		InternalResultsResponse<SuspiciousActivityParticipantId> response =
				new InternalResultsResponse<SuspiciousActivityParticipantId>();

		SuspiciousActivityParticipantId suspiciousActivityId =
				(SuspiciousActivityParticipantId)QATMyBatisDacHelper
						.doQueryForObject(getSqlSession(),
								SUSPICIOUS_ACTIVITY_PARTICIPANT_ID_STMT_FETCH_FOR_UPDATE,
								new SuspiciousActivityParticipantId());

		// If no transfer setting participant id is found, insert the initialized one injected by Spring.
		if (ValidationUtil.isNull(suspiciousActivityId))
		{
			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), PARTICIPANT_ID_STMT_INSERT,
							getInitialSuspiciousActivityParticipantId(), response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(getInitialSuspiciousActivityParticipantId());
			}
			return response;
		}
		// Update the existing one returned from the database.
		CommonRoutines.incrementParticipantId(suspiciousActivityId, response);

		// If the next transfer setting id was built with no errors, then save it to the database.
		if (!response.isInError())
		{
			int updateCount =
					QATMyBatisDacHelper
					.doUpdate(getSqlSession(), PARTICIPANT_ID_STMT_UPDATE, suspiciousActivityId, response);

			if (!response.isInError() && (updateCount > 0))
			{
				response.addResult(suspiciousActivityId);
			}
		}

		return response;
	}

}
