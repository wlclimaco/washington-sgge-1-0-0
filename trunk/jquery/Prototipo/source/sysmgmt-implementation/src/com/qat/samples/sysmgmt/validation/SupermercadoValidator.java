package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * The Class BundleDValidator.
 */
public class SupermercadoValidator implements IValidator
{

	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED =
			"sysmgmt.base.bundlevalidator.bundledesc.required";
	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED =
			"sysmgmt.base.bundlevalidator.bundlecode.required";
	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED =
			"sysmgmt.base.bundlevalidator.id.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		Supermercado bundle =
				(Supermercado)validationContext.getObjectToBeValidated(Supermercado.class.getSimpleName());

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateCidadeId(validationContext.getMessages(), bundle);
				break;
			case UPDATE:
				validateCidadeId(validationContext.getMessages(), bundle);
				validateCidadeCidade(validationContext.getMessages(), bundle);
				validateCidadeEstado(validationContext.getMessages(), bundle);
				break;
			default:
				validateCidadeCidade(validationContext.getMessages(), bundle);
				validateCidadeEstado(validationContext.getMessages(), bundle);
				break;
		}
	}

	private void validateCidadeId(List<MessageInfo> list, Supermercado bundle)
	{
		ValidationUtil.isNullOrZero(bundle.getId(), SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED, list);
	}

	private void validateCidadeCidade(List<MessageInfo> list, Supermercado bundle)
	{
		ValidationUtil.isNullOrEmpty(bundle.getEnderecos().get(0).getNome(),
				SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED, list);
	}

	private void validateCidadeEstado(List<MessageInfo> list, Supermercado bundle)
	{
		ValidationUtil.isNullOrEmpty(bundle.getDocumentos().get(0).getCpfCnpj(),
				SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED,
				list);
	}
}
