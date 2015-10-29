package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.util.Cidade;

/**
 * The Class BundleDValidator.
 */
public class EmpresaValidator implements IValidator
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
		Cidade bundle = (Cidade)validationContext.getObjectToBeValidated(Cidade.class.getSimpleName());

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

	private void validateCidadeId(List<MessageInfo> list, Cidade bundle)
	{
		ValidationUtil.isNullOrZero(bundle.getId(), SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED, list);
	}

	private void validateCidadeCidade(List<MessageInfo> list, Cidade bundle)
	{
		// ValidationUtil.isNullOrEmpty(bundle.getEstado(), SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED, list);
	}

	private void validateCidadeEstado(List<MessageInfo> list, Cidade bundle)
	{
		// ValidationUtil.isNullOrEmpty(bundle.getEstado(), SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED,
		// list);
	}
}
