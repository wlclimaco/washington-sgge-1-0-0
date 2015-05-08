package com.prosperitasglobal.cbof.unittest.bai;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.bai.INoteBAI;
import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.NoteMaintenanceRequest;
import com.prosperitasglobal.cbof.model.response.NoteResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.PriorityEnum;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;

/**
 * The Class CommonBusinessObjectsBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class CommonBusinessObjectsBAITest extends AbstractJUnit4SpringContextTests
{

	/** The Constant SHOULD_HAVE_1_MESSAGE_BUT_HAVE. */
	private static final String SHOULD_HAVE_1_MESSAGE_BUT_HAVE = "Should have 1 message, but have ";

	/** The Constant CONTACT_LIST_KEY. */
	private static final String CONTACT_LIST_KEY = "ContactList";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED =
			"sendsolv.base.notevalidator.parentkeytype.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED =
			"sendsolv.base.notevalidator.parentkey.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED =
			"sendsolv.base.notevalidator.notetext.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED =
			"sendsolv.base.base.notevalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_TOO_BIG =
			"sendsolv.base.notevalidator.notetext.too.big";

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	private static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The note bai. */
	private INoteBAI noteBAI;

	/** The mock note dac. */
	private INoteDAC mockNoteDAC;

	/** The validation controller. */
	private ValidationController validationController;

	/**
	 * Get organization validation controller.
	 *
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Spring sets the organization validation controller.
	 *
	 * @param organizationValidationController the new validation controller
	 */
	@Resource
	public void setValidationController(ValidationController organizationValidationController)
	{
		validationController = organizationValidationController;
	}

	/**
	 * Gets the note bai.
	 *
	 * @return the note bai
	 */
	public INoteBAI getNoteBAI()
	{
		return noteBAI;
	}

	/**
	 * Sets the note bai.
	 *
	 * @param noteBAI the note bai
	 */
	@Resource
	public void setNoteBAI(INoteBAI noteBAI)
	{
		this.noteBAI = noteBAI;
	}

	/**
	 * Gets the mock note dac.
	 *
	 * @return the mock note dac
	 */
	public INoteDAC getMockNoteDAC()
	{
		return mockNoteDAC;
	}

	/**
	 * Sets the mock note dac.
	 *
	 * @param mockNoteDAC the mock note dac
	 */
	@Resource
	public void setMockNoteDAC(INoteDAC mockNoteDAC)
	{
		this.mockNoteDAC = mockNoteDAC;
	}

	/**
	 * Setup.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockNoteDAC());
	}

	/**
	 * Test contact validator.
	 */
	@Test
	public void testContactValidator()
	{

		ValidationContextIndicator indicator = ValidationContextIndicator.INSERT;

		ArrayList<Contact> contactList = new ArrayList<Contact>();
		contactList.add(CommonTestRoutines.createDummyEmail(ContactTypeEnum.EMAIL_PERSONAL));
		contactList.add(CommonTestRoutines.createDummyEmail(ContactTypeEnum.EMAIL_WORK));

		contactList.add(CommonTestRoutines.createDummyPhone(ContactTypeEnum.OTHER, PriorityEnum.SECONDARY));
		contactList.add(CommonTestRoutines.createDummyPhone(ContactTypeEnum.PHONE_WORK, PriorityEnum.PRIMARY));

		contactList.add(CommonTestRoutines.createDummyAddress(ContactTypeEnum.POSTAL_HOME));
		contactList.add(CommonTestRoutines.createDummyAddress(ContactTypeEnum.POSTAL_WORK));

		ValidationContext context =
				new ValidationContext(CONTACT_LIST_KEY, contactList, indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());

		getValidationController().validate(context);

		Assert.assertTrue("Should have no messages, but have " + context.getMessages().size(), context.getMessages()
				.size() == 0);
	}

	/**
	 * Test contact validator failure address.
	 */
	@Test
	public void testContactValidatorFailureAddress()
	{
		ValidationContextIndicator indicator = ValidationContextIndicator.INSERT;

		ArrayList<Contact> contactList = new ArrayList<Contact>();
		Address addr = (Address)CommonTestRoutines.createDummyAddress(ContactTypeEnum.POSTAL_HOME);

		addr.setPostalCode("9999-99");
		contactList.add(addr);
		ValidationContext context =
				new ValidationContext(CONTACT_LIST_KEY, contactList, indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());
		getValidationController().validate(context);
		Assert.assertTrue(SHOULD_HAVE_1_MESSAGE_BUT_HAVE + context.getMessages().size(), context.getMessages()
				.size() == 1);

		contactList.clear();
		addr.setPostalCode("99999-999");
		contactList.add(addr);
		context.getMessages().clear();
		context.getObjectsToBeValidated().clear();
		context.putObjectToBeValidated(CONTACT_LIST_KEY, contactList);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());
		getValidationController().validate(context);
		Assert.assertTrue(SHOULD_HAVE_1_MESSAGE_BUT_HAVE + context.getMessages().size(), context.getMessages()
				.size() == 1);

		contactList.clear();
		addr.setPostalCode("9999-9999");
		contactList.add(addr);
		context.getMessages().clear();
		context.getObjectsToBeValidated().clear();
		context.putObjectToBeValidated(CONTACT_LIST_KEY, contactList);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());
		getValidationController().validate(context);
		Assert.assertTrue(SHOULD_HAVE_1_MESSAGE_BUT_HAVE + context.getMessages().size(), context.getMessages()
				.size() == 1);
	}

	/**
	 * Test contact validator failure phone.
	 */
	@Test
	public void testContactValidatorFailurePhone()
	{
		ValidationContextIndicator indicator = ValidationContextIndicator.INSERT;

		ArrayList<Contact> contactList = new ArrayList<Contact>();
		Phone phone = (Phone)CommonTestRoutines.createDummyPhone(ContactTypeEnum.OTHER, PriorityEnum.PRIMARY);

		phone.setNumber("12345");
		contactList.add(phone);
		ValidationContext context =
				new ValidationContext(CONTACT_LIST_KEY, contactList, indicator);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());
		getValidationController().validate(context);
		Assert.assertTrue(SHOULD_HAVE_1_MESSAGE_BUT_HAVE + context.getMessages().size(), context.getMessages()
				.size() == 1);

		contactList.clear();
		phone.setNumber("123456789012321000");
		contactList.add(phone);
		context.getMessages().clear();
		context.getObjectsToBeValidated().clear();
		context.putObjectToBeValidated(CONTACT_LIST_KEY, contactList);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());
		getValidationController().validate(context);
		Assert.assertTrue(SHOULD_HAVE_1_MESSAGE_BUT_HAVE + context.getMessages().size(), context.getMessages()
				.size() == 1);

		contactList.clear();
		phone.setNumber("1234567");
		contactList.add(phone);
		context.getMessages().clear();
		context.getObjectsToBeValidated().clear();
		context.putObjectToBeValidated(CONTACT_LIST_KEY, contactList);
		context.putObjectToBeValidated(UserContext.class.getSimpleName(),
				CommonTestRoutines.createDummyUserContext());
		getValidationController().validate(context);
		Assert.assertTrue("Should have 0 message, but have " + context.getMessages().size(), context.getMessages()
				.size() == 0);
	}

	/**
	 * Test insert note.
	 */
	@Test
	public void testInsertNote()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setModelAction(PersistanceActionEnum.INSERT);
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Note> internalResponse = new InternalResultsResponse<Note>();
		internalResponse.addResult(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));

		Mockito.when(getMockNoteDAC().insertNote(request.getNote())).thenReturn(
				internalResponse);

		NoteResponse response = getNoteBAI().insertNote(request);

		CommonTestRoutines.assertResponse(response);
		Mockito.verify(getMockNoteDAC()).insertNote(request.getNote());

	}

	/**
	 * Test insert note with note text too big.
	 */
	@Test
	public void testInsertNoteWithNoteTextTooBig()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setModelAction(PersistanceActionEnum.INSERT);
		request.getNote().setNoteText(request.getNote().getNoteText() + createInvalidNoteText());
		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Note> internalResponse = new InternalResultsResponse<Note>();
		internalResponse.addResult(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));

		Mockito.when(getMockNoteDAC().insertNote(request.getNote())).thenReturn(
				internalResponse);

		NoteResponse response = getNoteBAI().insertNote(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_TOO_BIG);

		Mockito.verify(getMockNoteDAC(), Mockito.never()).insertNote(request.getNote());

	}

	/**
	 * Test insert note with error.
	 */
	@Test
	public void testInsertNoteWithError()
	{
		// Test Insert Success
		InternalResultsResponse<Note> internalResultsResponse = new InternalResultsResponse<Note>();
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		request.setNote(new Note());
		request.getNote().setModelAction(PersistanceActionEnum.INSERT);
		request.getNote().setParentKey(null);
		request.getNote().setParentKeyType(null);
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockNoteDAC().insertNote(request.getNote())).thenReturn(
				internalResultsResponse);

		NoteResponse response = getNoteBAI().insertNote(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED,
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED);

		Mockito.verify(getMockNoteDAC(), Mockito.never()).insertNote(request.getNote());
	}

	/**
	 * Test insert note exception.
	 */
	@Test
	public void testInsertNoteException()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setModelAction(PersistanceActionEnum.INSERT);

		Mockito.when(getMockNoteDAC().insertNote(request.getNote())).thenThrow(
				new RuntimeException());

		NoteResponse response = getNoteBAI().insertNote(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockNoteDAC()).insertNote(request.getNote());
	}

	/**
	 * Test insert note exception.
	 */
	@Test
	public void testUpdateNoteException()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setId(TWO);
		request.getNote().setModelAction(PersistanceActionEnum.UPDATE);

		Mockito.when(getMockNoteDAC().updateNote(request.getNote())).thenThrow(new RuntimeException());

		NoteResponse response = getNoteBAI().updateNote(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockNoteDAC()).updateNote(request.getNote());
	}

	/**
	 * Test update note.
	 */
	@Test
	public void testUpdateNote()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setId(TWO);
		request.getNote().setModelAction(PersistanceActionEnum.UPDATE);

		Mockito.when(getMockNoteDAC().updateNote(request.getNote())).thenReturn(new InternalResultsResponse<Note>());

		NoteResponse response = getNoteBAI().updateNote(request);

		Assert.assertTrue(response.getMessageList().isEmpty());

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockNoteDAC()).updateNote(request.getNote());

	}

	/**
	 * Test delete note.
	 */
	@Test
	public void testDeleteNote()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setId(TWO);
		Mockito.when(getMockNoteDAC().deleteNote(request.getNote())).thenReturn(new InternalResponse());

		NoteResponse response = getNoteBAI().deleteNote(request);

		Assert.assertTrue(response.getMessageList().isEmpty());

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockNoteDAC()).deleteNote(request.getNote());

	}

	/**
	 * Test update note with error.
	 */
	@Test
	public void testUpdateNoteWithError()
	{
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setNote(new Note());
		request.getNote().setModelAction(PersistanceActionEnum.UPDATE);
		request.getNote().setParentKey(null);
		request.getNote().setParentKeyType(null);

		Mockito.when(getMockNoteDAC().updateNote(request.getNote())).thenReturn(new InternalResultsResponse<Note>());

		NoteResponse response = getNoteBAI().updateNote(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEYTYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_PARENTKEY_REQUIRED,
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_NOTETEXT_REQUIRED,
				PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockNoteDAC(), Mockito.never()).updateNote(request.getNote());
	}

	/**
	 * Test delete note with error.
	 */
	@Test
	public void testDeleteNoteWithError()
	{
		// Test Insert Success
		NoteMaintenanceRequest request = new NoteMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setNote(CommonTestRoutines.createDummyNote(BusinessTypeEnum.ORGANIZATION, 1));
		request.getNote().setId(null);
		request.getNote().setModelAction(PersistanceActionEnum.DELETE);

		Mockito.when(getMockNoteDAC().updateNote(request.getNote())).thenReturn(new InternalResultsResponse<Note>());

		NoteResponse response = getNoteBAI().deleteNote(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_NOTEVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockNoteDAC(), Mockito.never()).deleteNote(request.getNote());
	}

	private String createInvalidNoteText()
	{
		String name = new String();
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			name += "A";
		}
		return name;
	}

}
