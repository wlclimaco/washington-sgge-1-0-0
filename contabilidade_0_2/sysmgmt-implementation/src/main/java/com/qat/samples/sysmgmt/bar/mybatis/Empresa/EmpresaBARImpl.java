/** create by system gera-java version 1.0.0 01/05/2016 18:42 : 57*/
package com.qat.samples.sysmgmt.bar.mybatis.Empresa;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.MyBatisBARHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.bar.Configuracao.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.bar.Email.IEmailBAR;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.bar.Endereco.IEnderecoBAR;
import com.qat.samples.sysmgmt.bar.Financeiro.IFinanceiroBAR;
import com.qat.samples.sysmgmt.bar.Fiscal.IFiscalBAR;
import com.qat.samples.sysmgmt.bar.Historico.IHistoricoBAR;
import com.qat.samples.sysmgmt.bar.Notes.INotesBAR;
import com.qat.samples.sysmgmt.bar.Site.ISiteBAR;
import com.qat.samples.sysmgmt.bar.Socios.ISociosBAR;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.bar.Telefone.ITelefoneBAR;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.BaseBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.ConfiguracaoBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.EnviarEmailBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.InsertHistBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.PlanoByEmpresaBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.SociosBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.StatusBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.UserRoleBARD;
import com.qat.samples.sysmgmt.bar.mybatis.delegate.UsuarioBARD;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.TransactionInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Ajuda;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Field;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Menu;
import com.qat.samples.sysmgmt.entidade.model.Message;
import com.qat.samples.sysmgmt.entidade.model.Pagina;
import com.qat.samples.sysmgmt.entidade.model.Role;
import com.qat.samples.sysmgmt.entidade.model.Transaction;
import com.qat.samples.sysmgmt.entidade.model.UserRoles;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.Validacao;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.MessageInquiryRequest;
import com.qat.samples.sysmgmt.util.model.AcaoEnum;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

/**
 * The Class CountyBARImpl. (Business Access Repository - BAR)
 */
@Repository
public class EmpresaBARImpl extends SqlSessionDaoSupport implements IEmpresaBAR {

	/** The Constant ZERO. */
	private static final int ZERO = 0;
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ENTIDADE = "EntidadeMap.";

	/** The Constant STMT_INSERT_EMPRESA. */
	private static final String STMT_INSERT_ENTIDADE = NAMESPACE_ENTIDADE + "insertEntidade";

	/** The Constant STMT_UPDATE_EMPRESA. */
	private static final String STMT_UPDATE_ENTIDADE = NAMESPACE_ENTIDADE + "updateEntidade";

	/** The Constant STMT_DELETE_EMPRESA. */
	private static final String STMT_DELETE_ENTIDADE = NAMESPACE_ENTIDADE + "deleteEntidadeById";

	/** The Constant STMT_DELETE_EMPRESA_ALL. */
	private static final String STMT_DELETE_ENTIDADE_ALL = NAMESPACE_ENTIDADE + "deleteAllEntidades";

	private static final String NAMESPACE_MESSAGE = "MessageMap.";

	/** The Constant STMT_INSERT_EMPRESA. */
	private static final String STMT_INSERT_MESSAGE = NAMESPACE_MESSAGE + "insertMessage";

	/** The Constant STMT_UPDATE_EMPRESA. */
	private static final String STMT_UPDATE_MESSAGE = NAMESPACE_MESSAGE + "updateMessage";

	/** The Constant STMT_DELETE_EMPRESA. */
	private static final String STMT_DELETE_MESSAGE = NAMESPACE_MESSAGE + "deleteMessageById";

	/** The Constant STMT_FETCH_EMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_MESSAGE_ALL_REQUEST = NAMESPACE_MESSAGE + "fetchAllMessagesByRequest";

	/** The Constant STMT_FETCH_EMPRESA_COUNT. */
	private static final String STMT_FETCH_MESSAGE_COUNT = NAMESPACE_MESSAGE + "fetchMessageRowCount";



	/// ===================================### EMPRESA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_EMPRESA = "EmpresaMap.";

	/** The Constant STMT_FETCH_EMPRESA. */
	private static final String STMT_FETCH_EMPRESA = NAMESPACE_EMPRESA + "fetchEmpresaById";

	private static final String STMT_INSERT_EMPRESA = NAMESPACE_EMPRESA + "insertEmpresa";

	private static final String STMT_UPDATE_EMPRESA = NAMESPACE_EMPRESA + "updateEmpresa";

	/** The Constant STMT_FETCH_EMPRESA_ALL. */
	private static final String STMT_FETCH_EMPRESA_ALL = NAMESPACE_EMPRESA + "fetchAllEmpresas";

	/** The Constant STMT_FETCH_EMPRESA_COUNT. */
	private static final String STMT_FETCH_EMPRESA_COUNT = NAMESPACE_EMPRESA + "fetchEmpresaRowCount";

	/** The Constant STMT_FETCH_EMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_EMPRESA_ALL_REQUEST = NAMESPACE_EMPRESA + "fetchAllEmpresasByRequest";

	/// ======================================

	private static final String NAMESPACE_ENDERECO = "EnderecoMap.";

	/** The Constant STMT_FETCH_EMPRESA_COUNT. */
	private static final String STMT_FETCH_ENDERECO_COUNT = NAMESPACE_ENDERECO + "fetchEnderecoRowCount";

	/** The Constant STMT_FETCH_EMPRESA_ALL_REQUEST. */
	private static final String STMT_FETCH_ENDERECO_ALL_REQUEST = NAMESPACE_ENDERECO + "fetchAllEnderecosRequest";

	/// ===================================### FILIAL
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_FILIAL = "FilialMap.";

	/** The Constant STMT_FETCH_FILIAL. */
	private static final String STMT_FETCH_FILIAL = NAMESPACE_FILIAL + "fetchFilialById";

	/** The Constant STMT_FETCH_FILIAL_ALL. */
	private static final String STMT_FETCH_FILIAL_ALL = NAMESPACE_FILIAL + "fetchAllFilials";

	/** The Constant STMT_FETCH_FILIAL_COUNT. */
	private static final String STMT_FETCH_FILIAL_COUNT = NAMESPACE_FILIAL + "fetchFilialRowCount";

	/** The Constant STMT_FETCH_FILIAL_ALL_REQUEST. */
	private static final String STMT_FETCH_FILIAL_ALL_REQUEST = NAMESPACE_FILIAL + "fetchAllFilialsByRequest";

	/// ===================================### USERROLES
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_USERROLES = "UserRolesMap.";

	/** The Constant STMT_INSERT_USERROLES. */
	private static final String STMT_INSERT_USERROLES = NAMESPACE_USERROLES + "insertUserRoles";

	/** The Constant STMT_UPDATE_USERROLES. */
	private static final String STMT_UPDATE_USERROLES = NAMESPACE_USERROLES + "updateUserRoles";

	/** The Constant STMT_DELETE_USERROLES. */
	private static final String STMT_DELETE_USERROLES = NAMESPACE_USERROLES + "deleteUserRolesById";

	/** The Constant STMT_DELETE_USERROLES_ALL. */
	private static final String STMT_DELETE_USERROLES_ALL = NAMESPACE_USERROLES + "deleteAllUserRoless";

	/** The Constant STMT_FETCH_USERROLES. */
	private static final String STMT_FETCH_USERROLES = NAMESPACE_USERROLES + "fetchUserRolesById";

	/** The Constant STMT_FETCH_USERROLES_ALL. */
	private static final String STMT_FETCH_USERROLES_ALL = NAMESPACE_USERROLES + "fetchAllUserRoless";

	/** The Constant STMT_FETCH_USERROLES_COUNT. */
	private static final String STMT_FETCH_USERROLES_COUNT = NAMESPACE_USERROLES + "fetchUserRolesRowCount";

	/** The Constant STMT_FETCH_USERROLES_ALL_REQUEST. */
	private static final String STMT_FETCH_USERROLES_ALL_REQUEST = NAMESPACE_USERROLES + "fetchAllUserRolessRequest";

	/// ===================================### ROLE
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ROLE = "RoleMap.";

	/** The Constant STMT_INSERT_ROLE. */
	private static final String STMT_INSERT_ROLE = NAMESPACE_ROLE + "insertRole";

