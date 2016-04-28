package com.qat.samples.sysmgmt.bar.mybatis.Dicionario;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Dicionario.IDicionarioBAR;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Field;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class DicionarioBARImpl extends SqlSessionDaoSupport implements IDicionarioBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### CLASSES ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CLASSES = "ClassesMap.";

/** The Constant STMT_INSERT_CLASSES. */
private static final String STMT_INSERT_CLASSES = NAMESPACE_CLASSES + "insertClasses";

/** The Constant STMT_UPDATE_CLASSES. */
private static final String STMT_UPDATE_CLASSES = NAMESPACE_CLASSES + "updateClasses";

/** The Constant STMT_DELETE_CLASSES. */
private static final String STMT_DELETE_CLASSES = NAMESPACE_CLASSES + "deleteClassesById";

	/** The Constant STMT_DELETE_CLASSES_ALL. */
	private static final String STMT_DELETE_CLASSES_ALL = NAMESPACE_CLASSES + "deleteAllClassess";

	/** The Constant STMT_FETCH_CLASSES. */
	private static final String STMT_FETCH_CLASSES = NAMESPACE_CLASSES + "fetchClassesById";

	/** The Constant STMT_FETCH_CLASSES_ALL. */
	private static final String STMT_FETCH_CLASSES_ALL = NAMESPACE_CLASSES + "fetchAllClassess";

	/** The Constant STMT_FETCH_CLASSES_COUNT. */
	private static final String STMT_FETCH_CLASSES_COUNT = NAMESPACE_CLASSES + "fetchClassesRowCount";

	/** The Constant STMT_FETCH_CLASSES_ALL_REQUEST. */
	private static final String STMT_FETCH_CLASSES_ALL_REQUEST = NAMESPACE_CLASSES + "fetchAllClassessRequest";

///===================================### INTERFACE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_INTERFACE = "InterfaceMap.";

/** The Constant STMT_INSERT_INTERFACE. */
private static final String STMT_INSERT_INTERFACE = NAMESPACE_INTERFACE + "insertInterface";

/** The Constant STMT_UPDATE_INTERFACE. */
private static final String STMT_UPDATE_INTERFACE = NAMESPACE_INTERFACE + "updateInterface";

/** The Constant STMT_DELETE_INTERFACE. */
private static final String STMT_DELETE_INTERFACE = NAMESPACE_INTERFACE + "deleteInterfaceById";

	/** The Constant STMT_DELETE_INTERFACE_ALL. */
	private static final String STMT_DELETE_INTERFACE_ALL = NAMESPACE_INTERFACE + "deleteAllInterfaces";

	/** The Constant STMT_FETCH_INTERFACE. */
	private static final String STMT_FETCH_INTERFACE = NAMESPACE_INTERFACE + "fetchInterfaceById";

	/** The Constant STMT_FETCH_INTERFACE_ALL. */
	private static final String STMT_FETCH_INTERFACE_ALL = NAMESPACE_INTERFACE + "fetchAllInterfaces";

	/** The Constant STMT_FETCH_INTERFACE_COUNT. */
	private static final String STMT_FETCH_INTERFACE_COUNT = NAMESPACE_INTERFACE + "fetchInterfaceRowCount";

	/** The Constant STMT_FETCH_INTERFACE_ALL_REQUEST. */
	private static final String STMT_FETCH_INTERFACE_ALL_REQUEST = NAMESPACE_INTERFACE + "fetchAllInterfacesRequest";

///===================================### FIELD ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_FIELD = "FieldMap.";

/** The Constant STMT_INSERT_FIELD. */
private static final String STMT_INSERT_FIELD = NAMESPACE_FIELD + "insertField";

/** The Constant STMT_UPDATE_FIELD. */
private static final String STMT_UPDATE_FIELD = NAMESPACE_FIELD + "updateField";

/** The Constant STMT_DELETE_FIELD. */
private static final String STMT_DELETE_FIELD = NAMESPACE_FIELD + "deleteFieldById";

	/** The Constant STMT_DELETE_FIELD_ALL. */
	private static final String STMT_DELETE_FIELD_ALL = NAMESPACE_FIELD + "deleteAllFields";

	/** The Constant STMT_FETCH_FIELD. */
	private static final String STMT_FETCH_FIELD = NAMESPACE_FIELD + "fetchFieldById";

	/** The Constant STMT_FETCH_FIELD_ALL. */
	private static final String STMT_FETCH_FIELD_ALL = NAMESPACE_FIELD + "fetchAllFields";

	/** The Constant STMT_FETCH_FIELD_COUNT. */
	private static final String STMT_FETCH_FIELD_COUNT = NAMESPACE_FIELD + "fetchFieldRowCount";

	/** The Constant STMT_FETCH_FIELD_ALL_REQUEST. */
	private static final String STMT_FETCH_FIELD_ALL_REQUEST = NAMESPACE_FIELD + "fetchAllFieldsRequest";

//===================================### CLASSES ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassesBAR#insertClasses(com.qat.samples.sysmgmt.base.model.Classes)
 */
