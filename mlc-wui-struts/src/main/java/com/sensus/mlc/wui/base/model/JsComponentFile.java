package com.sensus.mlc.wui.base.model;


/**
 * Represents a single JavaScript or CSS file to be imported by a page. Used by the JsComponentLoader and LayoutAction.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class JsComponentFile
{
	/**
	 * The file type.
	 */
	private FileType type;

	/**
	 * The file path.
	 */
	private String path;

	/**
	 * Whether the file path is absolute (true) or relative (false).
	 */
	private boolean absolutePath;

	private boolean sec;

	/**
	 * Represents the different types of JavaScript and CSS files. Types are differentiated both by how the link needs
	 * to be rendered in HTML and where in the page the link needs to be inserted.
	 * 
	 * @author Anke Doerfel-Parker
	 * 
	 */
	public enum FileType
	{

		/** The SCRIP t_ local. */
		SCRIPT_LOCAL,
		/** The SCRIP t_ absolute. */
		SCRIPT_ABSOLUTE,
		/** The STYLESHEE t_ local. */
		STYLESHEET_LOCAL,
		/** The STYLESHEE t_ absolute. */
		STYLESHEET_ABSOLUTE,
		/** The SETTINGS. */
		SETTINGS,
		/** The SEC */
		SEC_LOCAL
	};

	/**
	 * Constructs a new JsComponentFile.
	 */
	public JsComponentFile()
	{
	}

	/**
	 * Constructs a new JsComponentFile from the type, path and absolute path indicator.
	 * 
	 * @param typeIn the file type
	 * @param pathIn the file path
	 * @param absolutePathIn whether the path is absolute (true) or relative (false)
	 */
	public JsComponentFile(FileType typeIn, String pathIn, boolean absolutePathIn, boolean sec)
	{
		setType(typeIn);
		setPath(pathIn);
		setAbsolutePath(absolutePathIn);
		setSec(sec);
	}

	/**
	 * Gets the file type.
	 * 
	 * @return the file type
	 */
	public FileType getType()
	{
		return type;
	}

	/**
	 * Sets the file type.
	 * 
	 * @param typeIn the file type.
	 */
	public void setType(FileType typeIn)
	{
		type = typeIn;
	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Sets the path.
	 * 
	 * @param pathIn the path.
	 */
	public void setPath(String pathIn)
	{
		if (pathIn != null)
		{
			path = pathIn.trim();
		}
	}

	/**
	 * Sets the absolute path indicator. Use <code>true</code> for absolute paths, otherwise <code>false</code>
	 * 
	 * @param absolutePathIn the absolute path indicator
	 */
	public void setAbsolutePath(boolean absolutePathIn)
	{
		absolutePath = absolutePathIn;
	}

	/**
	 * Gets the absolute path indicator. Returns <code>true</code> for absolute paths, otherwise <code>false</code>
	 * 
	 * @return the absolute path indicator
	 */
	public boolean isAbsolutePath()
	{
		return absolutePath;
	}

	public boolean getSec()
	{
		return sec;
	}

	public void setSec(Boolean sec)
	{
		this.sec = sec;
	}

	@Override
	public String toString()
	{
		return "JsComponentFile [getType()=" + getType() + ", getPath()=" + getPath() + ", isAbsolutePath()="
				+ isAbsolutePath() + ", getSec()=" + getSec() + "]";
	}

}