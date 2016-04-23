package com.qat.webdaptive.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ImportEcoModeAPIController.
 */
@Controller
@RequestMapping("/ecomode")
public class ImportEcoModeController extends CountyBaseController
{

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ImportEcoModeController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ImportEcoModeController";

	/** The Constant UPLOAD. */
	private static final String UPLOAD_FILE = "/upload";

	/** The Constant CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE. */
	private static final String CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE = "sensus.mlc.csvfilevalidator.file.maxuploadsize";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "fileName.csv";

	/**
	 * Upload.
	 * 
	 * @param groupModel the group model
	 * @param servletRequest the servlet request
	 * @return the group model
	 */

	@RequestMapping(value = UPLOAD_FILE, method = RequestMethod.POST)
	@ResponseBody
	public Boolean upload(@RequestParam(value = "uploadTag", required = false) String uploadTag,
			@RequestParam("upload") MultipartFile file,
			MultipartHttpServletRequest request)
	{

		try
		{

			// Upload File
			if (!ValidationUtil.isNull(file))
			{
				MultipartFile files = file;
				String fileName = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				if (files.getSize() > 0)
				{
					inputStream = files.getInputStream();

					System.out.println("size::" + files.getSize());
					fileName = "c:/images/"
							+ files.getOriginalFilename();
					outputStream = new FileOutputStream(fileName);
					System.out.println("fileName:" + files.getOriginalFilename());

					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1)
					{
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();

					// FotoRequest fotoRequest =
					// new FotoRequest(new Foto(fileName.toString(), "c:/images/", files.getOriginalFilename()
					// .toString(),
					// TabelaEnum.EXERCICIO));
					// setUserContext(fotoRequest, request);
					// FotoResponse fotoresponse = getComumBCF().insertFoto(fotoRequest);
					// ecoModeModel.setMessageCode(fotoresponse.getFotos().get(0).getCdfoto().toString());
					// ecoModeModel.setFoto(fotoresponse.getFotos().get(0));
				}
			}

		}
		catch (Exception e)
		{
			System.out.println("error");
		}

		return true;
	}

}