@Override
public InternalResponse insertClasses(Classes classes)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CLASSES, classes, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassesBAR#updateClasses(com.qat.samples.sysmgmt.base.model.Classes)
 */
@Override
public InternalResponse updateClasses(Classes classes)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CLASSES, classes, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassesBAR#deleteClasses(com.qat.samples.sysmgmt.base.model.Classes)
 */
@Override
public InternalResponse deleteClassesById(Classes classes)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLASSES, classes, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassesBAR#deleteAllClassess()
 */
@Override
public InternalResponse deleteAllClassess()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CLASSES_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IClassesBAR#fetchClassesById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Classes fetchClassesById(FetchByIdRequest request)
{
return (Classes)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLASSES, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IClassesBAR#fetchAllClassessCache()
 */
@Override
public InternalResultsResponse<Classes> fetchAllClassess(Classes classes)
{
	InternalResultsResponse<Classes> response = new InternalResultsResponse<Classes>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLASSES_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IClassesBAR#fetchClassessByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Classes> fetchClassessByRequest(ClassesInquiryRequest request)
{
	InternalResultsResponse<Classes> response = new InternalResultsResponse<Classes>();
	fetchClassessByRequest(getSqlSession(), request, STMT_FETCH_CLASSES_COUNT, STMT_FETCH_CLASSES_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchClassessByRequest ####======================================

public static void fetchClassessByRequest(SqlSession sqlSession, ClassesInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### INTERFACE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInterfaceBAR#insertInterface(com.qat.samples.sysmgmt.base.model.Interface)
 */
@Override
public InternalResponse insertInterface(Interface interfaces)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_INTERFACE, interfaces, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInterfaceBAR#updateInterface(com.qat.samples.sysmgmt.base.model.Interface)
 */
@Override
public InternalResponse updateInterface(Interface interfaces)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_INTERFACE, interfaces, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInterfaceBAR#deleteInterface(com.qat.samples.sysmgmt.base.model.Interface)
 */
@Override
public InternalResponse deleteInterfaceById(Interface interfaces)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_INTERFACE, interfaces, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInterfaceBAR#deleteAllInterfaces()
 */
@Override
public InternalResponse deleteAllInterfaces()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_INTERFACE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IInterfaceBAR#fetchInterfaceById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Interface fetchInterfaceById(FetchByIdRequest request)
{
return (Interface)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_INTERFACE, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IInterfaceBAR#fetchAllInterfacesCache()
 */
@Override
public InternalResultsResponse<Interface> fetchAllInterfaces(Interface interfaces)
{
	InternalResultsResponse<Interface> response = new InternalResultsResponse<Interface>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_INTERFACE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IInterfaceBAR#fetchInterfacesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Interface> fetchInterfacesByRequest(InterfaceInquiryRequest request)
{
	InternalResultsResponse<Interface> response = new InternalResultsResponse<Interface>();
	fetchInterfacesByRequest(getSqlSession(), request, STMT_FETCH_INTERFACE_COUNT, STMT_FETCH_INTERFACE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchInterfacesByRequest ####======================================

public static void fetchInterfacesByRequest(SqlSession sqlSession, InterfaceInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}


//===================================### FIELD ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#insertField(com.qat.samples.sysmgmt.base.model.Field)
 */
@Override
public InternalResponse insertField(Field field)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_FIELD, field, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#updateField(com.qat.samples.sysmgmt.base.model.Field)
 */
@Override
public InternalResponse updateField(Field field)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_FIELD, field, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#deleteField(com.qat.samples.sysmgmt.base.model.Field)
 */
@Override
public InternalResponse deleteFieldById(Field field)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FIELD, field, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#deleteAllFields()
 */
@Override
public InternalResponse deleteAllFields()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FIELD_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IFieldBAR#fetchFieldById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public Field fetchFieldById(FetchByIdRequest request)
{
return (Field)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FIELD, request.getFetchId());

}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#fetchAllFieldsCache()
 */
@Override
public InternalResultsResponse<Field> fetchAllFields(Field field)
{
	InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FIELD_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IFieldBAR#fetchFieldsByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Field> fetchFieldsByRequest(FieldInquiryRequest request)
{
	InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();
	fetchFieldsByRequest(getSqlSession(), request, STMT_FETCH_FIELD_COUNT, STMT_FETCH_FIELD_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchFieldsByRequest ####======================================

public static void fetchFieldsByRequest(SqlSession sqlSession, FieldInquiryRequest request, String countStatement,
			String fetchPagedStatement,
			InternalResultsResponse<?> response)
	{

		// If the user requested the total rows/record count
		if (request.isPreQueryCount())
		{
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)
			{
				response.setStatus(BusinessErrorCategory.NoRowsFound);
				return;
			}
		}

		// Fetch Objects by InquiryRequest Object, paged of course
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));

		// move request start page to response start page
		response.getResultsSetInfo().setStartPage(request.getStartPage());

		// move request page size to response page size
		response.getResultsSetInfo().setPageSize(request.getPageSize());

		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))
		{
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

}
