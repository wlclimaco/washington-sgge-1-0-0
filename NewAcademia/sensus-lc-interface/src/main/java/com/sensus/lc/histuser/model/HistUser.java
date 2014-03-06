package com.sensus.lc.histuser.model;

import java.util.List;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.user.model.User;

/**
 * The Class Academia.
 */
@SuppressWarnings("serial")
public class HistUser extends PrincipalClass
{

	/** The cd hist user. */
	private Integer cdHistUser;

	/** The cd user. */
	private User cdUser;

	/** The acao. */
	private Integer acao;

	/** The cd table. */
	private Integer cdTable;

	/** The id. */
	private String id;

	/** The posts. */
	List<Posts> posts;

	/**
	 * Gets the cd hist user.
	 * 
	 * @return the cd hist user
	 */
	public Integer getCdHistUser()
	{
		return cdHistUser;
	}

	/**
	 * Sets the cd hist user.
	 * 
	 * @param cdHistUser the new cd hist user
	 */
	public void setCdHistUser(Integer cdHistUser)
	{
		this.cdHistUser = cdHistUser;
	}

	/**
	 * Gets the cd user.
	 * 
	 * @return the cd user
	 */
	public User getCdUser()
	{
		return cdUser;
	}

	/**
	 * Sets the cd user.
	 * 
	 * @param cdUser the new cd user
	 */
	public void setCdUser(User cdUser)
	{
		this.cdUser = cdUser;
	}

	/**
	 * Gets the acao.
	 * 
	 * @return the acao
	 */
	public Integer getAcao()
	{
		return acao;
	}

	/**
	 * Sets the acao.
	 * 
	 * @param acao the new acao
	 */
	public void setAcao(Integer acao)
	{
		this.acao = acao;
	}

	/**
	 * Gets the cd table.
	 * 
	 * @return the cd table
	 */
	public Integer getCdTable()
	{
		return cdTable;
	}

	/**
	 * Sets the cd table.
	 * 
	 * @param cdTable the new cd table
	 */
	public void setCdTable(Integer cdTable)
	{
		this.cdTable = cdTable;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Gets the posts.
	 * 
	 * @return the posts
	 */
	public List<Posts> getPosts()
	{
		return posts;
	}

	/**
	 * Sets the posts.
	 * 
	 * @param posts the new posts
	 */
	public void setPosts(List<Posts> posts)
	{
		this.posts = posts;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.PrincipalClass#toString()
	 */
	@Override
	public String toString()
	{
		return "Histuser [getCdHistUser()=" + getCdHistUser() + ", getCdUser()=" + getCdUser() + ", getAcao()="
				+ getAcao() + ", getCdTable()=" + getCdTable() + ", getId()=" + getId() + ", getPosts()=" + getPosts()
				+ ", toString()=" + super.toString() + "]";
	}

}
