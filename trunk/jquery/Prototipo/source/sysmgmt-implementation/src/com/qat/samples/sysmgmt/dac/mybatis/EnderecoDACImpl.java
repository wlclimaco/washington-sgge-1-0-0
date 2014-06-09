package com.qat.samples.sysmgmt.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.endereco.model.Endereco;

/**
 * The Class EnderecoDACImpl. (Data Access Component - DAC)
 */
public class EnderecoDACImpl extends SqlSessionDaoSupport implements IEnderecoDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "EnderecoMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertEndereco";

	/** The Constant STMT_UPDATE. */
	private static final String STMT_UPDATE = NAMESPACE + "updateEndereco";

	/** The Constant STMT_VERSION. */
	private static final String STMT_VERSION = NAMESPACE + "fetchVersionNumber";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#insertEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
	 */
	@Override
	public InternalResponse insertEndereco(Endereco endereco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, endereco, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#updateEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
	 */
	@Override
	public InternalResponse updateEndereco(Endereco endereco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doUpdateOL(getSqlSession(), STMT_UPDATE, endereco, STMT_VERSION, response);
		return response;
	}

	@Override
	public InternalResponse deleteEndereco(Endereco endereco)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
