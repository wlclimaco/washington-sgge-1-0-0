package com.prosperitasglobal.sendsolv.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.sendsolv.dac.IDocumentTypeDAC;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentTypeResponse;
import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class DocumentTypeBAIImpl.
 */
public class DocumentTypeBAIImpl implements IDocumentTypeBAI
{

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = DocumentTypeBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LocationBAIImpl.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG. */
	private static final String DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG =
			"sendsolve.base.commonbusinessobjects.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTTYPEVALIDATOR_BUSINESSTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTTYPEVALIDATOR_BUSINESSTYPE_REQUIRED =
			"cbof.base.documenttypevalidator.applicability.required";

	private IDocumentTypeDAC documentTypeDAC; // injected by Spring through setter

	/**
	 * Gets the document type dac.
	 *
	 * @return the document type dac
	 */
	public IDocumentTypeDAC getDocumentTypeDAC()
	{
		return documentTypeDAC;
	}

	/**
	 * Sets the document type dac.
	 *
	 * @param documentTypeDAC the document type dac
	 */
	public void setDocumentTypeDAC(IDocumentTypeDAC documentTypeDAC)
	{
		this.documentTypeDAC = documentTypeDAC;
	}

	@Override
	public DocumentTypeResponse fetchDocumentTypeByRequest(DocumentTypeRequest request)
	{
		DocumentTypeResponse response = new DocumentTypeResponse();
		try
		{
			InternalResponse internalResponse = new InternalResponse();

			// validate fetchId field
			if (ValidationUtil.isNull(request.getBusinessType()))
			{
				internalResponse
						.addFieldErrorMessage(PROSPERITASGLOBAL_BASE_DOCUMENTTYPEVALIDATOR_BUSINESSTYPE_REQUIRED);
			}
			else
			{
				internalResponse = getDocumentTypeDAC().fetchDocumentTypeByRequest(request);

				if (internalResponse.isInError())
				{
					internalResponse.addMessage(DEFAULT_COMMON_BUSINESS_OBJECTS_EXCEPTION_MSG,
							Message.MessageSeverity.Error, Message.MessageLevel.Object,
							new Object[] {internalResponse.getStatus().toString()});
				}
			}
			// Handle the processing for all previous methods regardless of them failing or succeeding.
			QATInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, true);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}

		return response;
	}

}
