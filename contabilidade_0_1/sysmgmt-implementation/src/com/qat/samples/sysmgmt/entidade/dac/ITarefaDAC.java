package com.qat.samples.sysmgmt.entidade.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.Tarefa;

/**
 * The Interface ITarefaDAC.
 */
public interface ITarefaDAC
{

	/**
	 * Update tarefa.
	 * 
	 * @param tarefa the tarefa
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTarefa(Tarefa tarefa, InternalResultsResponse<?> response);

	/**
	 * Insert tarefa.
	 * 
	 * @param tarefa the tarefa
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTarefa(Tarefa tarefa, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business tarefa.
	 * 
	 * @param tarefa the tarefa
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessTarefa(Tarefa tarefa, InternalResultsResponse<?> response);

	/**
	 * Delete person tarefa.
	 * 
	 * @param tarefa the tarefa
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteTarefa(Tarefa tarefa, InternalResultsResponse<?> response);

	/**
	 * Fetch tarefa by id.
	 * 
	 * @param id the id
	 * @return the internal results response< tarefa>
	 */
	public InternalResultsResponse<Tarefa> fetchTarefaById(Integer id);

	/**
	 * Maintain tarefa associations.
	 * 
	 * @param tarefaList the tarefa list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainTarefaAssociations(List<Tarefa> tarefaList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response);

}
