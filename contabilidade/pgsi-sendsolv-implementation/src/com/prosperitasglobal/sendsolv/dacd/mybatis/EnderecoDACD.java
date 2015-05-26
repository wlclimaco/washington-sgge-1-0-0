package com.prosperitasglobal.sendsolv.dacd.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IEnderecoDAC;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Endereco;
import com.prosperitasglobal.sendsolv.model.Status;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.TypeEnum;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class EnderecoDACD extends SqlSessionDaoSupport
{

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	private IEnderecoDAC enderecoDAC;

	/**
	 * @return the enderecoDAC
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	/**
	 * @param enderecoDAC the enderecoDAC to set
	 */
	public setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

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
	public static Integer maintainEnderecoAssociations(List<Endereco> enderecoList,
			InternalResultsResponse<?> response, Integer parentId, TypeEnum type, AcaoEnum acaoType,
			TabelaEnum tabelaEnum,IEnderecoDAC enderecoDAC)
	{
		Integer count = 0;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(enderecoList))
		{
			return count;
		}
		// For Each Contact...
		for (Endereco endereco : enderecoList)
		{
			// Make sure we set the parent key
			endereco.setParentId(parentId);

			if (ValidationUtil.isNull(endereco.getModelAction()))
			{
				continue;
			}
			switch (endereco.getModelAction())
			{
				case INSERT:
					count = enderecoDAC.insertEndereco(endereco,
							"insertEndereco", response);
					break;
				case UPDATE:
					count = enderecoDAC.updateEndereco(endereco, response);
					break;
				case DELETE:
					count = enderecoDAC.deleteEndereco(endereco, response);
					break;
				default:

			}
		}
		if (count > 0)
		{
			Status status = new Status();
			status.setStatus(StatusEnum.ACTIVE);
			List<Status> statusList = new new ArrayList<Status>();
			count = StatusDACD.maintainStatusAssociations(statusList, response, count, type, enderecoList, tabelaEnum);
		}

		return count;
	}
}
