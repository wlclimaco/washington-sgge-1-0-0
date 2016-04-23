package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class CountyAPIController.
 */
@Controller
@RequestMapping("/county/api")
public class CountyAPIController extends CountyBaseController
{

//	/** The Constant CONTROLLER_EXCEPTION_MSG. */
//	private static final String CONTROLLER_EXCEPTION_MSG = "ImportEcoModeController";
//
//	/** The Constant UPLOAD. */
//	private static final String UPLOAD_FILE = "/upload";
//
//	/** The Constant CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE. */
//	private static final String CSVFILEVALIDATOR_FILE_MAXUPLOADSIZE = "sensus.mlc.csvfilevalidator.file.maxuploadsize";
//
//	/** The Constant FILE_NAME. */
//	public static final String FILE_NAME = "fileName.csv";
//
//	/**
//	 * Refresh bai.
//	 *
//	 * @param request the request
//	 * @return the county response
//	 */
//	@RequestMapping(value = "/refreshBAI", method = RequestMethod.POST)
//	@ResponseBody
//	public CountyResponse refreshBAI(@RequestBody RefreshRequest request)
//	{
//		return countyBERefresh(true, request);
//	}
//
//	/**
//	 * Fetch all bai.
//	 *
//	 * @param request the request
//	 * @return the county response
//	 */
//	@RequestMapping(value = "/fetchAllBAI", method = RequestMethod.POST)
//	@ResponseBody
//	public CountyResponse fetchAllBAI(@RequestBody FetchAllRequest request)
//	{
//		return countyBEFetchAll(true, request);
//	}
//
//	/**
//	 * Refresh bas.
//	 *
//	 * @param request the request
//	 * @return the county response
//	 */
//	@RequestMapping(value = "/refreshBAS", method = RequestMethod.POST)
//	@ResponseBody
//	public CountyResponse refreshBAS(@RequestBody RefreshRequest request)
//	{
//		return countyBERefresh(false, request);
//	}
//
//	/**
//	 * Fetch all bas.
//	 *
//	 * @param request the request
//	 * @return the county response
//	 */
//	@RequestMapping(value = "/fetchAllBAS", method = RequestMethod.POST)
//	@ResponseBody
//	public CountyResponse fetchAllBAS(@RequestBody FetchAllRequest request)
//	{
//		return countyBEFetchAll(false, request);
//	}
//
//	@RequestMapping(value = UPLOAD_FILE, method = RequestMethod.POST)
//	@ResponseBody
//	public Boolean upload(@RequestParam(value = "uploadTag", required = false) String uploadTag,
//			@RequestParam("upload") MultipartFile file,
//			MultipartHttpServletRequest request)
//	{
//
//		try
//		{
//
//			// Upload File
//			if (!ValidationUtil.isNull(file))
//			{
//				MultipartFile files = file;
//				String fileName = null;
//				InputStream inputStream = null;
//				OutputStream outputStream = null;
//				if (files.getSize() > 0)
//				{
//					inputStream = files.getInputStream();
//
//					System.out.println("size::" + files.getSize());
//					fileName = "c:/images/"
//							+ files.getOriginalFilename();
//					outputStream = new FileOutputStream(fileName);
//					System.out.println("fileName:" + files.getOriginalFilename());
//
//					int readBytes = 0;
//					byte[] buffer = new byte[10000];
//					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1)
//					{
//						outputStream.write(buffer, 0, readBytes);
//					}
//					outputStream.close();
//					inputStream.close();
//
//					// FotoRequest fotoRequest =
//					// new FotoRequest(new Foto(fileName.toString(), "c:/images/", files.getOriginalFilename()
//					// .toString(),
//					// TabelaEnum.EXERCICIO));
//					// setUserContext(fotoRequest, request);
//					// FotoResponse fotoresponse = getComumBCF().insertFoto(fotoRequest);
//					// ecoModeModel.setMessageCode(fotoresponse.getFotos().get(0).getCdfoto().toString());
//					// ecoModeModel.setFoto(fotoresponse.getFotos().get(0));
//				}
//			}
//
//		}
//		catch (Exception e)
//		{
//			System.out.println("error");
//		}
//
//		return true;
//	}
}
