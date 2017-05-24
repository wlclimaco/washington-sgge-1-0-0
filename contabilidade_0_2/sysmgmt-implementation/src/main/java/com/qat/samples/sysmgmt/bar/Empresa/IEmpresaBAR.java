/** create by system gera-java version 1.0.0 01/05/2016 18:42 : 57*/
package com.qat.samples.sysmgmt.bar.Empresa;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
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
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

/**
 * The Interface EmpresaBAR.. (Data Access Component - DAC)
 */
public interface IEmpresaBAR
{

	/**
	 * Fetch empresa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Empresa fetchEmpresaById(FetchByIdRequest request);


	/**
* Insert empresa.
*
* @param empresa the empresa
*
* @return the internal response
*/
	public InternalResponse insertEmpresa(Empresa empresa);


	public InternalResponse insertMessage(Message empresa);

	/**
* Update empresa.
*
* @param empresa the empresa
*
* @return the internal response
*/
	public InternalResponse updateEmpresa(Empresa empresa);

	public InternalResponse updateMessage(Message empresa);

	/**
* Delete empresa.
*
* @param empresa the empresa
*
* @return the internal response
*/
	public InternalResponse deleteEmpresaById(Empresa empresa);

	public InternalResponse deleteMessageById(Message empresa);

	/**
* Delete all empresas.
*
* @return the internal response
*/
	public InternalResponse deleteAllEmpresas();