	/** The Constant STMT_UPDATE_ROLE. */
	private static final String STMT_UPDATE_ROLE = NAMESPACE_ROLE + "updateRole";

	/** The Constant STMT_DELETE_ROLE. */
	private static final String STMT_DELETE_ROLE = NAMESPACE_ROLE + "deleteRoleById";

	/** The Constant STMT_DELETE_ROLE_ALL. */
	private static final String STMT_DELETE_ROLE_ALL = NAMESPACE_ROLE + "deleteAllRoles";

	/** The Constant STMT_FETCH_ROLE. */
	private static final String STMT_FETCH_ROLE = NAMESPACE_ROLE + "fetchRoleById";

	/** The Constant STMT_FETCH_ROLE_ALL. */
	private static final String STMT_FETCH_ROLE_ALL = NAMESPACE_ROLE + "fetchAllRoles";

	/** The Constant STMT_FETCH_ROLE_COUNT. */
	private static final String STMT_FETCH_ROLE_COUNT = NAMESPACE_ROLE + "fetchRoleRowCount";

	/** The Constant STMT_FETCH_ROLE_ALL_REQUEST. */
	private static final String STMT_FETCH_ROLE_ALL_REQUEST = NAMESPACE_ROLE + "fetchAllRolesRequest";

	/// ===================================### PAGINA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_PAGINA = "PaginaMap.";

	/** The Constant STMT_INSERT_PAGINA. */
	private static final String STMT_INSERT_PAGINA = NAMESPACE_PAGINA + "insertPagina";

	/** The Constant STMT_UPDATE_PAGINA. */
	private static final String STMT_UPDATE_PAGINA = NAMESPACE_PAGINA + "updatePagina";

	/** The Constant STMT_DELETE_PAGINA. */
	private static final String STMT_DELETE_PAGINA = NAMESPACE_PAGINA + "deletePaginaById";

	/** The Constant STMT_DELETE_PAGINA_ALL. */
	private static final String STMT_DELETE_PAGINA_ALL = NAMESPACE_PAGINA + "deleteAllPaginas";

	/** The Constant STMT_FETCH_PAGINA. */
	private static final String STMT_FETCH_PAGINA = NAMESPACE_PAGINA + "fetchPaginaById";

	/** The Constant STMT_FETCH_PAGINA_ALL. */
	private static final String STMT_FETCH_PAGINA_ALL = NAMESPACE_PAGINA + "fetchAllPaginas";

	/** The Constant STMT_FETCH_PAGINA_COUNT. */
	private static final String STMT_FETCH_PAGINA_COUNT = NAMESPACE_PAGINA + "fetchPaginaRowCount";

	/** The Constant STMT_FETCH_PAGINA_ALL_REQUEST. */
	private static final String STMT_FETCH_PAGINA_ALL_REQUEST = NAMESPACE_PAGINA + "fetchAllPaginasRequest";

	/// ===================================### VALIDACAO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_VALIDACAO = "ValidacaoMap.";

	/** The Constant STMT_INSERT_VALIDACAO. */
	private static final String STMT_INSERT_VALIDACAO = NAMESPACE_VALIDACAO + "insertValidacao";

	/** The Constant STMT_UPDATE_VALIDACAO. */
	private static final String STMT_UPDATE_VALIDACAO = NAMESPACE_VALIDACAO + "updateValidacao";

	/** The Constant STMT_DELETE_VALIDACAO. */
	private static final String STMT_DELETE_VALIDACAO = NAMESPACE_VALIDACAO + "deleteValidacaoById";

	/** The Constant STMT_DELETE_VALIDACAO_ALL. */
	private static final String STMT_DELETE_VALIDACAO_ALL = NAMESPACE_VALIDACAO + "deleteAllValidacaos";

	/** The Constant STMT_FETCH_VALIDACAO. */
	private static final String STMT_FETCH_VALIDACAO = NAMESPACE_VALIDACAO + "fetchValidacaoById";

	/** The Constant STMT_FETCH_VALIDACAO_ALL. */
	private static final String STMT_FETCH_VALIDACAO_ALL = NAMESPACE_VALIDACAO + "fetchAllValidacaos";

	/** The Constant STMT_FETCH_VALIDACAO_COUNT. */
	private static final String STMT_FETCH_VALIDACAO_COUNT = NAMESPACE_VALIDACAO + "fetchValidacaoRowCount";

	/** The Constant STMT_FETCH_VALIDACAO_ALL_REQUEST. */
	private static final String STMT_FETCH_VALIDACAO_ALL_REQUEST = NAMESPACE_VALIDACAO + "fetchAllValidacaosRequest";

	/// ===================================### FIELD
	/// ####======================================
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

	/// ===================================### AJUDA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_AJUDA = "AjudaMap.";

	/** The Constant STMT_INSERT_AJUDA. */
	private static final String STMT_INSERT_AJUDA = NAMESPACE_AJUDA + "insertAjuda";

	/** The Constant STMT_UPDATE_AJUDA. */
	private static final String STMT_UPDATE_AJUDA = NAMESPACE_AJUDA + "updateAjuda";

	/** The Constant STMT_DELETE_AJUDA. */
	private static final String STMT_DELETE_AJUDA = NAMESPACE_AJUDA + "deleteAjudaById";

	/** The Constant STMT_DELETE_AJUDA_ALL. */
	private static final String STMT_DELETE_AJUDA_ALL = NAMESPACE_AJUDA + "deleteAllAjudas";

	/** The Constant STMT_FETCH_AJUDA. */
	private static final String STMT_FETCH_AJUDA = NAMESPACE_AJUDA + "fetchAjudaById";

	/** The Constant STMT_FETCH_AJUDA_ALL. */
	private static final String STMT_FETCH_AJUDA_ALL = NAMESPACE_AJUDA + "fetchAllAjudas";

	/** The Constant STMT_FETCH_AJUDA_COUNT. */
	private static final String STMT_FETCH_AJUDA_COUNT = NAMESPACE_AJUDA + "fetchAjudaRowCount";

	/** The Constant STMT_FETCH_AJUDA_ALL_REQUEST. */
	private static final String STMT_FETCH_AJUDA_ALL_REQUEST = NAMESPACE_AJUDA + "fetchAllAjudasRequest";

	/// ===================================### MENU
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_MENU = "MenuMap.";

	/** The Constant STMT_INSERT_MENU. */
	private static final String STMT_INSERT_MENU = NAMESPACE_MENU + "insertMenu";

	/** The Constant STMT_UPDATE_MENU. */
	private static final String STMT_UPDATE_MENU = NAMESPACE_MENU + "updateMenu";

	/** The Constant STMT_DELETE_MENU. */
	private static final String STMT_DELETE_MENU = NAMESPACE_MENU + "deleteMenuById";

	/** The Constant STMT_DELETE_MENU_ALL. */
	private static final String STMT_DELETE_MENU_ALL = NAMESPACE_MENU + "deleteAllMenus";

	/** The Constant STMT_FETCH_MENU. */
	private static final String STMT_FETCH_MENU = NAMESPACE_MENU + "fetchMenuById";

	/** The Constant STMT_FETCH_MENU_ALL. */
	private static final String STMT_FETCH_MENU_ALL = NAMESPACE_MENU + "fetchAllMenus";

	/** The Constant STMT_FETCH_MENU_COUNT. */
	private static final String STMT_FETCH_MENU_COUNT = NAMESPACE_MENU + "fetchMenuRowCount";

	/** The Constant STMT_FETCH_MENU_ALL_REQUEST. */
	private static final String STMT_FETCH_MENU_ALL_REQUEST = NAMESPACE_MENU + "fetchAllMenusRequest";

	/// ===================================### DEPOSITO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_DEPOSITO = "DepositoMap.";

	/** The Constant STMT_FETCH_DEPOSITO. */
	private static final String STMT_FETCH_DEPOSITO = NAMESPACE_DEPOSITO + "fetchDepositoById";

	/** The Constant STMT_FETCH_DEPOSITO_ALL. */
	private static final String STMT_FETCH_DEPOSITO_ALL = NAMESPACE_DEPOSITO + "fetchAllDepositos";

	/** The Constant STMT_FETCH_DEPOSITO_COUNT. */
	private static final String STMT_FETCH_DEPOSITO_COUNT = NAMESPACE_DEPOSITO + "fetchDepositoRowCount";

