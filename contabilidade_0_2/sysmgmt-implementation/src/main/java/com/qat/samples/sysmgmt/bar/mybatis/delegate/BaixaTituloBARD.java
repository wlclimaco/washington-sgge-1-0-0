package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.conta.model.Conta;
import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class BaixaTituloBARD extends SqlSessionDaoSupport
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
	public static Integer maintainBaixaTituloAssociations(List<BaixaTitulo> baixaTituloList,
			InternalResponse response, Integer parentId, TypeEnum type, AcaoTypeEnum insert,
			TabelaEnum tabelaEnum, IFinanceiroBAR emailDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId,
			String UserId, Integer processId, Integer historicoId)
	{
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNullOrEmpty(baixaTituloList))
		{
			return 0;
		}
		// For Each Contact...
		for (BaixaTitulo baixa : baixaTituloList)
		{
			// Make sure we set the parent key
			baixa.setParentId(parentId);
			baixa.setTabelaEnum(tabelaEnum);
			baixa.setProcessId(processId);
			baixa.setFinanId(parentId);

			if (ValidationUtil.isNull(baixa.getModelAction()))
			{
				continue;
			}
			switch (baixa.getModelAction())
			{
				case INSERT:
					count = emailDAC.insertBaixaTitulo(baixa).hasSystemError();
					if(!count)
					{
					 Conta conta = new Conta();
					    Double value;

						// diminuir saldo conta
					    conta = emailDAC.fetchContaById(new FetchByIdRequest(baixa.getConta().getId()));
					    if (!ValidationUtil.isNull(conta))
					    {
					    	if(tabelaEnum.equals(TabelaEnum.CONTASPAGAR))
					    	{
					    		value = conta.getSaldo() - baixa.getValor();
					    	}
					    	else
					    	{
					    		value = conta.getSaldo() + baixa.getValor();
					    	}
						    conta.setSaldo(value);

						 // atualizar ulttrans conta
						    conta.setDataUltLanc((new Date()).getTime());

						    emailDAC.updateConta(conta);
					    }
					}
					break;
				case UPDATE:
					count = emailDAC.updateBaixaTitulo(baixa).hasSystemError();

					break;
				case DELETE:
					count = emailDAC.deleteBaixaTituloById(baixa).hasSystemError();
					if(!count)
					{
					 Conta conta = new Conta();
					    Double value;

						// voltar saldo conta
					    conta = emailDAC.fetchContaById(new FetchByIdRequest(baixa.getConta().getId()));
					    if (!ValidationUtil.isNull(conta))
					    {
					    	if(tabelaEnum.equals(TabelaEnum.CONTASPAGAR))
					    	{
					    		value = conta.getSaldo() + baixa.getValor();
					    	}
					    	else
					    	{
					    		value = conta.getSaldo() - baixa.getValor();
					    	}

						    conta.setSaldo(value);

						 // atualizar ulttrans conta
						    conta.setDataUltLanc((new Date()).getTime());

						    emailDAC.updateConta(conta);

						    emailDAC.deleteBaixaTituloById(baixa);
					    }
					}

					break;
			}
		}

		return 1;
	}
}
