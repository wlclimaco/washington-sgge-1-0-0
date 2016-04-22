package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

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
			TabelaEnum tabelaEnum, INotesBAR noteDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(noteList))
		{
			return 0;
		}
		// For Each Contact...
		for (Note note : noteList)
		{
			// Make sure we set the parent key
			note.setParentId(parentId);
			note.setProcessId(processId);
			note.setTabelaEnum(tabelaEnum);

			if (ValidationUtil.isNull(note.getModelAction()))
			{
				continue;
			}
			switch (note.getModelAction())
			{
				case INSERT:
					count = noteDAC.insertNotes(note).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						count =
								StatusDACD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.NOTE, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = noteDAC.updateNotes(note).hasSystemError();
					if (count == true)
					{
						count =
								StatusDACD.maintainStatusAssociations(note.getStatusList(), response,
										note.getId(), null, AcaoEnum.UPDATE, UserId, empId, TabelaEnum.NOTE,
										statusDAC, historicoDAC, processId, historicoId);
					}
					break;
				case DELETE:
					count = noteDAC.deleteNotesById(note).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					count =
							StatusDACD.maintainStatusAssociations(statusList, response, note.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.NOTE, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}
}
