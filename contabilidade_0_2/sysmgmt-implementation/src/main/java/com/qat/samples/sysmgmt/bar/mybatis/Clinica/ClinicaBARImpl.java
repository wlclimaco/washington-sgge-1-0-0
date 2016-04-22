package com.qat.samples.sysmgmt.bar.mybatis.Clinica;


import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.samples.sysmgmt.bar.Clinica.IClinicaBAR;
import com.qat.samples.sysmgmt.clinica.model.Consulta;
import com.qat.samples.sysmgmt.clinica.model.Exame;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class ClinicaBARImpl extends SqlSessionDaoSupport implements IClinicaBAR
{

/** The Constant ZERO. */
	private static final int ZERO = 0;


///===================================### MEDICO ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_MEDICO = "MedicoMap.";

/** The Constant STMT_INSERT_MEDICO. */
private static final String STMT_INSERT_MEDICO = NAMESPACE_MEDICO + "insertMedico";

/** The Constant STMT_UPDATE_MEDICO. */
private static final String STMT_UPDATE_MEDICO = NAMESPACE_MEDICO + "updateMedico";

/** The Constant STMT_DELETE_MEDICO. */
private static final String STMT_DELETE_MEDICO = NAMESPACE_MEDICO + "deleteMedicoById";

	/** The Constant STMT_DELETE_MEDICO_ALL. */
	private static final String STMT_DELETE_MEDICO_ALL = NAMESPACE_MEDICO + "deleteAllMedicos";

	/** The Constant STMT_FETCH_MEDICO. */
	private static final String STMT_FETCH_MEDICO = NAMESPACE_MEDICO + "fetchMedicoById";

	/** The Constant STMT_FETCH_MEDICO_ALL. */
	private static final String STMT_FETCH_MEDICO_ALL = NAMESPACE_MEDICO + "fetchAllMedicos";

	/** The Constant STMT_FETCH_MEDICO_COUNT. */
	private static final String STMT_FETCH_MEDICO_COUNT = NAMESPACE_MEDICO + "fetchMedicoRowCount";

	/** The Constant STMT_FETCH_MEDICO_ALL_REQUEST. */
	private static final String STMT_FETCH_MEDICO_ALL_REQUEST = NAMESPACE_MEDICO + "fetchAllMedicosRequest";

///===================================### PACIENTE ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_PACIENTE = "PacienteMap.";

/** The Constant STMT_INSERT_PACIENTE. */
private static final String STMT_INSERT_PACIENTE = NAMESPACE_PACIENTE + "insertPaciente";

/** The Constant STMT_UPDATE_PACIENTE. */
private static final String STMT_UPDATE_PACIENTE = NAMESPACE_PACIENTE + "updatePaciente";

/** The Constant STMT_DELETE_PACIENTE. */
private static final String STMT_DELETE_PACIENTE = NAMESPACE_PACIENTE + "deletePacienteById";

	/** The Constant STMT_DELETE_PACIENTE_ALL. */
	private static final String STMT_DELETE_PACIENTE_ALL = NAMESPACE_PACIENTE + "deleteAllPacientes";

	/** The Constant STMT_FETCH_PACIENTE. */
	private static final String STMT_FETCH_PACIENTE = NAMESPACE_PACIENTE + "fetchPacienteById";

	/** The Constant STMT_FETCH_PACIENTE_ALL. */
	private static final String STMT_FETCH_PACIENTE_ALL = NAMESPACE_PACIENTE + "fetchAllPacientes";

	/** The Constant STMT_FETCH_PACIENTE_COUNT. */
	private static final String STMT_FETCH_PACIENTE_COUNT = NAMESPACE_PACIENTE + "fetchPacienteRowCount";

	/** The Constant STMT_FETCH_PACIENTE_ALL_REQUEST. */
	private static final String STMT_FETCH_PACIENTE_ALL_REQUEST = NAMESPACE_PACIENTE + "fetchAllPacientesRequest";

///===================================### CONSULTA ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_CONSULTA = "ConsultaMap.";

/** The Constant STMT_INSERT_CONSULTA. */
private static final String STMT_INSERT_CONSULTA = NAMESPACE_CONSULTA + "insertConsulta";

/** The Constant STMT_UPDATE_CONSULTA. */
private static final String STMT_UPDATE_CONSULTA = NAMESPACE_CONSULTA + "updateConsulta";

/** The Constant STMT_DELETE_CONSULTA. */
private static final String STMT_DELETE_CONSULTA = NAMESPACE_CONSULTA + "deleteConsultaById";

	/** The Constant STMT_DELETE_CONSULTA_ALL. */
	private static final String STMT_DELETE_CONSULTA_ALL = NAMESPACE_CONSULTA + "deleteAllConsultas";

	/** The Constant STMT_FETCH_CONSULTA. */
	private static final String STMT_FETCH_CONSULTA = NAMESPACE_CONSULTA + "fetchConsultaById";

	/** The Constant STMT_FETCH_CONSULTA_ALL. */
	private static final String STMT_FETCH_CONSULTA_ALL = NAMESPACE_CONSULTA + "fetchAllConsultas";

	/** The Constant STMT_FETCH_CONSULTA_COUNT. */
	private static final String STMT_FETCH_CONSULTA_COUNT = NAMESPACE_CONSULTA + "fetchConsultaRowCount";

	/** The Constant STMT_FETCH_CONSULTA_ALL_REQUEST. */
	private static final String STMT_FETCH_CONSULTA_ALL_REQUEST = NAMESPACE_CONSULTA + "fetchAllConsultasRequest";

///===================================### EXAME ####======================================
/** The Constant NAMESPACE. */
private static final String NAMESPACE_EXAME = "ExameMap.";

/** The Constant STMT_INSERT_EXAME. */
private static final String STMT_INSERT_EXAME = NAMESPACE_EXAME + "insertExame";

/** The Constant STMT_UPDATE_EXAME. */
private static final String STMT_UPDATE_EXAME = NAMESPACE_EXAME + "updateExame";

/** The Constant STMT_DELETE_EXAME. */
private static final String STMT_DELETE_EXAME = NAMESPACE_EXAME + "deleteExameById";

	/** The Constant STMT_DELETE_EXAME_ALL. */
	private static final String STMT_DELETE_EXAME_ALL = NAMESPACE_EXAME + "deleteAllExames";

	/** The Constant STMT_FETCH_EXAME. */
	private static final String STMT_FETCH_EXAME = NAMESPACE_EXAME + "fetchExameById";

	/** The Constant STMT_FETCH_EXAME_ALL. */
	private static final String STMT_FETCH_EXAME_ALL = NAMESPACE_EXAME + "fetchAllExames";

	/** The Constant STMT_FETCH_EXAME_COUNT. */
	private static final String STMT_FETCH_EXAME_COUNT = NAMESPACE_EXAME + "fetchExameRowCount";

	/** The Constant STMT_FETCH_EXAME_ALL_REQUEST. */
	private static final String STMT_FETCH_EXAME_ALL_REQUEST = NAMESPACE_EXAME + "fetchAllExamesRequest";

//===================================### MEDICO ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#insertMedico(com.qat.samples.sysmgmt.base.model.Medico)
 */
@Override
public InternalResponse insertMedico(Medico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MEDICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#updateMedico(com.qat.samples.sysmgmt.base.model.Medico)
 */
@Override
public InternalResponse updateMedico(Medico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MEDICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#deleteMedico(com.qat.samples.sysmgmt.base.model.Medico)
 */
@Override
public InternalResponse deleteMedicoById(Medico county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MEDICO, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#deleteAllMedicos()
 */
@Override
public InternalResponse deleteAllMedicos()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MEDICO_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IMedicoBAR#fetchMedicoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	response.addResult((Medico)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MEDICO,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IMedicoBAR#fetchAllMedicosCache()
 */
@Override
public InternalResultsResponse<Medico> fetchAllMedicos(Medico medico)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MEDICO_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IMedicoBAR#fetchMedicosByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request)
{
	InternalResultsResponse<Medico> response = new InternalResultsResponse<Medico>();
	fetchMedicosByRequest(getSqlSession(), request, STMT_FETCH_MEDICO_COUNT, STMT_FETCH_MEDICO_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchMedicosByRequest ####======================================

public static void fetchMedicosByRequest(SqlSession sqlSession, MedicoInquiryRequest request, String countStatement,
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


//===================================### PACIENTE ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#insertPaciente(com.qat.samples.sysmgmt.base.model.Paciente)
 */
@Override
public InternalResponse insertPaciente(Paciente county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PACIENTE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#updatePaciente(com.qat.samples.sysmgmt.base.model.Paciente)
 */
@Override
public InternalResponse updatePaciente(Paciente county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PACIENTE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#deletePaciente(com.qat.samples.sysmgmt.base.model.Paciente)
 */
@Override
public InternalResponse deletePacienteById(Paciente county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PACIENTE, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#deleteAllPacientes()
 */
@Override
public InternalResponse deleteAllPacientes()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PACIENTE_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IPacienteBAR#fetchPacienteById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	response.addResult((Paciente)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PACIENTE,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IPacienteBAR#fetchAllPacientesCache()
 */
@Override
public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente paciente)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PACIENTE_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IPacienteBAR#fetchPacientesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request)
{
	InternalResultsResponse<Paciente> response = new InternalResultsResponse<Paciente>();
	fetchPacientesByRequest(getSqlSession(), request, STMT_FETCH_PACIENTE_COUNT, STMT_FETCH_PACIENTE_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchPacientesByRequest ####======================================

public static void fetchPacientesByRequest(SqlSession sqlSession, PacienteInquiryRequest request, String countStatement,
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


//===================================### CONSULTA ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConsultaBAR#insertConsulta(com.qat.samples.sysmgmt.base.model.Consulta)
 */
@Override
public InternalResponse insertConsulta(Consulta county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_CONSULTA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConsultaBAR#updateConsulta(com.qat.samples.sysmgmt.base.model.Consulta)
 */
@Override
public InternalResponse updateConsulta(Consulta county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_CONSULTA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConsultaBAR#deleteConsulta(com.qat.samples.sysmgmt.base.model.Consulta)
 */
@Override
public InternalResponse deleteConsultaById(Consulta county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONSULTA, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConsultaBAR#deleteAllConsultas()
 */
@Override
public InternalResponse deleteAllConsultas()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_CONSULTA_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IConsultaBAR#fetchConsultaById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Consulta> fetchConsultaById(FetchByIdRequest request)
{
	InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
	response.addResult((Consulta)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONSULTA,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IConsultaBAR#fetchAllConsultasCache()
 */
@Override
public InternalResultsResponse<Consulta> fetchAllConsultas(Consulta consulta)
{
	InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONSULTA_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IConsultaBAR#fetchConsultasByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Consulta> fetchConsultasByRequest(ConsultaInquiryRequest request)
{
	InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
	fetchConsultasByRequest(getSqlSession(), request, STMT_FETCH_CONSULTA_COUNT, STMT_FETCH_CONSULTA_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchConsultasByRequest ####======================================

public static void fetchConsultasByRequest(SqlSession sqlSession, ConsultaInquiryRequest request, String countStatement,
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


//===================================### EXAME ####======================================
	/**
/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IExameBAR#insertExame(com.qat.samples.sysmgmt.base.model.Exame)
 */
@Override
public InternalResponse insertExame(Exame county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_EXAME, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IExameBAR#updateExame(com.qat.samples.sysmgmt.base.model.Exame)
 */
@Override
public InternalResponse updateExame(Exame county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_EXAME, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IExameBAR#deleteExame(com.qat.samples.sysmgmt.base.model.Exame)
 */
@Override
public InternalResponse deleteExameById(Exame county)
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EXAME, county, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IExameBAR#deleteAllExames()
 */
@Override
public InternalResponse deleteAllExames()
{
	InternalResponse response = new InternalResponse();
	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_EXAME_ALL, response);
	return response;
}

/*
 * (non-Javadoc)
 * @see
 * com.qat.samples.sysmgmt.bar.IExameBAR#fetchExameById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
 */
@Override
public InternalResultsResponse<Exame> fetchExameById(FetchByIdRequest request)
{
	InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();
	response.addResult((Exame)MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_EXAME,
			request.getFetchId()));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.base.bar.IExameBAR#fetchAllExamesCache()
 */
@Override
public InternalResultsResponse<Exame> fetchAllExames(Exame exame)
{
	InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();
	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_EXAME_ALL));
	return response;
}

/*
 * (non-Javadoc)
 * @see com.qat.samples.sysmgmt.bar.IExameBAR#fetchExamesByRequest(com.qat.samples.sysmgmt.model.request.
 * PagedInquiryRequest)
 */
@Override
public InternalResultsResponse<Exame> fetchExamesByRequest(ExameInquiryRequest request)
{
	InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();
	fetchExamesByRequest(getSqlSession(), request, STMT_FETCH_EXAME_COUNT, STMT_FETCH_EXAME_ALL_REQUEST,
			response);
	return response;
}

//===================================### fetchExamesByRequest ####======================================

public static void fetchExamesByRequest(SqlSession sqlSession, ExameInquiryRequest request, String countStatement,
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
