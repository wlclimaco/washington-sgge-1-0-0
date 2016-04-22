package com.qat.samples.sysmgmt.util.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Imagem extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer tabId;

	/** The description. */
	private String description;

	/** The numero. */
	private String numero;

	/** The nome. */
	private String nome;

	/** The left. */
	private Integer left;

	/** The top. */
	private Integer top;

	/** The width. */
	private Integer width;

	/** The height. */
	private Integer height;

	/** The z index. */
	private Integer zIndex;

	/** The label. */
	private String label;

	/** The text. */
	private String text;

	/** The classe. */
	private String classe;

	/** The mask. */
	private String mask;

	/** The validation. */
	private String validation;

	/** The size. */
	private Integer size;

	/** The busca. */
	private FieldBusca busca;

	/**
	 * Default constructor.
	 */
	public Imagem()
	{
		super();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the numero.
	 * 
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 * 
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome the new nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the left.
	 * 
	 * @return the left
	 */
	public Integer getLeft()
	{
		return left;
	}

	/**
	 * Sets the left.
	 * 
	 * @param left the new left
	 */
	public void setLeft(Integer left)
	{
		this.left = left;
	}

	/**
	 * Gets the top.
	 * 
	 * @return the top
	 */
	public Integer getTop()
	{
		return top;
	}

	/**
	 * Sets the top.
	 * 
	 * @param top the new top
	 */
	public void setTop(Integer top)
	{
		this.top = top;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public Integer getWidth()
	{
		return width;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width the new width
	 */
	public void setWidth(Integer width)
	{
		this.width = width;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public Integer getHeight()
	{
		return height;
	}

	/**
	 * Sets the height.
	 * 
	 * @param height the new height
	 */
	public void setHeight(Integer height)
	{
		this.height = height;
	}

	/**
	 * Gets the z index.
	 * 
	 * @return the z index
	 */
	public Integer getzIndex()
	{
		return zIndex;
	}

	/**
	 * Sets the z index.
	 * 
	 * @param zIndex the new z index
	 */
	public void setzIndex(Integer zIndex)
	{
		this.zIndex = zIndex;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label the new label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Sets the text.
	 * 
	 * @param text the new text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * Gets the classe.
	 * 
	 * @return the classe
	 */
	public String getClasse()
	{
		return classe;
	}

	/**
	 * Sets the classe.
	 * 
	 * @param classe the new classe
	 */
	public void setClasse(String classe)
	{
		this.classe = classe;
	}

	/**
	 * Gets the mask.
	 * 
	 * @return the mask
	 */
	public String getMask()
	{
		return mask;
	}

	/**
	 * Sets the mask.
	 * 
	 * @param mask the new mask
	 */
	public void setMask(String mask)
	{
		this.mask = mask;
	}

	/**
	 * Gets the validation.
	 * 
	 * @return the validation
	 */
	public String getValidation()
	{
		return validation;
	}

	/**
	 * Sets the validation.
	 * 
	 * @param validation the new validation
	 */
	public void setValidation(String validation)
	{
		this.validation = validation;
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public Integer getSize()
	{
		return size;
	}

	/**
	 * Sets the size.
	 * 
	 * @param size the new size
	 */
	public void setSize(Integer size)
	{
		this.size = size;
	}

	/**
	 * Gets the busca.
	 * 
	 * @return the busca
	 */
	public FieldBusca getBusca()
	{
		return busca;
	}

	/**
	 * Sets the busca.
	 * 
	 * @param busca the new busca
	 */
	public void setBusca(FieldBusca busca)
	{
		this.busca = busca;
	}

	/**
	 * @return the tabId
	 */
	public Integer getTabId()
	{
		return tabId;
	}

	/**
	 * @param tabId the tabId to set
	 */
	public void setTabId(Integer tabId)
	{
		this.tabId = tabId;
	}

	@Override
	public String toString()
	{
		return "Field [getId()=" + getId() + ", getDescription()=" + getDescription() + ", getNumero()=" + getNumero()
				+ ", getNome()=" + getNome() + ", getLeft()=" + getLeft() + ", getTop()=" + getTop() + ", getWidth()="
				+ getWidth() + ", getHeight()=" + getHeight() + ", getzIndex()=" + getzIndex() + ", getLabel()="
				+ getLabel() + ", getText()=" + getText() + ", getClasse()=" + getClasse() + ", getMask()=" + getMask()
				+ ", getValidation()=" + getValidation() + ", getSize()=" + getSize() + ", getBusca()=" + getBusca()
				+ ", getTabId()=" + getTabId() + ", toString()=" + super.toString() + "]";
	}

}
