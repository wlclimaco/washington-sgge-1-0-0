/** create by system gera-java version 1.0.0 15/09/2017 16:6 : 45*/
package com.qat.samples.sysmgmt.bac.Advogado;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogados;
import com.qat.samples.sysmgmt.advocacia.Envolvidos;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.ProcessoCliente;
import com.qat.samples.sysmgmt.advocacia.ProcessoStatus;
import com.qat.samples.sysmgmt.advocacia.request.CompromissoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.EspecialidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.DiasHoras;
import com.qat.samples.sysmgmt.util.model.ParticipanteExterno;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IAdvocaciaBAC. (Business Area Component - BAC)
 */
public interface IAdvocaciaBAC
{



//===================================### ProcessoStatus ####======================================


	/**
* Refresh ProcessoStatuss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ProcessoStatus> refreshProcessoStatuss(RefreshRequest request);

	/**
* Fetch ProcessoStatus by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ProcessoStatus> fetchProcessoStatusById(FetchByIdRequest request);

	/**
* Fetch all ProcessoStatuss.
*
* @return the internal results response< ProcessoStatus>
*/
	public InternalResultsResponse<ProcessoStatus> fetchAllProcessoStatuss(ProcessoStatus  ProcessoStatus);

	/**
* Fetch ProcessoStatuss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ProcessoStatus> fetchProcessoStatussByRequest(PagedInquiryRequest request);


//===================================### DiasHoras ####======================================

	/**
* Refresh DiasHorass.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<DiasHoras> refreshDiasHorass(RefreshRequest request);

	/**
* Fetch DiasHoras by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<DiasHoras> fetchDiasHorasById(FetchByIdRequest request);

	/**
* Fetch all DiasHorass.
*
* @return the internal results response< DiasHoras>
*/
	public InternalResultsResponse<DiasHoras> fetchAllDiasHorass(DiasHoras  DiasHoras);

	/**
* Fetch DiasHorass by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<DiasHoras> fetchDiasHorassByRequest(PagedInquiryRequest request);


//===================================### ESPECIALIDADE ####======================================

	/**
* Refresh especialidades.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Especialidade> refreshEspecialidades(RefreshRequest request);

	/**
* Fetch especialidade by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Especialidade> fetchEspecialidadeById(FetchByIdRequest request);

	/**
* Fetch all especialidades.
*
* @return the internal results response< especialidade>
*/
	public InternalResultsResponse<Especialidade> fetchAllEspecialidades(Especialidade  especialidade);

	/**
* Fetch especialidades by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Especialidade> fetchEspecialidadesByRequest(PagedInquiryRequest request);


//===================================### COMPROMISSO ####======================================
	/**

	/**
	 * Insert compromisso.
	 *
* @param request the compromisso maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Compromisso> insertCompromisso(CompromissoMaintenanceRequest request);

	/**
* Update compromisso.
*
* @param request the compromisso maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Compromisso> updateCompromisso(CompromissoMaintenanceRequest request);

	/**
* Delete compromisso.
*
* @param request the compromisso maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Compromisso> deleteCompromisso(CompromissoMaintenanceRequest request);

	/**
* Refresh compromissos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Compromisso> refreshCompromissos(RefreshRequest request);

	/**
* Fetch compromisso by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Compromisso> fetchCompromissoById(FetchByIdRequest request);

	/**
* Fetch all compromissos.
*
* @return the internal results response< compromisso>
*/
	public InternalResultsResponse<Compromisso> fetchAllCompromissos(Compromisso  compromisso);

	/**
* Fetch compromissos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Compromisso> fetchCompromissosByRequest(PagedInquiryRequest request);


//===================================### ADVOGADOS ####======================================


	/**
* Refresh advogadoss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Advogados> refreshAdvogadoss(RefreshRequest request);

	/**
* Fetch advogados by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogados> fetchAdvogadosById(FetchByIdRequest request);

	/**
* Fetch all advogadoss.
*
* @return the internal results response< advogados>
*/
	public InternalResultsResponse<Advogados> fetchAllAdvogadoss(Advogados  advogados);

