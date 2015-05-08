package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.ContactParticipantId;
import com.prosperitasglobal.sendsolv.model.LocationParticipantId;
import com.prosperitasglobal.sendsolv.model.MemberParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantId;
import com.prosperitasglobal.sendsolv.model.OrganizationParticipantId;
import com.prosperitasglobal.sendsolv.model.RecipientParticipantId;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivityParticipantId;
import com.prosperitasglobal.sendsolv.model.TransferSettingParticipantId;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Interface specifies the methods available for working between the SendSolv system and the datastore which contains
 * the last participant id for a member, recipient, location, organization, and money transfer batch. Its sole purpose
 * is to define the methods needed for getting the next available participation id for those identities.
 */
public interface IParticipantIdDAC
{
	/**
	 * Fetch the next available contact participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link ContactParticipantId}.
	 */
	public InternalResultsResponse<ContactParticipantId> fetchNextContactParticipantId();

	/**
	 * Fetch the next available location participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link LocationParticipantId}.
	 */
	public InternalResultsResponse<LocationParticipantId> fetchNextLocationParticipantId();

	/**
	 * Fetch the next available money transfer participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link MoneyTransferParticipantId}.
	 */
	public InternalResultsResponse<MoneyTransferParticipantId> fetchNextMoneyTransferParticipantId();

	/**
	 * Fetch the next available batch participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link MoneyTransferBatchParticipantId}.
	 */
	public InternalResultsResponse<MoneyTransferBatchParticipantId> fetchNextMoneyTransferBatchParticipantId();

	/**
	 * Fetch the next available member participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link MemberParticipantId}.
	 */
	public InternalResultsResponse<MemberParticipantId> fetchNextMemberParticipantId();

	/**
	 * Fetch the next available organization participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link OrganizationParticipantId}.
	 */
	public InternalResultsResponse<OrganizationParticipantId> fetchNextOrganizationParticipantId();

	/**
	 * Fetch the next available recipient participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link RecipientParticipantId}.
	 */
	public InternalResultsResponse<RecipientParticipantId> fetchNextRecipientParticipantId();

	/**
	 * Fetch the next available transfer setting participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link TransferSettingParticipantId}.
	 */
	public InternalResultsResponse<TransferSettingParticipantId> fetchNextTransferSettingParticipantId();

	/**
	 * Fetch the next available suspicious activity participant id in the SendSolv system.
	 *
	 * @return The {@link InternalResultsResponse} containing the next available unique
	 *         {@link SuspiciousActivityParticipantId}.
	 */
	public InternalResultsResponse<SuspiciousActivityParticipantId> fetchNextSuspiciousActivityParticipantId();
}
