package com.sensus.mlc.wui.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.wui.BaseController;

@Controller
@RequestMapping("/export")
public class ExportFileController extends BaseController
{

	private IProcessBCF processBCF;

	/** The Constant ID. */
	private static final String ID = "id";

	private static final String FILE_NAME = "fileName";

	private static final String ACTION = "action";

	private static final String UPDATE_CSV_DOWNLOADED = "updateCSVDownloaded";

	private static final String DOWNLOAD = "download";

	@RequestMapping(value = "/download/{fileName}/{updateCSVDownloaded}/{id}", method = RequestMethod.GET)
	public void download(@PathVariable("fileName") String fileName,
			@PathVariable("updateCSVDownloaded") String updateCSVDownloaded,
			@PathVariable("id") String id, HttpServletResponse response) throws IOException
	{

		// MapResponse mapResponse = new MapResponse();
		try
		{

			// get your file as InputStream
			File file = new File(fileName);

			response.setHeader("Pragma", "public");
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Content-type", "application-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			response.setHeader("Content-Transfer-Encoding", "binary");

			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			// if (LOG.isErrorEnabled())
			// {
			// LOG.error(ERROR_MESSAGE, ex);
			// }
			// Util.treatFatalError(mapResponse);
		}

		return;

	}

	/*
	 * @RequestMapping(value = "/downloadTwo", method = RequestMethod.GET)
	 * @ResponseBody
	 * public void downloadTwo(@RequestBody Map<String, Object> mapRequest, HttpServletRequest request) {
	 * Response response = new Response();
	 * try
	 * {
	 * if (ValidationUtil.isNull(mapRequest.get(ACTION))){
	 * String sAction = mapRequest.get(ACTION).toString();
	 * switch (sAction) {
	 * case UPDATE_CSV_DOWNLOADED:
	 * response = getProcessBCF().updateCSVDownloaded(new ProcessRequest());
	 * //ProcessRequest processRequest = (ProcessRequest)fileRequest;
	 * ProcessRequest processRequest = new ProcessRequest();
	 * setUserContext(processRequest, request);
	 * Process process = new Process();
	 * process.setId((Integer)mapRequest.get(ID));
	 * LCActionParameter lcActionParameter = new LCActionParameter();
	 * lcActionParameter.setProperty(PropertyEnum.FILE_NAME);
	 * lcActionParameter.setActionValue((String)mapRequest.get(FILE_NAME));
	 * LCAction action = new LCAction(LCActionTypeEnum.GENERATE_CSV_FILE);
	 * action.getActionParameters().add(lcActionParameter);
	 * process.setLcAction(action);
	 * processRequest.setProcess(process);
	 * response = (ProcessResponse)getProcessBCF().updateCSVDownloaded(processRequest);
	 * break;
	 * case DOWNLOAD:
	 * //setProcessSearchResult(new SearchJsonResult());
	 * //if (!(getFileName().indexOf("..") > 0))
	 * //{
	 * //setInputStream(new FileInputStream(getFileName()));
	 * //String[] name = getFileName().split(FILE_NAME_SPLIT);
	 * //Integer nameIndex = name.length - 1;
	 * //setCsvName(name[nameIndex]);
	 * //deleteFile();
	 * //}
	 * return;
	 * break;
	 * default:
	 * break;
	 * }
	 * }
	 * }
	 * catch (Exception ex)
	 * {
	 * //TODO
	 * }
	 * return response;
	 * }
	 */

	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}
}
