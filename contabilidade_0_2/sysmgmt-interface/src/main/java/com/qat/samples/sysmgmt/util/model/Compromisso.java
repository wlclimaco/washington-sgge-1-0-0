package com.qat.samples.sysmgmt.util.model;

import java.util.List;

import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Compromisso extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The dias semanas. */
	private String titulo;

	/** The tipo compromisso. */
	private DoisValores tipoCompromisso;

	/** The hora inicio. */
	private DoisValores vinculado;

	/** The hora final. */
	private DiasHoras horaFinal;

	/** The local. */
	private String local;

	/** The responsavel. */
	private Advogado responsavel;

	/** The enviar email. */
	private Integer enviarEmail;

	/** The enviar mdg telefone. */
	private Integer enviarMdgTelefone;

	/** The quando. */
	private DoisValores quando;

	/** The participante. */
	private List<Cliente> participante;

	/** The participante externo. */
	private List<ParticipanteExterno> participanteExterno;

	/** The documentos. */
	private List<Documento> documentos;
	/**
	 * Default constructor.
	 */
	public Compromisso() {
		super();
	}

	/**
	 * Instantiates a new dias horas.
	 *
	 * @param i
	 *            the i
	 * @param string
	 *            the string
	 */
	public Compromisso(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the tipo compromisso.
	 *
	 * @return the tipo compromisso
	 */
	public DoisValores getTipoCompromisso() {
		return tipoCompromisso;
	}

	/**
	 * Sets the tipo compromisso.
	 *
	 * @param tipoCompromisso
	 *            the new tipo compromisso
	 */
	public void setTipoCompromisso(DoisValores tipoCompromisso) {
		this.tipoCompromisso = tipoCompromisso;
	}

	/**
	 * Gets the vinculado.
	 *
	 * @return the vinculado
	 */
	public DoisValores getVinculado() {
		return vinculado;
	}

	/**
	 * Sets the vinculado.
	 *
	 * @param vinculado
	 *            the new vinculado
	 */
	public void setVinculado(DoisValores vinculado) {
		this.vinculado = vinculado;
	}

	/**
	 * Gets the hora final.
	 *
	 * @return the hora final
	 */
	public DiasHoras getHoraFinal() {
		return horaFinal;
	}

	/**
	 * Sets the hora final.
	 *
	 * @param horaFinal
	 *            the new hora final
	 */
	public void setHoraFinal(DiasHoras horaFinal) {
		this.horaFinal = horaFinal;
	}

	/**
	 * Gets the local.
	 *
	 * @return the local
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * Sets the local.
	 *
	 * @param local
	 *            the new local
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * Gets the responsavel.
	 *
	 * @return the responsavel
	 */
	public Advogado getResponsavel() {
		return responsavel;
	}

	/**
	 * Sets the responsavel.
	 *
	 * @param responsavel
	 *            the new responsavel
	 */
	public void setResponsavel(Advogado responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * Gets the participante.
	 *
	 * @return the participante
	 */
	public List<Cliente> getParticipante() {
		return participante;
	}

	/**
	 * Sets the participante.
	 *
	 * @param participante
	 *            the new participante
	 */
	public void setParticipante(List<Cliente> participante) {
		this.participante = participante;
	}

	/**
	 * Gets the participante externo.
	 *
	 * @return the participante externo
	 */
	public List<ParticipanteExterno> getParticipanteExterno() {
		return participanteExterno;
	}

	/**
	 * Sets the participante externo.
	 *
	 * @param participanteExterno
	 *            the new participante externo
	 */
	public void setParticipanteExterno(List<ParticipanteExterno> participanteExterno) {
		this.participanteExterno = participanteExterno;
	}

	/**
	 * Gets the documentos.
	 *
	 * @return the documentos
	 */
	public List<Documento> getDocumentos() {
		return documentos;
	}

	/**
	 * Sets the documentos.
	 *
	 * @param documentos
	 *            the new documentos
	 */
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	/**
	 * Gets the enviar email.
	 *
	 * @return the enviar email
	 */
	public Integer getEnviarEmail() {
		return enviarEmail;
	}

	/**
	 * Sets the enviar email.
	 *
	 * @param enviarEmail
	 *            the new enviar email
	 */
	public void setEnviarEmail(Integer enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	/**
	 * Gets the enviar mdg telefone.
	 *
	 * @return the enviar mdg telefone
	 */
	public Integer getEnviarMdgTelefone() {
		return enviarMdgTelefone;
	}

	/**
	 * Sets the enviar mdg telefone.
	 *
	 * @param enviarMdgTelefone
	 *            the new enviar mdg telefone
	 */
	public void setEnviarMdgTelefone(Integer enviarMdgTelefone) {
		this.enviarMdgTelefone = enviarMdgTelefone;
	}

	/**
	 * Gets the quando.
	 *
	 * @return the quando
	 */
	public DoisValores getQuando() {
		return quando;
	}

	/**
	 * Sets the quando.
	 *
	 * @param quando
	 *            the new quando
	 */
	public void setQuando(DoisValores quando) {
		this.quando = quando;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "Compromisso [getId()=" + getId() + ", getTitulo()=" + getTitulo() + ", getTipoCompromisso()="
				+ getTipoCompromisso() + ", getVinculado()=" + getVinculado() + ", getHoraFinal()=" + getHoraFinal()
				+ ", getLocal()=" + getLocal() + ", getResponsavel()=" + getResponsavel() + ", getParticipante()="
				+ getParticipante() + ", getParticipanteExterno()=" + getParticipanteExterno() + ", getDocumentos()="
				+ getDocumentos() + ", getEnviarEmail()=" + getEnviarEmail() + ", getEnviarMdgTelefone()="
				+ getEnviarMdgTelefone() + ", getQuando()=" + getQuando() + ", toString()=" + super.toString() + "]";
	}

}
