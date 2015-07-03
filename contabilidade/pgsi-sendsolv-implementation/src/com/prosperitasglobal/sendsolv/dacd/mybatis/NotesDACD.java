package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class NotesDACD extends SqlSessionDaoSupport
{
	/**
	 * Fetch objects by request.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @param countStatement the count statement
	 * @param fetchPagedStatement the fetch paged statement
	 * @param response the response
	 */
	@SuppressWarnings("unchecked")
	public static Integer maintainNoteAssociations(List<Note> noteList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, INoteDAC noteDAC, IStatusDAC statusDAC, IHistoricoDAC historicoDAC,
			Integer empId, String UserId, Integer processId)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(noteList))
		{
			return count;
		}
		// For Each Contact...
		// for (Note note : noteList)
		// {
		// // Make sure we set the parent key
		// note.setParentId(parentId);
		//
		// if (ValidationUtil.isNull(note.getModelAction()))
		// {
		// continue;
		// }
		// switch (note.getModelAction())
		// {
		// case INSERT:
		// count = noteDAC.insertNote(note);
		// if (count > 0)
		// {
		// Status status = new Status();
		// status.setStatus(StatusEnum.ACTIVE);
		// List<Status> statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, count, null,
		// AcaoEnum.INSERT, UserId, empId, TabelaEnum.NOTE, statusDAC, historicoDAC);
		// }
		// break;
		// case UPDATE:
		// count = noteDAC.updateNote(note);
		// if (count > 0)
		// {
		// count =
		// StatusDACD.maintainStatusAssociations(note.getStatusList(), response,
		// note.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.NOTE,
		// statusDAC, historicoDAC);
		// }
		// break;
		// case DELETE:
		//
		// Status status = new Status();
		// status.setStatus(StatusEnum.INACTIVE);
		// List<Status> statusList = new ArrayList<Status>();
		// count =
		// StatusDACD.maintainStatusAssociations(statusList, response, note.getId(), null,
		// AcaoEnum.DELETE, UserId, empId, TabelaEnum.NOTE, statusDAC, historicoDAC);
		//
		// break;
		// }
		// }

		return count;
	}
}
