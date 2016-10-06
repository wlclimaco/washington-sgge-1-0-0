/** create by system gera-java version 1.0.0 13/09/2016 13:56 : 36*/


package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class UserRoleBARD extends SqlSessionDaoSupport
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
	public static Integer maintainUserRolesAssociations(List<UserRoles> userrolesList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum, IEmpresaBAR userrolesDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(userrolesList))
		{
			return 0;
		}
		// For Each Contact...
		for (UserRoles userroles : userrolesList)
		{
			// Make sure we set the parent key
			userroles.setParentId(parentId);
			userroles.setTabelaEnum(tabelaEnum);
			userroles.setProcessId(processId);

			if (ValidationUtil.isNull(userroles.getModelAction()))
			{
				continue;
			}
			switch (userroles.getModelAction())
			{
				case INSERT:
					count = userrolesDAC.insertUserRoles(userroles).hasSystemError();

					break;
				case UPDATE:
					count = userrolesDAC.updateUserRoles(userroles).hasSystemError();

					break;
				case DELETE:
					count = userrolesDAC.deleteUserRolesById(userroles).hasSystemError();


					break;
			}
		}
		if(count == true ){
			return 1;
		}else{
			return 0;
		}

	}
}