	/**
* Fetch advogadoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogados> fetchAdvogadossByRequest(PagedInquiryRequest request);


//===================================### ENVOLVIDOS ####======================================


	/**
* Refresh envolvidoss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Envolvidos> refreshEnvolvidoss(RefreshRequest request);

	/**
* Fetch envolvidos by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Envolvidos> fetchEnvolvidosById(FetchByIdRequest request);

	/**
* Fetch all envolvidoss.
*
* @return the internal results response< envolvidos>
*/
	public InternalResultsResponse<Envolvidos> fetchAllEnvolvidoss(Envolvidos  envolvidos);

	/**
* Fetch envolvidoss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Envolvidos> fetchEnvolvidossByRequest(PagedInquiryRequest request);


//===================================### PARTICIPANTEEXTERNO ####======================================

	/**
* Refresh participanteexternos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ParticipanteExterno> refreshParticipanteExternos(RefreshRequest request);

	/**
* Fetch participanteexterno by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ParticipanteExterno> fetchParticipanteExternoById(FetchByIdRequest request);

	/**
* Fetch all participanteexternos.
*
* @return the internal results response< participanteexterno>
*/
	public InternalResultsResponse<ParticipanteExterno> fetchAllParticipanteExternos(ParticipanteExterno  participanteexterno);

	/**
* Fetch participanteexternos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ParticipanteExterno> fetchParticipanteExternosByRequest(PagedInquiryRequest request);


//===================================### PROCESSO ####======================================
	/**

	/**
	 * Insert processo.
	 *
* @param request the processo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Processo> insertProcesso(ProcessoMaintenanceRequest request);

	/**
* Update processo.
*
* @param request the processo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Processo> updateProcesso(ProcessoMaintenanceRequest request);

	/**
* Delete processo.
*
* @param request the processo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Processo> deleteProcesso(ProcessoMaintenanceRequest request);

	/**
* Refresh processos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Processo> refreshProcessos(RefreshRequest request);

	/**
* Fetch processo by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Processo> fetchProcessoById(FetchByIdRequest request);

	/**
* Fetch all processos.
*
* @return the internal results response< processo>
*/
	public InternalResultsResponse<Processo> fetchAllProcessos(Processo  processo);

	/**
* Fetch processos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Processo> fetchProcessosByRequest(ProcessoInquiryRequest request);


	//===================================### ESPECIALIDADE ####======================================
		/**

		/**
		 * Insert especialidade.
		 *
	* @param request the especialidade maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Especialidade> insertEspecialidade(EspecialidadeMaintenanceRequest request);

		/**
	* Update especialidade.
	*
	* @param request the especialidade maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Especialidade> updateEspecialidade(EspecialidadeMaintenanceRequest request);

		/**
	* Delete especialidade.
	*
	* @param request the especialidade maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Especialidade> deleteEspecialidade(EspecialidadeMaintenanceRequest request);

		//===================================### ARQUIVO ####======================================
		/**

		/**
		 * Insert arquivo.
		 *
	* @param request the arquivo maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Arquivo> insertArquivo(ArquivoMaintenanceRequest request);

		/**
	* Update arquivo.
	*
	* @param request the arquivo maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Arquivo> updateArquivo(ArquivoMaintenanceRequest request);

		/**
	* Delete arquivo.
	*
	* @param request the arquivo maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Arquivo> deleteArquivo(ArquivoMaintenanceRequest request);

		/**
	* Refresh arquivos.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Arquivo> refreshArquivos(RefreshRequest request);

		/**
	* Fetch arquivo by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request);

		/**
	* Fetch all arquivos.
	*
	* @return the internal results response< arquivo>
	*/
		public InternalResultsResponse<Arquivo> fetchAllArquivos(Arquivo  arquivo);

		/**
	* Fetch arquivos by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Arquivo> fetchArquivosByRequest(PagedInquiryRequest request);

		public InternalResultsResponse<ProcessoCliente> fetchProcessoClientesByRequest(PagedInquiryRequest request);

}