	/** The Constant STMT_FETCH_DEPOSITO_ALL_REQUEST. */
	private static final String STMT_FETCH_DEPOSITO_ALL_REQUEST = NAMESPACE_DEPOSITO + "fetchAllDepositosByRequest";

	/// ===================================### USUARIO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_USUARIO = "UsuarioMap.";

	/** The Constant STMT_INSERT_USUARIO. */
	private static final String STMT_INSERT_USUARIO = NAMESPACE_USUARIO + "insertUsuario";

	/** The Constant STMT_UPDATE_USUARIO. */
	private static final String STMT_UPDATE_USUARIO = NAMESPACE_USUARIO + "updateUsuario";

	/** The Constant STMT_DELETE_USUARIO. */
	private static final String STMT_DELETE_USUARIO = NAMESPACE_USUARIO + "deleteUsuarioById";

	/** The Constant STMT_DELETE_USUARIO_ALL. */
	private static final String STMT_DELETE_USUARIO_ALL = NAMESPACE_USUARIO + "deleteAllUsuarios";

	/** The Constant STMT_FETCH_USUARIO. */
	private static final String STMT_FETCH_USUARIO = NAMESPACE_USUARIO + "fetchUsuarioById";

	/** The Constant STMT_FETCH_USUARIO_ALL. */
	private static final String STMT_FETCH_USUARIO_ALL = NAMESPACE_USUARIO + "fetchAllUsuarios";

	/** The Constant STMT_FETCH_USUARIO_COUNT. */
	private static final String STMT_FETCH_USUARIO_COUNT = NAMESPACE_USUARIO + "fetchUsuarioRowCount";

	/** The Constant STMT_FETCH_USUARIO_ALL_REQUEST. */
	private static final String STMT_FETCH_USUARIO_ALL_REQUEST = NAMESPACE_USUARIO + "fetchAllUsuariosRequest";

	/// ===================================### CONDOMINIO
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CONDOMINIO = "CondominioMap.";

	/** The Constant STMT_FETCH_CONDOMINIO. */
	private static final String STMT_FETCH_CONDOMINIO = NAMESPACE_CONDOMINIO + "fetchCondominioById";

	/** The Constant STMT_FETCH_CONDOMINIO_ALL. */
	private static final String STMT_FETCH_CONDOMINIO_ALL = NAMESPACE_CONDOMINIO + "fetchAllCondominios";

	/** The Constant STMT_FETCH_CONDOMINIO_COUNT. */
	private static final String STMT_FETCH_CONDOMINIO_COUNT = NAMESPACE_CONDOMINIO + "fetchCondominioRowCount";

	/** The Constant STMT_FETCH_CONDOMINIO_ALL_REQUEST. */
	private static final String STMT_FETCH_CONDOMINIO_ALL_REQUEST = NAMESPACE_CONDOMINIO + "fetchAllCondominiosRequest";

	/// ===================================### CLINICA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_CLINICA = "ClinicaMap.";

	/** The Constant STMT_FETCH_CLINICA. */
	private static final String STMT_FETCH_CLINICA = NAMESPACE_CLINICA + "fetchClinicaById";

	/** The Constant STMT_FETCH_CLINICA_ALL. */
	private static final String STMT_FETCH_CLINICA_ALL = NAMESPACE_CLINICA + "fetchAllClinicas";

	/** The Constant STMT_FETCH_CLINICA_COUNT. */
	private static final String STMT_FETCH_CLINICA_COUNT = NAMESPACE_CLINICA + "fetchClinicaRowCount";

	/** The Constant STMT_FETCH_CLINICA_ALL_REQUEST. */
	private static final String STMT_FETCH_CLINICA_ALL_REQUEST = NAMESPACE_CLINICA + "fetchAllClinicasRequest";

	/// ===================================### ADVOCACIA
	/// ####======================================
	/** The Constant NAMESPACE. */
	private static final String NAMESPACE_ADVOCACIA = "AdvocaciaMap.";

	/** The Constant STMT_FETCH_ADVOCACIA. */
	private static final String STMT_FETCH_ADVOCACIA = NAMESPACE_ADVOCACIA + "fetchAdvocaciaById";

	/** The Constant STMT_FETCH_ADVOCACIA_ALL. */
	private static final String STMT_FETCH_ADVOCACIA_ALL = NAMESPACE_ADVOCACIA + "fetchAllAdvocacias";

	/** The Constant STMT_FETCH_ADVOCACIA_COUNT. */
	private static final String STMT_FETCH_ADVOCACIA_COUNT = NAMESPACE_ADVOCACIA + "fetchAdvocaciaRowCount";

	/** The Constant STMT_FETCH_ADVOCACIA_ALL_REQUEST. */
	private static final String STMT_FETCH_ADVOCACIA_ALL_REQUEST = NAMESPACE_ADVOCACIA + "fetchAllAdvocaciasRequest";

	// ===================================### EMPRESA
	// ####======================================
	IEnderecoBAR enderecoBAR;

	IStatusBAR statusBAR;

	IHistoricoBAR historicoBAR;

	ICadastrosBAR cadastrosBAR;

	IFiscalBAR fiscalBAR;

	ITelefoneBAR telefoneBAR;

	IEmailBAR emailBAR;

	IDocumentoBAR documentoBAR;

	ISociosBAR sociosBAR;

	ISiteBAR siteBAR;

	IFinanceiroBAR financeiroBAR;

	INotesBAR notesBAR;

	IEmpresaBAR empresaBAR;

	IConfiguracaoBAR configuracaoBAR;

	public ISociosBAR getSociosBAR() {
		return sociosBAR;
	}

	public void setSociosBAR(ISociosBAR sociosBAR) {
		this.sociosBAR = sociosBAR;
	}

	public ISiteBAR getSiteBAR() {
		return siteBAR;
	}

	public void setSiteBAR(ISiteBAR siteBAR) {
		this.siteBAR = siteBAR;
	}

	public IFinanceiroBAR getFinanceiroBAR() {
		return financeiroBAR;
	}

	public void setFinanceiroBAR(IFinanceiroBAR financeiroBAR) {
		this.financeiroBAR = financeiroBAR;
	}

	public IDocumentoBAR getDocumentoBAR() {
		return documentoBAR;
	}

	public IHistoricoBAR getHistoricoBAR() {
		return historicoBAR;
	}

	public void setHistoricoBAR(IHistoricoBAR historicoBAR) {
		this.historicoBAR = historicoBAR;
	}

	public IStatusBAR getStatusBAR() {
		return statusBAR;
	}

	public void setStatusBAR(IStatusBAR statusBAR) {
		this.statusBAR = statusBAR;
	}

	public IEnderecoBAR getEnderecoBAR() {
		return enderecoBAR;
	}

	// @Resource
	public void setEnderecoBAR(IEnderecoBAR enderecoBAR) {
		this.enderecoBAR = enderecoBAR;
	}

	public ICadastrosBAR getCadastrosBAR() {
		return cadastrosBAR;
	}

	public void setCadastrosBAR(ICadastrosBAR cadastrosBAR) {
		this.cadastrosBAR = cadastrosBAR;
	}

	public IFiscalBAR getFiscalBAR() {
		return fiscalBAR;
	}

	public void setFiscalBAR(IFiscalBAR fiscalBAR) {
		this.fiscalBAR = fiscalBAR;
	}

	public ITelefoneBAR getTelefoneBAR() {
		return telefoneBAR;
	}

	public void setTelefoneBAR(ITelefoneBAR telefoneBAR) {
		this.telefoneBAR = telefoneBAR;
	}

	public IEmailBAR getEmailBAR() {
		return emailBAR;
	}

	public void setEmailBAR(IEmailBAR emailBAR) {
		this.emailBAR = emailBAR;
	}

	public IDocumentoBAR getDocumentosBAR() {
		return documentoBAR;
	}

	public void setDocumentoBAR(IDocumentoBAR documentoBAR) {
		this.documentoBAR = documentoBAR;
	}

	public INotesBAR getNotesBAR() {
		return notesBAR;
	}

	public void setNotesBAR(INotesBAR notesBAR) {
		this.notesBAR = notesBAR;
	}

	public IEmpresaBAR getEmpresaBAR() {
		return empresaBAR;
	}

