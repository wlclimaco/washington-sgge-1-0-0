package com.qat.samples.sysmgmt.nf.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.nf.dac.IContasDAC;
import com.qat.samples.sysmgmt.nf.dac.INotaFiscalDAC;
import com.qat.samples.sysmgmt.nf.model.Contas;

/**
 * The Class ContasDACImpl.
 */
public class ContasDACImpl extends SqlSessionDaoSupport implements IContasDAC
{
	private INotaFiscalDAC notaFiscalDac;

	/**
	 * @return the notaFiscalDac
	 */
	public INotaFiscalDAC getNotaFiscalDac()
	{
		return notaFiscalDac;
	}

	/**
	 * @param notaFiscalDac the notaFiscalDac to set
	 */
	public void setNotaFiscalDac(INotaFiscalDAC notaFiscalDac)
	{
		this.notaFiscalDac = notaFiscalDac;
	}

	@Override
	public Integer updateContas(Contas contas, InternalResultsResponse<?> response)
	{
		response = getNotaFiscalDac().updateContas(contas);
		return 1;
	}

	@Override
	public Integer insertContas(Contas contas, String statementName,
			InternalResultsResponse<?> response)
	{

		response = getNotaFiscalDac().insertContas(contas);

		return 1;
	}

	@Override
	public Integer deleteContas(Contas contas, InternalResultsResponse response)
	{

		response = (InternalResultsResponse)getNotaFiscalDac().deleteContas(contas);
		return 1;
	}
}