	/**
* Fetch all empresas.
*
* @return the list< empresa>
*/
	public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa  empresa);

	/**
* Fetch empresas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request);

	public InternalResultsResponse<Message> fetchMessagesByRequest(MessageInquiryRequest request);

	/**
	 * Fetch filial by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Filial fetchFilialById(FetchByIdRequest request);

	/**
* Insert filial.
*
* @param filial the filial
*
* @return the internal response
*/
	public InternalResponse insertFilial(Filial filial);

	/**
* Update filial.
*
* @param filial the filial
*
* @return the internal response
*/
	public InternalResponse updateFilial(Filial filial);

	/**
* Delete filial.
*
* @param filial the filial
*
* @return the internal response
*/
	public InternalResponse deleteFilialById(Filial filial);

	/**
* Delete all filials.
*
* @return the internal response
*/
	public InternalResponse deleteAllFilials();

	/**
* Fetch all filials.
*
* @return the list< filial>
*/
	public InternalResultsResponse<Filial> fetchAllFilials(Filial  filial);

	/**
* Fetch filials by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request);

	/**
	 * Fetch deposito by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Deposito fetchDepositoById(FetchByIdRequest request);

	/**
* Insert deposito.
*
* @param deposito the deposito
*
* @return the internal response
*/
	public InternalResponse insertDeposito(Deposito deposito);

	/**
* Update deposito.
*
* @param deposito the deposito
*
* @return the internal response
*/
	public InternalResponse updateDeposito(Deposito deposito);

	/**
* Delete deposito.
*
* @param deposito the deposito
*
* @return the internal response
*/
	public InternalResponse deleteDepositoById(Deposito deposito);

	/**
* Delete all depositos.
*
* @return the internal response
*/
	public InternalResponse deleteAllDepositos();

	/**
* Fetch all depositos.
*
* @return the list< deposito>
*/
	public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito  deposito);

	/**
* Fetch depositos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request);

	/**
	 * Fetch usuario by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Usuario fetchUsuarioById(FetchByIdRequest request);

	/**
* Insert usuario.
*
* @param usuario the usuario
*
* @return the internal response
*/
	public InternalResponse insertUsuario(Usuario usuario);

	/**
* Update usuario.
*
* @param usuario the usuario
*
* @return the internal response
*/
	public InternalResponse updateUsuario(Usuario usuario);

	/**
* Delete usuario.
*
* @param usuario the usuario
*
* @return the internal response
*/
	public InternalResponse deleteUsuarioById(Usuario usuario);

	/**
* Delete all usuarios.
*
* @return the internal response
*/
	public InternalResponse deleteAllUsuarios();

	/**
* Fetch all usuarios.
*
* @return the list< usuario>
*/
	public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario  usuario);

	/**
* Fetch usuarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request);

	/**
	 * Fetch condominio by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Condominio fetchCondominioById(FetchByIdRequest request);

	/**
* Insert condominio.
*
* @param condominio the condominio
*
* @return the internal response
*/
	public InternalResponse insertCondominio(Condominio condominio);

	/**
* Update condominio.
*
* @param condominio the condominio
*
* @return the internal response
*/
	public InternalResponse updateCondominio(Condominio condominio);

	/**
* Delete condominio.
*
* @param condominio the condominio
*
* @return the internal response
*/
	public InternalResponse deleteCondominioById(Condominio condominio);

	/**
* Delete all condominios.
*
* @return the internal response
*/
	public InternalResponse deleteAllCondominios();

	/**
* Fetch all condominios.
*
* @return the list< condominio>
*/
	public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio  condominio);

	/**
* Fetch condominios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request);

	/**
	 * Fetch clinica by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Clinica fetchClinicaById(FetchByIdRequest request);

	/**
* Insert clinica.
*
* @param clinica the clinica
*
* @return the internal response
*/
	public InternalResponse insertClinica(Clinica clinica);

	/**
* Update clinica.
*
* @param clinica the clinica
*
* @return the internal response
*/
	public InternalResponse updateClinica(Clinica clinica);

	/**
* Delete clinica.
*
* @param clinica the clinica
*
* @return the internal response
*/
	public InternalResponse deleteClinicaById(Clinica clinica);

	/**
* Delete all clinicas.
*
* @return the internal response
*/
	public InternalResponse deleteAllClinicas();

	/**
* Fetch all clinicas.
*
* @return the list< clinica>
*/
	public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica  clinica);

	/**
* Fetch clinicas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request);

	/**
	 * Fetch advocacia by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Advocacia fetchAdvocaciaById(FetchByIdRequest request);

	/**
* Insert advocacia.
*
* @param advocacia the advocacia
*
* @return the internal response
*/
	public InternalResponse insertAdvocacia(Advocacia advocacia);

	/**
* Update advocacia.
*
* @param advocacia the advocacia
*
* @return the internal response
*/
	public InternalResponse updateAdvocacia(Advocacia advocacia);

	/**
* Delete advocacia.
*
* @param advocacia the advocacia
*
* @return the internal response
*/
	public InternalResponse deleteAdvocaciaById(Advocacia advocacia);

	/**
* Delete all advocacias.
*
* @return the internal response
*/
	public InternalResponse deleteAllAdvocacias();

	/**
* Fetch all advocacias.
*
* @return the list< advocacia>
*/
	public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia  advocacia);

	/**
* Fetch advocacias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request);

	public InternalResultsResponse<Transaction> fetchTransactionById(TransactionInquiryRequest request);


	/**
	 * Fetch userroles by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public UserRoles fetchUserRolesById(FetchByIdRequest request);

	/**
* Insert userroles.
*
* @param userroles the userroles
*
* @return the internal response
*/
	public InternalResponse insertUserRoles(UserRoles userroles);

	/**
* Update userroles.
*
* @param userroles the userroles
*
* @return the internal response
*/
	public InternalResponse updateUserRoles(UserRoles userroles);

	/**
* Delete userroles.
*
* @param userroles the userroles
*
* @return the internal response
*/
	public InternalResponse deleteUserRolesById(UserRoles userroles);

	/**
* Delete all userroless.
*
* @return the internal response
*/
	public InternalResponse deleteAllUserRoless();

	/**
* Fetch all userroless.
*
* @return the list< userroles>
*/
	public InternalResultsResponse<UserRoles> fetchAllUserRoless(UserRoles  userroles);

	/**
* Fetch userroless by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<UserRoles> fetchUserRolessByRequest(PagedInquiryRequest request);

	/**
	 * Fetch role by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Role fetchRoleById(FetchByIdRequest request);

	/**
* Insert role.
*
* @param role the role
*
* @return the internal response
*/
	public InternalResponse insertRole(Role role);

	/**
* Update role.
*
* @param role the role
*
* @return the internal response
*/
	public InternalResponse updateRole(Role role);

	/**
* Delete role.
*
* @param role the role
*
* @return the internal response
*/
	public InternalResponse deleteRoleById(Role role);

	/**
* Delete all roles.
*
* @return the internal response
*/
	public InternalResponse deleteAllRoles();

	/**
* Fetch all roles.
*
* @return the list< role>
*/
	public InternalResultsResponse<Role> fetchAllRoles(Role  role);

	/**
* Fetch roles by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Role> fetchRolesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch pagina by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Pagina fetchPaginaById(FetchByIdRequest request);

	/**
* Insert pagina.
*
* @param pagina the pagina
*
* @return the internal response
*/
	public InternalResponse insertPagina(Pagina pagina);

	/**
* Update pagina.
*
* @param pagina the pagina
*
* @return the internal response
*/
	public InternalResponse updatePagina(Pagina pagina);

	/**
* Delete pagina.
*
* @param pagina the pagina
*
* @return the internal response
*/
	public InternalResponse deletePaginaById(Pagina pagina);

	/**
* Delete all paginas.
*
* @return the internal response
*/
	public InternalResponse deleteAllPaginas();

	/**
* Fetch all paginas.
*
* @return the list< pagina>
*/
	public InternalResultsResponse<Pagina> fetchAllPaginas(Pagina  pagina);

	/**
* Fetch paginas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Pagina> fetchPaginasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch validacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Validacao fetchValidacaoById(FetchByIdRequest request);

	/**
* Insert validacao.
*
* @param validacao the validacao
*
* @return the internal response
*/
	public InternalResponse insertValidacao(Validacao validacao);

	/**
* Update validacao.
*
* @param validacao the validacao
*
* @return the internal response
*/
	public InternalResponse updateValidacao(Validacao validacao);

	/**
* Delete validacao.
*
* @param validacao the validacao
*
* @return the internal response
*/
	public InternalResponse deleteValidacaoById(Validacao validacao);

	/**
* Delete all validacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllValidacaos();

	/**
* Fetch all validacaos.
*
* @return the list< validacao>
*/
	public InternalResultsResponse<Validacao> fetchAllValidacaos(Validacao  validacao);

	/**
* Fetch validacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Validacao> fetchValidacaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch field by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Field fetchFieldById(FetchByIdRequest request);

	/**
* Insert field.
*
* @param field the field
*
* @return the internal response
*/
	public InternalResponse insertField(Field field);

	/**
* Update field.
*
* @param field the field
*
* @return the internal response
*/
	public InternalResponse updateField(Field field);

	/**
* Delete field.
*
* @param field the field
*
* @return the internal response
*/
	public InternalResponse deleteFieldById(Field field);

	/**
* Delete all fields.
*
* @return the internal response
*/
	public InternalResponse deleteAllFields();

	/**
* Fetch all fields.
*
* @return the list< field>
*/
	public InternalResultsResponse<Field> fetchAllFields(Field  field);

	/**
* Fetch fields by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Field> fetchFieldsByRequest(FieldInquiryRequest request);

	/**
	 * Fetch ajuda by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Ajuda fetchAjudaById(FetchByIdRequest request);

	/**
* Insert ajuda.
*
* @param ajuda the ajuda
*
* @return the internal response
*/
	public InternalResponse insertAjuda(Ajuda ajuda);

	/**
* Update ajuda.
*
* @param ajuda the ajuda
*
* @return the internal response
*/
	public InternalResponse updateAjuda(Ajuda ajuda);

	/**
* Delete ajuda.
*
* @param ajuda the ajuda
*
* @return the internal response
*/
	public InternalResponse deleteAjudaById(Ajuda ajuda);

	/**
* Delete all ajudas.
*
* @return the internal response
*/
	public InternalResponse deleteAllAjudas();

	/**
* Fetch all ajudas.
*
* @return the list< ajuda>
*/
	public InternalResultsResponse<Ajuda> fetchAllAjudas(Ajuda  ajuda);

	/**
* Fetch ajudas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Ajuda> fetchAjudasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch menu by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Menu fetchMenuById(FetchByIdRequest request);

	/**
* Insert menu.
*
* @param menu the menu
*
* @return the internal response
*/
	public InternalResponse insertMenu(Menu menu);

	/**
* Update menu.
*
* @param menu the menu
*
* @return the internal response
*/
	public InternalResponse updateMenu(Menu menu);

	/**
* Delete menu.
*
* @param menu the menu
*
* @return the internal response
*/
	public InternalResponse deleteMenuById(Menu menu);

	/**
* Delete all menus.
*
* @return the internal response
*/
	public InternalResponse deleteAllMenus();

	/**
* Fetch all menus.
*
* @return the list< menu>
*/
	public InternalResultsResponse<Menu> fetchAllMenus(Menu  menu);

	/**
* Fetch menus by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Menu> fetchMenusByRequest(PagedInquiryRequest request);


	public InternalResultsResponse<Endereco> fetchEnderecosByRequest(EmpresaInquiryRequest request);



	/**
* Insert Note.
*
* @param Note the empresa
*
* @return the internal response
*/
	public InternalResponse insertNote(Note note);

	/**
* Update note.
*
notem note the note
*
* @return the internal response
*/
	public InternalResponse updateNote(Note note);

	/**
* Delete note.
*
* @param note the note
*
* @return the internal response
*/
	public InternalResponse deleteNoteById(Note note);

	/**
* Delete all notes.
*
* @return the internal response
*/
	public InternalResponse deleteAllNotes();

	/**
* Fetch all notes.
*
* @return the list< note>
*/
	public InternalResultsResponse<Note> fetchAllNotes(Note  note);

	/**
* Fetch notes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Note> fetchNotesByRequest(PagedInquiryRequest request);


	/**
* Insert note.
*
* @param status the status
*
* @return the internal response
*/
	public InternalResponse insertStatus(Status status);

	/**
* Update status.
*
* @param status the status
*
* @return the internal response
*/
	public InternalResponse updateStatus(Status status);

	/**
* Delete status.
*
* @param status the status
*
* @return the internal response
*/
	public InternalResponse deleteStatusById(Status status);

	/**
* Delete all statuss.
*
* @return the internal response
*/
	public InternalResponse deleteAllStatuss();

	/**
* Fetch all statuss.
*
* @return the list< status>
*/
	public InternalResultsResponse<Status> fetchAllStatuss(Status  status);

	/**
* Fetch statuss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Status> fetchStatussByRequest(PagedInquiryRequest request);

}
