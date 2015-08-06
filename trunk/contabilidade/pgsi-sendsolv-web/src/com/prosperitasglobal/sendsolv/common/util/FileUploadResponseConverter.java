package com.prosperitasglobal.sendsolv.common.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import com.sensus.common.web.json.CustomObjectMapper;
import com.sensus.dm.common.model.FileUploadResponse;

/**
 * Unique HttpMessageConverter used for file upload purposes.
 * This converter will take an FileUploadResponse object and transform
 * it into JSON String, returning the String as the response body.
 */
public class FileUploadResponseConverter extends AbstractHttpMessageConverter<FileUploadResponse>
{

	/** The default charset. */
	private final Charset defaultCharset;

	/** The available charsets. */
	private final List<Charset> availableCharsets;

	/** The mapper. */
	private CustomObjectMapper mapper;

	/**
	 * Gets the mapper.
	 *
	 * @return the mapper
	 */
	public CustomObjectMapper getMapper()
	{
		return mapper;
	}

	/**
	 * Sets the mapper.
	 *
	 * @param mapper the new mapper
	 */
	@Resource
	public void setMapper(CustomObjectMapper mapper)
	{
		this.mapper = mapper;
	}

	/**
	 * The Constructor.
	 */
	public FileUploadResponseConverter()
	{
		this(Charset.forName("ISO-8859-1"));
	}

	/**
	 * The Constructor.
	 *
	 * @param defaultCharset the default charset
	 */
	public FileUploadResponseConverter(Charset defaultCharset)
	{
		this(defaultCharset, new ArrayList<Charset>(Charset.availableCharsets().values()));
	}

	/**
	 * The Constructor.
	 *
	 * @param defaultCharset the default charset
	 * @param availableCharsets the available charsets
	 */
	public FileUploadResponseConverter(Charset defaultCharset, List<Charset> availableCharsets)
	{
		super(new MediaType("text", "plain", defaultCharset)); // This will set the only acceptable response type
		this.defaultCharset = defaultCharset;
		this.availableCharsets = availableCharsets;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#readInternal(java.lang.Class,
	 * org.springframework.http.HttpInputMessage)
	 */
	@Override
	protected FileUploadResponse readInternal(Class<? extends FileUploadResponse> arg0, HttpInputMessage arg1)
			throws IOException, HttpMessageNotReadableException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#supports(java.lang.Class)
	 */
	@Override
	protected boolean supports(Class<?> arg0)
	{
		if (arg0.getSimpleName().equalsIgnoreCase("FileUploadResponse"))
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#writeInternal(java.lang.Object,
	 * org.springframework.http.HttpOutputMessage)
	 */
	@Override
	protected void writeInternal(FileUploadResponse response, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException
	{
		outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());

		Charset charset = getContentTypeCharset(outputMessage.getHeaders().getContentType());
		FileCopyUtils.copy(getMapper().writeValueAsString(response), new OutputStreamWriter(outputMessage.getBody(),
				charset));
	}

	/**
	 * Gets the accepted charsets.
	 *
	 * @return the accepted charsets
	 */
	protected List<Charset> getAcceptedCharsets()
	{
		return availableCharsets;
	}

	/**
	 * Gets the default charset.
	 *
	 * @return the default charset
	 */
	public Charset getDefaultCharset()
	{
		return defaultCharset;
	}

	/**
	 * Gets the content type charset.
	 *
	 * @param contentType the content type
	 * @return the content type charset
	 */
	private Charset getContentTypeCharset(MediaType contentType)
	{
		if ((contentType != null) && (contentType.getCharSet() != null))
		{
			return contentType.getCharSet();
		}
		else
		{
			return getDefaultCharset();
		}
	}

}