package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class NoteBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(NoteBARTest.class);
private INotesBAR notesBAR; // injected by Spring through @Resource

@Resource
public void setNoteBAR(INotesBAR notesBAR)
{
	this.notesBAR = notesBAR;
}

public INotesBAR getNotesBAR()
{
	return notesBAR;
}


//===================================### NOTES ####======================================


@Test
	public void testDeleteNote()
	{
		Note notes = new Note(4, "Note_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Note notesResponse = getNotesBAR().fetchNotesById(request);
		Assert.assertEquals(notesResponse, null);
		getNotesBAR().insertNotes(notes);
		notesResponse = getNotesBAR().fetchNotesById(request);
		Assert.assertEquals(notes.getId(), notesResponse.getId());
		getNotesBAR().deleteNotesById(notes);
		notesResponse = getNotesBAR().fetchNotesById(request);
		Assert.assertEquals(notesResponse, null);
	}

	@Test
	public void testFetchAllNotes()
	{
	Note notes = new Note();
		List<Note> response = getNotesBAR().fetchAllNotess(notes).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllNotes()
	{
		getNotesBAR().deleteAllNotess();
	Note notes = new Note();
		List<Note> response = getNotesBAR().fetchAllNotess(new Note(1, "Note_999")).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateNote()
	{
		Note notes = new Note(1, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Note notesResponse = getNotesBAR().fetchNotesById(request);
		Assert.assertEquals(notesResponse.getNoteText(), "noteText_2");
		getNotesBAR().updateNotes(notes);
		notesResponse = getNotesBAR().fetchNotesById(request);
		Assert.assertEquals(notesResponse.getNoteText(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchNotesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Note> response = getNotesBAR().fetchNotessByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getNotesBAR().fetchNotessByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Note> response2 = getNotesBAR().fetchNotessByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getNotesBAR().deleteAllNotess();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Note> response3 = getNotesBAR().fetchNotessByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertNotes.sql", false);
	}

}