	public void setEmpresaBAR(IEmpresaBAR empresaBAR) {
		this.empresaBAR = empresaBAR;
	}

	public IConfiguracaoBAR getConfiguracaoBAR() {
		return configuracaoBAR;
	}

	public void setConfiguracaoBAR(IConfiguracaoBAR configuracaoBAR) {
		this.configuracaoBAR = configuracaoBAR;
	}

	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#insertEmpresa(com.qat.samples.sysmgmt.base.model.Empresa)
	 */
	@Override
	public InternalResponse insertEmpresa(Empresa empresa) {
		InternalResponse response = new InternalResponse();
		Integer historicoId = empresa.getTransactionId();
		Integer processId = empresa.getTransactionId();
		empresa.setProcessId(historicoId);
		Boolean count1;
		Integer a = 0;



		if (!ValidationUtil.isNull(empresa.getConfiguracao())) {
			 a = ConfiguracaoBARD.maintainConfiguracaoAssociations(empresa.getConfiguracao(), response,
					empresa.getId(), null, null, TabelaEnum.EMPRESA, getConfiguracaoBAR(), getStatusBAR(), getHistoricoBAR(),
					empresa.getId(), empresa.getCreateUser(), historicoId, historicoId);
		}else{
			empresa = BaseBARD.empresaConfigInicial(empresa);
			a = ConfiguracaoBARD.maintainConfiguracaoAssociations(empresa.getConfiguracao(), response,
					empresa.getId(), null, null, TabelaEnum.EMPRESA, getConfiguracaoBAR(), getStatusBAR(), getHistoricoBAR(),
					empresa.getId(), empresa.getCreateUser(), historicoId, historicoId);

		}
		if (!ValidationUtil.isNull(empresa.getPlanosServicos())) {
			a += PlanoByEmpresaBARD.maintainPlanoByEmpresaAssociations(empresa.getPlanosServicos(), response,
					empresa.getId(), null, null, TabelaEnum.EMPRESA, getSiteBAR(), getStatusBAR(), getHistoricoBAR(),
					empresa.getId(), empresa.getCreateUser(), historicoId, historicoId);
		}

		if(empresa.getModelAction() == PersistenceActionEnum.INSERT)
		{
			MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_EMPRESA, empresa, response);

			if (!ValidationUtil.isNullOrZero(empresa.getId()))
			{
				Status status = new Status();
				status.setStatus(CdStatusTypeEnum.ANALISANDO);
				List<Status> statusList = new ArrayList<Status>();
				statusList.add(status);
				count1 =
						StatusBARD.maintainStatusAssociations(statusList, response, empresa.getId(), null, AcaoEnum.INSERT,
								empresa.getCreateUser(), empresa.getId(), TabelaEnum.EMPRESA, statusBAR,
								historicoBAR, processId, historicoId);

			}
			a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.EMPRESA, AcaoEnum.INSERT, historicoId,
					getHistoricoBAR(), response, empresa.getId(), empresa.getUserId());
		}

		BaseBARD.maintainInsertBase(empresa, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());



		if (!ValidationUtil.isNullOrEmpty(empresa.getSocios())) {
			a += SociosBARD.maintainSocioAssociations(empresa.getSocios(), response, empresa.getId(), null, null,
					TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
					empresa.getCreateUser(), historicoId, historicoId, getDocumentosBAR());

		}

		if (!ValidationUtil.isNullOrEmpty(empresa.getUsuarios())) {
			a += UsuarioBARD.maintainUsuarioAssociations(empresa.getUsuarios(), response, empresa.getId(), null, null,
					TabelaEnum.EMPRESA, getEmpresaBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
					empresa.getCreateUser(), historicoId, historicoId);
			UserRoles userRoles = new UserRoles();
			ArrayList<UserRoles> userRoleList = new ArrayList<UserRoles>();
			for (Usuario usuario : empresa.getUsuarios()) {
				userRoles = new UserRoles();
				if (!ValidationUtil.isNull(empresa.getTipo())){
				switch (empresa.getTipo()) {
				case 5: // comercio
					userRoles.setRole(new Role(12));
					break;
				case 4:// clinica
					userRoles.setRole(new Role(6));
					break;
				case 2:// condominio
					userRoles.setRole(new Role(5));
					break;
				case 1: // adbocacia
					userRoles.setRole(new Role(8));
					break;
				case 6:// prest Servico
					userRoles.setRole(new Role(13));
					break;
				case 3: // Parceiro
					userRoles.setRole(new Role(7));
					break;
				default:
					System.out.println("Este não é um dia válido!");

				}
				}
				userRoles.setUsername(usuario.getEmail());
				userRoles.setModelAction(PersistenceActionEnum.INSERT);
				userRoleList.add(userRoles);

			}

			UserRoleBARD.maintainUserRolesAssociations(userRoleList, response, empresa.getId(), null, null,
					TabelaEnum.EMPRESA, getEmpresaBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
					empresa.getCreateUser(), historicoId, historicoId);


		}

		EnviarEmailBARD.sendMailTLS(empresa.getEmprId(), getEmpresaBAR(), empresa);

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#updateEmpresa(com.qat.
	 * samples.sysmgmt.base.model.Empresa)
	 */
	@Override
	public InternalResponse updateEmpresa(Empresa empresa) {
		InternalResponse response = new InternalResponse();
		Integer a = 0;

//		BaseBARD.maintainDeleteBase(empresa, empresa.getTransactionId(), empresa.getTransactionId(), TabelaEnum.EMPRESA, getEnderecoBAR(),
//				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
//				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		BaseBARD.maintainInsertBase(empresa, empresa.getTransactionId(), empresa.getTransactionId(), TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		if (!ValidationUtil.isNull(empresa.getPlanosServicos())) {
			a += PlanoByEmpresaBARD.maintainPlanoByEmpresaAssociations(empresa.getPlanosServicos(), response,
					empresa.getId(), null, null, TabelaEnum.EMPRESA, getSiteBAR(), getStatusBAR(), getHistoricoBAR(),
					empresa.getId(), empresa.getCreateUser(), empresa.getTransactionId(), empresa.getTransactionId());
		}

		if (!ValidationUtil.isNull(empresa.getConfiguracao())) {
			 a = ConfiguracaoBARD.maintainConfiguracaoAssociations(empresa.getConfiguracao(), response,
					empresa.getId(), null, null, TabelaEnum.EMPRESA, getConfiguracaoBAR(), getStatusBAR(), getHistoricoBAR(),
					empresa.getId(), empresa.getCreateUser(), empresa.getTransactionId(), empresa.getTransactionId());
		}
		if(empresa.getModelAction() == PersistenceActionEnum.UPDATE)
		{
			MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_EMPRESA, empresa, response);

			InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		}

		if (!ValidationUtil.isNullOrEmpty(empresa.getSocios())) {
			a += SociosBARD.maintainSocioAssociations(empresa.getSocios(), response, empresa.getId(), null, null,
					TabelaEnum.EMPRESA, getSociosBAR(), getStatusBAR(), getHistoricoBAR(), empresa.getId(),
					empresa.getCreateUser(), empresa.getTransactionId(), empresa.getTransactionId(), getDocumentosBAR());

		}



		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#deleteEmpresa(com.qat.
	 * samples.sysmgmt.base.model.Empresa)
	 */
	@Override
	public InternalResponse deleteEmpresaById(Empresa empresa) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, empresa, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		BaseBARD.maintainInsertBase(empresa, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		if (!ValidationUtil.isNull(empresa.getConfiguracao())) {
			Integer a = ConfiguracaoBARD.maintainConfiguracaoAssociations(empresa.getConfiguracao(), response,
					empresa.getId(), null, null, TabelaEnum.EMPRESA, getConfiguracaoBAR(), getStatusBAR(), getHistoricoBAR(),
					empresa.getId(), empresa.getCreateUser(), historicoId, historicoId);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#deleteAllEmpresas()
	 */
	@Override
	public InternalResponse deleteAllEmpresas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEmpresaBAR#fetchEmpresaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Empresa fetchEmpresaById(FetchByIdRequest request) {
		return (Empresa) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_EMPRESA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IEmpresaBAR#fetchAllEmpresasCache()
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa empresa) {
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_EMPRESA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IEmpresaBAR#fetchEmpresasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request) {
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		fetchEmpresasByRequest(getSqlSession(), request, STMT_FETCH_EMPRESA_COUNT, STMT_FETCH_EMPRESA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchEmpresasByRequest
	// ####======================================

	public static void fetchEmpresasByRequest(SqlSession sqlSession, EmpresaInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	@Override
	public InternalResultsResponse<Transaction> fetchTransactionById(TransactionInquiryRequest request) {

		InternalResponse response1 = new InternalResponse();

		MyBatisBARHelper.doInsert(getSqlSession(), "TransactionMap.insertTransaction", request.getTransaction(),
				response1);

		InternalResultsResponse<Transaction> response = new InternalResultsResponse<Transaction>();
		fetchTransactionByRequest(getSqlSession(), request, "TransactionMap.fetchTransactionRowCount",
				"TransactionMap.fetchAllTransactionsRequest", response);
		return response;
	}
	// ===================================### fetchEmpresasByRequest
	// ####======================================

	public static void fetchTransactionByRequest(SqlSession sqlSession, TransactionInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### FILIAL
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#insertFilial(com.qat.samples.sysmgmt.base.model.Filial)
	 */
	@Override
	public InternalResponse insertFilial(Filial filial) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, filial, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		BaseBARD.maintainInsertBase(filial, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IFilialBAR#updateFilial(com.qat.samples.
	 * sysmgmt.base.model.Filial)
	 */
	@Override
	public InternalResponse updateFilial(Filial filial) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, filial, response);

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		BaseBARD.maintainInsertBase(filial, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IFilialBAR#deleteFilial(com.qat.samples.
	 * sysmgmt.base.model.Filial)
	 */
	@Override
	public InternalResponse deleteFilialById(Filial filial) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = filial.getProcessId();

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, filial, response);

		BaseBARD.maintainInsertBase(filial, historicoId, historicoId, TabelaEnum.FILIAL, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#deleteAllFilials()
	 */
	@Override
	public InternalResponse deleteAllFilials() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IFilialBAR#fetchFilialById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Filial fetchFilialById(FetchByIdRequest request) {
		return (Filial) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FILIAL, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFilialBAR#fetchAllFilialsCache()
	 */
	@Override
	public InternalResultsResponse<Filial> fetchAllFilials(Filial filial) {
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FILIAL_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IFilialBAR#fetchFilialsByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request) {
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();
		fetchFilialsByRequest(getSqlSession(), request, STMT_FETCH_FILIAL_COUNT, STMT_FETCH_FILIAL_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchFilialsByRequest
	// ####======================================

	public static void fetchFilialsByRequest(SqlSession sqlSession, FilialInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### DEPOSITO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#insertDeposito(com.qat.samples.sysmgmt.base.model.Deposito)
	 */
	@Override
	public InternalResponse insertDeposito(Deposito deposito) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, deposito, response);

		BaseBARD.maintainInsertBase(deposito, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDepositoBAR#updateDeposito(com.qat.
	 * samples.sysmgmt.base.model.Deposito)
	 */
	@Override
	public InternalResponse updateDeposito(Deposito deposito) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;

		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, deposito, response);

		BaseBARD.maintainInsertBase(deposito, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDepositoBAR#deleteDeposito(com.qat.
	 * samples.sysmgmt.base.model.Deposito)
	 */
	@Override
	public InternalResponse deleteDepositoById(Deposito deposito) {
		InternalResponse response = new InternalResponse();

		Integer historicoId = InsertHistBARD.maintainInsertHistorico(TabelaEnum.EMPRESA, getHistoricoBAR(), response);
		Integer processId = historicoId;
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, deposito, response);

		BaseBARD.maintainInsertBase(deposito, historicoId, processId, TabelaEnum.EMPRESA, getEnderecoBAR(),
				getStatusBAR(), getHistoricoBAR(), getCadastrosBAR(), getFiscalBAR(), getTelefoneBAR(), getEmailBAR(),
				getDocumentosBAR(), getNotesBAR(), new InternalResultsResponse<Empresa>());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IDepositoBAR#deleteAllDepositos()
	 */
	@Override
	public InternalResponse deleteAllDepositos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IDepositoBAR#fetchDepositoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Deposito fetchDepositoById(FetchByIdRequest request) {
		return (Deposito) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_DEPOSITO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IDepositoBAR#fetchAllDepositosCache()
	 */
	@Override
	public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito deposito) {
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_DEPOSITO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IDepositoBAR#fetchDepositosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request) {
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();
		fetchDepositosByRequest(getSqlSession(), request, STMT_FETCH_DEPOSITO_COUNT, STMT_FETCH_DEPOSITO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchDepositosByRequest
	// ####======================================

	public static void fetchDepositosByRequest(SqlSession sqlSession, DepositoInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### USUARIO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#insertUsuario(com.qat.samples.sysmgmt.base.model.Usuario)
	 */
	@Override
	public InternalResponse insertUsuario(Usuario usuario) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_USUARIO, usuario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#updateUsuario(com.qat.
	 * samples.sysmgmt.base.model.Usuario)
	 */
	@Override
	public InternalResponse updateUsuario(Usuario usuario) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_USUARIO, usuario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#deleteUsuario(com.qat.
	 * samples.sysmgmt.base.model.Usuario)
	 */
	@Override
	public InternalResponse deleteUsuarioById(Usuario usuario) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USUARIO, usuario, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#deleteAllUsuarios()
	 */
	@Override
	public InternalResponse deleteAllUsuarios() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USUARIO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUsuarioBAR#fetchUsuarioById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Usuario fetchUsuarioById(FetchByIdRequest request) {
		return (Usuario) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_USUARIO, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUsuarioBAR#fetchAllUsuariosCache()
	 */
	@Override
	public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario usuario) {
		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_USUARIO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUsuarioBAR#fetchUsuariosByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request) {
		InternalResultsResponse<Usuario> response = new InternalResultsResponse<Usuario>();
		fetchUsuariosByRequest(getSqlSession(), request, STMT_FETCH_USUARIO_COUNT, STMT_FETCH_USUARIO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchUsuariosByRequest
	// ####======================================

	public static void fetchUsuariosByRequest(SqlSession sqlSession, UsuarioInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CONDOMINIO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.ICondominioBAR#insertCondominio(com.qat.samples.sysmgmt.base.model.Condominio)
	 */
	@Override
	public InternalResponse insertCondominio(Condominio condominio) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, condominio, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#updateCondominio(com.qat.
	 * samples.sysmgmt.base.model.Condominio)
	 */
	@Override
	public InternalResponse updateCondominio(Condominio condominio) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, condominio, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#deleteCondominio(com.qat.
	 * samples.sysmgmt.base.model.Condominio)
	 */
	@Override
	public InternalResponse deleteCondominioById(Condominio condominio) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, condominio, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#deleteAllCondominios()
	 */
	@Override
	public InternalResponse deleteAllCondominios() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICondominioBAR#fetchCondominioById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Condominio fetchCondominioById(FetchByIdRequest request) {
		return (Condominio) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CONDOMINIO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.ICondominioBAR#fetchAllCondominiosCache(
	 * )
	 */
	@Override
	public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio condominio) {
		InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CONDOMINIO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.ICondominioBAR#fetchCondominiosByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request) {
		InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();
		fetchCondominiosByRequest(getSqlSession(), request, STMT_FETCH_CONDOMINIO_COUNT,
				STMT_FETCH_CONDOMINIO_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchCondominiosByRequest
	// ####======================================

	public static void fetchCondominiosByRequest(SqlSession sqlSession, CondominioInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### CLINICA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#insertClinica(com.qat.samples.sysmgmt.base.model.Clinica)
	 */
	@Override
	public InternalResponse insertClinica(Clinica clinicas) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, clinicas, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#updateClinica(com.qat.
	 * samples.sysmgmt.base.model.Clinica)
	 */
	@Override
	public InternalResponse updateClinica(Clinica clinica) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, clinica, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#deleteClinica(com.qat.
	 * samples.sysmgmt.base.model.Clinica)
	 */
	@Override
	public InternalResponse deleteClinicaById(Clinica clinica) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, clinica, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#deleteAllClinicas()
	 */
	@Override
	public InternalResponse deleteAllClinicas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IClinicaBAR#fetchClinicaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Clinica fetchClinicaById(FetchByIdRequest request) {
		return (Clinica) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_CLINICA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IClinicaBAR#fetchAllClinicasCache()
	 */
	@Override
	public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica clinica) {
		InternalResultsResponse<Clinica> response = new InternalResultsResponse<Clinica>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_CLINICA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IClinicaBAR#fetchClinicasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request) {
		InternalResultsResponse<Clinica> response = new InternalResultsResponse<Clinica>();
		fetchClinicasByRequest(getSqlSession(), request, STMT_FETCH_CLINICA_COUNT, STMT_FETCH_CLINICA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchClinicasByRequest
	// ####======================================

	public static void fetchClinicasByRequest(SqlSession sqlSession, ClinicaInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ADVOCACIA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.IDoisValorBAR.bar.IAdvocaciaBAR#insertAdvocacia(com.qat.samples.sysmgmt.base.model.Advocacia)
	 */
	@Override
	public InternalResponse insertAdvocacia(Advocacia advocacia) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ENTIDADE, advocacia, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#updateAdvocacia(com.qat.
	 * samples.sysmgmt.base.model.Advocacia)
	 */
	@Override
	public InternalResponse updateAdvocacia(Advocacia advocacia) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ENTIDADE, advocacia, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#deleteAdvocacia(com.qat.
	 * samples.sysmgmt.base.model.Advocacia)
	 */
	@Override
	public InternalResponse deleteAdvocaciaById(Advocacia advocacia) {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE, advocacia, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#deleteAllAdvocacias()
	 */
	@Override
	public InternalResponse deleteAllAdvocacias() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ENTIDADE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAdvocaciaBAR#fetchAdvocaciaById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Advocacia fetchAdvocaciaById(FetchByIdRequest request) {
		return (Advocacia) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ADVOCACIA,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAdvocaciaBAR#fetchAllAdvocaciasCache()
	 */
	@Override
	public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia advocacia) {
		InternalResultsResponse<Advocacia> response = new InternalResultsResponse<Advocacia>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ADVOCACIA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAdvocaciaBAR#fetchAdvocaciasByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request) {
		InternalResultsResponse<Advocacia> response = new InternalResultsResponse<Advocacia>();
		fetchAdvocaciasByRequest(getSqlSession(), request, STMT_FETCH_ADVOCACIA_COUNT, STMT_FETCH_ADVOCACIA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchAdvocaciasByRequest
	// ####======================================

	public static void fetchAdvocaciasByRequest(SqlSession sqlSession, AdvocaciaInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### USERROLES
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUserRolesBAR#insertUserRoles(com.qat.samples.sysmgmt.base.model.UserRoles)
	 */
	@Override
	public InternalResponse insertUserRoles(UserRoles userroles) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		userroles.setProcessId(userroles.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_USERROLES, userroles, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.USERROLES, AcaoEnum.INSERT,
				userroles.getTransactionId(), getHistoricoBAR(), response, userroles.getUser_role_id(),
				userroles.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IUserRolesBAR#updateUserRoles(com.qat.
	 * samples.sysmgmt.base.model.UserRoles)
	 */
	@Override
	public InternalResponse updateUserRoles(UserRoles userroles) {
		InternalResponse response = new InternalResponse();
		userroles.setProcessId(userroles.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_USERROLES, userroles, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.USERROLES, AcaoEnum.UPDATE,
				userroles.getTransactionId(), getHistoricoBAR(), response, userroles.getUser_role_id(),
				userroles.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IUserRolesBAR#deleteUserRoles(com.qat.
	 * samples.sysmgmt.base.model.UserRoles)
	 */
	@Override
	public InternalResponse deleteUserRolesById(UserRoles userroles) {
		InternalResponse response = new InternalResponse();
		userroles.setProcessId(userroles.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USERROLES, userroles, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.USERROLES, AcaoEnum.DELETE,
				userroles.getTransactionId(), getHistoricoBAR(), response, userroles.getUser_role_id(),
				userroles.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IUserRolesBAR#deleteAllUserRoless()
	 */
	@Override
	public InternalResponse deleteAllUserRoless() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_USERROLES_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUserRolesBAR#fetchUserRolesById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public UserRoles fetchUserRolesById(FetchByIdRequest request) {
		return (UserRoles) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_USERROLES,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IUserRolesBAR#fetchAllUserRolessCache()
	 */
	@Override
	public InternalResultsResponse<UserRoles> fetchAllUserRoless(UserRoles userroles) {
		InternalResultsResponse<UserRoles> response = new InternalResultsResponse<UserRoles>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_USERROLES_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IUserRolesBAR#fetchUserRolessByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<UserRoles> fetchUserRolessByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<UserRoles> response = new InternalResultsResponse<UserRoles>();
		fetchUserRolessByRequest(getSqlSession(), request, STMT_FETCH_USERROLES_COUNT, STMT_FETCH_USERROLES_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchUserRolessByRequest
	// ####======================================

	public static void fetchUserRolessByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### ROLE
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRoleBAR#insertRole(com.qat.samples.sysmgmt.base.model.Role)
	 */
	@Override
	public InternalResponse insertRole(Role role) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		role.setProcessId(role.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_ROLE, role, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ROLE, AcaoEnum.INSERT,
				role.getTransactionId(), getHistoricoBAR(), response, role.getId(), role.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IRoleBAR#updateRole(com.qat.samples.
	 * sysmgmt.base.model.Role)
	 */
	@Override
	public InternalResponse updateRole(Role role) {
		InternalResponse response = new InternalResponse();
		role.setProcessId(role.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_ROLE, role, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ROLE, AcaoEnum.UPDATE,
				role.getTransactionId(), getHistoricoBAR(), response, role.getId(), role.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IRoleBAR#deleteRole(com.qat.samples.
	 * sysmgmt.base.model.Role)
	 */
	@Override
	public InternalResponse deleteRoleById(Role role) {
		InternalResponse response = new InternalResponse();
		role.setProcessId(role.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ROLE, role, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.ROLE, AcaoEnum.DELETE,
				role.getTransactionId(), getHistoricoBAR(), response, role.getId(), role.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRoleBAR#deleteAllRoles()
	 */
	@Override
	public InternalResponse deleteAllRoles() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_ROLE_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IRoleBAR#fetchRoleById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Role fetchRoleById(FetchByIdRequest request) {
		return (Role) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_ROLE, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IRoleBAR#fetchAllRolesCache()
	 */
	@Override
	public InternalResultsResponse<Role> fetchAllRoles(Role role) {
		InternalResultsResponse<Role> response = new InternalResultsResponse<Role>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_ROLE_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IRoleBAR#fetchRolesByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Role> fetchRolesByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Role> response = new InternalResultsResponse<Role>();
		fetchRolesByRequest(getSqlSession(), request, STMT_FETCH_ROLE_COUNT, STMT_FETCH_ROLE_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchRolesByRequest
	// ####======================================

	public static void fetchRolesByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### PAGINA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPaginaBAR#insertPagina(com.qat.samples.sysmgmt.base.model.Pagina)
	 */
	@Override
	public InternalResponse insertPagina(Pagina pagina) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		pagina.setProcessId(pagina.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_PAGINA, pagina, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PAGINA, AcaoEnum.INSERT,
				pagina.getTransactionId(), getHistoricoBAR(), response, pagina.getId(), pagina.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPaginaBAR#updatePagina(com.qat.samples.
	 * sysmgmt.base.model.Pagina)
	 */
	@Override
	public InternalResponse updatePagina(Pagina pagina) {
		InternalResponse response = new InternalResponse();
		pagina.setProcessId(pagina.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_PAGINA, pagina, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PAGINA, AcaoEnum.UPDATE,
				pagina.getTransactionId(), getHistoricoBAR(), response, pagina.getId(), pagina.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IPaginaBAR#deletePagina(com.qat.samples.
	 * sysmgmt.base.model.Pagina)
	 */
	@Override
	public InternalResponse deletePaginaById(Pagina pagina) {
		InternalResponse response = new InternalResponse();
		pagina.setProcessId(pagina.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PAGINA, pagina, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.PAGINA, AcaoEnum.DELETE,
				pagina.getTransactionId(), getHistoricoBAR(), response, pagina.getId(), pagina.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPaginaBAR#deleteAllPaginas()
	 */
	@Override
	public InternalResponse deleteAllPaginas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_PAGINA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPaginaBAR#fetchPaginaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Pagina fetchPaginaById(FetchByIdRequest request) {
		return (Pagina) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_PAGINA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IPaginaBAR#fetchAllPaginasCache()
	 */
	@Override
	public InternalResultsResponse<Pagina> fetchAllPaginas(Pagina pagina) {
		InternalResultsResponse<Pagina> response = new InternalResultsResponse<Pagina>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_PAGINA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IPaginaBAR#fetchPaginasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Pagina> fetchPaginasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Pagina> response = new InternalResultsResponse<Pagina>();
		fetchPaginasByRequest(getSqlSession(), request, STMT_FETCH_PAGINA_COUNT, STMT_FETCH_PAGINA_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchPaginasByRequest
	// ####======================================

	public static void fetchPaginasByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### VALIDACAO
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IValidacaoBAR#insertValidacao(com.qat.samples.sysmgmt.base.model.Validacao)
	 */
	@Override
	public InternalResponse insertValidacao(Validacao validacao) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		validacao.setProcessId(validacao.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_VALIDACAO, validacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.VALIDACAO, AcaoEnum.INSERT,
				validacao.getTransactionId(), getHistoricoBAR(), response, validacao.getId(), validacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IValidacaoBAR#updateValidacao(com.qat.
	 * samples.sysmgmt.base.model.Validacao)
	 */
	@Override
	public InternalResponse updateValidacao(Validacao validacao) {
		InternalResponse response = new InternalResponse();
		validacao.setProcessId(validacao.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_VALIDACAO, validacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.VALIDACAO, AcaoEnum.UPDATE,
				validacao.getTransactionId(), getHistoricoBAR(), response, validacao.getId(), validacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IValidacaoBAR#deleteValidacao(com.qat.
	 * samples.sysmgmt.base.model.Validacao)
	 */
	@Override
	public InternalResponse deleteValidacaoById(Validacao validacao) {
		InternalResponse response = new InternalResponse();
		validacao.setProcessId(validacao.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_VALIDACAO, validacao, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.VALIDACAO, AcaoEnum.DELETE,
				validacao.getTransactionId(), getHistoricoBAR(), response, validacao.getId(), validacao.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IValidacaoBAR#deleteAllValidacaos()
	 */
	@Override
	public InternalResponse deleteAllValidacaos() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_VALIDACAO_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IValidacaoBAR#fetchValidacaoById(com.qat.
	 * samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Validacao fetchValidacaoById(FetchByIdRequest request) {
		return (Validacao) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_VALIDACAO,
				request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IValidacaoBAR#fetchAllValidacaosCache()
	 */
	@Override
	public InternalResultsResponse<Validacao> fetchAllValidacaos(Validacao validacao) {
		InternalResultsResponse<Validacao> response = new InternalResultsResponse<Validacao>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_VALIDACAO_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IValidacaoBAR#fetchValidacaosByRequest(com.
	 * qat.samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Validacao> fetchValidacaosByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Validacao> response = new InternalResultsResponse<Validacao>();
		fetchValidacaosByRequest(getSqlSession(), request, STMT_FETCH_VALIDACAO_COUNT, STMT_FETCH_VALIDACAO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchValidacaosByRequest
	// ####======================================

	public static void fetchValidacaosByRequest(SqlSession sqlSession, PagedInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### FIELD
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#insertField(com.qat.samples.sysmgmt.base.model.Field)
	 */
	@Override
	public InternalResponse insertField(Field field) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		field.setProcessId(field.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_FIELD, field, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.FIELD, AcaoEnum.INSERT,
				field.getTransactionId(), getHistoricoBAR(), response, field.getId(), field.getUserId());

		if (field.getId() != 0 && field.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, field.getId(), null, AcaoEnum.INSERT,
					field.getCreateUser(), field.getId(), TabelaEnum.FIELD, statusBAR, historicoBAR,
					field.getTransactionId(), field.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IFieldBAR#updateField(com.qat.samples.
	 * sysmgmt.base.model.Field)
	 */
	@Override
	public InternalResponse updateField(Field field) {
		InternalResponse response = new InternalResponse();
		field.setProcessId(field.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_FIELD, field, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.FIELD, AcaoEnum.UPDATE,
				field.getTransactionId(), getHistoricoBAR(), response, field.getId(), field.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IFieldBAR#deleteField(com.qat.samples.
	 * sysmgmt.base.model.Field)
	 */
	@Override
	public InternalResponse deleteFieldById(Field field) {
		InternalResponse response = new InternalResponse();
		field.setProcessId(field.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FIELD, field, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.FIELD, AcaoEnum.DELETE,
				field.getTransactionId(), getHistoricoBAR(), response, field.getId(), field.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#deleteAllFields()
	 */
	@Override
	public InternalResponse deleteAllFields() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_FIELD_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IFieldBAR#fetchFieldById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Field fetchFieldById(FetchByIdRequest request) {
		return (Field) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_FIELD, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IFieldBAR#fetchAllFieldsCache()
	 */
	@Override
	public InternalResultsResponse<Field> fetchAllFields(Field field) {
		InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_FIELD_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IFieldBAR#fetchFieldsByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Field> fetchFieldsByRequest(FieldInquiryRequest request) {
		InternalResultsResponse<Field> response = new InternalResultsResponse<Field>();
		fetchFieldsByRequest(getSqlSession(), request, STMT_FETCH_FIELD_COUNT, STMT_FETCH_FIELD_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchFieldsByRequest
	// ####======================================

	public static void fetchFieldsByRequest(SqlSession sqlSession, FieldInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### AJUDA
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IAjudaBAR#insertAjuda(com.qat.samples.sysmgmt.base.model.Ajuda)
	 */
	@Override
	public InternalResponse insertAjuda(Ajuda ajuda) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		ajuda.setProcessId(ajuda.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_AJUDA, ajuda, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.AJUDA, AcaoEnum.INSERT,
				ajuda.getTransactionId(), getHistoricoBAR(), response, ajuda.getId(), ajuda.getUserId());

		if (ajuda.getId() != 0 && ajuda.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, ajuda.getId(), null, AcaoEnum.INSERT,
					ajuda.getCreateUser(), ajuda.getId(), TabelaEnum.AJUDA, statusBAR, historicoBAR,
					ajuda.getTransactionId(), ajuda.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAjudaBAR#updateAjuda(com.qat.samples.
	 * sysmgmt.base.model.Ajuda)
	 */
	@Override
	public InternalResponse updateAjuda(Ajuda ajuda) {
		InternalResponse response = new InternalResponse();
		ajuda.setProcessId(ajuda.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_AJUDA, ajuda, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.AJUDA, AcaoEnum.UPDATE,
				ajuda.getTransactionId(), getHistoricoBAR(), response, ajuda.getId(), ajuda.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IAjudaBAR#deleteAjuda(com.qat.samples.
	 * sysmgmt.base.model.Ajuda)
	 */
	@Override
	public InternalResponse deleteAjudaById(Ajuda ajuda) {
		InternalResponse response = new InternalResponse();
		ajuda.setProcessId(ajuda.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_AJUDA, ajuda, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.AJUDA, AcaoEnum.DELETE,
				ajuda.getTransactionId(), getHistoricoBAR(), response, ajuda.getId(), ajuda.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IAjudaBAR#deleteAllAjudas()
	 */
	@Override
	public InternalResponse deleteAllAjudas() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_AJUDA_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IAjudaBAR#fetchAjudaById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Ajuda fetchAjudaById(FetchByIdRequest request) {
		return (Ajuda) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_AJUDA, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IAjudaBAR#fetchAllAjudasCache()
	 */
	@Override
	public InternalResultsResponse<Ajuda> fetchAllAjudas(Ajuda ajuda) {
		InternalResultsResponse<Ajuda> response = new InternalResultsResponse<Ajuda>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_AJUDA_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IAjudaBAR#fetchAjudasByRequest(com.qat.
	 * samples.sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Ajuda> fetchAjudasByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Ajuda> response = new InternalResultsResponse<Ajuda>();
		fetchAjudasByRequest(getSqlSession(), request, STMT_FETCH_AJUDA_COUNT, STMT_FETCH_AJUDA_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchAjudasByRequest
	// ####======================================

	public static void fetchAjudasByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	// ===================================### MENU
	// ####======================================
	/**
	 * /* (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMenuBAR#insertMenu(com.qat.samples.sysmgmt.base.model.Menu)
	 */
	@Override
	public InternalResponse insertMenu(Menu menu) {
		InternalResponse response = new InternalResponse();
		Integer count = 0;
		Boolean count1 = false;

		menu.setProcessId(menu.getTransactionId());

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MENU, menu, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MENU, AcaoEnum.INSERT,
				menu.getTransactionId(), getHistoricoBAR(), response, menu.getId(), menu.getUserId());

		if (menu.getId() != 0 && menu.getId() != null) {
			Status status = new Status();
			status.setStatus(CdStatusTypeEnum.ATIVO);
			List<Status> statusList = new ArrayList<Status>();
			statusList.add(status);
			count1 = StatusBARD.maintainStatusAssociations(statusList, response, menu.getId(), null, AcaoEnum.INSERT,
					menu.getCreateUser(), menu.getId(), TabelaEnum.MENU, statusBAR, historicoBAR,
					menu.getTransactionId(), menu.getTransactionId());

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMenuBAR#updateMenu(com.qat.samples.
	 * sysmgmt.base.model.Menu)
	 */
	@Override
	public InternalResponse updateMenu(Menu menu) {
		InternalResponse response = new InternalResponse();
		menu.setProcessId(menu.getTransactionId());
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MENU, menu, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MENU, AcaoEnum.UPDATE,
				menu.getTransactionId(), getHistoricoBAR(), response, menu.getId(), menu.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.base.bar.IMenuBAR#deleteMenu(com.qat.samples.
	 * sysmgmt.base.model.Menu)
	 */
	@Override
	public InternalResponse deleteMenuById(Menu menu) {
		InternalResponse response = new InternalResponse();
		menu.setProcessId(menu.getTransactionId());
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MENU, menu, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.MENU, AcaoEnum.DELETE,
				menu.getTransactionId(), getHistoricoBAR(), response, menu.getId(), menu.getUserId());

		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMenuBAR#deleteAllMenus()
	 */
	@Override
	public InternalResponse deleteAllMenus() {
		InternalResponse response = new InternalResponse();
		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MENU_ALL, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.bar.IMenuBAR#fetchMenuById(com.qat.samples.
	 * sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public Menu fetchMenuById(FetchByIdRequest request) {
		return (Menu) MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_MENU, request.getFetchId());

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.base.bar.IMenuBAR#fetchAllMenusCache()
	 */
	@Override
	public InternalResultsResponse<Menu> fetchAllMenus(Menu menu) {
		InternalResultsResponse<Menu> response = new InternalResultsResponse<Menu>();
		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_MENU_ALL));
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.qat.samples.sysmgmt.bar.IMenuBAR#fetchMenusByRequest(com.qat.samples.
	 * sysmgmt.model.request. PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Menu> fetchMenusByRequest(PagedInquiryRequest request) {
		InternalResultsResponse<Menu> response = new InternalResultsResponse<Menu>();
		fetchMenusByRequest(getSqlSession(), request, STMT_FETCH_MENU_COUNT, STMT_FETCH_MENU_ALL_REQUEST, response);
		return response;
	}

	// ===================================### fetchMenusByRequest
	// ####======================================

	public static void fetchMenusByRequest(SqlSession sqlSession, PagedInquiryRequest request, String countStatement,
			String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

	@Override
	public InternalResultsResponse<Endereco> fetchEnderecosByRequest(EmpresaInquiryRequest request) {
		InternalResultsResponse<Endereco> response = new InternalResultsResponse<Endereco>();
		fetchEnderecosByRequest(getSqlSession(), request, STMT_FETCH_ENDERECO_COUNT, STMT_FETCH_ENDERECO_ALL_REQUEST,
				response);
		return response;
	}

	// ===================================### fetchEmpresasByRequest
	// ####======================================

	public static void fetchEnderecosByRequest(SqlSession sqlSession, EmpresaInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}



	@Override
	public InternalResponse insertNote(Note note) {
		return getNotesBAR().insertNotes(note);
	}

	@Override
	public InternalResponse updateNote(Note note) {
		return getNotesBAR().updateNotes(note);
	}

	@Override
	public InternalResponse deleteNoteById(Note note) {
		return getNotesBAR().deleteNotesById(note);
	}

	@Override
	public InternalResponse deleteAllNotes() {
		return getNotesBAR().deleteAllNotess();
	}

	@Override
	public InternalResultsResponse<Note> fetchAllNotes(Note note) {
		return getNotesBAR().fetchAllNotess(note);
	}

	@Override
	public InternalResultsResponse<Note> fetchNotesByRequest(PagedInquiryRequest request) {
		return getNotesBAR().fetchNotessByRequest(request);
	}

	@Override
	public InternalResponse insertStatus(Status status) {
		return getStatusBAR().insertStatus(status);
	}

	@Override
	public InternalResponse updateStatus(Status status) {
		return getStatusBAR().updateStatus(status);
	}

	@Override
	public InternalResponse deleteStatusById(Status status) {
		return getStatusBAR().deleteStatusById(status);
	}

	@Override
	public InternalResponse deleteAllStatuss() {
		return getStatusBAR().deleteAllStatus();
	}

	@Override
	public InternalResultsResponse<Status> fetchAllStatuss(Status status) {
		return getStatusBAR().fetchAllStatus(status);
	}

	@Override
	public InternalResultsResponse<Status> fetchStatussByRequest(PagedInquiryRequest request) {
		return getStatusBAR().fetchStatusByRequest(request);
	}

	@Override
	public InternalResponse insertMessage(Message empresa) {
		InternalResponse response = new InternalResponse();
		Integer historicoId = empresa.getTransactionId();
		Integer processId = empresa.getTransactionId();
		empresa.setProcessId(historicoId);

		MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_MESSAGE, empresa, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.AJUDA, AcaoEnum.INSERT,
				empresa.getTransactionId(), getHistoricoBAR(), response, empresa.getId(), empresa.getUserId());

		return response;
	}

	@Override
	public InternalResponse updateMessage(Message empresa) {
		InternalResponse response = new InternalResponse();
		Integer historicoId = empresa.getTransactionId();
		Integer processId = empresa.getTransactionId();
		empresa.setProcessId(historicoId);
		MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_MESSAGE, empresa, response);
		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.AJUDA, AcaoEnum.UPDATE,
				empresa.getTransactionId(), getHistoricoBAR(), response, empresa.getId(), empresa.getUserId());

		return response;
	}

	@Override
	public InternalResponse deleteMessageById(Message empresa) {
		InternalResponse response = new InternalResponse();
		Integer historicoId = empresa.getTransactionId();
		Integer processId = empresa.getTransactionId();
		empresa.setProcessId(historicoId);

		MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_MESSAGE, response);

		Integer a = InsertHistBARD.maintainInsertHistoricoItens(TabelaEnum.AJUDA, AcaoEnum.DELETE,
				empresa.getTransactionId(), getHistoricoBAR(), response, empresa.getId(), empresa.getUserId());

		return response;
	}

	@Override
	public InternalResultsResponse<Message> fetchMessagesByRequest(MessageInquiryRequest request) {
		InternalResultsResponse<Message> response = new InternalResultsResponse<Message>();
		fetchMessagesByRequest(getSqlSession(), request, STMT_FETCH_MESSAGE_COUNT, STMT_FETCH_MESSAGE_ALL_REQUEST, response);
		return response;
	}

	public static void fetchMessagesByRequest(SqlSession sqlSession, MessageInquiryRequest request,
			String countStatement, String fetchPagedStatement, InternalResultsResponse<?> response) {

		// If the user requested the total rows/record count
		if (request.isPreQueryCount()) {
			// set the total rows available in the response
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer) MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));

			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO) {
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

		// calculate correct startPage for more rows available comparison, since
		// it is zero based, we have to offset by
		// 1.
		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);

		// set moreRowsAvailable in response based on total rows compared to
		// (page size * start page)
		// remember if the count was not requested the TotalRowsAvailable will
		// be false because the assumption
		// is that you your own logic to handle this.
		if (response.getResultsSetInfo()
				.getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage)) {
			response.getResultsSetInfo().setMoreRowsAvailable(true);
		}

	}

}
