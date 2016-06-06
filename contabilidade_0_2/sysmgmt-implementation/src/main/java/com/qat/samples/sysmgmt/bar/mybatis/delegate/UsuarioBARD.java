package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class UsuarioBARD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

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
	public static Integer maintainUsuarioAssociations(List<Usuario> usuarioList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEmpresaBAR usuarioDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(usuarioList))
		{
			return 0;
		}
		// For Each Contact...
		for (Usuario usuario : usuarioList)
		{
			// Make sure we set the parent key
			usuario.setParentId(parentId);
			usuario.setTabelaEnum(tabelaEnum);
			usuario.setProcessId(processId);

			if (ValidationUtil.isNull(usuario.getModelAction()))
			{
				continue;
			}
			switch (usuario.getModelAction())
			{
				case INSERT:
					count = usuarioDAC.insertUsuario(usuario).hasSystemError();
					if (count == true)
					{
						Status status = new Status();
						status.setStatus(CdStatusTypeEnum.ATIVO);
						List<Status> statusList = new ArrayList<Status>();
						statusList.add(status);
						count =
								StatusBARD.maintainStatusAssociations(statusList, response, parentId, null,
										AcaoEnum.INSERT, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case UPDATE:
					count = usuarioDAC.updateUsuario(usuario).hasSystemError();
					if (count == true)
					{
						count =
								StatusBARD.maintainStatusAssociations(usuario.getStatusList(), response, usuario.getId(),
										null,
										AcaoEnum.UPDATE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
										processId, historicoId);
					}
					break;
				case DELETE:
					count = usuarioDAC.deleteUsuarioById(usuario).hasSystemError();
					Status status = new Status();
					status.setStatus(CdStatusTypeEnum.DELETADO);
					List<Status> statusList = new ArrayList<Status>();
					statusList.add(status);
					count =
							StatusBARD.maintainStatusAssociations(statusList, response, usuario.getId(), null,
									AcaoEnum.DELETE, UserId, empId, TabelaEnum.EMAIL, statusDAC, historicoDAC,
									processId, historicoId);

					break;
			}
		}

		return 1;
	}
}
