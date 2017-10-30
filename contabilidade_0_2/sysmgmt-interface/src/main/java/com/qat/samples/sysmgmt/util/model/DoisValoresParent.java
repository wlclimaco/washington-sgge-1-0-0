package com.qat.samples.sysmgmt.util.model;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class DoisValoresParent extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private DoisValores doisvalor;

	/**
	 * Default constructor.
	 */
	public DoisValoresParent()
	{
		super();
	}



	public DoisValoresParent(Integer id) {
		super();
		this.id = id;
	}



	public DoisValoresParent(int i, String string) {
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public DoisValores getDoisvalor() {
		return doisvalor;
	}



	public void setDoisvalor(DoisValores doisvalor) {
		this.doisvalor = doisvalor;
	}



	@Override
	public String toString() {
		return "DoisValoresParent [getId()=" + getId() + ", getDoisvalor()=" + getDoisvalor() + ", toString()="
				+ super.toString() + "]";
	}





}
