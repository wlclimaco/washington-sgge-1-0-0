package com.qat.samples.sysmgmt.advocacia;


import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class ProcessoUsuarios extends ModelCosmeDamiao
{

    /** The econtabil id for the ProcessoUsuarios. */
    private Integer id;

    /** The econtabil usuario for the ProcessoUsuarios. */
    private Usuario usuarioId;

    /** The econtabil processo for the ProcessoUsuarios. */
    private Integer processo;



    /**
     * Default constructor.
     */
    public ProcessoUsuarios()
    {
        super();
    }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getProcesso() {
		return processo;
	}



	public void setProcesso(Integer processo) {
		this.processo = processo;
	}



	public Usuario getUsuarioId() {
		return usuarioId;
	}



	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}



	@Override
	public String toString() {
		return "ProcessoUsuarios [getId()=" + getId() + ", getProcesso()=" + getProcesso() + ", getUsuarioId()="
				+ getUsuarioId() + ", toString()=" + super.toString() + "]";
	}

 }
