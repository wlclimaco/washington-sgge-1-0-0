/** create by system gera-java version 1.0.0 01/09/2016 12:56 : 31*/

package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY
 * static methods so everything must be passed into the methods. Nothing
 * injected.
 */
public final class ProdutoBARD extends SqlSessionDaoSupport {

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/**
	 * Fetch objects by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	@SuppressWarnings("unchecked")
	public static Integer maintainProdutoAssociations(Produto produto, InternalResponse response,
			Integer parentId, TypeEnum type, AcaoTypeEnum insert, TabelaEnum tabelaEnum, IProdutoBAR produtoDAC,
			IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId, String UserId, Integer processId,
			Integer historicoId) {
		Boolean count = false;

		// Make sure we set the parent key
		produto.setParentId(parentId);
		produto.setTabelaEnum(tabelaEnum);
		produto.setProcessId(processId);

		switch (produto.getModelAction()) {
		case INSERT:
			count = produtoDAC.insertProduto(produto).hasSystemError();
			break;
		case UPDATE:
			count = produtoDAC.updateProduto(produto).hasSystemError();
			break;
		case DELETE:
			count = produtoDAC.deleteProdutoById(produto).hasSystemError();


			break;
		}

		if (count == true) {
			return 1;
		} else {
			return 0;
		}

	}
}
