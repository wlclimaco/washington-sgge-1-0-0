package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import javax.annotation.Resource;

import org.junit.Test;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CommonBusinessObjectsDACTest.
 */
public class NoteDACTest extends AbstractTestBaseDAC
{
	/** The Constant UPDATED_TEXT. */
	private static final String UPDATED_TEXT = "Updated Text";

	/** The Constant USER_NAME. */
	private static final String USER_NAME = "System";

	private static final Integer ONE = 1;

	private static final Integer THREE = 3;

	private static final Integer FIVE = 5;

	/** The organization dac. */
	private IOrganizationDAC organizationDAC;

	/** The note dac. */
	private INoteDAC noteDAC;

	/**
	 * Gets the note dac.
	 *
	 * @return the note dac
	 */
	@Override
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Sets the note dac.
	 *
	 * @param noteDAC the note dac
	 */
	@Override
	@Resource
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * Gets the organization dac.
	 *
	 * @return the organization dac
	 */
	@Override
	public IOrganizationDAC getOrganizationDAC()
	{
		return organizationDAC;
	}

	/**
	 * Sets the organization dac.
	 *
	 * @param organizationDAC the organization dac
	 */
	@Override
	@Resource
	public void setOrganizationDAC(IOrganizationDAC organizationDAC)
	{
		this.organizationDAC = organizationDAC;
	}

	/**
	 * Test insert note.
	 */
	@Test
	public void testInsertNote()
	{
		// Insert a note
		insertNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, ONE));
		insertNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.MEMBER, THREE));
		insertNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.RECIPIENT, FIVE));
	}

	/**
	 * Test update note.
	 */
	@Test
	public void testUpdateNote()
	{
		Note note = CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, ONE);
		insertNote(note);

		note.setModifyUser(USER_NAME);
		note.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		note.setNoteText(UPDATED_TEXT);
		note.setModelAction(PersistanceActionEnum.UPDATE);

		InternalResultsResponse<Note> internalResponse =
				getNoteDAC().updateNote(note);

		CommonTestRoutines.assertResultResponse(internalResponse);

	}

	/**
	 * Test delete note.
	 */
	@Test
	public void testDeleteNote()
	{
		Note note = CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, ONE);
		insertNote(note);

		InternalResponse internalResponse =
				getNoteDAC().deleteNote(note);

		CommonTestRoutines.assertResponse(internalResponse);
	}

	/**
	 * Test fetch note by id.
	 */
	@Test
	public void testFetchNoteById()
	{
		Note note = CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, ONE);
		insertNote(note);

		// Fetch from database
		InternalResultsResponse<Note> response = getNoteDAC().fetchNoteById(note.getId());
		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Insert note.
	 *
	 * @param note the note
	 */
	private void insertNote(Note note)
	{

		switch (note.getParentKeyType())
		{
			case ORGANIZATION:
				// This creates a dummy organization object with 6 contacts already (2 addresses, 2 email, 2 phone)
				Organization organization = CommonTestRoutines.createDummyOrganization();
				getOrganizationDAC().insertOrganization(organization);

				note.setParentKey(organization.getId());
				note.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
				break;
			case RECIPIENT:
				Recipient recipient = insertRecipient();
				note.setParentKey(recipient.getId());
				note.setParentKeyType(BusinessTypeEnum.RECIPIENT);
				break;
			case MEMBER:
				Member member = insertMember();
				note.setParentKey(member.getId());
				note.setParentKeyType(BusinessTypeEnum.MEMBER);
				break;
			default:
				break;
		}

		note.setModelAction(PersistanceActionEnum.INSERT);
		note.setCreateUser(USER_NAME);
		note.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());

		InternalResultsResponse<Note> internalResponse =
				getNoteDAC().insertNote(note);

		CommonTestRoutines.assertResultResponse(internalResponse);
	}

}
