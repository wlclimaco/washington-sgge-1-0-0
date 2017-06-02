/** create by system gera-java version 1.0.0 05/12/2016 22:20 : 14*/

package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Nfe.INFNotaInfoItemBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.nfeItens.model.NFNotaInfoItemProdutoMedicamento;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.AcaoTypeEnum;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.TypeEnum;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY
 * static methods so everything must be passed into the methods. Nothing
 * injected.
 */
public final class NFNotaInfoItemProdutoMedicamentoBARD extends SqlSessionDaoSupport {

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/**
	 * Fetch objects by request.
	 *
	 * @param list the list
	 * @param response            the response
	 * @param parentId the parent id
	 * @param type the type
	 * @param acaoType the acao type
	 * @param tabelaEnum the tabela enum
	 * @param nfnotainfoitemprodutomedicamentoDAC the nfnotainfoitemprodutomedicamento DAC
	 * @param statusDAC the status DAC
	 * @param historicoDAC the historico DAC
	 * @param empId the emp id
	 * @param UserId the user id
	 * @param processId the process id
	 * @param historicoId the historico id
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	public static Integer maintainNFNotaInfoItemProdutoMedicamentoAssociations(
			List<NFNotaInfoItemProdutoMedicamento> list, InternalResponse response, Integer parentId, TypeEnum type,
			AcaoEnum acaoType, TabelaEnum tabelaEnum, INFNotaInfoItemBAR nfnotainfoitemprodutomedicamentoDAC,
			IStatusBAR statusDAC, IHistoricoBAR historicoDAC, Integer empId, String UserId, Integer processId,
			Integer historicoId) {
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(list)) {
			return 0;
		}
		// For Each Contact...
		for (NFNotaInfoItemProdutoMedicamento nfnotainfoitemprodutomedicamento : list) {
			// Make sure we set the parent key
			nfnotainfoitemprodutomedicamento.setParentId(parentId);
			nfnotainfoitemprodutomedicamento.setTabelaEnum(tabelaEnum);
			nfnotainfoitemprodutomedicamento.setProcessId(processId);

			if (ValidationUtil.isNull(nfnotainfoitemprodutomedicamento.getModelAction())) {
				continue;
			}
			switch (nfnotainfoitemprodutomedicamento.getModelAction()) {
			case INSERT:
				count = nfnotainfoitemprodutomedicamentoDAC
						.insertNFNotaInfoItemProdutoMedicamento(nfnotainfoitemprodutomedicamento).hasSystemError();

				break;
			case UPDATE:
				count = nfnotainfoitemprodutomedicamentoDAC
						.updateNFNotaInfoItemProdutoMedicamento(nfnotainfoitemprodutomedicamento).hasSystemError();

				break;
			case DELETE:
				count = nfnotainfoitemprodutomedicamentoDAC
						.deleteNFNotaInfoItemProdutoMedicamentoById(nfnotainfoitemprodutomedicamento).hasSystemError();

				break;
			}
		}
		if (count == true) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Maintain NF nota info item produto medicamento associations.
	 *
	 * @param nfNotaInfoItemProdutoMedicamento the nf nota info item produto medicamento
	 * @param response the response
	 * @param parentId the parent id
	 * @param type the type
	 * @param update the update
	 * @param tabelaEnum the tabela enum
	 * @param nfnotainfoitemprodutomedicamentoDAC the nfnotainfoitemprodutomedicamento DAC
	 * @param statusDAC the status DAC
	 * @param historicoDAC the historico DAC
	 * @param empId the emp id
	 * @param UserId the user id
	 * @param processId the process id
	 * @param historicoId the historico id
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	public static Integer maintainNFNotaInfoItemProdutoMedicamentoAssociations(
			NFNotaInfoItemProdutoMedicamento nfNotaInfoItemProdutoMedicamento, InternalResponse response,
			Integer parentId, TypeEnum type, AcaoTypeEnum update, TabelaEnum tabelaEnum,
			INFNotaInfoItemBAR nfnotainfoitemprodutomedicamentoDAC, IStatusBAR statusDAC, IHistoricoBAR historicoDAC,
			Integer empId, String UserId, Integer processId, Integer historicoId) {
		Boolean count = false;
		// First Maintain Empresa
		if (ValidationUtil.isNull(nfNotaInfoItemProdutoMedicamento)) {
			return 0;
		}
		// For Each Contact...

		// Make sure we set the parent key
		nfNotaInfoItemProdutoMedicamento.setParentId(parentId);
		nfNotaInfoItemProdutoMedicamento.setTabelaEnum(tabelaEnum);
		nfNotaInfoItemProdutoMedicamento.setProcessId(processId);

		switch (nfNotaInfoItemProdutoMedicamento.getModelAction()) {
		case INSERT:
			count = nfnotainfoitemprodutomedicamentoDAC
					.insertNFNotaInfoItemProdutoMedicamento(nfNotaInfoItemProdutoMedicamento).hasSystemError();

			break;
		case UPDATE:
			count = nfnotainfoitemprodutomedicamentoDAC
					.updateNFNotaInfoItemProdutoMedicamento(nfNotaInfoItemProdutoMedicamento).hasSystemError();

			break;
		case DELETE:
			count = nfnotainfoitemprodutomedicamentoDAC
					.deleteNFNotaInfoItemProdutoMedicamentoById(nfNotaInfoItemProdutoMedicamento).hasSystemError();

			break;
		}

		if (count == true) {
			return 1;
		} else {
			return 0;
		}

	}
}
