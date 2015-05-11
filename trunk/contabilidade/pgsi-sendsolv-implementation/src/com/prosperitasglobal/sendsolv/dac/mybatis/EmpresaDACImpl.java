package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.IEmpresaDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class EmpresaDACImpl.
 */
public class EmpresaDACImpl extends SqlSessionDaoSupport implements IEmpresaDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "EmpresaMap.";

	/** The Constant CBOF_NAMESPACE. */
	private static final String CBOF_NAMESPACE = "CBOFMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchEmpresaRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllEmpresasByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchEmpresaById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertEmpresa";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_CONTACT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_CONTACT = EMPRESA_NAMESPACE
			+ "associateEmpresaWithContact";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateEmpresa";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteEmpresaById";

	/** The Constant EMPRESA_DOCUMENT_STMT_UPDATE. */
	private static final String EMPRESA_DOCUMENT_STMT_UPDATE = EMPRESA_NAMESPACE
			+ "updateEmpresaDocument";

	/** The Constant EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT. */
	private static final String EMPRESA_STMT_ASSOC_ORG_TO_DOCUMENT = EMPRESA_NAMESPACE
			+ "associateEmpresaWithDocument";

	/** The Constant EMPRESA_STMT_DELETE_DOCUMENT. */
	private static final String EMPRESA_STMT_DELETE_DOCUMENT = EMPRESA_NAMESPACE
			+ "deleteEmpresaDocument";

	/** The Constant STMT_VERSION. */
	private static final String EMPRESA_STMT_VERSION = EMPRESA_NAMESPACE + "fetchVersionNumber";

	/** The Constant EMPRESA_STMT_UPDATE_EMPRESA_STATUS. */
	private static final String EMPRESA_STMT_UPDATE_EMPRESA_STATUS = CBOF_NAMESPACE
			+ "updateBusinessStatus";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmpresaDACImpl.class);

	/** The valid sort fields for an empresa inquiry. Will be injected by Spring. */
	private Map<String, String> empresaInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the empresa inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the empresa inquiry.
	 */
	public Map<String, String> getEmpresaInquiryValidSortFields()
	{
		return empresaInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the empresa inquiry. Attribute injected by Spring.
	 *
	 * @param empresaInquiryValidSortFields The valid sort fields for the empresa inquiry to set.
	 */
	public void setEmpresaInquiryValidSortFields(Map<String, String> empresaInquiryValidSortFields)
	{
		this.empresaInquiryValidSortFields = empresaInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#insertEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, empresa, response);

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		insertCount += maintainEmpresaAssociations(empresa, response);

		// Finally, if something was inserted then add the Empresa to the result.
		if (insertCount > 0)
		{
			response.addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#updateEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(Empresa empresa)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(empresa.getModelAction())
				&& (empresa.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, empresa,
							response);
		}

		if (response.isInError())
		{
			return response;
		}
		// Next traverse the object graph and "maintain" the associations
		updateCount += maintainEmpresaAssociations(empresa, response);

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(empresa);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#deleteEmpresa(com.prosperitasglobal.sendsolv.model
	 * .Empresa)
	 */
	@Override
	public InternalResponse deleteEmpresa(Empresa empresa)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, empresa, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#fetchEmpresaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IEmpresaDAC#fetchEmpresaByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain empresa associations.
	 *
	 * @param empresa the empresa
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainEmpresaAssociations(Empresa empresa,
			InternalResultsResponse<Empresa> response)
	{
		Integer count = 0;
		// First Maintain Contacts
		// if (ValidationUtil.isNullOrEmpty(empresa.getContactList()))
		// {
		// return count;
		// }
		// // For Each Contact...
		// for (Contact contact : empresa.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(empresa.getId());
		//
		// if (ValidationUtil.isNull(contact.getModelAction()))
		// {
		// continue;
		// }
		// switch (contact.getModelAction())
		// {
		// case INSERT:
		// count = getContactDAC().insertContact(contact,
		// EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
		// break;
		// case UPDATE:
		// count = getContactDAC().updateContact(contact, response);
		// break;
		// case DELETE:
		// count = getContactDAC().deleteBusinessContact(contact, response);
		// break;
		// default:
		// if (LOG.isDebugEnabled())
		// {
		// LOG.debug("ModelAction for Empresa missing!");
		// }
		// break;
		// }
		// }
		return count;
	}

	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresas()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
