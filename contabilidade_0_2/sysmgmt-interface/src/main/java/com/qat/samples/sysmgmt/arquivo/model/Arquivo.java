package com.qat.samples.sysmgmt.arquivo.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Arquivo extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	/** The parent key. */
	private Integer parentKey;

	/**
	 * The parent key type. *
	 * 
	 * /** The keyword text.
	 */
	private String keywordText;

	/** The is action required. */
	private Boolean isActionRequired;

	/** The note text. */
	private String noteText;

	/** The expiration date. */
	private Long expirationDate;

	/** The value. */
	private String value;

	/**
	 * The Constructor.
	 */
	public Arquivo()
	{

	}

}