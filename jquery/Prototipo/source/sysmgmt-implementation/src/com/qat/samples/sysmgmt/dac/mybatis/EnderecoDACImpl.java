package com.qat.samples.sysmgmt.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.endereco.model.Endereco;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;

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
	public InternalResponseLocal insertEndereco(Endereco endereco)
	{
		InternalResponseLocal response = new InternalResponseLocal();

		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_INSERT, endereco);
		response.setStatus(Status.NoRowsInsertedError);
		response.setId(academiaId);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IEnderecoDAC#updateEndereco(com.qat.samples.sysmgmt.base.model.Endereco)
	 */
	@Override
	public InternalResponseLocal updateEndereco(Endereco endereco)
	{

		InternalResponseLocal response = new InternalResponseLocal();

		Integer academiaId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_UPDATE, endereco);
		response.setStatus(Status.NoRowsInsertedError);
		response.setId(academiaId);
		return response;
	}

	@Override
	public InternalResponse deleteEndereco(Endereco endereco)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
