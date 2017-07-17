/** create by system gera-java version 1.0.0 04/06/2016 19:30 : 57*/
package com.qat.samples.sysmgmt.bac.Empresa;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
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
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.MessageInquiryRequest;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.AjudaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.DoisValoresMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.MenuMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.MessageMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.NoteMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.PaginaMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.RoleMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.StatusMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.UserRolesMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.ValidacaoMaintenanceRequest;

/**
 * The Interface IEmpresaBAC. (Business Area Component - BAC)
 */
public interface IEmpresaBAC
{



//===================================### EMPRESA ####======================================
	/**

	/**
	 * Insert empresa.
	 *
* @param request the empresa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request);


	/**
* Update empresa.
*
* @param request the empresa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request);

	/**
* Delete empresa.
*
* @param request the empresa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> deleteEmpresa(EmpresaMaintenanceRequest request);

	/**
* Refresh empresas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Empresa> refreshEmpresas(RefreshRequest request);

	/**
* Fetch empresa by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	/**
* Fetch all empresas.
*
* @return the internal results response< empresa>
*/
	public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa  empresa);

	/**
* Fetch empresas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request);


//===================================### FILIAL ####======================================
	/**

	/**
	 * Insert filial.
	 *
* @param request the filial maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Filial> insertFilial(FilialMaintenanceRequest request);

	/**
* Update filial.
*
* @param request the filial maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Filial> updateFilial(FilialMaintenanceRequest request);

	/**
* Delete filial.
*
* @param request the filial maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Filial> deleteFilial(FilialMaintenanceRequest request);

	/**
* Refresh filials.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Filial> refreshFilials(RefreshRequest request);

	/**
* Fetch filial by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

	/**
* Fetch all filials.
*
* @return the internal results response< filial>
*/
	public InternalResultsResponse<Filial> fetchAllFilials(Filial  filial);

	/**
* Fetch filials by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request);


//===================================### DEPOSITO ####======================================
	/**

	/**
	 * Insert deposito.
	 *
* @param request the deposito maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> insertDeposito(DepositoMaintenanceRequest request);

	/**
* Update deposito.
*
* @param request the deposito maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> updateDeposito(DepositoMaintenanceRequest request);

	/**
* Delete deposito.
*
* @param request the deposito maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> deleteDeposito(DepositoMaintenanceRequest request);

	/**
* Refresh depositos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Deposito> refreshDepositos(RefreshRequest request);

	/**
* Fetch deposito by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

	/**
* Fetch all depositos.
*
* @return the internal results response< deposito>
*/
	public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito  deposito);

	/**
* Fetch depositos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request);


//===================================### USUARIO ####======================================
	/**

	/**
	 * Insert usuario.
	 *
* @param request the usuario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> insertUsuario(UsuarioMaintenanceRequest request);

	/**
* Update usuario.
*
* @param request the usuario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> updateUsuario(UsuarioMaintenanceRequest request);

	/**
* Delete usuario.
*
* @param request the usuario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> deleteUsuario(UsuarioMaintenanceRequest request);

	/**
* Refresh usuarios.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Usuario> refreshUsuarios(RefreshRequest request);

	/**
* Fetch usuario by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> fetchUsuarioById(FetchByIdRequest request);

	/**
* Fetch all usuarios.
*
* @return the internal results response< usuario>
*/
	public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario  usuario);

	/**
* Fetch usuarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request);


//===================================### ADVOCACIA ####======================================
	/**

	/**
	 * Insert advocacia.
	 *
* @param request the advocacia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> insertAdvocacia(AdvocaciaMaintenanceRequest request);

	/**
* Update advocacia.
*
* @param request the advocacia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> updateAdvocacia(AdvocaciaMaintenanceRequest request);

	/**
* Delete advocacia.
*
* @param request the advocacia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> deleteAdvocacia(AdvocaciaMaintenanceRequest request);

	/**
* Refresh advocacias.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Advocacia> refreshAdvocacias(RefreshRequest request);

	/**
* Fetch advocacia by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> fetchAdvocaciaById(FetchByIdRequest request);

	/**
* Fetch all advocacias.
*
* @return the internal results response< advocacia>
*/
	public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia  advocacia);

	/**
* Fetch advocacias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request);


//===================================### CLINICA ####======================================
	/**

	/**
	 * Insert clinica.
	 *
* @param request the clinica maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> insertClinica(ClinicaMaintenanceRequest request);

	/**
* Update clinica.
*
* @param request the clinica maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> updateClinica(ClinicaMaintenanceRequest request);

	/**
* Delete clinica.
*
* @param request the clinica maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> deleteClinica(ClinicaMaintenanceRequest request);

	/**
* Refresh clinicas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Clinica> refreshClinicas(RefreshRequest request);

	/**
* Fetch clinica by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> fetchClinicaById(FetchByIdRequest request);

	/**
* Fetch all clinicas.
*
* @return the internal results response< clinica>
*/
	public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica  clinica);

	/**
* Fetch clinicas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request);


//===================================### CONDOMINIO ####======================================
	/**

	/**
	 * Insert condominio.
	 *
* @param request the condominio maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> insertCondominio(CondominioMaintenanceRequest request);

	/**
* Update condominio.
*
* @param request the condominio maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> updateCondominio(CondominioMaintenanceRequest request);

	/**
* Delete condominio.
*
* @param request the condominio maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> deleteCondominio(CondominioMaintenanceRequest request);

	/**
* Refresh condominios.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Condominio> refreshCondominios(RefreshRequest request);

	/**
* Fetch condominio by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> fetchCondominioById(FetchByIdRequest request);

	/**
* Fetch all condominios.
*
* @return the internal results response< condominio>
*/
	public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio  condominio);

	/**
* Fetch condominios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request);


	public InternalResultsResponse<Transaction> fetchTransactionById(TransactionInquiryRequest request);


	//===================================### USERROLES ####======================================
		/**

		/**
		 * Insert userroles.
		 *
	* @param request the userroles maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<UserRoles> insertUserRoles(UserRolesMaintenanceRequest request);

		/**
	* Update userroles.
	*
	* @param request the userroles maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<UserRoles> updateUserRoles(UserRolesMaintenanceRequest request);

		/**
	* Delete userroles.
	*
	* @param request the userroles maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<UserRoles> deleteUserRoles(UserRolesMaintenanceRequest request);

		/**
	* Refresh userroless.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<UserRoles> refreshUserRoless(RefreshRequest request);

		/**
	* Fetch userroles by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<UserRoles> fetchUserRolesById(FetchByIdRequest request);

		/**
	* Fetch all userroless.
	*
	* @return the internal results response< userroles>
	*/
		public InternalResultsResponse<UserRoles> fetchAllUserRoless(UserRoles  userroles);

		/**
	* Fetch userroless by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<UserRoles> fetchUserRolessByRequest(PagedInquiryRequest request);


	//===================================### ROLE ####======================================
		/**

		/**
		 * Insert role.
		 *
	* @param request the role maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Role> insertRole(RoleMaintenanceRequest request);

		/**
	* Update role.
	*
	* @param request the role maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Role> updateRole(RoleMaintenanceRequest request);

		/**
	* Delete role.
	*
	* @param request the role maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Role> deleteRole(RoleMaintenanceRequest request);

		/**
	* Refresh roles.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Role> refreshRoles(RefreshRequest request);

		/**
	* Fetch role by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Role> fetchRoleById(FetchByIdRequest request);

		/**
	* Fetch all roles.
	*
	* @return the internal results response< role>
	*/
		public InternalResultsResponse<Role> fetchAllRoles(Role  role);

		/**
	* Fetch roles by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Role> fetchRolesByRequest(PagedInquiryRequest request);


	//===================================### PAGINA ####======================================
		/**

		/**
		 * Insert pagina.
		 *
	* @param request the pagina maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Pagina> insertPagina(PaginaMaintenanceRequest request);

		/**
	* Update pagina.
	*
	* @param request the pagina maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Pagina> updatePagina(PaginaMaintenanceRequest request);

		/**
	* Delete pagina.
	*
	* @param request the pagina maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Pagina> deletePagina(PaginaMaintenanceRequest request);

		/**
	* Refresh paginas.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Pagina> refreshPaginas(RefreshRequest request);

		/**
	* Fetch pagina by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Pagina> fetchPaginaById(FetchByIdRequest request);

		/**
	* Fetch all paginas.
	*
	* @return the internal results response< pagina>
	*/
		public InternalResultsResponse<Pagina> fetchAllPaginas(Pagina  pagina);

		/**
	* Fetch paginas by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Pagina> fetchPaginasByRequest(PagedInquiryRequest request);


	//===================================### VALIDACAO ####======================================
		/**

		/**
		 * Insert validacao.
		 *
	* @param request the validacao maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Validacao> insertValidacao(ValidacaoMaintenanceRequest request);

		/**
	* Update validacao.
	*
	* @param request the validacao maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Validacao> updateValidacao(ValidacaoMaintenanceRequest request);

		/**
	* Delete validacao.
	*
	* @param request the validacao maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Validacao> deleteValidacao(ValidacaoMaintenanceRequest request);

		/**
	* Refresh validacaos.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Validacao> refreshValidacaos(RefreshRequest request);

		/**
	* Fetch validacao by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Validacao> fetchValidacaoById(FetchByIdRequest request);

		/**
	* Fetch all validacaos.
	*
	* @return the internal results response< validacao>
	*/
		public InternalResultsResponse<Validacao> fetchAllValidacaos(Validacao  validacao);

		/**
	* Fetch validacaos by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Validacao> fetchValidacaosByRequest(PagedInquiryRequest request);


	//===================================### FIELD ####======================================
		/**

		/**
		 * Insert field.
		 *
	* @param request the field maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Field> insertField(FieldMaintenanceRequest request);

		/**
	* Update field.
	*
	* @param request the field maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Field> updateField(FieldMaintenanceRequest request);

		/**
	* Delete field.
	*
	* @param request the field maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Field> deleteField(FieldMaintenanceRequest request);

		/**
	* Refresh fields.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Field> refreshFields(RefreshRequest request);

		/**
	* Fetch field by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Field> fetchFieldById(FetchByIdRequest request);

		/**
	* Fetch all fields.
	*
	* @return the internal results response< field>
	*/
		public InternalResultsResponse<Field> fetchAllFields(Field  field);

		/**
	* Fetch fields by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Field> fetchFieldsByRequest(FieldInquiryRequest request);


	//===================================### AJUDA ####======================================
		/**

		/**
		 * Insert ajuda.
		 *
	* @param request the ajuda maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Ajuda> insertAjuda(AjudaMaintenanceRequest request);

		/**
	* Update ajuda.
	*
	* @param request the ajuda maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Ajuda> updateAjuda(AjudaMaintenanceRequest request);

		/**
	* Delete ajuda.
	*
	* @param request the ajuda maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Ajuda> deleteAjuda(AjudaMaintenanceRequest request);

		/**
	* Refresh ajudas.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Ajuda> refreshAjudas(RefreshRequest request);

		/**
	* Fetch ajuda by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Ajuda> fetchAjudaById(FetchByIdRequest request);

		/**
	* Fetch all ajudas.
	*
	* @return the internal results response< ajuda>
	*/
		public InternalResultsResponse<Ajuda> fetchAllAjudas(Ajuda  ajuda);

		/**
	* Fetch ajudas by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Ajuda> fetchAjudasByRequest(PagedInquiryRequest request);


	//===================================### MENU ####======================================
		/**

		/**
		 * Insert menu.
		 *
	* @param request the menu maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Menu> insertMenu(MenuMaintenanceRequest request);

		/**
	* Update menu.
	*
	* @param request the menu maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Menu> updateMenu(MenuMaintenanceRequest request);

		/**
	* Delete menu.
	*
	* @param request the menu maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Menu> deleteMenu(MenuMaintenanceRequest request);

		/**
	* Refresh menus.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Menu> refreshMenus(RefreshRequest request);

		/**
	* Fetch menu by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Menu> fetchMenuById(FetchByIdRequest request);

		/**
	* Fetch all menus.
	*
	* @return the internal results response< menu>
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
		 * Fetch doisvalor by id.
		 *
		 * @param request the request
	* @return the internal results response
	*/
		public DoisValores fetchDoisValoresById(FetchByIdRequest request);

		/**
	* Insert doisvalor.
	*
	* @param doisvalor the doisvalor
	*
	* @return the internal response
	*/
		public InternalResultsResponse<DoisValores> insertDoisValores(DoisValoresMaintenanceRequest doisvalor);

		/**
	* Update doisvalor.
	*
	* @param doisvalor the doisvalor
	*
	* @return the internal response
	*/
		public InternalResultsResponse<DoisValores> updateDoisValores(DoisValoresMaintenanceRequest doisvalor);

		/**
	* Delete doisvalor.
	*
	* @param doisvalor the doisvalor
	*
	* @return the internal response
	*/
		public InternalResultsResponse<DoisValores> deleteDoisValoresById(DoisValoresMaintenanceRequest doisvalor);

		/**
	* Delete all doisvalors.
	*
	* @return the internal response
	*/
		public InternalResponse deleteAllDoisValoress();

		/**
	* Fetch all doisvalors.
	*
	* @return the list< doisvalor>
	*/
		public InternalResultsResponse<DoisValores> fetchAllDoisValoress(DoisValores  doisvalor);

		/**
	* Fetch doisvalors by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<DoisValores> fetchDoisValoressByRequest(DoisValoresInquiryRequest request);


		//===================================### NOTE ####======================================
		/**

		/**
		 * Insert note.
		 *
	* @param request the note maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Note> insertNote(NoteMaintenanceRequest request);

		/**
	* Update note.
	*
	* @param request the note maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Note> updateNote(NoteMaintenanceRequest request);

		/**
	* Delete note.
	*
	* @param request the note maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Note> deleteNote(NoteMaintenanceRequest request);

		/**
	* Fetch all notes.
	*
	* @return the internal results response< note>
	*/
		public InternalResultsResponse<Note> fetchAllNotes(Note  note);

		/**
	* Fetch notes by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Note> fetchNotesByRequest(PagedInquiryRequest request);

		//===================================### STATUS ####======================================
		/**

		/**
		 * Insert note.
		 *
	* @param request the status maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Status> insertStatus(StatusMaintenanceRequest request);

		/**
	* Update status.
	*
	* @param request the status maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Status> updateStatus(StatusMaintenanceRequest request);

		/**
	* Delete status.
	*
	* @param request the status maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Status> deleteStatus(StatusMaintenanceRequest request);



		/**
	* Fetch all statuss.
	*
	* @return the internal results response< status>
	*/
		public InternalResultsResponse<Status> fetchAllStatuss(Status  status);

		/**
	* Fetch statuss by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Status> fetchStatussByRequest(PagedInquiryRequest request);


		//===================================### EMPRESA ####======================================
		/**

		/**
		 * Insert empresa.
		 *
	* @param request the empresa maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Message> insertMessage(MessageMaintenanceRequest request);

		public InternalResultsResponse<Message> updateMessage(MessageMaintenanceRequest request);


		/**
	* Delete empresa.
	*
	* @param request the empresa maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Message> deleteMessage(MessageMaintenanceRequest request);

		public InternalResultsResponse<Message> fetchMessagesByRequest(MessageInquiryRequest request);


		InternalResultsResponse<Empresa> fetchAllEmpresasByUser(EmpresaInquiryRequest request);
}
