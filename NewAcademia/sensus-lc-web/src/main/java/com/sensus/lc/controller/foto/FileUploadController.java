package com.sensus.lc.controller.foto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller
@RequestMapping("/fotos")
class FileUploadController extends SimpleFormController
{

	public FileUploadController()
	{
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}

	@Override
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception
	{

		FileUpload file = (FileUpload)command;

		MultipartFile multipartFile = file.getFile();

		String fileName = "";

		if (multipartFile != null)
		{
			fileName = multipartFile.getOriginalFilename();
			// do whatever you want
		}

		return new ModelAndView("FileUploadSuccess", "fileName", fileName);

	}

	/*
	 * @Override
	 * protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	 * throws ServletException {
	 * // Convert multipart object to byte[]
	 * binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	 * }
	 */

}