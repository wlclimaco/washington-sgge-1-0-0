package com.sensus.lc.controller.foto;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload
{

	MultipartFile file;

	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

}