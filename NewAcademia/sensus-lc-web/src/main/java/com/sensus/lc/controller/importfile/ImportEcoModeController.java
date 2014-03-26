package com.sensus.lc.controller.importfile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.TabelaEnum;
import com.sensus.lc.comum.bcf.IComumBCF;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.controller.importfile.model.EcoModeModel;
import com.sensus.lc.ecomode.bcf.IEcoModeBCF;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.response.FotoResponse;
import com.sensus.lc.tag.model.Tag;

/**
 * The Class ImportEcoModeAPIController.
 */
@Controller
@RequestMapping("/ecomode")
public class ImportEcoModeController extends BaseViewController implements HandlerExceptionResolver
{

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ImportEcoModeController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ImportEcoModeController";

	/** The Constant UPLOAD. */
	private static final String UPLOAD_FILE = "/upload";

	/** The Constant CSV_MIME_TYPE. */
	private static final String CSV_MIME_TYPE = "text/csv";

	/** The Constant CSV_EXTENSION. */
	private static final String CSV_EXTENSION = "csv";

	/** The Constant FILE_IS_NOT_CSV. */
	private static final String FILE_IS_NOT_CSV = "sensus.mlc.csvfilevalidator.file.invalid";

	/** The Constant CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE. */
	private static final String CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE = "sensus.mlc.csvfilevalidator.file.maxuploadsize";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "fileName.csv";

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	private IComumBCF comumBCF;

	public IComumBCF getComumBCF()
	{
		return comumBCF;
	}

	@Resource
	public void setComumBCF(IComumBCF comumBCF)
	{
		this.comumBCF = comumBCF;
	}

	/**
	 * Gets the eco mode bcf.
	 * 
	 * @return the eco mode bcf
	 */
	public IEcoModeBCF getEcoModeBCF()
	{
		return ecoModeBCF;
	}

	/**
	 * Sets the eco mode bcf.
	 * 
	 * @param ecoModeBCF the new eco mode bcf
	 */
	@Resource
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

	/**
	 * Upload.
	 * 
	 * @param groupModel the group model
	 * @param servletRequest the servlet request
	 * @return the group model
	 */
	@RequestMapping(value = UPLOAD_FILE, method = RequestMethod.POST)
	public EcoModeModel upload(@RequestParam(value = "uploadTag", required = false) String uploadTag,
			@RequestParam("upload") MultipartFile file,
			MultipartHttpServletRequest request)
	{

		EcoModeResponse response = new EcoModeResponse();

		EcoModeModel ecoModeModel = new EcoModeModel();

		try
		{
			EcoModeRequest ecoModeRequest = new EcoModeRequest();

			// ADD user context to request
			setUserContext(ecoModeRequest, request);

			if (!ValidationUtil.isNull(uploadTag))
			{
				String[] tagIds = uploadTag.split(",");
				ecoModeRequest.setTags(new ArrayList<Tag>());
				Tag tag;

				for (String id : tagIds)
				{
					tag = new Tag();
					tag.setId(Integer.parseInt(id.trim()));
					ecoModeRequest.getTags().add(tag);
				}
			}

			// Upload File
			if (!ValidationUtil.isNull(file))
			{
				// String[] extension = StringUtils.splitByWholeSeparator(upload.getOriginalFilename(), ".");
				// if (!CSV_MIME_TYPE.equals(upload.getContentType())
				// && !CSV_EXTENSION.equals(extension[extension.length - 1]))
				// {
				// ecoModeModel.setMessageCode(FILE_IS_NOT_CSV);
				// ecoModeModel.setOperationSuccess(Boolean.FALSE);
				// throw new IllegalFormatFlagsException(FILE_IS_NOT_CSV);
				// }

				// File f = new File(FILE_NAME);
				// upload.transferTo(f);
				// Some type of file processing...
				System.err.println("-------------------------------------------");
				System.err.println("Test upload: " + file);
				System.err.println("-------------------------------------------");
				// for (Integer i = 0; i < files.size(); i++)
				// {
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

					// BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
					// BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
					// File destination = new File("c:/images/"); // something like
					// C:/Users/tom/Documents/nameBasedOnSomeId.png
					// ImageIO.write(src, "jpg", destination);
					// ecoModeRequest.setEcoModeCSVImport(upload.get);

					Foto foto =
							new Foto(fileName.toString(), "c:/images/", files.getOriginalFilename().toString(),
									TabelaEnum.EXERCICIO);
					FotoRequest fotoRequest = new FotoRequest(foto);
					FotoResponse fotoresponse = getComumBCF().insertFoto(fotoRequest);
					ecoModeModel.setMessageCode(fotoresponse.getFotos().get(0).getCdfoto().toString());
				}
			}
			response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);

			if (!ValidationUtil.isNullOrEmpty(response.getMessageList()))
			{
				MessageInfo messageInfo = response.getMessageInfoList().get(0);
				ecoModeModel.setArguments(getMapper().writeValueAsString(messageInfo.getArguments()));
				ecoModeModel.setOperationSuccess(response.isOperationSuccess());
			}
			// }
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return ecoModeModel;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object object,
			Exception exception)
	{
		ModelAndView modelAndView = new ModelAndView("ecomode/upload");
		EcoModeModel ecoModeModel = new EcoModeModel();

		if (exception instanceof MaxUploadSizeExceededException)
		{
			ecoModeModel.setMessageCode(CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE);
			ecoModeModel.setOperationSuccess(Boolean.FALSE);
			try
			{
				ecoModeModel.setArguments(getMapper().writeValueAsString(
						Arrays.asList(((MaxUploadSizeExceededException)exception).getMaxUploadSize())));
			}
			catch (Exception e)
			{
				LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
				modelAndView.addObject(RESPONSE, null);
			}
		}
		else
		{
			ecoModeModel.setMessageCode(DEFAULT_EXCEPTION_MSG);
			ecoModeModel.setOperationSuccess(Boolean.FALSE);
		}

		modelAndView.addObject(ecoModeModel);

		return modelAndView;
	}
}
